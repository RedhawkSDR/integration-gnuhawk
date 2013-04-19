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

#ifndef FIRDES_IMPL_BASE_H
#define FIRDES_IMPL_BASE_H

#include <boost/thread.hpp>
#include <ossie/Resource_impl.h>


#include "RH_ProcessThread.h"
#include "firdes_GnuHawkBlock.h"


class firdes_base : public GnuHawkBlock
{
  

    public:

        firdes_base(const char *uuid, const char *label);

        void start() throw (CF::Resource::StartError, CORBA::SystemException);

        void stop() throw (CF::Resource::StopError, CORBA::SystemException);

        void releaseObject() throw (CF::LifeCycle::ReleaseError, CORBA::SystemException);

        void initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException);

        void loadProperties();


    protected:

    typedef      boost::posix_time::ptime         TimeMark;
    typedef      boost::posix_time::time_duration TimeDuration;

    //
    // Enable or disable to adjusting of timestamp based on output rate
    //  
    inline void maintainTimeStamp( bool onoff=false ) {
       _maintainTimeStamp = onoff;
    };

    //
    // Enable or disable throttling of processing
    //  
    inline void setThrottle( bool onoff=false ) {
       _throttle = onoff;
    };

    //
    // getTargetDuration
    //
    // Target duration defines the expected time the service function requires
    // to produce/consume elements. For source patterns, the data output rate
    // will be used to defined the target duration.  For sink patterns, the
    // input rate of elements is used to define the target duration
    //
    virtual TimeDuration getTargetDuration();

    //
    // calcThrottle 
    //
    // Calculate the duration about that we should sleep based on processing time
    // based on value from getTargetDuration() minus processing time ( end time 
    // minus start time)
    //
    // If the value is a positive duration then the boost::this_thread::sleep
    // method is called with 1/4 of the calculated duration.
    //
    virtual TimeDuration calcThrottle( TimeMark &stime,
				       TimeMark &etime );
    //
    // createBlock
    //
    // Create the actual GNU Radio Block to that will perform the work method. The resulting
    // block object is assigned to gr_sptr
    //
    // Add property change callbacks for getter/setter methods
    //
    //
    virtual void createBlock() = 0;



    virtual void  setupIOMappings();
      
    
      






  typedef  gr_vector_const_void_star                GR_IN_BUFFERS;
  typedef  gr_vector_void_star                      GR_OUT_BUFFERS;
  typedef  gr_vector_int                            GR_BUFFER_LENGTHS;   
  



  
    
    
   ProcessThread<firdes_base> *serviceThread; 
   boost::mutex serviceThreadLock;  

  // cache variables to transferring data to/from a GNU Radio Block
  std::vector<bool>            _input_ready;
  GR_BUFFER_LENGTHS            _ninput_items_required;
  GR_BUFFER_LENGTHS            _ninput_items;
  GR_IN_BUFFERS                _input_items;
  GR_OUT_BUFFERS               _output_items;
  int32_t                      noutput_items;
  
 

  // mapping of RH ports to GNU Radio streams
     

                
        
  // Member variables exposed as properties

   std::vector<float> hilbert;
   CORBA::ULong hilbert_ntaps;
   double hilbert_beta;
   CORBA::Long hilbert_windowtype;

    
  ENABLE_LOGGING;
    
  protected:

     bool                     _maintainTimeStamp;
     bool                     _throttle;
     TimeMark                 p_start_time;
     TimeMark                 p_end_time;

 private:
     void construct();
        
  public:

  SF_State serviceFunction()
  {

    SF_State retval = NOOP;

    p_end_time =  boost::posix_time::microsec_clock::local_time();
    if ( retval == NORMAL && _throttle ) {
      TimeDuration  delta = calcThrottle( p_start_time, 
		                          p_end_time );
      if ( delta.is_not_a_date_time() == false && delta.is_negative() == false )  {
	LOG_TRACE( firdes_base, " SLEEP ...." << delta );
	boost::this_thread::sleep( delta );
      }
      else {
	LOG_TRACE( firdes_base, " NO SLEEPING...." );
      }
    }
    p_start_time = p_end_time;
       
    LOG_TRACE( firdes_base, " serviceFunction: retval:" << retval);

    return retval;
  };       
        
        
};
#endif
