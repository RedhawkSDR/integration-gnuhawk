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


class test_arg_max_fs_1i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,0.2,-0.3,0,12,0]
        a = sb.DataSource(dataFormat="float")
        argmax = gr.argmax(gr.sizeof_float, len(src1_data),1 )
        a.connect(argmax,providesPortName="float_in")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))
        
class test_arg_max_fs_2i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,0.2,-0.3,0,12,0]
        src2_data = [0,0.0,3.0,0,10,0]
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        argmax = gr.argmax(gr.sizeof_float, len(src1_data), 2)
        a.connect(argmax,providesPortName="float_in_1")
        b.connect(argmax,providesPortName="float_in_2")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_fs_3i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,0.2,-0.3,0,12,0]
        src2_data = [0,0.0,3.0,0,10,0]
        src3_data = (0,0.0,3.0,0,1,0)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        argmax = gr.argmax(gr.sizeof_float, len(src1_data), 3)
        a.connect(argmax,providesPortName="float_in_1")
        b.connect(argmax,providesPortName="float_in_2")
        c.connect(argmax,providesPortName="float_in_3")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        c.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_fs_4i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,0.2,-0.3,0,12,0]
        src2_data = [0,0.0,3.0,0,10,0]
        src3_data = (0,0.0,3.0,0,1,0)
        src4_data = (0,1.0,8.0,6,3,4)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = sb.DataSource(dataFormat="float")
        argmax = gr.argmax(gr.sizeof_float, len(src1_data), 4)
        a.connect(argmax,providesPortName="float_in_1")
        b.connect(argmax,providesPortName="float_in_2")
        c.connect(argmax,providesPortName="float_in_3")
        d.connect(argmax,providesPortName="float_in_4")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        c.push(src2_data,EOS=True)
        d.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_is_1i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [1,2,-3,1,12,5]
        a = sb.DataSource(dataFormat="long")
        argmax = gr.argmax(gr.sizeof_int, len(src1_data), 1)
        a.connect(argmax,providesPortName="long_in")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0, ))

class test_arg_max_is_2i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        src2_data = [0,0,3,0,10,0]
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        argmax = gr.argmax(gr.sizeof_int, len(src1_data), 2)
        a.connect(argmax,providesPortName="long_in_1")
        b.connect(argmax,providesPortName="long_in_2")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_is_3i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        src2_data = [0,0,3,0,10,0]
        src3_data = (0,0,3,0,1,0)
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        argmax = gr.argmax(gr.sizeof_int, len(src1_data), 3)
        a.connect(argmax,providesPortName="long_in_1")
        b.connect(argmax,providesPortName="long_in_2")
        c.connect(argmax,providesPortName="long_in_3")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        c.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_is_4i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        src2_data = [0,0,3,0,10,0]
        src3_data = (0,0,3,0,1,0)
        src4_data = (0,1,8,6,3,4)
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        d = sb.DataSource(dataFormat="long")
        argmax = gr.argmax(gr.sizeof_int, len(src1_data), 4)
        a.connect(argmax,providesPortName="long_in_1")
        b.connect(argmax,providesPortName="long_in_2")
        c.connect(argmax,providesPortName="long_in_3")
        d.connect(argmax,providesPortName="long_in_4")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        c.push(src2_data,EOS=True)
        d.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_ss_1i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        a = sb.DataSource(dataFormat="short")
        argmax = gr.argmax(gr.sizeof_short, len(src1_data),1 )
        a.connect(argmax,providesPortName="short_in")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_ss_2i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        src2_data = [0,0,3,0,10,0]
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        argmax = gr.argmax(gr.sizeof_short, len(src1_data), 2)
        a.connect(argmax,providesPortName="short_in_1")
        b.connect(argmax,providesPortName="short_in_2")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_ss_3i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        src2_data = [0,0,3,0,10,0]
        src3_data = (0,0,3,0,1,0)
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        argmax = gr.argmax(gr.sizeof_short, len(src1_data), 3)
        a.connect(argmax,providesPortName="short_in_1")
        b.connect(argmax,providesPortName="short_in_2")
        c.connect(argmax,providesPortName="short_in_3")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        c.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))

class test_arg_max_ss_4i (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()


    def tearDown (self):
        self.tb = None


    def test_001(self):
        
        src1_data = [0,2,-3,0,12,0]
        src2_data = [0,0,3,0,10,0]
        src3_data = (0,0,3,0,1,0)
        src4_data = (0,1,8,6,3,4)
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        d = sb.DataSource(dataFormat="short")
        argmax = gr.argmax(gr.sizeof_short, len(src1_data), 4)
        a.connect(argmax,providesPortName="short_in_1")
        b.connect(argmax,providesPortName="short_in_2")
        c.connect(argmax,providesPortName="short_in_3")
        d.connect(argmax,providesPortName="short_in_4")
        dest1 = gr.vector_sink_s ()
        dest2 = gr.vector_sink_s ()
        argmax.connect(dest1,usesPortName="short_out_1")
        argmax.connect(dest2,usesPortName="short_out_2")
        sb.start()
        a.push(src1_data,EOS=True)
        b.push(src2_data,EOS=True)
        c.push(src2_data,EOS=True)
        d.push(src2_data,EOS=True)
        index = dest1.getData(eos_block=True)
        source = dest2.getData(eos_block=True)
        self.assertEqual ( tuple(index), (4,))
        self.assertEqual ( tuple(source), (0,))



if __name__ == '__main__':
    gr_unittest.run(test_arg_max, "test_arg_max.xml")

