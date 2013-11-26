AC_INIT(gnuhawk_bulkio, 1.0.0)
AM_INIT_AUTOMAKE(nostdinc)

export PKG_CONFIG_PATH="$PKG_CONFIG_PATH:/usr/local/lib64/pkgconfig:/usr/local/lib/pkgconfig"

LT_INIT
AC_PROG_CXX
AC_PROG_INSTALL
AC_PROG_LIBTOOL

AX_BOOST_BASE([1.41])
AX_BOOST_THREAD
AM_PATH_PYTHON([2.3])

AC_SUBST([BULKIO_SO_VERSION], [1:0:0])
AC_SUBST([BULKIO_API_VERSION], [1.0])
AC_PREFIX_DEFAULT(${OSSIEHOME})

OSSIE_CHECK_OSSIE
OSSIE_OSSIEHOME_AS_PREFIX
OSSIE_PYTHON_INSTALL_SCHEME
OSSIE_ENABLE_LOG4CXX
CHECK_VECTOR_IMPL

AC_CORBA_ORB
AC_LANG_PUSH([C++])

PKG_CHECK_MODULES([OMNIORB], [ omniORB4 >= 4.0.0])
PKG_CHECK_MODULES([OSSIE], [ossie >= 1.8])
PKG_CHECK_MODULES([INTERFACEDEPS], [bulkio >= 1.0 bulkioInterfaces >= 1.9])

#
# Enable bulkioInterface Dependencies
#

AC_ARG_ENABLE(build,
 [  --enable-build[=build type] specify build type:
         local [default]: build in project directory
	 rpm : build using installed package dependencies ],
 [ if test "x$enableval" = "xrpm" ; then
    PKG_CHECK_MODULES([BULKIO_INF], [bulkioInterfaces >= 1.9] )
  else
    echo "Using LOCAL build environment"
    AC_SUBST(BULKIO_INF_INCLUDES,"-I../src/cpp -I../src/cpp/ossie")
    AC_SUBST(BULKIO_INF_CFLAGS, )
    AC_SUBST(BULKIO_INF_LIBS, )
  fi
 ],
 [
   dnl  No option specified use project build environment
   echo "Using LOCAL build environment"
   AC_SUBST(BULKIO_INF_INCLUDES,"-I../src/cpp -I../src/cpp/ossie")
   AC_SUBST(BULKIO_INF_CFLAGS, )
   AC_SUBST(BULKIO_INF_LIBS, )
  ]
 
 )

AC_CONFIG_FILES([ Makefile
		   gnuhawk_bulkio.pc:gnuhawk_bulkio.pc.in ])

AC_CONFIG_COMMANDS([echo package: $pkgname version: $pkgversion], [pkgname=$PACKAGE_NAME, pkgversion=$PACKAGE_VERSION])

AC_OUTPUT
