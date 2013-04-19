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


#include "kludge_copy_float_base.h"

/*******************************************************************************************

    AUTO-GENERATED CODE. DO NOT MODIFY
    
 	Source: kludge_copy_float.spd.xml
 	Generated on: Tue Feb 26 11:55:44 EST 2013
 	Redhawk IDE
 	Version:M.1.8.3
 	Build id: v201302151037

*******************************************************************************************/

//
//  Allow for logging 
// 
PREPARE_LOGGING(kludge_copy_float_base);


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
 
kludge_copy_float_base::kludge_copy_float_base(const char *uuid, const char *label) :
 GnuHawkBlock(uuid, label), 
 serviceThread(0), 
 noutput_items(0),
 _sriListener(*this), 
 _maintainTimeStamp(false),
 _throttle(false)
{
    construct();
}

void kludge_copy_float_base::construct()
{
    Resource_impl::_started = false;
    loadProperties();
    serviceThread = 0;

    
    PortableServer::ObjectId_var oid;
    float_in = new BULKIO_dataFloat_In_i("float_in",&_sriListener );
    oid = ossie::corba::RootPOA()->activate_object(float_in);
    float_out = new BULKIO_dataFloat_Out_i("float_out", this);
    oid = ossie::corba::RootPOA()->activate_object(float_out);

    registerInPort(float_in);
    registerOutPort(float_out, float_out->_this());
    
}

/*******************************************************************************************
    Framework-level functions
    These functions are generally called by the framework to perform housekeeping.
*******************************************************************************************/
void kludge_copy_float_base::initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException)
{
}

void kludge_copy_float_base::start() throw (CORBA::SystemException, CF::Resource::StartError)
{
    boost::mutex::scoped_lock lock(serviceThreadLock);
    if (serviceThread == 0) {
        if ( float_in ) float_in->unblock();
       	serviceThread = service_thread( this, 0.1);
        serviceThread->start();
    }
    
    if (!Resource_impl::started()) {
    	Resource_impl::start();
    }
}

void kludge_copy_float_base::stop() throw (CORBA::SystemException, CF::Resource::StopError)
{
    if ( float_in ) float_in->block();
 
    {
      boost::mutex::scoped_lock lock(_sriMutex);
      _sriQueue.clear();
    }

    // release the child thread (if it exists)
    if (serviceThread != 0) {
      {
        boost::mutex::scoped_lock lock(serviceThreadLock);
        LOG_TRACE( kludge_copy_float_base, "Stopping Service Function" );
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
    
    LOG_TRACE( kludge_copy_float_base, "COMPLETED STOP REQUEST" );
}

CORBA::Object_ptr kludge_copy_float_base::getPort(const char* _id) throw (CORBA::SystemException, CF::PortSupplier::UnknownPort)
{

    std::map<std::string, Port_Provides_base_impl *>::iterator p_in = inPorts.find(std::string(_id));
    if (p_in != inPorts.end()) {

        if (!strcmp(_id,"float_in")) {
            BULKIO_dataFloat_In_i *ptr = dynamic_cast<BULKIO_dataFloat_In_i *>(p_in->second);
            if (ptr) {
                return BULKIO::dataFloat::_duplicate(ptr->_this());
            }
        }
    }

    std::map<std::string, CF::Port_var>::iterator p_out = outPorts_var.find(std::string(_id));
    if (p_out != outPorts_var.end()) {
        return CF::Port::_duplicate(p_out->second);
    }

    throw (CF::PortSupplier::UnknownPort());
}

void kludge_copy_float_base::releaseObject() throw (CORBA::SystemException, CF::LifeCycle::ReleaseError)
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

    delete(float_in);
    delete(float_out);
 
    Resource_impl::releaseObject();
    LOG_TRACE( kludge_copy_float_base, "COMPLETED RELEASE OBJECT" );
}

void kludge_copy_float_base::loadProperties()
{
}

  
uint32_t kludge_copy_float_base::getNOutputStreams() {
	return 0;
}


void kludge_copy_float_base::setupIOMappings()
{
  int ninput_streams = 0;
  int noutput_streams = 0;

  if ( !validGRBlock() ) return;
  
  ninput_streams  = gr_sptr->get_max_input_streams();
  gr_io_signature_sptr g_isig = gr_sptr->input_signature();  
  noutput_streams = gr_sptr->get_max_output_streams();
  gr_io_signature_sptr g_osig = gr_sptr->output_signature();

  //
  // RESOLVE: Still need to resolve the issue with the input port/stream to output port.  We also need to resolve issue
  // with "ganging" ports together as an input to a GNU RADIO Block. transform cplx to real ... r/i -> float
  //
  
  LOG_DEBUG( kludge_copy_float_base, "GNUHAWK IO MAPPINGS IN/OUT " << ninput_streams << "/" << noutput_streams );
  std::string sid("");

  //
  // Someone reset the GR Block so we need to clean up old mappings if they exists
  // we need to reset the io signatures and check the vlens
  //
 
 
   if ( _istreams.size() > 0 || _ostreams.size() > 0 ) {


 
    LOG_DEBUG(  kludge_copy_float_base, "RESET INPUT SIGNATURE SIZE:" << _istreams.size() );
    IStreamList::iterator istream;
    for ( int idx=0 ; istream != _istreams.end(); idx++, istream++ ) {
        // re-add existing stream definitons
      LOG_DEBUG(  kludge_copy_float_base, "ADD READ INDEX TO GNU RADIO BLOCK");
      if ( ninput_streams == -1 ) gr_sptr->add_read_index();

      // setup io signature 
      istream->associate( gr_sptr );
    }
 
    LOG_DEBUG(  kludge_copy_float_base, "RESET OUTPUT SIGNATURE SIZE:" << _ostreams.size() );
    OStreamList::iterator ostream;
    for ( int idx=0 ; ostream != _ostreams.end(); idx++, ostream++ ) {
        // need to evaluated new settings...???
        ostream->associate( gr_sptr );
    }

    return;
  }


     
  // setup mapping of RH Port to GNU RADIO BLOCK input streams as a 1-1 mapping (basically we ignore streamID when pulling data from port)
  // for case ninput == -1 and 1 port we map out streamID to each GNU Radio Block input stream this is done in the notifySRI callback method
  if ( ninput_streams != -1 || ( ninput_streams == -1 && inPorts.size() > 1 ) ) {
  
    int nstreams = inPorts.size();
    if ( ninput_streams != -1 ) nstreams = std::min( nstreams, ninput_streams);
    
    RH_ProvidesPortMap::iterator p_in = inPorts.begin();
    for ( int i=0; i < nstreams && p_in != inPorts.end(); i++, p_in++ ) {
      // need to add read index to GNU Radio Block for processing, 
      if ( ninput_streams == -1 ) gr_sptr->add_read_index();
      _istreams.push_back( gr_istream< BULKIO_dataFloat_In_i > ( dynamic_cast< BULKIO_dataFloat_In_i * >(p_in->second), sid, gr_sptr, i));
    }
  }
  else {   // ninput_stream is variable == -1 and  component ports == 1
    //
    // need to worry about sync between when service function starts and pushSRI happens, 
    //
    for ( RH_ProvidesPortMap::iterator p_in = inPorts.begin(); p_in != inPorts.end(); p_in++ ) {

      BULKIO_dataFloat_In_i *port = dynamic_cast< BULKIO_dataFloat_In_i * >(p_in->second);
      BULKIO::StreamSRISequence_var sris = port->activeSRIs();
      for ( uint32_t i=0 ; i < sris->length(); i++ ) {
         BULKIO::StreamSRI sri = sris[i];
         int mode = sri.mode;
	 sid = sri.streamID;
	 _istreams.push_back( gr_istream< BULKIO_dataFloat_In_i > ( port, sid, gr_sptr, i, mode ));
      }
	  
    }
  }
 

  if ( noutput_streams != -1  || (noutput_streams == -1 && outPorts.size() > 1  ) ) {
    int32_t nstreams = outPorts.size();
    if ( noutput_streams != -1 ) {
      if ( nstreams != noutput_streams ) 
         LOG_WARN( kludge_copy_float_base, "Number of OUTPUT PORTS is different than number of GNU RADIO STREAMS  PORTS/STREAMS " <<  nstreams << "/" << noutput_streams );
      nstreams = std::min( nstreams, noutput_streams);
    }

    // add number of output streams based min ( gr block output streams, or output ports)
    LOG_TRACE( kludge_copy_float_base, "setupIOMappings OutputPorts: " << nstreams );
    RH_UsesPortMap::iterator p_out = outPorts.begin();
    for ( int i=0; i < nstreams && p_out != outPorts.end(); i++, p_out++ ) {
       _ostreams.push_back( gr_ostream < BULKIO_dataFloat_Out_i >( dynamic_cast< BULKIO_dataFloat_Out_i *>(p_out->second), sid, gr_sptr, i ));
    }
  } 
  else if ( (noutput_streams == -1 && outPorts.size() == 1) && ( ninput_streams == 0 ) ) {
    // RESOLVE: should generate maps based on port connections when output streams is variable and no input stream
    RH_UsesPortMap::iterator p_out = outPorts.begin();
    uint32_t idx =0;
    std::string sid("");
    uint32_t nstreams = getNOutputStreams();
    for( ; idx < nstreams; idx++ ) {
       _ostreams.push_back( gr_ostream < BULKIO_dataFloat_Out_i >( dynamic_cast< BULKIO_dataFloat_Out_i *>(p_out->second), sid, gr_sptr, idx ));
    }
  }

  // for each output stream definition, call createOutputSRI to create initial value for the stream 
  OStreamList::iterator ostream = _ostreams.begin();
  for ( int i=0;  ostream != _ostreams.end(); i++, ostream++ ) {
    BULKIO::StreamSRI sri = createOutputSRI( i );
    ostream->setSRI(sri, i );
    ostream->pushSRI();
  }
  
     
}



void kludge_copy_float_base::notifySRI( BULKIO_dataFloat_In_i *port, BULKIO::StreamSRI &sri ) {

  LOG_TRACE( kludge_copy_float_base, "START NotifySRI  port:stream " << port->getName() << "/" << sri.streamID);
  boost::mutex::scoped_lock lock(_sriMutex);
  _sriQueue.push_back( std::make_pair( port, sri ) );
  LOG_TRACE( kludge_copy_float_base, "END  NotifySRI  QUEUE " << _sriQueue.size() << " port:stream " << port->getName() << "/" << sri.streamID); 
  
}
 
void kludge_copy_float_base::processStreamIdChanges() {

  boost::mutex::scoped_lock lock(_sriMutex);

  LOG_TRACE( kludge_copy_float_base, "processStreamIDChanges QUEUE: " << _sriQueue.size()  );
  if (  _sriQueue.size() == 0 ) return;
  std::string sid("");

  if ( validGRBlock() ) {
    int max_input =  gr_sptr->get_max_input_streams();
    int n_input = gr_sptr->get_num_input_streams();
    LOG_TRACE( kludge_copy_float_base, " IN_MAX=" << max_input << " N_IN:" << n_input);
    int max_output =  gr_sptr->get_max_output_streams();
    int n_output = gr_sptr->get_num_output_streams(); 
    LOG_TRACE( kludge_copy_float_base, " OUT_MAX=" << max_output << " N_OUT:" << n_output);          

    bool var_istreams = false;
    if ( max_input == -1 && inPorts.size() == 1 ) 
       var_istreams = true;

    bool var_ostreams = false;
    if ( max_output == -1 && outPorts.size() == 1 ) 
       var_ostreams = true;    

    IStreamList::iterator istream;
    int idx=0;
    std::string sid("");
    int mode=0;
    SRIQueue::iterator item = _sriQueue.begin();
    
    for ( ; item != _sriQueue.end(); item++ ) {
       idx = 0;
       sid = "";
       mode= item->second.mode;
       sid = item->second.streamID;
       istream = _istreams.begin();
       for ( ; istream != _istreams.end(); idx++, istream++ ) {
	   if ( !var_istreams && istream->port == item->first ) {
	      break;
	   }
	   else if ( var_istreams && (istream->port == item->first) && (istream->streamID == sid) )  {
	        break;
	  }
       }

       if ( istream == _istreams.end() ) {
          if ( var_istreams )  {
	     if ( gr_sptr ) gr_sptr->add_read_index();
	     _istreams.push_back( gr_istream< BULKIO_dataFloat_In_i >( item->first, sid, gr_sptr, idx, mode ) );
             LOG_TRACE( kludge_copy_float_base, "GR_ISTREAM::ADD  PORT:" << item->first->getName() << " STREAM_ID:" << sid << " NSTREAMS:" << _istreams.size());

	     if ( var_ostreams ) {
	     	RH_UsesPortMap::iterator p_out = outPorts.begin();
		_ostreams.push_back( gr_ostream < BULKIO_dataFloat_Out_i >( dynamic_cast< BULKIO_dataFloat_Out_i *>(p_out->second), sid, gr_sptr, idx ));
	        LOG_TRACE( kludge_copy_float_base, "GR_OSTREAM::ADD  PORT:" << p_out->second->getName() << " STREAM_ID:" << sid << " OSTREAMS:" << _ostreams.size());
             }    
	  }
	  else {
	       LOG_WARN( kludge_copy_float_base, " NEW STREAM ID, MISSING INPUT STREAM DEFINITION"  );
	  }
       }
       else if ( !var_istreams ) {
	    LOG_DEBUG(  kludge_copy_float_base,  "  SETTING GR_OSTREAM ID/STREAM_ID :" << idx << "/" << sid  );
	    istream->sri(true);
	    istream->spe(mode);
       }

       LOG_DEBUG(  kludge_copy_float_base,  "  GR_OSTREAM ID/STREAM_ID :" << idx << "/" << sid  );
       setOutputStreamSRI( idx, item->second );
	        
    }

     _sriQueue.clear();
     
  }
  else {
 	LOG_WARN(  kludge_copy_float_base, " NEW STREAM ID, NO GNU RADIO BLOCK DEFINED, SRI QUEUE SIZE:" << _sriQueue.size() );
  }
      

 LOG_TRACE(  kludge_copy_float_base,  "processStreamID,  GR_ISTREAM MAP SIZE:"  << _istreams.size() );

}



BULKIO::StreamSRI kludge_copy_float_base::createOutputSRI( int32_t idx)
{
  // for each output stream set the SRI context
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
  t << naming_service_name.c_str() << "_" << idx;
  std::string sid = t.str();
  sri.streamID = CORBA::string_dup(sid.c_str());
  
  return sri;
 
} 


void kludge_copy_float_base::adjustOutputRate(BULKIO::StreamSRI &sri ) {

   if ( validGRBlock() ) {
      double ret=sri.xdelta*gr_sptr->relative_rate();
/**      
**/
      LOG_TRACE(kludge_copy_float_base, "ADJUSTING SRI.XDELTA FROM/TO: " << sri.xdelta << "/" << ret );
      sri.xdelta = ret;

   }
   
} 

kludge_copy_float_base::TimeDuration kludge_copy_float_base::getTargetDuration() {

  TimeDuration  t_drate;;
  uint64_t samps=0;
  double   xdelta=1.0;
  double   trate=1.0;

  if ( _ostreams.size() > 0 ) {
    samps= _ostreams[0].nelems();
    xdelta= _ostreams[0].sri.xdelta;
  } 

  trate = samps*xdelta;
  uint64_t sec = (uint64_t)trunc(trate);
  uint64_t usec = (uint64_t)((trate-sec)*1e6);
  t_drate = boost::posix_time::seconds(sec) + 
            boost::posix_time::microseconds(usec);
  LOG_TRACE( kludge_copy_float_base, " SEC/USEC " << sec << "/"  << usec << "\n"  <<
	     " target_duration " << t_drate );
  return t_drate;
}

kludge_copy_float_base::TimeDuration kludge_copy_float_base::calcThrottle( TimeMark &start_time,
                                             TimeMark &end_time ) {

  TimeDuration delta;
  TimeDuration target_duration = getTargetDuration();

  if ( start_time.is_not_a_date_time() == false ) {
    TimeDuration s_dtime= end_time - start_time;
    delta = target_duration - s_dtime;
    delta /= 4;
    LOG_TRACE( kludge_copy_float_base, " s_time/t_dime " << s_dtime << "/" << target_duration << "\n"  <<
	      " delta " << delta );
  }
  return delta;
}



template <  typename IN_PORT_TYPE, typename OUT_PORT_TYPE > int kludge_copy_float_base::_transformerServiceFunction( typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams ,
											typename  std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams  )
{
  typedef typename std::vector< gr_istream< IN_PORT_TYPE > >   _IStreamList;
  typedef typename std::vector< gr_ostream< OUT_PORT_TYPE > >  _OStreamList;

  boost::mutex::scoped_lock lock(serviceThreadLock);

  if ( validGRBlock() == false ) {

    // create our processing block, and setup  property notifiers
    createBlock();

    // create input/output port-stream mapping
    setupIOMappings();

    LOG_DEBUG( kludge_copy_float_base, " FINISHED BUILDING  GNU RADIO BLOCK");
  }
 
  //process any Stream ID changes this could affect number of io streams
  processStreamIdChanges();

  if ( !validGRBlock() || istreams.size() == 0 || ostreams.size() == 0  ) {
    LOG_WARN(kludge_copy_float_base, "NO STREAMS ATTACHED TO BLOCK..." );
    return NOOP;
  }

  _input_ready.resize( istreams.size() );
  _ninput_items_required.resize( istreams.size() );
  _ninput_items.resize( istreams.size() );
  _input_items.resize( istreams.size() );
  _output_items.resize( ostreams.size() );

  //
  // RESOLVE: need to look at forecast strategy, 
  //    1)  see how many read items are necessary for N number of outputs
  //    2)  read input data and see how much output we can produce
  //

  //
  // Grab available data from input streams
  //
  typename _OStreamList::iterator ostream;
  typename _IStreamList::iterator istream = istreams.begin();
  int nitems=0;
  for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {
    // note this a blocking read that can cause deadlocks
    nitems = istream->read();
    
    if ( istream->overrun() ) {
      LOG_WARN( kludge_copy_float_base, " NOT KEEPING UP WITH STREAM ID:" << istream->streamID );
    }

    if ( istream->sriChanged() ) {
      // RESOLVE - need to look at how SRI changes can affect Gnu Radio BLOCK state
      LOG_DEBUG( kludge_copy_float_base, "SRI CHANGED, STREAMD IDX/ID: " 
               << idx << "/" << istream->pkt->streamID );
      setOutputStreamSRI( idx, istream->pkt->SRI );
    }

  }

  LOG_TRACE( kludge_copy_float_base, "READ NITEMS: "  << nitems );
  if ( nitems <= 0 && !_istreams[0].eos() ) return NOOP;

  bool exitServiceFunction = false;
  bool eos = false;
  int  nout = 0;
  while ( nout > -1 && !exitServiceFunction && serviceThread->threadRunning() ) {

    eos = false;
    nout = _forecastAndProcess( eos, istreams, ostreams );
    if ( nout > -1  ) {

      // we chunked on data so move read pointer..
      istream = istreams.begin();
      for ( ; istream != istreams.end(); istream++ ) {
	int idx=std::distance( istreams.begin(), istream );
	// if we processed data for this stream
	if ( _input_ready[idx] ) {
	  size_t nitems = 0;
	  try {
	    nitems = gr_sptr->nitems_read( idx );
	  }
	  catch(...){}
      
	  istream->consume( nitems );
	  LOG_TRACE( kludge_copy_float_base, " CONSUME READ DATA  ITEMS/REMAIN " << nitems << "/" << istream->nitems());
	}

      }
      gr_sptr->reset_read_index();
    }

    // check for not enough data return
    if ( nout == -1 ) {

      // check for  end of stream
      istream = istreams.begin();
      for ( ; istream != istreams.end() ; istream++) if ( istream->eos() ) eos=true;

      if ( eos ) {
        LOG_TRACE(  kludge_copy_float_base, " DATA NOT READY, EOS:" << eos );
	_forecastAndProcess( eos, istreams, ostreams);
      }

      exitServiceFunction = true;
    }

  }

  if ( eos ) {

    istream = istreams.begin();
    for ( ; istream != istreams.end() ; ) {

      int idx=std::distance( istreams.begin(), istream );
      if (  istream->eos() || eos == true ) {
	LOG_DEBUG( kludge_copy_float_base, " CLOSING INPUT STREAM IDX:" << idx );
         istream->close();
         //
         // if we are variable input list of input streams and fixed output...... humm
         //
         if ( gr_sptr->get_max_input_streams() == -1 ) {
            LOG_DEBUG( kludge_copy_float_base, " REMOVE VARIABLE INPUT STREAM IDX:" << idx );
            istream=istreams.erase( istream );
            gr_sptr->remove_read_index( idx );

	    // if output is variable then close the corresponding output stream
	    if ( gr_sptr->get_max_output_streams() == -1 && outPorts.size() == 1 ) {
	       ostream = ostreams.begin();
	       for ( int i=0; ostream != ostreams.end(); i++, ostream++) {
	          if ( i == idx ) {
                     LOG_DEBUG( kludge_copy_float_base, " REMOVE VARIABLE OUTPUT STREAM IDX:" << idx );
	             ostream=ostreams.erase(ostream);
		     break;
                  }
               }
            }
         }
         else {
            istream++;
         }
      }

    }

    // close remaining output streams
    ostream = ostreams.begin();
    for ( ; eos && ostream != ostreams.end(); ostream++ ) ostream->close();

  }

  //
  // set the read pointers of the GNU Radio Block to start at the beginning of the 
  // supplied buffers
  //
  gr_sptr->reset_read_index();

  LOG_TRACE( kludge_copy_float_base, " END OF TRANSFORM SERVICE FUNCTION....." << noutput_items );

  if ( nout == -1 && eos == false )
    return NOOP;    
  else
    return NORMAL;

}


template <  typename IN_PORT_TYPE, typename OUT_PORT_TYPE > int kludge_copy_float_base::_forecastAndProcess( bool &eos, typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams ,
											typename  std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams  )
{
  typedef typename std::vector< gr_istream< IN_PORT_TYPE > >   _IStreamList;
  typedef typename std::vector< gr_ostream< OUT_PORT_TYPE > >  _OStreamList;

  typename _OStreamList::iterator ostream;
  typename _IStreamList::iterator istream = istreams.begin();
  int nout = 0;
  bool dataReady = false;
  if ( !eos ) {
    uint64_t max_items_avail = 0;
    for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {
      LOG_TRACE(  kludge_copy_float_base, "GET MAX ITEMS: STREAM:"<< idx << " NITEMS/SCALARS:" << istream->nitems() << "/" << istream->_data.size() );
      max_items_avail = std::max( istream->nitems(), max_items_avail );
    }

    if ( max_items_avail == 0  ) {
       LOG_TRACE( kludge_copy_float_base, "DATA CHECK - MAX ITEMS  NOUTPUT/MAX_ITEMS:" <<   noutput_items << "/" << max_items_avail);
       return -1;
    }

    //
    // calc number of output elements based on input items available
    //
    noutput_items = 0;
    if ( !gr_sptr->fixed_rate() )  {
      noutput_items = round_down((int32_t) (max_items_avail * gr_sptr->relative_rate()), gr_sptr->output_multiple());
      LOG_TRACE( kludge_copy_float_base, " VARIABLE FORECAST NOUTPUT == " << noutput_items );
    }   
    else {
      istream = istreams.begin();
      for ( int i=0; istream != istreams.end(); i++, istream++ ) {
        if ( gr_sptr->fixed_rate() ) {
          int t_noutput_items = gr_sptr->fixed_rate_ninput_to_noutput( istream->nitems() );
	  if ( gr_sptr->output_multiple_set() ) {
	    t_noutput_items = round_up(t_noutput_items, gr_sptr->output_multiple());
	  }
	  if ( t_noutput_items > 0 ) {
	    if ( noutput_items == 0 ) noutput_items = t_noutput_items;
	    if ( t_noutput_items <= noutput_items ) noutput_items = t_noutput_items;
	  }
        }
      }
      LOG_TRACE( kludge_copy_float_base,  " FIXED FORECAST NOUTPUT/output_multiple == " << noutput_items  << "/" << gr_sptr->output_multiple());
    }

    //
    // ask the block how much input they need to produce noutput_items...
    // if enough data is available to process then set the dataReady flag
    //
    int32_t  outMultiple = gr_sptr->output_multiple();
    while ( !dataReady && noutput_items >= outMultiple  ) {
      //
      // ask the block how much input they need to produce noutput_items...
      //
      gr_sptr->forecast(noutput_items, _ninput_items_required);

      LOG_TRACE( kludge_copy_float_base, "--> FORECAST IN/OUT " << _ninput_items_required[0]  << "/" << noutput_items  );

      istream = istreams.begin();
      uint32_t dr_cnt=0;
      for ( int idx=0 ; noutput_items > 0 && istream != istreams.end(); idx++, istream++ ) {
	// check if buffer has enough elements
	_input_ready[idx] = false;
	if ( istream->nitems() >= (uint64_t)_ninput_items_required[idx] ) {
	  _input_ready[idx] = true;
	  dr_cnt++;
	}
	LOG_TRACE( kludge_copy_float_base, "ISTREAM DATACHECK NELMS/NITEMS/REQ/READY:" <<   istream->nelems() << "/" << istream->nitems() << "/" << _ninput_items_required[idx] << "/" << _input_ready[idx]);
      }
    
      if ( dr_cnt < istreams.size() ) {
        if ( outMultiple > 1 )
       	  noutput_items -= outMultiple;
        else
          noutput_items /= 2;
      }
      else {
        dataReady = true;
      }
      LOG_TRACE( kludge_copy_float_base, " TRIM FORECAST NOUTPUT/READY " << noutput_items << "/" << dataReady );
    }

    // check if data is ready...
    if ( !dataReady ) {
      LOG_TRACE( kludge_copy_float_base, "DATA CHECK - NOT ENOUGH DATA  AVAIL/REQ:" <<   _istreams[0].nitems() << "/" << _ninput_items_required[0] );
      return -1;	 
    }

    // reset looping variables
    int  ritems = 0;
    int  nitems = 0;

    // reset caching vectors
    _output_items.clear();
    _input_items.clear();
    _ninput_items.clear();
    istream = istreams.begin();
    for ( int idx=0 ; istream != istreams.end(); idx++, istream++ ) {

      // check if the stream is ready
      if ( !_input_ready[idx] ) continue;
      
      // get number of items remaining
      try {
        ritems = gr_sptr->nitems_read( idx );
      }
      catch(...){
        // something bad has happened, we are missing an input stream
	LOG_ERROR( kludge_copy_float_base, "MISSING INPUT STREAM FOR GR BLOCK, STREAM ID:" <<   istream->streamID );
        return -2;
      } 
    
      nitems = istream->nitems() - ritems;
      LOG_TRACE( kludge_copy_float_base,  " ISTREAM: IDX:" << idx  << " ITEMS AVAIL/READ/REQ " << nitems << "/" 
		 << ritems << "/" << _ninput_items_required[idx] );
      if ( nitems >= _ninput_items_required[idx] && nitems > 0 ) {
	//remove eos checks ...if ( nitems < _ninput_items_required[idx] ) nitems=0;
        _ninput_items.push_back( nitems );
	_input_items.push_back( (const void *) (istream->read_pointer(ritems)) );
      }
    }

    //
    // setup output buffer vector based on noutput..
    //
    ostream = ostreams.begin();
    for( ; ostream != ostreams.end(); ostream++ ) {
      ostream->resize(noutput_items);
      _output_items.push_back((void*)(ostream->write_pointer()) );
    }

    nout=0;
    if ( _input_items.size() != 0 && serviceThread->threadRunning() ) {
      LOG_TRACE( kludge_copy_float_base, " CALLING WORK.....N_OUT:" << noutput_items << " N_IN:" << nitems << " ISTREAMS:" << _input_items.size() << " OSTREAMS:" << _output_items.size());
      nout = gr_sptr->general_work( noutput_items, _ninput_items, _input_items, _output_items);
      LOG_TRACE( kludge_copy_float_base, "RETURN  WORK ..... N_OUT:" << nout);
    }

    // check for stop condition from work method
    if ( nout < gr_block::WORK_DONE ) {
      LOG_WARN( kludge_copy_float_base, "WORK RETURNED STOP CONDITION..." << nout );
      nout=0;
      eos = true;
    }
  }

  if (nout != 0 or eos ) {

    noutput_items = nout;
    LOG_TRACE( kludge_copy_float_base, " WORK RETURNED: NOUT : " << nout << " EOS:" << eos);
    ostream = ostreams.begin();
    typename IN_PORT_TYPE::dataTransfer *pkt=NULL;
    for ( int idx=0 ; ostream != ostreams.end(); idx++, ostream++ ) {

      pkt=NULL;
      int inputIdx = idx;
      if ( (size_t)(inputIdx) >= istreams.size() ) {
	for ( inputIdx= istreams.size()-1; inputIdx > -1; inputIdx--) {
	  if ( istreams[inputIdx].pkt != NULL ) {
	    pkt = istreams[inputIdx].pkt;
	    break;
	  }
	}
      }
      else {
	pkt = istreams[inputIdx].pkt;
      }

      LOG_TRACE( kludge_copy_float_base,  "PUSHING DATA   ITEMS/STREAM_ID " << ostream->nitems() << "/" << ostream->streamID );    
      if ( _maintainTimeStamp ) {

	// set time stamp for output samples based on input time stamp
	if ( ostream->nelems() == 0 )  {
#ifdef TEST_TIME_STAMP
	  LOG_DEBUG(  kludge_copy_float_base, "SEED - TS SRI:  xdelta:" << std::setprecision(12) << ostream->sri.xdelta );
	  LOG_DEBUG(  kludge_copy_float_base, "OSTREAM WRITE:   maint:" << _maintainTimeStamp );
	  LOG_DEBUG(  kludge_copy_float_base, "                  mode:" <<  ostream->tstamp.tcmode );
	  LOG_DEBUG(  kludge_copy_float_base, "                status:" <<  ostream->tstamp.tcstatus );
	  LOG_DEBUG(  kludge_copy_float_base, "                offset:" <<  ostream->tstamp.toff );
	  LOG_DEBUG(  kludge_copy_float_base, "                 whole:" <<  std::setprecision(10) << ostream->tstamp.twsec );
	  LOG_DEBUG(  kludge_copy_float_base, "SEED - TS         frac:" <<  std::setprecision(12) << ostream->tstamp.tfsec );
#endif
	  ostream->setTimeStamp( pkt->T, _maintainTimeStamp );
	}

	// write out samples, and set next time stamp based on xdelta and  noutput_items
	ostream->write ( noutput_items, eos );

      }
      else {
	// use incoming packet's time stamp to forward
	if ( pkt ) {
#ifdef TEST_TIME_STAMP
	  LOG_DEBUG(  kludge_copy_float_base, "OSTREAM  SRI:  items/xdelta:" << noutput_items << "/" << std::setprecision(12) << ostream->sri.xdelta );
	  LOG_DEBUG(  kludge_copy_float_base, "PKT - TS         maint:" << _maintainTimeStamp );
	  LOG_DEBUG(  kludge_copy_float_base, "                  mode:" <<  pkt->T.tcmode );
	  LOG_DEBUG(  kludge_copy_float_base, "                status:" <<  pkt->T.tcstatus );
	  LOG_DEBUG(  kludge_copy_float_base, "                offset:" <<  pkt->T.toff );
	  LOG_DEBUG(  kludge_copy_float_base, "                 whole:" <<  std::setprecision(10) << pkt->T.twsec );
	  LOG_DEBUG(  kludge_copy_float_base, "PKT - TS          frac:" <<  std::setprecision(12) << pkt->T.tfsec );
#endif
	  ostream->write( noutput_items, eos, pkt->T  );	   
	}
	else {
#ifdef TEST_TIME_STAMP
	  LOG_DEBUG(  kludge_copy_float_base, "OSTREAM  SRI:  items/xdelta:" << noutput_items << "/" << std::setprecision(12) << ostream->sri.xdelta );
	  LOG_DEBUG(  kludge_copy_float_base, "OSTREAM TOD      maint:" << _maintainTimeStamp );
	  LOG_DEBUG(  kludge_copy_float_base, "                  mode:" <<  ostream->tstamp.tcmode );
	  LOG_DEBUG(  kludge_copy_float_base, "                status:" <<  ostream->tstamp.tcstatus );
	  LOG_DEBUG(  kludge_copy_float_base, "                offset:" <<  ostream->tstamp.toff );
	  LOG_DEBUG(  kludge_copy_float_base, "                 whole:" <<  std::setprecision(10) << ostream->tstamp.twsec );
	  LOG_DEBUG(  kludge_copy_float_base, "OSTREAM TOD       frac:" <<  std::setprecision(12) << ostream->tstamp.tfsec );
#endif
	  // use time of day as time stamp
	  ostream->write( noutput_items, eos,  _maintainTimeStamp );	   
	}
      }

    } // for ostreams

  }

  return nout;
     
}








