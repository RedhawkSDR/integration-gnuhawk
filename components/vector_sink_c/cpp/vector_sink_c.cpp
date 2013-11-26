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

#include "vector_sink_c.h"

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
//        this->setPropertyChangeListener("property_x", this, &component_class_i::setterForPropertyX);
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


vector_sink_c_i::vector_sink_c_i(const char *uuid, const char *label) :
vector_sink_c_base(uuid, label)
{
}

vector_sink_c_i::~vector_sink_c_i()
{
}

void vector_sink_c_i::on_reset(const std::string&)
{
  if (reset && validGRBlock()) {
    gr_sptr->reset();
    reset = false;
  }
}

// createBlock
//
// Create the actual GNU Radio Block to that will perform the work method. The resulting
// block object is assigned to gr_stpr
//
// Add property change callbacks for getter/setter methods
//

void vector_sink_c_i::createBlock()
{
    //
    // gr_sptr = xxx_make_xxx( args );
    //
    try {
        gr_sptr = gr_make_vector_sink_c( this->vlen );
    } catch (...) {
        return;
    }

    this->registerGetter("data", &gr_vector_sink_c::data);
    this->setPropertyChangeListener("reset", this, &vector_sink_c_i::on_reset);

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


