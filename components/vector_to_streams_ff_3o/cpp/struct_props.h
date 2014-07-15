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

struct stream_id_struct_struct {
    stream_id_struct_struct ()
    {
    };

    static std::string getId() {
        return std::string("stream_id_struct");
    };

    std::string port_name;
    std::string stream_id;
};

inline bool operator>>= (const CORBA::Any& a, stream_id_struct_struct& s) {
    CF::Properties* temp;
    if (!(a >>= temp)) return false;
    CF::Properties& props = *temp;
    for (unsigned int idx = 0; idx < props.length(); idx++) {
        if (!strcmp("port_name", props[idx].id)) {
            if (!(props[idx].value >>= s.port_name)) return false;
        }
        else if (!strcmp("stream_id", props[idx].id)) {
            if (!(props[idx].value >>= s.stream_id)) return false;
        }
    }
    return true;
};

inline void operator<<= (CORBA::Any& a, const stream_id_struct_struct& s) {
    CF::Properties props;
    props.length(2);
    props[0].id = CORBA::string_dup("port_name");
    props[0].value <<= s.port_name;
    props[1].id = CORBA::string_dup("stream_id");
    props[1].value <<= s.stream_id;
    a <<= props;
};

inline bool operator== (const stream_id_struct_struct& s1, const stream_id_struct_struct& s2) {
    if (s1.port_name!=s2.port_name)
        return false;
    if (s1.stream_id!=s2.stream_id)
        return false;
    return true;
};

inline bool operator!= (const stream_id_struct_struct& s1, const stream_id_struct_struct& s2) {
    return !(s1==s2);
};

typedef GR_StructProperty<stream_id_struct_struct> stream_id_struct_struct_GR_StructProperty;

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<stream_id_struct_struct> (stream_id_struct_struct& value)
{
    PropertyWrapper<stream_id_struct_struct>* impl = PropertyWrapperFactory::Create(value);
    return new stream_id_struct_struct_GR_StructProperty(impl);
}


#endif
