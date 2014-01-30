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

#include "multiply_cc_2i_base.h"

/*******************************************************************************************

    AUTO-GENERATED CODE. DO NOT MODIFY

    The following class functions are for the base class for the component class. To
    customize any of these functions, do not modify them here. Instead, overload them
    on the child class

******************************************************************************************/

multiply_cc_2i_base::multiply_cc_2i_base(const char *uuid, const char *label) :
    GnuHawkBlock(uuid, label),
    serviceThread(0),
    noutput_items(0),
    _maintainTimeStamp(false),
    _throttle(false)
{
    construct();
}

void multiply_cc_2i_base::construct()
{
    Resource_impl::_started = false;
    loadProperties();
    serviceThread = 0;
    sentEOS = false;
    inputPortOrder.resize(0);;
    outputPortOrder.resize(0);

    PortableServer::ObjectId_var oid;
    data_complex_in_0 = new bulkio::InFloatPort("data_complex_in_0");
    data_complex_in_0->setNewStreamListener(this, &multiply_cc_2i_base::data_complex_in_0_newStreamCallback);
    oid = ossie::corba::RootPOA()->activate_object(data_complex_in_0);
    data_complex_in_1 = new bulkio::InFloatPort("data_complex_in_1");
    data_complex_in_1->setNewStreamListener(this, &multiply_cc_2i_base::data_complex_in_1_newStreamCallback);
    oid = ossie::corba::RootPOA()->activate_object(data_complex_in_1);
    data_complex_out = new bulkio::OutFloatPort("data_complex_out");
    oid = ossie::corba::RootPOA()->activate_object(data_complex_out);

    registerInPort(data_complex_in_0);
    inputPortOrder.push_back("data_complex_in_0");
    registerInPort(data_complex_in_1);
    inputPortOrder.push_back("data_complex_in_1");
    registerOutPort(data_complex_out, data_complex_out->_this());
    outputPortOrder.push_back("data_complex_out");

}

/*******************************************************************************************
    Framework-level functions
    These functions are generally called by the framework to perform housekeeping.
*******************************************************************************************/
void multiply_cc_2i_base::initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException)
{
}

void multiply_cc_2i_base::start() throw (CORBA::SystemException, CF::Resource::StartError)
{
    boost::mutex::scoped_lock lock(serviceThreadLock);
    if (serviceThread == 0) {
        data_complex_in_0->unblock();
        data_complex_in_1->unblock();
        serviceThread = new ProcessThread<multiply_cc_2i_base>(this, 0.1);
        serviceThread->start();
    }
    
    if (!Resource_impl::started()) {
    	Resource_impl::start();
    }
}

void multiply_cc_2i_base::stop() throw (CORBA::SystemException, CF::Resource::StopError)
{
    if ( data_complex_in_0 ) data_complex_in_0->block();
    if ( data_complex_in_1 ) data_complex_in_1->block();
    {
        boost::mutex::scoped_lock lock(_sriMutex);
        _sriQueue.clear();
    }

    // release the child thread (if it exists)
    if (serviceThread != 0) {
      {
        boost::mutex::scoped_lock lock(serviceThreadLock);
        LOG_TRACE( multiply_cc_2i_base, "Stopping Service Function" );
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
    
    LOG_TRACE( multiply_cc_2i_base, "COMPLETED STOP REQUEST" );
}

CORBA::Object_ptr multiply_cc_2i_base::getPort(const char* _id) throw (CORBA::SystemException, CF::PortSupplier::UnknownPort)
{

    std::map<std::string, Port_Provides_base_impl *>::iterator p_in = inPorts.find(std::string(_id));
    if (p_in != inPorts.end()) {
        if (!strcmp(_id,"data_complex_in_0")) {
            bulkio::InFloatPort *ptr = dynamic_cast<bulkio::InFloatPort *>(p_in->second);
            if (ptr) {
                return ptr->_this();
            }
        }
        if (!strcmp(_id,"data_complex_in_1")) {
            bulkio::InFloatPort *ptr = dynamic_cast<bulkio::InFloatPort *>(p_in->second);
            if (ptr) {
                return ptr->_this();
            }
        }
    }

    std::map<std::string, CF::Port_var>::iterator p_out = outPorts_var.find(std::string(_id));
    if (p_out != outPorts_var.end()) {
        return CF::Port::_duplicate(p_out->second);
    }

    throw (CF::PortSupplier::UnknownPort());
}

void multiply_cc_2i_base::releaseObject() throw (CORBA::SystemException, CF::LifeCycle::ReleaseError)
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

    delete(data_complex_in_0);
    delete(data_complex_in_1);
    delete(data_complex_out);

    Resource_impl::releaseObject();
}

void multiply_cc_2i_base::loadProperties()
{
    addProperty(vlen,
                1,
                "vlen",
                "",
                "readwrite",
                "",
                "external",
                "configure");

}


// Destructor
multiply_cc_2i_base::~multiply_cc_2i_base()
{
    // Free input streams
    for (IStreamList::iterator iter = _istreams.begin(); iter != _istreams.end(); ++iter) {
        delete (*iter);
    }
    // Free output streams
    for (OStreamList::iterator iter = _ostreams.begin(); iter != _ostreams.end(); ++iter) {
        delete (*iter);
    }
}

//
//  Allow for logging 
// 
PREPARE_LOGGING(multiply_cc_2i_base);

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

uint32_t multiply_cc_2i_base::getNOutputStreams() {
    return 0;
}

void multiply_cc_2i_base::setupIOMappings( )
{
    int ninput_streams = 0;
    int noutput_streams = 0;
    std::string sid("");
    int inMode=RealMode;

    if ( !validGRBlock() ) return;
    ninput_streams  = gr_sptr->get_max_input_streams();
    gr_io_signature_sptr g_isig = gr_sptr->input_signature();

    noutput_streams = gr_sptr->get_max_output_streams();
    gr_io_signature_sptr g_osig = gr_sptr->output_signature();

    LOG_DEBUG( multiply_cc_2i_base, "GNUHAWK IO MAPPINGS IN/OUT " << ninput_streams << "/" << noutput_streams );

    //
    // Someone reset the GR Block so we need to clean up old mappings if they exists
    // we need to reset the io signatures and check the vlens
    //
    if ( _istreams.size() > 0 || _ostreams.size() > 0 ) {

        LOG_DEBUG( multiply_cc_2i_base, "RESET INPUT SIGNATURE SIZE:" << _istreams.size() );
        IStreamList::iterator istream;
        for ( int idx=0 ; istream != _istreams.end(); idx++, istream++ ) {
            // re-add existing stream definitons
            LOG_DEBUG(  multiply_cc_2i_base, "ADD READ INDEX TO GNU RADIO BLOCK");
            if ( ninput_streams == -1 ) gr_sptr->add_read_index();

            // setup io signature
            (*istream)->associate( gr_sptr );
        }

        LOG_DEBUG( multiply_cc_2i_base, "RESET OUTPUT SIGNATURE SIZE:" << _ostreams.size() );
        OStreamList::iterator ostream;
        for ( int idx=0 ; ostream != _ostreams.end(); idx++, ostream++ ) {
            // need to evaluate new settings...???
            (*ostream)->associate( gr_sptr );
        }

        return;
    }

    int i = 0;
   //
   // Setup mapping of RH port to GNU RADIO Block input streams
   // For version 1,  we are ignoring the GNU Radio input stream -1 case that allows multiple data 
   // streams over a single connection.  We are mapping a single RH Port to a single GNU Radio stream.
   // Stream Identifiers will  be pass along as they are received
   //
    LOG_TRACE( multiply_cc_2i_base, "setupIOMappings INPUT PORTS: " << inPorts.size() );
    RH_ProvidesPortMap::iterator p_in;
    i = 0;
    // grab ports based on their order in the scd.xml file
    p_in = inPorts.find("data_complex_in_0");
    if ( p_in != inPorts.end() ) {
        bulkio::InFloatPort *port = dynamic_cast< bulkio::InFloatPort * >(p_in->second);
        int mode = inMode;
        sid = "";

        // need to add read index to GNU Radio Block for processing streams when max_input == -1
        if ( ninput_streams == -1 ) gr_sptr->add_read_index();

        // check if we received SRI during setup
        BULKIO::StreamSRISequence_var sris = port->activeSRIs();
        if (  sris->length() > 0 ) {
            BULKIO::StreamSRI sri = sris[sris->length()-1];
            mode = sri.mode;
        }
        std::vector<int> in;
        io_mapping.push_back( in );
        _istreams.push_back( new gr_istream< bulkio::InFloatPort > ( port, gr_sptr, i, mode, sid ));
        LOG_DEBUG( multiply_cc_2i_base, "ADDING INPUT MAP IDX:" << i << " SID:" << sid );
        // increment port counter
        i++;
    }

    // grab ports based on their order in the scd.xml file
    p_in = inPorts.find("data_complex_in_1");
    if ( p_in != inPorts.end() ) {
        bulkio::InFloatPort *port = dynamic_cast< bulkio::InFloatPort * >(p_in->second);
        int mode = inMode;
        sid = "";

        // need to add read index to GNU Radio Block for processing streams when max_input == -1
        if ( ninput_streams == -1 ) gr_sptr->add_read_index();

        // check if we received SRI during setup
        BULKIO::StreamSRISequence_var sris = port->activeSRIs();
        if (  sris->length() > 0 ) {
            BULKIO::StreamSRI sri = sris[sris->length()-1];
            mode = sri.mode;
        }
        std::vector<int> in;
        io_mapping.push_back( in );
        _istreams.push_back( new gr_istream< bulkio::InFloatPort > ( port, gr_sptr, i, mode, sid ));
        LOG_DEBUG( multiply_cc_2i_base, "ADDING INPUT MAP IDX:" << i << " SID:" << sid );
        // increment port counter
        i++;
    }

    //
    // Setup mapping of RH port to GNU RADIO Block input streams
    // For version 1,  we are ignoring the GNU Radio output stream -1 case that allows multiple data 
    // streams over a single connection.  We are mapping a single RH Port to a single GNU Radio stream.
    //
    LOG_TRACE( multiply_cc_2i_base, "setupIOMappings OutputPorts: " << outPorts.size() );
    RH_UsesPortMap::iterator p_out;
    i = 0;
    // grab ports based on their order in the scd.xml file
    p_out = outPorts.find("data_complex_out");
    if ( p_out != outPorts.end() ) {
        bulkio::OutFloatPort *port = dynamic_cast< bulkio::OutFloatPort * >(p_out->second);
        int idx = -1;
        BULKIO::StreamSRI sri = createOutputSRI( i, idx );
        if (idx == -1) idx = i;
        if(idx < (int)io_mapping.size()) io_mapping[idx].push_back(i);
        int mode = sri.mode;
        sid = sri.streamID;
        _ostreams.push_back( new gr_ostream< bulkio::OutFloatPort > ( port, gr_sptr, i, mode, sid ));
        LOG_DEBUG( multiply_cc_2i_base, "ADDING OUTPUT MAP IDX:" << i << " SID:" << sid );
        _ostreams[i]->setSRI(sri, i );
        // increment port counter
        i++;
    }

}

void multiply_cc_2i_base::data_complex_in_0_newStreamCallback( BULKIO::StreamSRI &sri )
{
  LOG_TRACE( multiply_cc_2i_base, "START NotifySRI  port:stream " << data_complex_in_0->getName() << "/" << sri.streamID);

  boost::mutex::scoped_lock lock(_sriMutex);
  _sriQueue.push_back( std::make_pair( data_complex_in_0, sri ) );

  LOG_TRACE( multiply_cc_2i_base, "END  NotifySRI  QUEUE " << _sriQueue.size() << " port:stream " << data_complex_in_0->getName() << "/" << sri.streamID); 
}

void multiply_cc_2i_base::data_complex_in_1_newStreamCallback( BULKIO::StreamSRI &sri )
{
  LOG_TRACE( multiply_cc_2i_base, "START NotifySRI  port:stream " << data_complex_in_1->getName() << "/" << sri.streamID);

  boost::mutex::scoped_lock lock(_sriMutex);
  _sriQueue.push_back( std::make_pair( data_complex_in_1, sri ) );

  LOG_TRACE( multiply_cc_2i_base, "END  NotifySRI  QUEUE " << _sriQueue.size() << " port:stream " << data_complex_in_1->getName() << "/" << sri.streamID); 
}


void multiply_cc_2i_base::processStreamIdChanges()
{
    boost::mutex::scoped_lock lock(_sriMutex);

    LOG_TRACE( multiply_cc_2i_base, "processStreamIDChanges QUEUE: " << _sriQueue.size()  );
    if (  _sriQueue.size() == 0 ) return;
    std::string sid("");

    if ( validGRBlock() ) {

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

                if ( (*istream)->getPort() == item->first ) {
                    LOG_DEBUG( multiply_cc_2i_base,  "  SETTING IN_STREAM ID/STREAM_ID :" << idx << "/" << sid  );
                    (*istream)->sri(true);
                    (*istream)->spe(mode);

                    LOG_DEBUG( multiply_cc_2i_base,  "  SETTING  OUT_STREAM ID/STREAM_ID :" << idx << "/" << sid  );
                    setOutputStreamSRI( idx, item->second );
                }
            }
        }

        _sriQueue.clear();

    } else {
        LOG_WARN( multiply_cc_2i_base, " NEW STREAM ID, NO GNU RADIO BLOCK DEFINED, SRI QUEUE SIZE:" << _sriQueue.size() );
    }

}

BULKIO::StreamSRI multiply_cc_2i_base::createOutputSRI( int32_t oidx ) {
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
    t << naming_service_name.c_str() << "_" << oidx;
    std::string sid = t.str();
    sri.streamID = CORBA::string_dup(sid.c_str());
  
    return sri;
}

BULKIO::StreamSRI multiply_cc_2i_base::createOutputSRI( int32_t oidx, int32_t &in_idx)
{
    return createOutputSRI( oidx );
}

void multiply_cc_2i_base::adjustOutputRate(BULKIO::StreamSRI &sri )
{
    if ( validGRBlock() ) {
        double ret=sri.xdelta*gr_sptr->relative_rate();
/**
**/
        LOG_TRACE( multiply_cc_2i_base, "ADJUSTING SRI.XDELTA FROM/TO: " << sri.xdelta << "/" << ret );
        sri.xdelta = ret;
    }
}

multiply_cc_2i_base::TimeDuration multiply_cc_2i_base::getTargetDuration()
{
    TimeDuration  t_drate;;
    uint64_t samps=0;
    double   xdelta=1.0;
    double   trate=1.0;

    if ( _ostreams.size() > 0 ) {
        samps= _ostreams[0]->nelems();
        xdelta= _ostreams[0]->sri.xdelta;
    }

    trate = samps*xdelta;
    uint64_t sec = (uint64_t)trunc(trate);
    uint64_t usec = (uint64_t)((trate-sec)*1e6);
    t_drate = boost::posix_time::seconds(sec) + 
            boost::posix_time::microseconds(usec);
    LOG_TRACE( multiply_cc_2i_base, " SEC/USEC " << sec << "/"  << usec << "\n"  <<
              " target_duration " << t_drate );
    return t_drate;
}

multiply_cc_2i_base::TimeDuration multiply_cc_2i_base::calcThrottle( TimeMark &start_time,
                                             TimeMark &end_time )
{
    TimeDuration delta;
    TimeDuration target_duration = getTargetDuration();

    if ( start_time.is_not_a_date_time() == false ) {
        TimeDuration s_dtime= end_time - start_time;
        delta = target_duration - s_dtime;
        delta /= 4;
        LOG_TRACE( multiply_cc_2i_base, " s_time/t_dime " << s_dtime << "/" << target_duration << "\n"  <<
                  " delta " << delta );
    }

    return delta;
}

int multiply_cc_2i_base::_transformerServiceFunction( std::vector< gr_istream_base * > &istreams ,
    std::vector< gr_ostream_base * > &ostreams  )
{
    typedef std::vector< gr_istream_base * >   _IStreamList;
    typedef std::vector< gr_ostream_base * >  _OStreamList;

    boost::mutex::scoped_lock lock(serviceThreadLock);

    if ( validGRBlock() == false ) {

        // create our processing block, and setup  property notifiers
        createBlock();

        LOG_DEBUG( multiply_cc_2i_base, " FINISHED BUILDING  GNU RADIO BLOCK");
    }
 
    //process any Stream ID changes this could affect number of io streams
    processStreamIdChanges();

    if ( !validGRBlock() || istreams.size() == 0 || ostreams.size() == 0  ) {
        LOG_WARN( multiply_cc_2i_base, "NO STREAMS ATTACHED TO BLOCK..." );
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
    _OStreamList::iterator ostream;
    _IStreamList::iterator istream = istreams.begin();
    int nitems=0;
    for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {
        // note this a blocking read that can cause deadlocks
        nitems = (*istream)->read();
    
        if ( (*istream)->overrun() ) {
            LOG_WARN( multiply_cc_2i_base, " NOT KEEPING UP WITH STREAM ID:" << (*istream)->streamID );
        }

        if ( (*istream)->sriChanged() ) {
            // RESOLVE - need to look at how SRI changes can affect Gnu Radio BLOCK state
            LOG_DEBUG( multiply_cc_2i_base, "SRI CHANGED, STREAMD IDX/ID: " 
                      << idx << "/" << (*istream)->getPktStreamId() );
            setOutputStreamSRI( idx, (*istream)->getPktSri() );
        }
    }

    LOG_TRACE( multiply_cc_2i_base, "READ NITEMS: "  << nitems );
    if ( nitems <= 0 && !_istreams[0]->eos() ) {
        return NOOP;
    }

    bool eos = false;
    int  nout = 0;
    bool workDone = false;

    while ( nout > -1 && serviceThread->threadRunning() ) {
        eos = false;
        nout = _forecastAndProcess( eos, istreams, ostreams );
        if ( nout > -1  ) {
            workDone = true;

            // we chunked on data so move read pointer..
            istream = istreams.begin();
            for ( ; istream != istreams.end(); istream++ ) {
                int idx=std::distance( istreams.begin(), istream );
                // if we processed data for this stream
                if ( _input_ready[idx] ) {
                    size_t nitems = 0;
                    try {
                        nitems = gr_sptr->nitems_read( idx );
                    } catch(...){}
      
                    if ( nitems > (*istream)->nitems() ) {
                        LOG_WARN( multiply_cc_2i_base,  "WORK CONSUMED MORE DATA THAN AVAILABLE,  READ/AVAILABLE "
                                 << nitems << "/" << (*istream)->nitems() );
                        nitems = (*istream)->nitems();
                    }
                    (*istream)->consume( nitems );
                    LOG_TRACE( multiply_cc_2i_base, " CONSUME READ DATA  ITEMS/REMAIN " << nitems << "/" << (*istream)->nitems());
                }
            }
            gr_sptr->reset_read_index();
        }

        // check for not enough data return
        if ( nout == -1 ) {

            // check for  end of stream
            istream = istreams.begin();
            for ( ; istream != istreams.end() ; istream++) {
                if ( (*istream)->eos() ) {
                    eos=true;
                }
            }
            if ( eos ) {
                LOG_TRACE(  multiply_cc_2i_base, "EOS SEEN, SENDING DOWNSTREAM " );
                _forecastAndProcess( eos, istreams, ostreams);
            }
        }
    }

    if ( eos ) {
        istream = istreams.begin();
        for ( ; istream != istreams.end() ; istream++ ) {
            int idx=std::distance( istreams.begin(), istream );
            LOG_DEBUG( multiply_cc_2i_base, " CLOSING INPUT STREAM IDX:" << idx );
            (*istream)->close();
        }

        // close remaining output streams
        ostream = ostreams.begin();
        for ( ; eos && ostream != ostreams.end(); ostream++ ) {
            int idx=std::distance( ostreams.begin(), ostream );
            LOG_DEBUG( multiply_cc_2i_base, " CLOSING OUTPUT STREAM IDX:" << idx );
            (*ostream)->close();
        }
    }

    //
    // set the read pointers of the GNU Radio Block to start at the beginning of the 
    // supplied buffers
    //
    gr_sptr->reset_read_index();

    LOG_TRACE( multiply_cc_2i_base, " END OF TRANSFORM SERVICE FUNCTION....." << noutput_items );

    if ( nout == -1 && eos == false && !workDone ) {
        return NOOP;
    } else {
        return NORMAL;
    }
}

int multiply_cc_2i_base::_forecastAndProcess( bool &eos, std::vector< gr_istream_base * > &istreams ,
                                 std::vector< gr_ostream_base * > &ostreams  )
{
    typedef std::vector< gr_istream_base * >   _IStreamList;
    typedef std::vector< gr_ostream_base * >  _OStreamList;

    _OStreamList::iterator ostream;
    _IStreamList::iterator istream = istreams.begin();
    int nout = 0;
    bool dataReady = false;
    if ( !eos ) {
        uint64_t max_items_avail = 0;
        for ( int idx=0 ; istream != istreams.end() && serviceThread->threadRunning() ; idx++, istream++ ) {
            LOG_TRACE( multiply_cc_2i_base, "GET MAX ITEMS: STREAM:"<< idx << " NITEMS/SCALARS:" << 
                       (*istream)->nitems() << "/" << (*istream)->nelems() );
            max_items_avail = std::max( (*istream)->nitems(), max_items_avail );
        }

        if ( max_items_avail == 0  ) {
            LOG_TRACE( multiply_cc_2i_base, "DATA CHECK - MAX ITEMS  NOUTPUT/MAX_ITEMS:" <<   noutput_items << "/" << max_items_avail);
            return -1;
        }

        //
        // calc number of output elements based on input items available
        //
        noutput_items = 0;
        if ( !gr_sptr->fixed_rate() )  {
            noutput_items = round_down((int32_t) (max_items_avail * gr_sptr->relative_rate()), gr_sptr->output_multiple());
            LOG_TRACE( multiply_cc_2i_base, " VARIABLE FORECAST NOUTPUT == " << noutput_items );
        } else {
            istream = istreams.begin();
            for ( int i=0; istream != istreams.end(); i++, istream++ ) {
                int t_noutput_items = gr_sptr->fixed_rate_ninput_to_noutput( (*istream)->nitems() );
                if ( gr_sptr->output_multiple_set() ) {
                    t_noutput_items = round_up(t_noutput_items, gr_sptr->output_multiple());
                }
                if ( t_noutput_items > 0 ) {
                    if ( noutput_items == 0 ) {
                        noutput_items = t_noutput_items;
                    }
                    if ( t_noutput_items <= noutput_items ) {
                        noutput_items = t_noutput_items;
                    }
                }
            }
            LOG_TRACE( multiply_cc_2i_base,  " FIXED FORECAST NOUTPUT/output_multiple == " << 
                        noutput_items  << "/" << gr_sptr->output_multiple());
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

            LOG_TRACE( multiply_cc_2i_base, "--> FORECAST IN/OUT " << _ninput_items_required[0]  << "/" << noutput_items  );

            istream = istreams.begin();
            uint32_t dr_cnt=0;
            for ( int idx=0 ; noutput_items > 0 && istream != istreams.end(); idx++, istream++ ) {
                // check if buffer has enough elements
                _input_ready[idx] = false;
                if ( (*istream)->nitems() >= (uint64_t)_ninput_items_required[idx] ) {
                    _input_ready[idx] = true;
                    dr_cnt++;
                }
                LOG_TRACE( multiply_cc_2i_base, "ISTREAM DATACHECK NELMS/NITEMS/REQ/READY:" <<   (*istream)->nelems() << 
                          "/" << (*istream)->nitems() << "/" << _ninput_items_required[idx] << "/" << _input_ready[idx]);
            }
    
            if ( dr_cnt < istreams.size() ) {
                if ( outMultiple > 1 ) {
                    noutput_items -= outMultiple;
                } else {
                    noutput_items /= 2;
                }
            } else {
                dataReady = true;
            }
            LOG_TRACE( multiply_cc_2i_base, " TRIM FORECAST NOUTPUT/READY " << noutput_items << "/" << dataReady );
        }

        // check if data is ready...
        if ( !dataReady ) {
            LOG_TRACE( multiply_cc_2i_base, "DATA CHECK - NOT ENOUGH DATA  AVAIL/REQ:" <<   _istreams[0]->nitems() << 
                      "/" << _ninput_items_required[0] );
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
            if ( !_input_ready[idx] ) {
                continue;
            }
            // get number of items remaining
            try {
                ritems = gr_sptr->nitems_read( idx );
            } catch(...){
                // something bad has happened, we are missing an input stream
                LOG_ERROR( multiply_cc_2i_base, "MISSING INPUT STREAM FOR GR BLOCK, STREAM ID:" <<   (*istream)->streamID );
                return -2;
            } 
    
            nitems = (*istream)->nitems() - ritems;
            LOG_TRACE( multiply_cc_2i_base,  " ISTREAM: IDX:" << idx  << " ITEMS AVAIL/READ/REQ " << nitems << "/" 
                       << ritems << "/" << _ninput_items_required[idx] );
            if ( nitems >= _ninput_items_required[idx] && nitems > 0 ) {
                //remove eos checks ...if ( nitems < _ninput_items_required[idx] ) nitems=0;
                _ninput_items.push_back( nitems );
                _input_items.push_back( (*istream)->read_pointer(ritems) );
            }
        }

        //
        // setup output buffer vector based on noutput..
        //
        ostream = ostreams.begin();
        for( ; ostream != ostreams.end(); ostream++ ) {
            (*ostream)->resize(noutput_items);
            _output_items.push_back( (*ostream)->write_pointer() );
        }

        nout=0;
        if ( _input_items.size() != 0 && serviceThread->threadRunning() ) {
            LOG_TRACE( multiply_cc_2i_base, " CALLING WORK.....N_OUT:" << noutput_items << " N_IN:" << nitems 
                      << " ISTREAMS:" << _input_items.size() << " OSTREAMS:" << _output_items.size());
            nout = gr_sptr->general_work( noutput_items, _ninput_items, _input_items, _output_items);
            LOG_TRACE( multiply_cc_2i_base, "RETURN  WORK ..... N_OUT:" << nout);
        }

        // check for stop condition from work method
        if ( nout < gr_block::WORK_DONE ) {
            LOG_WARN( multiply_cc_2i_base, "WORK RETURNED STOP CONDITION..." << nout );
            nout=0;
            eos = true;
        }
    }

    if (nout != 0 or eos ) {
        noutput_items = nout;
        LOG_TRACE( multiply_cc_2i_base, " WORK RETURNED: NOUT : " << nout << " EOS:" << eos);
        ostream = ostreams.begin();

        for ( int idx=0 ; ostream != ostreams.end(); idx++, ostream++ ) {

            bool gotPkt = false;
            TimeStamp pktTs;
            int inputIdx = idx;
            if ( (size_t)(inputIdx) >= istreams.size() ) {
                for ( inputIdx= istreams.size()-1; inputIdx > -1; inputIdx--) {
                    if ( not istreams[inputIdx]->pktNull() ) {
                        gotPkt = true;
                        pktTs = istreams[inputIdx]->getPktTimeStamp();
                        break;
                    }
                }
            } else {
                pktTs = istreams[inputIdx]->getPktTimeStamp();
                if ( not istreams[inputIdx]->pktNull() ){
                    gotPkt = true;
                }
            }

            LOG_TRACE( multiply_cc_2i_base,  "PUSHING DATA   ITEMS/STREAM_ID " << (*ostream)->nitems() << "/" << (*ostream)->streamID );    
            if ( _maintainTimeStamp ) {

                // set time stamp for output samples based on input time stamp
                if ( (*ostream)->nelems() == 0 )  {
#ifdef TEST_TIME_STAMP
      LOG_DEBUG( multiply_cc_2i_base, "SEED - TS SRI:  xdelta:" << std::setprecision(12) << ostream->sri.xdelta );
      LOG_DEBUG( multiply_cc_2i_base, "OSTREAM WRITE:   maint:" << _maintainTimeStamp );
      LOG_DEBUG( multiply_cc_2i_base, "                  mode:" <<  ostream->tstamp.tcmode );
      LOG_DEBUG( multiply_cc_2i_base, "                status:" <<  ostream->tstamp.tcstatus );
      LOG_DEBUG( multiply_cc_2i_base, "                offset:" <<  ostream->tstamp.toff );
      LOG_DEBUG( multiply_cc_2i_base, "                 whole:" <<  std::setprecision(10) << ostream->tstamp.twsec );
      LOG_DEBUG( multiply_cc_2i_base, "SEED - TS         frac:" <<  std::setprecision(12) << ostream->tstamp.tfsec );
#endif
                    (*ostream)->setTimeStamp( pktTs, _maintainTimeStamp );
                }

                // write out samples, and set next time stamp based on xdelta and  noutput_items
                (*ostream)->write ( noutput_items, eos );
            } else {
// use incoming packet's time stamp to forward
                if ( gotPkt ) {
#ifdef TEST_TIME_STAMP
      LOG_DEBUG( multiply_cc_2i_base, "OSTREAM  SRI:  items/xdelta:" << noutput_items << "/" << std::setprecision(12) << ostream->sri.xdelta );
      LOG_DEBUG( multiply_cc_2i_base, "PKT - TS         maint:" << _maintainTimeStamp );
      LOG_DEBUG( multiply_cc_2i_base, "                  mode:" <<  pktTs.tcmode );
      LOG_DEBUG( multiply_cc_2i_base, "                status:" <<  pktTs.tcstatus );
      LOG_DEBUG( multiply_cc_2i_base, "                offset:" <<  pktTs.toff );
      LOG_DEBUG( multiply_cc_2i_base, "                 whole:" <<  std::setprecision(10) << pktTs.twsec );
      LOG_DEBUG( multiply_cc_2i_base, "PKT - TS          frac:" <<  std::setprecision(12) << pktTs.tfsec );
#endif
                    (*ostream)->write( noutput_items, eos, pktTs  );
                } else {
#ifdef TEST_TIME_STAMP
      LOG_DEBUG( multiply_cc_2i_base, "OSTREAM  SRI:  items/xdelta:" << noutput_items << "/" << std::setprecision(12) << ostream->sri.xdelta );
      LOG_DEBUG( multiply_cc_2i_base, "OSTREAM TOD      maint:" << _maintainTimeStamp );
      LOG_DEBUG( multiply_cc_2i_base, "                  mode:" <<  ostream->tstamp.tcmode );
      LOG_DEBUG( multiply_cc_2i_base, "                status:" <<  ostream->tstamp.tcstatus );
      LOG_DEBUG( multiply_cc_2i_base, "                offset:" <<  ostream->tstamp.toff );
      LOG_DEBUG( multiply_cc_2i_base, "                 whole:" <<  std::setprecision(10) << ostream->tstamp.twsec );
      LOG_DEBUG( multiply_cc_2i_base, "OSTREAM TOD       frac:" <<  std::setprecision(12) << ostream->tstamp.tfsec );
#endif
                    // use time of day as time stamp
                    (*ostream)->write( noutput_items, eos,  _maintainTimeStamp );
                }
            }

        } // for ostreams
    }

    return nout;     
}


