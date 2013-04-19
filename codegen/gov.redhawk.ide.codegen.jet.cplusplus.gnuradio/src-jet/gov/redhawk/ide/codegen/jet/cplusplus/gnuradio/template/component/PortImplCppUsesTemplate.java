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
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.cplusplus.CppHelper;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.CplusplusJetGeneratorPlugin;
import gov.redhawk.ide.idl.Attribute;
import gov.redhawk.ide.idl.IdlUtil;
import gov.redhawk.ide.idl.Interface;
import gov.redhawk.ide.idl.Operation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class PortImplCppUsesTemplate
{

  protected static String nl;
  public static synchronized PortImplCppUsesTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PortImplCppUsesTemplate result = new PortImplCppUsesTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "//RESOLVE PREPARE_ALT_LOGGING(";
  protected final String TEXT_2 = "_";
  protected final String TEXT_3 = "_Out_i,";
  protected final String TEXT_4 = "_i)";
  protected final String TEXT_5 = NL + "// ----------------------------------------------------------------------------------------" + NL + "// ";
  protected final String TEXT_6 = "_";
  protected final String TEXT_7 = "_Out_i definition" + NL + "// ----------------------------------------------------------------------------------------";
  protected final String TEXT_8 = NL + "BULKIO_dataSDDS_Out_i::BULKIO_dataSDDS_Out_i(std::string port_name, ";
  protected final String TEXT_9 = "_base *_parent) : Port_Uses_base_impl(port_name)" + NL + "{" + NL + "    parent = static_cast<";
  protected final String TEXT_10 = "_i *> (_parent);" + NL + "    lastStreamData = NULL;" + NL + "    recConnectionsRefresh = false;" + NL + "    recConnections.length(0);" + NL + "}" + NL + "" + NL + "BULKIO_dataSDDS_Out_i::~BULKIO_dataSDDS_Out_i()" + NL + "{" + NL + "}" + NL + "" + NL + "BULKIO::SDDSStreamDefinition* BULKIO_dataSDDS_Out_i::getStreamDefinition(const char* attachId)" + NL + "{" + NL + "    std::map<std::string, std::pair<BULKIO::SDDSStreamDefinition*, std::string> >::iterator groupIter;" + NL + "    groupIter = attachedGroup.begin();" + NL + "" + NL + "    while (groupIter != attachedGroup.end()) {" + NL + "        if (strcmp((*groupIter).first.c_str(), attachId) == 0) {" + NL + "            return (*groupIter).second.first;" + NL + "        }" + NL + "        groupIter++;" + NL + "    }" + NL + "    return NULL;" + NL + "}" + NL + "" + NL + "char* BULKIO_dataSDDS_Out_i::getUser(const char* attachId)" + NL + "{" + NL + "    std::map<std::string, std::pair<BULKIO::SDDSStreamDefinition*, std::string> >::iterator groupIter;" + NL + "    groupIter = attachedGroup.begin();" + NL + "    while (groupIter != attachedGroup.end()) {" + NL + "        if (strcmp((*groupIter).first.c_str(), attachId) == 0) {" + NL + "            return CORBA::string_dup((*groupIter).second.second.c_str());" + NL + "        }" + NL + "        groupIter++;" + NL + "    }" + NL + "    return NULL;" + NL + "}" + NL + "" + NL + "BULKIO::dataSDDS::InputUsageState BULKIO_dataSDDS_Out_i::usageState()" + NL + "{" + NL + "    if (attachedGroup.size() == 0) {" + NL + "        return BULKIO::dataSDDS::IDLE;" + NL + "    } else if (attachedGroup.size() == 1) {" + NL + "        return BULKIO::dataSDDS::BUSY;" + NL + "    } else {" + NL + "        return BULKIO::dataSDDS::ACTIVE;" + NL + "    }" + NL + "}" + NL + "" + NL + "BULKIO::SDDSStreamSequence* BULKIO_dataSDDS_Out_i::attachedStreams()" + NL + "{" + NL + "    BULKIO::SDDSStreamSequence* seq = new BULKIO::SDDSStreamSequence();" + NL + "    seq->length(1);" + NL + "    (*seq)[0] = *lastStreamData;" + NL + "    return seq;" + NL + "}" + NL + "" + NL + "BULKIO::StringSequence* BULKIO_dataSDDS_Out_i::attachmentIds()" + NL + "{" + NL + "    BULKIO::StringSequence* seq = new BULKIO::StringSequence();" + NL + "    seq->length(attachedGroup.size());" + NL + "    std::map<std::string, std::pair<BULKIO::SDDSStreamDefinition*, std::string> >::iterator groupIter;" + NL + "    groupIter = attachedGroup.begin();" + NL + "    unsigned int i = 0;" + NL + "    while (groupIter != attachedGroup.end()) {" + NL + "        (*seq)[i++] = CORBA::string_dup((*groupIter).first.c_str());" + NL + "        groupIter++;" + NL + "    }" + NL + "" + NL + "    return seq;" + NL + "}" + NL + "" + NL + "char* BULKIO_dataSDDS_Out_i::attach(const BULKIO::SDDSStreamDefinition& stream, const char* userid) throw (BULKIO::dataSDDS::AttachError, BULKIO::dataSDDS::StreamInputError)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);" + NL + "    std::string attachId;" + NL + "    user_id = userid;" + NL + "    std::map<BULKIO::dataSDDS::_var_type, std::string>::iterator portIter;" + NL + "    BULKIO::dataSDDS::_var_type port = NULL;" + NL + "    lastStreamData = new BULKIO::SDDSStreamDefinition(stream);" + NL + "    portIter = attachedPorts.begin();" + NL + "    while (portIter != attachedPorts.end()) {" + NL + "        port = (*portIter).first;" + NL + "        port->detach(attachedPorts[port].c_str());" + NL + "        attachedGroup.erase((*portIter).second);" + NL + "        portIter++;" + NL + "    }" + NL + "    std::vector< std::pair<BULKIO::dataSDDS::_var_type, std::string> >::iterator portIter2 = outConnections.begin();" + NL + "    while (portIter2 != outConnections.end()) {" + NL + "        port = (*portIter2).first;" + NL + "        attachId = port->attach(stream, user_id.c_str());" + NL + "        attachedGroup.insert(std::make_pair(attachId, std::make_pair(lastStreamData, user_id)));" + NL + "        attachedPorts[port] = attachId;" + NL + "        portIter2++;" + NL + "    }" + NL + "    return CORBA::string_dup(attachId.c_str());" + NL + "}" + NL + "" + NL + "void BULKIO_dataSDDS_Out_i::detach(const char* attachId, const char* connectionId)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);" + NL + "    std::vector< std::pair<BULKIO::dataSDDS::_var_type, std::string> >::iterator portIter = outConnections.begin();" + NL + "    std::map<BULKIO::dataSDDS::_var_type, std::string>::iterator portIter2;" + NL + "    while (portIter != outConnections.end()) {" + NL + "        portIter2 = attachedPorts.begin();" + NL + "        if (!strcmp(connectionId, (*portIter).second.c_str())) {" + NL + "        \twhile (portIter2 != attachedPorts.end()) {" + NL + "        \t\tif ((*portIter2).first == (*portIter).first) {" + NL + "        \t\t\t(*(*portIter).first).detach(attachedPorts[(*portIter).first].c_str());" + NL + "        \t\t\treturn;" + NL + "        \t\t}" + NL + "        \t\tportIter2++;" + NL + "        \t}" + NL + "        }" + NL + "        portIter++;" + NL + "    }" + NL + "}" + NL + "" + NL + "/*" + NL + " * pushSRI" + NL + " *     description: send out SRI describing the data payload" + NL + " *" + NL + " *  H: structure of type BULKIO::StreamSRI with the SRI for this stream" + NL + " *    hversion" + NL + " *    xstart: start time of the stream" + NL + " *    xdelta: delta between two samples" + NL + " *    xunits: unit types from Platinum specification" + NL + " *    subsize: 0 if the data is one-dimensional" + NL + " *    ystart" + NL + " *    ydelta" + NL + " *    yunits: unit types from Platinum specification" + NL + " *    mode: 0-scalar, 1-complex" + NL + " *    streamID: stream identifier" + NL + " *    sequence<CF::DataType> keywords: unconstrained sequence of key-value pairs for additional description" + NL + " *" + NL + " *  T: structure of type BULKIO::PrecisionUTCTime with the Time for this stream" + NL + " *    tcmode: timecode mode" + NL + " *    tcstatus: timecode status" + NL + " *    toff: Fractional sample offset" + NL + " *    twsec" + NL + " *    tfsec" + NL + " */" + NL + "void BULKIO_dataSDDS_Out_i::pushSRI(const BULKIO::StreamSRI& H, const BULKIO::PrecisionUTCTime& T)" + NL + "{" + NL + "" + NL + "" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "" + NL + "    if (active) {" + NL + "        std::vector < std::pair < BULKIO::dataSDDS_var, std::string > >::iterator i;" + NL + "        for (i = outConnections.begin(); i != outConnections.end(); ++i) {" + NL + "            try {" + NL + "                ((*i).first)->pushSRI(H, T);" + NL + "            } catch(...) {" + NL + "                //RESOLVE LOG_ERROR(BULKIO_dataSDDS_Out_i,\"Call to pushSRI by BULKIO_dataSDDS_Out_i failed\");" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    currentSRIs[std::string(H.streamID)] = std::make_pair(H, T);" + NL + "    refreshSRI = false;" + NL + "" + NL + "    return;" + NL + "}" + NL + NL;
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = "_";
  protected final String TEXT_13 = "_Out_i::";
  protected final String TEXT_14 = "_";
  protected final String TEXT_15 = "_Out_i(std::string port_name, ";
  protected final String TEXT_16 = "_base *_parent) :" + NL + "Port_Uses_base_impl(port_name)" + NL + "{" + NL + "    parent = static_cast<";
  protected final String TEXT_17 = "_i *> (_parent);" + NL + "    recConnectionsRefresh = false;" + NL + "    recConnections.length(0);" + NL + "}" + NL;
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = "_Out_i::~";
  protected final String TEXT_21 = "_";
  protected final String TEXT_22 = "_Out_i()" + NL + "{" + NL + "}" + NL + "" + NL + "/*" + NL + " * pushSRI" + NL + " *     description: send out SRI describing the data payload" + NL + " *" + NL + " *  H: structure of type BULKIO::StreamSRI with the SRI for this stream" + NL + " *    hversion" + NL + " *    xstart: start time of the stream" + NL + " *    xdelta: delta between two samples" + NL + " *    xunits: unit types from Platinum specification" + NL + " *    subsize: 0 if the data is one-dimensional" + NL + " *    ystart" + NL + " *    ydelta" + NL + " *    yunits: unit types from Platinum specification" + NL + " *    mode: 0-scalar, 1-complex" + NL + " *    streamID: stream identifier" + NL + " *    sequence<CF::DataType> keywords: unconstrained sequence of key-value pairs for additional description" + NL + " */" + NL + "void ";
  protected final String TEXT_23 = "_";
  protected final String TEXT_24 = "_Out_i::pushSRI(const BULKIO::StreamSRI& H)" + NL + "{" + NL + "" + NL + "" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "" + NL + "    if (active) {" + NL + "        std::vector < std::pair < ";
  protected final String TEXT_25 = "::";
  protected final String TEXT_26 = "_var, std::string > >::iterator i;" + NL + "        for (i = outConnections.begin(); i != outConnections.end(); ++i) {" + NL + "            try {" + NL + "                ((*i).first)->pushSRI(H);" + NL + "            } catch(...) {" + NL + "                //RESOLVE LOG_ERROR(";
  protected final String TEXT_27 = "_";
  protected final String TEXT_28 = "_Out_i, \"Call to pushSRI by ";
  protected final String TEXT_29 = "_";
  protected final String TEXT_30 = "_Out_i failed\");" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    currentSRIs[std::string(H.streamID)] = H;" + NL + "    refreshSRI = false;" + NL + "" + NL + "    return;" + NL + "}" + NL + NL;
  protected final String TEXT_31 = NL + NL + "// ----------------------------------------------------------------------------------------" + NL + "// ";
  protected final String TEXT_32 = "_";
  protected final String TEXT_33 = "_Out_i definition" + NL + "// ----------------------------------------------------------------------------------------";
  protected final String TEXT_34 = NL;
  protected final String TEXT_35 = "_";
  protected final String TEXT_36 = "_Out_i::";
  protected final String TEXT_37 = "_";
  protected final String TEXT_38 = "_Out_i(std::string port_name, ";
  protected final String TEXT_39 = "_base *_parent) :" + NL + "Port_Uses_base_impl(port_name)" + NL + "{" + NL + "    parent = static_cast<";
  protected final String TEXT_40 = "_i *> (_parent);" + NL + "    recConnectionsRefresh = false;" + NL + "    recConnections.length(0);" + NL + "}" + NL;
  protected final String TEXT_41 = NL;
  protected final String TEXT_42 = "_";
  protected final String TEXT_43 = "_Out_i::~";
  protected final String TEXT_44 = "_";
  protected final String TEXT_45 = "_Out_i()" + NL + "{" + NL + "}" + NL;
  protected final String TEXT_46 = NL;
  protected final String TEXT_47 = NL;
  protected final String TEXT_48 = " ";
  protected final String TEXT_49 = "_";
  protected final String TEXT_50 = "_Out_i::";
  protected final String TEXT_51 = "(";
  protected final String TEXT_52 = ")";
  protected final String TEXT_53 = " ";
  protected final String TEXT_54 = ")";
  protected final String TEXT_55 = ", ";
  protected final String TEXT_56 = NL + "{";
  protected final String TEXT_57 = NL + "    ";
  protected final String TEXT_58 = " retval";
  protected final String TEXT_59 = " = ";
  protected final String TEXT_60 = ";";
  protected final String TEXT_61 = NL + "    std::vector < std::pair < ";
  protected final String TEXT_62 = "::";
  protected final String TEXT_63 = "_var, std::string > >::iterator i;" + NL + "" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL;
  protected final String TEXT_64 = NL + "    Sequence_";
  protected final String TEXT_65 = "_";
  protected final String TEXT_66 = ".length(";
  protected final String TEXT_67 = ".size());" + NL + "    memcpy(&Sequence_";
  protected final String TEXT_68 = "_";
  protected final String TEXT_69 = "[0], &";
  protected final String TEXT_70 = "[0], ";
  protected final String TEXT_71 = ".size() * sizeof(";
  protected final String TEXT_72 = "[0]));" + NL;
  protected final String TEXT_73 = NL + "    if (active) {" + NL + "        for (i = outConnections.begin(); i != outConnections.end(); ++i) {" + NL + "            try {" + NL + "                ";
  protected final String TEXT_74 = "retval =";
  protected final String TEXT_75 = "((*i).first)->";
  protected final String TEXT_76 = "(";
  protected final String TEXT_77 = ");";
  protected final String TEXT_78 = "Sequence_";
  protected final String TEXT_79 = "_";
  protected final String TEXT_80 = ");";
  protected final String TEXT_81 = ", ";
  protected final String TEXT_82 = NL + "            } catch(...) {" + NL + "\t        //RESOLVE LOG_ERROR(";
  protected final String TEXT_83 = "_";
  protected final String TEXT_84 = "_Out_i, \"Call to ";
  protected final String TEXT_85 = " by ";
  protected final String TEXT_86 = "_";
  protected final String TEXT_87 = "_Out_i failed\");" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    return";
  protected final String TEXT_88 = " retval";
  protected final String TEXT_89 = ";" + NL + "}";
  protected final String TEXT_90 = NL;
  protected final String TEXT_91 = " ";
  protected final String TEXT_92 = "_";
  protected final String TEXT_93 = "_Out_i::";
  protected final String TEXT_94 = "()" + NL + "{";
  protected final String TEXT_95 = NL + "    ";
  protected final String TEXT_96 = " retval";
  protected final String TEXT_97 = " = ";
  protected final String TEXT_98 = ";";
  protected final String TEXT_99 = NL + "    std::vector < std::pair < ";
  protected final String TEXT_100 = "::";
  protected final String TEXT_101 = "_var, std::string > >::iterator i;" + NL + "" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "    " + NL + "    if (active) {" + NL + "        for (i = outConnections.begin(); i != outConnections.end(); ++i) {" + NL + "            try {" + NL + "                retval = ((*i).first)->";
  protected final String TEXT_102 = "();" + NL + "            } catch(...) {" + NL + "\t        //RESOLVE LOG_ERROR(";
  protected final String TEXT_103 = "_";
  protected final String TEXT_104 = "_Out_i, \"Call to ";
  protected final String TEXT_105 = " by ";
  protected final String TEXT_106 = "_";
  protected final String TEXT_107 = "_Out_i failed\");" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    return";
  protected final String TEXT_108 = " retval";
  protected final String TEXT_109 = ";" + NL + "}" + NL;
  protected final String TEXT_110 = NL + "void ";
  protected final String TEXT_111 = "_";
  protected final String TEXT_112 = "_Out_i::";
  protected final String TEXT_113 = "(";
  protected final String TEXT_114 = " data)" + NL + "{" + NL + "    std::vector < std::pair < ";
  protected final String TEXT_115 = "::";
  protected final String TEXT_116 = "_var, std::string > >::iterator i;" + NL + "" + NL + "    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "    " + NL + "    if (active) {" + NL + "        for (i = outConnections.begin(); i != outConnections.end(); ++i) {" + NL + "            try {" + NL + "                ((*i).first)->";
  protected final String TEXT_117 = "(data);" + NL + "            } catch(...) {" + NL + "\t        //RESOVLE LOG_ERROR(";
  protected final String TEXT_118 = "_";
  protected final String TEXT_119 = "_Out_i, \"Call to ";
  protected final String TEXT_120 = " by ";
  protected final String TEXT_121 = "_";
  protected final String TEXT_122 = "_Out_i failed\");" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    return;" + NL + "}" + NL;
  protected final String TEXT_123 = NL;

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
    Implementation impl = templ.getImpl();
    ImplementationSettings implSettings = templ.getImplSettings();
    SoftPkg softpkg = (SoftPkg) impl.eContainer();
    String PREFIX = gov.redhawk.ide.codegen.util.CodegenFileHelper.getPreferredFilePrefix(softpkg, implSettings);
    EList<Uses> uses = softpkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses();
    Uses use = null;
    List<IPath> search_paths = Arrays.asList(RedhawkIdeActivator.getDefault().getDefaultIdlIncludePath());
    CppHelper _cppHelper = new CppHelper();
    for (Uses entry : uses) {
        String intName = entry.getRepID();
        if (intName.equals(templ.getPortRepId())) {
            use = entry;
            break;
        }
    }

    if (templ.isGenClassImpl() && (!use.getRepID().equals("IDL:ExtendedEvent/MessageEvent:1.0"))) {
        Interface iface = IdlUtil.getInstance().getInterface(search_paths, use.getRepID().split(":")[1], true);
        if (iface != null) {
            String nameSpace = iface.getNameSpace();
            String interfaceName = iface.getName();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_4);
    
            if ("BULKIO".equals(nameSpace)) {

    
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


    stringBuffer.append(TEXT_5);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_7);
    
            if ("dataSDDS".equals(interfaceName)) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_10);
    
            } else {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_30);
    
            } // end else (if dataSDDS)

    
            } else {

    stringBuffer.append(TEXT_31);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_45);
    
            for (Operation op : iface.getOperations()) {
                int numParams = op.getParams().size();
                int numVector = 0;
                ArrayList<String> vectorList = new ArrayList<String>();

    stringBuffer.append(TEXT_46);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.varReturnValue(op.getCxxReturnType(), op.getReturnType()));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_51);
    
                if (numParams == 0) { 
    stringBuffer.append(TEXT_52);
    
                }
                for (int i = 0; i < numParams; i++) {
                    String iteratorBase = _cppHelper.getBaseSequenceMapping(op.getParams().get(i).getCxxType());
                    if (iteratorBase.length() > 11) {
                        if (iteratorBase.substring(0, 11).equals("std::vector")) {
                            numVector++;
                            vectorList.add(op.getParams().get(i).getName());
                        }
                    }
                
    stringBuffer.append(_cppHelper.getCppMapping(op.getParams().get(i).getCxxType()));
    
                
    stringBuffer.append(TEXT_53);
    stringBuffer.append(op.getParams().get(i).getName());
    
                    if (i == (numParams - 1)) {
                        
    stringBuffer.append(TEXT_54);
    
                    } else {
                        
    stringBuffer.append(TEXT_55);
    
                    }
                } // end for params

    stringBuffer.append(TEXT_56);
    
                if (!"void".equals(op.getCxxReturnType())) {
                    String initialValue = _cppHelper.getInitialValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength());

    stringBuffer.append(TEXT_57);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.varReturnValue(op.getCxxReturnType(), op.getReturnType()));
    stringBuffer.append(TEXT_58);
    if (!"".equals(initialValue)) {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(initialValue);
    }
    stringBuffer.append(TEXT_60);
    
                }

    stringBuffer.append(TEXT_61);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_63);
    
                    for (int i = 0; i < numVector; i++) {

    stringBuffer.append(TEXT_64);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(vectorList.get(i));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(vectorList.get(i));
    stringBuffer.append(TEXT_70);
    stringBuffer.append(vectorList.get(i));
    stringBuffer.append(TEXT_71);
    stringBuffer.append(vectorList.get(i));
    stringBuffer.append(TEXT_72);
    
                    }

    stringBuffer.append(TEXT_73);
      if (!"void".equals(op.getCxxReturnType())) {
    stringBuffer.append(TEXT_74);
    } else {}
    stringBuffer.append(TEXT_75);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_76);
    if (numParams == 0) {
    stringBuffer.append(TEXT_77);
    
                }
                for (int j = 0; j < numParams; j++) {
                    String paramName = op.getParams().get(j).getName();
                    boolean vectorParam = false;
                    for (int i = 0; i < numVector; i++) {
                        if (paramName.equals(vectorList.get(i))) {
                    
    stringBuffer.append(TEXT_78);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(i);
    
                            vectorParam = true;
                            break;
                        }
                    }
                    if (!vectorParam) {
                
    stringBuffer.append(paramName);
    
                    }
                    if (j == (numParams - 1)) {
                        
    stringBuffer.append(TEXT_80);
    
                    } else {
                        
    stringBuffer.append(TEXT_81);
    
                    }
                } // end for params

    stringBuffer.append(TEXT_82);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_87);
     if (!"void".equals(op.getCxxReturnType())) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_89);
    
            } // end for Operations

            for (Attribute op : iface.getAttributes()) {

    stringBuffer.append(TEXT_90);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.varReturnValue(op.getCxxReturnType(), op.getReturnType()));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_94);
    
                if (!"void".equals(op.getCxxReturnType())) {
                    String initialValue = _cppHelper.getInitialValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength());

    stringBuffer.append(TEXT_95);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.varReturnValue(op.getCxxReturnType(), op.getReturnType()));
    stringBuffer.append(TEXT_96);
    if (!"".equals(initialValue)) {
    stringBuffer.append(TEXT_97);
    stringBuffer.append(initialValue);
    }
    stringBuffer.append(TEXT_98);
    
                }

    stringBuffer.append(TEXT_99);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_107);
     if (!"void".equals(op.getCxxReturnType())) {
    stringBuffer.append(TEXT_108);
    }
    stringBuffer.append(TEXT_109);
    
                if (!op.isReadonly()) {

    stringBuffer.append(TEXT_110);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(_cppHelper.getCppMapping(op.getCxxType()));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_122);
    
                } // end if readonly
            } // end for attributes

            }
        // end if interfaces
        } else {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + use.getRepID()));
        }

    } 

    stringBuffer.append(TEXT_123);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE