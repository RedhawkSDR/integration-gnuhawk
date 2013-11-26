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

#include "probe_mpsk_snr_est_c.h"

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


probe_mpsk_snr_est_c_i::probe_mpsk_snr_est_c_i(const char *uuid, const char *label) :
probe_mpsk_snr_est_c_base(uuid, label)
{
}

probe_mpsk_snr_est_c_i::~probe_mpsk_snr_est_c_i()
{
}

double probe_mpsk_snr_est_c_i::get_snr()
{
    return this->gr_sptr->snr();
}

void probe_mpsk_snr_est_c_i::set_type(const std::string &id)
{
    snr_est_type_t curr_type = SNR_EST_SIMPLE;
    if (this->type == 0){
        curr_type = SNR_EST_SIMPLE;
    } else if (this->type == 1){
        curr_type = SNR_EST_SKEW;
    } else if (this->type == 2){
        curr_type = SNR_EST_M2M4;
    } else if (this->type == 3){
        curr_type = SNR_EST_SVR;
    }
    this->gr_sptr->set_type(curr_type);
}

// createBlock
//
// Create the actual GNU Radio Block to that will perform the work method. The resulting
// block object is assigned to gr_stpr
//
// Add property change callbacks for getter/setter methods
//

void probe_mpsk_snr_est_c_i::createBlock()
{
    //
    // gr_sptr = xxx_make_xxx( args );
    //

    snr_est_type_t curr_type = SNR_EST_SIMPLE;
    if (this->type == 0){
        curr_type = SNR_EST_SIMPLE;
    } else if (this->type == 1){
        curr_type = SNR_EST_SKEW;
    } else if (this->type == 2){
        curr_type = SNR_EST_M2M4;
    } else if (this->type == 3){
        curr_type = SNR_EST_SVR;
    }

    gr_sptr = digital_make_probe_mpsk_snr_est_c( curr_type, this->msg_nsamples, this->alpha );

    this->registerGetterSetter("msg_nsamples",
                               &digital_probe_mpsk_snr_est_c::msg_nsample,
                               &digital_probe_mpsk_snr_est_c::set_msg_nsample);

    this->registerGetterSetter("alpha",
                               &digital_probe_mpsk_snr_est_c::alpha,
                               &digital_probe_mpsk_snr_est_c::set_alpha);

    this->setPropertyChangeListener("type",
                                    this,
                                    &probe_mpsk_snr_est_c_i::set_type);

    this->registerGetValue("snr",
                           this,
                           &probe_mpsk_snr_est_c_i::get_snr);

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


