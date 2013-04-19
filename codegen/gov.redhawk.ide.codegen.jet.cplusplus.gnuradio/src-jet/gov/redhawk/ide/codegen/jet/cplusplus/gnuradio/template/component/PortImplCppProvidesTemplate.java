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
import java.util.Arrays;
import java.util.List;
import mil.jpeojtrs.sca.scd.Provides;
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
public class PortImplCppProvidesTemplate
{

  protected static String nl;
  public static synchronized PortImplCppProvidesTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PortImplCppProvidesTemplate result = new PortImplCppProvidesTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// ----------------------------------------------------------------------------------------" + NL + "// ";
  protected final String TEXT_2 = "_";
  protected final String TEXT_3 = "_In_i definition" + NL + "// ----------------------------------------------------------------------------------------";
  protected final String TEXT_4 = NL + "BULKIO_dataSDDS_In_i::BULKIO_dataSDDS_In_i(std::string port_name, ";
  protected final String TEXT_5 = "_base *_parent) : Port_Provides_base_impl(port_name)" + NL + "{" + NL + "    parent = static_cast<";
  protected final String TEXT_6 = "_i *> (_parent);" + NL + "}" + NL + "" + NL + "BULKIO_dataSDDS_In_i::~BULKIO_dataSDDS_In_i()" + NL + "{" + NL + "}" + NL + "" + NL + "BULKIO::PortStatistics * BULKIO_dataSDDS_In_i::statistics()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(statUpdateLock);" + NL + "    BULKIO::PortStatistics_var recStat = new BULKIO::PortStatistics(stats.retrieve());" + NL + "    return recStat._retn();" + NL + "}" + NL + "" + NL + "BULKIO::PortUsageType BULKIO_dataSDDS_In_i::state()" + NL + "{" + NL + "    if (attachedStreamMap.size() == 0) {" + NL + "        return BULKIO::IDLE;" + NL + "    } else if (attachedStreamMap.size() == 1) {" + NL + "        return BULKIO::BUSY;" + NL + "    } else {" + NL + "        return BULKIO::ACTIVE;" + NL + "    }" + NL + "}" + NL + "" + NL + "BULKIO::StreamSRISequence * BULKIO_dataSDDS_In_i::attachedSRIs()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(sriUpdateLock);" + NL + "    BULKIO::StreamSRISequence_var sris = new BULKIO::StreamSRISequence();" + NL + "    sris->length(currentHs.size());" + NL + "    std::map<std::string, std::pair<BULKIO::StreamSRI, BULKIO::PrecisionUTCTime> >::iterator sriIter;" + NL + "    unsigned int idx = 0;" + NL + "" + NL + "    sriIter = currentHs.begin();" + NL + "    while (sriIter != currentHs.end()) {" + NL + "        sris[idx++] = (*sriIter).second.first;" + NL + "        sriIter++;" + NL + "    }" + NL + "    return sris._retn();" + NL + "}" + NL + "" + NL + "void BULKIO_dataSDDS_In_i::pushSRI(const BULKIO::StreamSRI& H, const BULKIO::PrecisionUTCTime& T)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(sriUpdateLock);" + NL + "    bool foundSRI = false;" + NL + "    BULKIO::StreamSRI tmpH = H;" + NL + "    std::map<std::string, std::pair<BULKIO::StreamSRI, BULKIO::PrecisionUTCTime> >::iterator sriIter;" + NL + "" + NL + "    sriIter = currentHs.begin();" + NL + "    while (sriIter != currentHs.end()) {" + NL + "        if (strcmp(H.streamID, (*sriIter).first.c_str()) == 0) {" + NL + "            foundSRI = true;" + NL + "            break;" + NL + "        }" + NL + "        sriIter++;" + NL + "    }" + NL + "    if (!foundSRI) {" + NL + "        currentHs.insert(std::make_pair(CORBA::string_dup(H.streamID), std::make_pair(H, T)));" + NL + "    } else {" + NL + "        (*sriIter).second = std::make_pair(H, T);" + NL + "    }" + NL + "}" + NL + "" + NL + "BULKIO::SDDSStreamDefinition* BULKIO_dataSDDS_In_i::getStreamDefinition(const char* attachId)" + NL + "{" + NL + "    std::map<std::string, BULKIO::SDDSStreamDefinition*>::iterator portIter2;" + NL + "    portIter2 = attachedStreamMap.begin();" + NL + "    // use: attachedPorts[(*portIter).first] :instead" + NL + "    while (portIter2 != attachedStreamMap.end()) {" + NL + "        if (strcmp((*portIter2).first.c_str(), attachId) == 0) {" + NL + "            return (*portIter2).second;" + NL + "        }" + NL + "        portIter2++;" + NL + "    }" + NL + "    return NULL;" + NL + "}" + NL + "" + NL + "char* BULKIO_dataSDDS_In_i::getUser(const char* attachId)" + NL + "{" + NL + "    std::map<std::string, std::string>::iterator portIter2;" + NL + "    portIter2 = attachedUsers.begin();" + NL + "    while (portIter2 != attachedUsers.end()) {" + NL + "        if (strcmp((*portIter2).first.c_str(), attachId) == 0) {" + NL + "            return CORBA::string_dup((*portIter2).second.c_str());" + NL + "        }" + NL + "        portIter2++;" + NL + "    }" + NL + "    return NULL;" + NL + "}" + NL + "" + NL + "BULKIO::dataSDDS::InputUsageState BULKIO_dataSDDS_In_i::usageState()" + NL + "{" + NL + "    if (attachedStreamMap.size() == 0) {" + NL + "        return BULKIO::dataSDDS::IDLE;" + NL + "    } else if (attachedStreamMap.size() == 1) {" + NL + "        return BULKIO::dataSDDS::BUSY;" + NL + "    } else {" + NL + "        return BULKIO::dataSDDS::ACTIVE;" + NL + "    }" + NL + "}" + NL + "" + NL + "BULKIO::SDDSStreamSequence* BULKIO_dataSDDS_In_i::attachedStreams()" + NL + "{" + NL + "    BULKIO::SDDSStreamSequence* seq = new BULKIO::SDDSStreamSequence();" + NL + "    seq->length(attachedStreamMap.size());" + NL + "    std::map<std::string, BULKIO::SDDSStreamDefinition*>::iterator portIter2;" + NL + "    portIter2 = attachedStreamMap.begin();" + NL + "    unsigned int i = 0;" + NL + "    while (portIter2 != attachedStreamMap.end()) {" + NL + "        (*seq)[i++] = *((*portIter2).second);" + NL + "        portIter2++;" + NL + "    }" + NL + "    return seq;" + NL + "}" + NL + "" + NL + "BULKIO::StringSequence* BULKIO_dataSDDS_In_i::attachmentIds()" + NL + "{" + NL + "    BULKIO::StringSequence* seq = new BULKIO::StringSequence();" + NL + "    seq->length(attachedStreamMap.size());" + NL + "    std::map<std::string, BULKIO::SDDSStreamDefinition*>::iterator portIter2;" + NL + "    portIter2 = attachedStreamMap.begin();" + NL + "    unsigned int i = 0;" + NL + "    while (portIter2 != attachedStreamMap.end()) {" + NL + "        (*seq)[i++] = CORBA::string_dup((*portIter2).first.c_str());" + NL + "        portIter2++;" + NL + "    }" + NL + "    return seq;" + NL + "}" + NL + "" + NL + "char* BULKIO_dataSDDS_In_i::attach(const BULKIO::SDDSStreamDefinition& stream, const char* userid) throw (BULKIO::dataSDDS::AttachError, BULKIO::dataSDDS::StreamInputError)" + NL + "{" + NL + "    std::string attachId;" + NL + "" + NL + "    attachId = parent->attach(stream, userid);" + NL + "    attachedStreamMap.insert(std::make_pair(attachId, new BULKIO::SDDSStreamDefinition(stream)));" + NL + "    attachedUsers.insert(std::make_pair(attachId, std::string(userid)));" + NL + "" + NL + "    return CORBA::string_dup(attachId.c_str());" + NL + "}" + NL + "" + NL + "void BULKIO_dataSDDS_In_i::detach(const char* attachId)" + NL + "{" + NL + "    parent->detach(attachId);" + NL + "    attachedStreamMap.erase(attachId);" + NL + "    attachedUsers.erase(attachId);" + NL + "}" + NL;
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = "_";
  protected final String TEXT_9 = "_In_i::";
  protected final String TEXT_10 = "_";
  protected final String TEXT_11 = "_In_i(std::string port_name,  SRIListener *listener , SRICompare cmpFunc) : " + NL + "Port_Provides_base_impl(port_name)" + NL + "{" + NL + "    queueSem = new queueSemaphore(100);" + NL + "    blocking = false;" + NL + "    breakBlock = false;" + NL + "    lastQueueSize=0;" + NL + "    sriCmp = cmpFunc;" + NL + "    sriListener = listener;" + NL + "" + NL + "}" + NL;
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = "_";
  protected final String TEXT_14 = "_In_i::~";
  protected final String TEXT_15 = "_";
  protected final String TEXT_16 = "_In_i()" + NL + "{" + NL + "    block();" + NL + "    while (workQueue.size() != 0) {" + NL + "        dataTransfer *tmp = workQueue.front();" + NL + "        workQueue.pop_front();" + NL + "        delete tmp;" + NL + "    }" + NL + "}" + NL + "" + NL + "BULKIO::PortStatistics * ";
  protected final String TEXT_17 = "_";
  protected final String TEXT_18 = "_In_i::statistics()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "    BULKIO::PortStatistics_var recStat = new BULKIO::PortStatistics(stats.retrieve());" + NL + "    // NOTE: You must delete the object that this function returns!" + NL + "    return recStat._retn();" + NL + "}" + NL + "" + NL + "BULKIO::PortUsageType ";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = "_In_i::state()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "    if (workQueue.size() == queueSem->getMaxValue()) {" + NL + "        return BULKIO::BUSY;" + NL + "    } else if (workQueue.size() == 0) {" + NL + "        return BULKIO::IDLE;" + NL + "    } else {" + NL + "        return BULKIO::ACTIVE;" + NL + "    }" + NL + "" + NL + "    return BULKIO::BUSY;" + NL + "}" + NL + "" + NL + "BULKIO::StreamSRISequence * ";
  protected final String TEXT_21 = "_";
  protected final String TEXT_22 = "_In_i::activeSRIs()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(sriUpdateLock);" + NL + "    BULKIO::StreamSRISequence seq_rtn;" + NL + "    RH_SRIMap::iterator currH;" + NL + "    int i = 0;" + NL + "    for (currH = currentHs.begin(); currH != currentHs.end(); currH++) {" + NL + "    \ti++;" + NL + "    \tseq_rtn.length(i);" + NL + "    \tseq_rtn[i-1] = currH->second.first;" + NL + "    }" + NL + "    BULKIO::StreamSRISequence_var retSRI = new BULKIO::StreamSRISequence(seq_rtn);" + NL + "" + NL + "    // NOTE: You must delete the object that this function returns!" + NL + "    return retSRI._retn();" + NL + "}" + NL + "" + NL + "int ";
  protected final String TEXT_23 = "_";
  protected final String TEXT_24 = "_In_i::getMaxQueueDepth()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "    return queueSem->getMaxValue();" + NL + "}" + NL + "" + NL + "int ";
  protected final String TEXT_25 = "_";
  protected final String TEXT_26 = "_In_i::getCurrentQueueDepth()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "    return workQueue.size();" + NL + "}" + NL + "" + NL + "void ";
  protected final String TEXT_27 = "_";
  protected final String TEXT_28 = "_In_i::setMaxQueueDepth(int newDepth)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "    queueSem->setMaxValue(newDepth);" + NL + "}" + NL + "" + NL + "void ";
  protected final String TEXT_29 = "_";
  protected final String TEXT_30 = "_In_i::pushSRI(const BULKIO::StreamSRI& H)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(sriUpdateLock);" + NL + "    BULKIO::StreamSRI tmpH = H;" + NL + "    RH_SRIMap::iterator currH = currentHs.find(std::string(H.streamID));" + NL + "    if (currH == currentHs.end()) {" + NL + "    \tif ( sriListener ) (*sriListener)( this, tmpH);" + NL + "    \tcurrentHs[std::string(H.streamID)] = std::make_pair(tmpH, true);" + NL + "        if (H.blocking) {" + NL + "            boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "            blocking = true;" + NL + "            queueSem->setCurrValue(workQueue.size());" + NL + "        }" + NL + "    } else {" + NL + "    \tif ( sriCmp ) {" + NL + "\t    \tif (!sriCmp(tmpH, currH->second.first)) {" + NL + "\t            currentHs[std::string(H.streamID)] = std::make_pair(tmpH, true);" + NL + "\t            if (H.blocking) {" + NL + "\t                boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "\t                blocking = true;" + NL + "\t                queueSem->setCurrValue(workQueue.size());" + NL + "\t            }" + NL + "\t    \t}" + NL + "    \t}" + NL + "    }" + NL + "}";
  protected final String TEXT_31 = NL;
  protected final String TEXT_32 = NL;
  protected final String TEXT_33 = " ";
  protected final String TEXT_34 = "_";
  protected final String TEXT_35 = "_In_i::";
  protected final String TEXT_36 = "(";
  protected final String TEXT_37 = ")";
  protected final String TEXT_38 = " ";
  protected final String TEXT_39 = ")";
  protected final String TEXT_40 = ", ";
  protected final String TEXT_41 = NL + "{" + NL + "    if (queueSem->getMaxValue() == 0) {" + NL + "        return;" + NL + "    }    " + NL + "    BULKIO::StreamSRI tmpH = {1, 0.0, 1.0, 1, 0, 0.0, 0.0, 0, 0, streamID, false, 0};" + NL + "    bool sriChanged = false;" + NL + "    bool portBlocking = false;" + NL + "" + NL + "    RH_SRIMap::iterator currH;" + NL + "    {" + NL + "        boost::mutex::scoped_lock lock(sriUpdateLock);" + NL + "" + NL + "        currH = currentHs.find(std::string(streamID));" + NL + "        if (currH != currentHs.end()) {" + NL + "            tmpH = currH->second.first;" + NL + "            sriChanged = currH->second.second;" + NL + "            currentHs[streamID] = std::make_pair(currH->second.first, false);" + NL + "        }" + NL + "        portBlocking = blocking;" + NL + "    }" + NL + "" + NL + "    if(portBlocking) {" + NL + "        queueSem->incr();" + NL + "        boost::mutex::scoped_lock lock(dataBufferLock);";
  protected final String TEXT_42 = NL + "        stats.update(";
  protected final String TEXT_43 = "1";
  protected final String TEXT_44 = ".length()";
  protected final String TEXT_45 = ", workQueue.size()/queueSem->getMaxValue(), EOS, streamID, false);";
  protected final String TEXT_46 = NL + "        ";
  protected final String TEXT_47 = "_";
  protected final String TEXT_48 = "_In_i::dataTransfer *tmpIn = new ";
  protected final String TEXT_49 = "_";
  protected final String TEXT_50 = "_In_i::dataTransfer(";
  protected final String TEXT_51 = ", T, EOS, streamID, tmpH, sriChanged, false);";
  protected final String TEXT_52 = NL + "        stats.update(strlen(";
  protected final String TEXT_53 = "), workQueue.size()/queueSem->getMaxValue(), EOS, streamID, false);";
  protected final String TEXT_54 = NL + "        ";
  protected final String TEXT_55 = "_";
  protected final String TEXT_56 = "_In_i::dataTransfer *tmpIn = new  ";
  protected final String TEXT_57 = "_";
  protected final String TEXT_58 = "_In_i::dataTransfer(";
  protected final String TEXT_59 = ", EOS, streamID, tmpH, sriChanged, false);";
  protected final String TEXT_60 = NL + "        workQueue.push_back(tmpIn);" + NL + "        dataAvailable.notify_all();" + NL + "    } else {" + NL + "        boost::mutex::scoped_lock lock(dataBufferLock);" + NL + "        bool flushToReport = false;" + NL + "        if (workQueue.size() == queueSem->getMaxValue()) { // reached maximum queue depth - flush the queue" + NL + "            flushToReport = true;";
  protected final String TEXT_61 = NL + "            ";
  protected final String TEXT_62 = "_";
  protected final String TEXT_63 = "_In_i::dataTransfer *tmp;" + NL + "            while (workQueue.size() != 0) {" + NL + "                tmp = workQueue.front();" + NL + "                workQueue.pop_front();" + NL + "                delete tmp;" + NL + "            }" + NL + "        }";
  protected final String TEXT_64 = NL + "        stats.update(";
  protected final String TEXT_65 = "1";
  protected final String TEXT_66 = ".length()";
  protected final String TEXT_67 = ", workQueue.size()/queueSem->getMaxValue(), EOS, streamID, flushToReport);";
  protected final String TEXT_68 = NL + "        ";
  protected final String TEXT_69 = "_";
  protected final String TEXT_70 = "_In_i::dataTransfer *tmpIn = new ";
  protected final String TEXT_71 = "_";
  protected final String TEXT_72 = "_In_i::dataTransfer(";
  protected final String TEXT_73 = ", T, EOS, streamID, tmpH, sriChanged, flushToReport);";
  protected final String TEXT_74 = NL + "        stats.update(strlen(";
  protected final String TEXT_75 = "), workQueue.size()/queueSem->getMaxValue(), EOS, streamID, flushToReport);";
  protected final String TEXT_76 = NL + "        ";
  protected final String TEXT_77 = "_";
  protected final String TEXT_78 = "_In_i::dataTransfer *tmpIn = new ";
  protected final String TEXT_79 = "_";
  protected final String TEXT_80 = "_In_i::dataTransfer(";
  protected final String TEXT_81 = ", EOS, streamID, tmpH, sriChanged, flushToReport);";
  protected final String TEXT_82 = NL + "        workQueue.push_back(tmpIn);" + NL + "        dataAvailable.notify_all();" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_83 = NL + NL + "void ";
  protected final String TEXT_84 = "_";
  protected final String TEXT_85 = "_In_i::block()" + NL + "{" + NL + "    breakBlock = true;" + NL + "    queueSem->release();" + NL + "    dataAvailable.notify_all();" + NL + "}" + NL + "" + NL + "void ";
  protected final String TEXT_86 = "_";
  protected final String TEXT_87 = "_In_i::unblock()" + NL + "{" + NL + "    breakBlock = false;" + NL + "}" + NL + "" + NL + "bool ";
  protected final String TEXT_88 = "_";
  protected final String TEXT_89 = "_In_i::blocked()" + NL + "{" + NL + "    return breakBlock;" + NL + "}" + NL + "" + NL + "/*" + NL + " * getPacket" + NL + " *     description: retrieve data from the provides (input) port" + NL + " *" + NL + " *  timeout: the amount of time to wait for data before a NULL is returned." + NL + " *           Use 0.0 for non-blocking and -1 for blocking." + NL + " */";
  protected final String TEXT_90 = NL;
  protected final String TEXT_91 = "_";
  protected final String TEXT_92 = "_In_i::dataTransfer *";
  protected final String TEXT_93 = "_";
  protected final String TEXT_94 = "_In_i::getPacket(float timeout, std::string streamID )" + NL + "{" + NL + "    if (breakBlock) {" + NL + "        return NULL;" + NL + "    }" + NL + "    //if (workQueue.size() == 0) {" + NL + "    if ( (workQueue.size() == 0 ) or (( workQueue.size() != 0 ) and ( workQueue.size() == lastQueueSize )) ){" + NL + "        if (timeout == 0.0) {" + NL + "            lastQueueSize = workQueue.size();" + NL + "            return NULL;" + NL + "        } else if (timeout > 0){" + NL + "             uint64_t secs = (unsigned long)(trunc(timeout));" + NL + "            uint64_t msecs = (unsigned long)((timeout - secs) * 1e6);" + NL + "            boost::system_time to_time  = boost::get_system_time() + boost::posix_time::seconds(secs) + boost::posix_time::microseconds(msecs);" + NL + "            boost::unique_lock< boost::mutex > lock(dataAvailableMutex);" + NL + "            if ( dataAvailable.timed_wait( lock, to_time) == false ) {" + NL + "                return NULL;" + NL + "            }" + NL + "" + NL + "            if (breakBlock) {" + NL + "                return NULL;" + NL + "            }" + NL + "        } else {" + NL + "            boost::unique_lock< boost::mutex > lock(dataAvailableMutex);" + NL + "            dataAvailable.wait(lock);" + NL + "            if (breakBlock) {" + NL + "                return NULL;" + NL + "            }" + NL + "        }       " + NL + "        " + NL + "    }" + NL + "    boost::mutex::scoped_lock lock1(dataBufferLock);";
  protected final String TEXT_95 = NL + "    ";
  protected final String TEXT_96 = "_";
  protected final String TEXT_97 = "_In_i::dataTransfer *tmp = NULL;" + NL + "    if ( streamID == \"\" ){" + NL + "        tmp = workQueue.front();" + NL + "        workQueue.pop_front();" + NL + "    } else {" + NL + "        std::deque< dataTransfer * >::iterator p = workQueue.begin();" + NL + "        while ( p != workQueue.end() ) {" + NL + "            if ( (*p)->streamID == streamID ) {" + NL + "                tmp = *p;" + NL + "                workQueue.erase(p);" + NL + "                break;" + NL + "            }" + NL + "            p++;" + NL + "       }" + NL + "    " + NL + "    }" + NL + "    " + NL + "    if ( tmp == NULL ) {" + NL + "        lastQueueSize = workQueue.size();" + NL + "        return NULL;" + NL + "    }" + NL + "    " + NL + "    boost::mutex::scoped_lock lock2(sriUpdateLock);" + NL + "    if (tmp->EOS) {" + NL + "\t    RH_SRIMap::iterator target = currentHs.find(std::string(tmp->streamID));" + NL + "        if (target != currentHs.end()) {" + NL + "            if (target->second.first.blocking) {" + NL + "                RH_SRIMap::iterator currH;" + NL + "                bool keepBlocking = false;" + NL + "                for (currH = currentHs.begin(); currH != currentHs.end(); currH++) {" + NL + "                    if (currH->second.first.blocking) {" + NL + "                        keepBlocking = true;" + NL + "                        break;" + NL + "                    }" + NL + "                }" + NL + "" + NL + "                if (!keepBlocking) {" + NL + "                    queueSem->setCurrValue(0);" + NL + "                    blocking = false;" + NL + "                }" + NL + "            }" + NL + "            currentHs.erase(target);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    if (blocking) {" + NL + "        queueSem->decr();" + NL + "    }" + NL + "    " + NL + "    lastQueueSize=0;" + NL + "    return tmp;" + NL + "}";
  protected final String TEXT_98 = NL + NL + "// ----------------------------------------------------------------------------------------" + NL + "// ";
  protected final String TEXT_99 = "_";
  protected final String TEXT_100 = "_In_i definition" + NL + "// ----------------------------------------------------------------------------------------";
  protected final String TEXT_101 = NL;
  protected final String TEXT_102 = "_";
  protected final String TEXT_103 = "_In_i::";
  protected final String TEXT_104 = "_";
  protected final String TEXT_105 = "_In_i(std::string port_name, SRIListener *listener, SRICompare cmpFunc ) : " + NL + "Port_Provides_base_impl(port_name)" + NL + "{" + NL + "    parent = static_cast<";
  protected final String TEXT_106 = "_i *> (_parent);" + NL + "}" + NL;
  protected final String TEXT_107 = NL;
  protected final String TEXT_108 = "_";
  protected final String TEXT_109 = "_In_i::~";
  protected final String TEXT_110 = "_";
  protected final String TEXT_111 = "_In_i()" + NL + "{" + NL + "}" + NL;
  protected final String TEXT_112 = NL;
  protected final String TEXT_113 = NL;
  protected final String TEXT_114 = " ";
  protected final String TEXT_115 = "_";
  protected final String TEXT_116 = "_In_i::";
  protected final String TEXT_117 = "(";
  protected final String TEXT_118 = ")";
  protected final String TEXT_119 = " ";
  protected final String TEXT_120 = ")";
  protected final String TEXT_121 = ", ";
  protected final String TEXT_122 = NL + "{" + NL + "    boost::mutex::scoped_lock lock(portAccess);";
  protected final String TEXT_123 = NL + "    ";
  protected final String TEXT_124 = " tmpVal";
  protected final String TEXT_125 = " = ";
  protected final String TEXT_126 = ";";
  protected final String TEXT_127 = NL + "    // TODO: Fill in this function";
  protected final String TEXT_128 = NL + NL + "    return ";
  protected final String TEXT_129 = "CORBA::string_dup(";
  protected final String TEXT_130 = "tmpVal";
  protected final String TEXT_131 = ")";
  protected final String TEXT_132 = ";";
  protected final String TEXT_133 = NL + "}" + NL;
  protected final String TEXT_134 = NL;
  protected final String TEXT_135 = " ";
  protected final String TEXT_136 = "_";
  protected final String TEXT_137 = "_In_i::";
  protected final String TEXT_138 = "()" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(portAccess);" + NL + "    ";
  protected final String TEXT_139 = NL + "    char";
  protected final String TEXT_140 = NL + "    ";
  protected final String TEXT_141 = " tmpVal";
  protected final String TEXT_142 = "[255] = {0};";
  protected final String TEXT_143 = " = 0";
  protected final String TEXT_144 = ";";
  protected final String TEXT_145 = NL + "    // TODO: Fill in this function" + NL + "" + NL + "    return ";
  protected final String TEXT_146 = "CORBA::string_dup(";
  protected final String TEXT_147 = "tmpVal";
  protected final String TEXT_148 = ")";
  protected final String TEXT_149 = ";";
  protected final String TEXT_150 = NL + "}" + NL;
  protected final String TEXT_151 = NL + "void ";
  protected final String TEXT_152 = "_";
  protected final String TEXT_153 = "_In_i::";
  protected final String TEXT_154 = "(";
  protected final String TEXT_155 = " data)" + NL + "{" + NL + "    boost::mutex::scoped_lock lock(portAccess);" + NL + "    " + NL + "    // TODO: Fill in this function" + NL + "}" + NL;
  protected final String TEXT_156 = NL;

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
    SoftPkg softpkg = (SoftPkg) impl.eContainer();
    String PREFIX = gov.redhawk.ide.codegen.util.CodegenFileHelper.getPreferredFilePrefix(softpkg, implSettings);
    EList<Provides> provides = softpkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getProvides();
    Provides pro = null;
    List<IPath> search_paths = Arrays.asList(RedhawkIdeActivator.getDefault().getDefaultIdlIncludePath());
    CppHelper _cppHelper = new CppHelper();
    for (Provides entry : provides) {
        String intName = entry.getRepID();
        if (intName.equals(templ.getPortRepId())) {
            pro = entry;
            break;
        }
    }

    if ((pro != null) && templ.isGenClassImpl() && (!pro.getRepID().equals("IDL:ExtendedEvent/MessageEvent:1.0"))) {
        Interface iface = IdlUtil.getInstance().getInterface(search_paths, pro.getRepID().split(":")[1], true);
        if (iface != null) {
            String nameSpace = iface.getNameSpace();
            String interfaceName = iface.getName();
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


    stringBuffer.append(TEXT_1);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_3);
    
            if ("dataSDDS".equals(interfaceName)) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_6);
    
            } else {

            boolean hasPushPacketCall = false;
            boolean hasPushPacketXMLCall = false;
            boolean hasPushPacketFileCall = false;
            for (Operation op : iface.getOperations()) {
                int numParams = op.getParams().size();
                if ("pushPacket".equals(op.getName()) && "dataFile".equals(interfaceName)) {
                    hasPushPacketFileCall = true;
                } else if ("pushPacket".equals(op.getName()) && (numParams == 4)) {
                    hasPushPacketCall = true;
                } else if ("pushPacket".equals(op.getName()) && "dataXML".equals(interfaceName)) {
                    hasPushPacketXMLCall = true;
                }
            }

    stringBuffer.append(TEXT_7);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(interfaceName);
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
    
            for (Operation op : iface.getOperations()) {
                int numParams = op.getParams().size();
                if (!"pushPacket".equals(op.getName())) {
                    continue;
                }

    stringBuffer.append(TEXT_31);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.pointerReturnValue(op.getCxxReturnType(), op.getReturnType(), 
op.isCxxReturnTypeVariableLength()));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_36);
    if (op.getParams().size() == 0) {
    stringBuffer.append(TEXT_37);
    }
                for (int i = 0; i < numParams; i++) {
                
    stringBuffer.append(op.getParams().get(i).getCxxType());
    
                
    stringBuffer.append(TEXT_38);
    stringBuffer.append(op.getParams().get(i).getName());
    
                    if (i == (numParams - 1)) {
                        
    stringBuffer.append(TEXT_39);
    
                    } else {
                        
    stringBuffer.append(TEXT_40);
    
                    }
                } // end for params

    stringBuffer.append(TEXT_41);
    
        if (!hasPushPacketXMLCall) {

    stringBuffer.append(TEXT_42);
    if ("dataFile".equals(interfaceName)) {
    stringBuffer.append(TEXT_43);
    } else {
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_51);
    
        } else {

    stringBuffer.append(TEXT_52);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_59);
    
        }

    stringBuffer.append(TEXT_60);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_63);
    
        if (!hasPushPacketXMLCall) {

    stringBuffer.append(TEXT_64);
    if ("dataFile".equals(interfaceName)) {
    stringBuffer.append(TEXT_65);
    } else {
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_66);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_73);
    
        } else {

    stringBuffer.append(TEXT_74);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_81);
    
        }

    stringBuffer.append(TEXT_82);
    
            } // end for operations

    stringBuffer.append(TEXT_83);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_97);
    
            }

    
            } else {

    stringBuffer.append(TEXT_98);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_111);
    
                for (Operation op : iface.getOperations()) {
                    int numParams = op.getParams().size();

    stringBuffer.append(TEXT_112);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.pointerReturnValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength()));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_117);
    if (op.getParams().size() == 0) {
    stringBuffer.append(TEXT_118);
    }
                    for (int i = 0; i < numParams; i++) {
                
                
    stringBuffer.append(op.getParams().get(i).getCxxType());
    
                
    stringBuffer.append(TEXT_119);
    stringBuffer.append(op.getParams().get(i).getName());
    
                        if (i == (numParams - 1)) {
                        
    stringBuffer.append(TEXT_120);
    
                        } else {
                        
    stringBuffer.append(TEXT_121);
    
                        }
                    } // end for params

    stringBuffer.append(TEXT_122);
    
                    if (!"void".equals(op.getCxxReturnType())) {
                        String initialValue = _cppHelper.getInitialValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength());

    stringBuffer.append(TEXT_123);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.pointerReturnValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength()));
    stringBuffer.append(TEXT_124);
    if (!"".equals(initialValue)) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(initialValue);
    }
    stringBuffer.append(TEXT_126);
    
                    }

    stringBuffer.append(TEXT_127);
    
                    if (!"void".equals(op.getCxxReturnType())) {

    stringBuffer.append(TEXT_128);
     if ("char*".equals(op.getCxxReturnType())) { 
    stringBuffer.append(TEXT_129);
    }
    stringBuffer.append(TEXT_130);
     if ("char*".equals(op.getCxxReturnType())) { 
    stringBuffer.append(TEXT_131);
     }
    stringBuffer.append(TEXT_132);
    
                    }

    stringBuffer.append(TEXT_133);
    
                } // end for operations
                for (Attribute op : iface.getAttributes()) {

    stringBuffer.append(TEXT_134);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.pointerReturnValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength()));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_138);
    
                    if (!"void".equals(op.getCxxReturnType())) {

    if ("char*".equals(op.getCxxReturnType())) {
    stringBuffer.append(TEXT_139);
    } else {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.pointerReturnValue(op.getCxxReturnType(), op.getReturnType(), op.isCxxReturnTypeVariableLength()));
    }
    stringBuffer.append(TEXT_141);
    if ("char*".equals(op.getCxxReturnType())) {
    stringBuffer.append(TEXT_142);
    } else {
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    
                    }
                    if (!"void".equals(op.getCxxReturnType())) {

    stringBuffer.append(TEXT_145);
     if ("char*".equals(op.getCxxReturnType())) { 
    stringBuffer.append(TEXT_146);
    }
    stringBuffer.append(TEXT_147);
     if ("char*".equals(op.getCxxReturnType())) { 
    stringBuffer.append(TEXT_148);
     }
    stringBuffer.append(TEXT_149);
    
                    }

    stringBuffer.append(TEXT_150);
    
                    if (!op.isReadonly()) {

    stringBuffer.append(TEXT_151);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(op.getCxxType());
    stringBuffer.append(TEXT_155);
    
                    } // end if readonly
                } // end for attributes
            } // end if !bulkio
        // end if interfaces
        } else {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + pro.getRepID()));
        }
    } // end for providesList

    stringBuffer.append(TEXT_156);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE