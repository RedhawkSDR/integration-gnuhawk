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
package gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component;

import gov.redhawk.ide.RedhawkIdeActivator;
import gov.redhawk.ide.codegen.CodegenUtil;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.IPortTemplateDesc;
import gov.redhawk.ide.codegen.IScaPortCodegenTemplate;
import gov.redhawk.ide.codegen.PortRepToGeneratorMap;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.CplusplusJetGeneratorPlugin;
import gov.redhawk.ide.codegen.jet.cplusplus.ports.PropertyChangeEventPortTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.PortTemplate;
import gov.redhawk.ide.idl.IdlUtil;
import gov.redhawk.ide.idl.Interface;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public class PortImplHTemplate
{

  protected static String nl;
  public static synchronized PortImplHTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PortImplHTemplate result = new PortImplHTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t        ";
  protected final String TEXT_2 = NL + "   ";
  protected final String TEXT_3 = "_";
  protected final String TEXT_4 = "_In_i(\"";
  protected final String TEXT_5 = "\",&_sriListener )";
  protected final String TEXT_6 = NL + "   ";
  protected final String TEXT_7 = "_";
  protected final String TEXT_8 = "_In_i(\"";
  protected final String TEXT_9 = "\", this )";
  protected final String TEXT_10 = "_";
  protected final String TEXT_11 = "_Out_i(\"";
  protected final String TEXT_12 = "\", this)";
  protected final String TEXT_13 = NL + "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "" + NL + "#ifndef PORT_H" + NL + "#define PORT_H" + NL + "" + NL + "#include \"ossie/Port_impl.h\"" + NL + "#include \"ossie/debug.h\"" + NL + "#include <queue>" + NL + "#include <list>" + NL + "#include <boost/thread/condition_variable.hpp>" + NL + "#include <boost/thread/locks.hpp>" + NL + "" + NL + "class ";
  protected final String TEXT_14 = "_base;" + NL + "class ";
  protected final String TEXT_15 = "_i;" + NL + "" + NL + "#define CORBA_MAX_TRANSFER_BYTES omniORB::giopMaxMsgSize()" + NL + "" + NL + "typedef char                        Char;" + NL + "typedef int8_t                      Int8;" + NL + "typedef uint8_t                     UInt8;" + NL + "typedef int16_t                     Int16;" + NL + "typedef uint16_t                    UInt16;" + NL + "" + NL + "#if SIZEOF_LONG == 4" + NL + "typedef long                        Int32;" + NL + "typedef unsigned long               UInt32;" + NL + "#else" + NL + "typedef int32_t                     Int32;" + NL + "typedef uint32_t                    UInt32;" + NL + "#endif" + NL + "" + NL + "typedef int64_t                     Int64;" + NL + "typedef uint64_t                    UInt64;" + NL + "typedef float                       Float;" + NL + "typedef double                      Double;" + NL;
  protected final String TEXT_16 = NL + "#include \"ossie/CF/QueryablePort.h\"";
  protected final String TEXT_17 = NL;
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = NL;
  protected final String TEXT_20 = NL;
  protected final String TEXT_21 = NL + NL + "class queueSemaphore" + NL + "{" + NL + "    private:" + NL + "        unsigned int maxValue;" + NL + "        unsigned int currValue;" + NL + "        boost::mutex mutex;" + NL + "        boost::condition_variable condition;" + NL + "" + NL + "    public:" + NL + "        queueSemaphore(unsigned int initialMaxValue):mutex(),condition() {" + NL + "        \tmaxValue = initialMaxValue;" + NL + "        }" + NL + "        " + NL + "        void release() {" + NL + "          currValue=0;" + NL + "          condition.notify_all();" + NL + "        } " + NL + "" + NL + "        void setMaxValue(unsigned int newMaxValue) {" + NL + "            boost::unique_lock<boost::mutex> lock(mutex);" + NL + "            maxValue = newMaxValue;" + NL + "        }" + NL + "" + NL + "        unsigned int getMaxValue(void) {" + NL + "\t\t\tboost::unique_lock<boost::mutex> lock(mutex);" + NL + "\t\t\treturn maxValue;" + NL + "\t\t}" + NL + "" + NL + "        void setCurrValue(unsigned int newValue) {" + NL + "        \tboost::unique_lock<boost::mutex> lock(mutex);" + NL + "        \tif (newValue < maxValue) {" + NL + "        \t\tunsigned int oldValue = currValue;" + NL + "        \t\tcurrValue = newValue;" + NL + "" + NL + "        \t\tif (oldValue > newValue) {" + NL + "        \t\t\tcondition.notify_one();" + NL + "        \t\t}" + NL + "        \t}" + NL + "        }" + NL + "" + NL + "        void incr() {" + NL + "            boost::unique_lock<boost::mutex> lock(mutex);" + NL + "            while (currValue >= maxValue) {" + NL + "                condition.wait(lock);" + NL + "            }" + NL + "            ++currValue;" + NL + "        }" + NL + "" + NL + "        void decr() {" + NL + "            boost::unique_lock<boost::mutex> lock(mutex);" + NL + "            if (currValue > 0) {" + NL + "            \t--currValue;" + NL + "            }" + NL + "            condition.notify_one();" + NL + "        }" + NL + "};        " + NL;
  protected final String TEXT_22 = NL;
  protected final String TEXT_23 = NL;
  protected final String TEXT_24 = NL + "#endif";
  protected final String TEXT_25 = NL;

  /**
   * {@inheritDoc}
   */
  public String generate(Object argument) throws CoreException
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

     
    TemplateParameter templ = (TemplateParameter) argument;
    ImplementationSettings implSettings = templ.getImplSettings();
    Implementation impl = templ.getImpl();
    SoftPkg softPkg = (SoftPkg) impl.eContainer();
    String PREFIX = gov.redhawk.ide.codegen.util.CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
    EList<Uses> uses = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses();
    EList<Provides> provides = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getProvides();
    HashSet<String> usesReps = new HashSet<String>();
    HashSet<String> providesReps = new HashSet<String>();
    List<IPath> search_paths = Arrays.asList(RedhawkIdeActivator.getDefault().getDefaultIdlIncludePath());
    TemplateParameter portTempl = new TemplateParameter(impl, implSettings, search_paths);
    HashMap<String, IScaPortCodegenTemplate> portMap = new HashMap<String, IScaPortCodegenTemplate>();
    boolean includeQueryablePort = false;
    boolean includePropertyChange = false;
    for (PortRepToGeneratorMap p : implSettings.getPortGenerators()) {
        try {
            IPortTemplateDesc template = CodegenUtil.getPortTemplate(p.getGenerator(), null);
            if (template != null) {
                portMap.put(p.getRepId(), template.getTemplate());
            }
        } catch (CoreException e) {
            // TODO What to do here! Throw the exception and not generate anything?
        }
    }
    for (Uses entry : uses) {
        if (PropertyChangeEventPortTemplate.EVENTCHANNEL_REPID.equals(entry.getRepID()) 
                && PropertyChangeEventPortTemplate.EVENTCHANNEL_NAME.equals(entry.getUsesName())) {
            includePropertyChange = true;
            continue;
        }
        usesReps.add(entry.getRepID());
        if (!entry.getRepID().startsWith("IDL:BULKIO/")) {
            includeQueryablePort = true;
        }
    }
    for (Provides entry : provides) {
        providesReps.add(entry.getRepID());
        if (!entry.getRepID().startsWith("IDL:BULKIO/")) {
            includeQueryablePort = true;
        }
    }
    
    if (!templ.isGenClassDef() && !templ.isGenClassImpl() && !templ.isGenSupport()) {
        if (templ.isProvidesPort()) {
		    for (Provides pro : provides) {
		        String entry = pro.getRepID();
		        if (templ.getPortRepId().equals(entry) && pro.getProvidesName().equals(templ.getPortName())) {
			        Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true); 
			        if (intf == null) {
			            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
			        }
			         
			        if ( entry.contains("BULKIO") ) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(intf.getNameSpace());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_5);
    			        
			        }
     			    else {    

    stringBuffer.append(TEXT_6);
    stringBuffer.append(intf.getNameSpace());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_9);
    
                    }
                    break;
                }
		    }
		} else {
		    for (Uses use : uses) {
		        String entry = use.getRepID();
		        if (templ.getPortRepId().equals(entry) && use.getUsesName().equals(templ.getPortName())) {
			        Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true); 
			        if (intf == null) {
			            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
			        }

    stringBuffer.append(intf.getNameSpace());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_12);
    
                    break;
	            }
	        }
	    }
    } else if (templ.isGenClassDef()) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_15);
    
    if (includeQueryablePort) {

    stringBuffer.append(TEXT_16);
    
    }
    List<String> neededHeaders = new ArrayList<String>();
    for (Uses interim : uses) {
        IScaPortCodegenTemplate gen = portMap.get(interim.getInterface().getName());
        portTempl.setPortRepId(interim.getRepID());
        portTempl.setPortName(interim.getName());
        portTempl.setGenSupport(true);
        portTempl.setGenClassDef(false);
        portTempl.setGenClassImpl(false);
        String hdrs = null;
        if (gen != null) {
            hdrs = gen.generateClassSupport(interim.getRepID(), false, softPkg, implSettings, portTempl, CodegenUtil.CPP);
        } else {
            hdrs = new PortTemplate().generateClassSupport(interim.getRepID(), false, softPkg, implSettings, portTempl, CodegenUtil.CPP);
        }
        if ((hdrs != null) && (hdrs.trim().length() != 0)) {
            String[] hdrSplit = hdrs.split("\n");
            for (String line : hdrSplit) {
                neededHeaders.add(line);
            }
        }
    }
    for (Provides interim : provides) {
        IScaPortCodegenTemplate gen = portMap.get(interim.getInterface().getName());
        portTempl.setPortRepId(interim.getRepID());
        portTempl.setPortName(interim.getName());
        portTempl.setGenSupport(true);
        portTempl.setGenClassDef(false);
        portTempl.setGenClassImpl(false);
        String hdrs = null;
        if (gen != null) {
            hdrs = gen.generateClassSupport(interim.getRepID(), true, softPkg, implSettings, portTempl, CodegenUtil.CPP);
        } else {
            hdrs = new PortTemplate().generateClassSupport(interim.getRepID(), true, softPkg, implSettings, portTempl, CodegenUtil.CPP);
        }
        if ((hdrs != null) && (hdrs.trim().length() != 0)) {
            String[] hdrSplit = hdrs.split("\n");
            for (String line : hdrSplit) {
                if (!neededHeaders.contains(line)) {
                    neededHeaders.add(line);
                }
            }
        }
    }
    for (String hdr : neededHeaders) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(hdr);
    
    }

    
    if (includePropertyChange) {

    stringBuffer.append(TEXT_18);
    stringBuffer.append(new PropertyChangeEventPortTemplate().generateClassDefinition(null, false, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
    }
    for (String intName : usesReps) {
        IScaPortCodegenTemplate gen = portMap.get(intName);
        portTempl.setPortRepId(intName);
        portTempl.setGenSupport(false);
        portTempl.setGenClassDef(true);
        portTempl.setGenClassImpl(false);
        if (gen != null) {

    stringBuffer.append(TEXT_19);
    stringBuffer.append(gen.generateClassDefinition(intName, false, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        } else {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(new PortTemplate().generateClassDefinition(intName, false, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        }
    }
    boolean providesBULKIO = false;
    for (Provides entry : provides) {
        providesReps.add(entry.getRepID());
        if (entry.getRepID().startsWith("IDL:BULKIO/")) {
            providesBULKIO = true;
        }
    }
    if (providesBULKIO) {

    stringBuffer.append(TEXT_21);
    
    }
    for (String intName : providesReps) {
        IScaPortCodegenTemplate gen = portMap.get(intName);
        portTempl.setPortRepId(intName);
        portTempl.setGenSupport(false);
        portTempl.setGenClassDef(true);
        portTempl.setGenClassImpl(false);
        if (gen != null) {

    stringBuffer.append(TEXT_22);
    stringBuffer.append(gen.generateClassDefinition(intName, true, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        } else {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(new PortTemplate().generateClassDefinition(intName, true, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        }
    }

    stringBuffer.append(TEXT_24);
    
    } // end if genClassDef

    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE