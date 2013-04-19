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
 
# Generate a list of all of the available GNURadio blocks within the
# current directory and its subdirectories.

rm componentListA.txt
rm componentListB.txt

# Get huge file list
# -> a
grep -r gr_ *      | \
grep -v Binary     | \
grep -v doxygen    | \
grep -v ".deps"    | \
grep -v "#include" | \
grep -v xml        | \
grep -v NAME       | \
grep -v Makefile > componentListA.txt

# a -> b
grep -h "\.h" componentListA.txt >> componentListB.txt

# b -> a
mv componentListB.txt componentListA.txt

# a -> b
egrep -h 'gr_sync_dec|gr_sync_inter|gr_block|gr_sync_block' componentListA.txt > componentListB.txt

# b -> a
mv componentListB.txt componentListA.txt

# a -> b
egrep -h ' : |\t: ' componentListA.txt > componentListB.txt

# b -> a
mv componentListB.txt componentListA.txt

