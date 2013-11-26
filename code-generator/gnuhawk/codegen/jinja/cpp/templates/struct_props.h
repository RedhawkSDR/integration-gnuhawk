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
