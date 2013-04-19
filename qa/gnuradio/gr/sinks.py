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
 
import time
from ossie.utils import sb
import numpy
import struct

class sink(sb.DataSink):
    def __init__(self):
        self.numReturned = None 
        self.sleepTime = 0.1

        self.eos_block = True 

        sb.DataSink.__init__(self)

        self.receivedData = None

    def setNumReturned(self, num):
        self.numReturned = num

    def data(self):
        """
        A wrapper around the sb.DataSink.getData() method.

        """
        if self.receivedData != None:
            return self.receivedData

        if not self.eos_block == False:
            # Allow some time for the component to finish processing.
            # This is not needed if EOS is being implemented
            time.sleep(self.sleepTime)

        # Must type-cast as tuple, as GNURADIO code will
        # expect a tuple instead of a list.
        if self.numReturned == None:
            dat = tuple(self.getData(eos_block=self.eos_block))

            # Need to handle complex data
            sri = self.sri()
            if sri != None and sri.mode == 1:
                complex_dat = []
                dat = numpy.reshape(dat, (-1,2))
                for item in dat:
                    complex_dat.append(complex(item[0],item[1]))
                dat = complex_dat

            if str(self.__class__).find("vector_sink_b") != -1: 
                outData = []
                for val in dat:
                    newVal = struct.unpack("B",val)[0]
                    outData.append(newVal)
                dat = outData

            self.receivedData = tuple(dat)
            return self.receivedData 
        else:
            dat = []
            while dat != None and len(dat) == 0:
                dat = self.getData(eos_block=False)

            if dat == None:
                return tuple()
             
            # Need to handle complex data
            sri = self.sri()
            if sri != None and sri.mode == 1:
                complex_dat = []
                dat = numpy.reshape(dat, (-1,2))
                for item in dat:
                    complex_dat.append(complex(item[0],item[1]))
                dat = complex_dat
            self.receivedData = tuple(dat[0:self.numReturned])
            return self.receivedData 


# The GNU QA module will create sinks using one of the
# following names.  For GNUHAWK testing purposes, they
# are all the same
class vector_sink_b(sink):pass
class vector_sink_f(sink):pass
class vector_sink_c(sink):pass
class vector_sink_i(sink):pass
class vector_sink_s(sink):pass
