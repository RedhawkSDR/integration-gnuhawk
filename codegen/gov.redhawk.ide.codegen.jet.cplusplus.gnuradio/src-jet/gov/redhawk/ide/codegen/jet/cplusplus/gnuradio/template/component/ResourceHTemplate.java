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

import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.ImplementationSettings;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.scd.Uses;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public class ResourceHTemplate
{

  protected static String nl;
  public static synchronized ResourceHTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceHTemplate result = new ResourceHTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "" + NL + "#ifndef ";
  protected final String TEXT_2 = NL + "#define ";
  protected final String TEXT_3 = NL + NL + "#include \"";
  protected final String TEXT_4 = ".h\"" + NL + "" + NL + "class ";
  protected final String TEXT_5 = " : public ";
  protected final String TEXT_6 = "_base" + NL + "{" + NL + "   public:";
  protected final String TEXT_7 = NL + "      ";
  protected final String TEXT_8 = "(const char *uuid, const char *label);" + NL + "      ~";
  protected final String TEXT_9 = "();" + NL + "" + NL + "    //" + NL + "    // createBlock" + NL + "    //" + NL + "    // Create the actual GNU Radio Block to that will perform the work method. The resulting" + NL + "    // block object is assigned to gr_stpr" + NL + "    //" + NL + "    // Add property change callbacks for getter/setter methods" + NL + "    //" + NL + "    //" + NL + "    void createBlock();" + NL;
  protected final String TEXT_10 = " " + NL + "    //" + NL + "    // createOutputSRI" + NL + "    //" + NL + "    // Called by setupIOMappings when an output mapping is defined. For each output mapping" + NL + "    // defined, a call to createOutputSRI will be issued with the associated output index." + NL + "    // This default SRI and StreamID will be saved to the mapping and pushed down stream via pushSRI." + NL + "    // The subclass is responsible for overriding behavior of this method. The index provide matches" + NL + "    // the stream index number that will be use by the GR Block object" + NL + "    //" + NL + "    // @param idx : output stream index number to associate the returned SRI object with" + NL + "    // @return sri : default SRI object passed down stream over a RedHawk port" + NL + "    //      " + NL + "    BULKIO::StreamSRI  createOutputSRI( int32_t idx );" + NL + "      ";
  protected final String TEXT_11 = NL + "};" + NL + "" + NL + "#endif";
  protected final String TEXT_12 = NL;

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


    
    TemplateParameter templ = (TemplateParameter) argument;
    Implementation impl = templ.getImpl();
    SoftPkg softPkg = (SoftPkg) impl.eContainer();
    ImplementationSettings implSettings = templ.getImplSettings();
    String PREFIX = gov.redhawk.ide.codegen.util.CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
    EList<Uses> uses = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses();

    // check if we have an output port for data flow
    String outputType = "";
    for (Uses entry : uses) {
    	String intName = entry.getRepID();
        if (intName.contains("BULKIO")) {
	    outputType = "BULKIO";
         }
    }
    String className = PREFIX + "_i";
    String baseClass = PREFIX + "_base";    
    String includeGuard = PREFIX.toUpperCase() + "_IMPL_H";

    stringBuffer.append(TEXT_1);
    stringBuffer.append(includeGuard);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(includeGuard);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(baseClass);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_9);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_10);
     } 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
} 

// END GENERATED CODE