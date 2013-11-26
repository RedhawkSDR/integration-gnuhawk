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
 
# If new components have been made availaable, want to set to True
# so that new associated wrapper functions are generated.
# Otherwise, set this to False to save on processing time.
generateNewComponentWrappers = False

from top_block import *
from sources   import *
from sinks     import *
from gnuradioStubs import *

if generateNewComponentWrappers:
    from genGnuComponentWrappers import *

try:
    from _gnuComponentWrappers import *
except ImportError:
    print "ERROR: _gnuComponentWrappers.py not found.  Try setting generateNewComponentWrappers to True in gr/__init__.py.  This will cause a _gnuComponentWrappers.py file to be generated based on GNUHAWK components installed to $SDRROOT.\n"

# Enum bit orders for packed_to_unpacked blocks
GR_MSB_FIRST = 1
GR_LSB_FIRST = 2
