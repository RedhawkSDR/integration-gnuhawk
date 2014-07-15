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
      version='1.10.0',
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
