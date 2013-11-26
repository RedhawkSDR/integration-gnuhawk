#!/usr/bin/env python
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
 

import ossie.parsers.spd as _SPDParser
import ossie.parsers.prf as _PRFParser
import ossie.parsers.scd as _SCDParser
import ossie.parsers.sad as _SADParser
import os as _os
import fnmatch as _fnmatch
from optparse import OptionParser
import sys

parser = OptionParser(usage='This program recursively scans the current directory for SPD files. Each found file is parsed, and all softpkg dependencies, properties, and ports are assembled into a section of the output LaTeX sub-document')
qa_test_prefix = None
parser.add_option("--output",type="string",action="store",dest="output_file",default='out.tex',help="LaTeX output file")
(options,args) = parser.parse_args()
output_file = options.output_file

try:
    fp = open(output_file, 'w')
except:
    print "Unable to open "+output_file
    sys.exit(-1)

files = []
components = []
searchPath = '.'
for root, dirs, fnames in _os.walk(searchPath):
    for filename in _fnmatch.filter(fnames, "*spd.xml"):
        files.append((_os.path.join(root,filename),root+'/'))

tex = '\\documentclass[12pt]{report}\n\\usepackage{tabularx}\n\\begin{document}\n'

for (file_entry,dir_entry) in files:
    print "Evaluating: "+file_entry
    comp = {}
    try:
        tmp = _SPDParser.parse(file_entry)
    except:
        print "Could not parse", file_entry
        continue
    comp['name'] = str(tmp.get_name())
    components.append(str(tmp.get_name()))
    if tmp.description == None:
        comp['description'] = ''
    else:
        comp['description'] = str(tmp.description)
    if tmp.descriptor == None:
        comp['SCD'] = None
    else:
        comp['SCD'] = _SCDParser.parse(dir_entry+str(tmp.descriptor.localfile.get_name()))
    if tmp.propertyfile == None:
        comp['PRF'] = None
    else:
        comp['PRF'] = _PRFParser.parse(dir_entry+str(tmp.propertyfile.localfile.get_name()))
    comp['implementations'] = []
    for impl in tmp.implementation:
        dep = ''
        procs = ''
        first = True
        if impl.dependency != None:
            for dependency in impl.dependency:
                if dependency.softpkgref != None:
                    if first:
                        dep = str(dependency.softpkgref.localfile.get_name())
                        first = False
                    else:
                        dep += ',' + str(dependency.softpkgref.localfile.get_name())
        first = True
        for proc in impl.processor:
            if first:
                procs += str(proc.get_name())
                first = False
            else:
                procs += ','+str(proc.get_name())
        comp['implementations'].append({'dep':dep,'procs':procs})
    comp['ports'] = {}
    if comp['SCD'] != None:
        if comp['SCD'].componentfeatures != None:
            if comp['SCD'].componentfeatures.ports != None:
                for prov in comp['SCD'].componentfeatures.ports.provides:
                    comp['ports'][str(prov.get_providesname())] = (str(prov.get_repid()),'provides')
                for use in comp['SCD'].componentfeatures.ports.uses:
                    comp['ports'][str(use.get_usesname())] = (str(use.get_repid()),'uses')
    comp['base'] = str(comp['SCD'].componentrepid.get_repid())
    comp['props'] = []
    for prop in comp['PRF'].simple:
        kinds = []
        for kind in prop.kind:
           kinds.append(str(kind.get_kindtype()))
        action = None
        if 'allocation' in kinds:
           action = str(prop.action.get_type())
        name = ''
        if prop.get_name() != None:
            name = str(prop.get_name())
        comp['props'].append({'prop_type':'simple','id':str(prop.get_id()),'name':name,'mode':str(prop.get_mode()),'type':str(prop.get_type()),'kinds':kinds,'action':action})
    for prop in comp['PRF'].simplesequence:
        kinds = []
        for kind in prop.kind:
           kinds.append(str(kind.get_kindtype()))
        action = None
        if 'allocation' in kinds:
           action = str(prop.action.get_type())
        name = ''
        if prop.get_name() != None:
            name = str(prop.get_name())
        comp['props'].append({'prop_type':'simplesequence','id':str(prop.get_id()),'name':name,'mode':str(prop.get_mode()),'type':str(prop.get_type()),'kinds':kinds,'action':action})
    for prop in comp['PRF'].struct:
        kinds = []
        for kind in prop.configurationkind:
           kinds.append(str(kind.get_kindtype()))
        name = ''
        if prop.get_name() != None:
            name = str(prop.get_name())
        subprops = []
        for subprop in prop.simple:
            name = ''
            if subprop.get_name() != None:
                name = str(subprop.get_name())
            subprops.append({'id':str(subprop.get_id()),'name':name,'type':str(subprop.get_type())})
        comp['props'].append({'prop_type':'struct','id':str(prop.get_id()),'name':name,'mode':str(prop.get_mode()),'members':subprops,'kinds':kinds,})
    for prop in comp['PRF'].structsequence:
        kinds = []
        for kind in prop.configurationkind:
           kinds.append(str(kind.get_kindtype()))
        name = ''
        if prop.get_name() != None:
            name = str(prop.get_name())
        subprops = []
        for subprop in prop.struct.simple:
            name = ''
            if subprop.get_name() != None:
                name = str(subprop.get_name())
            subprops.append({'id':str(subprop.get_id()),'name':name,'type':str(subprop.get_type())})
        comp['props'].append({'prop_type':'structsequence','id':str(prop.get_id()),'name':name,'mode':str(prop.get_mode()),'members':subprops,'kinds':kinds,})
    tex += '\\section{'+comp['name']+'}\n\n'
    if comp['description'] == '':
        tex += '\nNo description is available for this component.\n\n'
    else:
        tex += '\n'+comp['description']+'\n\n'
    tex += '\\subsection{Base supported interface}\nThis is the base supported interface for this component.\n'
    tex += '\\begin{itemize}\\setlength{\\itemsep}{-3pt}\\setlength{\parsep}{0pt}\\setlength{\\topsep}{0pt}\\setlength{\partopsep}{0pt}'
    tex += '\\item \\texttt{'+comp['base']+'}\n'
    tex += '\\end{itemize}\n\n'
    
    if len(comp['ports']) == 0:
        tex += '\\subsection{Ports}\nThis component contains no ports.\n\n'
    else:
        tex += '\\subsection{Ports}\nThese are the input and output ports for this component (name, interface, direction).\n\n'
        tex += '\\begin{itemize}\\setlength{\\itemsep}{-3pt}\\setlength{\parsep}{0pt}\\setlength{\\topsep}{0pt}\\setlength{\partopsep}{0pt}'
        for port in comp['ports']:
            if comp['ports'][port][1] == 'provides':
                tex += '\\item \\textbf{'+port+'}, \\texttt{'+comp['ports'][port][0]+'}, provides/input\n'
            else:
                tex += '\\item \\textbf{'+port+'}, \\texttt{'+comp['ports'][port][0]+'}, uses/output\n'
        tex += '\\end{itemize}\n\n'
    tex += '\n'
    if len(comp['ports']) == 0:
        tex += '\\subsection{Requirements}\nNo processor(s) and dependencies are available for this component.\n\n'
    else:
        tex += '\\subsection{Requirements}\nThese are the processor(s) and dependencies for this component ([processors], softpkg dependency).\n\n'
        tex += '\\begin{itemize}\\setlength{\\itemsep}{-3pt}\\setlength{\parsep}{0pt}\\setlength{\\topsep}{0pt}\\setlength{\partopsep}{0pt}'
        for impl in comp['implementations']:
            tex += '\\item {['+impl['procs']+']}, \\texttt{'+impl['dep']+'}\n'
        tex += '\\end{itemize}\n\n'
    tex += '\n'
    if len(comp['props']) == 0:
        tex += '\\subsection{Properties}\nNo properties are available in this component.\n\n'
    else:
        tex += '\\subsection{Properties}\nThese are the properties available in this component (id/(optional)name, class (i.e.: simple, simplesequence, struct, structsequence), type, and mode). In the case of structures, the type is presented in the form [(id:type)....(id:type)] describing the members of the structure.\n\n'
        tex += '\\begin{itemize}\\setlength{\\itemsep}{-3pt}\\setlength{\parsep}{0pt}\\setlength{\\topsep}{0pt}\\setlength{\partopsep}{0pt}'
        for prop in comp['props']:
            if prop['prop_type'] == 'simple':
                if prop['name'] != '':
                    tex += '\\item \\textbf{'+prop['id']+'/'+prop['name']+'}, \\texttt{'+prop['type']+'}, \\textit{simple}, '+prop['mode']+'\n'
                else:
                    tex += '\\item \\textbf{'+prop['id'] +'}, \\texttt{'+prop['type']+'}, \\textit{simple}, '+prop['mode']+'\n'
            if prop['prop_type'] == 'simplesequence':
                if prop['name'] != '':
                    tex += '\\item \\textbf{'+prop['id']+'/'+prop['name']+'}, \\texttt{'+prop['type']+'}, \\textit{simplesequence}, '+prop['mode']+'\n'
                else:
                    tex += '\\item \\textbf{'+prop['id'] +'}, \\texttt{'+prop['type']+'}, \\textit{simplesequence}, '+prop['mode']+'\n'
            if prop['prop_type'] == 'struct':
                if prop['name'] != '':
                    tex += '\\item \\textbf{'+prop['id']+'/'+prop['name']+'}, ['
                    for subprop in prop['members']:
                        tex += '(\\textbf{'+subprop['id']+'}:\\texttt{'+subprop['type']+'})'
                    tex += '], \\textit{struct}, '+prop['mode']+'\n'
                else:
                    tex += '\\item \\textbf{'+prop['id']+'}, ['
                    for subprop in prop['members']:
                        tex += '(\\textbf{'+subprop['id']+'}:\\texttt{'+subprop['type']+'})'
                    tex += '], \\textit{struct}, '+prop['mode']+'\n'
            if prop['prop_type'] == 'structsequence':
                if prop['name'] != '':
                    tex += '\\item \\textbf{'+prop['id']+'/'+prop['name']+'}, ['
                    for subprop in prop['members']:
                        tex += '(\\textbf{'+subprop['id']+'}:\\texttt{'+subprop['type']+'})'
                    tex += '], \\textit{structsequence}, '+prop['mode']+'\n'
                else:
                    tex += '\\item \\textbf{'+prop['id']+'}, ['
                    for subprop in prop['members']:
                        tex += '(\\textbf{'+subprop['id']+'}:\\texttt{'+subprop['type']+'})'
                    tex += '], \\textit{structsequence}, '+prop['mode']+'\n'
        tex += '\\end{itemize}\n\n'

tex += '\\end{document}\n'
tex = tex.replace('_','\_')
print "Writing "+output_file
fp.write(tex)
fp.close()
print "Done."
