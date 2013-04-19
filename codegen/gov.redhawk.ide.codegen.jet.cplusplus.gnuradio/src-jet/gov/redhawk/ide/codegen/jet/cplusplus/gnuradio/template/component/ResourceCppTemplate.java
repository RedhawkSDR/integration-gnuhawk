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
import mil.jpeojtrs.sca.scd.Provides;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public class ResourceCppTemplate
{

  protected static String nl;
  public static synchronized ResourceCppTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceCppTemplate result = new ResourceCppTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "" + NL + "#include \"";
  protected final String TEXT_2 = ".h\"" + NL + "" + NL + "" + NL + "//" + NL + "//   This file contains the bindings to the hosted block" + NL + "//" + NL + "" + NL + "//" + NL + "//    For GNU Radio blocks with getter and setter methods, the associated property can be bound" + NL + "//    directly to the block's getter and/or setter." + NL + "//" + NL + "//    void component_class_i::createBlock()" + NL + "//    {" + NL + "//        ..." + NL + "//        this->registerGetter(\"property_x\", &gnuradio_class::getter_for_x);" + NL + "//        this->registerSetter(\"property_y\", &gnuradio_class::setter_for_y);" + NL + "//        this->registerGetterSetter(\"property_z\", &gnuradio_class::getter_for_z," + NL + "//                                   &gnuradio_class::setter_for_z);" + NL + "//        ..." + NL + "//    }" + NL + "" + NL + "//" + NL + "//    To bind to a setter via the REDHAWK component class" + NL + "//" + NL + "//    void component_class_i::setterForPropertyX()" + NL + "//    {" + NL + "//       if ( validGRBlock() ) {" + NL + "//           gr_sptr->set_property_x(this->property_x);" + NL + "//        }" + NL + "//    }" + NL + "//" + NL + "//    void component_class_i::createBlock()" + NL + "//    {" + NL + "//        ..." + NL + "//        this->setPropertyChangeListener(\"property_x\", this, &component_class_i::setterForPropertyX);" + NL + "//        ..." + NL + "//    }" + NL + "" + NL + "//" + NL + "//    To bind to a getter via the REDHAWK component class" + NL + "//" + NL + "//    double component_class_i::getterForPropertyX()" + NL + "//    {" + NL + "//       if ( validGRBlock() ) {" + NL + "//         return this->gr_sptr->get_property_x();" + NL + "//       }" + NL + "//       return 0.0;" + NL + "//    }" + NL + "//    void component_class_i::createBlock()" + NL + "//    {" + NL + "//        ..." + NL + "//        this->registerGetValue(\"property_x\", this, &component_class_i::getterForPropertyX);" + NL + "//        ..." + NL + "//    }" + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = "::";
  protected final String TEXT_6 = "(const char *uuid, const char *label) :";
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = "(uuid, label)" + NL + "{" + NL + "}" + NL;
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = "::~";
  protected final String TEXT_11 = "()" + NL + "{" + NL + "}" + NL + "" + NL + "" + NL + "// createBlock" + NL + "//" + NL + "// Create the actual GNU Radio Block to that will perform the work method. The resulting" + NL + "// block object is assigned to gr_stpr" + NL + "//" + NL + "// Add property change callbacks for getter/setter methods" + NL + "//" + NL + "void ";
  protected final String TEXT_12 = "::createBlock()" + NL + "{" + NL + "  //" + NL + "  // gr_sptr = xxx_make_xxx( args );" + NL + "  //" + NL + "#error Insert the assignment/constructor of the GNRadio Block pointer here, then delete this error preprocessor directive. It should be of the form \"gr_sptr = xxx_make_xxx(args)\"" + NL + "" + NL + "  // " + NL + "  // Use setThrottle method to enable the throttling of consumption/production of data by the" + NL + "  // service function. The affect of the throttle will try to pause the execution of the " + NL + "  // service function for a specified duration.  This duration is calculated using the getTargetDuration() method" + NL + "  // and the actual processing time to perform the work method." + NL + "  //" + NL + "  // This is turned ON by default for \"output\" only components" + NL + "  //" + NL + "  // setThrottle( bool onoff );" + NL + "" + NL + "  // " + NL + "  // Use maintainTimeStamp to enable proper data flow of timestamp with input and output data. " + NL + "  // if turned on (true)" + NL + "  //  The timestamp from the input source will be used and maintained based on the output rate and" + NL + "  //  the number of samples produced" + NL + "  // if turned off" + NL + "  //   then the timestamp from the input source is passed through if available or the time of day" + NL + "  // " + NL + "  // maintainTimestamp( bool onoff );" + NL;
  protected final String TEXT_13 = NL + "   setupIOMappings();";
  protected final String TEXT_14 = NL + NL + "} " + NL + NL;
  protected final String TEXT_15 = NL + "//" + NL + "// createOutputSRI" + NL + "//" + NL + "// For each output mapping defined, a call to createOutputSRI will be issued with the associated output index." + NL + "// This default SRI and StreamID will be saved to the mapping and pushed down stream via pushSRI." + NL + "//" + NL + "// @param idx : output stream index number to associate the returned SRI object with" + NL + "// @return sri : default SRI object passed down stream over a RedHawk port" + NL + "//      " + NL + "BULKIO::StreamSRI ";
  protected final String TEXT_16 = "::createOutputSRI( int32_t idx)" + NL + "{" + NL + "  //" + NL + "  // idx is the stream number that you are returning an SRI context for" + NL + "  //" + NL + "" + NL + "  BULKIO::StreamSRI sri = BULKIO::StreamSRI();" + NL + "  sri.hversion = 1;" + NL + "  sri.xstart = 0.0;" + NL + "  sri.xdelta = 1;" + NL + "  sri.xunits = BULKIO::UNITS_TIME;" + NL + "  sri.subsize = 0;" + NL + "  sri.ystart = 0.0;" + NL + "  sri.ydelta = 0.0;" + NL + "  sri.yunits = BULKIO::UNITS_NONE;" + NL + "  sri.mode = 0;" + NL + "  std::ostringstream t;" + NL + "  t << naming_service_name.c_str() << \"_\" << idx;" + NL + "  std::string sid = t.str();" + NL + "  sri.streamID = CORBA::string_dup(sid.c_str());" + NL + "  " + NL + "  return sri;" + NL + " " + NL + "}";
  protected final String TEXT_17 = " " + NL + NL;
  protected final String TEXT_18 = NL;

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
    EList<Provides> provides = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getProvides();

    // check if we have an output port for data flow
    String outputType = "";
    for (Uses entry : uses) {
    	String intName = entry.getRepID();
        if (intName.contains("BULKIO")) {
	    outputType = "BULKIO";
         }
    }

    String inputType = "";
    for (Provides entry : provides) {
    	String intName = entry.getRepID();
        if (intName.contains("BULKIO")) {
	    inputType = "BULKIO";
         }
    }

    String className = PREFIX + "_i";
    String baseClass = PREFIX + "_base";

    stringBuffer.append(TEXT_1);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_2);
     
//
//    To redirect incoming messages to the handler
//    This assumes that an input (provides) port exists of type ExtendedEvent/MessageEvent
//    with the name simple_in. The name can be anything - it just needs to match the argument
//
//    void component_class_i::createBlock()
//    {
//        ...
//        gr_sptr->setInboundMessageDispatch(this->simple_in);
//        ...
//    }

//
//    To redirect outgoing messages to out a particular output message
//    This assumes that an output (uses) port exists of type ExtendedEvent/MessageEvent
//    with the name simple_out. The name can be anything - it just needs to match the argument
//
//    void component_class_i::createBlock()
//    {
//        ...
//        simple_sptr = gr_basic_block_sptr(new gr_basic_block());
//        simple_sptr->setMessageDispatch(this->simple_out);
//        gr_sptr->set_simple_ref(simple_sptr);
//        ...
//    }
//
//    private:
//        gr_basic_block_sptr simple_sptr;
//

    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(baseClass);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_12);
     if ( !outputType.equals("") || !inputType.equals("") ) { 
    stringBuffer.append(TEXT_13);
     }  
    stringBuffer.append(TEXT_14);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_16);
     } 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
} 

// END GENERATED CODE