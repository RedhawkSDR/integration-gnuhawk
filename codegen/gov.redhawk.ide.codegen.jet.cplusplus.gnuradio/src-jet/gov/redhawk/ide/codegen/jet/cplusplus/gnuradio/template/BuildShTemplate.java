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
public class BuildShTemplate
{

  protected static String nl;
  public static synchronized BuildShTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    BuildShTemplate result = new BuildShTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#" + NL + "# This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + "# distributed with this source distribution." + NL + "# " + NL + "# This file is part of GNUHAWK." + NL + "# " + NL + "# GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + "# terms of the GNU General Public License as published by the Free Software " + NL + "# Foundation, either version 3 of the License, or (at your option) any later " + NL + "# version." + NL + "# " + NL + "# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + "# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR " + NL + "# A PARTICULAR PURPOSE.  See the GNU General Public License for more details." + NL + "" + NL + "# You should have received a copy of the GNU General Public License along with " + NL + "# this program.  If not, see http://www.gnu.org/licenses/." + NL + "#" + NL + "" + NL + "#!/bin/sh" + NL + "configure='configure'" + NL + "makefile_in='Makefile.in'" + NL + "config_ac='configure.ac'" + NL + "make_am='Makefile.am'" + NL + "makefile='Makefile'" + NL + "" + NL + "if [ \"$1\" == 'clean' ]; then" + NL + "  make clean" + NL + "else" + NL + "  # Checks if build is newer than makefile (based on modification time)" + NL + "  if [[ ! -e $configure || ! -e $makefile_in || $config_ac -nt $makefile || $make_am -nt $makefile ]]; then" + NL + "    ./reconf" + NL + "    ./configure" + NL + "  fi" + NL + "  make -j" + NL + "  exit 0 " + NL + "fi";
  protected final String TEXT_2 = NL + " ";

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
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
} 

// END GENERATED CODE