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
 
from ossie.utils import sb 

GR_CONST_WAVE = 100 
GR_SIN_WAVE   = 101
GR_COS_WAVE   = 102 
GR_SQR_WAVE   = 103 
GR_TRI_WAVE   = 104 
GR_SAW_WAVE   = 105 

# Noise types
GR_UNIFORM   = 200
GR_GAUSSIAN  = 201
GR_LAPLACIAN = 202
GR_IMPULSE   = 203

class sizeof_char(object):
    def __init__(self):
        pass 

class sizeof_float(object):
    def __init__(self):
        pass 

class sizeof_int(object):
    def __init__(self):
        pass 

class sizeof_gr_complex(object):
    def __init__(self):
        pass 

class sizeof_short(object):
    def __init__(self):
        pass 

class stream_to_vector(object):
    def __init__(self, sizeof, vlen):
        pass 

class vector_to_stream(object):
    def __init__(self, sizeof, vlen):
        pass

class head:
    def __init__(self, sizeof, num_items):
        self.comp = None
        if str(sizeof.__name__).find("sizeof_char") != -1:
            self.comp = sb.Component("./components/gr_head_octet/gr_head_octet.spd.xml")
        elif str(sizeof.__name__).find("sizeof_float") != -1:
            self.comp = sb.Component("./components/gr_head_float/gr_head_float.spd.xml")
        elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
            self.comp = sb.Component("./components/gr_head_complex/gr_head_complex.spd.xml")
        elif str(sizeof.__name__).find("sizeof_int") != -1:
            self.comp = sb.Component("./components/gr_head_int/gr_head_int.spd.xml")
        elif str(sizeof.__name__).find("sizeof_short") != -1:
            self.comp = sb.Component("./components/gr_head_short/gr_head_short.spd.xml")

        if self.comp != None:
            self.comp.num = num_items
    def connect(self, dst):
        if self.comp != None:
            self.comp.connect(dst)
