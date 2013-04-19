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
 
from ossie.utils import sb
from gnuradio import gr, gr_unittest
import os

class test_add (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_add_cc (self):
        src_data = [1,2,3,4,5, 6,7,8]
        expected_result = [2,4,6,8,10,12,14,16]
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.Component("../components/add_cc_2i/add_cc_2i.spd.xml")
        d = sb.DataSink()
        a.connect(c,providesPortName="data_complex_in_0")
        b.connect(c,providesPortName="data_complex_in_1")
        c.connect(d)
        sb.start()
        a.push(src_data,EOS=True)
        b.push(src_data,EOS=True)
        result_data = d.getData(eos_block=True)
        self.assertEqual(expected_result, result_data)

    def test_add_ff (self):
        src_data = [1,2,3,4,5, 6,7,8]
        expected_result = [2,4,6,8,10,12,14,16]
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.Component("../components/add_ff_2i/add_ff_2i.spd.xml")
        d = sb.DataSink()
        a.connect(c,providesPortName="data_in_0")
        b.connect(c,providesPortName="data_in_1")
        c.connect(d)
        sb.start()
        a.push(src_data,EOS=True)
        b.push(src_data,EOS=True)
        result_data = d.getData(eos_block=True)
        self.assertEqual(expected_result, result_data)

    def test_add_ii (self):
        src_data = [1,2,3,4,5, 6,7,8]
        expected_result = [2,4,6,8,10,12,14,16]
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.Component("../components/add_ii_2i/add_ii_2i.spd.xml")
        d = sb.DataSink()
        a.connect(c,providesPortName="data_in_0")
        b.connect(c,providesPortName="data_in_1")
        c.connect(d)
        sb.start()
        a.push(src_data,EOS=True)
        b.push(src_data,EOS=True)
        result_data = d.getData(eos_block=True)
        self.assertEqual(expected_result, result_data)

    def test_add_ss (self):
        src_data = [1,2,3,4,5, 6,7,8]
        expected_result = [2,4,6,8,10,12,14,16]
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.Component("../components/add_ss_2i/add_ss_2i.spd.xml")
        d = sb.DataSink()
        a.connect(c,providesPortName="data_in_0")
        b.connect(c,providesPortName="data_in_1")
        c.connect(d)
        sb.start()
        a.push(src_data,EOS=True)
        b.push(src_data,EOS=True)
        result_data = d.getData(eos_block=True)
        self.assertEqual(expected_result, result_data)

if __name__ == '__main__':
    gr_unittest.run(test_add)
