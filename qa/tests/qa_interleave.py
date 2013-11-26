#!/usr/bin/env python
#
# Copyright 2004,2007,2010 Free Software Foundation, Inc.
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
import numpy
import time
from ossie.utils import sb
from gnuradio import gr, gr_unittest
import math

class test_interleave_ff_1 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 1)
        a = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_float, 1)
        dst = gr.vector_sink_f ()
        a.connect(op, providesPortName="float_in")
        a.push(src0,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)


    def test_deint_001 (self):
        lenx = 64
        src = gr.vector_source_f (range (lenx))
        op = gr.deinterleave (gr.sizeof_float, 1)
        dst0 = gr.vector_sink_f ()

        self.tb.connect (src, op)
        self.tb.connect ((op, 0), dst0)
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 1))
        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data ())

class test_interleave_ff_2 (gr_unittest.TestCase):
    
    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 2)
        src1 = range (1, lenx, 2)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_float, 2)
        dst = gr.vector_sink_f ()
        a.connect(op, providesPortName="float_in_0")
        b.connect(op, providesPortName="float_in_1")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()
        
        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_f (range (lenx))
        op = gr.deinterleave (gr.sizeof_float,2)
        dst0 = gr.vector_sink_f ()
        dst1 = gr.vector_sink_f ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="float_out_1")
        op.connect(dst1,usesPortName="float_out_2")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 2))
        expected_result1 = tuple (range (1, lenx, 2))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        

class test_interleave_ff_3 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 63
        src0 = range (0, lenx, 3)
        src1 = range (1, lenx, 3)
        src2 = range (2, lenx, 3)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_float, 3)
        dst = gr.vector_sink_f ()
        a.connect(op, providesPortName="float_in_0")
        b.connect(op, providesPortName="float_in_1")
        c.connect(op, providesPortName="float_in_2")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)

        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 63
        src0 = gr.vector_source_f (range (lenx))
        op = gr.deinterleave (gr.sizeof_float,3)
        dst0 = gr.vector_sink_f ()
        dst1 = gr.vector_sink_f ()
        dst2 = gr.vector_sink_f ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="float_out_1")
        op.connect(dst1,usesPortName="float_out_2")
        op.connect(dst2,usesPortName="float_out_3")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 3))
        expected_result1 = tuple (range (1, lenx, 3))
        expected_result2 = tuple (range (2, lenx, 3))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())


class test_interleave_ff_4 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 4)
        src1 = range (1, lenx, 4)
        src2 = range (2, lenx, 4)
        src3 = range (3, lenx, 4)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_float, 4)
        dst = gr.vector_sink_f ()
        a.connect(op, providesPortName="float_in_0")
        b.connect(op, providesPortName="float_in_1")
        c.connect(op, providesPortName="float_in_2")
        d.connect(op, providesPortName="float_in_3")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)
        d.push(src3,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_f (range (lenx))
        op = gr.deinterleave (gr.sizeof_float,4)
        dst0 = gr.vector_sink_f ()
        dst1 = gr.vector_sink_f ()
        dst2 = gr.vector_sink_f ()
        dst3 = gr.vector_sink_f ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="float_out_1")
        op.connect(dst1,usesPortName="float_out_2")
        op.connect(dst2,usesPortName="float_out_3")
        op.connect(dst3,usesPortName="float_out_4")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 4))
        expected_result1 = tuple (range (1, lenx, 4))
        expected_result2 = tuple (range (2, lenx, 4))
        expected_result3 = tuple (range (3, lenx, 4))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())
        self.assertFloatTuplesAlmostEqual (expected_result3, dst3.data ())

class test_interleave_ss_1 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 1)
        a = sb.DataSource(dataFormat="short")
        op = gr.interleave (gr.sizeof_short, 1)
        dst = gr.vector_sink_s ()
        a.connect(op, providesPortName="short_in")
        a.push(src0,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src = gr.vector_source_s (range (lenx))
        op = gr.deinterleave (gr.sizeof_short, 1)
        dst0 = gr.vector_sink_s ()

        self.tb.connect (src, op)
        self.tb.connect ((op, 0), dst0)
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 1))
        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data ())

class test_interleave_ss_2 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 2)
        src1 = range (1, lenx, 2)
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        op = gr.interleave (gr.sizeof_short, 2)
        dst = gr.vector_sink_s ()
        a.connect(op, providesPortName="short_in_0")
        b.connect(op, providesPortName="short_in_1")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()
        
        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

        
    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_s (range (lenx))
        op = gr.deinterleave (gr.sizeof_short,2)
        dst0 = gr.vector_sink_s ()
        dst1 = gr.vector_sink_s ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="short_out_0")
        op.connect(dst1,usesPortName="short_out_1")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 2))
        expected_result1 = tuple (range (1, lenx, 2))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())

class test_interleave_ss_3 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 63
        src0 = range (0, lenx, 3)
        src1 = range (1, lenx, 3)
        src2 = range (2, lenx, 3)
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        op = gr.interleave (gr.sizeof_short, 3)
        dst = gr.vector_sink_s ()
        a.connect(op, providesPortName="short_in_0")
        b.connect(op, providesPortName="short_in_1")
        c.connect(op, providesPortName="short_in_2")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)

        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 63
        src0 = gr.vector_source_s (range (lenx))
        op = gr.deinterleave (gr.sizeof_short,3)
        dst0 = gr.vector_sink_s ()
        dst1 = gr.vector_sink_s ()
        dst2 = gr.vector_sink_s ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="short_out_0")
        op.connect(dst1,usesPortName="short_out_1")
        op.connect(dst2,usesPortName="short_out_2")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 3))
        expected_result1 = tuple (range (1, lenx, 3))
        expected_result2 = tuple (range (2, lenx, 3))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())

class test_interleave_ss_4 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 4)
        src1 = range (1, lenx, 4)
        src2 = range (2, lenx, 4)
        src3 = range (3, lenx, 4)
        a = sb.DataSource(dataFormat="short")
        b = sb.DataSource(dataFormat="short")
        c = sb.DataSource(dataFormat="short")
        d = sb.DataSource(dataFormat="short")
        op = gr.interleave (gr.sizeof_short, 4)
        dst = gr.vector_sink_s ()
        a.connect(op, providesPortName="short_in_0")
        b.connect(op, providesPortName="short_in_1")
        c.connect(op, providesPortName="short_in_2")
        d.connect(op, providesPortName="short_in_3")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)
        d.push(src3,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_s (range (lenx))
        op = gr.deinterleave (gr.sizeof_short,4)
        dst0 = gr.vector_sink_s ()
        dst1 = gr.vector_sink_s ()
        dst2 = gr.vector_sink_s ()
        dst3 = gr.vector_sink_s ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="short_out_0")
        op.connect(dst1,usesPortName="short_out_1")
        op.connect(dst2,usesPortName="short_out_2")
        op.connect(dst3,usesPortName="short_out_3")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 4))
        expected_result1 = tuple (range (1, lenx, 4))
        expected_result2 = tuple (range (2, lenx, 4))
        expected_result3 = tuple (range (3, lenx, 4))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())
        self.assertFloatTuplesAlmostEqual (expected_result3, dst3.data ())

class test_interleave_bb_1 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 1)
        a = sb.DataSource(dataFormat="octet")
        op = gr.interleave (gr.sizeof_char, 1)
        dst = gr.vector_sink_b ()
        a.connect(op, providesPortName="byte_in")
        a.push(src0,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src = gr.vector_source_b (range (lenx))
        op = gr.deinterleave (gr.sizeof_char, 1)
        dst0 = gr.vector_sink_b ()

        self.tb.connect (src, op)
        self.tb.connect ((op, 0), dst0)
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 1))
        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data ())

class test_interleave_bb_2 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 2)
        src1 = range (1, lenx, 2)
        a = sb.DataSource(dataFormat="octet")
        b = sb.DataSource(dataFormat="octet")
        op = gr.interleave (gr.sizeof_char, 2)
        dst = gr.vector_sink_b ()
        a.connect(op, providesPortName="byte_in_0")
        b.connect(op, providesPortName="byte_in_1")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()
        
        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

        
    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_b (range (lenx))
        op = gr.deinterleave (gr.sizeof_char,2)
        dst0 = gr.vector_sink_b ()
        dst1 = gr.vector_sink_b ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="byte_out_0")
        op.connect(dst1,usesPortName="byte_out_1")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 2))
        expected_result1 = tuple (range (1, lenx, 2))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())

class test_interleave_bb_3 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 63
        src0 = range (0, lenx, 3)
        src1 = range (1, lenx, 3)
        src2 = range (2, lenx, 3)
        a = sb.DataSource(dataFormat="octet")
        b = sb.DataSource(dataFormat="octet")
        c = sb.DataSource(dataFormat="octet")
        op = gr.interleave (gr.sizeof_char, 3)
        dst = gr.vector_sink_b ()
        a.connect(op, providesPortName="byte_in_0")
        b.connect(op, providesPortName="byte_in_1")
        c.connect(op, providesPortName="byte_in_2")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)

        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 63
        src0 = gr.vector_source_b (range (lenx))
        op = gr.deinterleave (gr.sizeof_char,3)
        dst0 = gr.vector_sink_b ()
        dst1 = gr.vector_sink_b ()
        dst2 = gr.vector_sink_b ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="byte_out_0")
        op.connect(dst1,usesPortName="byte_out_1")
        op.connect(dst2,usesPortName="byte_out_2")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 3))
        expected_result1 = tuple (range (1, lenx, 3))
        expected_result2 = tuple (range (2, lenx, 3))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())

class test_interleave_bb_4 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 4)
        src1 = range (1, lenx, 4)
        src2 = range (2, lenx, 4)
        src3 = range (3, lenx, 4)
        a = sb.DataSource(dataFormat="octet")
        b = sb.DataSource(dataFormat="octet")
        c = sb.DataSource(dataFormat="octet")
        d = sb.DataSource(dataFormat="octet")
        op = gr.interleave (gr.sizeof_char, 4)
        dst = gr.vector_sink_b ()
        a.connect(op, providesPortName="byte_in_0")
        b.connect(op, providesPortName="byte_in_1")
        c.connect(op, providesPortName="byte_in_2")
        d.connect(op, providesPortName="byte_in_3")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)
        d.push(src3,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_b (range (lenx))
        op = gr.deinterleave (gr.sizeof_char,4)
        dst0 = gr.vector_sink_b ()
        dst1 = gr.vector_sink_b ()
        dst2 = gr.vector_sink_b ()
        dst3 = gr.vector_sink_b ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="byte_out_0")
        op.connect(dst1,usesPortName="byte_out_1")
        op.connect(dst2,usesPortName="byte_out_2")
        op.connect(dst3,usesPortName="byte_out_3")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 4))
        expected_result1 = tuple (range (1, lenx, 4))
        expected_result2 = tuple (range (2, lenx, 4))
        expected_result3 = tuple (range (3, lenx, 4))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())
        self.assertFloatTuplesAlmostEqual (expected_result3, dst3.data ())

class test_interleave_ii_1 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 1)
        a = sb.DataSource(dataFormat="long")
        op = gr.interleave (gr.sizeof_int, 1)
        dst = gr.vector_sink_i ()
        a.connect(op, providesPortName="long_in")
        a.push(src0,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)


    def test_deint_001 (self):
        lenx = 64
        src = gr.vector_source_i (range (lenx))
        op = gr.deinterleave (gr.sizeof_int, 1)
        dst0 = gr.vector_sink_i ()

        self.tb.connect (src, op)
        self.tb.connect ((op, 0), dst0)
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 1))
        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data ())

class test_interleave_ii_2 (gr_unittest.TestCase):
    
    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 2)
        src1 = range (1, lenx, 2)
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        op = gr.interleave (gr.sizeof_int, 2)
        dst = gr.vector_sink_f ()
        a.connect(op, providesPortName="long_in_0")
        b.connect(op, providesPortName="long_in_1")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()
        
        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

        
    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_i (range (lenx))
        op = gr.deinterleave (gr.sizeof_int,2)
        dst0 = gr.vector_sink_i ()
        dst1 = gr.vector_sink_i ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="long_out_0")
        op.connect(dst1,usesPortName="long_out_1")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 2))
        expected_result1 = tuple (range (1, lenx, 2))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())

class test_interleave_ii_3 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 63
        src0 = range (0, lenx, 3)
        src1 = range (1, lenx, 3)
        src2 = range (2, lenx, 3)
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        op = gr.interleave (gr.sizeof_int, 3)
        dst = gr.vector_sink_i ()
        a.connect(op, providesPortName="long_in_0")
        b.connect(op, providesPortName="long_in_1")
        c.connect(op, providesPortName="long_in_2")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)

        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 63
        src0 = gr.vector_source_i (range (lenx))
        op = gr.deinterleave (gr.sizeof_int,3)
        dst0 = gr.vector_sink_i ()
        dst1 = gr.vector_sink_i ()
        dst2 = gr.vector_sink_i ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="long_out_0")
        op.connect(dst1,usesPortName="long_out_1")
        op.connect(dst2,usesPortName="long_out_2")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 3))
        expected_result1 = tuple (range (1, lenx, 3))
        expected_result2 = tuple (range (2, lenx, 3))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())


class test_interleave_ii_4 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = range (0, lenx, 4)
        src1 = range (1, lenx, 4)
        src2 = range (2, lenx, 4)
        src3 = range (3, lenx, 4)
        a = sb.DataSource(dataFormat="long")
        b = sb.DataSource(dataFormat="long")
        c = sb.DataSource(dataFormat="long")
        d = sb.DataSource(dataFormat="long")
        op = gr.interleave (gr.sizeof_int, 4)
        dst = gr.vector_sink_i ()
        a.connect(op, providesPortName="long_in_0")
        b.connect(op, providesPortName="long_in_1")
        c.connect(op, providesPortName="long_in_2")
        d.connect(op, providesPortName="long_in_3")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        c.push(src2,EOS=True)
        d.push(src3,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_i (range (lenx))
        op = gr.deinterleave (gr.sizeof_int,4)
        dst0 = gr.vector_sink_i ()
        dst1 = gr.vector_sink_i ()
        dst2 = gr.vector_sink_i ()
        dst3 = gr.vector_sink_i ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="long_out_0")
        op.connect(dst1,usesPortName="long_out_1")
        op.connect(dst2,usesPortName="long_out_2")
        op.connect(dst3,usesPortName="long_out_3")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 4))
        expected_result1 = tuple (range (1, lenx, 4))
        expected_result2 = tuple (range (2, lenx, 4))
        expected_result3 = tuple (range (3, lenx, 4))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())
        self.assertFloatTuplesAlmostEqual (expected_result3, dst3.data ())

class test_interleave_cc_1 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = [0,0]
        i = 0
        while i < lenx-1:
            i+=1
            src0.append(i)
            src0.append(0)
        a = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_gr_complex, 1)
        dst = gr.vector_sink_c ()
        a.connect(op, providesPortName="complex_in")
        a.push(src0,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()
        self.assertFloatTuplesAlmostEqual (expected_result, result_data)



    def test_deint_001 (self):
        lenx = 64
        src = gr.vector_source_c (range (lenx))
        op = gr.deinterleave (gr.sizeof_gr_complex, 1)
        dst0 = gr.vector_sink_c ()

        self.tb.connect (src, op)
        self.tb.connect ((op, 0), dst0)
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 1))
        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data ())

class test_interleave_cc_2 (gr_unittest.TestCase):
    
    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 64
        src0 = [0,0]
        i = 0
        while i < lenx-3:
            i+=2
            src0.append(i)
            src0.append(0)
        src1 = [1,0]
        i=1
        while i < lenx-2:
            i+=2
            src1.append(i)
            src1.append(0)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_gr_complex, 2)
        dst = gr.vector_sink_c ()
        a.connect(op, providesPortName="complex_in_0")
        b.connect(op, providesPortName="complex_in_1")
        a.push(src0,EOS=True)
        b.push(src1,EOS=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()
        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

        
    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_c (range (lenx))
        op = gr.deinterleave (gr.sizeof_gr_complex,2)
        dst0 = gr.vector_sink_c ()
        dst1 = gr.vector_sink_c ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="complex_out_0")
        op.connect(dst1,usesPortName="complex_out_1")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 2))
        expected_result1 = tuple (range (1, lenx, 2))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())

class test_interleave_cc_3 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):
        lenx = 63
        src0 = [0,0]
        i = 0
        while i < lenx-5:
            i+=3
            src0.append(i)
            src0.append(0)
        src1 = [1,0]
        i=1
        while i < lenx-4:
            i+=3
            src1.append(i)
            src1.append(0)
        src2 = [2,0]
        i=2
        while i < lenx-3:
            i+=3
            src2.append(i)
            src2.append(0)
        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_gr_complex, 3)
        dst = gr.vector_sink_c ()
        a.connect(op, providesPortName="complex_in_0")
        b.connect(op, providesPortName="complex_in_1")
        c.connect(op, providesPortName="complex_in_2")
        a.push(src0,EOS=True,complexData=True)
        b.push(src1,EOS=True,complexData=True)
        c.push(src2,EOS=True,complexData=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 63
        src0 = gr.vector_source_c (range (lenx))
        op = gr.deinterleave (gr.sizeof_gr_complex,3)
        dst0 = gr.vector_sink_c ()
        dst1 = gr.vector_sink_c ()
        dst2 = gr.vector_sink_c ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="complex_out_0")
        op.connect(dst1,usesPortName="complex_out_1")
        op.connect(dst2,usesPortName="complex_out_2")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 3))
        expected_result1 = tuple (range (1, lenx, 3))
        expected_result2 = tuple (range (2, lenx, 3))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())


class test_interleave_cc_4 (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_int_001 (self):

        lenx = 64
        src0 = [0,0]
        i = 0
        while i < lenx-5:
            i+=4
            src0.append(i)
            src0.append(0)
        src1 = [1,0]
        i=1
        while i < lenx-4:
            i+=4
            src1.append(i)
            src1.append(0)
        src2 = [2,0]
        i=2
        while i < lenx-3:
            i+=4
            src2.append(i)
            src2.append(0)
        src3 = [3,0]
        i=3
        while i < lenx-2:
            i+=4
            src3.append(i)
            src3.append(0)

        a = sb.DataSource(dataFormat="float")
        b = sb.DataSource(dataFormat="float")
        c = sb.DataSource(dataFormat="float")
        d = sb.DataSource(dataFormat="float")
        op = gr.interleave (gr.sizeof_gr_complex, 4)
        dst = gr.vector_sink_c ()
        a.connect(op, providesPortName="complex_in_0")
        b.connect(op, providesPortName="complex_in_1")
        c.connect(op, providesPortName="complex_in_2")
        d.connect(op, providesPortName="complex_in_3")
        a.push(src0,EOS=True,complexData=True)
        b.push(src1,EOS=True,complexData=True)
        c.push(src2,EOS=True,complexData=True)
        d.push(src3,EOS=True,complexData=True)
        self.tb.connect (op, dst)
        self.tb.run ()
        expected_result = tuple (range (lenx))
        result_data = dst.data ()

        self.assertFloatTuplesAlmostEqual (expected_result, result_data)

    def test_deint_001 (self):
        lenx = 64
        src0 = gr.vector_source_c (range (lenx))
        op = gr.deinterleave (gr.sizeof_gr_complex,4)
        dst0 = gr.vector_sink_c ()
        dst1 = gr.vector_sink_c ()
        dst2 = gr.vector_sink_c ()
        dst3 = gr.vector_sink_c ()

        self.tb.connect (src0, op)
        op.connect(dst0,usesPortName="complex_out_0")
        op.connect(dst1,usesPortName="complex_out_1")
        op.connect(dst2,usesPortName="complex_out_2")
        op.connect(dst3,usesPortName="complex_out_3")
        self.tb.run ()

        expected_result0 = tuple (range (0, lenx, 4))
        expected_result1 = tuple (range (1, lenx, 4))
        expected_result2 = tuple (range (2, lenx, 4))
        expected_result3 = tuple (range (3, lenx, 4))

        self.assertFloatTuplesAlmostEqual (expected_result0, dst0.data())
        self.assertFloatTuplesAlmostEqual (expected_result1, dst1.data())
        self.assertFloatTuplesAlmostEqual (expected_result2, dst2.data ())
        self.assertFloatTuplesAlmostEqual (expected_result3, dst3.data ())

if __name__ == '__main__':
    gr_unittest.run(test_interleave, "test_interleave.xml")

