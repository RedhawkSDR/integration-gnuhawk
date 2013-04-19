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


#include "firdes_base.h"

/*******************************************************************************************

    AUTO-GENERATED CODE. DO NOT MODIFY
    
 	Source: firdes.spd.xml
 	Generated on: Mon Feb 25 16:23:36 EST 2013
 	Redhawk IDE
 	Version:M.1.8.3
 	Build id: v201302151037

*******************************************************************************************/

//
//  Allow for logging 
// 
PREPARE_LOGGING(firdes_base);


inline static unsigned int
round_up (unsigned int n, unsigned int multiple)
{
  return ((n + multiple - 1) / multiple) * multiple;
}

inline static unsigned int
round_down (unsigned int n, unsigned int multiple)
{
  return (n / multiple) * multiple;
}


/******************************************************************************************

    The following class functions are for the base class for the component class. To
    customize any of these functions, do not modify them here. Instead, overload them
    on the child class

******************************************************************************************/
 
firdes_base::firdes_base(const char *uuid, const char *label) :
 GnuHawkBlock(uuid, label), 
 serviceThread(0), 
 noutput_items(0), 
 _maintainTimeStamp(false),
 _throttle(false)
{
    construct();
}

void firdes_base::construct()
{
    Resource_impl::_started = false;
    loadProperties();
    serviceThread = 0;

    
    PortableServer::ObjectId_var oid;
}

/*******************************************************************************************
    Framework-level functions
    These functions are generally called by the framework to perform housekeeping.
*******************************************************************************************/
void firdes_base::initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException)
{
}

void firdes_base::start() throw (CORBA::SystemException, CF::Resource::StartError)
{
    boost::mutex::scoped_lock lock(serviceThreadLock);
    if (serviceThread == 0) {
       	serviceThread = service_thread( this, 0.1);
        serviceThread->start();
    }
    
    if (!Resource_impl::started()) {
    	Resource_impl::start();
    }
}

void firdes_base::stop() throw (CORBA::SystemException, CF::Resource::StopError)
{


    // release the child thread (if it exists)
    if (serviceThread != 0) {
      {
        boost::mutex::scoped_lock lock(serviceThreadLock);
        LOG_TRACE( firdes_base, "Stopping Service Function" );
        serviceThread->stop();
      }

      if ( !serviceThread->release()) {
         throw CF::Resource::StopError(CF::CF_NOTSET, "Processing thread did not die");
      }

      boost::mutex::scoped_lock lock(serviceThreadLock);
      if ( serviceThread ) {
        delete serviceThread;
      }
    }
    serviceThread = 0;

    if (Resource_impl::started()) {
        Resource_impl::stop();
    }
    
    LOG_TRACE( firdes_base, "COMPLETED STOP REQUEST" );
}


void firdes_base::releaseObject() throw (CORBA::SystemException, CF::LifeCycle::ReleaseError)
{
    // This function clears the component running condition so main shuts down everything
    try {
        stop();
    } catch (CF::Resource::StopError& ex) {
        // TODO - this should probably be logged instead of ignored
    }

    // deactivate ports
    releaseInPorts();
    releaseOutPorts();

 
    Resource_impl::releaseObject();
    LOG_TRACE( firdes_base, "COMPLETED RELEASE OBJECT" );
}

void firdes_base::loadProperties()
{
    addProperty(hilbert,
               "hilbert",
               "",
               "readonly",
               "",
               "external",
               "configure");

    addProperty(hilbert_ntaps,
                19, 
               "hilbert_ntaps",
               "",
               "readwrite",
               "",
               "external",
               "configure");

    addProperty(hilbert_beta,
                6.76, 
               "hilbert_beta",
               "",
               "readwrite",
               "",
               "external",
               "configure");

    addProperty(hilbert_windowtype,
                3, 
               "hilbert_windowtype",
               "",
               "readwrite",
               "",
               "external",
               "configure");

}




void firdes_base::setupIOMappings()
{
  int ninput_streams = 0;
  int noutput_streams = 0;

  if ( !validGRBlock() ) return;


  //
  // RESOLVE: Still need to resolve the issue with the input port/stream to output port.  We also need to resolve issue
  // with "ganging" ports together as an input to a GNU RADIO Block. transform cplx to real ... r/i -> float
  //
  
  LOG_DEBUG( firdes_base, "GNUHAWK IO MAPPINGS IN/OUT " << ninput_streams << "/" << noutput_streams );
  std::string sid("");

  //
  // Someone reset the GR Block so we need to clean up old mappings if they exists
  // we need to reset the io signatures and check the vlens
  //


    

     
}



 

 

firdes_base::TimeDuration firdes_base::getTargetDuration() {

  TimeDuration  t_drate;;
  uint64_t samps=0;
  double   xdelta=1.0;
  double   trate=1.0;
 

  trate = samps*xdelta;
  uint64_t sec = (uint64_t)trunc(trate);
  uint64_t usec = (uint64_t)((trate-sec)*1e6);
  t_drate = boost::posix_time::seconds(sec) + 
            boost::posix_time::microseconds(usec);
  LOG_TRACE( firdes_base, " SEC/USEC " << sec << "/"  << usec << "\n"  <<
	     " target_duration " << t_drate );
  return t_drate;
}

firdes_base::TimeDuration firdes_base::calcThrottle( TimeMark &start_time,
                                             TimeMark &end_time ) {

  TimeDuration delta;
  TimeDuration target_duration = getTargetDuration();

  if ( start_time.is_not_a_date_time() == false ) {
    TimeDuration s_dtime= end_time - start_time;
    delta = target_duration - s_dtime;
    delta /= 4;
    LOG_TRACE( firdes_base, " s_time/t_dime " << s_dtime << "/" << target_duration << "\n"  <<
	      " delta " << delta );
  }
  return delta;
}








