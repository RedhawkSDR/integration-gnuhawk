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

import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.model.sca.util.ModelUtil;
import gov.redhawk.ide.sdr.ui.SdrUiPlugin;
import gov.redhawk.ide.sdr.ComponentsContainer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.Dependency;
import mil.jpeojtrs.sca.spd.SoftPkgRef;
import mil.jpeojtrs.sca.spd.SoftPkg;
import org.eclipse.emf.common.util.EList;
import org.eclipse.core.resources.IResource;

/**
 * @generated
 */
public class ConfigureAcTemplate
{

  protected static String nl;
  public static synchronized ConfigureAcTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ConfigureAcTemplate result = new ConfigureAcTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#" + NL + "# This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + "# distributed with this source distribution." + NL + "# " + NL + "# This file is part of GNUHAWK." + NL + "# " + NL + "# GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + "# terms of the GNU General Public License as published by the Free Software " + NL + "# Foundation, either version 3 of the License, or (at your option) any later " + NL + "# version." + NL + "# " + NL + "# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + "# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR " + NL + "# A PARTICULAR PURPOSE.  See the GNU General Public License for more details." + NL + "" + NL + "# You should have received a copy of the GNU General Public License along with " + NL + "# this program.  If not, see http://www.gnu.org/licenses/." + NL + "#" + NL + "AC_INIT(";
  protected final String TEXT_2 = ", ";
  protected final String TEXT_3 = ")" + NL + "AM_INIT_AUTOMAKE(nostdinc)" + NL + "" + NL + "AC_PROG_CC" + NL + "AC_PROG_CXX" + NL + "AC_PROG_INSTALL" + NL + "" + NL + "AC_CORBA_ORB" + NL + "OSSIE_CHECK_OSSIE" + NL + "OSSIE_SDRROOT_AS_PREFIX" + NL + "" + NL + "# Dependencies" + NL + "export PKG_CONFIG_PATH=\"${SDRROOT}/dom/deps/gnuhawk/lib64/pkgconfig:${SDRROOT}/dom/deps/gnuhawk/lib/pkgconfig:";
  protected final String TEXT_4 = "$PKG_CONFIG_PATH:/usr/local/lib/pkgconfig\"" + NL + "PKG_CHECK_MODULES([PROJECTDEPS], [ossie >= 1.10 omniORB4 >= 4.0.0 gnuhawk >= 0.1.0 ])";
  protected final String TEXT_5 = NL + "PKG_CHECK_MODULES([INTERFACEDEPS], [";
  protected final String TEXT_6 = "Interfaces";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = "Interfaces";
  protected final String TEXT_9 = "])";
  protected final String TEXT_10 = NL + "OSSIE_ENABLE_LOG4CXX" + NL + "AX_BOOST_BASE([1.41])" + NL + "AX_BOOST_THREAD";
  protected final String TEXT_11 = NL + "CHECK_VECTOR_IMPL";
  protected final String TEXT_12 = NL + NL + "AC_CONFIG_FILES(Makefile)" + NL + "AC_OUTPUT";
  protected final String TEXT_13 = NL;

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
     
    TemplateParameter templ = (TemplateParameter) argument;
    ImplementationSettings implSettings = templ.getImplSettings();
    IResource resource = ModelUtil.getResource(implSettings);
    Implementation impl = templ.getImpl();
    SoftPkg softpkg = (SoftPkg) impl.eContainer();
    
    // Determine softpkg version, set to 1.0.0 if none exists 
    String version = "1.0.0";
    String softpkgVersion = null;
    if (softpkg != null) {
        softpkgVersion = softpkg.getVersion();
    }  
    if (softpkgVersion != null && !softpkgVersion.equals("")) {
        version = softpkgVersion;
    }
    
    EList<Dependency>deps = impl.getDependency();
    String additionalPkg = "";
	ComponentsContainer installedStuff = SdrUiPlugin.getDefault().getTargetSdrRoot().getComponentsContainer();
    for (Dependency dep : deps) {
    	SoftPkgRef spr = dep.getSoftPkgRef();
    	if (spr == null) {
    		continue;
    	}
    	String filename = spr.getLocalFile().getName();
    	for (SoftPkg item : installedStuff.getComponents()) {
    		String tmpout = item.eResource().getURI().toString();
    		String[] split_tmp = tmpout.split("\\?");
    		if (split_tmp.length != 2)
    			continue;
    		String softpkg_file = split_tmp[0].substring(4); // remove "sca:"
   			if (softpkg_file.equals(filename)) {
				additionalPkg += "${SDRROOT}/dom" + filename.substring(0,filename.lastIndexOf("/")) + "/" + item.getImplementation().get(0).getCode().getLocalFile().getName() + "/pkgconfig:";
   			}
    	}
    }
    
    // Determine what interfaces we require
    Map<String, String> ifaceNameAndVer = new HashMap<String, String>();
    List<Interface> interfaces = softpkg.getDescriptor().getComponent().getInterfaces().getInterface();
    Pattern idlPattern = Pattern.compile("^IDL:(\\w+)/");
    for (Interface iface : interfaces) {
        Matcher match = idlPattern.matcher(iface.getRepid());
        if (match.find()) {
            String interfaceNamespace = match.group(1);
            if ("BULKIO".equals(interfaceNamespace)) {
                ifaceNameAndVer.put("bulkio", " >= 1.10");
            } else if ("REDHAWK".equals(interfaceNamespace)) {
                ifaceNameAndVer.put("redhawk", " >= 1.2.0");
            } else if (! "CF".equals(interfaceNamespace)) {
                ifaceNameAndVer.put(interfaceNamespace.toLowerCase(), "");
            }
        }
    }

    stringBuffer.append(TEXT_1);
    stringBuffer.append(resource.getProject().getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(version);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(additionalPkg);
    stringBuffer.append(TEXT_4);
    
    boolean first = true;
    for (String interfaceNamespace : ifaceNameAndVer.keySet()) {
	    if (interfaceNamespace.equals("extendedevent")) {
	    	continue;
	    }
	    if (first) {
	        first = false;

    stringBuffer.append(TEXT_5);
    stringBuffer.append(interfaceNamespace);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(ifaceNameAndVer.get(interfaceNamespace));
    
        } else {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(interfaceNamespace);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(ifaceNameAndVer.get(interfaceNamespace));
    
        }
    }
    if (!first) {

    stringBuffer.append(TEXT_9);
    
    }

    stringBuffer.append(TEXT_10);
    
    if (ifaceNameAndVer.containsKey("bulkio")) {

    stringBuffer.append(TEXT_11);
    
    }

    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    return stringBuffer.toString();
  }
} 

// END GENERATED CODE
