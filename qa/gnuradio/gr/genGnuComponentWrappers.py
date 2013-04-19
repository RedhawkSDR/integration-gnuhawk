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
 
"""
This modules generates a file containing functions that work as wrappers
to GNUHAWK components.  When a GNU QA module attempts to create a
GNU componet (e.g., gr.moving_average_ss), the class invocation is
replaced by a function (e.g., moving_average_ss()).

One function is created for each GNUHAWK componet installed to
SDRROOT.  GNUHAWK components are assumed to have the prefix RH_GR_PREFIX
(typically "rh_gr_"):

Below is an example generated function:

        from ossie.utils import sb
        def moving_average_ss(length, scale, max_iter):
            c = sb.Component("rh_gr_moving_average_ss")
            c.length = length
            c.scale = scale
            c.max_iter = max_iter
            return c

"""

from ossie.utils import sb

RH_GR_PREFIX = "rh_gr_"

def getProps(component):
    """
    Instantiate the GNUHAWK component of interest in the Sandbox in
    order to get a list of available properties.
    """

    tmpComponent = sb.Component(component)
    return tmpComponent.__dict__["_props"].keys()

def getPropValue(component, prop):
    tmpComponent = sb.Component(component)
    return eval("tmpComponent."+prop)

def writeComponentFunction(component, fp):
    """
    Generate a wrapper function to the GNUHAWK component.

    Below is an example generated function:

        from ossie.utils import sb
        def moving_average_ss(length, scale, max_iter):
            c = sb.Component("rh_gr_moving_average_ss")
            c.length = length
            c.scale = scale
            c.max_iter = max_iter
            return c

    """

    # The gnuName is the GNUHAWK name without the RH_GR_PREFIX
    # e.g., moving_average_ss instead of rh_gr_moving_average_ss
    gnuName = component.split(RH_GR_PREFIX)[1]

    props = getProps(component)

    defStr = "def %s(" % gnuName

    configStrings = ""
    compStr = "    " + "c = sb.Component('%s%s')\n" % (RH_GR_PREFIX, gnuName)
    defStr2 = ""

    # Loop through the properties to populate the input arguments
    # to the function and to populate the property sets for the component.
    for prop in props:
        propDefaultValue = getPropValue(component,prop)
        # replace any "."'s which may occur with struct properties
        # "."'s cause syntax error with class argument names
        if prop.find(".") != -1:
            prop = prop.replace(".","_")
        if prop == "vlen":
            defStr2 += ", vlen = 1"
            configStrings += "    c." + prop + " = 1\n"
        else:
            defStr2 += ", " + prop + "=" + str(propDefaultValue) 
            configStrings += "    c." + prop + " = " + prop + "\n"

    if len(defStr2) > 1:
        # if there is at least 1 property
        # strip off teh leading ", "
        defStr += defStr2[2:]

    # close out the method definition line
    funcString = defStr + "):\n"

    # component instantiations
    funcString += compStr

    # component property sets
    funcString += configStrings

    # return statement at the end of the function
    funcString += "    return c\n"

    # Write out teh entire function for this component to the file
    fp.write(funcString)

def main():
    fp = open("_gnuComponentWrappers.py", "w")

    # Always include the sandbox import statement at the beginning
    # of the file
    fp.write("from ossie.utils import sb\n")

    # Write a function for each component in SDRROOT prefixed by RH_GR_PREFIX
    for component in sb.catalog():
        if component.find(RH_GR_PREFIX) != -1:
            writeComponentFunction(component, fp)

    fp.close

main()
