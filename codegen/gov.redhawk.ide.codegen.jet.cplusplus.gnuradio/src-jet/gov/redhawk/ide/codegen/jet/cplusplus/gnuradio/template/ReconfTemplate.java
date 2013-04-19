// BEGIN GENERATED CODE
/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file 
 * distributed with this source distribution.
 * 
 * This file is part of GNUHAWK.
 * 
 * GNUHAWK is free software: you can redistribute it and/or modify is under the 
 * terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * 
 * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program.  If not, see http://www.gnu.org/licenses/.
 */
// Identification: $Revision$
package gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template;

/**
 * @generated
 */
public class ReconfTemplate
{

  protected static String nl;
  public static synchronized ReconfTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ReconfTemplate result = new ReconfTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#!/bin/sh" + NL + "#" + NL + "# This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + "# distributed with this source distribution." + NL + "# " + NL + "# This file is part of GNUHAWK." + NL + "# " + NL + "# GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + "# terms of the GNU General Public License as published by the Free Software " + NL + "# Foundation, either version 3 of the License, or (at your option) any later " + NL + "# version." + NL + "# " + NL + "# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + "# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR " + NL + "# A PARTICULAR PURPOSE.  See the GNU General Public License for more details." + NL + "" + NL + "# You should have received a copy of the GNU General Public License along with " + NL + "# this program.  If not, see http://www.gnu.org/licenses/." + NL + "#" + NL + "" + NL + "" + NL + "rm -f config.cache" + NL + "" + NL + "# Setup the libtool stuff" + NL + "if [ -e /usr/local/share/aclocal/libtool.m4 ]; then" + NL + "    /bin/cp /usr/local/share/aclocal/libtool.m4 aclocal.d/acinclude.m4" + NL + "elif [ -e /usr/share/aclocal/libtool.m4 ]; then" + NL + "    /bin/cp /usr/share/aclocal/libtool.m4 acinclude.m4" + NL + "fi" + NL + "libtoolize --force --automake" + NL + "" + NL + "# Search in expected locations for the OSSIE acincludes" + NL + "if [ -n ${OSSIEHOME} ] && [ -d ${OSSIEHOME}/share/aclocal/ossie ]; then" + NL + "        OSSIE_AC_INCLUDE=${OSSIEHOME}/share/aclocal/ossie" + NL + "else" + NL + "    echo \"Error: Cannot find the OSSIE aclocal files. This is not expected!\"" + NL + "fi" + NL + "" + NL + "if [ -n ${OSSIE_AC_INCLUDE} ]; then" + NL + "        aclocal -I ${OSSIE_AC_INCLUDE}" + NL + "else" + NL + "        aclocal" + NL + "fi" + NL + "" + NL + "autoconf" + NL + "automake --foreign --add-missing";

    /**
    * {@inheritDoc}
    */
    public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file 
 * distributed with this source distribution.
 * 
 * This file is part of GNUHAWK.
 * 
 * GNUHAWK is free software: you can redistribute it and/or modify is under the 
 * terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * 
 * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program.  If not, see http://www.gnu.org/licenses/.
 */

     // REDHAWK HEADER 
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
} 

// END GENERATED CODE