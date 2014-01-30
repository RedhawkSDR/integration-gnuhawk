/*#
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
 #*/
/*{% extends "pull/struct_props.h" %}*/
/*{% block license %}*/
${component.cppLicense}

/*{% endblock %}*/
/*# ********** inherit header from redhawk base template ********** #*/

/*{% block includes %}*/
#include <ossie/CorbaUtils.h>
#include <gnuhawk/gr_properties.h>
/*{% endblock %}*/

/*{% block struct %}*/
/*{% from "properties/properties.cpp" import structdef with context %}*/
/*{% for struct in component.structdefs %}*/
${structdef(struct)}

typedef GR_StructProperty<${struct['cpptype']}> ${struct['cpptype']}_GR_StructProperty;

template <>
inline PropertyInterface* GR_PropertyWrapperFactory::Create<${struct['cpptype']}> (${struct['cpptype']}& value)
{
    return new ${struct['cpptype']}_GR_StructProperty(value);
}

/*{% endfor %}*/
/*{% endblock %}*/

/*# ********** inherit structSequence from redhawk base template ********** #*/
