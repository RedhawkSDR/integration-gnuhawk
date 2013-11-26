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
