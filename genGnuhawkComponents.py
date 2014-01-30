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
import sys 
import os
import commands
from optparse import OptionParser
import shutil
from getopt import getopt

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


def regen_component( gnuhawk_root, dname, comp, ignore_components_file, mem_align_comps, mem_align_comps_only,gen_only, install ):
    print "COMPONENT ["+comp+"] SRC DIR:" + os.path.join(gnuhawk_root, dname )
    # start regen process
    target = os.path.join( gnuhawk_root,dname,comp)
    fixCompTarget = os.path.join(gnuhawk_root,dname)
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
    #check if component needs mem_align to provide different code generation
    if comp in mem_align_comps or mem_align_comps_only:
        codegen = 'gnuhawk-codegen -fa '+gendir_cmd+comp+'.spd.xml'
        print "MEM ALIGN: " + codegen
    else:
        codegen = 'gnuhawk-codegen -f '+gendir_cmd+comp+'.spd.xml'
    print "\t["+comp+"] Regenerating Code:" + codegen
    (status,output) = commands.getstatusoutput(codegen)
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to Regenerate Code",comp
        return -1

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
    shutil.copyfile( bkupdir+'/'+'reconf', cppdir+'/'+'reconf')
    shutil.copyfile( bkupdir+'/'+'Makefile.am', cppdir+'/'+'Makefile.am')
    shutil.copyfile( bkupdir+'/'+'configure.ac', cppdir+'/'+'configure.ac')

    if not gen_only:
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
            print "reconf"
            return -1
        (status,output) = commands.getstatusoutput('./configure')
        if status != 0:
            print "=========="
            print status
            print "----------"
            print output
            print "**********"
            print "Failed to compile",comp
            print "configure"
            return -1
        (status,output) = commands.getstatusoutput('make clean')
        if status != 0:
            print "=========="
            print status
            print "----------"
            print output
            print "**********"
            print "Failed to compile",compi
            print "make clean"
            return -1
        (status,output) = commands.getstatusoutput('make -j')
        if status != 0:
            print "=========="
            print status
            print "----------"
            print output
            print "**********"
            print "Failed to compile",comp
            print "make -j"
            return -1
        if install:
            (status,output) = commands.getstatusoutput('make install')
            if status != 0:
                print "=========="
                print status
                print "----------"
                print output
                print "**********"
                print "Failed to install",comp
                return -1
        """
        if install:
            (status,output) = commands.getstatusoutput('make distclean')
            if status != 0:
                print "=========="
                print status
                print "----------"
                print output
                print "**********"
                print "Failed make distclean",comp
                return -1
            else:
                print status
        (status,output) = commands.getstatusoutput('rm *.new')
        if status != 0:
            print "failed to remove .new"
            return -1
        """   
    os.chdir(target)
    (stutus,output) = commands.getstatusoutput('rm -rf bkup.cpp')
    if status != 0:
        print "=========="
        print status
        print "----------"
        print output
        print "**********"
        print "Failed to delete bkup.cpp",comp
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
parser.add_option("--gen_components_file", type="string",action="store",dest="gen_components_file",default='')
parser.add_option("-a", "--memory align", action="store_true", dest="mem_align_comps_only")
parser.add_option("-g", "--generate only", action="store_true", dest="gen_only")
parser.add_option("-i", "--install", action="store_true", dest="install")
parser.add_option("-c", type="string", action="store",dest="single_component",default='')
##parser.add_option("--gendir",type="string",action="store",dest="gendir",default=None,help="output directory to save generated source")
(options,args) = parser.parse_args()
ignore_components_file = options.ignore_components_file
##gendir=options.gendir
mem_align_comps_only = options.mem_align_comps_only
gen_only = options.gen_only
install = options.install
gen_components_file = options.gen_components_file
single_component = options.single_component
#
# list of files to ignore and any subdirectories to traverse...
#
ignore_files=[ 'Makefile', 'Makefile.in', 'Makefile.am', 'scaLatex.py', 'components_to_ignore', 'bld', 'components_for_testing', 'reconf', 'README.txt', 'mk.comp.out', 'config.log', 'config.status', 'configure', 'configure.ac', 'acinclude.m4', 'aclocal.m4', 'cfg.cdirs.out' '.gitignore', 'autom4te.cache', 'cfg.cdirs.out' ]

# These are for components that need to be manually edited because they have changes to the _base classes
ignore_comps = ['simple_framer', 'endian_swap_ss', 'endian_swap_bb', 'endian_swap_cc', 'endian_swap_ii']

mem_align_comps = ['add_ff_2i','char_to_float','char_to_short','complex_to_arg', 'complex_to_float_1o', 'complex_to_float_2o','complex_to_imag', 'complex_to_mag', 'complex_to_mag_squared', 'complex_to_real', 'conjugate_cc','fft_filter_ccc','fft_filter_fff', 'filter_delay_fc_1i', 'filter_delay_fc_2i', 'fir_filter_ccc','fir_filter_ccf', 'fir_filter_fcc','fir_filter_fff','fir_filter_fsf', 'fir_filter_scc','fll_band_edge_cc_4o','float_to_int','float_to_short', 'hilbert_fc', 'int_to_float', 'multiply_cc_2i','multiply_ff_2i','multiply_const_cc', 'multiply_const_ff', 'multiply_conjugate_cc', 'multiply_ff_2i', 'pfb_clock_sync_ccf_4o', 'psk_demod_cb', 'short_to_float']
#the following components all need mem align but cannot use the normal code generator
#mem align components that need to be put into a std::vector for writing: float_to_char, short_to_char
#mem align components taht need to have the output multiple set endian_swap_bb, endian_swap_cc, endian_swap_ii, endian_swap_ss
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

gen_components = []
if gen_components_file:
    fp = open(gen_components_file,'r')
    while True:
        comp = fp.readline()
        if comp == '':
            break
        gen_components.append(comp[:-1])
    fp.close()


lang = '-Dlang=C++'



gnuhawk_root=os.getcwd()
for dname in gnuhawkComponentDirs:
    if single_component and dname == "components":
        if regen_component(gnuhawk_root,dname,single_component,ignore_components_file,mem_align_comps,mem_align_comps_only,gen_only,install) < 0:
            sys.exit(-1)
    
    elif gen_components_file and dname == "components":
        for comp in gen_components:
            print "gen_components file " + comp
            if regen_component( gnuhawk_root, dname, comp, ignore_components_file, mem_align_comps, mem_align_comps_only,gen_only, install ) < 0 :
                sys.exit(-1)


    elif mem_align_comps_only and dname == "components":
        #mem_align_comps=['add_ff_2i']
        for comp in mem_align_comps:
            print "MEM_ALIGN: ", comp
            if regen_component( gnuhawk_root, dname, comp, ignore_components_file, mem_align_comps, mem_align_comps_only,gen_only, install ) < 0 :
                sys.exit(-1)

    elif not mem_align_comps_only and not gen_components_file and not single_component:
        gnuhawkComponents=[]

        for name in os.listdir(dname):
            gnuhawkComponents.append(name)

        #
        # test case....  gnuhawkComponents=[ 'add_const_ii', 'add_const_ff' ]
        #
        for comp in gnuhawkComponents:
            print "ALL: ", comp
            # check to ignore the component
            if comp in ignore_components:
                print "Ignore: COMP:" + str(comp)
                continue

            if comp in  ignore_files:
                print "Skipping FILE:" + str(comp)
                continue
          
            if regen_component( gnuhawk_root, dname, comp, ignore_components_file, mem_align_comps, mem_align_comps_only,gen_only, install ) < 0 :
                sys.exit(-1)

