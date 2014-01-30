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
#{% extends "base/configure.ac" %}
#{% block license %}
{{component.shellLicense}}

#{% endblock %}

#{######### Inherit acInit from redhawk base template #########}
#{######### Inherit acChecks from redhawk base template #########}

#{% block pkgConfigPath %}
{{super()}}":${SDRROOT}/dom/deps/gnuhawk/lib64/pkgconfig:${SDRROOT}/dom/deps/gnuhawk/lib/pkgconfig"
#{% endblock %}
#{% block projectDeps %}
{{super()}} gnuhawk >= 0.1.0#{% endblock %}

#{######### Inherit interfaceDeps from redhawk base template #########}
#{######### Inherit coreDeps from redhawk base template #########}
#{######### Inherit acConfig from redhawk base template #########}


