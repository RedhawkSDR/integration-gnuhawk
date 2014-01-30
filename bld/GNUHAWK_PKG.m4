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


dnl
dnl check for --with-gnuhawk we are not caching results, too many issues 
dnl gnuhawk_cv_gnuhawk_pkg needs to have all the relative paths from components/xxx/cpp and qa/components/xxx/cpp to gnuhawk top dir
dnl so PKG_CHECK_MODULES can find gnuhawk-local.pc file. For continuous builds and rpm builds 
dnl run each components 
dnl   ./configure  --enable-deps=sdr     
dnl this will build against the sdr_root dependency
dnl
AC_DEFUN([GNUHAWK_PKG_PATH],[
  dnl echo "gnuhawk_pkg_path start"
  AC_ARG_WITH( gnuhawk,
      AC_HELP_STRING([--with-gnuhawk], [gnuhawk package path]), 
      [
        gnuhawk_cv_gnuhawk_pkg=$withval
      ],
      [
        if test "x${GHAWK_DIR}" != "x";
	then
	   dnl echo "hootie " ${GHAWK_DIR}
	   gnuhawk_cv_gnuhawk_pkg=${GHAWK_DIR}		
	else
	  gnuhawk_cv_gnuhawk_pkg="../../..:../../../.."
	fi
       ])
  dnl echo "gnuhawk_pkg_path end :" $gnuhawk_cv_gnuhawk_pkg
  AC_SUBST(GNUHAWK_DIR,$gnuhawk_cv_gnuhawk_pkg)
])

	
AC_DEFUN([GNUHAWK_PKG_CONFIG],[
  AC_REQUIRE([GNUHAWK_PKG_PATH])
  dnl  echo "gnuhawk pkg config" $gnuhawk_cv_gnuhawk_pkg
  AC_ARG_ENABLE(deps,
        [ --enable-deps[=build type] specify build type: 
	   local [default]: build against local library directory  
	   sdr : build using installed sdr package dependencies ], [ 

	   if test "x$enableval" = "xsdr" ; then
	       echo "ENABLED BUILD: SDR DEPS"
               PKG_CONFIG_PATH="${SDRROOT}/dom/deps/gnuhawk/lib64/pkgconfig:${SDRROOT}/dom/deps/gnuhawk/lib/pkgconfig:$PKG_CONFIG_PATH:/usr/local/lib/pkgconfig"
               PKG_CHECK_MODULES([GNUHAWK], [gnuhawk >= 0.1.0 ])
          else
               echo "ENABLED BUILD: LOCAL"
	       if test "x$1" == "x"; 
               then
	          pkg_path=$gnuhawk_cv_gnuhawk_pkg
	       else
	          pkg_path=$1
	       fi
              PKG_CONFIG_PATH="$pkg_path:$PKG_CONFIG_PATH:/usr/local/lib/pkgconfig"
	      dnl echo $PKG_CONFIG_PATH
              PKG_CHECK_MODULES([GNUHAWK], [gnuhawk-local >= 0.1.0 ])
          fi
      ],[
         echo "DEFAULT BUILD: LOCAL"
         if test "x$1" == "x"; 
         then
            pkg_path=$gnuhawk_cv_gnuhawk_pkg
         else
            pkg_path=$1
         fi
         PKG_CONFIG_PATH="$pkg_path:$PKG_CONFIG_PATH:/usr/local/lib/pkgconfig"
         dnl echo $PKG_CONFIG_PATH
         PKG_CHECK_MODULES([GNUHAWK], [gnuhawk-local >= 0.1.0 ])
      ])
])

