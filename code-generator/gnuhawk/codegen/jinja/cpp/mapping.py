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
from redhawk.codegen.jinja.cpp.component.pull.mapping import PullComponentMapper

class GnuhawkComponentMapper(PullComponentMapper):
    def __init__(self, gnuType, mem_align):
        self.gnuType = gnuType
        self.mem_align = mem_align

    def _mapComponent(self, softpkg):
        cppcomp =  super(GnuhawkComponentMapper, self)._mapComponent(softpkg)
        cppcomp['hasbulkiouses'] = self.hasBulkioUsesPorts(softpkg)
        cppcomp['hasbulkioprovides'] = self.hasBulkioProvidesPorts(softpkg)
        cppcomp['prefix'] = softpkg.name()
        cppcomp['inputType'] = self.getBulkioInputType(softpkg)
        cppcomp['outputType'] = self.getBulkioOutputType(softpkg)
        cppcomp['cppLicense'] = self.cppLicense()
        cppcomp['shellLicense'] = self.shellLicense()
        cppcomp['gnuType'] = self.gnuType
        cppcomp['mem_align'] = self.mem_align
        return cppcomp

    def hasBulkioUsesPorts(self, softpkg):
        for port in softpkg.usesPorts():
            if 'BULKIO' in port.repid():
                return True
        return False

    def getBulkioInputType(self, softpkg):
        for port in softpkg.providesPorts():
            if 'BULKIO' in port.repid():
                interface = port.repid()[11:].split(':')[0][1:][3:]
                porttype = 'In'
                if interface[0] == "U":
                    porttype += interface[0] + interface[1].upper() + interface[2:] + 'Port'
                else:
                    porttype += interface + 'Port'
                return porttype

    def getBulkioOutputType(self, softpkg):
        for port in softpkg.usesPorts():
            if 'BULKIO' in port.repid():
                interface = port.repid()[11:].split(':')[0][1:][3:]
                porttype = 'Out'
                if interface[0] == "U":
                    porttype += interface[0] + interface[1].upper() + interface[2:] + 'Port'
                else:
                    porttype += interface + 'Port'
                return porttype

    def cppLicense(self):
        return '/*\n\
 * This file is protected by Copyright. Please refer to the COPYRIGHT file\n\
 * distributed with this source distribution.\n\
 * \n\
 * This file is part of GNUHAWK.\n\
 * \n\
 * GNUHAWK is free software: you can redistribute it and/or modify is under the \n\
 * terms of the GNU General Public License as published by the Free Software \n\
 * Foundation, either version 3 of the License, or (at your option) any later \n\
 * version.\n\
 * \n\
 * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY \n\
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS \n\
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more \n\
 * details.\n\
 * \n\
 * You should have received a copy of the GNU General Public License along with \n\
 * this program.  If not, see http://www.gnu.org/licenses/.\n\
 */'

    def shellLicense(self):
        return '#\n\
# This file is protected by Copyright. Please refer to the COPYRIGHT file \n\
# distributed with this source distribution.\n\
# \n\
# This file is part of GNUHAWK.\n\
# \n\
# GNUHAWK is free software: you can redistribute it and/or modify is under the \n\
# terms of the GNU General Public License as published by the Free Software \n\
# Foundation, either version 3 of the License, or (at your option) any later \n\
# version.\n\
# \n\
# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY \n\
# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR \n\
# A PARTICULAR PURPOSE.  See the GNU General Public License for more details.\n\
# \n\
# You should have received a copy of the GNU General Public License along with \n\
# this program.  If not, see http://www.gnu.org/licenses/.\n\
#'
