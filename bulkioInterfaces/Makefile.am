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

###############################################################################
# CONFIGURE YOUR INTERFACES LIBRARY HERE
#
# vim: noet: softtabstop=0
#
# This Makefile.am will require GNUMake (because it uses special GNUMake
# functions).  As an alternative, all of the specical functions can be
# replaced with configure.ac macros and AC_SUBST calls.
#
###############################################################################
# As a Redhawk convention this should always be 'redhawk'. BulkIO does not
# follow the Redhawk conventione for legacy compatibility.
LIBRARY_NAME = gnuhawk_bulkio

## Place generated object files (.o) into the same directory as their source
## files, in order to avoid collisions when non-recursive make is used.
AUTOMAKE_OPTIONS = subdir-objects

## Additional flags to pass to aclocal when it is invoked automatically at
## make time. The ${ACLOCAL_FLAGS} variable is picked up from the environment
## to provide a way for the user to supply additional arguments.
ACLOCAL_AMFLAGS = ${ACLOCAL_FLAGS}

# You have to manually set the _SOURCES, automake/libtool does not support
# makefile variable substituion here...so unfortunately each IDL file
# needs to be listed here...for each input IDL you will need to add three
# sources.  For example:
#        some_file.idl becomes ->
#            src/cpp/some_fileSK.cpp src/cpp/some_fileDynSK.cpp src/cpp/some_file.h
lib_LTLIBRARIES = libgnuhawk_bulkio-@BULKIO_API_VERSION@.la
libgnuhawk_bulkio_@BULKIO_API_VERSION@_la_LDFLAGS = -version-info $(BULKIO_SO_VERSION)
#libgnuhawk_bulkio_@BULKIO_API_VERSION@_la_LIBADD = $(INTERFACEDEPS_LIBS)

# Again, we don't use the auto variable because order is important to us
libgnuhawk_bulkio_@BULKIO_API_VERSION@_la_SOURCES = \
    cpp/gnuhawk_bulkio_in_port.cpp \
	cpp/gnuhawk_bulkio_out_port.cpp

## Define the list of public header files and their install location.  The
## nobase_ prefix instructs Automake to not strip the directory part from each
## filename, in order to avoid the need to define separate file lists for each
## installation directory.  This only works if the directory hierarchy in the
## source tree matches the hierarchy at the install location, however.

##bulkio_includedir = $(includedir)/bulkio-$(BULKIO_API_VERSION)
##nobase_bulkio_include_HEADERS = bulkio.h
#library_includedir = $(includedir)/bulkio-$(BULKIO_API_VERSION)
library_includedir = $(includedir)/bulkio
library_include_HEADERS = cpp/gnuhawk_bulkio_in_port.h \
	cpp/gnuhawk_bulkio_out_port.h

## The generated configuration header is installed in its own subdirectory of
## $(libdir).  The reason for this is that the configuration information put
## into this header file describes the target platform the installed library
## has been built for.  Thus the file must not be installed into a location
## intended for architecture-independent files, as defined by the Filesystem
## Hierarchy Standard (FHS).
## The nodist_ prefix instructs Automake to not generate rules for including
## the listed files in the distribution on 'make dist'.  Files that are listed
## in _HEADERS variables are normally included in the distribution, but the
## configuration header file is generated at configure time and should not be
## shipped with the source tarball.
#bulkio_libincludedir = $(libdir)/bulkio-$(BULKIO_API_VERSION)/include


##libbulkio_@BULKIO_API_VERSION@_la_CXXFLAGS = -I./cpp -I../src/cpp -I../src/cpp/ossie $(BOOST_CPPFLAGS) $(RH_DEPS_CFLAGS)
libgnuhawk_bulkio_@BULKIO_API_VERSION@_la_CXXFLAGS = -I./cpp $(BULKIO_INF_INCLUDES) $(BOOST_CPPFLAGS) $(OMNIORB_CFLAGS) $(OSSIE_CFLAGS) $(INTERFACEDEPS_CFLAGS)

.PHONY: all-local reallyclean

all-local:

install-exec-hook: 

clean-local:
	rm -rf build

###############################################################################
# C++ (via automake and libtool)
pkgconfigdir = $(libdir)/pkgconfig
pkgconfig_DATA = gnuhawk_bulkio.pc
dist_pkgconfig_DATA = $(PACKAGE_NAME).pc

AM_CXXFLAGS = -Wall -I./cpp -g  $(BULKIO_INF_INCLUDES) $(BOOST_CPPFLAGS) $(OMNIORB_CFLAGS) $(OSSIE_CFLAGS)
AM_LIBS = $(OSSIE_LIBS)

## Install the generated pkg-config file (.pc) into the expected location for
## architecture-dependent package configuration information.  Occasionally,
## pkg-config files are also used for architecture-independent data packages,
## in which case the correct install location would be $(datadir)/pkgconfig.

###############################################################################
# General

zip-src:
	zip 	$(LOWER_CASE_IDL_MODULE)Interfaces.zip \
		$(IDL_FILES) \
		Makefile.am \
		configure.ac \
		reconf \
		reconf.launch \
		.project \
		.autotools \
		.cproject \
		.project \
		.settings/org.eclipse.cdt.managedbuilder.core.prefs \
		README


reallyclean: clean
	rm -f gnuhawk_bulkio.pc
	rm -f configure
	rm -f Makefile.in
	rm -f config.log
	rm -f depcomp
	rm -f config.guess
	rm -f config.sub
	rm -f ltmain.sh
	rm -rf .deps
	rm -f Makefile
	rm -f config.status
	rm -f libtool
	rm -rf acinclude.m4
	rm -f missing
	rm -rf aclocal.m4
	rm -f install-sh
	rm -rf autom4te.cache
	rm -f *.lo
	rm -f *.o
	rm -rf src




