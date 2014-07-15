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
#include <iostream>

template <typename T>
//class GR_PropertyWrapper : public PropertyWrapper< T >
class GR_PropertyWrapper : public PropertyInterface
{
public:
    typedef T value_type;
    typedef PropertyInterface    super;
    PropertyWrapper<T>*  impl_;
    CORBA::Any* value_;
    
    virtual void getValue (CORBA::Any& outValue)
    {

        if (p_getValueCallback) {
            value_type retval = (*p_getValueCallback)();
            if (retval != impl_->getValue()) {
                impl_->setValue(retval);

            }
        }
        return impl_->getValue(outValue);
    }

    virtual const value_type& getValue (void)
    {
        if (p_getValueCallback) {
            value_type retval = (*p_getValueCallback)();
            if (retval != impl_->getValue()) {
                impl_->setValue(retval);

            }
        }
        return impl_->getValue();
    }

    virtual void setValue (const CORBA::Any& newValue)
    {
        impl_->setValue(newValue);
        if (p_setValueCallback) {
            const value_type &retval = impl_->getValue();
            (*p_setValueCallback)(retval);
        }
    }



    virtual short int compare(const CORBA::Any& a){
        return impl_->compare(a);
    }
    virtual void increment(const CORBA::Any& a){ 
        if (p_getValueCallback) {
            value_type retval = (*p_getValueCallback)();
            if (retval != impl_->getValue()) {
                impl_->setValue(retval);
            }
        }
        impl_->increment(a);
    }
    virtual void decrement(const CORBA::Any& a){
        if (p_getValueCallback) {
            value_type retval = (*p_getValueCallback)();
            if (retval != impl_->getValue()) {
                impl_->setValue(retval);
            }
        }
        impl_->decrement(a);
    }
    virtual bool allocate(const CORBA::Any& a){
      return impl_->allocate(a);
    }
    virtual void deallocate(const CORBA::Any& a){
      impl_->deallocate(a);
    }
    virtual const std::string getNativeType()const{
      return impl_->getNativeType();
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
    GR_PropertyWrapper (PropertyWrapper<T> *impl) : 
        PropertyInterface(ossie::corba::TypeCode<value_type>()),
        impl_(impl)
    {
    }
    GR_PropertyWrapper (SequenceProperty<T> *impl) : 
        PropertyInterface(ossie::corba::TypeCode< std::vector< value_type > >()),
        impl_(impl)
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
        return super::impl_->compare(a);
    }

    virtual void increment (const CORBA::Any& a){
        super::impl_->increment(a);
    }
    virtual void decrement (const CORBA::Any& a){
        super::impl_->decrement(a);
    }
    virtual bool allocate (const CORBA::Any& a){
      return super::impl_->allocate(a);
    }
    virtual void deallocate (const CORBA::Any& a){
      return super::impl_->deallocate(a);
    }
    virtual const std::string getNativeType()const{
      return super::impl_->getNativeType();
    }


protected:
    GR_NumericPropertyWrapper (PropertyWrapper<T> *impl) :
        super(impl)
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
        return super::impl_->compare(a);
    }

protected:
    GR_StructProperty (PropertyWrapper<T>* impl) :
        super(impl)
 
    {
        super::type = CORBA::_tc_TypeCode;
    }

    friend class GR_PropertyWrapperFactory;
};

template <typename T>
class GR_StructSequenceProperty : public GR_PropertyWrapper<std::vector<T> >
{
public:
    typedef T elem_type;
    typedef std::vector<elem_type> value_type;
    typedef  GR_PropertyWrapper<value_type> super;

    // This definition exists strictly because pre-1.10 code generators define
    // an explicit specialization of this method; it may be removed when source
    // compatibility with 1.9 and older is no longer required.
    virtual short compare (const CORBA::Any& a)
    {
        return super::compare(a);
    }

protected:

    GR_StructSequenceProperty (PropertyWrapper<std::vector<T> >* impl) :
        super(impl)
 
    {
       super::type = CORBA::_tc_TypeCode;
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
typedef GR_PropertyWrapper<std::vector<std::complex<float> >  > GR_ComplexFloatSeqProperty;

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
        PropertyWrapper<T>* impl = PropertyWrapperFactory::Create(value);
        return new GR_PropertyWrapper<T>(impl);
    }

   template <typename T>
    static PropertyInterface* Create (std::vector< T >& value)
    {
     PropertyWrapper<std::vector<T> >* impl = PropertyWrapperFactory::Create< T >(value);
     return new  GR_StructSequenceProperty<T>(impl);
      
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
    PropertyWrapper<std::string>* impl = PropertyWrapperFactory::Create(value);
    return new GR_StringProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<bool> (bool& value)
{
    PropertyWrapper<bool>* impl = PropertyWrapperFactory::Create(value);
    return new GR_BooleanProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<char> (char& value)
{
    PropertyWrapper<char>* impl = PropertyWrapperFactory::Create(value);
    return new GR_CharProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Octet> (CORBA::Octet& value)
{
    PropertyWrapper<CORBA::Octet>* impl = PropertyWrapperFactory::Create(value);
    return new GR_OctetProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Short> (CORBA::Short& value)
{
  PropertyWrapper<CORBA::Short>* impl = PropertyWrapperFactory::Create(value);
  return new GR_ShortProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::UShort> (CORBA::UShort& value)
{
  PropertyWrapper<CORBA::UShort>* impl = PropertyWrapperFactory::Create(value);
  return new GR_UShortProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Long> (CORBA::Long& value)
{
  PropertyWrapper<CORBA::Long>* impl = PropertyWrapperFactory::Create(value);
  return new GR_LongProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::ULong> (CORBA::ULong& value)
{
  PropertyWrapper<CORBA::ULong>* impl = PropertyWrapperFactory::Create(value);
  return new GR_ULongProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Float> (CORBA::Float& value)
{
  PropertyWrapper<CORBA::Float>* impl = PropertyWrapperFactory::Create(value);
  return new GR_FloatProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Double> (CORBA::Double& value)
{
  PropertyWrapper<CORBA::Double>* impl = PropertyWrapperFactory::Create(value);
  return new GR_DoubleProperty(impl);
}


template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<std::string> (std::vector<std::string>& value)
{
    SequenceProperty<std::string>* impl = PropertyWrapperFactory::Create(value);
    return new GR_StringSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<bool> (std::vector<bool>& value)
{
  SequenceProperty<bool>* impl = PropertyWrapperFactory::Create(value);
  return new GR_BooleanSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<char> (std::vector<char>& value)
{
  SequenceProperty<char>* impl = PropertyWrapperFactory::Create(value);
  return new GR_CharSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Octet> (std::vector<CORBA::Octet>& value)
{
  SequenceProperty<CORBA::Octet>* impl = PropertyWrapperFactory::Create(value);
  return new GR_OctetSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Short> (std::vector<CORBA::Short>& value)
{
  SequenceProperty<CORBA::Short>* impl = PropertyWrapperFactory::Create(value);
  return new GR_ShortSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::UShort> (std::vector<CORBA::UShort>& value)
{
  SequenceProperty<CORBA::UShort>* impl = PropertyWrapperFactory::Create(value);
  return new GR_UShortSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Long> (std::vector<CORBA::Long>& value)
{
  SequenceProperty<CORBA::Long>* impl = PropertyWrapperFactory::Create(value);
  return new GR_LongSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::ULong> (std::vector<CORBA::ULong>& value)
{
  SequenceProperty<CORBA::ULong>* impl = PropertyWrapperFactory::Create(value);
  return new GR_ULongSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::LongLong> (std::vector<CORBA::LongLong>& value)
{
  SequenceProperty<CORBA::LongLong>* impl = PropertyWrapperFactory::Create(value);
  return new GR_LongLongSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::ULongLong> (std::vector<CORBA::ULongLong>& value)
{
  SequenceProperty<CORBA::ULongLong>* impl = PropertyWrapperFactory::Create(value);
  return new GR_ULongLongSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Float> (std::vector<CORBA::Float>& value)
{
  SequenceProperty<CORBA::Float>* impl = PropertyWrapperFactory::Create(value);
  return new GR_FloatSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<CORBA::Double> (std::vector<CORBA::Double>& value)
{
  SequenceProperty<CORBA::Double>* impl = PropertyWrapperFactory::Create(value);
  return new GR_DoubleSeqProperty(impl);
}

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<std::complex<float> > (
    std::vector<std::complex<float> >& inputVector)
{
    SequenceProperty<std::complex<float> >* impl = PropertyWrapperFactory::Create(inputVector);
    return new GR_ComplexFloatSeqProperty(impl);
}

#endif



