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
#{$ extends "base/component.spec" $}

#{$ block variableExtensions $}
# Define the namespace of the GNUHawk component
%define _namespace FIXME

#{$ endblock $}

#{$ block requireExtensions $}
Requires: gnuhawk >= 1.9
BuildRequires: gnuhawk >= 1.9
BuildRequires: fftw-devel
#{$ endblock $}

#{$ block build $}
#{$ for impl in component.implementations $}
# Implementation {{impl.id}}
pushd {{impl.outputdir}}
export SDRROOT=%{_sdrroot}
./reconf
%define _bindir %{_prefix}/{{component.sdrpath}}/%{_namespace}/%{_version}/{{name}}/{{impl.outputdir}}
%configure
make %{?_smp_mflags}
popd
#{$ endfor $}
#{$ endblock $}

#{$ block install $}
rm -rf $RPM_BUILD_ROOT
#{$ for impl in component.implementations $}
# Implementation {{impl.id}}
pushd {{impl.outputdir}}
export SDRROOT=%{_sdrroot}
%define _bindir %{_prefix}/{{component.sdrpath}}/%{_namespace}/%{_version}/{{name}}/{{impl.outputdir}}
make install DESTDIR=$RPM_BUILD_ROOT
popd
#{$ endfor $}
#{$ endblock $}

#{$ block files $}
%defattr(-,redhawk,redhawk)
%dir %{_prefix}/{{component.sdrpath}}/%{_namespace}/%{name}/%{version}
#{$ for xmlfile in component.profile.values() $}
%{_prefix}/{{component.sdrpath}}/%{_namespace}/%{name}/%{version}/{{xmlfile}}
#{$ endfor $}
#{$ for impl in component.implementations $}
%{_prefix}/{{component.sdrpath}}/%{_namespace}/%{name}/%{version}/{{impl.outputdir}}
#{$ endfor $}
%{_prefix}/dom/components/%{_namespace}/%{name}/current
#{$ endblock $}
