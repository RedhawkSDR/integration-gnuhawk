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
import commands
from optparse import OptionParser
import shutil

gnuhawkComponentDir = "./components"

# Check command line argument to get eclipse directory 
parser = OptionParser()
qa_test_prefix = None
parser.add_option("--eclipse_dir",type="string",action="store",dest="eclipse_dir",default=None,help="path to eclipse executable")
parser.add_option("--ignore_components_file",type="string",action="store",dest="ignore_components_file",default='',help="list of components not to regenerate")
(options,args) = parser.parse_args()
eclipse_dir = options.eclipse_dir
ignore_components_file = options.ignore_components_file

# make sure eclipse directory ends in /
if eclipse_dir[len(eclipse_dir)-1] != "/":
    eclipse_dir += "/"

ignore_components = []
if ignore_components_file:
    fp = open(ignore_components_file,'r')
    while True:
        comp = fp.readline()
        if comp == '':
            break
        ignore_components.append(comp[:-1])
    fp.close()

lang = '-Dlang=C++'

gnuhawkComponents=[]
for name in os.listdir(gnuhawkComponentDir):
    gnuhawkComponents.append(name)

for comp in gnuhawkComponents:
    if comp in ignore_components:
        continue
    target = os.getcwd()+'/components/'+comp
    namespace = ''
    try:
        fp = open(target+'/cpp/Makefile.am', 'r')
        old_Makefile = fp.read()
        namespace = old_Makefile[old_Makefile.find('GR_NAMESPACE'):old_Makefile.find('GR_NAMESPACE')+old_Makefile[old_Makefile.find('GR_NAMESPACE'):].find('\n')+1]
        fp.close()
    except:
        pass

    old_Derived = ''
    try:
        fp = open(target+'/cpp/'+comp+'.cpp', 'r')
        old_Derived = fp.read()
        fp.close()
    except:
        pass

    try:
        shutil.copyfile(target+'/cpp/'+comp+'.cpp', target+'/cpp/'+comp+'.cpp.tmp')
    except:
        if ignore_components_file:
            ignore_components.append(comp)
            fp = open(ignore_components_file,'w')
            for entry in ignore_components:
                fp.write(entry+'\n')
            fp.close()
        print "Unable to process "+comp+" due to unexpected file content, continuing..."
        continue
    shutil.copyfile(target+'/cpp/'+comp+'.h', target+'/cpp/'+comp+'.h.tmp')
    shutil.copyfile(target+'/cpp/'+comp+'_GnuHawkBlock.h', target+'/cpp/'+comp+'_GnuHawkBlock.h.tmp')
    codegen = eclipse_dir+'/bin/rhgen -generate '+target+' '+lang
    print "Regenerating "+comp
    (status,output) = commands.getstatusoutput(codegen)
    fp = open(target+'/cpp/Makefile.am', 'r')
    new_Makefile = fp.read()
    fp.close()
    if namespace != '':
        new_Makefile = new_Makefile.replace('GR_NAMESPACE =\n',namespace)
        fp = open(target+'/cpp/Makefile.am', 'w')
        new_Makefile = fp.write(new_Makefile)
        fp.close()
    os.rename(target+'/cpp/'+comp+'.cpp', target+'/cpp/'+comp+'.cpp.new')
    os.rename(target+'/cpp/'+comp+'.h', target+'/cpp/'+comp+'.h.new')
    os.rename(target+'/cpp/'+comp+'_GnuHawkBlock.h', target+'/cpp/'+comp+'_GnuHawkBlock.h.new')
    os.rename(target+'/cpp/'+comp+'.cpp.tmp', target+'/cpp/'+comp+'.cpp')
    os.rename(target+'/cpp/'+comp+'.h.tmp', target+'/cpp/'+comp+'.h')
    os.rename(target+'/cpp/'+comp+'_GnuHawkBlock.h.tmp', target+'/cpp/'+comp+'_GnuHawkBlock.h')
    os.chdir('components/'+comp+'/cpp')
    (status,output) = commands.getstatusoutput('./reconf')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        break
    (status,output) = commands.getstatusoutput('./configure')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        break
    (status,output) = commands.getstatusoutput('make clean')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        break
    (status,output) = commands.getstatusoutput('make -j')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        break
    os.chdir('../../..')

    if ignore_components_file:
        ignore_components.append(comp)
        fp = open(ignore_components_file,'w')
        for entry in ignore_components:
            fp.write(entry+'\n')
        fp.close()
    