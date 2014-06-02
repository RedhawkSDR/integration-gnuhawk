#
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
#
 
# Define default SDRROOT
%{!?_sdrroot:    %define _sdrroot    /var/redhawk/sdr}

# Locate install under the SDRROOT
%define _prefix    %{_sdrroot}/dom/deps/gnuhawk

Name:		gnuhawk
Version:	1.9.0
Release:	3%{?dist}
Summary:	GNUHAWK is a library for using GNU Radio blocks in REDHAWK
Prefix:		%{_sdrroot}/dom/deps/gnuhawk

Group:		Applications/Engineering
License:	GPLv3+
Source:		%{name}-%{version}.tar.gz

BuildRequires:	redhawk-devel >= 1.9
BuildRequires:	cmake fftw-devel python-cheetah gsl-devel
Requires:	redhawk >= 1.9
Requires:	fftw gsl

BuildRoot:	%{_tmppath}/%{name}-%{version}-%{release}-buildroot

%description
GNUHAWK is a library for using GNU Radio blocks in REDHAWK.


%prep
%setup -q -n gnuhawk-%{version}


%build
./reconf
%configure --with-sdr=%{_sdrroot} 
make %{?_smp_mflags}


%install
rm -rf --preserve-root %{buildroot}
make install-strip DESTDIR=%{buildroot}


%clean
rm -rf --preserve-root %{buildroot}


%files
%defattr(-,redhawk,redhawk,-)
%dir %{_prefix}
%{_bindir}
%{_prefix}/lib/libvolk.so*
%{_prefix}/lib/libgnuhawk.a
%{_prefix}/lib/libgnuhawk.la
%{_prefix}/lib/pkgconfig
%{_prefix}/lib/libgnuhawk.so*
%{_includedir}
%{_prefix}/gnuhawk.spd.xml
%{_prefix}/share/gnuhawk


%changelog
* Mon Apr  1 2013 - 1.8.4.-1
- Initial release
