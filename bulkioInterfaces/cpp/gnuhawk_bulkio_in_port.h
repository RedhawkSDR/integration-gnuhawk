#ifndef __gnuhawk_bulkio_in_port_h
#define __gnuhawk_bulkio_in_port_h

#include "bulkio/bulkio.h"

namespace bulkio {


template < typename PortTraits, typename dataType >
class GnuhawkInPort : public InPort< PortTraits >
{
    public:
        typedef dataType RH_NativeType;

        typedef PortTraits  Traits;

        // Port Variable Type
        typedef typename Traits::POAPortType   PortVarType;

        //  Interface Type
        typedef typename  Traits::PortType      PortType;

        //  Interface Type
        typedef typename  Traits::PortType      ProvidesPortType;

        // Transport Sequence Type use to during push packet
        typedef typename Traits::SequenceType    PortSequenceType;

        //
        // Transport type used by this port
        //
        typedef typename Traits::TransportType  TransportType;

        //
        // Native type mapping of TransportType
        //
        typedef typename Traits::NativeType      NativeType;

        //
        // Declaration of DataTransfer class from TransportType trait and DataBuffer type trait
        //
        typedef DataTransfer< typename Traits::DataTransferTraits > DataTransferType;

        // backwards compatible definition
        typedef DataTransfer< typename Traits::DataTransferTraits > dataTransfer;

        // queue of dataTranfer objects maintained by the port
        typedef   std::deque< DataTransferType * > WorkQueue;


        GnuhawkInPort(std::string port_name, 
	                  bulkio::sri::Compare sriCmp = bulkio::sri::DefaultComparator,
	                  bulkio::sri::Notifier streamCallBack = NULL
	                 );

        virtual DataTransferType *getPacket(float timeout, std::string streamID);
        virtual void pushSRI(const BULKIO::StreamSRI& H);

    protected:
        unsigned int lastQueueSize;
};

    /**
     Provides Port Definitions for All Bulk IO pushPacket Port definitions
     *
     */

    typedef GnuhawkInPort< CharPortTraits, int8_t >            GnuhawkInCharPort;
    typedef GnuhawkInPort< OctetPortTraits, uint8_t >          GnuhawkInOctetPort;
    typedef GnuhawkInCharPort                                  GnuhawkInInt8Port;
    typedef GnuhawkInOctetPort                                 GnuhawkInUInt8Port;
    typedef GnuhawkInPort< ShortPortTraits, int16_t >          GnuhawkInShortPort;
    typedef GnuhawkInPort< UShortPortTraits, uint16_t >        GnuhawkInUShortPort;
    typedef GnuhawkInShortPort                                 GnuhawkInInt16Port;
    typedef GnuhawkInUShortPort                                GnuhawkInUInt16Port;
    typedef GnuhawkInPort< LongPortTraits, int32_t >           GnuhawkInLongPort;
    typedef GnuhawkInPort< ULongPortTraits, uint32_t >         GnuhawkInULongPort;
    typedef GnuhawkInLongPort                                  GnuhawkInInt32Port;
    typedef GnuhawkInULongPort                                 GnuhawkInUInt32Port;
    typedef GnuhawkInPort< LongLongPortTraits, int64_t >       GnuhawkInLongLongPort;
    typedef GnuhawkInPort< ULongLongPortTraits, uint64_t >     GnuhawkInULongLongPort;
    typedef GnuhawkInLongLongPort                              GnuhawkInInt64Port;
    typedef GnuhawkInULongLongPort                             GnuhawkInUInt64Port;
    typedef GnuhawkInPort< FloatPortTraits, float >            GnuhawkInFloatPort;
    typedef GnuhawkInPort< DoublePortTraits, double >          GnuhawkInDoublePort;
    //typedef GnuhawkInStringPort< URLPortTraits >            GnuhawkInURLPort;
    //typedef GnuhawkInStringPort< FilePortTraits >           GnuhawkInFilePort;
    //typedef GnuhawkInStringPort< XMLPortTraits >            GnuhawkInXMLPort;


}  // end of bulkio namespace


#endif
