from redhawk.codegen.jinja.common import ShellTemplate, SpecfileTemplate
from redhawk.codegen.jinja.project.component.mapping import ProjectMapper
from redhawk.codegen.jinja.project.component.generator import ComponentProjectGenerator
from redhawk.codegen.jinja.loader import CodegenLoader

if not '__package__' in locals():
    # Python 2.4 compatibility
    __package__ = __name__.rsplit('.', 1)[0]

loader = CodegenLoader(__package__,
                       {'base'       : 'redhawk.codegen.jinja.project.component'})

class GnuhawkComponentProjectGenerator(ComponentProjectGenerator):
    def projectMapper(self):
        return ProjectMapper()

    def loader(self, project):
        return loader

    def templates(self, project):   
        return [
            ShellTemplate('base/build.sh'),
            SpecfileTemplate('component.spec', project['name']+'.spec')
            ]
