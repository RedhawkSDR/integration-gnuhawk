import os
from distutils.core import setup
from distutils.command.install_lib import install_lib

class filtered_install_lib(install_lib):
    def byte_compile(self, files):
        # The default 'install_lib' implementation will attempt to compile all
        # '.py' files in the build tree, including any template files that
        # happen to end in '.py'; filter out everything from a 'templates'
        # directory to prevent this unwanted behavior.
        files = [f for f in files if not os.path.dirname(f).endswith('templates')]
        return install_lib.byte_compile(self,files)

setup(name='gnuhawk-codegen',
      version='1.9.0',
      scripts=['gnuhawk-codegen'],
      cmdclass={'install_lib':filtered_install_lib},
      packages=['gnuhawk',
                'gnuhawk.codegen',
                'gnuhawk.codegen.jinja',
                'gnuhawk.codegen.jinja.cpp',
                'gnuhawk.codegen.jinja.project',
                'gnuhawk.codegen.jinja.project.component'],
      package_data={'gnuhawk.codegen.jinja.cpp':['templates/*'],
                    'gnuhawk.codegen.jinja.project.component':['templates/*']
                   }
      )
