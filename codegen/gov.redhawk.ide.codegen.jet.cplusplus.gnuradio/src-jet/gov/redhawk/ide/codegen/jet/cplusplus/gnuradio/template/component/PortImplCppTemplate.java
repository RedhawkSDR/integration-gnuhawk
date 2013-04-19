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
import gov.redhawk.ide.codegen.jet.cplusplus.ports.PropertyChangeEventPortTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.PortTemplate;
import gov.redhawk.model.sca.util.ModelUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Date;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public class PortImplCppTemplate
{

  protected static String nl;
  public static synchronized PortImplCppTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PortImplCppTemplate result = new PortImplCppTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "" + NL + "/*******************************************************************************************" + NL + "" + NL + "    AUTO-GENERATED CODE. DO NOT MODIFY" + NL + "" + NL + " \tSource: ";
  protected final String TEXT_2 = NL + " \t";
  protected final String TEXT_3 = NL + " \t";
  protected final String TEXT_4 = NL + " \t";
  protected final String TEXT_5 = NL + NL + "*******************************************************************************************/" + NL + "" + NL + "#include \"";
  protected final String TEXT_6 = ".h\"" + NL;
  protected final String TEXT_7 = NL + NL + "bool CompareSRI(BULKIO::StreamSRI &SRI_1, BULKIO::StreamSRI &SRI_2){" + NL + "    if (SRI_1.hversion != SRI_2.hversion)" + NL + "      return false;" + NL + "    if (SRI_1.xstart != SRI_2.xstart)" + NL + "      return false;" + NL + "    if (SRI_1.xdelta != SRI_2.xdelta)" + NL + "      return false;" + NL + "    if (SRI_1.xunits != SRI_2.xunits)" + NL + "      return false;" + NL + "    if (SRI_1.subsize != SRI_2.subsize)" + NL + "      return false;" + NL + "    if (SRI_1.ystart != SRI_2.ystart)" + NL + "      return false;" + NL + "    if (SRI_1.ydelta != SRI_2.ydelta)" + NL + "      return false;" + NL + "    if (SRI_1.yunits != SRI_2.yunits)" + NL + "      return false;" + NL + "    if (SRI_1.mode != SRI_2.mode)" + NL + "      return false;" + NL + "    if (strcmp(SRI_1.streamID, SRI_2.streamID) != 0)" + NL + "      return false;" + NL + "    if (SRI_1.keywords.length() != SRI_2.keywords.length())" + NL + "      return false;" + NL + "    std::string action = \"eq\";" + NL + "    for (unsigned int i=0; i<SRI_1.keywords.length(); i++) {" + NL + "      if (strcmp(SRI_1.keywords[i].id, SRI_2.keywords[i].id)) {" + NL + "\treturn false;" + NL + "      }" + NL + "" + NL + "      if (!ossie::compare_anys(SRI_1.keywords[i].value, SRI_2.keywords[i].value, action)) {" + NL + "\treturn false;" + NL + "      }" + NL + "    }" + NL + "    return true;" + NL + "}" + NL;
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL;

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
    boolean includePropertyChange = false;
    Date date = new Date(System.currentTimeMillis());
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
    }
    for (Provides entry : provides) {
        providesReps.add(entry.getRepID());
    }

    stringBuffer.append(TEXT_1);
    stringBuffer.append(ModelUtil.getSpdFileName(softPkg));
    
	String[] output;
	IProduct product = Platform.getProduct();
	if (product != null) {
		output = product.getProperty("aboutText").split("\n");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(output[0]);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(output[1]);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(output[2]);
    
	}

    stringBuffer.append(TEXT_5);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_6);
     if ( provides.size() > 0 ) { 
    stringBuffer.append(TEXT_7);
     } 
    stringBuffer.append(TEXT_8);
    
    if (includePropertyChange) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(new PropertyChangeEventPortTemplate().generateClassImplementation(null, false, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
    }
    for (String intName : usesReps) {
        IScaPortCodegenTemplate gen = portMap.get(intName);
        portTempl.setPortRepId(intName);
        portTempl.setGenSupport(false);
        portTempl.setGenClassDef(false);
        portTempl.setGenClassImpl(true);
        if (gen != null) {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(gen.generateClassImplementation(intName, false, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        } else {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(new PortTemplate().generateClassImplementation(intName, false, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        }
    }
    for (String intName : providesReps) {
        IScaPortCodegenTemplate gen = portMap.get(intName);
        portTempl.setPortRepId(intName);
        portTempl.setGenSupport(false);
        portTempl.setGenClassDef(false);
        portTempl.setGenClassImpl(true);
        if (gen != null) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(gen.generateClassImplementation(intName, true, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        } else {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(new PortTemplate().generateClassImplementation(intName, true, softPkg, implSettings, portTempl, CodegenUtil.CPP));
    
        }
    }

    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE