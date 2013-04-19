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

 	Source: vector_source_s.spd.xml
 	Generated on: Tue Mar 05 08:58:48 EST 2013
 	Redhawk IDE
 	Version:M.1.8.3
 	Build id: v201303011817

*******************************************************************************************/

#include "vector_source_s.h"


//RESOLVE PREPARE_ALT_LOGGING(BULKIO_dataShort_Out_i,vector_source_s_i)
// ----------------------------------------------------------------------------------------
// BULKIO_dataShort_Out_i definition
// ----------------------------------------------------------------------------------------
BULKIO_dataShort_Out_i::BULKIO_dataShort_Out_i(std::string port_name, vector_source_s_base *_parent) :
Port_Uses_base_impl(port_name)
{
    parent = static_cast<vector_source_s_i *> (_parent);
    recConnectionsRefresh = false;
    recConnections.length(0);
}

BULKIO_dataShort_Out_i::~BULKIO_dataShort_Out_i()
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
void BULKIO_dataShort_Out_i::pushSRI(const BULKIO::StreamSRI& H)
{


    boost::mutex::scoped_lock lock(updatingPortsLock);   // don't want to process while command information is coming in

    if (active) {
        std::vector < std::pair < BULKIO::dataShort_var, std::string > >::iterator i;
        for (i = outConnections.begin(); i != outConnections.end(); ++i) {
            try {
                ((*i).first)->pushSRI(H);
            } catch(...) {
                //RESOLVE LOG_ERROR(BULKIO_dataShort_Out_i, "Call to pushSRI by BULKIO_dataShort_Out_i failed");
            }
        }
    }

    currentSRIs[std::string(H.streamID)] = H;
    refreshSRI = false;

    return;
}



