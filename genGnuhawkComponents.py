#!/bin/env python
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

def bkup_component_cpp( cdir, bkdir=None ):
    cppdir =  os.path.join(cdir,'cpp')
    if not bkdir:
        bkupdir=os.path.join(cdir,'bkup.cpp')
    try:
        shutil.os.stat(bkupdir)
        shutil.rmtree(bkupdir)
    except Exception, e:
        pass

    shutil.copytree( cppdir, bkupdir)
    return cppdir, bkupdir


def regen_component( gnuhawk_root, dname, comp, ignore_components_file ):
    print "COMPONENT ["+comp+"] SRC DIR:" + os.path.join(gnuhawk_root, dname )
    # start regen process
    target = os.path.join( gnuhawk_root,dname,comp)

    cppdir=None
    bkupdir=None
    try:
        cppdir,bkupdir =bkup_component_cpp( target )
        print "\t["+comp+"] Creating backup: ", cppdir, bkupdir
    except Exception, e:
        print "WARN: Unable to process "+comp+",  Continuing..." + str(e)

    # get namespace if assigned
    namespace = ''
    try:
        fp = open(cppdir+'/Makefile.am', 'r')
        old_Makefile = fp.read()
        namespace = old_Makefile[old_Makefile.find('GR_NAMESPACE'):old_Makefile.find('GR_NAMESPACE')+old_Makefile[old_Makefile.find('GR_NAMESPACE'):].find('\n')+1]
        fp.close()
    except:
        pass

    os.chdir(target)
    gendir_cmd=''
    if gendir:
        gendir_cmd='-C ./' + gendir + ' '
        # resolve: relative directories are appended to comp/cpp/<dir>
        cppdir=os.path.join(target,"cpp",gendir)
        print "\t["+comp+"] Changing CPP directory to:" + str(cppdir)

    codegen = 'gnuhawk-codegen -f '+gendir_cmd+comp+'.spd.xml'
    print "\t["+comp+"] Regenerating Code:" + codegen
    (status,output) = commands.getstatusoutput(codegen)

    os.chdir(cppdir)
    # reapply namespace
    fp = open('Makefile.am', 'r')
    new_Makefile = fp.read()
    fp.close()
    if namespace != '':
        new_Makefile = new_Makefile.replace('GR_NAMESPACE =\n',namespace)
        fp = open('Makefile.am', 'w')
        new_Makefile = fp.write(new_Makefile)
        fp.close()

    ## swap old component header/cpp and gnuhawk block definition
    shutil.copyfile( comp+'.cpp', comp+'.cpp.new' )
    shutil.copyfile( comp+'.h',  comp+'.h.new')
    shutil.copyfile( comp+'_GnuHawkBlock.h', comp+'_GnuHawkBlock.h.new')
    
    shutil.copyfile( bkupdir+'/'+comp+'.cpp', cppdir+'/'+comp+'.cpp')
    shutil.copyfile( bkupdir+'/'+comp+'.h', cppdir+'/'+comp+'.h')
    shutil.copyfile( bkupdir+'/'+comp+'_GnuHawkBlock.h', cppdir+'/'+comp+'_GnuHawkBlock.h')

    os.chdir(cppdir)
    print "\t["+comp+"] Reconf/Configure/Make...."
    (status,output) = commands.getstatusoutput('./reconf')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        return -1
    (status,output) = commands.getstatusoutput('./configure')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        return -1
    (status,output) = commands.getstatusoutput('make clean')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        return -1
    (status,output) = commands.getstatusoutput('make -j')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to compile",comp
        return -1
    
    os.chdir(gnuhawk_root)

    if ignore_components_file:
        ignore_components.append(comp)
        fp = open(ignore_components_file,'w')
        for entry in ignore_components:
            fp.write(entry+'\n')
        fp.close()

    return 0


# search directories for components... don't forget to add components/xxxxx to ignore_files list below (i.e. skip the directory)        
gnuhawkComponentDirs = [ "components", "components/components_for_testing" ]

# Check command line argument to get eclipse directory 
parser = OptionParser()
qa_test_prefix = None
gendir=None
parser.add_option("--ignore_components_file",type="string",action="store",dest="ignore_components_file",default='',help="list of components not to regenerate")
##parser.add_option("--gendir",type="string",action="store",dest="gendir",default=None,help="output directory to save generated source")
(options,args) = parser.parse_args()
ignore_components_file = options.ignore_components_file
##gendir=options.gendir

#
# list of files to ignore and any subdirectories to traverse...
#
ignore_files=[ 'Makefile', 'Makefile.in', 'Makefile.am', 'scaLatex.py', 'components_to_ignore', 'bld', 'components_for_testing', 'reconf', 'README.txt', 'mk.comp.out', 'config.log', 'config.status', 'configure', 'configure.ac', 'acinclude.m4', 'aclocal.m4', 'cfg.cdirs.out' '.gitignore', 'autom4te.cache', 'cfg.cdirs.out' ]

# These are for components that need to be manually edited because they have changes to the _base classes
ignore_comps = ['simple_framer', 'endian_swap_ss', 'endian_swap_bb', 'endian_swap_cc', 'endian_swap_ii']

ignore_files.extend(ignore_comps)

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


gnuhawk_root=os.getcwd()
for dname in gnuhawkComponentDirs:

    gnuhawkComponents=[]

    for name in os.listdir(dname):
        gnuhawkComponents.append(name)

    #
    # test case....  gnuhawkComponents=[ 'add_const_ii', 'add_const_ff' ]
    #
    for comp in gnuhawkComponents:

        # check to ignore the component
        if comp in ignore_components:
            print "Ignore: COMP:" + str(comp)
            continue

        if comp in  ignore_files:
            print "Skipping FILE:" + str(comp)
            continue

        if regen_component( gnuhawk_root, dname, comp, ignore_components_file ) < 0 :
            sys.exit(-1)

