#!/usr/bin/env python
#
# Copyright 2007,2010 Free Software Foundation, Inc.
#
# This file is part of GNU Radio
#
# GNU Radio is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation; either version 3, or (at your option)
# any later version.
#
# GNU Radio is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with GNU Radio; see the file COPYING.  If not, write to
# the Free Software Foundation, Inc., 51 Franklin Street,
# Boston, MA 02110-1301, USA.
#
from ossie.utils import sb
from gnuradio import gr, gr_unittest
import math


class test_max_ff_1i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data = (0,0.2,-0.3,0,12,0)
        expected_result = (float(max(src_data)), )

        src = gr.vector_source_f(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, len(src_data))
        op = gr.max_(gr.sizeof_float, 1, len(src_data) )
        dst = gr.vector_sink_f()


        self.tb.connect(src, s2v, op, dst)
        self.tb.run()
        result_data = dst.data()
        self.assertEqual(expected_result, result_data)

    def test_002(self):

        src_data=(-100,-99,-98,-97,-96,-1)
        expected_result = (float(max(src_data)), )

        src = gr.vector_source_f(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, len(src_data))
        op = gr.max_(gr.sizeof_float, 1, len(src_data) )
        dst = gr.vector_sink_f()

        self.tb.connect(src, s2v, op, dst)
        self.tb.run()
        result_data = dst.data()
        self.assertEqual(expected_result, result_data)

class test_max_ff_2i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,0.2,-0.3,0.0,12,0.0,2.0]
        src_data2 = [0,0.3,-0.4,1.0,13.0,1.0,2.0]
        max_src_data = ( max(src_data1), max(src_data2))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = gr.max_(gr.sizeof_float, 2, len(src_data1))
        d = sb.DataSink()
        a.connect(c,providesPortName="float_in_1")
        b.connect(c,providesPortName="float_in_2")
        c.connect(d)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        result_data = d.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1=[-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        max_src_data = ( max(src_data1), max(src_data2))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = gr.max_(gr.sizeof_float, 2, len(src_data1))
        d = sb.DataSink()
        a.connect(c,providesPortName="float_in_1")
        b.connect(c,providesPortName="float_in_2")
        c.connect(d)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        result_data = d.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ff_3i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,0.2,-0.3,0.0,12,0.0,2.0]
        src_data2 = [0,0.3,-0.4,1.0,13.0,1.0,2.0]
        src_data3 = [0,0.4,-0.6,3.0,11.0,1.0,4.0]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = gr.max_(gr.sizeof_float, 3, len(src_data1))
        e = sb.DataSink()
        a.connect(d,providesPortName="float_in_1")
        b.connect(d,providesPortName="float_in_2")
        c.connect(d,providesPortName="float_in_3")
        d.connect(e)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        result_data = e.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1=[-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        src_data3 = [-98,-102, -95,-90, -91,-3]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = gr.max_(gr.sizeof_float, 3, len(src_data1))
        e = sb.DataSink()
        a.connect(d,providesPortName="float_in_1")
        b.connect(d,providesPortName="float_in_2")
        c.connect(d,providesPortName="float_in_3")
        d.connect(e)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        result_data = e.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ff_4i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,0.2,-0.3,0.0,12,0.0,2.0]
        src_data2 = [0,0.3,-0.4,1.0,13.0,1.0,2.0]
        src_data3 = [0,0.4,-0.6,3.0,11.0,1.0,4.0]
        src_data4 = [0,0.5,-0.4,5.0,16.0,2.0,1.0]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3), max(src_data4))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = sb.DataSource(dataFormat="float")
        e = gr.max_(gr.sizeof_float, 4, len(src_data1))
        f = sb.DataSink()
        a.connect(e,providesPortName="float_in_1")
        b.connect(e,providesPortName="float_in_2")
        c.connect(e,providesPortName="float_in_3")
        d.connect(e,providesPortName="float_in_4")
        e.connect(f)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        d.push(src_data4,EOS=True)
        result_data = f.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1= [-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        src_data3 = [-98,-102, -95,-90, -91,-3]
        src_data4 = [-97,-101, -93,-91, -95,-1]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3), max(src_data4))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = sb.DataSource(dataFormat="float")
        e = gr.max_(gr.sizeof_float, 4, len(src_data1))
        f = sb.DataSink()
        a.connect(e,providesPortName="float_in_1")
        b.connect(e,providesPortName="float_in_2")
        c.connect(e,providesPortName="float_in_3")
        d.connect(e,providesPortName="float_in_4")
        e.connect(f)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        d.push(src_data4,EOS=True)
        result_data = f.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ss_1i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data = (0,2,-3,0,12,0)
        expected_result = ((max(src_data)), )

        src = gr.vector_source_s(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_short, len(src_data))
        op = gr.max_(gr.sizeof_short, 1, len(src_data) )
        dst = gr.vector_sink_f()


        self.tb.connect(src, s2v, op, dst)
        self.tb.run()
        result_data = dst.data()
        self.assertEqual(expected_result, result_data)

    def test_002(self):

        src_data=(-100,-99,-98,-97,-96,-1)
        expected_result = ((max(src_data)), )

        src = gr.vector_source_s(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_short, len(src_data))
        op = gr.max_(gr.sizeof_short, 1, len(src_data) )
        dst = gr.vector_sink_f()

        self.tb.connect(src, s2v, op, dst)
        self.tb.run()
        result_data = dst.data()
        self.assertEqual(expected_result, result_data)

class test_max_ss_2i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,2,-3,0,12,0,2]
        src_data2 = [0,3,-4,1,13,1,2]
        max_src_data = ( max(src_data1), max(src_data2))
        expected_result = (max((max_src_data)))

        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = gr.max_(gr.sizeof_short, 2, len(src_data1))
        d = sb.DataSink()
        a.connect(c,providesPortName="short_in_1")
        b.connect(c,providesPortName="short_in_2")
        c.connect(d)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        result_data = d.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1=[-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        max_src_data = ( max(src_data1), max(src_data2))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = gr.max_(gr.sizeof_short, 2, len(src_data1))
        d = sb.DataSink()
        a.connect(c,providesPortName="short_in_1")
        b.connect(c,providesPortName="short_in_2")
        c.connect(d)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        result_data = d.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ss_3i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,2,-3,0,12,0,2]
        src_data2 = [0,3,-4,1,13,1,2]
        src_data3 = [0,4,-6,3,11,1,4]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        d = gr.max_(gr.sizeof_short, 3, len(src_data1))
        e = sb.DataSink()
        a.connect(d,providesPortName="short_in_1")
        b.connect(d,providesPortName="short_in_2")
        c.connect(d,providesPortName="short_in_3")
        d.connect(e)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        result_data = e.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1=[-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        src_data3 = [-98,-102, -95,-90, -91,-3]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3))
        expected_result = max((max_src_data))

        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        d = gr.max_(gr.sizeof_short, 3, len(src_data1))
        e = sb.DataSink()
        a.connect(d,providesPortName="short_in_1")
        b.connect(d,providesPortName="short_in_2")
        c.connect(d,providesPortName="short_in_3")
        d.connect(e)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        result_data = e.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ss_4i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,2,-3,0,12,0,2]
        src_data2 = [0,3,-4,1,13,1,2]
        src_data3 = [0,4,-6,3,11,1,4]
        src_data4 = [0,5,-4,5,16,2,1]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3), max(src_data4))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        d = sb.DataSource(dataFormat="short")
        e = gr.max_(gr.sizeof_short, 4, len(src_data1))
        f = sb.DataSink()
        a.connect(e,providesPortName="short_in_1")
        b.connect(e,providesPortName="short_in_2")
        c.connect(e,providesPortName="short_in_3")
        d.connect(e,providesPortName="short_in_4")
        e.connect(f)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        d.push(src_data4,EOS=True)
        result_data = f.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1= [-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        src_data3 = [-98,-102, -95,-90, -91,-3]
        src_data4 = [-97,-101, -93,-91, -95,-1]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3), max(src_data4))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        d = sb.DataSource(dataFormat="short")
        e = gr.max_(gr.sizeof_short, 4, len(src_data1))
        f = sb.DataSink()
        a.connect(e,providesPortName="short_in_1")
        b.connect(e,providesPortName="short_in_2")
        c.connect(e,providesPortName="short_in_3")
        d.connect(e,providesPortName="short_in_4")
        e.connect(f)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        d.push(src_data4,EOS=True)
        result_data = f.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ii_1i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data = (0,2,-3,0,12,0)
        expected_result = ((max(src_data)), )

        src = gr.vector_source_i(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_int, len(src_data))
        op = gr.max_(gr.sizeof_int, 1, len(src_data) )
        dst = gr.vector_sink_i()


        self.tb.connect(src, s2v, op, dst)
        self.tb.run()
        result_data = dst.data()
        self.assertEqual(expected_result, result_data)

    def test_002(self):

        src_data=(-100,-99,-98,-97,-96,-1)
        expected_result = ((max(src_data)), )

        src = gr.vector_source_i(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_int, len(src_data))
        op = gr.max_(gr.sizeof_int, 1, len(src_data) )
        dst = gr.vector_sink_i()

        self.tb.connect(src, s2v, op, dst)
        self.tb.run()
        result_data = dst.data()
        self.assertEqual(expected_result, result_data)

class test_max_ii_2i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,2,-3,0,12,0,2]
        src_data2 = [0,3,-4,1,13,1,2]
        max_src_data = ( max(src_data1), max(src_data2))
        expected_result = (max((max_src_data)))

        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = gr.max_(gr.sizeof_int, 2, len(src_data1))
        d = sb.DataSink()
        a.connect(c,providesPortName="long_in_1")
        b.connect(c,providesPortName="long_in_2")
        c.connect(d)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        result_data = d.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1=[-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        max_src_data = ( max(src_data1), max(src_data2))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = gr.max_(gr.sizeof_int, 2, len(src_data1))
        d = sb.DataSink()
        a.connect(c,providesPortName="long_in_1")
        b.connect(c,providesPortName="long_in_2")
        c.connect(d)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        result_data = d.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ii_3i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,2,-3,0,12,0,2]
        src_data2 = [0,3,-4,1,13,1,2]
        src_data3 = [0,4,-6,3,11,1,4]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        d = gr.max_(gr.sizeof_int, 3, len(src_data1))
        e = sb.DataSink()
        a.connect(d,providesPortName="long_in_1")
        b.connect(d,providesPortName="long_in_2")
        c.connect(d,providesPortName="long_in_3")
        d.connect(e)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        result_data = e.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1=[-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        src_data3 = [-98,-102, -95,-90, -91,-3]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3))
        expected_result = max((max_src_data))

        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        d = gr.max_(gr.sizeof_int, 3, len(src_data1))
        e = sb.DataSink()
        a.connect(d,providesPortName="long_in_1")
        b.connect(d,providesPortName="long_in_2")
        c.connect(d,providesPortName="long_in_3")
        d.connect(e)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        result_data = e.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )

class test_max_ii_4i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):

        src_data1 = [0,2,-3,0,12,0,2]
        src_data2 = [0,3,-4,1,13,1,2]
        src_data3 = [0,4,-6,3,11,1,4]
        src_data4 = [0,5,-4,5,16,2,1]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3), max(src_data4))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        d = sb.DataSource(dataFormat="long")
        e = gr.max_(gr.sizeof_int, 4, len(src_data1))
        f = sb.DataSink()
        a.connect(e,providesPortName="long_in_1")
        b.connect(e,providesPortName="long_in_2")
        c.connect(e,providesPortName="long_in_3")
        d.connect(e,providesPortName="long_in_4")
        e.connect(f)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        d.push(src_data4,EOS=True)
        result_data = f.getData(eos_block=True)
        
        self.assertEqual( expected_result, result_data[0] )

    def test_002(self):

        src_data1= [-100,-99,-98,-97,-96,-1]
        src_data2 = [-101,-98, -97,-95, -95,-1]
        src_data3 = [-98,-102, -95,-90, -91,-3]
        src_data4 = [-97,-101, -93,-91, -95,-1]
        max_src_data = ( max(src_data1), max(src_data2), max(src_data3), max(src_data4))
        expected_result = float(max((max_src_data)))

        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        d = sb.DataSource(dataFormat="long")
        e = gr.max_(gr.sizeof_int, 4, len(src_data1))
        f = sb.DataSink()
        a.connect(e,providesPortName="long_in_1")
        b.connect(e,providesPortName="long_in_2")
        c.connect(e,providesPortName="long_in_3")
        d.connect(e,providesPortName="long_in_4")
        e.connect(f)
        sb.start()
        a.push(src_data1,EOS=True)
        b.push(src_data2,EOS=True)
        c.push(src_data3,EOS=True)
        d.push(src_data4,EOS=True)
        result_data = f.getData(eos_block=True)
        
        self.assertEqual(expected_result, result_data[0] )



if __name__ == '__main__':
    gr_unittest.run(test_max, "test_max.xml")

