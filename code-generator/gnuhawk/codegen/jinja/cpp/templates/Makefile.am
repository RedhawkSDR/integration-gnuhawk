#{% extends "base/Makefile.am" %}
#{% block license %}
{{component.shellLicense}}

# Manually set the GNURadio namespace here 
GR_NAMESPACE =

namespacedir = $(prefix)/{{component.sdrpath}}/$(GR_NAMESPACE)/{{component.name}}
versiondir = $(namespacedir)/$(PACKAGE_VERSION)

#{% endblock %}

#{% block binInfo %}
ossieName = {{target}}
bindir = $(versiondir)/{{outputdir}}
bin_PROGRAMS = {{executable}}
#{% endblock %}

#{% block xmlInfo %}
xmldir = $(versiondir)
dist_xml_DATA = {{component.profile.values()|relpath(outputdir)|join(' ')}}
#{% endblock %}

#{% block extensions %}
# Create a "current" link to the version just installed
install-exec-hook:
	ln -fns $(versiondir) $(DESTDIR)$(namespacedir)/current
#{% endblock %}

#{######### Inherit distClean from redhawk base template #########}
#{######### Inherit compileFlags from redhawk base template #########}
