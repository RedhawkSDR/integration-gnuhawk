from redhawk.codegen.jinja.generator import CodeGenerator
from redhawk.codegen.jinja.loader import CodegenLoader
from redhawk.codegen.jinja.common import ShellTemplate, AutomakeTemplate, AutoconfTemplate
from redhawk.codegen.jinja.cpp import CppCodeGenerator, CppTemplate
from redhawk.codegen.jinja.cpp.properties import CppPropertyMapper
from redhawk.codegen.jinja.cpp.ports import CppPortMapper, CppPortFactory

from mapping import GnuhawkComponentMapper

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

    def loader(self, component):
        return loader

    def componentMapper(self):
        return GnuhawkComponentMapper(self.gnuType)

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
