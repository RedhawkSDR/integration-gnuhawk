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

#include "bit_timing_loop_ff_2o.h"

//
//   This file contains the bindings to the hosted block
//

//
//    For GNU Radio blocks with getter and setter methods, the associated property can be bound
//    directly to the block's getter and/or setter.
//
//    void component_class_i::createBlock()
//    {
//        ...
//        this->registerGetter("property_x", &gnuradio_class::getter_for_x);
//        this->registerSetter("property_y", &gnuradio_class::setter_for_y);
//        this->registerGetterSetter("property_z", &gnuradio_class::getter_for_z,
//                                   &gnuradio_class::setter_for_z);
//        ...
//    }

//
//    To bind to a setter via the REDHAWK component class
//
//    void component_class_i::setterForPropertyX()
//    {
//       if ( validGRBlock() ) {
//           gr_sptr->set_property_x(this->property_x);
//        }
//    }
//
//    void component_class_i::createBlock()
//    {
//        ...
//        this->    setPropertyChangeListener("property_x", this, &component_class_i::setterForPropertyX);
//        ...
//    }

//
//    To bind to a getter via the REDHAWK component class
//
//    double component_class_i::getterForPropertyX()
//    {
//       if ( validGRBlock() ) {
//         return this->gr_sptr->get_property_x();
//       }
//       return 0.0;
//    }
//    void component_class_i::createBlock()
//    {
//        ...
//        this->registerGetValue("property_x", this, &component_class_i::getterForPropertyX);
//        ...
//    }


bit_timing_loop_ff_2o_i::bit_timing_loop_ff_2o_i(const char *uuid, const char *label) :
bit_timing_loop_ff_2o_base(uuid, label)
{
    setPropertyChangeListener("stream_id_map", this, &bit_timing_loop_ff_2o_i::streamIdChanged);
}

bit_timing_loop_ff_2o_i::~bit_timing_loop_ff_2o_i()
{
}

void bit_timing_loop_ff_2o_i::streamIdChanged(const std::string& id)
{ 
   RH_ProvidesPortMap::iterator in_port;
   in_port = inPorts.find("float_in");
   bulkio::InFloatPort *port = dynamic_cast<   bulkio::InFloatPort * >(in_port->second);
   BULKIO::StreamSRISequence_var sris = port->activeSRIs();
   if (sris->length() > 0 ) {
     BULKIO::StreamSRI sri = sris[sris->length()-1];
     setOutputStreamSRI(sri);
   }
} 

// createBlock
//
// Create the actual GNU Radio Block to that will perform the work method. The resulting
// block object is assigned to gr_stpr
//
// Add property change callbacks for getter/setter methods
//

void bit_timing_loop_ff_2o_i::createBlock()
{
    //
    //

    try {
    	gr_sptr = atsc_make_bit_timing_loop();
    }
    catch (...) {
    	return;
    }
	this->registerSetter("a_mu", &atsc_bit_timing_loop::set_mu);
	this->registerSetter("a_no_update", &atsc_bit_timing_loop::set_no_update);
	this->registerSetter("tap", &atsc_bit_timing_loop::set_loop_filter_tap);
	this->registerSetter("rate", &atsc_bit_timing_loop::set_timing_rate);
    // 
    // Use setThrottle method to enable the throttling of consumption/production of data by the
    // service function. The affect of the throttle will try to pause the execution of the 
    // service function for a specified duration.  This duration is calculated using the getTargetDuration() method
    // and the actual processing time to perform the work method.
    //
    // This is turned ON by default for "output" only components
    //
    // setThrottle( bool onoff );

    // 
    // Use maintainTimeStamp to enable proper data flow of timestamp with input and output data. 
    // if turned on (true)
    //  The timestamp from the input source will be used and maintained based on the output rate and
    //  the number of samples produced
    // if turned off
    //   then the timestamp from the input source is passed through if available or the time of day
    // 
    // maintainTimestamp( bool onoff );

    setupIOMappings();
}

//
// createOutputSRI
//
// For each output map ping defined, a call to createOutputSRI will be issued with the associated output index.
// This default SRI and StreamID will be saved to the map ping and pushed down stream via pushSRI.
//
// @param oidx : output stream index number to associate the returned SRI object with
// @param in_idx : input stream index number to associate the returned SRI object with
// @param ext : extension to append to incoming StreamID
// @return sri : default SRI object passed down stream over a RedHawk port
//      
BULKIO::StreamSRI bit_timing_loop_ff_2o_i::createOutputSRI( int32_t oidx, int32_t &in_idx, std::string &ext)
{
    //
    // oidx is the  stream number that you are returning an SRI context for
    //

    in_idx = 0;
    BULKIO::StreamSRI sri = BULKIO::StreamSRI();
    sri.hversion = 1;
    sri.xstart = 0.0;
    sri.xdelta = 1;
    sri.xunits = BULKIO::UNITS_TIME;
    sri.subsize = 0;
    sri.ystart = 0.0;
    sri.ydelta = 0.0;
    sri.yunits = BULKIO::UNITS_NONE;
    sri.mode = 0;
    std::ostringstream t;
    t << naming_service_name.c_str() << "_" << oidx;
    std::string sid = t.str();

    sri.streamID = CORBA::string_dup(sid.c_str());
    std::ostringstream t1;
    t1 << "_" << oidx;
    ext = t1.str();

  
    return sri;
}

