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

import gov.redhawk.ide.codegen.CodegenUtil;
import gov.redhawk.ide.codegen.IPortTemplateDesc;
import gov.redhawk.ide.codegen.IScaPortCodegenTemplate;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.PortRepToGeneratorMap;
import gov.redhawk.ide.codegen.Property;
import gov.redhawk.ide.codegen.cplusplus.CppHelper;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.CplusplusJetGeneratorPlugin;
import gov.redhawk.ide.codegen.jet.cplusplus.CppProperties;
import gov.redhawk.ide.codegen.jet.cplusplus.ports.PropertyChangeEventPortTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.ports.MessagingPortTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.PortTemplate;
import gov.redhawk.model.sca.util.ModelUtil;
import gov.redhawk.ide.idl.IdlUtil;
import gov.redhawk.ide.idl.Interface;
import gov.redhawk.ide.idl.Operation;
import gov.redhawk.ide.RedhawkIdeActivator;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public class ResourceBaseCppTemplate
{

  protected static String nl;
  public static synchronized ResourceBaseCppTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceBaseCppTemplate result = new ResourceBaseCppTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "" + NL + "#include \"";
  protected final String TEXT_2 = "_base.h\"" + NL + "" + NL + "/*******************************************************************************************" + NL + "" + NL + "    AUTO-GENERATED CODE. DO NOT MODIFY" + NL + "    " + NL + " \tSource: ";
  protected final String TEXT_3 = NL + " \t";
  protected final String TEXT_4 = NL + " \t";
  protected final String TEXT_5 = NL + " \t";
  protected final String TEXT_6 = NL + NL + "*******************************************************************************************/" + NL + "" + NL + "//" + NL + "//  Allow for logging " + NL + "// " + NL + "PREPARE_LOGGING(";
  protected final String TEXT_7 = "_base);" + NL + "" + NL + "" + NL + "inline static unsigned int" + NL + "round_up (unsigned int n, unsigned int multiple)" + NL + "{" + NL + "  return ((n + multiple - 1) / multiple) * multiple;" + NL + "}" + NL + "" + NL + "inline static unsigned int" + NL + "round_down (unsigned int n, unsigned int multiple)" + NL + "{" + NL + "  return (n / multiple) * multiple;" + NL + "}" + NL + "" + NL + "" + NL + "/******************************************************************************************" + NL + "" + NL + "    The following class functions are for the base class for the component class. To" + NL + "    customize any of these functions, do not modify them here. Instead, overload them" + NL + "    on the child class" + NL + "" + NL + "******************************************************************************************/" + NL;
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = "_base::";
  protected final String TEXT_11 = "_base(const char *uuid, const char *label) :" + NL + " GnuHawkBlock(uuid, label), " + NL + " serviceThread(0), " + NL + " noutput_items(0),";
  protected final String TEXT_12 = NL + " _sriListener(*this),";
  protected final String TEXT_13 = " " + NL + " _maintainTimeStamp(false)," + NL + " _throttle(false)" + NL + "{" + NL + "    construct();" + NL + "}";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = "_base::";
  protected final String TEXT_16 = "_base(char *devMgr_ior, char *id, char *lbl, char *sftwrPrfl) :";
  protected final String TEXT_17 = NL + "          ";
  protected final String TEXT_18 = "Device_impl (devMgr_ior, id, lbl, sftwrPrfl) ";
  protected final String TEXT_19 = ", AggregateDevice_impl ()";
  protected final String TEXT_20 = ", serviceThread(0){" + NL + "    construct();" + NL + "}" + NL;
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = "_base::";
  protected final String TEXT_23 = "_base(char *devMgr_ior, char *id, char *lbl, char *sftwrPrfl, char *compDev) :";
  protected final String TEXT_24 = NL + "          ";
  protected final String TEXT_25 = "Device_impl (devMgr_ior, id, lbl, sftwrPrfl, compDev) ";
  protected final String TEXT_26 = ", AggregateDevice_impl ()";
  protected final String TEXT_27 = ", serviceThread(0){" + NL + "    construct();" + NL + "}" + NL;
  protected final String TEXT_28 = NL;
  protected final String TEXT_29 = "_base::";
  protected final String TEXT_30 = "_base(char *devMgr_ior, char *id, char *lbl, char *sftwrPrfl, CF::Properties capacities) :";
  protected final String TEXT_31 = NL + "          ";
  protected final String TEXT_32 = "Device_impl (devMgr_ior, id, lbl, sftwrPrfl) ";
  protected final String TEXT_33 = ", AggregateDevice_impl ()";
  protected final String TEXT_34 = ", serviceThread(0){" + NL + "    construct();" + NL + "}" + NL;
  protected final String TEXT_35 = NL;
  protected final String TEXT_36 = "_base::";
  protected final String TEXT_37 = "_base(char *devMgr_ior, char *id, char *lbl, char *sftwrPrfl, CF::Properties capacities, char *compDev) :";
  protected final String TEXT_38 = NL + "          ";
  protected final String TEXT_39 = "Device_impl (devMgr_ior, id, lbl, sftwrPrfl, compDev) ";
  protected final String TEXT_40 = ", AggregateDevice_impl ()";
  protected final String TEXT_41 = ", serviceThread(0)" + NL + "{" + NL + "    construct();" + NL + "}";
  protected final String TEXT_42 = NL + NL + "void ";
  protected final String TEXT_43 = "_base::construct()" + NL + "{" + NL + "    Resource_impl::_started = false;" + NL + "    loadProperties();" + NL + "    serviceThread = 0;";
  protected final String TEXT_44 = NL + "    sentEOS = false;";
  protected final String TEXT_45 = "   ";
  protected final String TEXT_46 = NL + "    inputPortOrder.resize(0);;";
  protected final String TEXT_47 = NL + "    outputPortOrder.resize(0);";
  protected final String TEXT_48 = NL;
  protected final String TEXT_49 = NL + "   setThrottle(true);";
  protected final String TEXT_50 = NL + "    " + NL + "    PortableServer::ObjectId_var oid;";
  protected final String TEXT_51 = NL + "    ";
  protected final String TEXT_52 = " = new ";
  protected final String TEXT_53 = ";" + NL + "    oid = ossie::corba::RootPOA()->activate_object(";
  protected final String TEXT_54 = ");";
  protected final String TEXT_55 = NL + "    ";
  protected final String TEXT_56 = " = new ";
  protected final String TEXT_57 = ";" + NL + "    oid = ossie::corba::RootPOA()->activate_object(";
  protected final String TEXT_58 = ");";
  protected final String TEXT_59 = NL + "    ";
  protected final String TEXT_60 = "->registerProperty(this->_identifier, this->naming_service_name, this->getPropertyFromId(\"";
  protected final String TEXT_61 = "\"));";
  protected final String TEXT_62 = NL + "    this->registerPropertyChangePort(";
  protected final String TEXT_63 = ");";
  protected final String TEXT_64 = NL;
  protected final String TEXT_65 = NL + "    registerInPort(";
  protected final String TEXT_66 = ");" + NL + "    inputPortOrder.push_back(\"";
  protected final String TEXT_67 = "\");";
  protected final String TEXT_68 = NL + "    registerOutPort(";
  protected final String TEXT_69 = ", ";
  protected final String TEXT_70 = "->_this());" + NL + "    outputPortOrder.push_back(\"";
  protected final String TEXT_71 = "\");" + NL + "    ";
  protected final String TEXT_72 = NL + "}" + NL + "" + NL + "/*******************************************************************************************" + NL + "    Framework-level functions" + NL + "    These functions are generally called by the framework to perform housekeeping." + NL + "*******************************************************************************************/" + NL + "void ";
  protected final String TEXT_73 = "_base::initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException)" + NL + "{" + NL + "}" + NL + "" + NL + "void ";
  protected final String TEXT_74 = "_base::start() throw (CORBA::SystemException, CF::Resource::StartError)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(serviceThreadLock);" + NL + "    if (serviceThread == 0) {";
  protected final String TEXT_75 = NL + "        if ( ";
  protected final String TEXT_76 = " ) ";
  protected final String TEXT_77 = "->unblock();";
  protected final String TEXT_78 = NL + "       \tserviceThread = service_thread( this, 0.1);" + NL + "        serviceThread->start();" + NL + "    }" + NL + "    " + NL + "    if (!Resource_impl::started()) {" + NL + "    \tResource_impl::start();" + NL + "    }" + NL + "}" + NL + "" + NL + "void ";
  protected final String TEXT_79 = "_base::stop() throw (CORBA::SystemException, CF::Resource::StopError)" + NL + "{";
  protected final String TEXT_80 = NL + "    if ( ";
  protected final String TEXT_81 = " ) ";
  protected final String TEXT_82 = "->block();";
  protected final String TEXT_83 = NL;
  protected final String TEXT_84 = " " + NL + "    {" + NL + "      boost::mutex::scoped_lock lock(_sriMutex);" + NL + "      _sriQueue.clear();" + NL + "    }";
  protected final String TEXT_85 = NL + NL + "    // release the child thread (if it exists)" + NL + "    if (serviceThread != 0) {" + NL + "      {" + NL + "        boost::mutex::scoped_lock lock(serviceThreadLock);" + NL + "        LOG_TRACE( ";
  protected final String TEXT_86 = "_base, \"Stopping Service Function\" );" + NL + "        serviceThread->stop();" + NL + "      }" + NL + "" + NL + "      if ( !serviceThread->release()) {" + NL + "         throw CF::Resource::StopError(CF::CF_NOTSET, \"Processing thread did not die\");" + NL + "      }" + NL + "" + NL + "      boost::mutex::scoped_lock lock(serviceThreadLock);" + NL + "      if ( serviceThread ) {" + NL + "        delete serviceThread;" + NL + "      }" + NL + "    }" + NL + "    serviceThread = 0;" + NL + "" + NL + "    if (Resource_impl::started()) {" + NL + "        Resource_impl::stop();" + NL + "    }" + NL + "    " + NL + "    LOG_TRACE( ";
  protected final String TEXT_87 = "_base, \"COMPLETED STOP REQUEST\" );" + NL + "}" + NL;
  protected final String TEXT_88 = NL + "CORBA::Object_ptr ";
  protected final String TEXT_89 = "_base::getPort(const char* _id) throw (CORBA::SystemException, CF::PortSupplier::UnknownPort)" + NL + "{" + NL + "" + NL + "    std::map<std::string, Port_Provides_base_impl *>::iterator p_in = inPorts.find(std::string(_id));" + NL + "    if (p_in != inPorts.end()) {" + NL;
  protected final String TEXT_90 = NL + "        if (!strcmp(_id,\"";
  protected final String TEXT_91 = "\")) {" + NL + "            MessageConsumerPort *ptr = dynamic_cast<MessageConsumerPort *>(p_in->second);" + NL + "            if (ptr) {" + NL + "                return ";
  protected final String TEXT_92 = "::";
  protected final String TEXT_93 = "::_duplicate(ptr->_this());" + NL + "            }" + NL + "        }";
  protected final String TEXT_94 = NL + "        if (!strcmp(_id,\"";
  protected final String TEXT_95 = "\")) {";
  protected final String TEXT_96 = NL + "            ";
  protected final String TEXT_97 = "_";
  protected final String TEXT_98 = "_In_i *ptr = dynamic_cast<";
  protected final String TEXT_99 = "_";
  protected final String TEXT_100 = "_In_i *>(p_in->second);" + NL + "            if (ptr) {" + NL + "                return ";
  protected final String TEXT_101 = "::";
  protected final String TEXT_102 = "::_duplicate(ptr->_this());" + NL + "            }" + NL + "        }";
  protected final String TEXT_103 = NL + "    }" + NL + "" + NL + "    std::map<std::string, CF::Port_var>::iterator p_out = outPorts_var.find(std::string(_id));" + NL + "    if (p_out != outPorts_var.end()) {" + NL + "        return CF::Port::_duplicate(p_out->second);" + NL + "    }" + NL + "" + NL + "    throw (CF::PortSupplier::UnknownPort());" + NL + "}";
  protected final String TEXT_104 = NL + NL + "void ";
  protected final String TEXT_105 = "_base::releaseObject() throw (CORBA::SystemException, CF::LifeCycle::ReleaseError)" + NL + "{" + NL + "    // This function clears the component running condition so main shuts down everything" + NL + "    try {" + NL + "        stop();" + NL + "    } catch (CF::Resource::StopError& ex) {" + NL + "        // TODO - this should probably be logged instead of ignored" + NL + "    }" + NL + "" + NL + "    // deactivate ports" + NL + "    releaseInPorts();" + NL + "    releaseOutPorts();" + NL;
  protected final String TEXT_106 = NL + "    delete(";
  protected final String TEXT_107 = ");";
  protected final String TEXT_108 = NL + "    delete(";
  protected final String TEXT_109 = ");";
  protected final String TEXT_110 = NL;
  protected final String TEXT_111 = " " + NL + "    Resource_impl::releaseObject();";
  protected final String TEXT_112 = NL + "    ";
  protected final String TEXT_113 = "Device_impl::releaseObject();";
  protected final String TEXT_114 = NL + "    LOG_TRACE( ";
  protected final String TEXT_115 = "_base, \"COMPLETED RELEASE OBJECT\" );" + NL + "}" + NL + "" + NL + "void ";
  protected final String TEXT_116 = "_base::loadProperties()" + NL + "{";
  protected final String TEXT_117 = NL + "    // Set the sequence with its initial values";
  protected final String TEXT_118 = NL + "    ";
  protected final String TEXT_119 = ".push_back(";
  protected final String TEXT_120 = ");";
  protected final String TEXT_121 = "            ";
  protected final String TEXT_122 = NL + "        ";
  protected final String TEXT_123 = ".resize(";
  protected final String TEXT_124 = ");";
  protected final String TEXT_125 = NL + "        ";
  protected final String TEXT_126 = "[";
  protected final String TEXT_127 = "].";
  protected final String TEXT_128 = " = ";
  protected final String TEXT_129 = ";";
  protected final String TEXT_130 = NL + "    addProperty(";
  protected final String TEXT_131 = ",";
  protected final String TEXT_132 = NL + "                ";
  protected final String TEXT_133 = ", ";
  protected final String TEXT_134 = NL + "               \"";
  protected final String TEXT_135 = "\",";
  protected final String TEXT_136 = NL + "               \"";
  protected final String TEXT_137 = "\",";
  protected final String TEXT_138 = NL + "               \"\",";
  protected final String TEXT_139 = NL + "               \"";
  protected final String TEXT_140 = "\"," + NL + "               \"";
  protected final String TEXT_141 = "\"," + NL + "               \"";
  protected final String TEXT_142 = "\"," + NL + "               \"";
  protected final String TEXT_143 = "\");" + NL;
  protected final String TEXT_144 = NL + "}" + NL + NL;
  protected final String TEXT_145 = "  " + NL + "uint32_t ";
  protected final String TEXT_146 = "_base::getNOutputStreams() {" + NL + "\treturn 0;" + NL + "}";
  protected final String TEXT_147 = NL;
  protected final String TEXT_148 = "  " + NL + "void ";
  protected final String TEXT_149 = "_base::setupIOMappings( ) " + NL + "{" + NL + "  int ninput_streams = 0;" + NL + "  int noutput_streams = 0;" + NL + "  std::vector<std::string>::iterator pname;" + NL + "  std::string sid(\"\");";
  protected final String TEXT_150 = "  " + NL + "  int inMode=RealMode;";
  protected final String TEXT_151 = NL + NL + "  if ( !validGRBlock() ) return;" + NL;
  protected final String TEXT_152 = "  " + NL + "  ninput_streams  = gr_sptr->get_max_input_streams();" + NL + "  gr_io_signature_sptr g_isig = gr_sptr->input_signature();";
  protected final String TEXT_153 = "  " + NL + "  noutput_streams = gr_sptr->get_max_output_streams();" + NL + "  gr_io_signature_sptr g_osig = gr_sptr->output_signature();";
  protected final String TEXT_154 = NL + "  " + NL + "  LOG_DEBUG( ";
  protected final String TEXT_155 = "_base, \"GNUHAWK IO MAPPINGS IN/OUT \" << ninput_streams << \"/\" << noutput_streams );" + NL + "" + NL + "  //" + NL + "  // Someone reset the GR Block so we need to clean up old mappings if they exists" + NL + "  // we need to reset the io signatures and check the vlens" + NL + "  //";
  protected final String TEXT_156 = " " + NL;
  protected final String TEXT_157 = " " + NL + "   if ( _istreams.size() > 0 || _ostreams.size() > 0 ) {";
  protected final String TEXT_158 = NL;
  protected final String TEXT_159 = " " + NL + "   if ( _istreams.size() > 0 ) {";
  protected final String TEXT_160 = NL;
  protected final String TEXT_161 = " " + NL + "   if ( _ostreams.size() > 0 ) {";
  protected final String TEXT_162 = NL;
  protected final String TEXT_163 = " " + NL + "    LOG_DEBUG(  ";
  protected final String TEXT_164 = "_base, \"RESET INPUT SIGNATURE SIZE:\" << _istreams.size() );" + NL + "    IStreamList::iterator istream;" + NL + "    for ( int idx=0 ; istream != _istreams.end(); idx++, istream++ ) {" + NL + "        // re-add existing stream definitons" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_165 = "_base, \"ADD READ INDEX TO GNU RADIO BLOCK\");" + NL + "      if ( ninput_streams == -1 ) gr_sptr->add_read_index();" + NL + "" + NL + "      // setup io signature " + NL + "      istream->associate( gr_sptr );" + NL + "    }";
  protected final String TEXT_166 = NL;
  protected final String TEXT_167 = " " + NL + "    LOG_DEBUG(  ";
  protected final String TEXT_168 = "_base, \"RESET OUTPUT SIGNATURE SIZE:\" << _ostreams.size() );" + NL + "    OStreamList::iterator ostream;" + NL + "    for ( int idx=0 ; ostream != _ostreams.end(); idx++, ostream++ ) {" + NL + "        // need to evaluate new settings...???" + NL + "        ostream->associate( gr_sptr );" + NL + "    }";
  protected final String TEXT_169 = NL + NL + "    return;" + NL + "  }" + NL;
  protected final String TEXT_170 = NL + "    ";
  protected final String TEXT_171 = " " + NL + "   //" + NL + "   // Setup mapping of RH port to GNU RADIO Block input streams" + NL + "   // For version 1,  we are ignoring the GNU Radio input stream -1 case that allows multiple data " + NL + "   // streams over a single connection.  We are mapping a single RH Port to a single GNU Radio stream." + NL + "   // Stream Identifiers will  be pass along as they are received" + NL + "   //" + NL + "   LOG_TRACE( ";
  protected final String TEXT_172 = "_base, \"setupIOMappings INPUT PORTS: \" << inPorts.size() );" + NL + "   pname = inputPortOrder.begin();" + NL + "   for( int i=0; pname != inputPortOrder.end(); pname++ ) {" + NL + "" + NL + "       // grab ports based on their order in the scd.xml file" + NL + "       RH_ProvidesPortMap::iterator p_in = inPorts.find(*pname);" + NL + "       if ( p_in != inPorts.end() ) {" + NL + "           BULKIO_data";
  protected final String TEXT_173 = "_In_i *port = dynamic_cast< BULKIO_data";
  protected final String TEXT_174 = "_In_i * >(p_in->second);" + NL + "   \t   int mode = inMode;" + NL + "\t   sid = \"\";" + NL + "" + NL + "           // need to add read index to GNU Radio Block for processing streams when max_input == -1" + NL + "       \t   if ( ninput_streams == -1 ) gr_sptr->add_read_index();" + NL + "" + NL + "\t   // check if we received SRI during setup" + NL + "\t   BULKIO::StreamSRISequence_var sris = port->activeSRIs();" + NL + "           if (  sris->length() > 0 ) {" + NL + "               BULKIO::StreamSRI sri = sris[sris->length()-1];" + NL + "               mode = sri.mode;" + NL + "           }" + NL + "" + NL + "\t   _istreams.push_back( gr_istream< BULKIO_data";
  protected final String TEXT_175 = "_In_i > ( port, gr_sptr, i, mode, sid ));" + NL + "\t   LOG_DEBUG(  ";
  protected final String TEXT_176 = "_base, \"ADDING INPUT MAP IDX:\" << i << \" SID:\" << sid );\t" + NL + "\t   // increment port counter" + NL + "\t   i++;" + NL + "       }" + NL + "   } " + NL;
  protected final String TEXT_177 = NL;
  protected final String TEXT_178 = " " + NL + "   //" + NL + "   // Setup mapping of RH port to GNU RADIO Block input streams" + NL + "   // For version 1,  we are ignoring the GNU Radio output stream -1 case that allows multiple data " + NL + "   // streams over a single connection.  We are mapping a single RH Port to a single GNU Radio stream." + NL + "   //" + NL + "   LOG_TRACE( ";
  protected final String TEXT_179 = "_base, \"setupIOMappings OutputPorts: \" << outPorts.size() );" + NL + "   pname = outputPortOrder.begin();" + NL + "   for( int i=0; pname != outputPortOrder.end(); pname++ ) {" + NL + "" + NL + "       // grab ports based on their order in the scd.xml file" + NL + "       RH_UsesPortMap::iterator p_out = outPorts.find(*pname);" + NL + "       if ( p_out != outPorts.end() ) {" + NL + "           BULKIO_data";
  protected final String TEXT_180 = "_Out_i *port = dynamic_cast< BULKIO_data";
  protected final String TEXT_181 = "_Out_i * >(p_out->second);" + NL + "\t   BULKIO::StreamSRI sri = createOutputSRI( i );" + NL + "\t   int mode = sri.mode;" + NL + "\t   sid = sri.streamID;" + NL + "\t   _ostreams.push_back( gr_ostream< BULKIO_data";
  protected final String TEXT_182 = "_Out_i > ( port, gr_sptr, i, mode, sid ));" + NL + "\t   LOG_DEBUG(  ";
  protected final String TEXT_183 = "_base, \"ADDING OUTPUT MAP IDX:\" << i << \" SID:\" << sid );\t" + NL + "\t   _ostreams[i].setSRI(sri, i );";
  protected final String TEXT_184 = NL + "           _ostreams[i].pushSRI();";
  protected final String TEXT_185 = NL + "\t   // increment port counter" + NL + "\t   i++;" + NL + "       }" + NL + "   }" + NL;
  protected final String TEXT_186 = NL + NL + "}" + NL;
  protected final String TEXT_187 = NL + NL + NL;
  protected final String TEXT_188 = NL + NL + "void ";
  protected final String TEXT_189 = "_base::notifySRI( BULKIO_data";
  protected final String TEXT_190 = "_In_i *port, BULKIO::StreamSRI &sri ) {" + NL + "" + NL + "  LOG_TRACE( ";
  protected final String TEXT_191 = "_base, \"START NotifySRI  port:stream \" << port->getName() << \"/\" << sri.streamID);" + NL + "  boost::mutex::scoped_lock lock(_sriMutex);" + NL + "  _sriQueue.push_back( std::make_pair( port, sri ) );" + NL + "  LOG_TRACE( ";
  protected final String TEXT_192 = "_base, \"END  NotifySRI  QUEUE \" << _sriQueue.size() << \" port:stream \" << port->getName() << \"/\" << sri.streamID); " + NL + "  " + NL + "}" + NL + " " + NL + "void ";
  protected final String TEXT_193 = "_base::processStreamIdChanges() {" + NL + "" + NL + "  boost::mutex::scoped_lock lock(_sriMutex);" + NL + "" + NL + "  LOG_TRACE( ";
  protected final String TEXT_194 = "_base, \"processStreamIDChanges QUEUE: \" << _sriQueue.size()  );" + NL + "  if (  _sriQueue.size() == 0 ) return;" + NL + "  std::string sid(\"\");" + NL + "" + NL + "  if ( validGRBlock() ) {" + NL + "" + NL + "    IStreamList::iterator istream;" + NL + "    int idx=0;" + NL + "    std::string sid(\"\");" + NL + "    int mode=0;" + NL + "    SRIQueue::iterator item = _sriQueue.begin();" + NL + "    " + NL + "    for ( ; item != _sriQueue.end(); item++ ) {" + NL + "       idx = 0;" + NL + "       sid = \"\";" + NL + "       mode= item->second.mode;" + NL + "       sid = item->second.streamID;" + NL + "       istream = _istreams.begin();" + NL + "       for ( ; istream != _istreams.end(); idx++, istream++ ) {" + NL + "" + NL + "\t   if ( istream->port == item->first ) {" + NL + "\t       LOG_DEBUG(  ";
  protected final String TEXT_195 = "_base,  \"  SETTING IN_STREAM ID/STREAM_ID :\" << idx << \"/\" << sid  );" + NL + "\t       istream->sri(true);" + NL + "\t       istream->spe(mode);" + NL;
  protected final String TEXT_196 = NL + "\t       LOG_DEBUG(  ";
  protected final String TEXT_197 = "_base,  \"  SETTING  OUT_STREAM ID/STREAM_ID :\" << idx << \"/\" << sid  );" + NL + "\t       setOutputStreamSRI( idx, item->second );";
  protected final String TEXT_198 = NL + "           }" + NL + "       }" + NL + "" + NL + "   }" + NL + "" + NL + "   _sriQueue.clear();" + NL + "     " + NL + "  }" + NL + "  else {" + NL + " \tLOG_WARN(  ";
  protected final String TEXT_199 = "_base, \" NEW STREAM ID, NO GNU RADIO BLOCK DEFINED, SRI QUEUE SIZE:\" << _sriQueue.size() );" + NL + "  }" + NL + "" + NL + "}" + NL;
  protected final String TEXT_200 = NL + NL;
  protected final String TEXT_201 = NL + "BULKIO::StreamSRI ";
  protected final String TEXT_202 = "_base::createOutputSRI( int32_t idx)" + NL + "{" + NL + "  // for each output stream set the SRI context" + NL + "  BULKIO::StreamSRI sri = BULKIO::StreamSRI();" + NL + "  sri.hversion = 1;" + NL + "  sri.xstart = 0.0;" + NL + "  sri.xdelta = 1;" + NL + "  sri.xunits = BULKIO::UNITS_TIME;" + NL + "  sri.subsize = 0;" + NL + "  sri.ystart = 0.0;" + NL + "  sri.ydelta = 0.0;" + NL + "  sri.yunits = BULKIO::UNITS_NONE;" + NL + "  sri.mode = 0;" + NL + "  std::ostringstream t;" + NL + "  t << naming_service_name.c_str() << \"_\" << idx;" + NL + "  std::string sid = t.str();" + NL + "  sri.streamID = CORBA::string_dup(sid.c_str());" + NL + "  " + NL + "  return sri;" + NL + " " + NL + "}";
  protected final String TEXT_203 = " " + NL + NL;
  protected final String TEXT_204 = NL + "void ";
  protected final String TEXT_205 = "_base::adjustOutputRate(BULKIO::StreamSRI &sri ) {" + NL + "" + NL + "   if ( validGRBlock() ) {" + NL + "      double ret=sri.xdelta*gr_sptr->relative_rate();" + NL + "/**      ";
  protected final String TEXT_206 = NL + "      ret = ret *gr_sptr->decimation();";
  protected final String TEXT_207 = NL + "      ret = ret  / gr_sptr->interpolation();";
  protected final String TEXT_208 = NL + "**/" + NL + "      LOG_TRACE(";
  protected final String TEXT_209 = "_base, \"ADJUSTING SRI.XDELTA FROM/TO: \" << sri.xdelta << \"/\" << ret );" + NL + "      sri.xdelta = ret;" + NL + "" + NL + "   }" + NL + "   " + NL + "}";
  protected final String TEXT_210 = " " + NL;
  protected final String TEXT_211 = NL;
  protected final String TEXT_212 = "_base::TimeDuration ";
  protected final String TEXT_213 = "_base::getTargetDuration() {" + NL + "" + NL + "  TimeDuration  t_drate;;" + NL + "  uint64_t samps=0;" + NL + "  double   xdelta=1.0;" + NL + "  double   trate=1.0;" + NL;
  protected final String TEXT_214 = NL + "  if ( _ostreams.size() > 0 ) {" + NL + "    samps= _ostreams[0].nelems();" + NL + "    xdelta= _ostreams[0].sri.xdelta;" + NL + "  }";
  protected final String TEXT_215 = " " + NL + "" + NL + "  trate = samps*xdelta;" + NL + "  uint64_t sec = (uint64_t)trunc(trate);" + NL + "  uint64_t usec = (uint64_t)((trate-sec)*1e6);" + NL + "  t_drate = boost::posix_time::seconds(sec) + " + NL + "            boost::posix_time::microseconds(usec);" + NL + "  LOG_TRACE( ";
  protected final String TEXT_216 = "_base, \" SEC/USEC \" << sec << \"/\"  << usec << \"\\n\"  <<" + NL + "\t     \" target_duration \" << t_drate );" + NL + "  return t_drate;" + NL + "}" + NL;
  protected final String TEXT_217 = NL;
  protected final String TEXT_218 = "_base::TimeDuration ";
  protected final String TEXT_219 = "_base::calcThrottle( TimeMark &start_time," + NL + "                                             TimeMark &end_time ) {" + NL + "" + NL + "  TimeDuration delta;" + NL + "  TimeDuration target_duration = getTargetDuration();" + NL + "" + NL + "  if ( start_time.is_not_a_date_time() == false ) {" + NL + "    TimeDuration s_dtime= end_time - start_time;" + NL + "    delta = target_duration - s_dtime;" + NL + "    delta /= 4;" + NL + "    LOG_TRACE( ";
  protected final String TEXT_220 = "_base, \" s_time/t_dime \" << s_dtime << \"/\" << target_duration << \"\\n\"  <<" + NL + "\t      \" delta \" << delta );" + NL + "  }" + NL + "  return delta;" + NL + "}" + NL + NL;
  protected final String TEXT_221 = NL + NL + "/**" + NL + "  DATA ANALYZER TEMPLATE Service Function for GR_BLOCK PATTERN" + NL + "*/" + NL + "" + NL + "template < typename IN_PORT_TYPE > int ";
  protected final String TEXT_222 = "_base::_analyzerServiceFunction( typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams ) {" + NL + "" + NL + "  typedef typename std::vector< gr_istream< IN_PORT_TYPE > > _IStreamList;" + NL + "" + NL + "  boost::mutex::scoped_lock lock(serviceThreadLock);" + NL + "" + NL + "  if ( validGRBlock() == false ) {" + NL + "    " + NL + "    // create our processing block" + NL + "    createBlock();" + NL + "" + NL + "    LOG_DEBUG(  ";
  protected final String TEXT_223 = "_base, \" FINISHED BUILDING  GNU RADIO BLOCK\");" + NL + "  }" + NL + "   " + NL + "  // process any Stream ID changes this could affect number of io streams" + NL + "  processStreamIdChanges();" + NL + "    " + NL + "  if ( !validGRBlock() || istreams.size() == 0 ) {" + NL + "    LOG_WARN(";
  protected final String TEXT_224 = "_base, \"NO STREAMS ATTACHED TO BLOCK...\" );" + NL + "    return NOOP;" + NL + "  }" + NL + "" + NL + "  // resize data vectors for passing data to GR_BLOCK object" + NL + "  _input_ready.resize( istreams.size() );" + NL + "  _ninput_items_required.resize( istreams.size());" + NL + "  _ninput_items.resize( istreams.size());" + NL + "  _input_items.resize(istreams.size());" + NL + "  _output_items.resize(0);" + NL + "  " + NL + "  //" + NL + "  // RESOLVE: need to look at forecast strategy, " + NL + "  //    1)  see how many read items are necessary for N number of outputs" + NL + "  //    2)  read input data and see how much output we can produce" + NL + "  //" + NL + "  " + NL + "  //" + NL + "  // Grab available data from input streams" + NL + "  //" + NL + "  typename _IStreamList::iterator istream = istreams.begin();" + NL + "  int nitems=0;" + NL + "  for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {" + NL + "    // note this a blocking read that can cause deadlocks" + NL + "    nitems = istream->read();" + NL + "    " + NL + "    if ( istream->overrun() ) {" + NL + "        LOG_WARN( ";
  protected final String TEXT_225 = "_base, \" NOT KEEPING UP WITH STREAM ID:\" << istream->streamID );" + NL + "    }" + NL + "    " + NL + "    // RESOLVE issue when SRI changes that could affect the GNU Radio BLOCK" + NL + "    if ( istream->sriChanged() ) {" + NL + "      LOG_DEBUG( ";
  protected final String TEXT_226 = "_base, \"SRI CHANGED, STREAMD IDX/ID: \" " + NL + "               << idx << \"/\" << istream->pkt->streamID );" + NL + "    }" + NL + "  }" + NL + "" + NL + "  LOG_TRACE( ";
  protected final String TEXT_227 = "_base, \"READ NITEMS: \"  << nitems );" + NL + "  if ( nitems <= 0 && !_istreams[0].eos() ) return NOOP;" + NL + "" + NL + "  bool exitServiceFunction = false;" + NL + "  bool eos = false;" + NL + "  int  nout = 0;" + NL + "  while ( nout > -1 && !exitServiceFunction && serviceThread->threadRunning() ) {" + NL + "" + NL + "    eos = false;" + NL + "    nout = _forecastAndProcess( eos, istreams );" + NL + "    if ( nout > -1  ) {" + NL + "      // we chunked on data so move read pointer.." + NL + "      istream = istreams.begin();" + NL + "      for ( ; istream != istreams.end(); istream++ ) {" + NL + "" + NL + "\tint idx=std::distance( istreams.begin(), istream );" + NL + "\t// if we processed data for this stream" + NL + "\tif ( _input_ready[idx] ) {" + NL + "\t  size_t nitems = 0;" + NL + "\t  try {" + NL + "\t    nitems = gr_sptr->nitems_read( idx );" + NL + "\t  }" + NL + "\t  catch(...){}" + NL + "      " + NL + "\t  if ( nitems > istream->nitems() ) {" + NL + "\t       LOG_WARN( ";
  protected final String TEXT_228 = "_base,  \"WORK CONSUMED MORE DATA THAN AVAILABLE,  READ/AVAILABLE \" << nitems << \"/\" << istream->nitems() );" + NL + "               nitems = istream->nitems();" + NL + "\t  }" + NL + "\t  istream->consume( nitems );" + NL + "          LOG_TRACE( ";
  protected final String TEXT_229 = "_base, \" CONSUME READ DATA  ITEMS/REMAIN \" << nitems << \"/\" << istream->nitems());" + NL + "\t}" + NL + "" + NL + "      }" + NL + "      gr_sptr->reset_read_index();" + NL + "    }" + NL + "" + NL + "    // check for not enough data return" + NL + "    if ( nout == -1 ) {" + NL + "" + NL + "      // check for  end of stream" + NL + "      istream = istreams.begin();" + NL + "      for ( ; istream != istreams.end() ; istream++) if ( istream->eos() ) eos=true;" + NL + "" + NL + "      if ( eos ) {" + NL + "        LOG_TRACE( ";
  protected final String TEXT_230 = "_base, \" DATA NOT READY, EOS:\" << eos );" + NL + "\t_forecastAndProcess( eos, istreams );" + NL + "      }" + NL + "" + NL + "      exitServiceFunction = true;" + NL + "    }" + NL + "" + NL + "  }" + NL + "" + NL + "  if ( eos ) {" + NL + "" + NL + "    istream = istreams.begin();" + NL + "    for ( ; istream != istreams.end() ; istream++) {" + NL + "      int idx=std::distance( istreams.begin(), istream );" + NL + "      LOG_TRACE( ";
  protected final String TEXT_231 = "_base, \" CLOSING INPUT STREAM IDX:\" << idx );" + NL + "      istream->close();" + NL + "    }" + NL + "  }" + NL + "" + NL + "  //" + NL + "  // set the read pointers of the GNU Radio Block to start at the beginning of the " + NL + "  // supplied buffers" + NL + "  //" + NL + "  gr_sptr->reset_read_index();" + NL + "" + NL + "  LOG_TRACE( ";
  protected final String TEXT_232 = "_base, \" END OF ANALYZER SERVICE FUNCTION.....\" << noutput_items );" + NL + "" + NL + "  if ( nout == -1 && eos == false )" + NL + "    return NOOP; " + NL + "  else" + NL + "    return NORMAL;" + NL + "}" + NL + "" + NL + "" + NL + "template <  typename IN_PORT_TYPE > int ";
  protected final String TEXT_233 = "_base::_forecastAndProcess( bool &eos, typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams )" + NL + "{" + NL + "  typedef typename std::vector< gr_istream< IN_PORT_TYPE > >   _IStreamList;" + NL + "" + NL + "  typename _IStreamList::iterator istream = istreams.begin();" + NL + "  int nout = 0;" + NL + "  bool dataReady = false;" + NL + "  if ( !eos ) {" + NL + "    uint64_t max_items_avail = 0;" + NL + "    for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {" + NL + "      LOG_TRACE(  ";
  protected final String TEXT_234 = "_base, \"GET MAX ITEMS: STREAM:\"<< idx << \" NITEMS/SCALARS:\" << istream->nitems() << \"/\" << istream->_data.size() );" + NL + "      max_items_avail = std::max( istream->nitems(), max_items_avail );" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // calc number of output items to produce" + NL + "    //" + NL + "    noutput_items = (int) (max_items_avail * gr_sptr->relative_rate ());" + NL + "    noutput_items = round_down (noutput_items, gr_sptr->output_multiple ());" + NL + "" + NL + "    if ( noutput_items <= 0  ) {" + NL + "       LOG_TRACE( ";
  protected final String TEXT_235 = "_base, \"DATA CHECK - MAX ITEMS  NOUTPUT/MAX_ITEMS:\" <<   noutput_items << \"/\" << max_items_avail);" + NL + "       return -1;" + NL + "    }" + NL + "" + NL + "    if ( gr_sptr->fixed_rate() ) {" + NL + "      istream = istreams.begin();" + NL + "      for ( int i=0; istream != istreams.end(); i++, istream++ ) {" + NL + "\tif ( gr_sptr->fixed_rate() ) {" + NL + "\t  int t_noutput_items = gr_sptr->fixed_rate_ninput_to_noutput( istream->nitems() );" + NL + "\t  if ( gr_sptr->output_multiple_set() ) {" + NL + "\t    t_noutput_items = round_up(t_noutput_items, gr_sptr->output_multiple());" + NL + "\t  }" + NL + "\t  if ( t_noutput_items > 0 ) {" + NL + "\t    if ( noutput_items == 0 ) noutput_items = t_noutput_items;" + NL + "\t    if ( t_noutput_items <= noutput_items ) noutput_items = t_noutput_items;" + NL + "\t  }" + NL + "\t}" + NL + "      }" + NL + "      LOG_TRACE( ";
  protected final String TEXT_236 = "_base, \" FIXED FORECAST NOUTPUT/output_multiple == \" << noutput_items  << \"/\" << gr_sptr->output_multiple());" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // ask the block how much input they need to produce noutput_items..." + NL + "    // if enough data is available to process then set the dataReady flag" + NL + "    //" + NL + "    int32_t  outMultiple = gr_sptr->output_multiple();" + NL + "    while ( !dataReady && noutput_items >= outMultiple  ) {" + NL + "" + NL + "      //" + NL + "      // ask the block how much input they need to produce noutput_items..." + NL + "      //" + NL + "      gr_sptr->forecast(noutput_items, _ninput_items_required);" + NL + "" + NL + "      LOG_TRACE( ";
  protected final String TEXT_237 = "_base, \"--> FORECAST IN/OUT \" << _ninput_items_required[0]  << \"/\" << noutput_items  );" + NL + "" + NL + "      istream = istreams.begin();" + NL + "      uint32_t dr_cnt=0;" + NL + "      for ( int idx=0 ; noutput_items > 0 && istream != istreams.end(); idx++, istream++ ) {" + NL + "\t// check if buffer has enough elements" + NL + "\t_input_ready[idx] = false;" + NL + "\tif ( istream->nitems() >= (uint64_t)_ninput_items_required[idx] ) {" + NL + "\t  _input_ready[idx] = true;" + NL + "\t  dr_cnt++;" + NL + "\t}" + NL + "\tLOG_TRACE( ";
  protected final String TEXT_238 = "_base, \"ISTREAM DATACHECK NELMS/NITEMS/REQ/READY:\" <<   istream->nelems() << \"/\" << istream->nitems() << \"/\" << _ninput_items_required[idx] << \"/\" << _input_ready[idx]);" + NL + "      }" + NL + "    " + NL + "      if ( dr_cnt < istreams.size() ) {" + NL + "        if ( outMultiple > 1 )" + NL + "       \t  noutput_items -= outMultiple;" + NL + "        else" + NL + "          noutput_items /= 2;" + NL + "      }" + NL + "      else {" + NL + "        dataReady = true;" + NL + "      }" + NL + "      LOG_TRACE( ";
  protected final String TEXT_239 = "_base, \" TRIM FORECAST NOUTPUT/READY \" << noutput_items << \"/\" << dataReady );" + NL + "    }" + NL + "" + NL + "    // check if data is ready..." + NL + "    if ( !dataReady ) {" + NL + "      LOG_TRACE( ";
  protected final String TEXT_240 = "_base, \"DATA CHECK - NOT ENOUGH DATA  AVAIL/REQ:\" <<   _istreams[0].nitems() << \"/\" << _ninput_items_required[0] );" + NL + "      return -1;\t " + NL + "    }" + NL + "" + NL + "" + NL + "    // reset looping variables" + NL + "    int  ritems = 0;" + NL + "    int  nitems = 0;" + NL + "" + NL + "    // reset caching vectors" + NL + "    _output_items.clear();" + NL + "    _input_items.clear();" + NL + "    _ninput_items.clear();" + NL + "    istream = istreams.begin();" + NL + "    for ( int idx=0 ; istream != istreams.end(); idx++, istream++ ) {" + NL + "" + NL + "      // check if the stream is ready" + NL + "      if ( !_input_ready[idx] ) continue;" + NL + "      " + NL + "      // get number of items remaining" + NL + "      try {" + NL + "        ritems = gr_sptr->nitems_read( idx );" + NL + "      }" + NL + "      catch(...){" + NL + "        // something bad has happened, we are missing an input stream" + NL + "\tLOG_ERROR( ";
  protected final String TEXT_241 = "_base, \"MISSING INPUT STREAM FOR GR BLOCK, STREAM ID:\" <<   istream->streamID );" + NL + "        return -2;" + NL + "      } " + NL + "    " + NL + "      nitems = istream->nitems() - ritems;" + NL + "      LOG_TRACE( ";
  protected final String TEXT_242 = "_base,  \" ISTREAM: IDX:\" << idx  << \" ITEMS AVAIL/READ/REQ \" << nitems << \"/\" " + NL + "\t\t << ritems << \"/\" << _ninput_items_required[idx] );" + NL + "      if ( nitems >= _ninput_items_required[idx] && nitems > 0 ) {" + NL + "\t//remove eos checks ...if ( nitems < _ninput_items_required[idx] ) nitems=0;" + NL + "        _ninput_items.push_back( nitems );" + NL + "\t_input_items.push_back( (const void *) (istream->read_pointer(ritems)) );" + NL + "      }" + NL + "    }" + NL + "" + NL + "    nout=0;" + NL + "    if ( _input_items.size() != 0 && serviceThread->threadRunning() ) {" + NL + "      LOG_TRACE( ";
  protected final String TEXT_243 = "_base, \" CALLING WORK.....N_OUT:\" << noutput_items << \" N_IN:\" << nitems << \" ISTREAMS:\" << _input_items.size() << \" OSTREAMS:\" << _output_items.size());" + NL + "      nout = gr_sptr->general_work( noutput_items, _ninput_items, _input_items, _output_items);" + NL + "" + NL + "       // sink/analyzer patterns do not return items, so consume_each is not called in Gnu Radio BLOCK" + NL + "       if ( nout == 0 ) {" + NL + "           gr_sptr->consume_each(nitems);" + NL + "       }" + NL + "" + NL + "      LOG_TRACE( ";
  protected final String TEXT_244 = "_base, \"RETURN  WORK ..... N_OUT:\" << nout);" + NL + "    }" + NL + "" + NL + "    // check for stop condition from work method" + NL + "    if ( nout < gr_block::WORK_DONE ) {" + NL + "      LOG_WARN( ";
  protected final String TEXT_245 = "_base, \"WORK RETURNED STOP CONDITION...\" << nout );" + NL + "      nout=0;" + NL + "      eos = true;" + NL + "    }" + NL + "  }" + NL + "" + NL + "  return nout;" + NL + "     " + NL + "}" + NL + NL;
  protected final String TEXT_246 = NL;
  protected final String TEXT_247 = NL + "template <  typename IN_PORT_TYPE, typename OUT_PORT_TYPE > int ";
  protected final String TEXT_248 = "_base::_transformerServiceFunction( typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams ," + NL + "\t\t\t\t\t\t\t\t\t\t\ttypename  std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams  )" + NL + "{" + NL + "  typedef typename std::vector< gr_istream< IN_PORT_TYPE > >   _IStreamList;" + NL + "  typedef typename std::vector< gr_ostream< OUT_PORT_TYPE > >  _OStreamList;" + NL + "" + NL + "  boost::mutex::scoped_lock lock(serviceThreadLock);" + NL + "" + NL + "  if ( validGRBlock() == false ) {" + NL + "" + NL + "    // create our processing block, and setup  property notifiers" + NL + "    createBlock();" + NL + "" + NL + "    LOG_DEBUG( ";
  protected final String TEXT_249 = "_base, \" FINISHED BUILDING  GNU RADIO BLOCK\");" + NL + "  }" + NL + " " + NL + "  //process any Stream ID changes this could affect number of io streams" + NL + "  processStreamIdChanges();" + NL + "" + NL + "  if ( !validGRBlock() || istreams.size() == 0 || ostreams.size() == 0  ) {" + NL + "    LOG_WARN(";
  protected final String TEXT_250 = "_base, \"NO STREAMS ATTACHED TO BLOCK...\" );" + NL + "    return NOOP;" + NL + "  }" + NL + "" + NL + "  _input_ready.resize( istreams.size() );" + NL + "  _ninput_items_required.resize( istreams.size() );" + NL + "  _ninput_items.resize( istreams.size() );" + NL + "  _input_items.resize( istreams.size() );" + NL + "  _output_items.resize( ostreams.size() );" + NL + "" + NL + "  //" + NL + "  // RESOLVE: need to look at forecast strategy, " + NL + "  //    1)  see how many read items are necessary for N number of outputs" + NL + "  //    2)  read input data and see how much output we can produce" + NL + "  //" + NL + "" + NL + "  //" + NL + "  // Grab available data from input streams" + NL + "  //" + NL + "  typename _OStreamList::iterator ostream;" + NL + "  typename _IStreamList::iterator istream = istreams.begin();" + NL + "  int nitems=0;" + NL + "  for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {" + NL + "    // note this a blocking read that can cause deadlocks" + NL + "    nitems = istream->read();" + NL + "    " + NL + "    if ( istream->overrun() ) {" + NL + "      LOG_WARN( ";
  protected final String TEXT_251 = "_base, \" NOT KEEPING UP WITH STREAM ID:\" << istream->streamID );" + NL + "    }" + NL + "" + NL + "    if ( istream->sriChanged() ) {" + NL + "      // RESOLVE - need to look at how SRI changes can affect Gnu Radio BLOCK state" + NL + "      LOG_DEBUG( ";
  protected final String TEXT_252 = "_base, \"SRI CHANGED, STREAMD IDX/ID: \" " + NL + "               << idx << \"/\" << istream->pkt->streamID );" + NL + "      setOutputStreamSRI( idx, istream->pkt->SRI );" + NL + "    }" + NL + "" + NL + "  }" + NL + "" + NL + "  LOG_TRACE( ";
  protected final String TEXT_253 = "_base, \"READ NITEMS: \"  << nitems );" + NL + "  if ( nitems <= 0 && !_istreams[0].eos() ) return NOOP;" + NL + "" + NL + "  bool exitServiceFunction = false;" + NL + "  bool eos = false;" + NL + "  int  nout = 0;" + NL + "  while ( nout > -1 && !exitServiceFunction && serviceThread->threadRunning() ) {" + NL + "" + NL + "    eos = false;" + NL + "    nout = _forecastAndProcess( eos, istreams, ostreams );" + NL + "    if ( nout > -1  ) {" + NL + "" + NL + "      // we chunked on data so move read pointer.." + NL + "      istream = istreams.begin();" + NL + "      for ( ; istream != istreams.end(); istream++ ) {" + NL + "\tint idx=std::distance( istreams.begin(), istream );" + NL + "\t// if we processed data for this stream" + NL + "\tif ( _input_ready[idx] ) {" + NL + "\t  size_t nitems = 0;" + NL + "\t  try {" + NL + "\t    nitems = gr_sptr->nitems_read( idx );" + NL + "\t  }" + NL + "\t  catch(...){}" + NL + "      " + NL + "\t  if ( nitems > istream->nitems() ) {" + NL + "\t       LOG_WARN( ";
  protected final String TEXT_254 = "_base,  \"WORK CONSUMED MORE DATA THAN AVAILABLE,  READ/AVAILABLE \" << nitems << \"/\" << istream->nitems() );" + NL + "               nitems = istream->nitems();" + NL + "\t  }" + NL + "\t  istream->consume( nitems );" + NL + "\t  LOG_TRACE( ";
  protected final String TEXT_255 = "_base, \" CONSUME READ DATA  ITEMS/REMAIN \" << nitems << \"/\" << istream->nitems());" + NL + "\t}" + NL + "" + NL + "      }" + NL + "      gr_sptr->reset_read_index();" + NL + "    }" + NL + "" + NL + "    // check for not enough data return" + NL + "    if ( nout == -1 ) {" + NL + "" + NL + "      // check for  end of stream" + NL + "      istream = istreams.begin();" + NL + "      for ( ; istream != istreams.end() ; istream++) if ( istream->eos() ) eos=true;" + NL + "" + NL + "      if ( eos ) {" + NL + "        LOG_TRACE(  ";
  protected final String TEXT_256 = "_base, \"EOS SEEN, SENDING DOWNSTREAM \" );" + NL + "\t_forecastAndProcess( eos, istreams, ostreams);" + NL + "      }" + NL + "" + NL + "      exitServiceFunction = true;" + NL + "    }" + NL + "" + NL + "  }" + NL + "" + NL + "  if ( eos ) {" + NL + "" + NL + "    istream = istreams.begin();" + NL + "    for ( ; istream != istreams.end() ; istream++ ) {" + NL + "        int idx=std::distance( istreams.begin(), istream );" + NL + "        LOG_DEBUG( ";
  protected final String TEXT_257 = "_base, \" CLOSING INPUT STREAM IDX:\" << idx );" + NL + "        istream->close();" + NL + "    }" + NL + "" + NL + "    // close remaining output streams" + NL + "    ostream = ostreams.begin();" + NL + "    for ( ; eos && ostream != ostreams.end(); ostream++ ) {" + NL + "        int idx=std::distance( ostreams.begin(), ostream );" + NL + "        LOG_DEBUG( ";
  protected final String TEXT_258 = "_base, \" CLOSING OUTPUT STREAM IDX:\" << idx );" + NL + "        ostream->close();" + NL + "    }" + NL + "" + NL + "  }" + NL + "" + NL + "  //" + NL + "  // set the read pointers of the GNU Radio Block to start at the beginning of the " + NL + "  // supplied buffers" + NL + "  //" + NL + "  gr_sptr->reset_read_index();" + NL + "" + NL + "  LOG_TRACE( ";
  protected final String TEXT_259 = "_base, \" END OF TRANSFORM SERVICE FUNCTION.....\" << noutput_items );" + NL + "" + NL + "  if ( nout == -1 && eos == false )" + NL + "    return NOOP;    " + NL + "  else" + NL + "    return NORMAL;" + NL + "" + NL + "}" + NL + "" + NL + "" + NL + "template <  typename IN_PORT_TYPE, typename OUT_PORT_TYPE > int ";
  protected final String TEXT_260 = "_base::_forecastAndProcess( bool &eos, typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams ," + NL + "\t\t\t\t\t\t\t\t\t\t\ttypename  std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams  )" + NL + "{" + NL + "  typedef typename std::vector< gr_istream< IN_PORT_TYPE > >   _IStreamList;" + NL + "  typedef typename std::vector< gr_ostream< OUT_PORT_TYPE > >  _OStreamList;" + NL + "" + NL + "  typename _OStreamList::iterator ostream;" + NL + "  typename _IStreamList::iterator istream = istreams.begin();" + NL + "  int nout = 0;" + NL + "  bool dataReady = false;" + NL + "  if ( !eos ) {" + NL + "    uint64_t max_items_avail = 0;" + NL + "    for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {" + NL + "      LOG_TRACE(  ";
  protected final String TEXT_261 = "_base, \"GET MAX ITEMS: STREAM:\"<< idx << \" NITEMS/SCALARS:\" << istream->nitems() << \"/\" << istream->_data.size() );" + NL + "      max_items_avail = std::max( istream->nitems(), max_items_avail );" + NL + "    }" + NL + "" + NL + "    if ( max_items_avail == 0  ) {" + NL + "       LOG_TRACE( ";
  protected final String TEXT_262 = "_base, \"DATA CHECK - MAX ITEMS  NOUTPUT/MAX_ITEMS:\" <<   noutput_items << \"/\" << max_items_avail);" + NL + "       return -1;" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // calc number of output elements based on input items available" + NL + "    //" + NL + "    noutput_items = 0;" + NL + "    if ( !gr_sptr->fixed_rate() )  {" + NL + "      noutput_items = round_down((int32_t) (max_items_avail * gr_sptr->relative_rate()), gr_sptr->output_multiple());" + NL + "      LOG_TRACE( ";
  protected final String TEXT_263 = "_base, \" VARIABLE FORECAST NOUTPUT == \" << noutput_items );" + NL + "    }   " + NL + "    else {" + NL + "      istream = istreams.begin();" + NL + "      for ( int i=0; istream != istreams.end(); i++, istream++ ) {" + NL + "        if ( gr_sptr->fixed_rate() ) {" + NL + "          int t_noutput_items = gr_sptr->fixed_rate_ninput_to_noutput( istream->nitems() );" + NL + "\t  if ( gr_sptr->output_multiple_set() ) {" + NL + "\t    t_noutput_items = round_up(t_noutput_items, gr_sptr->output_multiple());" + NL + "\t  }" + NL + "\t  if ( t_noutput_items > 0 ) {" + NL + "\t    if ( noutput_items == 0 ) noutput_items = t_noutput_items;" + NL + "\t    if ( t_noutput_items <= noutput_items ) noutput_items = t_noutput_items;" + NL + "\t  }" + NL + "        }" + NL + "      }" + NL + "      LOG_TRACE( ";
  protected final String TEXT_264 = "_base,  \" FIXED FORECAST NOUTPUT/output_multiple == \" << noutput_items  << \"/\" << gr_sptr->output_multiple());" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // ask the block how much input they need to produce noutput_items..." + NL + "    // if enough data is available to process then set the dataReady flag" + NL + "    //" + NL + "    int32_t  outMultiple = gr_sptr->output_multiple();" + NL + "    while ( !dataReady && noutput_items >= outMultiple  ) {" + NL + "      //" + NL + "      // ask the block how much input they need to produce noutput_items..." + NL + "      //" + NL + "      gr_sptr->forecast(noutput_items, _ninput_items_required);" + NL + "" + NL + "      LOG_TRACE( ";
  protected final String TEXT_265 = "_base, \"--> FORECAST IN/OUT \" << _ninput_items_required[0]  << \"/\" << noutput_items  );" + NL + "" + NL + "      istream = istreams.begin();" + NL + "      uint32_t dr_cnt=0;" + NL + "      for ( int idx=0 ; noutput_items > 0 && istream != istreams.end(); idx++, istream++ ) {" + NL + "\t// check if buffer has enough elements" + NL + "\t_input_ready[idx] = false;" + NL + "\tif ( istream->nitems() >= (uint64_t)_ninput_items_required[idx] ) {" + NL + "\t  _input_ready[idx] = true;" + NL + "\t  dr_cnt++;" + NL + "\t}" + NL + "\tLOG_TRACE( ";
  protected final String TEXT_266 = "_base, \"ISTREAM DATACHECK NELMS/NITEMS/REQ/READY:\" <<   istream->nelems() << \"/\" << istream->nitems() << \"/\" << _ninput_items_required[idx] << \"/\" << _input_ready[idx]);" + NL + "      }" + NL + "    " + NL + "      if ( dr_cnt < istreams.size() ) {" + NL + "        if ( outMultiple > 1 )" + NL + "       \t  noutput_items -= outMultiple;" + NL + "        else" + NL + "          noutput_items /= 2;" + NL + "      }" + NL + "      else {" + NL + "        dataReady = true;" + NL + "      }" + NL + "      LOG_TRACE( ";
  protected final String TEXT_267 = "_base, \" TRIM FORECAST NOUTPUT/READY \" << noutput_items << \"/\" << dataReady );" + NL + "    }" + NL + "" + NL + "    // check if data is ready..." + NL + "    if ( !dataReady ) {" + NL + "      LOG_TRACE( ";
  protected final String TEXT_268 = "_base, \"DATA CHECK - NOT ENOUGH DATA  AVAIL/REQ:\" <<   _istreams[0].nitems() << \"/\" << _ninput_items_required[0] );" + NL + "      return -1;\t " + NL + "    }" + NL + "" + NL + "    // reset looping variables" + NL + "    int  ritems = 0;" + NL + "    int  nitems = 0;" + NL + "" + NL + "    // reset caching vectors" + NL + "    _output_items.clear();" + NL + "    _input_items.clear();" + NL + "    _ninput_items.clear();" + NL + "    istream = istreams.begin();" + NL + "    for ( int idx=0 ; istream != istreams.end(); idx++, istream++ ) {" + NL + "" + NL + "      // check if the stream is ready" + NL + "      if ( !_input_ready[idx] ) continue;" + NL + "      " + NL + "      // get number of items remaining" + NL + "      try {" + NL + "        ritems = gr_sptr->nitems_read( idx );" + NL + "      }" + NL + "      catch(...){" + NL + "        // something bad has happened, we are missing an input stream" + NL + "\tLOG_ERROR( ";
  protected final String TEXT_269 = "_base, \"MISSING INPUT STREAM FOR GR BLOCK, STREAM ID:\" <<   istream->streamID );" + NL + "        return -2;" + NL + "      } " + NL + "    " + NL + "      nitems = istream->nitems() - ritems;" + NL + "      LOG_TRACE( ";
  protected final String TEXT_270 = "_base,  \" ISTREAM: IDX:\" << idx  << \" ITEMS AVAIL/READ/REQ \" << nitems << \"/\" " + NL + "\t\t << ritems << \"/\" << _ninput_items_required[idx] );" + NL + "      if ( nitems >= _ninput_items_required[idx] && nitems > 0 ) {" + NL + "\t//remove eos checks ...if ( nitems < _ninput_items_required[idx] ) nitems=0;" + NL + "        _ninput_items.push_back( nitems );" + NL + "\t_input_items.push_back( (const void *) (istream->read_pointer(ritems)) );" + NL + "      }" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // setup output buffer vector based on noutput.." + NL + "    //" + NL + "    ostream = ostreams.begin();" + NL + "    for( ; ostream != ostreams.end(); ostream++ ) {" + NL + "      ostream->resize(noutput_items);" + NL + "      _output_items.push_back((void*)(ostream->write_pointer()) );" + NL + "    }" + NL + "" + NL + "    nout=0;" + NL + "    if ( _input_items.size() != 0 && serviceThread->threadRunning() ) {" + NL + "      LOG_TRACE( ";
  protected final String TEXT_271 = "_base, \" CALLING WORK.....N_OUT:\" << noutput_items << \" N_IN:\" << nitems << \" ISTREAMS:\" << _input_items.size() << \" OSTREAMS:\" << _output_items.size());" + NL + "      nout = gr_sptr->general_work( noutput_items, _ninput_items, _input_items, _output_items);" + NL + "      LOG_TRACE( ";
  protected final String TEXT_272 = "_base, \"RETURN  WORK ..... N_OUT:\" << nout);" + NL + "    }" + NL + "" + NL + "    // check for stop condition from work method" + NL + "    if ( nout < gr_block::WORK_DONE ) {" + NL + "      LOG_WARN( ";
  protected final String TEXT_273 = "_base, \"WORK RETURNED STOP CONDITION...\" << nout );" + NL + "      nout=0;" + NL + "      eos = true;" + NL + "    }" + NL + "  }" + NL + "" + NL + "  if (nout != 0 or eos ) {" + NL + "" + NL + "    noutput_items = nout;" + NL + "    LOG_TRACE( ";
  protected final String TEXT_274 = "_base, \" WORK RETURNED: NOUT : \" << nout << \" EOS:\" << eos);" + NL + "    ostream = ostreams.begin();" + NL + "    typename IN_PORT_TYPE::dataTransfer *pkt=NULL;" + NL + "    for ( int idx=0 ; ostream != ostreams.end(); idx++, ostream++ ) {" + NL + "" + NL + "      pkt=NULL;" + NL + "      int inputIdx = idx;" + NL + "      if ( (size_t)(inputIdx) >= istreams.size() ) {" + NL + "\tfor ( inputIdx= istreams.size()-1; inputIdx > -1; inputIdx--) {" + NL + "\t  if ( istreams[inputIdx].pkt != NULL ) {" + NL + "\t    pkt = istreams[inputIdx].pkt;" + NL + "\t    break;" + NL + "\t  }" + NL + "\t}" + NL + "      }" + NL + "      else {" + NL + "\tpkt = istreams[inputIdx].pkt;" + NL + "      }" + NL + "" + NL + "      LOG_TRACE( ";
  protected final String TEXT_275 = "_base,  \"PUSHING DATA   ITEMS/STREAM_ID \" << ostream->nitems() << \"/\" << ostream->streamID );    " + NL + "      if ( _maintainTimeStamp ) {" + NL + "" + NL + "\t// set time stamp for output samples based on input time stamp" + NL + "\tif ( ostream->nelems() == 0 )  {" + NL + "#ifdef TEST_TIME_STAMP" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_276 = "_base, \"SEED - TS SRI:  xdelta:\" << std::setprecision(12) << ostream->sri.xdelta );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_277 = "_base, \"OSTREAM WRITE:   maint:\" << _maintainTimeStamp );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_278 = "_base, \"                  mode:\" <<  ostream->tstamp.tcmode );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_279 = "_base, \"                status:\" <<  ostream->tstamp.tcstatus );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_280 = "_base, \"                offset:\" <<  ostream->tstamp.toff );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_281 = "_base, \"                 whole:\" <<  std::setprecision(10) << ostream->tstamp.twsec );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_282 = "_base, \"SEED - TS         frac:\" <<  std::setprecision(12) << ostream->tstamp.tfsec );" + NL + "#endif" + NL + "\t  ostream->setTimeStamp( pkt->T, _maintainTimeStamp );" + NL + "\t}" + NL + "" + NL + "\t// write out samples, and set next time stamp based on xdelta and  noutput_items" + NL + "\tostream->write ( noutput_items, eos );" + NL + "" + NL + "      }" + NL + "      else {" + NL + "\t// use incoming packet's time stamp to forward" + NL + "\tif ( pkt ) {" + NL + "#ifdef TEST_TIME_STAMP" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_283 = "_base, \"OSTREAM  SRI:  items/xdelta:\" << noutput_items << \"/\" << std::setprecision(12) << ostream->sri.xdelta );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_284 = "_base, \"PKT - TS         maint:\" << _maintainTimeStamp );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_285 = "_base, \"                  mode:\" <<  pkt->T.tcmode );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_286 = "_base, \"                status:\" <<  pkt->T.tcstatus );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_287 = "_base, \"                offset:\" <<  pkt->T.toff );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_288 = "_base, \"                 whole:\" <<  std::setprecision(10) << pkt->T.twsec );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_289 = "_base, \"PKT - TS          frac:\" <<  std::setprecision(12) << pkt->T.tfsec );" + NL + "#endif" + NL + "\t  ostream->write( noutput_items, eos, pkt->T  );\t   " + NL + "\t}" + NL + "\telse {" + NL + "#ifdef TEST_TIME_STAMP" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_290 = "_base, \"OSTREAM  SRI:  items/xdelta:\" << noutput_items << \"/\" << std::setprecision(12) << ostream->sri.xdelta );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_291 = "_base, \"OSTREAM TOD      maint:\" << _maintainTimeStamp );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_292 = "_base, \"                  mode:\" <<  ostream->tstamp.tcmode );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_293 = "_base, \"                status:\" <<  ostream->tstamp.tcstatus );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_294 = "_base, \"                offset:\" <<  ostream->tstamp.toff );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_295 = "_base, \"                 whole:\" <<  std::setprecision(10) << ostream->tstamp.twsec );" + NL + "\t  LOG_DEBUG(  ";
  protected final String TEXT_296 = "_base, \"OSTREAM TOD       frac:\" <<  std::setprecision(12) << ostream->tstamp.tfsec );" + NL + "#endif" + NL + "\t  // use time of day as time stamp" + NL + "\t  ostream->write( noutput_items, eos,  _maintainTimeStamp );\t   " + NL + "\t}" + NL + "      }" + NL + "" + NL + "    } // for ostreams" + NL + "" + NL + "  }" + NL + "" + NL + "  return nout;" + NL + "     " + NL + "}" + NL + NL + NL;
  protected final String TEXT_297 = NL + NL;
  protected final String TEXT_298 = NL + NL + "/**" + NL + "  DATA GENERATOR TEMPLATE Service Function for GR_BLOCK PATTERN" + NL + "*/" + NL + "" + NL + "template < typename OUT_PORT_TYPE > int ";
  protected final String TEXT_299 = "_base::_generatorServiceFunction( typename  std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams ) " + NL + "{" + NL + "" + NL + "  typedef typename std::vector< gr_ostream< OUT_PORT_TYPE > >  _OStreamList;" + NL + "" + NL + "  boost::mutex::scoped_lock lock(serviceThreadLock);" + NL + "" + NL + "  if ( validGRBlock() == false ) {" + NL + "" + NL + "    // create our processing block, and setup  property notifiers" + NL + "    createBlock();" + NL + "" + NL + "    LOG_DEBUG( ";
  protected final String TEXT_300 = "_base, \"FINISHED BUILDING  GNU RADIO BLOCK\");" + NL + "  }" + NL + "" + NL + "" + NL + "  if ( !validGRBlock() || ostreams.size() == 0  ) {" + NL + "    LOG_WARN( ";
  protected final String TEXT_301 = "_base, \"NO OUTPUT STREAMS DEFINED FOR GNU RADIO BLOCK...\" );" + NL + "    return NOOP;" + NL + "  }" + NL + "" + NL + "  _ninput_items_required.resize( 0 );" + NL + "  _ninput_items.resize( 0 );" + NL + "  _input_items.resize(0);" + NL + "  _output_items.resize( 0 );" + NL + "" + NL + "  typename _OStreamList::iterator  ostream;" + NL + "  noutput_items = gr_pagesize();" + NL + "" + NL + "  // find transfer length for this block... " + NL + "  //   Might want to add per port property and save it off when setupIOMappings is called" + NL + "  if ( propTable.find(\"transfer_size\") != propTable.end()) {" + NL + "    CORBA::Any transfer_any;" + NL + "    CORBA::Long transfer;" + NL + "" + NL + "    propTable[\"transfer_size\"]->getValue(transfer_any);" + NL + "    try {" + NL + "      transfer_any >>= transfer;" + NL + "      noutput_items = transfer;" + NL + "    }" + NL + "    catch(...)" + NL + "    {}" + NL + "  }" + NL + "" + NL + "  gr_sptr->forecast(noutput_items, _ninput_items_required);" + NL + "" + NL + "  LOG_TRACE(  ";
  protected final String TEXT_302 = "_base, \" FORECAST == \" << noutput_items );" + NL + "" + NL + "  ostream = ostreams.begin();" + NL + "  for( ; ostream != ostreams.end(); ostream++ ) {" + NL + "    // push ostream's buffer address onto list of output buffers" + NL + "    ostream->resize(noutput_items);" + NL + "    _output_items.push_back((void*)(ostream->write_pointer()) );" + NL + "  }" + NL + "" + NL + "  // call the work function" + NL + "  int numOut=0;" + NL + "  numOut = gr_sptr->general_work( noutput_items, _ninput_items, _input_items, _output_items);" + NL + "" + NL + "  bool eos = false;" + NL + "  // check for stop condition from work method" + NL + "  if ( numOut == gr_block::WORK_DONE ) {" + NL + "    numOut = 0;" + NL + "    eos=true;" + NL + "  } else {" + NL + "    sentEOS = false;" + NL + "  }" + NL + "" + NL + "  if (numOut != 0 or (eos and !sentEOS)){" + NL + "" + NL + "    // write out all the data   " + NL + "    ostream = ostreams.begin();" + NL + "    for( ; ostream != ostreams.end(); ostream++ ) {" + NL + "      LOG_TRACE( ";
  protected final String TEXT_303 = "_base, \"PUSHING DATA   NOUT/NITEMS/OITEMS/STREAM_ID \" << numOut << \"/\" << ostream->nitems()  << \"/\" << ostream->oitems() << \"/\" << ostream->streamID );" + NL + "#ifdef TEST_TIME_STAMP" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_304 = "_base, \"OSTREAM SRI:    xdelta:\" << std::setprecision(12) << ostream->sri.xdelta );" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_305 = "_base, \"OSTREAM WRITE:   maint:\" << _maintainTimeStamp );" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_306 = "_base, \"                  mode:\" <<  ostream->tstamp.tcmode );" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_307 = "_base, \"                status:\" <<  ostream->tstamp.tcstatus );" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_308 = "_base, \"                offset:\" <<  ostream->tstamp.toff );" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_309 = "_base, \"                 whole:\" <<  std::setprecision(10) << ostream->tstamp.twsec );" + NL + "      LOG_DEBUG(  ";
  protected final String TEXT_310 = "_base, \"                  frac:\" <<  std::setprecision(12) << ostream->tstamp.tfsec );" + NL + "#endif" + NL + "      ostream->write( numOut, eos, _maintainTimeStamp );" + NL + "" + NL + "   }" + NL + "   if (eos)" + NL + "     sentEOS = true;" + NL + "" + NL + "   // close stream and reset counters  " + NL + "   ostream = ostreams.begin(); " + NL + "   for( ; eos && ostream != ostreams.end(); ostream++ )  ostream->close();" + NL + "" + NL + "   if (eos)" + NL + "     return NOOP;" + NL + "" + NL + "  }" + NL + "" + NL + "  return NORMAL;" + NL + "}" + NL;
  protected final String TEXT_311 = NL + NL + NL;
  protected final String TEXT_312 = NL;

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
    List<CppProperties.Property> properties = CppProperties.getProperties(softPkg);
    Ports ports = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts();
    EList<Provides> provides = ports.getProvides();
    EList<Uses> uses = ports.getUses();
    List<IPath> search_paths = Arrays.asList(RedhawkIdeActivator.getDefault().getDefaultIdlIncludePath());
    TemplateParameter portTempl = new TemplateParameter(impl, implSettings, search_paths);
    boolean autoStart = false;
    Date date = new Date(System.currentTimeMillis());
    for (Property tempProp : implSettings.getProperties()) {
        if ("auto_start".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
                autoStart = true;
                break;
            }
        }
    }
    
    HashMap<String, IScaPortCodegenTemplate> portMap = new HashMap<String, IScaPortCodegenTemplate>();
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
    String deviceType = "";
    boolean aggregateDevice = false;

    // TODO: Refactor this long block of code (and other similar blocks) into one handy place that can just give you an enum
    final List<SupportsInterface> supportedInterfaces = softPkg.getDescriptor().getComponent().getComponentFeatures().getSupportsInterface();
    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/Device:1.0")) {
            deviceType = "";
            break;
        }
    }

    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/LoadableDevice:1.0")) {
            deviceType = "Loadable"; 
            break;
        }
    }

    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/ExecutableDevice:1.0")) {
            deviceType = "Executable"; 
            break;
        }
    }

    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/AggregateDevice:1.0")) {
            aggregateDevice = true;
            break;
        }
    }

    Pattern p = Pattern.compile("/(.*):");
    boolean containsBULKIO = false;
    String outputType = "";         // maps to bulkio for i.e BULKIO_dataUshort_Out_i Ushort
    String outputRaw = "";          // maps to C type defined in port_impl.h UInt16
    for (Uses entry : uses) {
        String intName = entry.getRepID();

        if (intName.contains("BULKIO")) {
	   Matcher m = p.matcher(intName);
	   String iname="";	
	   if ( m.find() ) {
	   	iname = m.group(1);
           }
	   
            containsBULKIO = true;
            if (iname.equals("dataFloat")) {
                outputType = "Float";
                outputRaw = "Float";
            } else if (iname.equals("dataOctet")) {
                outputType = "Octet";
                outputRaw = "UInt8";
            } else if (iname.equals("dataChar")) {
                outputType = "Char";
                outputRaw = "Char";
            } else if (iname.equals("dataUshort")) {
                outputType = "Ushort";
                outputRaw = "UInt16";
            } else if (iname.equals("dataShort")) {
                outputType = "Short";
                outputRaw = "Int16";
            } else if (iname.equals("dataLong")) {
                outputType = "Long";
                  outputRaw = "Int32";
            } else if (iname.equals("dataLongLong")) {
                outputType = "LongLong";
                outputRaw = "Int64";
            } else if (iname.equals("dataUlong")) {
                outputType = "Ulong";
                  outputRaw = "UInt32";
            } else if (iname.equals("dataUlonglong")) {
                outputType = "Ulonglong";
                outputRaw = "UInt64";
            } else if (iname.equals("dataDouble")) {
                outputType = "Double";
                outputRaw = "Double";
            }
        }
    }
    String inputType = "";         // maps to bulkio for i.e BULKIO_dataUlong_In_i Ulong
    String inputRaw = "";          // maps to C type defined in port_impl.h UInt32
    for (Provides entry : provides) {
        String intName = entry.getRepID();
        if (intName.contains("BULKIO")) {
           containsBULKIO = true;
	   Matcher m = p.matcher(intName);
	   String iname="";	
	   if ( m.find() ) {
	   	iname = m.group(1);
           }

           //System.out.println("INT:" + intName + " iname:" + iname );
            if (iname.equals("dataFloat")) {
                inputType = "Float";
                inputRaw = "Float";
            } else if (iname.equals("dataOctet")) {
                inputType = "Octet";
                inputRaw = "UInt8";
            } else if (iname.equals("dataChar")) {
                inputType = "Char";
                inputRaw = "Char";
            } else if (iname.equals("dataUshort")) {
                inputType = "Ushort";
                   inputRaw = "UInt16";
            } else if (iname.equals("dataShort")) {
                inputType = "Short";
                  inputRaw = "Int16";
            } else if (iname.equals("dataLong")) {
                inputType = "Long";
                inputRaw = "Int32";
            } else if (iname.equals("dataLongLong")) {
                inputType = "LongLong";
                inputRaw = "Int64";
            } else if (iname.equals("dataUlong")) {
                inputType = "Ulong";
                inputRaw = "UInt32";
            } else if (iname.equals("dataUlonglong")) {
                inputType = "Ulonglong";
                inputRaw = "UInt64";
            } else if (iname.equals("dataDouble")) {
                inputType = "Double";
                inputRaw = "Double";
            }
        }
    }
    
    
    /**
      Determine Type of GnuRadio block we are patterning.
    */
	String blockType = "";
    for (Property tempProp : implSettings.getProperties()) {
        if ("pattern_gr_block".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
            	blockType = "GR_Block";
                break;
            }
        }
        if ("pattern_gr_sync_block".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
            	blockType = "GR_Sync_Block";
                break;
            }
        }
        if ("pattern_gr_sync_decimator".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
            	blockType = "GR_Sync_Decimator";
                break;
            }
        }
        if ("pattern_gr_sync_interpolator".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
            	blockType = "GR_Sync_Interpolator";
                break;
            }
        }
        
    }
    

    stringBuffer.append(TEXT_1);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(ModelUtil.getSpdFileName(softPkg));
    
	String[] output;
	IProduct product = Platform.getProduct();
	if (product != null) {
		output = product.getProperty("aboutText").split("\n");

    stringBuffer.append(TEXT_3);
    stringBuffer.append(output[0]);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(output[1]);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(output[2]);
    
	}

    stringBuffer.append(TEXT_6);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_7);
    
  if (!templ.isDevice()) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_11);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_12);
     } 
    stringBuffer.append(TEXT_13);
    
    } else {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(deviceType);
    stringBuffer.append(TEXT_18);
    
        if (aggregateDevice) {

    stringBuffer.append(TEXT_19);
    
        }

    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(deviceType);
    stringBuffer.append(TEXT_25);
    
        if (aggregateDevice) {

    stringBuffer.append(TEXT_26);
    
        }

    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(deviceType);
    stringBuffer.append(TEXT_32);
    
        if (aggregateDevice) {

    stringBuffer.append(TEXT_33);
    
        }

    stringBuffer.append(TEXT_34);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(deviceType);
    stringBuffer.append(TEXT_39);
    
        if (aggregateDevice) {

    stringBuffer.append(TEXT_40);
    
        }

    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_43);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_44);
     } 
    stringBuffer.append(TEXT_45);
    
    if (provides.size() > 0) {

    stringBuffer.append(TEXT_46);
    
    }
    if (uses.size() > 0) {

    stringBuffer.append(TEXT_47);
    
    }

    stringBuffer.append(TEXT_48);
     if ( inputType.equals("") && !outputType.equals("") ) { 
    stringBuffer.append(TEXT_49);
     } 
    stringBuffer.append(TEXT_50);
    
    for (Provides pro : provides) {
        String entry = pro.getRepID();
        IScaPortCodegenTemplate gen = portMap.get(entry);
        portTempl.setPortRepId(entry);
        portTempl.setPortName(pro.getProvidesName());
        portTempl.setProvidesPort(true);
        portTempl.setGenSupport(false);
        portTempl.setGenClassDef(false);
        portTempl.setGenClassImpl(false);
        String inst = null;
        if (MessagingPortTemplate.MESSAGECHANNEL_REPID.equals(entry)) {
            inst = new MessagingPortTemplate().generateClassInstantiator(entry, true, softPkg, implSettings, portTempl, CodegenUtil.CPP);
            inst += "(\""+pro.getProvidesName()+"\")";
        } else if (gen != null) {
            inst = gen.generateClassInstantiator(entry, true, softPkg, implSettings, portTempl, CodegenUtil.CPP);
        } else {
            inst = new PortTemplate().generateClassInstantiator(entry, true, softPkg, implSettings, portTempl, CodegenUtil.CPP);
        }

    stringBuffer.append(TEXT_51);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(inst.trim());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_54);
    
    }
    for (Uses use : uses) {
        String entry = use.getRepID();
        IScaPortCodegenTemplate gen = portMap.get(entry);
        portTempl.setPortRepId(entry);
        portTempl.setPortName(use.getUsesName());
        portTempl.setProvidesPort(false);
        portTempl.setGenSupport(false);
        portTempl.setGenClassDef(false);
        portTempl.setGenClassImpl(false);
        String inst = null;
        // Loop over provides ports to see if there is a matching interface and port name for the current uses port
        // If so, ignore the uses port
        // This is to support bi-directional ports
        boolean foundMatchingProvides = false;
        for (Provides pro : provides) {
            String entryProvides = pro.getRepID();
            if (entry.equals(entryProvides) && use.getUsesName().equals(pro.getProvidesName())) {
                foundMatchingProvides = true;
                break;
            }
        }
        if (foundMatchingProvides == false){
            if (PropertyChangeEventPortTemplate.EVENTCHANNEL_REPID.equals(entry) 
                    && PropertyChangeEventPortTemplate.EVENTCHANNEL_NAME.equals(use.getUsesName())) {
                inst = new PropertyChangeEventPortTemplate().generateClassInstantiator(entry, false, softPkg, implSettings, portTempl, CodegenUtil.CPP);
                inst += "(\""+use.getUsesName()+"\")";
            } else if (MessagingPortTemplate.MESSAGECHANNEL_REPID.equals(entry)) {
        
                inst = new MessagingPortTemplate().generateClassInstantiator(entry, false, softPkg, implSettings, portTempl, CodegenUtil.CPP);
                inst += "(\""+use.getUsesName()+"\")";
            
            } else if (gen != null) {
                inst = gen.generateClassInstantiator(entry, false, softPkg, implSettings, portTempl, CodegenUtil.CPP);
            } else {
                inst = new PortTemplate().generateClassInstantiator(entry, false, softPkg, implSettings, portTempl, CodegenUtil.CPP);
            }

    stringBuffer.append(TEXT_55);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(inst.trim());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_58);
    
    	    if (PropertyChangeEventPortTemplate.EVENTCHANNEL_REPID.equals(entry) 
                    && PropertyChangeEventPortTemplate.EVENTCHANNEL_NAME.equals(use.getUsesName())) {
	    		for (CppProperties.Property prop : properties) {
            		if (prop.getKinds().indexOf("event") != -1) {

    stringBuffer.append(TEXT_59);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(CppHelper.escapeString(prop.getId()));
    stringBuffer.append(TEXT_61);
    
        		    }
    		    }

    stringBuffer.append(TEXT_62);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_63);
    
    	    }
    	}  //if (foundMatchingProvides == false)
    }
    if ((provides.size() > 0) || (uses.size() > 0)) {

    stringBuffer.append(TEXT_64);
    
}
    for (Provides pro : provides) {

    stringBuffer.append(TEXT_65);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_67);
    
    }
    for (Uses use : uses) {
        String entry = use.getRepID();
        // Loop over provides ports to see if there is a matching interface and port name for the current uses port
        // If so, ignore the uses port
        // This is to support bi-directional ports
        boolean foundMatchingProvides = false;
        for (Provides pro : provides) {
            String entryProvides = pro.getRepID();
            if (entry.equals(entryProvides) && use.getUsesName().equals(pro.getProvidesName())) {
                foundMatchingProvides = true;
                break;
            }
        }
        if (foundMatchingProvides == false){

    stringBuffer.append(TEXT_68);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_71);
    
        }
    }

    stringBuffer.append(TEXT_72);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_74);
    
    for (Provides pro : provides) {
        String entry = pro.getRepID();
        Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true); 
        if (intf == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
        }
        
        String interfaceName = intf.getName();
        boolean hasPushPacketCall = false;
        boolean hasPushPacketXMLCall = false;
        boolean hasPushPacketFileCall = false;
        for (Operation op : intf.getOperations()) {
            int numParams = op.getParams().size();
            if ("pushPacket".equals(op.getName()) && "dataFile".equals(interfaceName)) {
                hasPushPacketFileCall = true;
            } else if ("pushPacket".equals(op.getName()) && (numParams == 4)) {
                hasPushPacketCall = true;
            } else if ("pushPacket".equals(op.getName()) && "dataXML".equals(interfaceName)) {
                hasPushPacketXMLCall = true;
            }
        }
        if (hasPushPacketCall || hasPushPacketXMLCall || hasPushPacketFileCall) {

    stringBuffer.append(TEXT_75);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_77);
    
        }
    }

    stringBuffer.append(TEXT_78);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_79);
    
    for (Provides pro : provides) {
        String entry = pro.getRepID();
        Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true);
        if (intf == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
        }
        
        String interfaceName = intf.getName();
        boolean hasPushPacketCall = false;
        boolean hasPushPacketXMLCall = false;
        boolean hasPushPacketFileCall = false;
        for (Operation op : intf.getOperations()) {
            int numParams = op.getParams().size();
            if ("pushPacket".equals(op.getName()) && (numParams == 4)) {
                hasPushPacketCall = true;
                break;
            } else if ("pushPacket".equals(op.getName()) && "dataXML".equals(interfaceName)) {
                hasPushPacketXMLCall = true;
                break;
            } else if ("pushPacket".equals(op.getName()) && "dataFile".equals(interfaceName)) {
                hasPushPacketFileCall = true;
                break;
            }
        }
        if (hasPushPacketCall || hasPushPacketXMLCall || hasPushPacketFileCall) {

    stringBuffer.append(TEXT_80);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_82);
    
        }
    }

    stringBuffer.append(TEXT_83);
     if (!inputType.equals("") ) { 
    stringBuffer.append(TEXT_84);
     } 
    stringBuffer.append(TEXT_85);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_87);
    
    if ((uses.size() > 0) || (provides.size() > 0)) {

    stringBuffer.append(TEXT_88);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_89);
    
        for (Provides pro : provides) {
            String entry = pro.getRepID();
            Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true); 
            if (intf == null) {
                throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
            }
            String nameSpace = intf.getNameSpace();
            String interfaceName = intf.getName();
        	if (MessagingPortTemplate.MESSAGECHANNEL_REPID.equals(entry)) {

    stringBuffer.append(TEXT_90);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_93);
    
        	} else {

    stringBuffer.append(TEXT_94);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_102);
    
        	}
        }

    stringBuffer.append(TEXT_103);
    
    }

    stringBuffer.append(TEXT_104);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_105);
    
    for (Provides pro : provides) {

    stringBuffer.append(TEXT_106);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_107);
    
    }
    for (Uses use : uses) {
        String entry = use.getRepID();
        // Loop over provides ports to see if there is a matching interface and port name for the current uses port
        // If so, ignore the uses port
        // This is to support bi-directional ports
        boolean foundMatchingProvides = false;
        for (Provides pro : provides) {
            String entryProvides = pro.getRepID();
            if (entry.equals(entryProvides) && use.getUsesName().equals(pro.getProvidesName())) {
                foundMatchingProvides = true;
                break;
            }
        }
        if (foundMatchingProvides == false){

    stringBuffer.append(TEXT_108);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_109);
    
        }
    }

    stringBuffer.append(TEXT_110);
    
    if (!templ.isDevice()) {

    stringBuffer.append(TEXT_111);
    
    } else {

    stringBuffer.append(TEXT_112);
    stringBuffer.append(deviceType);
    stringBuffer.append(TEXT_113);
    
    }

    stringBuffer.append(TEXT_114);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_116);
    
    for (CppProperties.Property prop : properties) {
        if (prop.hasValue() && (prop instanceof CppProperties.SimpleSeqProperty)) {

    stringBuffer.append(TEXT_117);
    
            for(String v : ((CppProperties.SimpleSeqProperty)prop).getCppValues()) {

    stringBuffer.append(TEXT_118);
    stringBuffer.append(prop.getCppName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(v);
    stringBuffer.append(TEXT_120);
    
            }
        } else if (prop instanceof CppProperties.StructSequenceProperty) {

    stringBuffer.append(TEXT_121);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(prop.getCppName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(((CppProperties.StructSequenceProperty)prop).numberOfStructValues());
    stringBuffer.append(TEXT_124);
    
            Map<CppProperties.SimpleProperty, List<String>> myMap = ((CppProperties.StructSequenceProperty)prop).getValueMap(); 
            for (CppProperties.SimpleProperty simple : myMap.keySet()) {
                if (!myMap.get(simple).isEmpty()) {
                    int i = 0;
                    for (String val : myMap.get(simple)) {

    stringBuffer.append(TEXT_125);
    stringBuffer.append(prop.getCppName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(simple.getCppName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(val);
    stringBuffer.append(TEXT_129);
    
                        i++;
                    }
                }
            }
        }
        

    stringBuffer.append(TEXT_130);
    stringBuffer.append(prop.getCppName());
    stringBuffer.append(TEXT_131);
    
        if (prop.hasValue()) {

    stringBuffer.append(TEXT_132);
    stringBuffer.append(prop.getCppValue());
    stringBuffer.append(TEXT_133);
    
        }

    stringBuffer.append(TEXT_134);
    stringBuffer.append(CppHelper.escapeString(prop.getId()));
    stringBuffer.append(TEXT_135);
    
        if (prop.hasName()) {

    stringBuffer.append(TEXT_136);
    stringBuffer.append(CppHelper.escapeString(prop.getName()));
    stringBuffer.append(TEXT_137);
    
        } else {

    stringBuffer.append(TEXT_138);
    
        }

    stringBuffer.append(TEXT_139);
    stringBuffer.append(CppHelper.escapeString(prop.getMode()));
    stringBuffer.append(TEXT_140);
    stringBuffer.append(CppHelper.escapeString(prop.getUnits()));
    stringBuffer.append(TEXT_141);
    stringBuffer.append(CppHelper.escapeString(prop.getAction()));
    stringBuffer.append(TEXT_142);
    stringBuffer.append(CppHelper.escapeString(prop.getKinds()));
    stringBuffer.append(TEXT_143);
    
    }

    stringBuffer.append(TEXT_144);
     if (!outputType.equals("") ) { 
    stringBuffer.append(TEXT_145);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_146);
     } 
    stringBuffer.append(TEXT_147);
     if (!outputType.equals("") || !inputType.equals("") ) { 
    stringBuffer.append(TEXT_148);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_149);
     if (!inputType.equals("") ) { 
    stringBuffer.append(TEXT_150);
     } 
    stringBuffer.append(TEXT_151);
     if (!inputType.equals("") ) { 
    stringBuffer.append(TEXT_152);
     } 
     if (!outputType.equals("") ) { 
    stringBuffer.append(TEXT_153);
     } 
    stringBuffer.append(TEXT_154);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_155);
     
   if (!inputType.equals("") || !outputType.equals("") ) { 

    stringBuffer.append(TEXT_156);
     
   if (!inputType.equals("") && !outputType.equals("") ) { 

    stringBuffer.append(TEXT_157);
     } 
    stringBuffer.append(TEXT_158);
     
   if (!inputType.equals("") && outputType.equals("") ) { 

    stringBuffer.append(TEXT_159);
     } 
    stringBuffer.append(TEXT_160);
     
   if (inputType.equals("") && !outputType.equals("") ) { 

    stringBuffer.append(TEXT_161);
     } 
    stringBuffer.append(TEXT_162);
     
   // create mapping for input Ports to istream definitions 
   if (!inputType.equals("") ) { 

    stringBuffer.append(TEXT_163);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_165);
     } 
    stringBuffer.append(TEXT_166);
     
   // create mapping for input Ports to istream definitions 
   if (!outputType.equals("") ) { 

    stringBuffer.append(TEXT_167);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_168);
     } 
    stringBuffer.append(TEXT_169);
     } 
    stringBuffer.append(TEXT_170);
     
   // create mapping for input Ports to istream definitions 
   if (!inputType.equals("") ) { 

    stringBuffer.append(TEXT_171);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_176);
     } 
    stringBuffer.append(TEXT_177);
     
  // create mapping for output Ports to ostream definitions 
  if (!outputType.equals("") ) { 

    stringBuffer.append(TEXT_178);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_183);
     if (inputType.equals("") ) { 
    stringBuffer.append(TEXT_184);
     } 
    stringBuffer.append(TEXT_185);
     } 
    stringBuffer.append(TEXT_186);
     } 
    stringBuffer.append(TEXT_187);
     if (!inputType.equals("") ) { 
    stringBuffer.append(TEXT_188);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_195);
     if (!outputType.equals("") ) { 
    stringBuffer.append(TEXT_196);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_197);
     } 
    stringBuffer.append(TEXT_198);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_199);
     } 
    stringBuffer.append(TEXT_200);
    
   if ( !outputType.equals("") ) {

    stringBuffer.append(TEXT_201);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_202);
     } 
    stringBuffer.append(TEXT_203);
    
   if ( !outputType.equals("") ) {

    stringBuffer.append(TEXT_204);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_205);
     if ( blockType.equals("GR_Sync_Decimator") ) { 
    stringBuffer.append(TEXT_206);
     } 
     if ( blockType.equals("GR_Sync_Interpolator") ) { 
    stringBuffer.append(TEXT_207);
     } 
    stringBuffer.append(TEXT_208);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_209);
     } 
    stringBuffer.append(TEXT_210);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_213);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_214);
     } 
    stringBuffer.append(TEXT_215);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_220);
    
    /////////////////////////////////////
    // BEGIN DATA ANALYZER PATTERN
    /////////////////////////////////////
  if ( !inputType.equals("") && outputType.equals("") ) {

    stringBuffer.append(TEXT_221);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_245);
    
   // End of Template for Data Analyzer Pattern 
  } 

    stringBuffer.append(TEXT_246);
    
    /////////////////////////////////////
    // BEGIN DATA TRANSFORMER PATTERN
    /////////////////////////////////////
   if ( !inputType.equals("") && !outputType.equals("") ) {

    stringBuffer.append(TEXT_247);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_248);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_254);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_264);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_279);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_280);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_283);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_287);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_296);
    
   // End of Template for Data Transformer Pattern 
  } 

    stringBuffer.append(TEXT_297);
    
 
   /////////////////////////////////////
   //  DATA GENERATOR TEMPLATE Service Function for GR_BLOCK PATTERN
   /////////////////////////////////////
  if ( inputType.equals("") && !outputType.equals("") ) {


    stringBuffer.append(TEXT_298);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_309);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_310);
    
   // End of Template for Data Generator Pattern 
  } 

    stringBuffer.append(TEXT_311);
    stringBuffer.append(TEXT_312);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE