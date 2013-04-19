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
import gov.redhawk.ide.codegen.cplusplus.CppHelper;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.CplusplusJetGeneratorPlugin;
import gov.redhawk.ide.codegen.jet.cplusplus.CppProperties;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.idl.Attribute;
import gov.redhawk.ide.idl.IdlUtil;
import gov.redhawk.ide.idl.Interface;
import gov.redhawk.ide.idl.Operation;
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
public class PortImplHUsesTemplate
{

  protected static String nl;
  public static synchronized PortImplHUsesTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    PortImplHUsesTemplate result = new PortImplHUsesTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "#include \"ossie/MessageInterface.h\"";
  protected final String TEXT_2 = NL + "#include \"COS/";
  protected final String TEXT_3 = ".hh\"";
  protected final String TEXT_4 = NL + "#include \"";
  protected final String TEXT_5 = ".h\"";
  protected final String TEXT_6 = NL + "#include <set>";
  protected final String TEXT_7 = " " + NL + "#include \"struct_props.h\" ";
  protected final String TEXT_8 = NL + "// ----------------------------------------------------------------------------------------" + NL + "// ";
  protected final String TEXT_9 = "_";
  protected final String TEXT_10 = "_Out_i declaration" + NL + "// ----------------------------------------------------------------------------------------";
  protected final String TEXT_11 = NL + "class BULKIO_dataSDDS_Out_i : public Port_Uses_base_impl, public virtual POA_BULKIO::UsesPortStatisticsProvider" + NL + "{" + NL + "//RESOVLE ENABLE_LOGGING;" + NL + "public:" + NL + "    BULKIO_dataSDDS_Out_i(std::string port_name, ";
  protected final String TEXT_12 = "_base *_parent);" + NL + "" + NL + "    ~BULKIO_dataSDDS_Out_i();" + NL + "" + NL + "    class linkStatistics" + NL + "    {" + NL + "        public:" + NL + "            struct statPoint {" + NL + "                unsigned int elements;" + NL + "                float queueSize;" + NL + "                double secs;" + NL + "                double usecs;" + NL + "            };" + NL + "" + NL + "            linkStatistics() {" + NL + "                bitSize = 8.0;" + NL + "                historyWindow = 10;" + NL + "                activeStreamIDs.resize(0);" + NL + "                receivedStatistics_idx = 0;" + NL + "                receivedStatistics.resize(historyWindow);" + NL + "                runningStats.elementsPerSecond = -1.0;" + NL + "                runningStats.bitsPerSecond = -1.0;" + NL + "                runningStats.callsPerSecond = -1.0;" + NL + "                runningStats.averageQueueDepth = -1.0;" + NL + "                runningStats.streamIDs.length(0);" + NL + "                runningStats.timeSinceLastCall = -1;" + NL + "                enabled = true;" + NL + "            };" + NL + "" + NL + "            void setBitSize(double _bitSize) {" + NL + "                bitSize = _bitSize;" + NL + "            }" + NL + "" + NL + "            void setEnabled(bool enableStats) {" + NL + "                enabled = enableStats;" + NL + "            }" + NL + "" + NL + "            void update(unsigned int elementsReceived, float queueSize, bool EOS, std::string streamID) {" + NL + "                if (!enabled) {" + NL + "                    return;" + NL + "                }" + NL + "                struct timeval tv;" + NL + "                struct timezone tz;" + NL + "                gettimeofday(&tv, &tz);" + NL + "                receivedStatistics[receivedStatistics_idx].elements = elementsReceived;" + NL + "                receivedStatistics[receivedStatistics_idx].queueSize = queueSize;" + NL + "                receivedStatistics[receivedStatistics_idx].secs = tv.tv_sec;" + NL + "                receivedStatistics[receivedStatistics_idx++].usecs = tv.tv_usec;" + NL + "                receivedStatistics_idx = receivedStatistics_idx % historyWindow;" + NL + "                if (!EOS) {" + NL + "                    std::list<std::string>::iterator p = activeStreamIDs.begin();" + NL + "                    bool foundStreamID = false;" + NL + "                    while (p != activeStreamIDs.end()) {" + NL + "                        if (*p == streamID) {" + NL + "                            foundStreamID = true;" + NL + "                            break;" + NL + "                        }" + NL + "                        p++;" + NL + "                    }" + NL + "                    if (!foundStreamID) {" + NL + "                        activeStreamIDs.push_back(streamID);" + NL + "                    }" + NL + "                } else {" + NL + "                    std::list<std::string>::iterator p = activeStreamIDs.begin();" + NL + "                    while (p != activeStreamIDs.end()) {" + NL + "                        if (*p == streamID) {" + NL + "                            activeStreamIDs.erase(p);" + NL + "                            break;" + NL + "                        }" + NL + "                        p++;" + NL + "                    }" + NL + "                }" + NL + "            };" + NL + "" + NL + "            BULKIO::PortStatistics retrieve() {" + NL + "                if (!enabled) {" + NL + "                    return runningStats;" + NL + "                }" + NL + "                struct timeval tv;" + NL + "                struct timezone tz;" + NL + "                gettimeofday(&tv, &tz);" + NL + "" + NL + "                int idx = (receivedStatistics_idx == 0) ? (historyWindow - 1) : (receivedStatistics_idx - 1);" + NL + "                double front_sec = receivedStatistics[idx].secs;" + NL + "                double front_usec = receivedStatistics[idx].usecs;" + NL + "                double secDiff = tv.tv_sec - receivedStatistics[receivedStatistics_idx].secs;" + NL + "                double usecDiff = (tv.tv_usec - receivedStatistics[receivedStatistics_idx].usecs) / ((double)1e6);" + NL + "" + NL + "                double totalTime = secDiff + usecDiff;" + NL + "                double totalData = 0;" + NL + "                float queueSize = 0;" + NL + "                int startIdx = (receivedStatistics_idx + 1) % historyWindow;" + NL + "                for (int i = startIdx; i != receivedStatistics_idx; ) {" + NL + "                    totalData += receivedStatistics[i].elements;" + NL + "                    queueSize += receivedStatistics[i].queueSize;" + NL + "                    i = (i + 1) % historyWindow;" + NL + "                }" + NL + "                runningStats.bitsPerSecond = ((totalData * bitSize) / totalTime);" + NL + "                runningStats.elementsPerSecond = (totalData / totalTime);" + NL + "                runningStats.averageQueueDepth = (queueSize / historyWindow);" + NL + "                runningStats.callsPerSecond = (double(historyWindow - 1) / totalTime);" + NL + "                runningStats.timeSinceLastCall = (((double)tv.tv_sec) - front_sec) + (((double)tv.tv_usec - front_usec) / ((double)1e6));" + NL + "                unsigned int streamIDsize = activeStreamIDs.size();" + NL + "                std::list< std::string >::iterator p = activeStreamIDs.begin();" + NL + "                runningStats.streamIDs.length(streamIDsize);" + NL + "                for (unsigned int i = 0; i < streamIDsize; i++) {" + NL + "                    if (p == activeStreamIDs.end()) {" + NL + "                        break;" + NL + "                    }" + NL + "                    runningStats.streamIDs[i] = CORBA::string_dup((*p).c_str());" + NL + "                    p++;" + NL + "                }" + NL + "                return runningStats;" + NL + "            };" + NL + "" + NL + "        protected:" + NL + "            bool enabled;" + NL + "            double bitSize;" + NL + "            BULKIO::PortStatistics runningStats;" + NL + "            std::vector<statPoint> receivedStatistics;" + NL + "            std::list< std::string > activeStreamIDs;" + NL + "            unsigned long historyWindow;" + NL + "            int receivedStatistics_idx;" + NL + "    };" + NL + "" + NL + "    BULKIO::UsesPortStatisticsSequence * statistics()" + NL + "    {" + NL + "        boost::mutex::scoped_lock lock(updatingPortsLock);" + NL + "        BULKIO::UsesPortStatisticsSequence_var recStat = new BULKIO::UsesPortStatisticsSequence();" + NL + "        recStat->length(outConnections.size());" + NL + "        for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "            recStat[i].connectionId = CORBA::string_dup(outConnections[i].second.c_str());" + NL + "            recStat[i].statistics = stats[outConnections[i].second].retrieve();" + NL + "        }" + NL + "        return recStat._retn();" + NL + "    };" + NL + "" + NL + "    BULKIO::PortUsageType state()" + NL + "    {" + NL + "        boost::mutex::scoped_lock lock(updatingPortsLock);" + NL + "        if (outConnections.size() > 0) {" + NL + "            return BULKIO::ACTIVE;" + NL + "        } else {" + NL + "            return BULKIO::IDLE;" + NL + "        }" + NL + "" + NL + "        return BULKIO::BUSY;" + NL + "    };" + NL + "" + NL + "    void enableStats(bool enable)" + NL + "    {" + NL + "        for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "            stats[outConnections[i].second].setEnabled(enable);" + NL + "        }" + NL + "    };" + NL + "" + NL + "    void setBitSize(double bitSize)" + NL + "    {" + NL + "        for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "            stats[outConnections[i].second].setBitSize(bitSize);" + NL + "        }" + NL + "    };" + NL + "" + NL + "    void updateStats(unsigned int elementsReceived, unsigned int queueSize, bool EOS, std::string streamID)" + NL + "    {" + NL + "        for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "            stats[outConnections[i].second].update(elementsReceived, queueSize, EOS, streamID);" + NL + "        }" + NL + "    };" + NL + "" + NL + "    void connectPort(CORBA::Object_ptr connection, const char* connectionId)" + NL + "    {" + NL + "        boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "        BULKIO::dataSDDS_var port = BULKIO::dataSDDS::_narrow(connection);" + NL + "        if (lastStreamData != NULL) {" + NL + "            // TODO - use the username instead" + NL + "            std::string attachId = port->attach(*lastStreamData, user_id.c_str());" + NL + "            attachedGroup.insert(std::make_pair(attachId, std::make_pair(lastStreamData, user_id)));" + NL + "            attachedPorts.insert(std::make_pair(port, attachId));" + NL + "        }" + NL + "        outConnections.push_back(std::make_pair(port, connectionId));" + NL + "        active = true;" + NL + "\treConnectionsRefresh = true;" + NL + "        refreshSRI = true;" + NL + "    };" + NL + "" + NL + "    void disconnectPort(const char* connectionId)" + NL + "    {" + NL + "        boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "        for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "            if (outConnections[i].second == connectionId) {" + NL + "            \tif (attachedPorts.find(outConnections[i].first) != attachedPorts.end()) {" + NL + "                    outConnections[i].first->detach(attachedPorts[outConnections[i].first].c_str());" + NL + "                }" + NL + "                outConnections.erase(outConnections.begin() + i);" + NL + "                break;" + NL + "            }" + NL + "        }" + NL + "" + NL + "        if (outConnections.size() == 0) {" + NL + "            active = false;" + NL + "        }" + NL + "\treConnectionsRefresh = true;" + NL + "    };" + NL + "" + NL + "    ExtendedCF::UsesConnectionSequence * connections() " + NL + "    {" + NL + "        boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "        if (recConnectionsRefresh) {" + NL + "            recConnections.length(outConnections.size());" + NL + "            for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "                recConnections[i].connectionId = CORBA::string_dup(outConnections[i].second.c_str());" + NL + "                recConnections[i].port = CORBA::Object::_duplicate(outConnections[i].first);" + NL + "            }" + NL + "            recConnectionsRefresh = false;" + NL + "        }" + NL + "        ExtendedCF::UsesConnectionSequence_var retVal = new ExtendedCF::UsesConnectionSequence(recConnections);" + NL + "        return retVal._retn();" + NL + "    };" + NL + "" + NL + "    std::vector< std::pair<BULKIO::dataSDDS_var, std::string> > _getConnections()" + NL + "    {" + NL + "        return outConnections;" + NL + "    };" + NL + "    " + NL + "    void pushSRI(const BULKIO::StreamSRI& H, const BULKIO::PrecisionUTCTime& T);" + NL + "" + NL + "    BULKIO::SDDSStreamDefinition* getStreamDefinition(const char* attachId);" + NL + "" + NL + "    char* getUser(const char* attachId);" + NL + "" + NL + "    BULKIO::dataSDDS::InputUsageState usageState();" + NL + "" + NL + "    BULKIO::SDDSStreamSequence* attachedStreams();" + NL + "" + NL + "    BULKIO::StringSequence* attachmentIds();" + NL + "" + NL + "    char* attach(const BULKIO::SDDSStreamDefinition& stream, const char* userid) throw (BULKIO::dataSDDS::AttachError, BULKIO::dataSDDS::StreamInputError);" + NL + "" + NL + "    void detach(const char* attachId, const char* connectionId);" + NL + "" + NL + "    std::map<std::string, std::pair<BULKIO::StreamSRI, BULKIO::PrecisionUTCTime> > currentSRIs;" + NL + "" + NL + "protected:";
  protected final String TEXT_13 = NL + "    ";
  protected final String TEXT_14 = "_i *parent;" + NL + "    // maps a stream ID to a pair of Stream and userID" + NL + "    std::map<std::string, std::pair<BULKIO::SDDSStreamDefinition*, std::string> > attachedGroup;" + NL + "" + NL + "    BULKIO::SDDSStreamDefinition* lastStreamData;" + NL + "    std::vector < std::pair<BULKIO::dataSDDS_var, std::string> > outConnections;" + NL + "    std::map<BULKIO::dataSDDS::_var_type, std::string> attachedPorts;" + NL + "    std::string user_id;" + NL + "    ExtendedCF::UsesConnectionSequence recConnections;" + NL + "    bool recConnectionsRefresh;" + NL + "    std::map<std::string, linkStatistics> stats;" + NL + "};";
  protected final String TEXT_15 = NL + "class ";
  protected final String TEXT_16 = "_";
  protected final String TEXT_17 = "_Out_i : public MessageSupplierPort" + NL + "{" + NL + "//RESOLVE    ENABLE_LOGGING;" + NL + "    public:";
  protected final String TEXT_18 = NL + "        ";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = "_Out_i(std::string port_name) : MessageSupplierPort(port_name) {" + NL + "        };";
  protected final String TEXT_21 = NL + "        void sendMessage(propertyChange< ";
  protected final String TEXT_22 = " > message) {" + NL + "            CF::Properties outProps;" + NL + "            CORBA::Any data;" + NL + "            outProps.length(1);" + NL + "            outProps[0].id = CORBA::string_dup(message.getId().c_str());" + NL + "            outProps[0].value <<= message;" + NL + "            data <<= outProps;" + NL + "            push(data);" + NL + "        };" + NL + "" + NL + "        void sendMessages(std::vector<propertyChange< ";
  protected final String TEXT_23 = "> > messages) {" + NL + "            CF::Properties outProps;" + NL + "            CORBA::Any data;" + NL + "            outProps.length(messages.size());" + NL + "            for (unsigned int i=0; i<messages.size(); i++) {" + NL + "                outProps[i].id = CORBA::string_dup(messages[i].getId().c_str());" + NL + "                outProps[i].value <<= messages[i];" + NL + "            }" + NL + "            data <<= outProps;" + NL + "            push(data);" + NL + "        };";
  protected final String TEXT_24 = NL + "        void sendMessage(";
  protected final String TEXT_25 = " message) {" + NL + "            CF::Properties outProps;" + NL + "            CORBA::Any data;" + NL + "            outProps.length(1);" + NL + "            outProps[0].id = CORBA::string_dup(message.getId().c_str());" + NL + "            outProps[0].value <<= message;" + NL + "            data <<= outProps;" + NL + "            push(data);" + NL + "        };" + NL + "" + NL + "        void sendMessages(std::vector<";
  protected final String TEXT_26 = "> messages) {" + NL + "            CF::Properties outProps;" + NL + "            CORBA::Any data;" + NL + "            outProps.length(messages.size());" + NL + "            for (unsigned int i=0; i<messages.size(); i++) {" + NL + "                outProps[i].id = CORBA::string_dup(messages[i].getId().c_str());" + NL + "                outProps[i].value <<= messages[i];" + NL + "            }" + NL + "            data <<= outProps;" + NL + "            push(data);" + NL + "        };";
  protected final String TEXT_27 = NL + "};";
  protected final String TEXT_28 = NL + "class ";
  protected final String TEXT_29 = "_";
  protected final String TEXT_30 = "_Out_i : public Port_Uses_base_impl, public ";
  protected final String TEXT_31 = "virtual POA_BULKIO::UsesPortStatisticsProvider";
  protected final String TEXT_32 = "POA_ExtendedCF::QueryablePort";
  protected final String TEXT_33 = NL + "{" + NL + "//RESOLVE    ENABLE_LOGGING;" + NL + "    public:" + NL + "" + NL + "    ";
  protected final String TEXT_34 = NL + "        ";
  protected final String TEXT_35 = "_";
  protected final String TEXT_36 = "_Out_i(std::string port_name, ";
  protected final String TEXT_37 = "_base *_parent);" + NL + "        ~";
  protected final String TEXT_38 = "_";
  protected final String TEXT_39 = "_Out_i();";
  protected final String TEXT_40 = NL + "     typedef ";
  protected final String TEXT_41 = "    RH_TransportType;" + NL + "     typedef ";
  protected final String TEXT_42 = "         RH_NativeType;";
  protected final String TEXT_43 = NL + NL + NL + "        " + NL + "\t\t\t /*" + NL + "\t\t\t  * pushPacket" + NL + "\t\t\t  *     description: push data out of the port" + NL + "\t\t\t  *" + NL + "\t\t\t  *  ";
  protected final String TEXT_44 = ": structure containing the payload to send out" + NL + "\t\t\t  *  T: constant of type BULKIO::PrecisionUTCTime containing the timestamp for the outgoing data." + NL + "\t\t\t  *    tcmode: timecode mode" + NL + "\t\t\t  *    tcstatus: timecode status " + NL + "\t\t\t  *    toff: fractional sample offset" + NL + "\t\t\t  *    twsec: J1970 GMT " + NL + "\t\t\t  *    tfsec: fractional seconds: 0.0 to 1.0" + NL + "\t\t\t  *  EOS: end-of-stream flag" + NL + "\t\t\t  *  streamID: stream identifier" + NL + "\t\t\t  */" + NL + "\t\t\t void pushPacket(";
  protected final String TEXT_45 = " ";
  protected final String TEXT_46 = ", const BULKIO::PrecisionUTCTime& T, bool EOS, const std::string& streamID) {" + NL + "\t\t\t     if (refreshSRI && (currentSRIs.find(streamID) != currentSRIs.end())) {" + NL + "\t\t\t\t pushSRI(currentSRIs[streamID]);" + NL + "\t\t\t     }" + NL + "\t\t\t     boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "\t\t\t     if (active) {" + NL + "\t\t\t\t std::vector < std::pair < ";
  protected final String TEXT_47 = "::";
  protected final String TEXT_48 = "_var, std::string > >::iterator port;" + NL + "\t\t\t\t for (port = outConnections.begin(); port != outConnections.end(); port++) {" + NL + "\t\t\t\t     try {" + NL + "\t\t\t\t\t ((*port).first)->pushPacket(";
  protected final String TEXT_49 = ", T, EOS, streamID.c_str());" + NL + "\t\t\t\t\t stats[(*port).second].update(1, 0, 0, streamID);" + NL + "\t\t\t\t     } catch(...) {" + NL + "\t\t\t\t\t //RESOVLE LOG_ERROR(";
  protected final String TEXT_50 = "_";
  protected final String TEXT_51 = "_Out_i, \"Call to pushPacket by ";
  protected final String TEXT_52 = "_";
  protected final String TEXT_53 = "_Out_i failed\");" + NL + "\t\t\t\t     }" + NL + "\t\t\t\t }" + NL + "\t\t\t     }" + NL + "\t\t\t };" + NL + "\t\t\t ";
  protected final String TEXT_54 = NL + "        " + NL + "\t\t\t /*" + NL + "\t\t\t  * pushPacket" + NL + "\t\t\t  *     description: push data out of the port" + NL + "\t\t\t  *" + NL + "\t\t\t  *  ";
  protected final String TEXT_55 = ": structure containing the payload to send out" + NL + "\t\t\t  *  EOS: end-of-stream flag" + NL + "\t\t\t  *  streamID: stream identifier" + NL + "\t\t\t  */" + NL + "\t\t\t void pushPacket(";
  protected final String TEXT_56 = " ";
  protected final String TEXT_57 = ", bool EOS, const std::string& streamID) {" + NL + "\t\t\t     if (refreshSRI) {" + NL + "\t\t\t\t if (currentSRIs.find(streamID) != currentSRIs.end()) {" + NL + "\t\t\t\t     pushSRI(currentSRIs[streamID]);" + NL + "\t\t\t\t }" + NL + "\t\t\t     }" + NL + "\t\t\t     boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "\t\t\t     if (active) {" + NL + "\t\t\t\t std::vector < std::pair < ";
  protected final String TEXT_58 = "::";
  protected final String TEXT_59 = "_var, std::string > >::iterator port;" + NL + "\t\t\t\t for (port = outConnections.begin(); port != outConnections.end(); port++) {" + NL + "\t\t\t\t     try {" + NL + "\t\t\t\t\t ((*port).first)->pushPacket(";
  protected final String TEXT_60 = ", EOS, streamID.c_str());" + NL + "\t\t\t\t\t stats[(*port).second].update(strlen(";
  protected final String TEXT_61 = "), 0, 0, streamID);" + NL + "\t\t\t\t     } catch(...) {" + NL + "\t\t\t\t\t //RESOLVE LOG_ERROR(";
  protected final String TEXT_62 = "_";
  protected final String TEXT_63 = "_Out_i, \"Call to pushPacket by ";
  protected final String TEXT_64 = "_";
  protected final String TEXT_65 = "_Out_i failed\");" + NL + "\t\t\t\t     }" + NL + "\t\t\t\t }" + NL + "\t\t\t     }" + NL + "\t\t\t };";
  protected final String TEXT_66 = NL + NL + "\t\t/*" + NL + "\t\t * pushPacket" + NL + "\t\t *     description: push data out of the port" + NL + "\t\t *" + NL + "\t\t *  data: structure containing the payload to send out" + NL + "\t\t *  T: constant of type BULKIO::PrecisionUTCTime containing the timestamp for the outgoing data." + NL + "\t\t *    tcmode: timecode mode" + NL + "\t\t *    tcstatus: timecode status " + NL + "\t\t *    toff: fractional sample offset" + NL + "\t\t *    twsec: J1970 GMT " + NL + "\t\t *    tfsec: fractional seconds: 0.0 to 1.0" + NL + "\t\t *  EOS: end-of-stream flag" + NL + "\t\t *  streamID: stream identifier" + NL + "\t\t */" + NL + "       template <typename ALLOCATOR>";
  protected final String TEXT_67 = NL + "        void pushPacket(";
  protected final String TEXT_68 = " data, BULKIO::PrecisionUTCTime& T, bool EOS, const std::string& streamID) {";
  protected final String TEXT_69 = NL + "        void pushPacket(";
  protected final String TEXT_70 = " data, BULKIO::PrecisionUTCTime& T, bool EOS, const std::string& streamID) {";
  protected final String TEXT_71 = NL + "\t\t\t     if (refreshSRI) {" + NL + "\t\t\t\t if (currentSRIs.find(streamID) != currentSRIs.end()) {" + NL + "\t\t\t\t     pushSRI(currentSRIs[streamID]);" + NL + "\t\t\t\t }" + NL + "\t\t\t     }" + NL + "\t\t\t     boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "\t\t\t     // Magic is below, make a new sequence using the data from the Iterator" + NL + "\t\t\t     // as the data for the sequence.  The 'false' at the end is whether or not" + NL + "\t\t\t     // CORBA is allowed to delete the buffer when the sequence is destroyed." + NL + "\t\t\t     ";
  protected final String TEXT_72 = " seq = ";
  protected final String TEXT_73 = "(data.size(), data.size(), ";
  protected final String TEXT_74 = "(CORBA::ULong*)";
  protected final String TEXT_75 = "(CORBA::Char*)";
  protected final String TEXT_76 = "&(data[0]), false);" + NL + "\t\t\t     if (active) {" + NL + "\t\t\t\t std::vector < std::pair < ";
  protected final String TEXT_77 = "::";
  protected final String TEXT_78 = "_var, std::string > >::iterator port;" + NL + "\t\t\t\t for (port = outConnections.begin(); port != outConnections.end(); port++) {" + NL + "\t\t\t\t     try {" + NL + "\t\t\t\t\t ((*port).first)->pushPacket(seq, T, EOS, streamID.c_str());" + NL + "\t\t\t\t\t stats[(*port).second].update(data.size(), 0, 0, streamID);" + NL + "\t\t\t\t     } catch(...) {" + NL + "\t\t\t\t\t //RESOLVE LOG_ERROR(";
  protected final String TEXT_79 = "_";
  protected final String TEXT_80 = "_Out_i, \"Call to pushPacket by ";
  protected final String TEXT_81 = "_";
  protected final String TEXT_82 = "_Out_i failed\");" + NL + "\t\t\t\t     }" + NL + "\t\t\t\t }" + NL + "\t\t\t     }" + NL + "\t\t\t };" + NL + "\t\t\t ";
  protected final String TEXT_83 = NL + NL + "\t     ";
  protected final String TEXT_84 = " ";
  protected final String TEXT_85 = "(";
  protected final String TEXT_86 = ");";
  protected final String TEXT_87 = "::iterator begin, ";
  protected final String TEXT_88 = "::iterator end,";
  protected final String TEXT_89 = " ";
  protected final String TEXT_90 = ");";
  protected final String TEXT_91 = ", ";
  protected final String TEXT_92 = NL + "        ";
  protected final String TEXT_93 = " ";
  protected final String TEXT_94 = "();" + NL;
  protected final String TEXT_95 = NL + "        void ";
  protected final String TEXT_96 = "(";
  protected final String TEXT_97 = " data);" + NL;
  protected final String TEXT_98 = NL + "        class linkStatistics" + NL + "        {" + NL + "            public:" + NL + "                struct statPoint {" + NL + "                    unsigned int elements;" + NL + "                    float queueSize;" + NL + "                    double secs;" + NL + "                    double usecs;" + NL + "                };" + NL + "                " + NL + "                linkStatistics() {" + NL + "                    bitSize = sizeof(";
  protected final String TEXT_99 = ") * 8.0;" + NL + "                    historyWindow = 10;" + NL + "                    activeStreamIDs.resize(0);" + NL + "                    receivedStatistics_idx = 0;" + NL + "                    receivedStatistics.resize(historyWindow);" + NL + "                    runningStats.elementsPerSecond = -1.0;" + NL + "                    runningStats.bitsPerSecond = -1.0;" + NL + "                    runningStats.callsPerSecond = -1.0;" + NL + "                    runningStats.averageQueueDepth = -1.0;" + NL + "                    runningStats.streamIDs.length(0);" + NL + "                    runningStats.timeSinceLastCall = -1;" + NL + "                    enabled = true;" + NL + "                };" + NL + "" + NL + "                void setEnabled(bool enableStats) {" + NL + "                    enabled = enableStats;" + NL + "                }" + NL + "" + NL + "                void update(unsigned int elementsReceived, float queueSize, bool EOS, std::string streamID) {" + NL + "                    if (!enabled) {" + NL + "                        return;" + NL + "                    }" + NL + "                    struct timeval tv;" + NL + "                    struct timezone tz;" + NL + "                    gettimeofday(&tv, &tz);" + NL + "                    receivedStatistics[receivedStatistics_idx].elements = elementsReceived;" + NL + "                    receivedStatistics[receivedStatistics_idx].queueSize = queueSize;" + NL + "                    receivedStatistics[receivedStatistics_idx].secs = tv.tv_sec;" + NL + "                    receivedStatistics[receivedStatistics_idx++].usecs = tv.tv_usec;" + NL + "                    receivedStatistics_idx = receivedStatistics_idx % historyWindow;" + NL + "                    if (!EOS) {" + NL + "                        std::list<std::string>::iterator p = activeStreamIDs.begin();" + NL + "                        bool foundStreamID = false;" + NL + "                        while (p != activeStreamIDs.end()) {" + NL + "                            if (*p == streamID) {" + NL + "                                foundStreamID = true;" + NL + "                                break;" + NL + "                            }" + NL + "                            p++;" + NL + "                        }" + NL + "                        if (!foundStreamID) {" + NL + "                            activeStreamIDs.push_back(streamID);" + NL + "                        }" + NL + "                    } else {" + NL + "                        std::list<std::string>::iterator p = activeStreamIDs.begin();" + NL + "                        while (p != activeStreamIDs.end()) {" + NL + "                            if (*p == streamID) {" + NL + "                                activeStreamIDs.erase(p);" + NL + "                                break;" + NL + "                            }" + NL + "                            p++;" + NL + "                        }" + NL + "                    }" + NL + "                };" + NL + "" + NL + "                BULKIO::PortStatistics retrieve() {" + NL + "                    if (!enabled) {" + NL + "                        return runningStats;" + NL + "                    }" + NL + "                    struct timeval tv;" + NL + "                    struct timezone tz;" + NL + "                    gettimeofday(&tv, &tz);" + NL + "" + NL + "                    int idx = (receivedStatistics_idx == 0) ? (historyWindow - 1) : (receivedStatistics_idx - 1);" + NL + "                    double front_sec = receivedStatistics[idx].secs;" + NL + "                    double front_usec = receivedStatistics[idx].usecs;" + NL + "                    double secDiff = tv.tv_sec - receivedStatistics[receivedStatistics_idx].secs;" + NL + "                    double usecDiff = (tv.tv_usec - receivedStatistics[receivedStatistics_idx].usecs) / ((double)1e6);" + NL + "" + NL + "                    double totalTime = secDiff + usecDiff;" + NL + "                    double totalData = 0;" + NL + "                    float queueSize = 0;" + NL + "                    int startIdx = (receivedStatistics_idx + 1) % historyWindow;" + NL + "                    for (int i = startIdx; i != receivedStatistics_idx; ) {" + NL + "                        totalData += receivedStatistics[i].elements;" + NL + "                        queueSize += receivedStatistics[i].queueSize;" + NL + "                        i = (i + 1) % historyWindow;" + NL + "                    }" + NL + "                    runningStats.bitsPerSecond = ((totalData * bitSize) / totalTime);" + NL + "                    runningStats.elementsPerSecond = (totalData / totalTime);" + NL + "                    runningStats.averageQueueDepth = (queueSize / historyWindow);" + NL + "                    runningStats.callsPerSecond = (double(historyWindow - 1) / totalTime);" + NL + "                    runningStats.timeSinceLastCall = (((double)tv.tv_sec) - front_sec) + (((double)tv.tv_usec - front_usec) / ((double)1e6));" + NL + "                    unsigned int streamIDsize = activeStreamIDs.size();" + NL + "                    std::list< std::string >::iterator p = activeStreamIDs.begin();" + NL + "                    runningStats.streamIDs.length(streamIDsize);" + NL + "                    for (unsigned int i = 0; i < streamIDsize; i++) {" + NL + "                        if (p == activeStreamIDs.end()) {" + NL + "                            break;" + NL + "                        }" + NL + "                        runningStats.streamIDs[i] = CORBA::string_dup((*p).c_str());" + NL + "                        p++;" + NL + "                    }" + NL + "                    return runningStats;" + NL + "                };" + NL + "" + NL + "            protected:" + NL + "                bool enabled;" + NL + "                double bitSize;" + NL + "                BULKIO::PortStatistics runningStats;" + NL + "                std::vector<statPoint> receivedStatistics;" + NL + "                std::list< std::string > activeStreamIDs;" + NL + "                unsigned long historyWindow;" + NL + "                int receivedStatistics_idx;" + NL + "        };" + NL + "" + NL + "        BULKIO::UsesPortStatisticsSequence * statistics()" + NL + "        {" + NL + "            boost::mutex::scoped_lock lock(updatingPortsLock);" + NL + "            BULKIO::UsesPortStatisticsSequence_var recStat = new BULKIO::UsesPortStatisticsSequence();" + NL + "            recStat->length(outConnections.size());" + NL + "            for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "                recStat[i].connectionId = CORBA::string_dup(outConnections[i].second.c_str());" + NL + "                recStat[i].statistics = stats[outConnections[i].second].retrieve();" + NL + "            }" + NL + "            return recStat._retn();" + NL + "        };" + NL + "" + NL + "        BULKIO::PortUsageType state()" + NL + "        {" + NL + "            boost::mutex::scoped_lock lock(updatingPortsLock);" + NL + "            if (outConnections.size() > 0) {" + NL + "                return BULKIO::ACTIVE;" + NL + "            } else {" + NL + "                return BULKIO::IDLE;" + NL + "            }" + NL + "" + NL + "            return BULKIO::BUSY;" + NL + "        };" + NL + "        " + NL + "        void enableStats(bool enable)" + NL + "        {" + NL + "            for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "                stats[outConnections[i].second].setEnabled(enable);" + NL + "            }" + NL + "        };" + NL;
  protected final String TEXT_100 = NL + NL + "        ExtendedCF::UsesConnectionSequence * connections() " + NL + "        {" + NL + "            boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "            if (recConnectionsRefresh) {" + NL + "                recConnections.length(outConnections.size());" + NL + "                for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "                    recConnections[i].connectionId = CORBA::string_dup(outConnections[i].second.c_str());" + NL + "                    recConnections[i].port = CORBA::Object::_duplicate(outConnections[i].first);" + NL + "                }" + NL + "                recConnectionsRefresh = false;" + NL + "            }" + NL + "            ExtendedCF::UsesConnectionSequence_var retVal = new ExtendedCF::UsesConnectionSequence(recConnections);" + NL + "            // NOTE: You must delete the object that this function returns!" + NL + "            return retVal._retn();" + NL + "        };" + NL + "" + NL + "        void connectPort(CORBA::Object_ptr connection, const char* connectionId)" + NL + "        {" + NL + "            boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in";
  protected final String TEXT_101 = NL + "            ";
  protected final String TEXT_102 = "::";
  protected final String TEXT_103 = "_var port = ";
  protected final String TEXT_104 = "::";
  protected final String TEXT_105 = "::_narrow(connection);" + NL + "            outConnections.push_back(std::make_pair(port, connectionId));" + NL + "            active = true;" + NL + "            recConnectionsRefresh = true;";
  protected final String TEXT_106 = NL + "            refreshSRI = true;";
  protected final String TEXT_107 = NL + "        };" + NL + "" + NL + "        void disconnectPort(const char* connectionId)" + NL + "        {" + NL + "            boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in" + NL + "            for (unsigned int i = 0; i < outConnections.size(); i++) {" + NL + "                if (outConnections[i].second == connectionId) {" + NL + "                    outConnections.erase(outConnections.begin() + i);" + NL + "                    break;" + NL + "                }" + NL + "            }" + NL + "" + NL + "            if (outConnections.size() == 0) {" + NL + "                active = false;" + NL + "            }" + NL + "            recConnectionsRefresh = true;" + NL + "        };" + NL + "" + NL + "        std::vector< std::pair<";
  protected final String TEXT_108 = "::";
  protected final String TEXT_109 = "_var, std::string> > _getConnections()" + NL + "        {" + NL + "            return outConnections;" + NL + "        };";
  protected final String TEXT_110 = NL + "        std::map<std::string, BULKIO::StreamSRI> currentSRIs;";
  protected final String TEXT_111 = NL + NL + "    protected:";
  protected final String TEXT_112 = NL + "        ";
  protected final String TEXT_113 = "_i *parent;" + NL + "        std::vector < std::pair<";
  protected final String TEXT_114 = "::";
  protected final String TEXT_115 = "_var, std::string> > outConnections;" + NL + "        ExtendedCF::UsesConnectionSequence recConnections;" + NL + "        bool recConnectionsRefresh;";
  protected final String TEXT_116 = NL + "        std::map<std::string, linkStatistics> stats;";
  protected final String TEXT_117 = NL + "    ";
  protected final String TEXT_118 = NL + "    ";
  protected final String TEXT_119 = " Sequence_";
  protected final String TEXT_120 = "_";
  protected final String TEXT_121 = "; ";
  protected final String TEXT_122 = NL + "};";
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
    SoftPkg softPkg = (SoftPkg) impl.eContainer();
    String PREFIX = gov.redhawk.ide.codegen.util.CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
    EList<Uses> uses = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses();
    List<CppProperties.Property> properties = CppProperties.getProperties(softPkg);
    Uses use = null;
    List<IPath> search_paths = Arrays.asList(RedhawkIdeActivator.getDefault().getDefaultIdlIncludePath());
    CppHelper _cppHelper = new CppHelper();
    boolean containsBULKIO = false;
    for (Uses entry : uses) {
        String intName = entry.getRepID();
        if (intName.equals(templ.getPortRepId())) {
            use = entry;
            if (templ.isGenSupport()) {
                Interface intf = IdlUtil.getInstance().getInterface(search_paths, intName.split(":")[1], true);
                if (intf == null) {
                    throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + intName));
                }
                if (intf.getNameSpace().equals("BULKIO")) {
                	containsBULKIO = true;
                }
                if (intf.getNameSpace().equals("ExtendedEvent")) {

    stringBuffer.append(TEXT_1);
    
                	continue;
                }
                if (intf.getFullPath().contains("/COS/")) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(intf.getFilename());
    stringBuffer.append(TEXT_3);
    
                } else {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(intf.getNameSpace() + "/" + intf.getFilename());
    stringBuffer.append(TEXT_5);
    
            	}
            }
            break;
        }
    }
    if (containsBULKIO) {

    stringBuffer.append(TEXT_6);
    
    }

	for (CppProperties.Property prop : properties) { 
		if (prop.getKinds().indexOf("message") != -1) { 

    stringBuffer.append(TEXT_7);
     
			break; 
		} 
	} 

    if (templ.isGenClassDef()) {
        Interface intf = IdlUtil.getInstance().getInterface(search_paths, use.getRepID().split(":")[1], true);
        if (intf == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + use.getRepID()));
        }

        String nameSpace = intf.getNameSpace();
        String interfaceName = intf.getName();
        boolean pushPacketCall = false;
        boolean isBULKIO = "BULKIO".equals(nameSpace);
        String dataTransfer = "";
        String tmpDataTransfer = "";
        String rawTransferType = "char";

    stringBuffer.append(TEXT_8);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_10);
    
        if (isBULKIO && "dataSDDS".equals(interfaceName)) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_14);
    
        } else if (nameSpace.equals("ExtendedEvent") && interfaceName.equals("MessageEvent")) {

    stringBuffer.append(TEXT_15);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_20);
    
		for (CppProperties.Property prop : properties) {
			if (prop.getKinds().indexOf("message") != -1) {
        		if (prop.getKinds().indexOf("event") != -1) {         

    stringBuffer.append(TEXT_21);
    stringBuffer.append(prop.getCppType());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(prop.getCppType());
    stringBuffer.append(TEXT_23);
    
				} else {

    stringBuffer.append(TEXT_24);
    stringBuffer.append(prop.getCppType());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(prop.getCppType());
    stringBuffer.append(TEXT_26);
    
				}
			}
		}

    stringBuffer.append(TEXT_27);
    
        } else {
            String ppDataTransfer = "";

    stringBuffer.append(TEXT_28);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_30);
    if (isBULKIO) {
    stringBuffer.append(TEXT_31);
    } else {
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_39);
    
     for (Operation op : intf.getOperations()) {
	 int numParams = op.getParams().size();
	 if ("pushPacket".equals(op.getName())) {
	     if (numParams == 4) {
		 ppDataTransfer = _cppHelper.getCppMapping(op.getParams().get(0).getCxxType());
		 if (ppDataTransfer.startsWith("std::vector")) {
		     if (ppDataTransfer.endsWith("& ")) {
			 rawTransferType = ppDataTransfer.substring(12, ppDataTransfer.length() - 3);
		     } else { 
			 rawTransferType = ppDataTransfer.substring(12, ppDataTransfer.length() - 2);
		     }
		 } else if ("dataFile".equals(interfaceName)) {
		     ppDataTransfer = "char";
		 }
	     }
	     dataTransfer = op.getParams().get(0).getCxxType();
	     if (dataTransfer.endsWith("&")) {
		 tmpDataTransfer = dataTransfer.substring(6, dataTransfer.length()-1);
	     } else {
		 tmpDataTransfer = dataTransfer.substring(6, dataTransfer.length());
	     }


	     //raw type is CORBA::XXX or unsigned char for some reason
	     // map CORBA Transfer type to Native type
	     String nativeType="Int8";
	     if (rawTransferType.endsWith("Float")) {
		 nativeType = "Float";
	     } else if (rawTransferType.endsWith("Octet")) {
		 nativeType = "UInt8";
	     } else if (rawTransferType.endsWith("Char")) {
		 nativeType = "Char";
	     } else if (rawTransferType.endsWith("unsigned char")) {
		 nativeType = "UInt8";
	     } else if (rawTransferType.endsWith("UShort")) {
		 nativeType = "UInt16";
	     } else if (rawTransferType.endsWith("Short")) {
		 nativeType = "Int16";
	     } else if (rawTransferType.endsWith("ULongLong")) {
		 nativeType = "UInt64";
	     } else if (rawTransferType.endsWith("ULong")) {
		 nativeType = "UInt32";
	     } else if (rawTransferType.endsWith("LongLong")) {
		 nativeType = "Int64";
	     } else if (rawTransferType.endsWith("Long")) {
		 nativeType = "Int32";
	     } else if (rawTransferType.endsWith("Double")) {
		 nativeType = "Double";
	     }

    stringBuffer.append(TEXT_40);
    stringBuffer.append(rawTransferType);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(nativeType);
    stringBuffer.append(TEXT_42);
    

	     pushPacketCall = true;
	     if ("dataFile".equals(interfaceName)) {

    stringBuffer.append(TEXT_43);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(op.getParams().get(0).getCxxType());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_53);
    
			     continue;
		     } else if ("dataXML".equals(interfaceName)) {
			 
    stringBuffer.append(TEXT_54);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(op.getParams().get(0).getCxxType());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(op.getParams().get(0).getName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_65);
    
			     continue;
		     } else {

    stringBuffer.append(TEXT_66);
    
         if ("dataChar".equals(interfaceName)) {

    stringBuffer.append(TEXT_67);
    stringBuffer.append(_cppHelper.vectorize("std::vector<char>&"));
    stringBuffer.append(TEXT_68);
    
         } else {

    stringBuffer.append(TEXT_69);
    stringBuffer.append(_cppHelper.vectorize(ppDataTransfer.trim()));
    stringBuffer.append(TEXT_70);
    
        }

    stringBuffer.append(TEXT_71);
    stringBuffer.append(tmpDataTransfer);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(tmpDataTransfer);
    stringBuffer.append(TEXT_73);
    
									     if (tmpDataTransfer.contains("UlongSequence")) {
										 
    stringBuffer.append(TEXT_74);
    
									     } else if (tmpDataTransfer.contains("PortTypes::CharSequence")) {
										 
    stringBuffer.append(TEXT_75);
    
									     }
									     
    stringBuffer.append(TEXT_76);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_82);
    
			     continue;
		     }
	 } // end if opName = pushPacket
	 
    stringBuffer.append(TEXT_83);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.varReturnValue(op.getCxxReturnType(), op.getReturnType()));
    stringBuffer.append(TEXT_84);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_85);
    
																   if (numParams == 0) { 
    stringBuffer.append(TEXT_86);
    
																   }
	 for (int i = 0; i < numParams; i++) {
	     if ("pushPacket".equals(op.getName()) && (numParams == 4) && (i == 0)) {
		 String iteratorBase = _cppHelper.getBaseSequenceMapping(op.getParams().get(i).getCxxType());
		 pushPacketCall = true;
		 
    stringBuffer.append(iteratorBase);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(iteratorBase);
    stringBuffer.append(TEXT_88);
    
			  continue;
	     }
	     
    stringBuffer.append(_cppHelper.getCppMapping(op.getParams().get(i).getCxxType()));
    
		      
    stringBuffer.append(TEXT_89);
    stringBuffer.append(op.getParams().get(i).getName());
    
		      if (i == (numParams - 1)) {
			  
    stringBuffer.append(TEXT_90);
    
		      } else {
	     
    stringBuffer.append(TEXT_91);
    
	 }
     } // end for params
            } // end for operations

            if (!isBULKIO) {
                for (Attribute op : intf.getAttributes()) {

    stringBuffer.append(TEXT_92);
    stringBuffer.append(op.getCxxReturnType());
    stringBuffer.append(_cppHelper.varReturnValue(op.getCxxReturnType(), op.getReturnType()));
    stringBuffer.append(TEXT_93);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_94);
    
                    if (!op.isReadonly()) {

    stringBuffer.append(TEXT_95);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(op.getCxxType());
    stringBuffer.append(TEXT_97);
    
                    } // end if readonly
                } // end for attributes
            } else {

    stringBuffer.append(TEXT_98);
    stringBuffer.append(rawTransferType);
    stringBuffer.append(TEXT_99);
    
            } // end ifBULKIO

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
    
            if (pushPacketCall) {

    stringBuffer.append(TEXT_106);
    
            }

    stringBuffer.append(TEXT_107);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_109);
    
            if (isBULKIO) {

    stringBuffer.append(TEXT_110);
    
            } // end if isBULKIO

    stringBuffer.append(TEXT_111);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_115);
    
            if (isBULKIO) {

    stringBuffer.append(TEXT_116);
    
            } // end if isBULKIO

            for (Operation op : intf.getOperations()) {
                int numParams = op.getParams().size();
                if (!"pushSRI".equals(op.getName()) && !("pushPacket".equals(op.getName()) && (numParams == 4))) {
                    for (int i = 0; i < numParams; i++) {
                        String iteratorBase = _cppHelper.getBaseSequenceMapping(op.getParams().get(i).getCxxType());
                        if (iteratorBase.length() > 11) {
                            if (iteratorBase.startsWith("std::vector")) {
                                String corbaBase = op.getParams().get(i).getCxxType();
                                int beginingIndex = 0;
                                if (corbaBase.startsWith("const")) {
                                    beginingIndex = 6;
                                }
                                if (corbaBase.endsWith("&")) {
                            
    stringBuffer.append(TEXT_117);
    stringBuffer.append(corbaBase.substring(beginingIndex, corbaBase.length()-1));
    
                                } else {
                            
    stringBuffer.append(TEXT_118);
    stringBuffer.append(corbaBase.substring(beginingIndex, corbaBase.length()));
    
                                }
                        
    stringBuffer.append(TEXT_119);
    stringBuffer.append(op.getName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_121);
    
                            }
                        }
                    } // end for params
                } // end if not pushSRI && not pushPacket
            } // end for operations


    stringBuffer.append(TEXT_122);
    
        }
    } // end if genClassDef

    stringBuffer.append(TEXT_123);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE