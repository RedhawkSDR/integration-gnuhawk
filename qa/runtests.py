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
 
import unittest 
import os
import fnmatch
import sys
from optparse import OptionParser
from gnuradio import gr

debug_level=None

def findall(topdir, pattern):
    foundList = []
    for path, dirs, files in os.walk(topdir):
        for name in files:
           if fnmatch.fnmatch(name,pattern):
                foundList.append((path,name))
    return foundList

# If it doesn't exist, generate gnuradio block component wrappers
if not os.path.exists("./gnuradio/gr/_gnuComponentWrappers.py"):
    os.system("(cd gnuradio/gr; python genGnuComponentWrappers.py)")

# Check command line argument to see if a subset of tests is to be run
parser = OptionParser()
qa_test_prefix = None 
parser.add_option("--prefix",type="string",action="store",dest="prefix",default=None,help="only run tests with this prefix")
parser.add_option("--debug",type="int",action="store",dest="debug",default=None,help="add debug param to component instantiation")
(options,args) = parser.parse_args()
qa_test_prefix = options.prefix
debug_level = options.debug
gr.setDebug(debug_level)
if (len(args) >= 1):
    qa_test_string = args[0]
    if (len(args)>1):
        qa_test_prefix = args[1]
        print "Running qa test " + str(qa_test_string) + " with test prefix " + str(qa_test_prefix) + " ==============================="
    else:
        print "Running qa test " + str(qa_test_string) + " ==============================="
else:
    print "Running all qa tests ==============================="
    qa_test_string = "qa_*.py"

# Find qa files contained in gnuradio baseline
found_files = findall("./tests", qa_test_string) 
module_strings = []
for path,filename in found_files:
        module_strings.append(filename[0:len(filename)-3])

# Add gnuhawk/qa path to find gnuhawk gnuradio.gr python package
cwd = os.getcwd()
if not cwd in sys.path:
    sys.path.append(cwd)
# Add path to local versions of swig modules
if not cwd+"/swig_modules" in sys.path:
    sys.path.append(cwd+"/swig_modules")
# Add path to tests 
if not cwd+"/tests" in sys.path:
    sys.path.append(cwd+"/tests")

# Set the the DEBUG_LEVEL execparam for the other component namespaces
swig_modules = os.listdir(cwd+"/swig_modules")
for mod in swig_modules:
    x = __import__(mod)
    x.setDebug(debug_level)

testLoader = unittest.TestLoader()
if qa_test_prefix != None:
    testLoader.testMethodPrefix = qa_test_prefix 
suites = [testLoader.loadTestsFromName(mod_str) for mod_str in module_strings]
testSuite = unittest.TestSuite(suites)
suite = unittest.TestSuite()
text_runner = unittest.TextTestRunner(verbosity=5).run(testSuite)


