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

#ifndef STRUCTPROPS_H
#define STRUCTPROPS_H

/*******************************************************************************************

    AUTO-GENERATED CODE. DO NOT MODIFY

*******************************************************************************************/

#include <ossie/CorbaUtils.h>
#include <gnuhawk/gr_properties.h>

struct true_tag_struct {
    true_tag_struct ()
    {
        true_tag_key = "burst";
        true_tag_value = true;
    };

    static std::string getId() {
        return std::string("true_tag");
    };

    std::string true_tag_key;
    bool true_tag_value;
};

inline bool operator>>= (const CORBA::Any& a, true_tag_struct& s) {
    CF::Properties* temp;
    if (!(a >>= temp)) return false;
    CF::Properties& props = *temp;
    for (unsigned int idx = 0; idx < props.length(); idx++) {
        if (!strcmp("true_tag_key", props[idx].id)) {
            if (!(props[idx].value >>= s.true_tag_key)) return false;
        }
        if (!strcmp("true_tag_value", props[idx].id)) {
            if (!(props[idx].value >>= s.true_tag_value)) return false;
        }
    }
    return true;
};

inline void operator<<= (CORBA::Any& a, const true_tag_struct& s) {
    CF::Properties props;
    props.length(2);
    props[0].id = CORBA::string_dup("true_tag_key");
    props[0].value <<= s.true_tag_key;
    props[1].id = CORBA::string_dup("true_tag_value");
    props[1].value <<= s.true_tag_value;
    a <<= props;
};

inline bool operator== (const true_tag_struct& s1, const true_tag_struct& s2) {
    if (s1.true_tag_key!=s2.true_tag_key)
        return false;
    if (s1.true_tag_value!=s2.true_tag_value)
        return false;
    return true;
};

inline bool operator!= (const true_tag_struct& s1, const true_tag_struct& s2) {
    return !(s1==s2);
};

template<> inline short StructProperty<true_tag_struct>::compare (const CORBA::Any& a) {
    if (super::isNil_) {
        if (a.type()->kind() == (CORBA::tk_null)) {
            return 0;
        }
        return 1;
    }

    true_tag_struct tmp;
    if (fromAny(a, tmp)) {
        if (tmp != this->value_) {
            return 1;
        }

        return 0;
    } else {
        return 1;
    }
}

typedef GR_StructProperty<true_tag_struct> true_tag_struct_GR_StructProperty;

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<true_tag_struct> (true_tag_struct& value)
{
    PropertyWrapper<true_tag_struct>* impl = PropertyWrapperFactory::Create(value);
    return new true_tag_struct_GR_StructProperty(impl);
}

struct false_tag_struct {
    false_tag_struct ()
    {
        false_tag_key = "burst";
        false_tag_value = false;
    };

    static std::string getId() {
        return std::string("false_tag");
    };

    std::string false_tag_key;
    bool false_tag_value;
};

inline bool operator>>= (const CORBA::Any& a, false_tag_struct& s) {
    CF::Properties* temp;
    if (!(a >>= temp)) return false;
    CF::Properties& props = *temp;
    for (unsigned int idx = 0; idx < props.length(); idx++) {
        if (!strcmp("false_tag_key", props[idx].id)) {
            if (!(props[idx].value >>= s.false_tag_key)) return false;
        }
        if (!strcmp("false_tag_value", props[idx].id)) {
            if (!(props[idx].value >>= s.false_tag_value)) return false;
        }
    }
    return true;
};

inline void operator<<= (CORBA::Any& a, const false_tag_struct& s) {
    CF::Properties props;
    props.length(2);
    props[0].id = CORBA::string_dup("false_tag_key");
    props[0].value <<= s.false_tag_key;
    props[1].id = CORBA::string_dup("false_tag_value");
    props[1].value <<= s.false_tag_value;
    a <<= props;
};

inline bool operator== (const false_tag_struct& s1, const false_tag_struct& s2) {
    if (s1.false_tag_key!=s2.false_tag_key)
        return false;
    if (s1.false_tag_value!=s2.false_tag_value)
        return false;
    return true;
};

inline bool operator!= (const false_tag_struct& s1, const false_tag_struct& s2) {
    return !(s1==s2);
};

template<> inline short StructProperty<false_tag_struct>::compare (const CORBA::Any& a) {
    if (super::isNil_) {
        if (a.type()->kind() == (CORBA::tk_null)) {
            return 0;
        }
        return 1;
    }

    false_tag_struct tmp;
    if (fromAny(a, tmp)) {
        if (tmp != this->value_) {
            return 1;
        }

        return 0;
    } else {
        return 1;
    }
}

typedef GR_StructProperty<false_tag_struct> false_tag_struct_GR_StructProperty;

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<false_tag_struct> (false_tag_struct& value)
{
    PropertyWrapper<false_tag_struct>* impl = PropertyWrapperFactory::Create(value);
    return new false_tag_struct_GR_StructProperty(impl);
}



#endif
