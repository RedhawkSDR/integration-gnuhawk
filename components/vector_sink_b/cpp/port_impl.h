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


#ifndef PORT_H
#define PORT_H

#include "ossie/Port_impl.h"
#include "ossie/debug.h"
#include <queue>
#include <list>
#include <boost/thread/condition_variable.hpp>
#include <boost/thread/locks.hpp>

class vector_sink_b_base;
class vector_sink_b_i;

#define CORBA_MAX_TRANSFER_BYTES omniORB::giopMaxMsgSize()

typedef char                        Char;
typedef int8_t                      Int8;
typedef uint8_t                     UInt8;
typedef int16_t                     Int16;
typedef uint16_t                    UInt16;

typedef int32_t                     Int32;
typedef uint32_t                    UInt32;

typedef int64_t                     Int64;
typedef uint64_t                    UInt64;
typedef float                       Float;
typedef double                      Double;


#include "BULKIO/bio_dataOctet.h"

class queueSemaphore
{
    private:
        unsigned int maxValue;
        unsigned int currValue;
        boost::mutex mutex;
        boost::condition_variable condition;

    public:
        queueSemaphore(unsigned int initialMaxValue):mutex(),condition() {
        	maxValue = initialMaxValue;
        }
        
        void release() {
          currValue=0;
          condition.notify_all();
        } 

        void setMaxValue(unsigned int newMaxValue) {
            boost::unique_lock<boost::mutex> lock(mutex);
            maxValue = newMaxValue;
        }

        unsigned int getMaxValue(void) {
			boost::unique_lock<boost::mutex> lock(mutex);
			return maxValue;
		}

        void setCurrValue(unsigned int newValue) {
        	boost::unique_lock<boost::mutex> lock(mutex);
        	if (newValue < maxValue) {
        		unsigned int oldValue = currValue;
        		currValue = newValue;

        		if (oldValue > newValue) {
        			condition.notify_one();
        		}
        	}
        }

        void incr() {
            boost::unique_lock<boost::mutex> lock(mutex);
            while (currValue >= maxValue) {
                condition.wait(lock);
            }
            ++currValue;
        }

        void decr() {
            boost::unique_lock<boost::mutex> lock(mutex);
            if (currValue > 0) {
            	--currValue;
            }
            condition.notify_one();
        }
};        




//
//  Comparator method to search for matching SRI information if "a" matches "b"
//
typedef bool  (*SRICompare)( BULKIO::StreamSRI &a, BULKIO::StreamSRI &b);


bool CompareSRI(BULKIO::StreamSRI &SRI_1, BULKIO::StreamSRI &SRI_2);

// ----------------------------------------------------------------------------------------
// BULKIO_dataOctet_In_i declaration
// ----------------------------------------------------------------------------------------
class BULKIO_dataOctet_In_i : public POA_BULKIO::dataOctet, public Port_Provides_base_impl
{
    public:
    
     class SRIListener {

       public:

          virtual void operator() ( BULKIO_dataOctet_In_i *, BULKIO::StreamSRI &  ) = 0;
     };
      
     //BULKIO_dataOctet_In_i(std::string port_name, vector_sink_b_base *_parent);
     BULKIO_dataOctet_In_i(std::string port_name, SRIListener *listener=NULL, SRICompare cmp=CompareSRI);
     ~BULKIO_dataOctet_In_i();  
        
    

        void pushSRI(const BULKIO::StreamSRI& H);
        void pushPacket(const CF::OctetSequence& data, const BULKIO::PrecisionUTCTime& T, CORBA::Boolean EOS, const char* streamID);

     	typedef unsigned char         RH_TransportType;
        typedef UInt8        RH_NativeType;

        BULKIO::PortUsageType state();
        BULKIO::PortStatistics* statistics();
        BULKIO::StreamSRISequence* activeSRIs();
        int getCurrentQueueDepth();
        int getMaxQueueDepth();
        void setMaxQueueDepth(int newDepth);

        class linkStatistics
        {
            public:
                struct statPoint {
                    unsigned int elements;
                    float queueSize;
                    double secs;
                    double usecs;
                };

                linkStatistics() {
                    bitSize = sizeof(unsigned char) * 8.0;
                    historyWindow = 10;
                    receivedStatistics_idx = 0;
                    receivedStatistics.resize(historyWindow);
                    activeStreamIDs.resize(0);
                    runningStats.elementsPerSecond = -1.0;
                    runningStats.bitsPerSecond = -1.0;
                    runningStats.callsPerSecond = -1.0;
                    runningStats.averageQueueDepth = -1.0;
                    runningStats.streamIDs.length(0);
                    runningStats.timeSinceLastCall = -1;
                    enabled = true;
                    flush_sec = 0;
                    flush_usec = 0;
                };

                ~linkStatistics() {
                }

                void setEnabled(bool enableStats) {
                    enabled = enableStats;
                }

                void update(unsigned int elementsReceived, float queueSize, bool EOS, std::string streamID, bool flush) {
                    if (!enabled) {
                        return;
                    }
                    struct timeval tv;
                    struct timezone tz;
                    gettimeofday(&tv, &tz);
                    receivedStatistics[receivedStatistics_idx].elements = elementsReceived;
                    receivedStatistics[receivedStatistics_idx].queueSize = queueSize;
                    receivedStatistics[receivedStatistics_idx].secs = tv.tv_sec;
                    receivedStatistics[receivedStatistics_idx++].usecs = tv.tv_usec;
                    receivedStatistics_idx = receivedStatistics_idx % historyWindow;
                    if (flush) {
                        flush_sec = tv.tv_sec;
                        flush_usec = tv.tv_usec;
                    }
                    if (!EOS) {
                        std::list<std::string>::iterator p = activeStreamIDs.begin();
                        bool foundStreamID = false;
                        while (p != activeStreamIDs.end()) {
                            if (*p == streamID) {
                                foundStreamID = true;
                                break;
                            }
                            p++;
                        }
                        if (!foundStreamID) {
                            activeStreamIDs.push_back(streamID);
                        }
                    } else {
                        std::list<std::string>::iterator p = activeStreamIDs.begin();
                        while (p != activeStreamIDs.end()) {
                            if (*p == streamID) {
                                activeStreamIDs.erase(p);
                                break;
                            }
                            p++;
                        }
                    }
                }

                BULKIO::PortStatistics retrieve() {
                    if (!enabled) {
                        return runningStats;
                    }
                    struct timeval tv;
                    struct timezone tz;
                    gettimeofday(&tv, &tz);

                    int idx = (receivedStatistics_idx == 0) ? (historyWindow - 1) : (receivedStatistics_idx - 1);
                    double front_sec = receivedStatistics[idx].secs;
                    double front_usec = receivedStatistics[idx].usecs;
                    double secDiff = tv.tv_sec - receivedStatistics[receivedStatistics_idx].secs;
                    double usecDiff = (tv.tv_usec - receivedStatistics[receivedStatistics_idx].usecs) / ((double)1e6);
                    double totalTime = secDiff + usecDiff;
                    double totalData = 0;
                    float queueSize = 0;
                    int startIdx = (receivedStatistics_idx + 1) % historyWindow;
                    for (int i = startIdx; i != receivedStatistics_idx; ) {
                        totalData += receivedStatistics[i].elements;
                        queueSize += receivedStatistics[i].queueSize;
                        i = (i + 1) % historyWindow;
                    }
                    runningStats.bitsPerSecond = ((totalData * bitSize) / totalTime);
                    runningStats.elementsPerSecond = (totalData / totalTime);
                    runningStats.averageQueueDepth = (queueSize / historyWindow);
                    runningStats.callsPerSecond = (double(historyWindow - 1) / totalTime);
                    runningStats.timeSinceLastCall = (((double)tv.tv_sec) - front_sec) + (((double)tv.tv_usec - front_usec) / ((double)1e6));
                    unsigned int streamIDsize = activeStreamIDs.size();
                    std::list< std::string >::iterator p = activeStreamIDs.begin();
                    runningStats.streamIDs.length(streamIDsize);
                    for (unsigned int i = 0; i < streamIDsize; i++) {
                        if (p == activeStreamIDs.end()) {
                            break;
                        }
                        runningStats.streamIDs[i] = CORBA::string_dup((*p).c_str());
                        p++;
                    }
                    if ((flush_sec != 0) && (flush_usec != 0)) {
                        double flushTotalTime = (((double)tv.tv_sec) - flush_sec) + (((double)tv.tv_usec - flush_usec) / ((double)1e6));
                        runningStats.keywords.length(1);
                        runningStats.keywords[0].id = CORBA::string_dup("timeSinceLastFlush");
                        runningStats.keywords[0].value <<= CORBA::Double(flushTotalTime);
                    }
                    return runningStats;
                }

            protected:
                bool enabled;
                double bitSize;
                BULKIO::PortStatistics runningStats;
                std::vector<statPoint> receivedStatistics;
                std::list< std::string > activeStreamIDs;
                unsigned long historyWindow;
                long receivedStatistics_idx;
                double flush_sec;
                double flush_usec;
        };
        
        void enableStats(bool enable) {
            stats.setEnabled(enable);
        };


        class dataTransfer
        {
            public:
                dataTransfer(const CF::OctetSequence& data, const BULKIO::PrecisionUTCTime &_T, bool _EOS, const char* _streamID, BULKIO::StreamSRI &_H, bool _sriChanged, bool _inputQueueFlushed)
                {
                    int dataLength = data.length();

#ifdef EXPECTED_VECTOR_IMPL
                    std::_Vector_base<unsigned char, _seqVector::seqVectorAllocator<unsigned char> >::_Vector_impl *vectorPointer = (std::_Vector_base<unsigned char, _seqVector::seqVectorAllocator<unsigned char> >::_Vector_impl *) ((void*) & dataBuffer);
                    vectorPointer->_M_start = const_cast<CF::OctetSequence*>(&data)->get_buffer(1);

                    vectorPointer->_M_finish = vectorPointer->_M_start + dataLength;
                    vectorPointer->_M_end_of_storage = vectorPointer->_M_finish;

#else
                    dataBuffer.resize(dataLength);
                    if (dataLength > 0) {
                        memcpy(&dataBuffer[0], &data[0], dataLength * sizeof(data[0]));
                    }

#endif
                    T = _T;
                    EOS = _EOS;
                    streamID = _streamID;
                    SRI = _H;
                    sriChanged = _sriChanged;
                    inputQueueFlushed = _inputQueueFlushed;
                };

#ifdef EXPECTED_VECTOR_IMPL
                std::vector< unsigned char, _seqVector::seqVectorAllocator<unsigned char> > dataBuffer;
#else
                std::vector<unsigned char> dataBuffer;
#endif
                BULKIO::PrecisionUTCTime T;
                bool EOS;
                std::string streamID;
                BULKIO::StreamSRI SRI;
                bool sriChanged;
                bool inputQueueFlushed;
        };

        dataTransfer *getPacket(float timeout, std::string streamID);
        void block();
        void unblock();
        bool blocked();

    protected:
    	typedef  std::map<std::string, std::pair<BULKIO::StreamSRI, bool> > 	RH_SRIMap;
    
        //vector_sink_b_i *parent;
        SRICompare     sriCmp;
	    SRIListener   *sriListener;
        std::deque<dataTransfer *> workQueue;
        unsigned int lastQueueSize;
        RH_SRIMap     currentHs;
        boost::mutex dataBufferLock;
        boost::mutex sriUpdateLock;
        boost::mutex dataAvailableMutex;
        boost::condition_variable dataAvailable;
        unsigned long secs, nsecs, timeout_secs, timeout_nsecs;
        bool breakBlock;
        bool blocking;
        queueSemaphore* queueSem;

        // statistics
        linkStatistics stats;

};

#endif
