from redhawk.codegen.jinja.cpp.template import *
from redhawk.codegen.jinja.cpp.generator import *
from generator import GnuhawkComponentGenerator, loader

def factory(**opts):
    return GnuhawkComponentGenerator(**opts)

__all__ = ('CppTemplate', 'CppCodeGenerator')
