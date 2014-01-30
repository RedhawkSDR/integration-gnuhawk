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
from redhawk.codegen.jinja.generator import CodeGenerator
from redhawk.codegen.jinja.loader import CodegenLoader
from redhawk.codegen.jinja.common import ShellTemplate, AutomakeTemplate, AutoconfTemplate
from redhawk.codegen.jinja.cpp import CppCodeGenerator, CppTemplate
from redhawk.codegen.jinja.cpp.properties import CppPropertyMapper
from redhawk.codegen.jinja.cpp.ports import CppPortMapper, CppPortFactory

from mapping import GnuhawkComponentMapper


if not '__package__'in locals():
    #python 2.4 compatiblity
    __package__=__name__.rsplit('.',1)[0]

loader = CodegenLoader(__package__,
                       {'common'     : 'redhawk.codegen.jinja.common',
                        'base'       : 'redhawk.codegen.jinja.cpp.component.base',
                        'properties' : 'redhawk.codegen.jinja.cpp.properties',
                        'pull'       : 'redhawk.codegen.jinja.cpp.component.pull'})

class GnuhawkComponentGenerator(CppCodeGenerator):
    def parseopts (self, **args):
        if args['pattern_gr_block'] == 'TRUE':
            self.gnuType = 'gr_block'
        elif args['pattern_gr_sync_block'] == 'TRUE':
            self.gnuType = 'gr_sync_block'
        elif args['pattern_gr_sync_decimator'] == 'TRUE':
            self.gnuType = 'gr_sync_decimator'
        elif args['pattern_gr_sync_interpolator'] == 'TRUE':
            self.gnuType = 'gr_sync_interpolator'
        self.useVectorImpl = False
        self.mem_align = args['mem_align']

    def loader(self, component):
        return loader

    def componentMapper(self):
        return GnuhawkComponentMapper(self.gnuType, self.mem_align)

    def propertyMapper(self):
        return CppPropertyMapper()

    def portMapper(self):
        return CppPortMapper()

    def portFactory(self):
        return CppPortFactory()

    def templates(self, component):
        templates = [
            CppTemplate('main.cpp'),
            CppTemplate('resource.cpp', component['userclass']['file']),
            CppTemplate('resource.h', component['userclass']['header']),
            CppTemplate('resource_base.cpp', component['baseclass']['file']),
            CppTemplate('resource_base.h', component['baseclass']['header']),
            CppTemplate('GnuHawkBlock.h', component['prefix'] + '_GnuHawkBlock.h'),
            AutomakeTemplate('Makefile.am'),
            AutomakeTemplate('Makefile.am.ide'),
            AutoconfTemplate('configure.ac'),
            ShellTemplate('reconf')
        ]

        for gen in component['portgenerators']:
            # Need to include port_impl if a non-bulkio port exists
            if str(type(gen)).find("BulkioPortGenerator") == -1:
                templates.append(CppTemplate('pull/port_impl.cpp'))
                templates.append(CppTemplate('pull/port_impl.h'))
                break

        if component['structdefs']:
            templates.append(CppTemplate('struct_props.h'))

        return templates
