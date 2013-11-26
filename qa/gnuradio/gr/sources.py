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

class sbSource:
    def __init__(self, data, dataFormat,loop=False):
        """
        Create an sb.InputDAta instace and store off the 
        data and dataFormat arguments for the push() call.

        """

        # need to convert to a list, as GNU QA stuff tends to pass tuples
        self.data   = list(data)
        self.loop = loop
        self.source = sb.DataSource(dataFormat = dataFormat,loop=self.loop)

        if self.loop == False:
            self.EOS = True 
        else:
            self.EOS = False 

        # Defailt to non-complex data.  Will be overwritten by
        # vector_source_c.
        self.complexData = False

    def connect(self, dst, providesPortName=None):
        """
        A wrapper around the Sandbox connect method.  When connect
        is called by GNURadio, the dst argument will be a tuple
        composed of a  destination reference and a streamID.

        """

        # dst is a tuple: first arg is think to connect to 
        # second arg is a streamID
        self.source.connect(dst[0], providesPortName=providesPortName)

        # Hang onto the streamID for the push call.
        self.streamID = str(dst[1])

    def push(self):
        """
        A wrapper around the sb.DataSource.push() method.

        Argumetns to the push method are set in self.__init__()
        and self.connect()

        """
        if self.complexData == True:
            new_data = []
            for val in self.data:
                # python 2.4 doesn't support real and imag 
                #   attributes for non-complex types
                #   so need to test for type
                if type(val) == complex:
                    new_data.append(val.real)
                    new_data.append(val.imag)
                else:
                    new_data.append(val)
                    new_data.append(0.0)
            self.data = new_data

        self.source.push(data        = self.data,
                         streamID    = self.streamID,
                         EOS         = self.EOS,
                         complexData = self.complexData)

class vector_source_b(sbSource):
    def __init__(self,data,repeat=False,vlen=1):
        if repeat == False:
            sbSource.__init__(self, data = data, dataFormat = "octet")
        else:
            sbSource.__init__(self, data = data, dataFormat = "octet",loop=True)

class vector_source_i(sbSource):
    def __init__(self,data,repeat=False,vlen=1):
        if repeat == False:
            sbSource.__init__(self, data = data, dataFormat = "long")
        else:
            sbSource.__init__(self, data = data, dataFormat = "long",loop=True)

class vector_source_s(sbSource):
    def __init__(self,data,repeat=False,vlen=1):
        if repeat == False:
            sbSource.__init__(self, data = data, dataFormat = "short")
        else:
            sbSource.__init__(self, data = data, dataFormat = "short",loop=True)

class vector_source_f(sbSource):
    def __init__(self,data,repeat=False,vlen=1):
        if repeat == False:
            sbSource.__init__(self, data = data, dataFormat = "float")
        else:
            sbSource.__init__(self, data = data, dataFormat = "float",loop=True)

class vector_source_c(sbSource):
    def __init__(self,data,repeat=False,vlen=1):
        if repeat == False:
            sbSource.__init__(self, data = data, dataFormat = "float")
        else:
            sbSource.__init__(self, data = data, dataFormat = "float",loop=True)
        self.complexData = True

