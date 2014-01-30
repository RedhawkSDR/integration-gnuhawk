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
