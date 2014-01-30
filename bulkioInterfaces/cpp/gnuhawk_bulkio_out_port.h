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


#ifndef __gnuhawk_bulkio_out_port_h
#define __gnuhawk_bulkio_out_port_h

#include "bulkio/bulkio.h"


namespace bulkio {

  //
  //  GnuhawkOutPort
  //
  //  Base template for data transfers between GNUHAWK BULKIO ports.  This class is defined by 2 trait classes
  //    PortTraits - This template provides the context for the port's middleware transport classes and they base data types
  //                 passed between port objects
  //    dataType - Provides the RH_NativeType for GNUHAWK use
  //

template <typename PortTraits, typename dataType >
class GnuhawkOutPort : public OutPort < PortTraits >
{
    public:
        typedef dataType RH_NativeType;

        GnuhawkOutPort(std::string port_name, 
	        ConnectNotifier connectCB=NULL,
	        DisconnectNotifier disconnectCB=NULL );

        ~GnuhawkOutPort();

};

  //
  // Character Specialization..
  //
  // This class over rides the pushPacket method to use the Int8 parameter and also overriding the PortSequence constructor to
  // use the CORBA::Char type.
  //
  // For some reason, you cannot specialize a method of a template and have the template be inherited which caused major
  // issues during compilation.  Every member variable was being reported as unknown and the _Connections type was being
  //  lost.
  //

template < typename PortTraits, typename dataType >
class GnuhawkOutInt8Port: public OutInt8Port<  PortTraits >  {

    public:
        typedef dataType RH_NativeType;

        GnuhawkOutInt8Port(std::string port_name);

        virtual ~GnuhawkOutInt8Port() {};

        void pushPacket( std::vector< Int8 >& data, BULKIO::PrecisionUTCTime& T, bool EOS, const std::string& streamID);

};


    /**
     Uses Port Definitions for All Bulk IO port definitions
     *
     */
    typedef GnuhawkOutInt8Port<  CharPortTraits, int8_t >      GnuhawkOutCharPort;
    typedef GnuhawkOutPort< OctetPortTraits, uint8_t >         GnuhawkOutOctetPort;
    typedef GnuhawkOutOctetPort                                GnuhawkOutUInt8Port;
    typedef GnuhawkOutPort<  ShortPortTraits, int16_t >        GnuhawkOutShortPort;
    typedef GnuhawkOutPort<  UShortPortTraits, uint16_t >      GnuhawkOutUShortPort;
    typedef GnuhawkOutShortPort                                GnuhawkOutInt16Port;
    typedef GnuhawkOutUShortPort                               GnuhawkOutUInt16Port;
    typedef GnuhawkOutPort<  LongPortTraits, int32_t >         GnuhawkOutLongPort;
    typedef GnuhawkOutPort< ULongPortTraits, uint32_t >        GnuhawkOutULongPort;
    typedef GnuhawkOutLongPort                                 GnuhawkOutInt32Port;
    typedef GnuhawkOutULongPort                                GnuhawkOutUInt32Port;
    typedef GnuhawkOutPort<  LongLongPortTraits, int64_t >     GnuhawkOutLongLongPort;
    typedef GnuhawkOutPort<  ULongLongPortTraits, uint64_t >   GnuhawkOutULongLongPort;
    typedef GnuhawkOutLongLongPort                             GnuhawkOutInt64Port;
    typedef GnuhawkOutULongLongPort                            GnuhawkOutUInt64Port;
    typedef GnuhawkOutPort<  FloatPortTraits, float >          GnuhawkOutFloatPort;
    typedef GnuhawkOutPort<  DoublePortTraits, double >        GnuhawkOutDoublePort;
    //typedef GnuhawkOutStringPort< URLPortTraits >     GnuhawkOutURLPort;
    //typedef GnuhawkOutStringPort< FilePortTraits >    GnuhawkOutFilePort;
    //typedef GnuhawkOutStringPort< XMLPortTraits >     GnuhawkOutXMLPort;

}  // end of bulkio namespace


#endif
