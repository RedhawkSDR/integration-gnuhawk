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

Name:		gnuhawk-components
Version:	1.10.0
Release:	0.1%{?dist}
Summary:	A set of GNU Radio blocks built for use in REDHAWK
Prefix:		%{_sdrroot}

Group:		Applications/Engineering
License:	GPLv3+
Source:		%{name}-%{version}.tar.gz

BuildRequires:	redhawk-devel >= 1.10
BuildRequires:	cmake
BuildRequires:	gnuhawk = %{version}-%{release}
BuildRequires:	fftw-devel bulkioInterfaces gsl-devel
Requires:	gnuhawk = %{version}-%{release}

%if "%{?rhel}" != "6"
BuildRoot:	%{_tmppath}/%{name}-%{version}-%{release}-buildroot
%endif

%description
A set of GNU Radio blocks built for use in REDHAWK


%prep
%setup -q


%build
export SDRROOT=%{_sdrroot}
pushd components
./reconf
./configure --with-sdr=%{_sdrroot}  --enable-deps=sdr
make %{?_smp_mflags}
popd


%install
rm -rf --preserve-root %{buildroot}
pushd components
make install-strip DESTDIR=%{buildroot}
popd


%clean
rm -rf --preserve-root %{buildroot}


%files
%defattr(-,redhawk,redhawk,-)
%{_sdrroot}/dom/components/gnuhawk


%changelog
* Mon Apr  1 2013 - 1.8.4.-1
- Initial release
