#
# This file is protected by Copyright. Please refer to the COPYRIGHT file
# distributed with this source distribution.
#
# This file is part of REDHAWK core.
#
# REDHAWK core is free software: you can redistribute it and/or modify it under
# the terms of the GNU Lesser General Public License as published by the Free
# Software Foundation, either version 3 of the License, or (at your option) any
# later version.
#
# REDHAWK core is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
# FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
# details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with this program.  If not, see http://www.gnu.org/licenses/.
#

# By default, the RPM will install to the standard REDHAWK OSSIE root location (/usr/local/redhawk/core)
# You can override this at install time using --prefix /usr/local/redhawk/core when invoking rpm (preferred)
%define _ossiehome /usr/local/redhawk/core
%define _prefix %{_ossiehome}
Prefix:         %{_prefix}

# Java libraries built by default; use '--without java' to disable
%bcond_without java

Name:           bulkio
Version:        1.0.0
Release:        0.1%{?dist}
Summary:        The bulkio library for REDHAWK

#Group:          Applications/Engineering
Group:          REDHAWK/Library
License:        LGPLv3+
URL:            http://redhawksdr.org/
#Source:         %{name}-%{version}.tar.bz2
Source:         bulkio.tar.bz2

BuildRoot:      %{_tmppath}/%{name}-%{version}-%{release}-buildroot

Requires:       redhawk >= 1.9
Requires:       bulkioInterfaces >= 1.9
#BuildRequires:   bulkioInterfaces
#BuildRequires:   redhawk-devel >= 1.9
BuildRequires:  autoconf automake libtool

%description
Libraries and interface definitions for bulkio interfaces.

# Point install paths to locations within our target SDR root
%define _sysconfdir    %{_prefix}/etc
%define _localstatedir %{_prefix}/var
%define _mandir        %{_prefix}/man
%define _infodir       %{_prefix}/info

# C++ requirements
Requires: boost >= 1.41
Requires: apache-log4cxx >= 0.10
BuildRequires: boost-devel >= 1.41
BuildRequires: libomniORB4.1-devel
#BuildRequires: apache-log4cxx-devel >= 0.10

# Java requirements
Requires: java
BuildRequires: jdk

# Python requirements
Requires: python 
BuildRequires: python-devel >= 2.3

#Requires: python omniORBpy
#BuildRequires: libomniORBpy3-devel
#BuildRequires: python-devel >= 2.3


%description
bulkio convience library to interface to BULKIO interface definitions used by REDHAWK compnents

%prep
%setup -q

%build
# Implementation cpp
./reconf
%configure --enable-build=rpm
make

# Implementation python
#python setup.py build
# Implementation java
#pushd java
#./reconf
#%configure
#make
#popd

%install
rm -rf $RPM_BUILD_ROOT
#
# c++ library #make install DESTDIR=$RPM_BUILD_ROOT
#
%makeinstall

#
# python implementation
#
#python setup.py install --prefix=$RPM_BUILD_ROOT/%{_prefix} --install-purelib=$RPM_BUILD_ROOT/%{_prefix}/lib/python/

#
# java implementation 
#    old make install DESTDIR=$RPM_BUILD_ROOT
#
#pushd java
#%makeinstall
#popd

%clean
rm -rf $RPM_BUILD_ROOT


%files
%defattr(-,redhawk,redhawk)
%{_includedir}/bulkio
%{_libdir}/libbulkio*
%{_libdir}/pkgconfig/bulkio.pc
%{_prefix}/lib/python/bulkio
%if %{with java}
%{_prefix}/lib/bulkio.jar
%endif
%if "%{?rhel}" == "6"
%{_prefix}/lib/python/bulkio-1.0.0-py2.6.egg-info
%endif


%post
/sbin/ldconfig

%postun
/sbin/ldconfig
