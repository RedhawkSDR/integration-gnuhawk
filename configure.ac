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
 
AC_INIT([gnuhawk], [0.1.0])
AC_PREREQ(2.59)

AM_INIT_AUTOMAKE(nostdinc)

AM_PROG_AS

AC_PROG_CC
AC_PROG_CXX

AC_PROG_INSTALL
AC_PROG_LIBTOOL
AM_PATH_PYTHON([2.2])

AX_BOOST_BASE([1.41])
AX_BOOST_THREAD

OSSIE_CHECK_OSSIE
OSSIE_SDRROOT_AS_PREFIX([dom/deps/gnuhawk])

AM_CONDITIONAL([ARCH_64], test $gr_libdir_suffix = "64")

CHECK_VECTOR_IMPL
PKG_CHECK_MODULES([PROJECTDEPS], [ossie >= 1.8 omniORB4 >= 4.0.0])

# Require FFTW3
PKG_CHECK_MODULES([FFTW], [fftw3f >= 3.0])

# Requires gsl
PKG_CHECK_MODULES([gsl], [gsl])

# Undefine the PACKAGE_ variables to avoid warnings when omniORB is installed outside of
# /usr.  OSSIE doesn't use these variables anyways...and even if it did
# they would get undefined in any file that included CORBA.h (because ossieconfig.h
# undefines them)
CPPFLAGS="${CPPFLAGS} -UPACKAGE_NAME -UPACKAGE_BUGREPORT -UPACKAGE_TARNAME -UPACKAGE_STRING -UPACKAGE_VERSION"

# Add the OSSIE include flags
CXXFLAGS="$CXXFLAGS -Wall -Wno-strict-aliasing $OSSIE_CFLAGS"

# Include the Boost CPP flags to make sure we get the right headers
GNUHAWK_CPPFLAGS="$GNUHAWK_CPPFLAGS $BOOST_CPPFLAGS -msse2"
AC_MSG_RESULT($GNUHAWK_CPPFLAGS)
AC_SUBST(GNUHAWK_CPPFLAGS)

# Build the library with the platform preprocessor flags.
CPPFLAGS="${CPPFLAGS} ${GNUHAWK_CPPFLAGS}"

AC_CONFIG_FILES(Makefile \
        include/Makefile \
        include/gnuhawk/Makefile \
        src/Makefile \
        gnuhawk-local.pc \
        gnuhawk.pc)

AC_ARG_WITH(with_components, [  --with-components       enable component configure])

if test "$with_components" == "yes"
then
    AC_CONFIG_SUBDIRS(components qa )
    components=true
else
    components=false
fi

AM_CONDITIONAL(COMPONENTS, test x$components = xtrue)

# volk setup
(cd gnuradio/volk;cmake -DCMAKE_INSTALL_PREFIX:PATH=${prefix} -DLIB_SUFFIX:STRING="" .)

libdir=${prefix}/lib

AC_OUTPUT
