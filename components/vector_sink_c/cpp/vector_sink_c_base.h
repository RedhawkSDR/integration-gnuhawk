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

#ifndef VECTOR_SINK_C_IMPL_BASE_H
#define VECTOR_SINK_C_IMPL_BASE_H

#include <boost/thread.hpp>
#include <ossie/Resource_impl.h>

#include "bulkio/bulkio.h"

#include <boost/date_time/posix_time/posix_time.hpp>
#include "vector_sink_c_GnuHawkBlock.h"

#define NOOP 0
#define FINISH -1
#define NORMAL 1

class vector_sink_c_base;

template < typename TargetClass >
class ProcessThread
{
    public:
        ProcessThread(TargetClass *_target, float _delay) :
            target(_target)
        {
            _mythread = 0;
            _thread_running = false;
            _udelay = (__useconds_t)(_delay * 1000000);
        };

        // kick off the thread
        void start() {
            if (_mythread == 0) {
                _thread_running = true;
                _mythread = new boost::thread(&ProcessThread::run, this);
            }
        };

        // manage calls to target's service function
        void run() {
            int state = NORMAL;
            while (_thread_running and (state != FINISH)) {
                state = target->serviceFunction();
                if (state == NOOP) {
                    boost::this_thread::sleep( boost::posix_time::microseconds( _udelay ) );
                } else {
                    boost::this_thread::yield();
                }
            }
        };

        // stop thread and wait for termination
        bool release(unsigned long secs = 0, unsigned long usecs = 0) {
            _thread_running = false;
            if (_mythread)  {
                if ((secs == 0) and (usecs == 0)){
                    _mythread->join();
                } else {
                    boost::system_time waitime= boost::get_system_time() + boost::posix_time::seconds(secs) +  boost::posix_time::microseconds(usecs) ;
                    if (!_mythread->timed_join(waitime)) {
                        return 0;
                    }
                }
                delete _mythread;
                _mythread = 0;
            }
    
            return 1;
        };

        virtual ~ProcessThread(){
            if (_mythread != 0) {
                release(0);
                _mythread = 0;
            }
        };

        void updateDelay(float _delay) { _udelay = (__useconds_t)(_delay * 1000000); };

        void stop() {
            _thread_running = false;
            if ( _mythread ) _mythread->interrupt();
        };

        bool threadRunning() { return _thread_running; };

    private:
        boost::thread *_mythread;
        bool _thread_running;
        TargetClass *target;
        __useconds_t _udelay;
        boost::condition_variable _end_of_run;
        boost::mutex _eor_mutex;
};

class vector_sink_c_base : public GnuHawkBlock
{
    public:
        vector_sink_c_base(const char *uuid, const char *label);

        void start() throw (CF::Resource::StartError, CORBA::SystemException);

        void stop() throw (CF::Resource::StopError, CORBA::SystemException);

        CORBA::Object_ptr getPort(const char* _id) throw (CF::PortSupplier::UnknownPort, CORBA::SystemException);

        void releaseObject() throw (CF::LifeCycle::ReleaseError, CORBA::SystemException);

        void initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException);

        void loadProperties();

    protected:
        ProcessThread<vector_sink_c_base> *serviceThread; 
        boost::mutex serviceThreadLock;

        // Member variables exposed as properties
        CORBA::Long vlen;
        bool reset;
        std::vector<std::complex<float> > data;

        // Ports
        bulkio::InFloatPort *complex_in;

        std::vector< std::string > inputPortOrder;

    private:
        void construct();

    protected:
        static       const        int                 RealMode=0;
        static       const        int                 ComplexMode=1;
        std::vector<std::vector<int> >                io_mapping;
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

        // 
        // setupIOMappings
        //
        // Sets up mappings for input and output ports and GnuRadio Stream indexes
        //
        // A Gnu Radio input or output streams will be created for each defined RedHawk port.
        // The streams will be ordered 0..N-1 as they are defined in inputPortOrder and outputPortOrder
        // lists created during Component initialization.  
        //
        // For Gnu Radio blocks that define -1 for maximum number of input streams. The number of
        // input streams created will be restricted to the number of RedHawk ports.
        //
        // RESOLVE - need to base mapping for -1 condition on "connections" and not streams
        // RESOLVE - need to add parameters to define expected modes for input ports.. i.e. real or complex and 
        //           not have to wait for SRI.
        //
        virtual void  setupIOMappings();

        // callback when a new Stream ID is detected on the port so we can add to istream/ostream mapping list
        void  complex_in_newStreamCallback( BULKIO::StreamSRI &sri );

        void  processStreamIdChanges();

    //
    // gr_istream - Mapping of Provides Ports to Gnu Radio Stream indexes
    //
    // Gnu Radio Block input stream definition:
    //  Input = 1 .. N then each Provides Port type of X is mapped to a stream index 0..N-1
    //                  This assumes the component will only have 1 input port type. (i.e float ports)
    //  Input = -1  and single Provides Port interface then each unique stream definition will map to a stream index 0..N
    //  Input = -1  and N Provides Port interface then each port will map to a stream index 0..N-1
    //
    // The mapping items are stored in a vector and maintain by setIOMappings and notifySRI methods, and
    // the service function when "end of stream" happens.
    //
    template < typename IN_PORT_TYPE > struct gr_istream {
        IN_PORT_TYPE                       *port;            // RH port object
        GNU_RADIO_BLOCK_PTR                grb;              // shared pointer to our gr_block
        int                                _idx;             // index of stream in gr_block
        std::string                        streamID;         // redhawk stream id
        std::vector< typename IN_PORT_TYPE::NativeType >      _data;     // buffered data from port
        int                                _spe;             // scalars per element
        int                                _vlen;            // vector length in items, the gr_block process data 
        int                                _hlen;            // history length in items, the gr_blocks expects
        bool                               _eos;             // if EOS was received from port
        bool                               _sri;             // that we received an SRI call
        typename IN_PORT_TYPE::dataTransfer *pkt;            // pointer to last packet read from port

        gr_istream( IN_PORT_TYPE *in_port, GNU_RADIO_BLOCK_PTR in_grb, int idx, int mode, std::string &sid ) :
        port(in_port), grb(in_grb), _idx(idx), streamID(sid), _data(0), _spe(1), _vlen(1), _hlen(1), _eos(false), _sri(true), pkt(NULL)
        {
            _spe = ScalarsPerElement(mode);
            _check(mode, true);
        };

        gr_istream( IN_PORT_TYPE *in_port, GNU_RADIO_BLOCK_PTR in_grb, int idx,  std::string &sid ) :
        port(in_port), grb(in_grb), _idx(idx), streamID(sid), _data(0), _spe(1), _vlen(1), _hlen(1), _eos(false), _sri(false), pkt(NULL)
        {
            int mode=0;
            _spe = ScalarsPerElement(mode);
            _check(mode, true);
        };

        //
        // translate scalars per element for incoming data
        //    mode == 0 : real, mode == 1 : complex
        static inline int ScalarsPerElement( int mode ) {
            int spe=1;
            if ( mode == 1 ) spe=2;
            return spe;
        };

        //
        // translate scalars per element for incoming data
        //    mode == 0 : real, mode == 1 : complex
        static inline int ScalarsPerElement( BULKIO::StreamSRI &sri ) {
            return ScalarsPerElement( sri.mode );
        };

        //
        // Return the size of an element (sample) in bytes
        //
        static inline int SizeOfElement(int mode ) {
            return sizeof( typename IN_PORT_TYPE::NativeType)*ScalarsPerElement( mode);
        };

        //
        // Return the size of an element (sample) in bytes
        //
        static inline int SizeOfElement( BULKIO::StreamSRI &sri ) {
            return sizeof( typename IN_PORT_TYPE::NativeType)*ScalarsPerElement(sri);
        }

        //
        // return scalars per element
        //
        inline int spe () {
            return _spe;
        }

        //
        // set scalars per element
        //
        inline int spe( int mode ) {
            _check( mode );
            return _spe;
        }

        //
        // return state if SRI was set
        //
        inline  bool sri() {
            return _sri;
        }

        inline  bool sri( bool newSri ) {
            _sri = newSri;
            return _sri;
        }

        //
        // return if End of Stream was seen
        //
        inline  bool eos() {
            return _eos;
        }

        inline  bool eos( bool newEos ) {
            _eos = newEos;
            return _eos;
        }

        inline int vlen () {
            return _vlen;
        }

        void _check( int inMode , bool force=false) {
            // calc old history value
            int32_t  old_hlen = (_hlen-1) * (_vlen*_spe);
            int32_t  spe=ScalarsPerElement(inMode);
            int32_t  nvlen=_vlen;
            bool     newVlen=false;
            bool     newSpe=false;
            try {
                if ( grb && grb->input_signature() )
                    nvlen = grb->input_signature()->sizeof_stream_item(_idx) / ( spe *  sizeof( typename IN_PORT_TYPE::NativeType));
            } catch(...) {
                //std::cout << "UNABLE TO SET VLEN, BAD INDEX:" << _idx ;
            }

            if ( nvlen != _vlen && nvlen >= 1 ) {
                _vlen=nvlen;
                newVlen=true;
            }

            if ( spe != _spe ) {
                _spe = spe;
                newSpe = true;
            }

            if ( force || newSpe || newVlen ) {
                // seed history for buffer with empty items
                int32_t new_hlen = ( grb->history()-1)* ( _vlen * _spe );
                if ( (old_hlen != new_hlen)  && ( new_hlen > -1 ) ) {
                    _hlen = grb->history();
                    _data.resize( new_hlen );
                }
            }
        }

        //
        // reset our association to a GR Block
        //    
        void associate( GNU_RADIO_BLOCK_PTR newBlock ) {
            grb = newBlock;
            if ( grb ) _check( _spe, true );
        }

        //
        //
        //
        inline uint64_t nitems () {
            uint64_t tmp = nelems();
            if ( _vlen > 0 ) tmp /= _vlen;
            return tmp;
        }

        inline uint64_t nelems () {
            uint64_t tmp = _data.size();
            if ( _spe > 0 ) tmp /= _spe;
            return tmp;
        }

        uint64_t  itemsToScalars( uint64_t N ) {
            return  N*_vlen*_spe;
        };

        // RESOLVE: need to allow for requests of certain size, and blocking and timeouts
        int   read( int64_t ritems=-1 ) {
      
            int retval = -1;
            typename IN_PORT_TYPE::dataTransfer *tpkt;

            if ( port && _sri ) {
                //std::cout << "getPacket :  STREAM ID: " << streamID  << std::endl;
                tpkt = port->getPacket( -1, streamID );
    
                if ( tpkt == NULL ) {
                    //std::cout << "getPacket :  NO DATA for STREAM ID: " << streamID  << std::endl;
                    if ( port != NULL && port->blocked() )  retval = 0;
               } else {
                    _data.insert( _data.end(), tpkt->dataBuffer.begin(), tpkt->dataBuffer.end() );
                    if ( tpkt->sriChanged ) {
                        spe(tpkt->SRI.mode);
                    }
  
                    // resolve need to keep time stamp accurate for first sample of data.... we could loose this if we
                    // end having residual data left in the buffer when output_multiple and vlen are used
                    // by the gr_block - read and consume_elements need refactoring
          
                    _eos = tpkt->EOS;
                    if ( pkt !=  NULL )  delete pkt;
                    pkt = tpkt;
                    retval=nitems();
                }    
            }

            return retval;
        }

        inline bool overrun() {
            return ( pkt && pkt->inputQueueFlushed);
        }

        inline bool sriChanged() {
            return ( pkt && pkt->sriChanged );
        }

        typename IN_PORT_TYPE::NativeType *read_pointer( int32_t items ) {
            uint32_t idx = itemsToScalars(items);
            if ( idx < _data.size() ) 
                return &_data[ idx ];
            else
                return &_data[0];
        }
      
        // compress data buffer for requested number of items
        void consume( int32_t n_items ) {
            if ( n_items > 0 ) {
                consume_elements( n_items*_vlen );
            }
        }

        // compress data buffer for requested number of items
        void consume_elements( int32_t inNelems ) {
             int d_idx = inNelems*_spe;
             int n = std::distance( _data.begin() + d_idx, _data.end() );
             if ( d_idx > 0 && n >= 0  ) {
                  std::copy( _data.begin() + d_idx, _data.end(), _data.begin() );
                  _data.resize(n);
              }
        }

        // perform clean up of stream state and mapping
        void close() {
            _data.clear();
            _vlen = 1;
            _hlen=1;
            _eos = false;
            _sri = false;
            if ( pkt ) {
                delete pkt;
                pkt=NULL;
            }
        }
    };

    //
    // template Typedefs work around
    //
    template < typename T > class _IStream {
        private:
            _IStream(void) {};
        public:
            typedef typename std::vector< gr_istream< T > > List;
    };

    typedef  gr_vector_const_void_star                GR_IN_BUFFERS;
    typedef  gr_vector_void_star                      GR_OUT_BUFFERS;
    typedef  gr_vector_int                            GR_BUFFER_LENGTHS;

    template < typename IN_PORT_TYPE >   int  _analyzerServiceFunction( typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams );
    template < typename IN_PORT_TYPE >   int  _forecastAndProcess( bool &eos, std::vector< gr_istream< IN_PORT_TYPE > > &istreams );

    typedef std::deque< std::pair< bulkio::InFloatPort *, BULKIO::StreamSRI > >  SRIQueue;
    typedef _IStream< bulkio::InFloatPort >::List    IStreamList;


    // cache variables to transferring data to/from a GNU Radio Block
    std::vector<bool>            _input_ready;
    GR_BUFFER_LENGTHS            _ninput_items_required;
    GR_BUFFER_LENGTHS            _ninput_items;
    GR_IN_BUFFERS                _input_items;
    GR_OUT_BUFFERS               _output_items;
    int32_t                      noutput_items;

    boost::mutex                _sriMutex;  
    SRIQueue                    _sriQueue;

    // mapping of RH ports to GNU Radio streams
    IStreamList                  _istreams;


    ENABLE_LOGGING;
    
    protected:

        bool                     _maintainTimeStamp;
        bool                     _throttle;
        TimeMark                 p_start_time;
        TimeMark                 p_end_time;

    public:

        int serviceFunction()
        {
            int retval = NOOP;
            retval = _analyzerServiceFunction( _istreams );

            p_end_time =  boost::posix_time::microsec_clock::local_time();
            if ( retval == NORMAL && _throttle ) {
                TimeDuration  delta = calcThrottle( p_start_time, p_end_time );
                if ( delta.is_not_a_date_time() == false && delta.is_negative() == false ) {
                    LOG_TRACE( vector_sink_c_base, " SLEEP ...." << delta );
                    boost::this_thread::sleep( delta );
                } else {
                    LOG_TRACE( vector_sink_c_base, " NO SLEEPING...." );
                }
            }
            p_start_time = p_end_time;
       
            LOG_TRACE( vector_sink_c_base, " serviceFunction: retval:" << retval);

            return retval;
        };
};
#endif
