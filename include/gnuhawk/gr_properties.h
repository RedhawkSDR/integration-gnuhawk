/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file 
 * distributed with this source distribution.
 * 
 * This file is part of GNUHAWK.
 * 
 * GNUHAWK is free software: you can redistribute it and/or modify is under the 
 * terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * 
 * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program.  If not, see http://www.gnu.org/licenses/.
 */

#ifndef GR_PROPERTIES_H
#define GR_PROPERTIES_H

#include <ossie/Resource_impl.h>
#include <boost/scoped_ptr.hpp>

// This code should be moved to the REDHAWK core framework, in CorbaUtils.h
namespace ossie {
    namespace corba {

        template <class T, class SeqT>
        inline bool vector_extract (const CORBA::Any& _a, std::vector<T>& _s)
        {
            SeqT* seq;
            if (_a >>= seq) {
                size_t length = seq->length();
                if (length == 0) {
                    _s.clear();
                } else {
                    T* begin = &(*seq)[0];
                    _s.assign(begin, begin+length);
                }
                return true;
            }
            return false;
        }

        template <class T, class SeqT>
        inline void vector_insert (CORBA::Any& _a, const std::vector<T>& _s)
        {
            SeqT seq(_s.size(), _s.size(), (T*)&_s[0], 0);
            _a <<= seq;
        }

        inline bool element_convert(bool in) {
            return in;
        }

        inline char element_convert(CORBA::Char in) {
            return in;
        }

        inline CORBA::Char element_convert(char in) {
            return in;
        }

        inline std::string element_convert(_CORBA_String_element in) {
            return static_cast<const char*>(in);
        }
        
        inline const char* element_convert(const std::string& in) {
            return in.c_str();
        }
        
        template <class T, class SeqT>
        inline bool vector_extract_convert (const CORBA::Any& _a, std::vector<T>& _s)
        {
            SeqT* seq;
            if (_a >>= seq) {
                _s.resize(seq->length());
                for (size_t ii = 0; ii < _s.size(); ++ii) {
                    _s[ii] = element_convert((*seq)[ii]);
                }
                return true;
            }
            return false;
        }

        template <class T, class SeqT>
        inline void vector_insert_convert (CORBA::Any& _a, const std::vector<T>& _s)
        {
            SeqT seq;
            seq.length(_s.size());
            for (size_t ii = 0; ii < _s.size(); ++ii) {
                seq[ii] = element_convert(_s[ii]);
            }
            _a <<= seq;
        }

    } // namespace ossie
} //namespace corba

#define ANY_VECTOR_OPERATORS(T,SEQ)                                 \
inline bool operator >>= (const CORBA::Any& _a, std::vector<T>& _s) \
{                                                                   \
    return ossie::corba::vector_extract<T,SEQ>(_a, _s);             \
}                                                                   \
inline void operator <<= (CORBA::Any& _a, const std::vector<T>& _s) \
{                                                                   \
    ossie::corba::vector_insert<T,SEQ>(_a, _s);                     \
}                                                                   \

ANY_VECTOR_OPERATORS(CORBA::Octet, CORBA::OctetSeq);
ANY_VECTOR_OPERATORS(CORBA::Short, CORBA::ShortSeq);
ANY_VECTOR_OPERATORS(CORBA::UShort, CORBA::UShortSeq);
ANY_VECTOR_OPERATORS(CORBA::Long, CORBA::LongSeq);
ANY_VECTOR_OPERATORS(CORBA::ULong, CORBA::ULongSeq);
ANY_VECTOR_OPERATORS(CORBA::LongLong, CORBA::LongLongSeq);
ANY_VECTOR_OPERATORS(CORBA::ULongLong, CORBA::ULongLongSeq);
ANY_VECTOR_OPERATORS(CORBA::Float, CORBA::FloatSeq);
ANY_VECTOR_OPERATORS(CORBA::Double, CORBA::DoubleSeq);
#undef ANY_VECTOR_OPERATORS

#define ANY_VECTOR_CONVERT_OPERATORS(T,SEQ)                         \
inline bool operator >>= (const CORBA::Any& _a, std::vector<T>& _s) \
{                                                                   \
    return ossie::corba::vector_extract_convert<T,SEQ>(_a, _s);     \
}                                                                   \
inline void operator <<= (CORBA::Any& _a, const std::vector<T>& _s) \
{                                                                   \
    ossie::corba::vector_insert_convert<T,SEQ>(_a, _s);             \
}

ANY_VECTOR_CONVERT_OPERATORS(bool,CORBA::BooleanSeq);
ANY_VECTOR_CONVERT_OPERATORS(char,CORBA::CharSeq);
ANY_VECTOR_CONVERT_OPERATORS(std::string,CORBA::StringSeq);

#undef ANY_VECTOR_CONVERT_OPERATORS
// End core framework section

template <typename T>
class GR_PropertyWrapper : public PropertyWrapper<T>
{
public:
    typedef T value_type;
    typedef PropertyWrapper<T> super;

    virtual void getValue (CORBA::Any& outValue)
    {
        const value_type& tmp_value = this->getValue();
        if (super::enableNil_ && super::isNil_) {
            outValue = CORBA::Any();
        } else {
            toAny(tmp_value, outValue);
        }
    }

    virtual const value_type& getValue (void)
    {
        if (p_getValueCallback) {
            value_type retval = (*p_getValueCallback)();
            if (retval != super::value_) {
                super::setValue(retval);
            }
        }
        return super::value_;
    }

    virtual void setValue (const CORBA::Any& newValue)
    {
        value_type value;
        if (fromAny(newValue, value)) {
            super::isNil_ = false;
            setValue(value);
        } else {
            super::isNil_ = super::enableNil_;
        }
    }

    virtual void setValue (const value_type& newValue)
    {
        super::setValue(newValue);
        if (p_setValueCallback) {
            (*p_setValueCallback)(newValue);
        }
    }
    
    class GetValueCallback
    {
    public:
        virtual value_type operator() () = 0;
        virtual ~GetValueCallback (void) { };

    protected:
        GetValueCallback () { };
    };

    template <class Te>
    class MemberGetValueCallback : public GetValueCallback
    {
    public:
        typedef value_type (Te::*MemberFn)();

        virtual value_type operator() ()
        {
             return (target_.*func_)();
        }

    private:
        // Only allow PropertySet_impl to instantiate this class.
        MemberGetValueCallback (Te& target, MemberFn func) :
            target_(target),
            func_(func)
        {
        }

        friend class GR_PropertyWrapper;

        Te& target_;
        MemberFn func_;
    };

    template <class Te>
    class IndirectGetter : public GetValueCallback
    {
    public:
        typedef value_type (Te::*GetterFn)() const;

        virtual value_type operator() ()
        {
            if (target_) {
                return ((*target_).*func_)();
            }
            return value_type();
        }

    private:
        IndirectGetter(boost::shared_ptr<Te>& block, GetterFn func) :
            target_(block),
            func_(func)
        {
        }

        friend class GR_PropertyWrapper;

        boost::shared_ptr<Te>& target_;
        GetterFn func_;
    };

    class SetValueCallback
    {
    public:
        virtual void operator() (const value_type& value) = 0;
        virtual ~SetValueCallback (void) { };

    protected:
        SetValueCallback () { };
    };

    template <class Te, typename Targ>
    class IndirectSetter : public SetValueCallback
    {
    public:
        typedef void (Te::*SetterFn)(Targ);

        virtual void operator() (const value_type& value)
        {
            if (target_) {
                ((*target_).*func_)(value);
            }
        }

    private:
        IndirectSetter(boost::shared_ptr<Te>& block, SetterFn func) :
            target_(block),
            func_(func)
        {
        }

        friend class GR_PropertyWrapper;

        boost::shared_ptr<Te>& target_;
        SetterFn func_;
    };

    template <class Te>
    void setGetValueOverride (Te& target, value_type (Te::*func)())
    {
        setGetValueCallback(new MemberGetValueCallback<Te>(target, func));
    }

    template <class Te>
    void setGetValueOverride (Te* target, value_type (Te::*func)())
    {
        setGetValueCallback(new MemberGetValueCallback<Te>(*target, func));
    }

    template <class Te>
    void setGetValueOverride (boost::shared_ptr<Te>& block, typename IndirectGetter<Te>::GetterFn func)
    {
        setGetValueCallback(new IndirectGetter<Te>(block, func));
    }

    template <class Te>
    void setSetValueOverride (boost::shared_ptr<Te>& block, typename IndirectSetter<Te,value_type>::SetterFn func)
    {
        setSetValueCallback(new IndirectSetter<Te,value_type>(block, func));
    }

    template <class Te>
    void setSetValueOverride (boost::shared_ptr<Te>& block, typename IndirectSetter<Te,const value_type&>::SetterFn func)
    {
        setSetValueCallback(new IndirectSetter<Te,const value_type&>(block, func));
    }

protected:
    GR_PropertyWrapper (value_type& value) :
        super(value)
    {
    }
    friend class GR_PropertyWrapperFactory;

private:
    void setGetValueCallback (GetValueCallback* callback)
    {
        p_getValueCallback.reset(callback);
    }

    void setSetValueCallback (SetValueCallback* callback)
    {
        p_setValueCallback.reset(callback);
    }

    boost::scoped_ptr<GetValueCallback> p_getValueCallback;
    boost::scoped_ptr<SetValueCallback> p_setValueCallback;
};

template <typename T>
class GR_NumericPropertyWrapper : public GR_PropertyWrapper<T>
{
public:
    typedef T value_type;
    typedef GR_PropertyWrapper<T> super;

    virtual short compare (const CORBA::Any& a)
    {
        if (super::isNil_) {
            if (a.type()->kind() == (CORBA::tk_null)) {
                return 0;
            }
            return 1;
        }

        value_type tmp;
        if (fromAny(a, tmp)) {
            if (tmp < super::value_) {
                return -1;
            }
            if (tmp == super::value_) {
                return 0;
            }
            return 1;
        } else {
            return 1;
        }
    }

    virtual void increment (const CORBA::Any& a)
    {
        if (!super::isNil_) {
            value_type tmp;
            if (fromAny(a, tmp)) {
                super::value_ += tmp;
            }
        }
    }

    virtual void decrement (const CORBA::Any& a)
    {
        if (!super::isNil_) {
            value_type tmp;
            if (fromAny(a, tmp)) {
                super::value_ -= tmp;
            }
        }
    }

protected:
    GR_NumericPropertyWrapper (value_type& value) :
        super(value)
    {
    };

    friend class GR_PropertyWrapperFactory;
};

template <typename T>
class GR_StructProperty : public GR_PropertyWrapper<T>
{
public:
    typedef T value_type;
    typedef GR_PropertyWrapper<T> super;

    virtual short compare (const CORBA::Any& a)
    {
        if (super::isNil_) {
            if (a.type()->kind() == (CORBA::tk_null)) {
                return 0;
            }
            return 1;
        }

        value_type tmp;
        if (fromAny(a, tmp)) {
            if (tmp != super::value_) {
                return 1;
            }
            return 0;
        } else {
            return 1;
        }
    }
protected:
    GR_StructProperty (value_type& value) :
        super(value)
    {
        super::type = CORBA::tk_struct;
    }

    friend class GR_PropertyWrapperFactory;
};

typedef GR_PropertyWrapper<std::vector<std::string> > GR_StringSeqProperty;
typedef GR_PropertyWrapper<std::vector<char> > GR_CharSeqProperty;
typedef GR_PropertyWrapper<std::vector<bool> > GR_BooleanSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::Octet> > GR_OctetSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::Short> > GR_ShortSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::UShort> > GR_UShortSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::Long> > GR_LongSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::ULong> > GR_ULongSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::LongLong> > GR_LongLongSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::ULongLong> > GR_ULongLongSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::Float> > GR_FloatSeqProperty;
typedef GR_PropertyWrapper<std::vector<CORBA::Double> > GR_DoubleSeqProperty;

class GR_PropertyWrapperFactory : public PropertyWrapperFactory
{
public:

    /*
    * The template specializtions would not compile outside of the scope of the class
    * definition
    */

    template <typename T>
    static PropertyInterface* Create (T& value)
    {
        return new GR_StructProperty<T>(value);
    }

    template <typename T>
    static PropertyInterface* Create (std::vector<T>& value)
    {
      return new StructSequenceProperty<T>(value);
    }


private:
    // This class should never be instantiated.
    GR_PropertyWrapperFactory();

};

typedef GR_PropertyWrapper<std::string> GR_StringProperty;
typedef GR_PropertyWrapper<bool> GR_BooleanProperty;
typedef GR_PropertyWrapper<char> GR_CharProperty;
typedef GR_NumericPropertyWrapper<CORBA::Octet> GR_OctetProperty;
typedef GR_NumericPropertyWrapper<CORBA::Short> GR_ShortProperty;
typedef GR_NumericPropertyWrapper<CORBA::UShort> GR_UShortProperty;
typedef GR_NumericPropertyWrapper<CORBA::Long> GR_LongProperty;
typedef GR_NumericPropertyWrapper<CORBA::ULong> GR_ULongProperty;
typedef GR_NumericPropertyWrapper<CORBA::ULongLong> GR_ULongLongProperty;
typedef GR_NumericPropertyWrapper<CORBA::LongLong> GR_LongLongProperty;
typedef GR_NumericPropertyWrapper<CORBA::Float> GR_FloatProperty;
typedef GR_NumericPropertyWrapper<CORBA::Double> GR_DoubleProperty;

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<std::string> (std::string& value)
{
  return new GR_StringProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<bool> (bool& value)
{
    return new GR_BooleanProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<char> (char& value)
{
    return new GR_CharProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Octet> (CORBA::Octet& value)
{
  return new GR_OctetProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Short> (CORBA::Short& value)
{
  return new GR_ShortProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::UShort> (CORBA::UShort& value)
{
  return new GR_UShortProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Long> (CORBA::Long& value)
{
  return new GR_LongProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::ULong> (CORBA::ULong& value)
{
  return new GR_ULongProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Float> (CORBA::Float& value)
{
  return new GR_FloatProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Double> (CORBA::Double& value)
{
  return new GR_DoubleProperty(value);
}


template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<std::string> (std::vector<std::string>& value)
{
    return new GR_StringSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<bool> (std::vector<bool>& value)
{
  return new GR_BooleanSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<char> (std::vector<char>& value)
{
  return new GR_CharSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Octet> (std::vector<CORBA::Octet>& value)
{
  return new GR_OctetSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Short> (std::vector<CORBA::Short>& value)
{
  return new GR_ShortSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::UShort> (std::vector<CORBA::UShort>& value)
{
  return new GR_UShortSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Long> (std::vector<CORBA::Long>& value)
{
  return new GR_LongSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::ULong> (std::vector<CORBA::ULong>& value)
{
  return new GR_ULongSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::LongLong> (std::vector<CORBA::LongLong>& value)
{
  return new GR_LongLongSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::ULongLong> (std::vector<CORBA::ULongLong>& value)
{
  return new GR_ULongLongSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Float> (std::vector<CORBA::Float>& value)
{
  return new GR_FloatSeqProperty(value);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Double> (std::vector<CORBA::Double>& value)
{
  return new GR_DoubleSeqProperty(value);
}
#endif

