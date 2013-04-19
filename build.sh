#!/bin/sh
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
 
config_ac='configure.ac'
make_am='Makefile.am'
makefile='Makefile'

version=1.8.3

builddir=rpmbuild

if [ "$1" == 'clean' ]; then
  make clean
elif [ "$1" == 'rpm' ]; then
  echo "A copy of the gnuradio source must be in the current directory, linking will not work!"
  mv ~/.rpmmacros /tmp/rpmmacros.bk
  echo '%_topdir '$PWD'/'$builddir > ~/.rpmmacros
  make clean
  mkdir -p $builddir/{BUILD,BUILDROOT/gnuhawk-${version},RPMS,SOURCES,SPECS,SRPMS,gnuhawk-${version}}
  cp -ru `ls | grep -v $builddir` $builddir/gnuhawk-${version}
  rm $builddir/gnuhawk-${version}/gnuradio/volk/CMakeCache.txt
  tar -czvf \
    $builddir/SOURCES/gnuhawk-${version}.tar.gz \
    --exclude-backups \
    --exclude-vcs \
    -C $builddir gnuhawk-${version}
  cp gnuhawk.spec $builddir/SPECS/
  rpmbuild \
    --nodeps \
    --rmsource \
    -ba \
    $builddir/SPECS/gnuhawk.spec
  mv /tmp/rpmmacros.bk ~/.rpmmacros
elif [ "$1" == 'rpmrebuild' ]; then
  cp gnuhawk.spec $builddir/SPECS/
  rpmbuild \
    --nodeps \
    --rmsource \
    -ba \
    $builddir/SPECS/gnuhawk.spec
else
  # Checks if build is newer than makefile (based on modification time)
  if [[ $config_ac -nt $makefile || $make_am -nt $makefile ]]; then
    ./autogen.sh
    ./configure
  fi
  make -j4
  exit 0
fi
