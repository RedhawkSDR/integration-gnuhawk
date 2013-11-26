#include "gnuhawk_bulkio_p.h"

#include "gnuhawk_bulkio_in_port.h"



namespace  bulkio {

template < typename PortTraits, typename dataType >
GnuhawkInPort< PortTraits, dataType >::GnuhawkInPort(std::string port_name, 
			       bulkio::sri::Compare sriCmp,
			       bulkio::sri::Notifier streamCB ) : 
InPort< PortTraits >(port_name, sriCmp, streamCB)
{
}

template < typename PortTraits, typename dataType >
void GnuhawkInPort< PortTraits, dataType >::pushSRI(const BULKIO::StreamSRI& H)
{
    bool updateSem = false;
    {
        boost::mutex::scoped_lock lock(this->sriUpdateLock);
        BULKIO::StreamSRI tmpH = H;
        SriMap::iterator currH = this->currentHs.find(std::string(H.streamID));
        if (currH == this->currentHs.end()) {
    	    if ( this->streamCallback ) {
                (*this->streamCallback)(tmpH);
            }
    	    this->currentHs[std::string(H.streamID)] = std::make_pair(tmpH, true);
            if (H.blocking) {
                updateSem = true;
            }
        } else {
    	    if ( this->sri_cmp ) {
	    	    if (!this->sri_cmp(tmpH, currH->second.first)) {
	            this->currentHs[std::string(H.streamID)] = std::make_pair(tmpH, true);
	                if (H.blocking) {
                        updateSem = true;
	                }
	    	    }
    	    }
        }
    }

    if (updateSem) {
        boost::mutex::scoped_lock lock(this->dataBufferLock);
        this->blocking = true;
        this->queueSem->setCurrValue(this->workQueue.size());
    }
}

/*
 * getPacket
 *     description: retrieve data from the provides (input) port
 *
 *  timeout: the amount of time to wait for data before a NULL is returned.
 *           Use 0.0 for non-blocking and -1 for blocking.
 */
template < typename PortTraits, typename dataType >
typename GnuhawkInPort< PortTraits, dataType >::DataTransferType * GnuhawkInPort< PortTraits, dataType >::getPacket(float timeout, std::string streamID )
{
    if (this->breakBlock) {
        return NULL;
    }
    if ( (this->workQueue.size() == 0 ) or (( this->workQueue.size() != 0 ) and ( this->workQueue.size() == lastQueueSize )) ){
        if (timeout == 0.0) {
            lastQueueSize = this->workQueue.size();
            return NULL;
        } else if (timeout > 0){
            uint64_t secs = (unsigned long)(trunc(timeout));
            uint64_t msecs = (unsigned long)((timeout - secs) * 1e6);
            boost::system_time to_time  = boost::get_system_time() + boost::posix_time::seconds(secs) + boost::posix_time::microseconds(msecs);
            boost::unique_lock< boost::mutex > lock(this->dataAvailableMutex);
            if ( this->dataAvailable.timed_wait( lock, to_time) == false ) {
                return NULL;
            }

            if (this->breakBlock) {
                return NULL;
            }
        } else {
            boost::unique_lock< boost::mutex > lock(this->dataAvailableMutex);
            this->dataAvailable.wait(lock);
            if (this->breakBlock) {
                return NULL;
            }
        }       
        
    }
    boost::mutex::scoped_lock lock1(this->dataBufferLock);
    DataTransferType *tmp = NULL;
    if ( streamID == "" ){
        tmp = this->workQueue.front();
        this->workQueue.pop_front();
    } else {
        typename WorkQueue::iterator p = this->workQueue.begin();
        while ( p != this->workQueue.end() ) {
            if ( (*p)->streamID == streamID ) {
                tmp = *p;
                this->workQueue.erase(p);
                break;
            }
            p++;
       }
    
    }
    
    if ( tmp == NULL ) {
        lastQueueSize = this->workQueue.size();
        return NULL;
    }
    
    boost::mutex::scoped_lock lock2(this->sriUpdateLock);
    if (tmp->EOS) {
	    SriMap::iterator target = this->currentHs.find(std::string(tmp->streamID));
        if (target != this->currentHs.end()) {
            bool sriBlocking = target->second.first.blocking;
            this->currentHs.erase(target);
            if (sriBlocking) {
                SriMap::iterator  currH;
                bool keepBlocking = false;
                for (currH = this->currentHs.begin(); currH != this->currentHs.end(); currH++) {
                    if (currH->second.first.blocking) {
                        keepBlocking = true;
                        break;
                    }
                }

                if (!keepBlocking) {
                    this->queueSem->setCurrValue(0);
                    this->blocking = false;
                }
            }
        }
    }

    if (this->blocking) {
        this->queueSem->decr();
    }
    
    lastQueueSize=0;
    return tmp;
}


//
// Required for Template Instantion for the compilation unit.
// Note: we only define those valid types for which Bulkio IDL is defined. Users wanting to
// inherit this functionality will be unable to since they cannot instantiate and
// link against the template.
//

    template class GnuhawkInPort< CharPortTraits, int8_t >;
    template class GnuhawkInPort< OctetPortTraits, uint8_t >;
    template class GnuhawkInPort< ShortPortTraits, int16_t >;
    template class GnuhawkInPort< UShortPortTraits, uint16_t >;
    template class GnuhawkInPort< LongPortTraits, int32_t >;
    template class GnuhawkInPort< ULongPortTraits, uint32_t >;
    template class GnuhawkInPort< LongLongPortTraits, int64_t >;
    template class GnuhawkInPort< ULongLongPortTraits, uint64_t >;
    template class GnuhawkInPort< FloatPortTraits, float >;
    template class GnuhawkInPort< DoublePortTraits, double >;
    //template class GnuhawkInStringPort< FilePortTraits >; 
    //template class GnuhawkInStringPort< XMLPortTraits >;


} // end of bulkio namespace
