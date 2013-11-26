
/*******************************************************************************************


*******************************************************************************************/
#include "gnuhawk_bulkio_out_port.h"


namespace  bulkio {

/**

  GnuhawkOutPort Constructor
  
  Accepts connect/disconnect interfaces for notification when these events occur
  */

template < typename PortTraits, typename dataType >
GnuhawkOutPort< PortTraits, dataType >::GnuhawkOutPort(std::string port_name,  ConnectNotifier connectCB, DisconnectNotifier disconnectCB ) :
  OutPort< PortTraits >(port_name, connectCB, disconnectCB)
{
}

template < typename PortTraits, typename dataType >
GnuhawkOutPort< PortTraits, dataType >::~GnuhawkOutPort()
{
}

template < typename PortTraits, typename dataType >
GnuhawkOutInt8Port< PortTraits, dataType >::GnuhawkOutInt8Port( std::string name ) :
    OutInt8Port < PortTraits >(name)
{
}


//
// Required for Template Instantion for the compilation unit.
// Note: we only define those valid types for which Bulkio IDL is defined. Users wanting to
// inherit this functionality will be unable to since they cannot instantiate and
// link against the template.
//

template class GnuhawkOutInt8Port<  CharPortTraits, int8_t >;
template class GnuhawkOutPort<  OctetPortTraits, uint8_t >;
template class GnuhawkOutPort<  ShortPortTraits, int16_t >;
template class GnuhawkOutPort<  UShortPortTraits, uint16_t >;
template class GnuhawkOutPort<  LongPortTraits, int32_t >;
template class GnuhawkOutPort<  ULongPortTraits, uint32_t >;
template class GnuhawkOutPort<  LongLongPortTraits, int64_t >;
template class GnuhawkOutPort<  ULongLongPortTraits, uint64_t >;
template class GnuhawkOutPort<  FloatPortTraits, float >;
template class GnuhawkOutPort<  DoublePortTraits, double >;
//template class OutStringPort< FilePortTraits >;
//template class OutStringPort<  XMLPortTraits >;



} // end of bulkio namespace
