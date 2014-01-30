#{#
# This file is protected by Copyright. Please refer to the COPYRIGHT file 
# distributed with this source distribution.
# 
# This file is part of GNUHAWK.
# 
# GNUHAWK is free software: you can redistribute it and/or modify is under the 
# terms of the GNU General Public License as published by the Free Software 
# Foundation, either version 3 of the License, or (at your option) any later 
# version.
# 
# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR 
# A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

# You should have received a copy of the GNU General Public License along with 
# this program.  If not, see http://www.gnu.org/licenses/.
#}
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
