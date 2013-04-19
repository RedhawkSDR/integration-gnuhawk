// BEGIN GENERATED CODE
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
// Identification: $Revision$
package gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component;

import gov.redhawk.ide.RedhawkIdeActivator;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.Property;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.CplusplusJetGeneratorPlugin;
import gov.redhawk.ide.codegen.jet.cplusplus.CppProperties;
import gov.redhawk.ide.codegen.jet.cplusplus.ports.MessagingPortTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.ports.PropertyChangeEventPortTemplate;
import gov.redhawk.ide.idl.IdlUtil;
import gov.redhawk.ide.idl.Interface;
import gov.redhawk.ide.idl.Operation;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.spd.Implementation;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;

/**
 * @generated
 */
public class ResourceBaseHTemplate
{

  protected static String nl;
  public static synchronized ResourceBaseHTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceBaseHTemplate result = new ResourceBaseHTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "" + NL + "#ifndef ";
  protected final String TEXT_2 = "_IMPL_BASE_H" + NL + "#define ";
  protected final String TEXT_3 = "_IMPL_BASE_H" + NL + "" + NL + "#include <boost/thread.hpp>" + NL + "#include <ossie/Resource_impl.h>";
  protected final String TEXT_4 = NL + "#include \"CF/AggregateDevices.h\"";
  protected final String TEXT_5 = NL + "#include \"ossie/AggregateDevice_impl.h\"";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL + "#include \"ossie/";
  protected final String TEXT_8 = "Device_impl.h\"";
  protected final String TEXT_9 = NL + "#include <ossie/prop_helpers.h>";
  protected final String TEXT_10 = NL + "#include \"port_impl.h\"";
  protected final String TEXT_11 = NL + "#include \"struct_props.h\"";
  protected final String TEXT_12 = NL + NL + "#include \"RH_ProcessThread.h\"" + NL + "#include \"";
  protected final String TEXT_13 = "_GnuHawkBlock.h\"" + NL + "" + NL + "" + NL + "class ";
  protected final String TEXT_14 = "_base : public GnuHawkBlock" + NL + "{";
  protected final String TEXT_15 = NL + "    friend class ";
  protected final String TEXT_16 = "_";
  protected final String TEXT_17 = "_In_i;";
  protected final String TEXT_18 = NL + "    friend class ";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = "_Out_i;";
  protected final String TEXT_21 = NL + "    friend class PropertyChangeEventPort_i;";
  protected final String TEXT_22 = NL;
  protected final String TEXT_23 = NL + "  class SRIListener : BULKIO_data";
  protected final String TEXT_24 = "_In_i::SRIListener {" + NL + "" + NL + "    friend class  ";
  protected final String TEXT_25 = "_base;" + NL + "" + NL + "  public:" + NL + "    void operator() ( BULKIO_data";
  protected final String TEXT_26 = "_In_i *port, BULKIO::StreamSRI &sri  ) {" + NL + "      _parent.notifySRI( port, sri );" + NL + "    };" + NL + "  " + NL + "  private:" + NL + "    " + NL + "  SRIListener( ";
  protected final String TEXT_27 = "_base &parent ) : _parent(parent) {};" + NL;
  protected final String TEXT_28 = NL + "    ";
  protected final String TEXT_29 = "_base &_parent;" + NL + "  };" + NL + "  ";
  protected final String TEXT_30 = "  " + NL + "" + NL + "    public:" + NL;
  protected final String TEXT_31 = NL + "        ";
  protected final String TEXT_32 = "_base(const char *uuid, const char *label);" + NL + "" + NL + "        void start() throw (CF::Resource::StartError, CORBA::SystemException);" + NL + "" + NL + "        void stop() throw (CF::Resource::StopError, CORBA::SystemException);" + NL + "" + NL + "        void releaseObject() throw (CF::LifeCycle::ReleaseError, CORBA::SystemException);" + NL + "" + NL + "        void initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException);" + NL + "" + NL + "        void loadProperties();" + NL;
  protected final String TEXT_33 = NL + "        CORBA::Object_ptr getPort(const char* _id) throw (CF::PortSupplier::UnknownPort, CORBA::SystemException);" + NL;
  protected final String TEXT_34 = NL + NL + "    protected:" + NL + "" + NL + "    static       const        int                 RealMode=0;" + NL + "    static       const        int                 ComplexMode=1;" + NL + "    typedef      boost::posix_time::ptime         TimeMark;" + NL + "    typedef      boost::posix_time::time_duration TimeDuration;";
  protected final String TEXT_35 = NL + "    typedef      BULKIO::PrecisionUTCTime\t  TimeStamp;";
  protected final String TEXT_36 = NL + NL + "    //" + NL + "    // Enable or disable to adjusting of timestamp based on output rate" + NL + "    //  " + NL + "    inline void maintainTimeStamp( bool onoff=false ) {" + NL + "       _maintainTimeStamp = onoff;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Enable or disable throttling of processing" + NL + "    //  " + NL + "    inline void setThrottle( bool onoff=false ) {" + NL + "       _throttle = onoff;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // getTargetDuration" + NL + "    //" + NL + "    // Target duration defines the expected time the service function requires" + NL + "    // to produce/consume elements. For source patterns, the data output rate" + NL + "    // will be used to defined the target duration.  For sink patterns, the" + NL + "    // input rate of elements is used to define the target duration" + NL + "    //" + NL + "    virtual TimeDuration getTargetDuration();" + NL + "" + NL + "    //" + NL + "    // calcThrottle " + NL + "    //" + NL + "    // Calculate the duration about that we should sleep based on processing time" + NL + "    // based on value from getTargetDuration() minus processing time ( end time " + NL + "    // minus start time)" + NL + "    //" + NL + "    // If the value is a positive duration then the boost::this_thread::sleep" + NL + "    // method is called with 1/4 of the calculated duration." + NL + "    //" + NL + "    virtual TimeDuration calcThrottle( TimeMark &stime," + NL + "\t\t\t\t       TimeMark &etime );" + NL + "    //" + NL + "    // createBlock" + NL + "    //" + NL + "    // Create the actual GNU Radio Block to that will perform the work method. The resulting" + NL + "    // block object is assigned to gr_sptr" + NL + "    //" + NL + "    // Add property change callbacks for getter/setter methods" + NL + "    //" + NL + "    //" + NL + "    virtual void createBlock() = 0;" + NL;
  protected final String TEXT_37 = " " + NL + "    // " + NL + "    // setupIOMappings" + NL + "    //" + NL + "    // Sets up mappings for input and output ports and GnuRadio Stream indexes" + NL + "    //" + NL + "    // A Gnu Radio input or output streams will be created for each defined RedHawk port." + NL + "    // The streams will be ordered 0..N-1 as they are defined in inputPortOrder and outputPortOrder" + NL + "    // lists created during Component initialization.  " + NL + "    //" + NL + "    // For Gnu Radio blocks that define -1 for maximum number of input streams. The number of" + NL + "    // input streams created will be restricted to the number of RedHawk ports." + NL + "    //" + NL + "    // RESOLVE - need to base mapping for -1 condition on \"connections\" and not streams" + NL + "    // RESOLVE - need to add parameters to define expected modes for input ports.. i.e. real or complex and " + NL + "    //           not have to wait for SRI." + NL + "    //" + NL + "    virtual void  setupIOMappings();";
  protected final String TEXT_38 = NL;
  protected final String TEXT_39 = " " + NL + "" + NL + "    //" + NL + "    // getNOutputStreams" + NL + "    //" + NL + "    // Called by setupIOMappings when the number of Gnu Radio output streams == -1 (variable ) and number of " + NL + "    // Redhawk ports  == 1." + NL + "    " + NL + "    // @return uint32_t : Number of output streams to build" + NL + "    //" + NL + "    virtual uint32_t  getNOutputStreams(); " + NL + " " + NL + "    //" + NL + "    // createOutputSRI" + NL + "    //" + NL + "    // Called by setupIOMappings when an output mapping is defined. For each output mapping" + NL + "    // defined, a call to createOutputSRI will be issued with the associated output index." + NL + "    // This default SRI and StreamID will be saved to the mapping and pushed down stream via pushSRI." + NL + "    // The subclass is responsible for overriding behavior of this method. The index provide matches" + NL + "    // the stream index number that will be use by the Gnu Radio  Block object" + NL + "    //" + NL + "    // @param idx : output stream index number to associate the returned SRI object with" + NL + "    // @return sri : default SRI object passed down stream over a RedHawk port" + NL + "    //      " + NL + "    virtual BULKIO::StreamSRI  createOutputSRI( int32_t idx );" + NL + "    " + NL + "    //" + NL + "    // adjustOutputRate" + NL + "    //" + NL + "    // Called by seOutputStreamSRI method when pushing SRI down stream to adjust the " + NL + "    // the xdelta and/or ydelta values accordingly.  The provided method will perform the following:" + NL + "    //" + NL + "    //  gr_blocks, gr_sync_block - no modifications are performed" + NL + "    //  gr_sync_decimator - sri.xdelta * gr_sptr->decimation()" + NL + "    //  gr_sync_interpolator - sri.xdelta  / gr_sptr->interpolate()" + NL + "    //" + NL + "    virtual void  adjustOutputRate(BULKIO::StreamSRI &sri );" + NL + "    " + NL + "      ";
  protected final String TEXT_40 = "    " + NL;
  protected final String TEXT_41 = NL + NL + "    // callback when a new Stream ID is detected on the port so we can add to istream/ostream mapping list" + NL + "    void  notifySRI( BULKIO_data";
  protected final String TEXT_42 = "_In_i *port, BULKIO::StreamSRI &sri );" + NL + "     " + NL + "    void  processStreamIdChanges();";
  protected final String TEXT_43 = "    " + NL + "      ";
  protected final String TEXT_44 = "   " + NL + "    //" + NL + "    // setOutputSteamSRI" + NL + "    //" + NL + "    // Set the SRI context for an output stream from a Gnu Radio Block, when a pushPacket call occurs. Whenever the SRI is established" + NL + "    // for an output stream it is sent down stream to the next component." + NL + "    //  " + NL + "    virtual void  setOutputStreamSRI( int streamIdx, BULKIO::StreamSRI &in_sri, bool sendSRI=true, bool setStreamID=true ) {" + NL + "      int o_idx = std::max( 0, (int)_ostreams.size()-1 );" + NL + "      o_idx = std::min( streamIdx, o_idx );" + NL + "      if ( (uint32_t)o_idx < _ostreams.size() ) {" + NL + "         _ostreams[o_idx].adjustSRI(in_sri, o_idx, setStreamID );" + NL + "         if ( sendSRI ) _ostreams[o_idx].pushSRI();" + NL + "      }" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // setOutputSteamSRI" + NL + "    //" + NL + "    // Use the same SRI context for all output streams from a Gnu Radio Block, when a pushPacket call occurs. Whenever the SRI is established" + NL + "    // for an output stream it is sent down stream to the next component." + NL + "    // " + NL + "    virtual void  setOutputStreamSRI( BULKIO::StreamSRI &in_sri , bool sendSRI = true, bool setStreamID = true ) {" + NL + "      OStreamList::iterator ostream=_ostreams.begin();" + NL + "      for( int o_idx=0;  ostream != _ostreams.end(); o_idx++, ostream++ ) {" + NL + "      \t ostream->adjustSRI(in_sri, o_idx, setStreamID );" + NL + "         if ( sendSRI )  ostream->pushSRI();" + NL + "      }" + NL + "    }" + NL + "      ";
  protected final String TEXT_45 = NL;
  protected final String TEXT_46 = NL;
  protected final String TEXT_47 = " " + NL + "  //" + NL + "  // gr_istream - Mapping of Provides Ports to Gnu Radio Stream indexes" + NL + "  //" + NL + "  // Gnu Radio Block input stream definition:" + NL + "  //  Input = 1 .. N then each Provides Port type of X is mapped to a stream index 0..N-1" + NL + "  //                  This assumes the component will only have 1 input port type. (i.e float ports)" + NL + "  //  Input = -1  and single Provides Port interface then each unique stream definition will map to a stream index 0..N" + NL + "  //  Input = -1  and N Provides Port interface then each port will map to a stream index 0..N-1" + NL + "  //" + NL + "  // The mapping items are stored in a vector and maintain by setIOMappings and notifySRI methods, and" + NL + "  // the service function when \"end of stream\" happens." + NL + "  //" + NL + "  template < typename IN_PORT_TYPE > struct gr_istream {" + NL + "" + NL + "    IN_PORT_TYPE                       *port;            // RH port object" + NL + "    GNU_RADIO_BLOCK_PTR                grb;              // shared pointer to our gr_block" + NL + "    int                                _idx;             // index of stream in gr_block" + NL + "    std::string                        streamID;         // redhawk stream id" + NL + "    std::vector< typename IN_PORT_TYPE::RH_NativeType >      _data;     // buffered data from port" + NL + "    int                                _spe;             // scalars per element" + NL + "    int                                _vlen;            // vector length in items, the gr_block process data " + NL + "    int                                _hlen;            // history length in items, the gr_blocks expects" + NL + "    bool                               _eos;             // if EOS was received from port" + NL + "    bool                               _sri;             // that we received an SRI call" + NL + "    typename IN_PORT_TYPE::dataTransfer *pkt;            // pointer to last packet read from port" + NL + "" + NL + "    gr_istream( IN_PORT_TYPE *in_port, GNU_RADIO_BLOCK_PTR in_grb, int idx, int mode, std::string &sid ) :" + NL + "    port(in_port), grb(in_grb), _idx(idx), streamID(sid), _data(0), _spe(1), _vlen(1), _hlen(1), _eos(false), _sri(true), pkt(NULL)" + NL + "    {" + NL + "      _spe = ScalarsPerElement(mode);" + NL + "      _check(mode, true);" + NL + "    };" + NL + "" + NL + "     gr_istream( IN_PORT_TYPE *in_port, GNU_RADIO_BLOCK_PTR in_grb, int idx,  std::string &sid ) :" + NL + "    port(in_port), grb(in_grb), _idx(idx), streamID(sid), _data(0), _spe(1), _vlen(1), _hlen(1), _eos(false), _sri(false), pkt(NULL)" + NL + "    {" + NL + "       int mode=0;" + NL + "      _spe = ScalarsPerElement(mode);" + NL + "      _check(mode, true);" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // translate scalars per element for incoming data" + NL + "    //    mode == 0 : real, mode == 1 : complex" + NL + "    static inline int ScalarsPerElement( int mode ) {" + NL + "      int spe=1;" + NL + "      if ( mode == 1 ) spe=2;" + NL + "      return spe;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // translate scalars per element for incoming data" + NL + "    //    mode == 0 : real, mode == 1 : complex" + NL + "    static inline int ScalarsPerElement( BULKIO::StreamSRI &sri ) {" + NL + "      return ScalarsPerElement( sri.mode );" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Return the size of an element (sample) in bytes" + NL + "    //" + NL + "    static inline int SizeOfElement(int mode ) {" + NL + "      return sizeof( typename IN_PORT_TYPE::RH_NativeType)*ScalarsPerElement( mode);" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Return the size of an element (sample) in bytes" + NL + "    //" + NL + "    static inline int SizeOfElement( BULKIO::StreamSRI &sri ) {" + NL + "      return sizeof( typename IN_PORT_TYPE::RH_NativeType)*ScalarsPerElement(sri);" + NL + "    }" + NL + "" + NL + "" + NL + "    //" + NL + "    // return scalars per element" + NL + "    //" + NL + "    inline int spe () {" + NL + "      return _spe;" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // set scalars per element" + NL + "    //" + NL + "    inline int spe( int mode ) {" + NL + "      _check( mode );" + NL + "      return _spe;" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // return state if SRI was set" + NL + "    //" + NL + "    inline  bool sri() {" + NL + "      return _sri;" + NL + "    }" + NL + "" + NL + "    inline  bool sri( bool newSri ) {" + NL + "       _sri = newSri;" + NL + "       return _sri;" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // return if End of Stream was seen" + NL + "    //" + NL + "    inline  bool eos() {" + NL + "      return _eos;" + NL + "    }" + NL + "" + NL + "    inline  bool eos( bool newEos ) {" + NL + "       _eos = newEos;" + NL + "       return _eos;" + NL + "    }" + NL + "" + NL + "    inline int vlen () {" + NL + "      return _vlen;" + NL + "    }" + NL + "" + NL + "    void _check( int inMode , bool force=false) {" + NL + "" + NL + "      // calc old history value" + NL + "      int32_t  old_hlen = (_hlen-1) * (_vlen*_spe);" + NL + "      int32_t  spe=ScalarsPerElement(inMode);" + NL + "      int32_t  nvlen=_vlen;" + NL + "      bool     newVlen=false;" + NL + "      bool     newSpe=false;" + NL + "      try {" + NL + "\tif ( grb && grb->input_signature() )" + NL + "\t  nvlen = grb->input_signature()->sizeof_stream_item(_idx) / ( spe *  sizeof( typename IN_PORT_TYPE::RH_NativeType));" + NL + "      }" + NL + "      catch(...) {" + NL + "\t//std::cout << \"UNABLE TO SET VLEN, BAD INDEX:\" << _idx ;" + NL + "      }" + NL + "" + NL + "      if ( nvlen != _vlen && nvlen >= 1 ) {" + NL + "\t_vlen=nvlen;" + NL + "\tnewVlen=true;" + NL + "      }" + NL + "" + NL + "      if ( spe != _spe ) {" + NL + "\t_spe = spe;" + NL + "\tnewSpe = true;" + NL + "      }" + NL + "" + NL + "      if ( force || newSpe || newVlen ) {" + NL + "\t  // seed history for buffer with empty items" + NL + "\t  int32_t new_hlen = ( grb->history()-1)* ( _vlen * _spe );" + NL + "\t  if ( (old_hlen != new_hlen)  && ( new_hlen > -1 ) ) {" + NL + "\t      _hlen = grb->history();" + NL + "\t      _data.resize( new_hlen );" + NL + "\t  }" + NL + "\t}" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // reset our association to a GR Block" + NL + "    //    " + NL + "    void associate( GNU_RADIO_BLOCK_PTR newBlock ) {" + NL + "      grb = newBlock;" + NL + "      if ( grb ) _check( _spe, true );" + NL + "    }" + NL + "" + NL + "    //" + NL + "    //" + NL + "    //" + NL + "    inline uint64_t nitems () {" + NL + "      uint64_t tmp = nelems();" + NL + "      if ( _vlen > 0 ) tmp /= _vlen;" + NL + "      return tmp;" + NL + "    }" + NL + "" + NL + "    inline uint64_t nelems () {" + NL + "       uint64_t tmp = _data.size();" + NL + "       if ( _spe > 0 ) tmp /= _spe;" + NL + "       return tmp;" + NL + "    }" + NL + "" + NL + "    uint64_t  itemsToScalars( uint64_t N ) {" + NL + "      return  N*_vlen*_spe;" + NL + "    };" + NL + "" + NL + "    // RESOLVE: need to allow for requests of certain size, and blocking and timeouts" + NL + "    int   read( int64_t ritems=-1 ) {" + NL + "      " + NL + "      int retval = -1;" + NL + "      typename IN_PORT_TYPE::dataTransfer *tpkt;" + NL + "" + NL + "      if ( port && _sri ) {" + NL + "        //std::cout << \"getPacket :  STREAM ID: \" << streamID  << std::endl;" + NL + "        tpkt = port->getPacket( -1, streamID );" + NL + "    " + NL + "        if ( tpkt == NULL ) {" + NL + "          //std::cout << \"getPacket :  NO DATA for STREAM ID: \" << streamID  << std::endl;" + NL + "          if ( port != NULL && port->blocked() )  retval = 0;" + NL + "        }" + NL + "        else {" + NL + "          _data.insert( _data.end(), tpkt->dataBuffer.begin(), tpkt->dataBuffer.end() );" + NL + "          if ( tpkt->sriChanged ) {" + NL + "\t     spe(tpkt->SRI.mode);" + NL + "          }" + NL + "\t  " + NL + "\t  // resolve need to keep time stamp accurate for first sample of data.... we could loose this if we" + NL + "\t  // end having residual data left in the buffer when output_multiple and vlen are used" + NL + "          // by the gr_block - read and consume_elements need refactoring" + NL + "          " + NL + "          _eos = tpkt->EOS;" + NL + "          if ( pkt !=  NULL )  delete pkt;" + NL + "          pkt = tpkt;" + NL + "          retval=nitems();" + NL + "        }    " + NL + "    " + NL + "      }" + NL + "" + NL + "      return retval;" + NL + "    }" + NL + "" + NL + "    inline bool overrun() {" + NL + "      return ( pkt && pkt->inputQueueFlushed);" + NL + "    }" + NL + "" + NL + "    inline bool sriChanged() {" + NL + "      return ( pkt && pkt->sriChanged );" + NL + "    }" + NL + "" + NL + "    typename IN_PORT_TYPE::RH_NativeType *read_pointer( int32_t items ) {" + NL + "      uint32_t idx = itemsToScalars(items);" + NL + "      if ( idx < _data.size() ) " + NL + "\treturn &_data[ idx ];" + NL + "      else" + NL + "\treturn &_data[0];" + NL + "     }" + NL + "      " + NL + "    // compress data buffer for requested number of items" + NL + "    void consume( int32_t n_items ) {" + NL + "      if ( n_items > 0 ) {" + NL + "\tconsume_elements( n_items*_vlen );" + NL + "      }" + NL + "    }" + NL + "" + NL + "    // compress data buffer for requested number of items" + NL + "    void consume_elements( int32_t inNelems ) {" + NL + "       int d_idx = inNelems*_spe;" + NL + "       int n = std::distance( _data.begin() + d_idx, _data.end() );" + NL + "       if ( d_idx > 0 && n >= 0  ) {" + NL + "       \t  std::copy( _data.begin() + d_idx, _data.end(), _data.begin() );" + NL + "          _data.resize(n);" + NL + "       }" + NL + "    }" + NL + "" + NL + "    // perform clean up of stream state and mapping" + NL + "    void close() {" + NL + "      _data.clear();" + NL + "      _vlen = 1;" + NL + "      _hlen=1;" + NL + "      _eos = false;" + NL + "      _sri = false;" + NL + "      if ( pkt ) {" + NL + "        delete pkt;" + NL + "        pkt=NULL;" + NL + "      }" + NL + "    }" + NL + "" + NL + "  };" + NL;
  protected final String TEXT_48 = NL;
  protected final String TEXT_49 = " " + NL + "  " + NL + "  // gr_ostream " + NL + "  // " + NL + "  // Provides a mapping of output ports to a Gnu Radio  Block's output stream.  These items" + NL + "  // are stored in a vector for managing output from the Gnu Radio Block and pushing" + NL + "  // the data down stream to the next component over the port object." + NL + "  //" + NL + "  // Items in the vector are maintain by setIOMappings, notifySRI and the " + NL + "  // the service function when \"end of stream\" happens" + NL + "  //" + NL + " template < typename OUT_PORT_TYPE > struct gr_ostream {" + NL + "" + NL + "    OUT_PORT_TYPE                      *port;                // handle to Port object" + NL + "    GNU_RADIO_BLOCK_PTR                grb;                  // shared pointer ot GR_BLOCK" + NL + "    int                                _idx;                 // output index (loose association)" + NL + "    std::string                        streamID;             // Stream Id to send down stream" + NL + "    BULKIO::StreamSRI                  sri;                  // SRI to send down stream" + NL + "    bool                               _m_tstamp;            // set to true if we are maintaining outgoing time stamp" + NL + "    BULKIO::PrecisionUTCTime           tstamp;               // time stamp to use for pushPacket calls" + NL + "    std::vector< typename OUT_PORT_TYPE::RH_NativeType >  _data;    // output buffer used by GR_Block" + NL + "    bool                               _eos;                        // if EOS was sent" + NL + "    uint64_t                           _nelems;                     // number of elements in that have been pushed down stream" + NL + "    int                                _vlen;                      // vector length in items, to allocate output buffer for GR_BLOCK" + NL + "" + NL + "  gr_ostream( OUT_PORT_TYPE *out_port, GNU_RADIO_BLOCK_PTR ingrb, int idx, int mode, std::string &in_sid ) :" + NL + "   port(out_port), grb(ingrb), _idx(idx), streamID(in_sid), _m_tstamp(false), _data(0), _eos(false), _nelems(0), _vlen(1)" + NL + "    {" + NL + "      sri.hversion = 1;" + NL + "      sri.xstart = 0.0;" + NL + "      sri.xdelta = 1;" + NL + "      sri.xunits = BULKIO::UNITS_TIME;" + NL + "      sri.subsize = 0;" + NL + "      sri.ystart = 0.0;" + NL + "      sri.ydelta = 0.0;" + NL + "      sri.yunits = BULKIO::UNITS_NONE;" + NL + "      sri.mode = mode;" + NL + "      sri.streamID = streamID.c_str();" + NL + "      // RESOLVE sri.blocking=0;   to block or not" + NL + "" + NL + "      tstamp.tcmode = BULKIO::TCM_CPU;" + NL + "      tstamp.tcstatus = (short)1;" + NL + "      tstamp.toff = 0.0;" + NL + "      setTimeStamp();     " + NL + "    };" + NL + "" + NL + "    //" + NL + "    // translate scalars per element for incoming data" + NL + "    //    mode == 0 : real, mode == 1 : complex" + NL + "    static inline int ScalarsPerElement( int mode ) {" + NL + "      int spe=1;" + NL + "      if ( mode == 1 ) spe=2;" + NL + "      return spe;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // translate scalars per element for incoming data" + NL + "    //    mode == 0 : real, mode == 1 : complex" + NL + "    static inline int ScalarsPerElement( BULKIO::StreamSRI &sri ) {" + NL + "      return ScalarsPerElement( sri.mode );" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Return the size of an element (sample) in bytes" + NL + "    //" + NL + "    static inline int SizeOfElement(int mode ) {" + NL + "      return sizeof( typename OUT_PORT_TYPE::RH_NativeType)*ScalarsPerElement( mode);" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Return the size of an element (sample) in bytes" + NL + "    //" + NL + "    static inline int SizeOfElement( BULKIO::StreamSRI &sri ) {" + NL + "      return sizeof( typename OUT_PORT_TYPE::RH_NativeType)*ScalarsPerElement(sri);" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Establish and SRI context for this output stream" + NL + "    //" + NL + "    void setSRI( BULKIO::StreamSRI &inSri, int idx ) {" + NL + "       sri=inSri;" + NL + "       streamID = sri.streamID;" + NL + "       // check if history, spe and vlen need to be adjusted" + NL + "       _check(idx);" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Only adjust stream id and output rate for SRI object" + NL + "    //" + NL + "   void adjustSRI( BULKIO::StreamSRI &inSri, int idx, bool setStreamID=true ) {" + NL + "      if ( setStreamID ) { " + NL + "          streamID = inSri.streamID;" + NL + "          sri.streamID = inSri.streamID;" + NL + "      }" + NL + "      double ret=inSri.xdelta;" + NL + "      if ( grb ) ret = ret *grb->relative_rate();" + NL + "      sri.xdelta = ret;" + NL + "       _check(idx);" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // push our SRI object down stream" + NL + "    //" + NL + "    void pushSRI() {" + NL + "      if ( port ) port->pushSRI( sri );" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // push incoming SRI object down stream, do not save this object" + NL + "    //" + NL + "    void pushSRI( BULKIO::StreamSRI &inSri ) {" + NL + "      if ( port ) port->pushSRI( inSri );" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Set our stream ID ..." + NL + "    //" + NL + "    void setStreamID( std::string &sid ) {" + NL + "      streamID=sid;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Return the number of scalars per element (sample) that we use" + NL + "    //" + NL + "    inline int spe() {" + NL + "      return ScalarsPerElement(sri.mode);" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // return the state if EOS was pushed down stream" + NL + "    //" + NL + "    inline bool eos () {" + NL + "      return _eos;" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // return the vector length to process data by the GR_BLOCK" + NL + "    //" + NL + "    inline int vlen() {" + NL + "      return _vlen;" + NL + "    }" + NL + "" + NL + "    inline bool eos ( bool inEos ) {" + NL + "       _eos=inEos;" + NL + "       return _eos;" + NL + "    }" + NL + "" + NL + "    void _check( int idx ) {" + NL + "       if ( grb ) {" + NL + "          int nvlen=1;" + NL + "\t  try {" + NL + "\t     if ( grb && grb->output_signature() )" + NL + "\t        nvlen = grb->output_signature()->sizeof_stream_item(idx) / (spe() *  sizeof( typename OUT_PORT_TYPE::RH_NativeType));" + NL + "\t     //std::cout << \"_CheckVlen IDX: \" << _idx << \" NEW/OLD VLEN: \" << nvlen << \"/\" << _vlen << \" SPE:\" << spe() ;" + NL + "\t     if ( nvlen != _vlen && nvlen >= 1 ) _vlen=nvlen;" + NL + "\t  }" + NL + "\t  catch(...) {" + NL + "\t    //std::cout << \"UNABLE TO SET VLEN, BAD INDEX:\" << idx ;" + NL + "\t  }" + NL + "       }" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // establish and assocation with a new GR_BLOCK" + NL + "    //" + NL + "    void associate( GNU_RADIO_BLOCK_PTR newblock ) {" + NL + "        grb = newblock;" + NL + "       _check( _idx );" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // return the number of items in the output buffer" + NL + "    //" + NL + "    inline uint64_t nitems () {" + NL + "       uint64_t tmp=nelems();" + NL + "       if ( _vlen > 0 ) tmp /= _vlen;" + NL + "       return tmp;" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // return the number of elements (samples) in the output buffer" + NL + "    //" + NL + "    inline uint64_t  nelems() {" + NL + "       uint64_t tmp = _data.size();" + NL + "       if ( spe() > 0 ) tmp /= spe();" + NL + "       return tmp;" + NL + "    };" + NL + "" + NL + "" + NL + "    //" + NL + "    // return the number of scalars used for N number of items" + NL + "    //" + NL + "    inline uint64_t itemsToScalars( uint64_t N ) {" + NL + "       return  N*_vlen*spe();" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // return the number of output elements sent down stream" + NL + "    //" + NL + "    inline uint64_t  oelems() {\t" + NL + "       return _nelems;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // return the number of output items sent down stream" + NL + "    //" + NL + "    inline uint64_t  oitems() {" + NL + "       uint64_t tmp = _nelems;" + NL + "       if ( _vlen > 0 ) tmp /= _vlen;" + NL + "       return tmp;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // Turn time stamp calculations on or off" + NL + "    //" + NL + "    void setAutoAdjustTime( bool onoff ) {" + NL + "       _m_tstamp = onoff;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // resize the output buffer to N number of items" + NL + "    //" + NL + "    void resize( int32_t n_items ) {" + NL + "       if ( _data.size() != (size_t)(n_items*spe()*_vlen) ) {" + NL + "          _data.resize( n_items*spe()*_vlen );" + NL + "       }" + NL + "    }" + NL + "" + NL + "    typename OUT_PORT_TYPE::RH_NativeType *write_pointer(){" + NL + "       // push ostream's buffer address onto list of output buffers" + NL + "       return &(_data[0]);" + NL + "    }" + NL + "" + NL + "    //" + NL + "    // sets time stamp value to be time of day" + NL + "    //" + NL + "    void setTimeStamp( ) {    " + NL + "        struct timeval tmp_time;" + NL + "\tstruct timezone tmp_tz;" + NL + "\tgettimeofday(&tmp_time, &tmp_tz);" + NL + "\ttstamp.twsec = tmp_time.tv_sec;" + NL + "\ttstamp.tfsec = tmp_time.tv_usec / 1e6;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // set time stamp value for the stream to a specific value, turns on " + NL + "    // stream's monitoring of time stamp" + NL + "    //" + NL + "    void setTimeStamp( TimeStamp &inTimeStamp, bool adjust_ts=true ) {    " + NL + "      _m_tstamp = adjust_ts;" + NL + "      tstamp = inTimeStamp;" + NL + "    };" + NL + "" + NL + "    void forwardTimeStamp( int32_t noutput_items, TimeStamp &ts ) {    " + NL + "    \tdouble twsec = ts.twsec;" + NL + "    \tdouble tfsec = ts.tfsec;" + NL + "\tdouble sdelta=sri.xdelta;" + NL + "\tsdelta  = sdelta * noutput_items*_vlen;" + NL + "     \tdouble new_time = (twsec+tfsec)+sdelta;" + NL + "     \tts.tfsec = std::modf( new_time, &ts.twsec );" + NL + "    };" + NL + "" + NL + "    void forwardTimeStamp( int32_t noutput_items ) {    " + NL + "    \tdouble twsec = tstamp.twsec;" + NL + "    \tdouble tfsec = tstamp.tfsec;" + NL + "\tdouble sdelta=sri.xdelta;" + NL + "\tsdelta  = sdelta * noutput_items*_vlen;" + NL + "     \tdouble new_time = (twsec+tfsec)+sdelta;" + NL + "     \ttstamp.tfsec = std::modf( new_time, &tstamp.twsec );" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // write data to output ports using the provided time stamp and adjust the time " + NL + "    // accordingly using the xdelta value of the SRI and the number of items" + NL + "    //" + NL + "    int  write( int32_t n_items, bool eos, TimeStamp &ts, bool adjust_ts=false ) {" + NL + "" + NL + "       resize( n_items );" + NL + "" + NL + "       if ( port ) port->pushPacket( _data, ts, eos, streamID );" + NL + "" + NL + "       if ( adjust_ts ) forwardTimeStamp( n_items, ts );" + NL + "" + NL + "       _eos = eos;" + NL + "       _nelems += (n_items*_vlen);" + NL + "       return n_items;" + NL + "    };" + NL + "" + NL + "    //" + NL + "    // write data to the output port using the map object's timestamp" + NL + "    // if the adjust_ts value equals true. otherwise use time of" + NL + "    // day for the time stamp" + NL + "    //" + NL + "    int  write( int32_t n_items, bool eos, bool adjust_ts ) {" + NL + "       if ( !adjust_ts ) setTimeStamp();" + NL + "" + NL + "       resize( n_items );" + NL + "       if ( port ) port->pushPacket( _data, tstamp, eos, streamID );" + NL + "" + NL + "       if ( adjust_ts ) forwardTimeStamp( n_items );" + NL + "" + NL + "       _eos = eos;" + NL + "       _nelems += (n_items*_vlen);" + NL + "       return n_items;" + NL + "    };" + NL + "" + NL + "" + NL + "    //" + NL + "    // write data to the output port using the map object's timestamp and" + NL + "    // adjust the time stamp if the maps's m_tstamp value == true" + NL + "    //" + NL + "    int  write( int32_t n_items, bool eos ) {" + NL + "       if ( !_m_tstamp ) setTimeStamp();" + NL + "" + NL + "       resize( n_items );" + NL + "       if ( port ) port->pushPacket( _data, tstamp, eos, streamID );" + NL + "" + NL + "       if ( _m_tstamp ) forwardTimeStamp( n_items );" + NL + "" + NL + "       _eos = eos;" + NL + "       _nelems += n_items*_vlen;" + NL + "       return n_items;" + NL + "    };" + NL + "" + NL + "    // perform clean up on the stream state and map" + NL + "    void close() {" + NL + "      _data.clear();" + NL + "      _nelems = 0;" + NL + "      _vlen=1;" + NL + "      _eos = false;" + NL + "      _m_tstamp=false;" + NL + "    };" + NL + "" + NL + "  };" + NL + "  " + NL;
  protected final String TEXT_50 = NL;
  protected final String TEXT_51 = " " + NL + "  //" + NL + "  // template Typedefs work around" + NL + "  //" + NL + "  template < typename T > class _IStream {" + NL + "      private:" + NL + "        _IStream(void) {};" + NL + "      public:" + NL + "        typedef typename std::vector< gr_istream< T > > List;" + NL + "  };";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "  template < typename T > class _OStream {" + NL + "      private:" + NL + "        _OStream(void) {};" + NL + "      public:" + NL + "        typedef typename std::vector< gr_ostream< T > > List;" + NL + "  };" + NL;
  protected final String TEXT_54 = NL + NL + "  typedef  gr_vector_const_void_star                GR_IN_BUFFERS;" + NL + "  typedef  gr_vector_void_star                      GR_OUT_BUFFERS;" + NL + "  typedef  gr_vector_int                            GR_BUFFER_LENGTHS;   " + NL + "  ";
  protected final String TEXT_55 = " " + NL + "  template < typename IN_PORT_TYPE >   int  _analyzerServiceFunction( typename  std::vector< gr_istream< IN_PORT_TYPE > > &istreams );" + NL + "  template < typename IN_PORT_TYPE >   int  _forecastAndProcess( bool &eos, std::vector< gr_istream< IN_PORT_TYPE > > &istreams );" + NL;
  protected final String TEXT_56 = NL;
  protected final String TEXT_57 = NL + "  template < typename OUT_PORT_TYPE >                         int  _generatorServiceFunction( std::vector< gr_ostream< OUT_PORT_TYPE > >  &ostreams );";
  protected final String TEXT_58 = NL;
  protected final String TEXT_59 = NL + "template < typename IN_PORT_TYPE, typename OUT_PORT_TYPE >  int  _transformerServiceFunction( std::vector< gr_istream< IN_PORT_TYPE > > &istreams," + NL + "                                                                         std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams ); " + NL + "template < typename IN_PORT_TYPE, typename OUT_PORT_TYPE >  int  _forecastAndProcess( bool &eos, std::vector< gr_istream< IN_PORT_TYPE > > &istreams," + NL + "                                                                         std::vector< gr_ostream< OUT_PORT_TYPE > > &ostreams ); ";
  protected final String TEXT_60 = NL;
  protected final String TEXT_61 = " " + NL + " typedef std::deque< std::pair< BULKIO_data";
  protected final String TEXT_62 = "_In_i *, BULKIO::StreamSRI > >  SRIQueue;" + NL + "  " + NL + "  typedef _IStream< BULKIO_data";
  protected final String TEXT_63 = "_In_i >::List    IStreamList;";
  protected final String TEXT_64 = NL;
  protected final String TEXT_65 = NL + "  typedef _OStream< BULKIO_data";
  protected final String TEXT_66 = "_Out_i >::List   OStreamList;";
  protected final String TEXT_67 = "  " + NL + "    " + NL + "    " + NL + "   ProcessThread<";
  protected final String TEXT_68 = "_base> *serviceThread; " + NL + "   boost::mutex serviceThreadLock;  " + NL + "" + NL + "  // cache variables to transferring data to/from a GNU Radio Block" + NL + "  std::vector<bool>            _input_ready;" + NL + "  GR_BUFFER_LENGTHS            _ninput_items_required;" + NL + "  GR_BUFFER_LENGTHS            _ninput_items;" + NL + "  GR_IN_BUFFERS                _input_items;" + NL + "  GR_OUT_BUFFERS               _output_items;" + NL + "  int32_t                      noutput_items;" + NL + "  " + NL + " ";
  protected final String TEXT_69 = " " + NL + "  // Listener object for handling SRI Changes" + NL + "  SRIListener                  _sriListener;" + NL + "  boost::mutex                _sriMutex;  " + NL + "  SRIQueue                    _sriQueue;";
  protected final String TEXT_70 = NL + NL + "  // mapping of RH ports to GNU Radio streams";
  protected final String TEXT_71 = " " + NL + "  IStreamList                  _istreams;";
  protected final String TEXT_72 = NL + "  ";
  protected final String TEXT_73 = NL + "  OStreamList                  _ostreams;" + NL + "  bool                         sentEOS;";
  protected final String TEXT_74 = "   " + NL;
  protected final String TEXT_75 = NL + "        ";
  protected final String TEXT_76 = "        " + NL + "        ";
  protected final String TEXT_77 = NL + "  // Member variables exposed as properties";
  protected final String TEXT_78 = NL;
  protected final String TEXT_79 = NL + "   ";
  protected final String TEXT_80 = " ";
  protected final String TEXT_81 = ";";
  protected final String TEXT_82 = NL;
  protected final String TEXT_83 = NL + "  // Ports";
  protected final String TEXT_84 = NL + "  MessageConsumerPort *";
  protected final String TEXT_85 = ";";
  protected final String TEXT_86 = NL + "  ";
  protected final String TEXT_87 = "_";
  protected final String TEXT_88 = "_In_i *";
  protected final String TEXT_89 = ";";
  protected final String TEXT_90 = NL + "  ";
  protected final String TEXT_91 = "_";
  protected final String TEXT_92 = "_Out_i *";
  protected final String TEXT_93 = ";";
  protected final String TEXT_94 = NL + "  std::vector< std::string > inputPortOrder;";
  protected final String TEXT_95 = NL + "  std::vector< std::string > outputPortOrder;";
  protected final String TEXT_96 = NL + "    " + NL + "  ENABLE_LOGGING;" + NL + "    " + NL + "  protected:" + NL + "" + NL + "     bool                     _maintainTimeStamp;" + NL + "     bool                     _throttle;" + NL + "     TimeMark                 p_start_time;" + NL + "     TimeMark                 p_end_time;" + NL + "" + NL + " private:" + NL + "     void construct();" + NL + "        " + NL + "  public:" + NL + "" + NL + "  SF_State serviceFunction()" + NL + "  {" + NL + "" + NL + "    SF_State retval = NOOP;";
  protected final String TEXT_97 = "     " + NL + "    retval = _analyzerServiceFunction( _istreams );";
  protected final String TEXT_98 = "     " + NL + "    retval = _generatorServiceFunction( _ostreams );";
  protected final String TEXT_99 = NL + "    retval = _transformerServiceFunction( _istreams, _ostreams );";
  protected final String TEXT_100 = NL + NL + "    p_end_time =  boost::posix_time::microsec_clock::local_time();" + NL + "    if ( retval == NORMAL && _throttle ) {" + NL + "      TimeDuration  delta = calcThrottle( p_start_time, " + NL + "\t\t                          p_end_time );" + NL + "      if ( delta.is_not_a_date_time() == false && delta.is_negative() == false )  {" + NL + "\tLOG_TRACE( ";
  protected final String TEXT_101 = "_base, \" SLEEP ....\" << delta );" + NL + "\tboost::this_thread::sleep( delta );" + NL + "      }" + NL + "      else {" + NL + "\tLOG_TRACE( ";
  protected final String TEXT_102 = "_base, \" NO SLEEPING....\" );" + NL + "      }" + NL + "    }" + NL + "    p_start_time = p_end_time;" + NL + "       " + NL + "    LOG_TRACE( ";
  protected final String TEXT_103 = "_base, \" serviceFunction: retval:\" << retval);" + NL + "" + NL + "    return retval;" + NL + "  };       " + NL + "        " + NL + "        " + NL + "};" + NL + "#endif";
  protected final String TEXT_104 = NL;

  /**
   * {@inheritDoc}
   */
  public String generate(Object argument) throws CoreException
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
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


    
    TemplateParameter templ = (TemplateParameter) argument;
    ImplementationSettings implSettings = templ.getImplSettings();
    Implementation impl = templ.getImpl();
    SoftPkg softPkg = (SoftPkg) impl.eContainer();
    String PREFIX = gov.redhawk.ide.codegen.util.CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
    List<IPath> search_paths = Arrays.asList(RedhawkIdeActivator.getDefault().getDefaultIdlIncludePath());
    List<CppProperties.Property> properties = CppProperties.getProperties(softPkg);
    EList<Uses> uses = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getUses();
    EList<Provides> provides = softPkg.getDescriptor().getComponent().getComponentFeatures().getPorts().getProvides();
    String deviceType = "";
    String baseClass = "Resource_impl";
    boolean hasPorts = (uses.size() > 0) || (provides.size() > 0);
    boolean hasPushPacketCall = false;
    boolean aggregateDevice = false;

    // TODO: Refactor this long block of code (and other similar blocks) into one handy place that can just give you an enum
    final List<SupportsInterface> supportedInterfaces = softPkg.getDescriptor().getComponent().getComponentFeatures().getSupportsInterface();
    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/Device:1.0")) {
            deviceType = "";
            baseClass = "Device_impl";
            break;
        }
    }

    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/LoadableDevice:1.0")) {
            deviceType = "Loadable";
            baseClass = "LoadableDevice_impl";
            break;
        }
    }

    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/ExecutableDevice:1.0")) {
            deviceType = "Executable"; 
            baseClass = "ExecutableDevice_impl";
            break;
        }
    }
    
    for (SupportsInterface inter : supportedInterfaces) {
        if (inter.getRepId().equals("IDL:CF/AggregateDevice:1.0")) {
            aggregateDevice = true;
            
            if ("Executable".equals(deviceType)) {
                baseClass = "virtual POA_CF::AggregateExecutableDevice, public " + baseClass;
            } else if ("Loadable".equals(deviceType)) {
                baseClass = "virtual POA_CF::AggregateLoadableDevice, public " + baseClass;
            } else {
                baseClass = "virtual POA_CF::AggregatePlainDevice, public " + baseClass;
            }
            baseClass += ", public AggregateDevice_impl ";
            break;
        }
   }
    
    HashSet<String> usesList = new HashSet<String>();
    boolean hasPropChangePort = false;
    for (Uses entry : uses) {
        String intName = entry.getRepID();
        if (PropertyChangeEventPortTemplate.EVENTCHANNEL_REPID.equals(intName) 
                && PropertyChangeEventPortTemplate.EVENTCHANNEL_NAME.equals(entry.getUsesName())) {
            hasPropChangePort = true;
        } else {
            usesList.add(intName);
        }
    }
    
    HashSet<String> providesList = new HashSet<String>();
    for (Provides entry : provides) {
        String intName = entry.getRepID();
        providesList.add(intName);
    }

    for (String entry : providesList) {
        Interface iface = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true);
        if (iface == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
        }
        for (Operation op : iface.getOperations()) {
            if ("pushPacket".equals(op.getName())) {
                hasPushPacketCall = true;
                break;
            }
        }
    }
    
    Pattern p = Pattern.compile("/(.*):");
    boolean containsBULKIO = false;
    String outputType = "";         // maps to bulkio for i.e BULKIO_dataUshort_Out_i Ushort
    String outputRaw = "";          // maps to C type defined in port_impl.h UInt16
    for (Uses entry : uses) {
        String intName = entry.getRepID();

        if (intName.contains("BULKIO")) {
	   Matcher m = p.matcher(intName);
	   String iname="";	
	   if ( m.find() ) {
	   	iname = m.group(1);
           }
	   
            containsBULKIO = true;
            if (iname.equals("dataFloat")) {
                outputType = "Float";
                outputRaw = "Float";
            } else if (iname.equals("dataOctet")) {
                outputType = "Octet";
                outputRaw = "UInt8";
            } else if (iname.equals("dataChar")) {
                outputType = "Char";
                outputRaw = "Char";
            } else if (iname.equals("dataUshort")) {
                outputType = "Ushort";
                outputRaw = "UInt16";
            } else if (iname.equals("dataShort")) {
                outputType = "Short";
                outputRaw = "Int16";
            } else if (iname.equals("dataLong")) {
                outputType = "Long";
                  outputRaw = "Int32";
            } else if (iname.equals("dataLongLong")) {
                outputType = "LongLong";
                outputRaw = "Int64";
            } else if (iname.equals("dataUlong")) {
                outputType = "Ulong";
                  outputRaw = "UInt32";
            } else if (iname.equals("dataUlonglong")) {
                outputType = "Ulonglong";
                outputRaw = "UInt64";
            } else if (iname.equals("dataDouble")) {
                outputType = "Double";
                outputRaw = "Double";
            }
        }
    }
    String inputType = "";         // maps to bulkio for i.e BULKIO_dataUlong_In_i Ulong
    String inputRaw = "";          // maps to C type defined in port_impl.h UInt32
    for (Provides entry : provides) {
        String intName = entry.getRepID();
        if (intName.contains("BULKIO")) {
           containsBULKIO = true;
	   Matcher m = p.matcher(intName);
	   String iname="";	
	   if ( m.find() ) {
	   	iname = m.group(1);
           }

            if (iname.equals("dataFloat")) {
                inputType = "Float";
                inputRaw = "Float";
            } else if (iname.equals("dataOctet")) {
                inputType = "Octet";
                inputRaw = "UInt8";
            } else if (iname.equals("dataChar")) {
                inputType = "Char";
                inputRaw = "Char";
            } else if (iname.equals("dataUshort")) {
                inputType = "Ushort";
                   inputRaw = "UInt16";
            } else if (iname.equals("dataShort")) {
                inputType = "Short";
                  inputRaw = "Int16";
            } else if (iname.equals("dataLong")) {
                inputType = "Long";
                inputRaw = "Int32";
            } else if (iname.equals("dataLongLong")) {
                inputType = "LongLong";
                inputRaw = "Int64";
            } else if (iname.equals("dataUlong")) {
                inputType = "Ulong";
                inputRaw = "UInt32";
            } else if (iname.equals("dataUlonglong")) {
                inputType = "Ulonglong";
                inputRaw = "UInt64";
            } else if (iname.equals("dataDouble")) {
                inputType = "Double";
                inputRaw = "Double";
            }
        }
    }
    
    String blockType = "";
    for (Property tempProp : implSettings.getProperties()) {
        if ("pattern_gr_block".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
                blockType = "GR_Block";
                break;
            }
        }
        if ("pattern_gr_sync_block".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
                blockType = "GR_Sync_Block";
                break;
            }
        }
        if ("pattern_gr_sync_decimator".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
                blockType = "GR_Sync_Decimator";
                break;
            }
        }
        if ("pattern_gr_sync_interpolator".equals(tempProp.getId())) {
            if (Boolean.parseBoolean(tempProp.getValue())) {
                blockType = "GR_Sync_Interpolator";
                break;
            }
        }
    }

    stringBuffer.append(TEXT_1);
    stringBuffer.append(PREFIX.toUpperCase());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(PREFIX.toUpperCase());
    stringBuffer.append(TEXT_3);
    if (aggregateDevice) {
    stringBuffer.append(TEXT_4);
    }
    if (aggregateDevice) {
    stringBuffer.append(TEXT_5);
    }
    stringBuffer.append(TEXT_6);
    if (templ.isDevice()) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(deviceType);
    stringBuffer.append(TEXT_8);
    }
    if (hasPushPacketCall) {
    stringBuffer.append(TEXT_9);
    }
    if (hasPorts) {
    stringBuffer.append(TEXT_10);
    }
    List<Struct> structProps = new ArrayList<Struct>();
    structProps.addAll(softPkg.getPropertyFile().getProperties().getStruct());
    for (StructSequence structSequence : softPkg.getPropertyFile().getProperties().getStructSequence()) {
        if (structSequence.getStruct() != null) {
            structProps.add(structSequence.getStruct());
        }
    }
    if (!structProps.isEmpty()) {
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_14);
    

    for (String entry : providesList) {
        Interface iface = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true);
        if (iface == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
        }
        String nameSpace = iface.getNameSpace();
        String interfaceName = iface.getName();

    stringBuffer.append(TEXT_15);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_17);
    
    }

    for (String entry : usesList) {
        Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true);
        if (intf == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
        }
        String nameSpace = intf.getNameSpace();
        String interfaceName = intf.getName();

    stringBuffer.append(TEXT_18);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_20);
    
    }
    if (hasPropChangePort) {

    stringBuffer.append(TEXT_21);
    
    }

    stringBuffer.append(TEXT_22);
     if ( !inputType.equals("")) { 
    stringBuffer.append(TEXT_23);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_29);
     } 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_32);
     if ((uses.size() > 0) || (provides.size() > 0)) { 
    stringBuffer.append(TEXT_33);
     } 
    stringBuffer.append(TEXT_34);
     if ( !outputType.equals("")) { 
    stringBuffer.append(TEXT_35);
     } 
    stringBuffer.append(TEXT_36);
     if ( !outputType.equals("") || !inputType.equals("") ) { 
    stringBuffer.append(TEXT_37);
     } 
    stringBuffer.append(TEXT_38);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_39);
     } 
    stringBuffer.append(TEXT_40);
     if (!inputType.equals("")) { 
    stringBuffer.append(TEXT_41);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_42);
     } 
    stringBuffer.append(TEXT_43);
     if (!outputType.equals("")) { 
    stringBuffer.append(TEXT_44);
     } 
    stringBuffer.append(TEXT_45);
    
  //
  // gr_istream
  //
  // RESOLVE: This class should to be integrated with the Port objects for a component
  // that would resolve a lot of the integration issues.. We already have a queue (vertically)
  // that holds all the incoming data... this class is just partitioning the data
  // queue (horizontally) based on stream id..  Too much data movement, re-copying of 
  // vector contents and then presenting this to the work method of the Gnu Radio Block.
  //
  // Need to look at strategies of limit the number of copies and the vector resizing
  // that happens.
  //
  // We also ran across the issue of data synchronization from multiple ports,
  // and a GR_SYNC blocks. There is always the possiblity of getting swapped out by the
  // scheduled at any time your process is running.
  //
  //

    stringBuffer.append(TEXT_46);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_47);
     } 
    stringBuffer.append(TEXT_48);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_49);
     } 
    stringBuffer.append(TEXT_50);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_51);
     } 
    stringBuffer.append(TEXT_52);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_53);
     } 
    stringBuffer.append(TEXT_54);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_55);
     } 
    stringBuffer.append(TEXT_56);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_57);
     } 
    stringBuffer.append(TEXT_58);
     if ( !inputType.equals("") && !outputType.equals("") ) { 
    stringBuffer.append(TEXT_59);
     } 
    stringBuffer.append(TEXT_60);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_61);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(inputType);
    stringBuffer.append(TEXT_63);
     } 
    stringBuffer.append(TEXT_64);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_65);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_66);
     } 
    stringBuffer.append(TEXT_67);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_68);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_69);
     } 
    stringBuffer.append(TEXT_70);
     if ( !inputType.equals("") ) { 
    stringBuffer.append(TEXT_71);
     } 
    stringBuffer.append(TEXT_72);
     if ( !outputType.equals("") ) { 
    stringBuffer.append(TEXT_73);
     } 
    stringBuffer.append(TEXT_74);
     if (blockType.equals("GR_Sync_Decimator")) { 
     } 
    stringBuffer.append(TEXT_75);
     if (blockType.equals("GR_Sync_Interpolator")) { 
     } 
    stringBuffer.append(TEXT_76);
    if (properties.size() > 0) { 
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
      for (CppProperties.Property prop : properties) { 
    stringBuffer.append(TEXT_79);
    stringBuffer.append(prop.getCppType());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(prop.getCppName());
    stringBuffer.append(TEXT_81);
    }
    stringBuffer.append(TEXT_82);
    if (hasPorts) { 
    stringBuffer.append(TEXT_83);
    }
    
    for (Provides pro : provides) {
      String entry = pro.getRepID();
      Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true);
      if (intf == null) { 
          throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry));
      } 
      String nameSpace = intf.getNameSpace();
      String interfaceName = intf.getName();
      if (MessagingPortTemplate.MESSAGECHANNEL_REPID.equals(entry)) {

    stringBuffer.append(TEXT_84);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_85);
    
      } else {

    stringBuffer.append(TEXT_86);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(pro.getProvidesName());
    stringBuffer.append(TEXT_89);
    
     }
   }
   for (Uses use : uses) {
        String entry = use.getRepID();
        Interface intf = IdlUtil.getInstance().getInterface(search_paths, entry.split(":")[1], true); 
        if (intf == null) {
            throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entry)); 
        }
        String nameSpace = intf.getNameSpace();
        String interfaceName = intf.getName();
        // Loop over provides ports to see if there is a matching interface and port name for the current uses port
        // If so, ignore the uses port
        // This is to support bi-directional ports
        boolean foundMatchingProvides = false;
        for (Provides pro : provides) {
            String entryProvides = pro.getRepID();
            Interface intfProvides = IdlUtil.getInstance().getInterface(search_paths, entryProvides.split(":")[1], true);
            if (intfProvides == null) { 
                throw new CoreException(new Status(IStatus.ERROR, CplusplusJetGeneratorPlugin.PLUGIN_ID, "Unable to find interface for " + entryProvides));
            } 
            String nameSpaceProvides = intfProvides.getNameSpace();
            String interfaceNameProvides = intfProvides.getName();
            if (entry.equals(entryProvides) && use.getUsesName().equals(pro.getProvidesName())) {
                foundMatchingProvides = true;
                break;
            }
        } // for (Provides pro : provides)
        if(foundMatchingProvides == false){

    stringBuffer.append(TEXT_90);
    stringBuffer.append(nameSpace);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(interfaceName);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(use.getUsesName());
    stringBuffer.append(TEXT_93);
    
       } // if(foundMatchingProvides == false)
  } // for (Uses use : uses)

    
    if (provides.size() > 0) {

    stringBuffer.append(TEXT_94);
    
    }
    if (uses.size() > 0) {

    stringBuffer.append(TEXT_95);
    
    }

    stringBuffer.append(TEXT_96);
     if ( !inputType.equals("") && outputType.equals("") ) { 
    stringBuffer.append(TEXT_97);
     } else if ( inputType.equals("") && !outputType.equals("") ) { 
    stringBuffer.append(TEXT_98);
     } else if ( !inputType.equals("") && !outputType.equals("") ) { 
    stringBuffer.append(TEXT_99);
     } 
    stringBuffer.append(TEXT_100);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(PREFIX);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(TEXT_104);
    return stringBuffer.toString();
  }
}

// END GENERATED CODE