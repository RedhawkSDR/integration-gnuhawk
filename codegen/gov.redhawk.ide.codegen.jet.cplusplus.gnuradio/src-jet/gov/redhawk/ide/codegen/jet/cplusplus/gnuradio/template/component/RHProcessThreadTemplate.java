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

import gov.redhawk.ide.codegen.jet.TemplateParameter;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import gov.redhawk.ide.codegen.ImplementationSettings;

/**
 * @generated
 */
public class RHProcessThreadTemplate
{

  protected static String nl;
  public static synchronized RHProcessThreadTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    RHProcessThreadTemplate result = new RHProcessThreadTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/*" + NL + " * This file is protected by Copyright. Please refer to the COPYRIGHT file " + NL + " * distributed with this source distribution." + NL + " * " + NL + " * This file is part of GNUHAWK." + NL + " * " + NL + " * GNUHAWK is free software: you can redistribute it and/or modify is under the " + NL + " * terms of the GNU General Public License as published by the Free Software " + NL + " * Foundation, either version 3 of the License, or (at your option) any later " + NL + " * version." + NL + " * " + NL + " * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY " + NL + " * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS " + NL + " * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more " + NL + " * details." + NL + " * " + NL + " * You should have received a copy of the GNU General Public License along with " + NL + " * this program.  If not, see http://www.gnu.org/licenses/." + NL + " */" + NL + "#ifndef RH_PROCESS_THREAD_H" + NL + "#define RH_PROCESS_THREAD_H" + NL + "" + NL + "#include <boost/thread.hpp>" + NL + "#include <boost/date_time/posix_time/posix_time.hpp>" + NL + "" + NL + "" + NL + "enum SF_STATE { FINISH=-1, NOOP=0,  NORMAL=1 };" + NL + "" + NL + "typedef int   SF_State;" + NL + "" + NL + "" + NL + "template < typename TargetClass >" + NL + "class ProcessThread" + NL + "{" + NL + "    public:" + NL + "" + NL + "        ProcessThread(TargetClass *_target, float _delay=0.1) :" + NL + "            target(_target)" + NL + "        {" + NL + "            _mythread = 0;" + NL + "            _thread_running = false;" + NL + "            _udelay = (__useconds_t)(_delay * 1000000);" + NL + "        };" + NL + "" + NL + "        // kick off the thread" + NL + "        void start() {" + NL + "            if (_mythread == 0) {" + NL + "                _thread_running = true;" + NL + "                _mythread = new boost::thread(&ProcessThread::run, this);" + NL + "            }" + NL + "        };" + NL + "" + NL + "        // manage calls to target's service function" + NL + "        void run() {" + NL + "            SF_State state = NORMAL;" + NL + "            while (_thread_running and (state != FINISH)) {" + NL + "                state = target->serviceFunction();" + NL + "                if (state == NOOP) {" + NL + "\t\t  boost::this_thread::sleep( boost::posix_time::microseconds( _udelay ) );" + NL + "                } else {" + NL + "\t\t    boost::this_thread::yield();" + NL + "                }" + NL + "            }" + NL + "        };" + NL + "        " + NL + "        void stop() {" + NL + "          _thread_running = false;" + NL + "\t   if ( _mythread ) _mythread->interrupt();" + NL + "        };" + NL + "" + NL + "        // stop thread and wait for termination" + NL + "        bool release(unsigned long secs = 0, unsigned long usecs = 0) {" + NL + "            _thread_running = false;" + NL + "            if (_mythread)  {" + NL + "                if ((secs == 0) and (usecs == 0)){" + NL + "                    _mythread->join();" + NL + "                } else {" + NL + "                    boost::system_time waitime= boost::get_system_time() + boost::posix_time::seconds(secs) +  boost::posix_time::microseconds(usecs) ;" + NL + "                    if (!_mythread->timed_join(waitime)) {" + NL + "                        return 0;" + NL + "                    }" + NL + "                }" + NL + "                delete _mythread;" + NL + "                _mythread = 0;" + NL + "            }" + NL + "    " + NL + "            return 1;" + NL + "        };" + NL + "" + NL + "        virtual ~ProcessThread(){" + NL + "            if (_mythread != 0) {" + NL + "                release(0);" + NL + "                _mythread = 0;" + NL + "            }" + NL + "        };" + NL + "" + NL + "        void updateDelay(float _delay) { _udelay = (__useconds_t)(_delay * 1000000); };" + NL + "" + NL + "        bool threadRunning() { return _thread_running; };" + NL + "" + NL + "    private:" + NL + "        boost::thread *_mythread;" + NL + "        bool _thread_running;" + NL + "        TargetClass *target;" + NL + "        __useconds_t _udelay;" + NL + "        boost::condition_variable _end_of_run;" + NL + "        boost::mutex _eor_mutex;" + NL + "};" + NL + "" + NL + "" + NL + "template< typename CT>" + NL + "ProcessThread<CT> *service_thread(CT *component, float _delay )" + NL + "{" + NL + "   return new ProcessThread<CT>(component, _delay);" + NL + "};" + NL + "" + NL + "" + NL + "#endif";
  protected final String TEXT_2 = NL;

    /**
    * {@inheritDoc}
    */
    public String generate(Object argument)
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
    Implementation impl = templ.getImpl();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
} 

// END GENERATED CODE