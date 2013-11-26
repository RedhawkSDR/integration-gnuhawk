This file is protected by Copyright. Please refer to the COPYRIGHT file 
distributed with this source distribution.

This file is part of GNUHAWK.

GNUHAWK is free software: you can redistribute it and/or modify is under the 
terms of the GNU General Public License as published by the Free Software 
Foundation, either version 3 of the License, or (at your option) any later 
version.

GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with 
this program.  If not, see http://www.gnu.org/licenses/.

Table of Contents:

* Adding New Components to Build Environment
* Removing A Component from the Build Environment


############################################
Adding New Components to Build Environment
############################################

1) Generate component using REDHAWK IDE and GNUHAWK code generator

2) cp component directory under gnuhawk/components

3) from qa directory 
   ./bld/fixcomp  <component directory name >

4) add component to build environment, just the component directory name
   vi bld/cdirs

5) Rebuild Components:

   -- from gnuhawk/components
   ./reconf; ./configure; make
  
   or 
   
   -- for a specific component 
   cd gnuhawk/components/<component name>/cpp
   ./reconf;./configure; make

   or 


   -- to build with gnuhawk sdr dependency
   cd gnuhawk/components/<component name>/cpp
   ./configure --enable-deps=sdr; make
   



############################################
Removing A Component from the Build Environment
############################################

1) cd gnuhawk/components/bld

2) vi ignore_dirs
   -- add component name to list

3) cd gnuhawk/components
   ./reconf; ./configure
