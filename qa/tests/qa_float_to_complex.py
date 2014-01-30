#
# This file is protected by Copyright. Please refer to the COPYRIGHT file
# distributed with this source distribution.
#
# This file is part of GNUHAWK.
#
# GNUHAWK is free software: you can redistribute it and/or modify is under the
# terms of the GNU General Public License as published by the Free Software
# Foundation, either version 3 of the License, or (at your option) any later
# version.
#
# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY
# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
# A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

# You should have received a copy of the GNU General Public License along with
# this program.  If not, see http://www.gnu.org/licenses/.
#

from gnuradio import gr, gr_unittest
import math
import time
from ossie.utils import sb

class test_complex_ops (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_float_to_complex_1 (self):
        src_data = (0, 1, -1, 3, -3, -2, -3, 2)
        expected_result = (0, 1, -1, 3+0j, -3+0j, -2+0j, -3+0j, 2+0j)
        src = gr.vector_source_f (src_data)
        op = gr.float_to_complex (numPorts=1)
        dst = gr.vector_sink_c ()
        self.tb.connect (src, op)
        self.tb.connect (op, dst)
        self.tb.run ()              
        actual_result = dst.data ()
        self.assertComplexTuplesAlmostEqual (expected_result, actual_result)

    def test_float_to_complex_2 (self):
        src_data_0 = [0, 1, -1, 3, -3, 2, -4, -2]
        src_data_1 = [0, 0, 0, 4, -4, 4, -4, 4]
        expected_result = (0, 1, -1, 3+4j, -3-4j, 2+4j, -4-4j, -2+4j)
        src0 = sb.DataSource()
        src1 = sb.DataSource()
        op = sb.launch('../components/float_to_complex_2i/float_to_complex_2i.spd.xml')
        dst = gr.vector_sink_c ()
        src0.connect(op, providesPortName='real_float_in')
        src1.connect(op, providesPortName='imag_float_in')
        op.connect(dst)
        sb.start()
        src0.push(src_data_0, EOS=True)
        src1.push(src_data_1, EOS=True)
        actual_result = dst.data ()
        self.assertComplexTuplesAlmostEqual (expected_result, actual_result)
