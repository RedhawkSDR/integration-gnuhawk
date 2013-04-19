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

/*******************************************************************************************

    AUTO-GENERATED CODE. DO NOT MODIFY

 	Source: unpack_k_bits_bb.spd.xml
 	Generated on: Thu Mar 07 12:29:56 EST 2013
 	Redhawk IDE
 	Version:M.1.8.3
 	Build id: v201302151037

*******************************************************************************************/

#include "unpack_k_bits_bb.h"


bool CompareSRI(BULKIO::StreamSRI &SRI_1, BULKIO::StreamSRI &SRI_2){
    if (SRI_1.hversion != SRI_2.hversion)
      return false;
    if (SRI_1.xstart != SRI_2.xstart)
      return false;
    if (SRI_1.xdelta != SRI_2.xdelta)
      return false;
    if (SRI_1.xunits != SRI_2.xunits)
      return false;
    if (SRI_1.subsize != SRI_2.subsize)
      return false;
    if (SRI_1.ystart != SRI_2.ystart)
      return false;
    if (SRI_1.ydelta != SRI_2.ydelta)
      return false;
    if (SRI_1.yunits != SRI_2.yunits)
      return false;
    if (SRI_1.mode != SRI_2.mode)
      return false;
    if (strcmp(SRI_1.streamID, SRI_2.streamID) != 0)
      return false;
    if (SRI_1.keywords.length() != SRI_2.keywords.length())
      return false;
    std::string action = "eq";
    for (unsigned int i=0; i<SRI_1.keywords.length(); i++) {
      if (strcmp(SRI_1.keywords[i].id, SRI_2.keywords[i].id)) {
	return false;
      }

      if (!ossie::compare_anys(SRI_1.keywords[i].value, SRI_2.keywords[i].value, action)) {
	return false;
      }
    }
    return true;
}


//RESOLVE PREPARE_ALT_LOGGING(BULKIO_dataOctet_Out_i,unpack_k_bits_bb_i)
// ----------------------------------------------------------------------------------------
// BULKIO_dataOctet_Out_i definition
// ----------------------------------------------------------------------------------------
BULKIO_dataOctet_Out_i::BULKIO_dataOctet_Out_i(std::string port_name, unpack_k_bits_bb_base *_parent) :
Port_Uses_base_impl(port_name)
{
    parent = static_cast<unpack_k_bits_bb_i *> (_parent);
    recConnectionsRefresh = false;
    recConnections.length(0);
}

BULKIO_dataOctet_Out_i::~BULKIO_dataOctet_Out_i()
{
}

/*
 * pushSRI
 *     description: send out SRI describing the data payload
 *
 *  H: structure of type BULKIO::StreamSRI with the SRI for this stream
 *    hversion
 *    xstart: start time of the stream
 *    xdelta: delta between two samples
 *    xunits: unit types from Platinum specification
 *    subsize: 0 if the data is one-dimensional
 *    ystart
 *    ydelta
 *    yunits: unit types from Platinum specification
 *    mode: 0-scalar, 1-complex
 *    streamID: stream identifier
 *    sequence<CF::DataType> keywords: unconstrained sequence of key-value pairs for additional description
 */
void BULKIO_dataOctet_Out_i::pushSRI(const BULKIO::StreamSRI& H)
{


    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in

    if (active) {
        std::vector < std::pair < BULKIO::dataOctet_var, std::string > >::iterator i;
        for (i = outConnections.begin(); i != outConnections.end(); ++i) {
            try {
                ((*i).first)->pushSRI(H);
            } catch(...) {
                //RESOLVE LOG_ERROR(BULKIO_dataOctet_Out_i, "Call to pushSRI by BULKIO_dataOctet_Out_i failed");
            }
        }
    }

    currentSRIs[std::string(H.streamID)] = H;
    refreshSRI = false;

    return;
}



// ----------------------------------------------------------------------------------------
// BULKIO_dataOctet_In_i definition
// ----------------------------------------------------------------------------------------
BULKIO_dataOctet_In_i::BULKIO_dataOctet_In_i(std::string port_name,  SRIListener *listener , SRICompare cmpFunc) : 
Port_Provides_base_impl(port_name)
{
    queueSem = new queueSemaphore(100);
    blocking = false;
    breakBlock = false;
    lastQueueSize=0;
    sriCmp = cmpFunc;
    sriListener = listener;

}

BULKIO_dataOctet_In_i::~BULKIO_dataOctet_In_i()
{
    block();
    while (workQueue.size() != 0) {
        dataTransfer *tmp = workQueue.front();
        workQueue.pop_front();
        delete tmp;
    }
}

BULKIO::PortStatistics * BULKIO_dataOctet_In_i::statistics()
{
    boost::mutex::scoped_lock lock(dataBufferLock);
    BULKIO::PortStatistics_var recStat = new BULKIO::PortStatistics(stats.retrieve());
    // NOTE: You must delete the object that this function returns!
    return recStat._retn();
}

BULKIO::PortUsageType BULKIO_dataOctet_In_i::state()
{
    boost::mutex::scoped_lock lock(dataBufferLock);
    if (workQueue.size() == queueSem->getMaxValue()) {
        return BULKIO::BUSY;
    } else if (workQueue.size() == 0) {
        return BULKIO::IDLE;
    } else {
        return BULKIO::ACTIVE;
    }

    return BULKIO::BUSY;
}

BULKIO::StreamSRISequence * BULKIO_dataOctet_In_i::activeSRIs()
{
    boost::mutex::scoped_lock lock(sriUpdateLock);
    BULKIO::StreamSRISequence seq_rtn;
    RH_SRIMap::iterator currH;
    int i = 0;
    for (currH = currentHs.begin(); currH != currentHs.end(); currH++) {
    	i++;
    	seq_rtn.length(i);
    	seq_rtn[i-1] = currH->second.first;
    }
    BULKIO::StreamSRISequence_var retSRI = new BULKIO::StreamSRISequence(seq_rtn);

    // NOTE: You must delete the object that this function returns!
    return retSRI._retn();
}

int BULKIO_dataOctet_In_i::getMaxQueueDepth()
{
    boost::mutex::scoped_lock lock(dataBufferLock);
    return queueSem->getMaxValue();
}

int BULKIO_dataOctet_In_i::getCurrentQueueDepth()
{
    boost::mutex::scoped_lock lock(dataBufferLock);
    return workQueue.size();
}

void BULKIO_dataOctet_In_i::setMaxQueueDepth(int newDepth)
{
    boost::mutex::scoped_lock lock(dataBufferLock);
    queueSem->setMaxValue(newDepth);
}

void BULKIO_dataOctet_In_i::pushSRI(const BULKIO::StreamSRI& H)
{
    boost::mutex::scoped_lock lock(sriUpdateLock);
    BULKIO::StreamSRI tmpH = H;
    RH_SRIMap::iterator currH = currentHs.find(std::string(H.streamID));
    if (currH == currentHs.end()) {
    	if ( sriListener ) (*sriListener)( this, tmpH);
    	currentHs[std::string(H.streamID)] = std::make_pair(tmpH, true);
        if (H.blocking) {
            boost::mutex::scoped_lock lock(dataBufferLock);
            blocking = true;
            queueSem->setCurrValue(workQueue.size());
        }
    } else {
    	if ( sriCmp ) {
	    	if (!sriCmp(tmpH, currH->second.first)) {
	            currentHs[std::string(H.streamID)] = std::make_pair(tmpH, true);
	            if (H.blocking) {
	                boost::mutex::scoped_lock lock(dataBufferLock);
	                blocking = true;
	                queueSem->setCurrValue(workQueue.size());
	            }
	    	}
    	}
    }
}

void BULKIO_dataOctet_In_i::pushPacket(const CF::OctetSequence& data, const BULKIO::PrecisionUTCTime& T, CORBA::Boolean EOS, const char* streamID)
{
    if (queueSem->getMaxValue() == 0) {
        return;
    }    
    BULKIO::StreamSRI tmpH = {1, 0.0, 1.0, 1, 0, 0.0, 0.0, 0, 0, streamID, false, 0};
    bool sriChanged = false;
    bool portBlocking = false;

    RH_SRIMap::iterator currH;
    {
        boost::mutex::scoped_lock lock(sriUpdateLock);

        currH = currentHs.find(std::string(streamID));
        if (currH != currentHs.end()) {
            tmpH = currH->second.first;
            sriChanged = currH->second.second;
            currentHs[streamID] = std::make_pair(currH->second.first, false);
        }
        portBlocking = blocking;
    }

    if(portBlocking) {
        queueSem->incr();
        boost::mutex::scoped_lock lock(dataBufferLock);
        stats.update(data.length(), workQueue.size()/queueSem->getMaxValue(), EOS, streamID, false);
        BULKIO_dataOctet_In_i::dataTransfer *tmpIn = new BULKIO_dataOctet_In_i::dataTransfer(data, T, EOS, streamID, tmpH, sriChanged, false);
        workQueue.push_back(tmpIn);
        dataAvailable.notify_all();
    } else {
        boost::mutex::scoped_lock lock(dataBufferLock);
        bool flushToReport = false;
        if (workQueue.size() == queueSem->getMaxValue()) { // reached maximum queue depth - flush the queue
            flushToReport = true;
            BULKIO_dataOctet_In_i::dataTransfer *tmp;
            while (workQueue.size() != 0) {
                tmp = workQueue.front();
                workQueue.pop_front();
                delete tmp;
            }
        }
        stats.update(data.length(), workQueue.size()/queueSem->getMaxValue(), EOS, streamID, flushToReport);
        BULKIO_dataOctet_In_i::dataTransfer *tmpIn = new BULKIO_dataOctet_In_i::dataTransfer(data, T, EOS, streamID, tmpH, sriChanged, flushToReport);
        workQueue.push_back(tmpIn);
        dataAvailable.notify_all();
    }
}


void BULKIO_dataOctet_In_i::block()
{
    breakBlock = true;
    queueSem->release();
    dataAvailable.notify_all();
}

void BULKIO_dataOctet_In_i::unblock()
{
    breakBlock = false;
}

bool BULKIO_dataOctet_In_i::blocked()
{
    return breakBlock;
}

/*
 * getPacket
 *     description: retrieve data from the provides (input) port
 *
 *  timeout: the amount of time to wait for data before a NULL is returned.
 *           Use 0.0 for non-blocking and -1 for blocking.
 */
BULKIO_dataOctet_In_i::dataTransfer *BULKIO_dataOctet_In_i::getPacket(float timeout, std::string streamID )
{
    if (breakBlock) {
        return NULL;
    }
    //if (workQueue.size() == 0) {
    if ( (workQueue.size() == 0 ) or (( workQueue.size() != 0 ) and ( workQueue.size() == lastQueueSize )) ){
        if (timeout == 0.0) {
            lastQueueSize = workQueue.size();
            return NULL;
        } else if (timeout > 0){
             uint64_t secs = (unsigned long)(trunc(timeout));
            uint64_t msecs = (unsigned long)((timeout - secs) * 1e6);
            boost::system_time to_time  = boost::get_system_time() + boost::posix_time::seconds(secs) + boost::posix_time::microseconds(msecs);
            boost::unique_lock< boost::mutex > lock(dataAvailableMutex);
            if ( dataAvailable.timed_wait( lock, to_time) == false ) {
                return NULL;
            }

            if (breakBlock) {
                return NULL;
            }
        } else {
            boost::unique_lock< boost::mutex > lock(dataAvailableMutex);
            dataAvailable.wait(lock);
            if (breakBlock) {
                return NULL;
            }
        }       
        
    }
    boost::mutex::scoped_lock lock1(dataBufferLock);
    BULKIO_dataOctet_In_i::dataTransfer *tmp = NULL;
    if ( streamID == "" ){
        tmp = workQueue.front();
        workQueue.pop_front();
    } else {
        std::deque< dataTransfer * >::iterator p = workQueue.begin();
        while ( p != workQueue.end() ) {
            if ( (*p)->streamID == streamID ) {
                tmp = *p;
                workQueue.erase(p);
                break;
            }
            p++;
       }
    
    }
    
    if ( tmp == NULL ) {
        lastQueueSize = workQueue.size();
        return NULL;
    }
    
    boost::mutex::scoped_lock lock2(sriUpdateLock);
    if (tmp->EOS) {
	    RH_SRIMap::iterator target = currentHs.find(std::string(tmp->streamID));
        if (target != currentHs.end()) {
            if (target->second.first.blocking) {
                RH_SRIMap::iterator currH;
                bool keepBlocking = false;
                for (currH = currentHs.begin(); currH != currentHs.end(); currH++) {
                    if (currH->second.first.blocking) {
                        keepBlocking = true;
                        break;
                    }
                }

                if (!keepBlocking) {
                    queueSem->setCurrValue(0);
                    blocking = false;
                }
            }
            currentHs.erase(target);
        }
    }

    if (blocking) {
        queueSem->decr();
    }
    
    lastQueueSize=0;
    return tmp;
}

