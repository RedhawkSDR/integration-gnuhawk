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

from gnuradio import gr, gr_unittest
from ossie.utils import sb

class test_add_and_friends (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def help_ii (self, src_data, exp_data, op, port_prefix='data_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_i (s[1])
            if port_prefix == 'SINGLE_PORT':
                src.source.connect(op)
            else:
                src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_i ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_ii_const (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_i (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_i ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_ss (self, src_data, exp_data, op, port_prefix='data_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_s (s[1])
            if port_prefix == 'SINGLE_PORT':
                src.source.connect(op)
            else:
                src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_s ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_ss_const (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_s (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_s ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_sf (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_s (s[1])
            src.source.connect(op,providesPortName="data_in_"+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_f ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_sf_const (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_s (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_f ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_ff (self, src_data, exp_data, op, port_prefix='data_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_f (s[1])
            # For components with only one port, no need to specify provides port name
            if port_prefix == 'SINGLE_PORT':
                src.source.connect(op)
            else:
                src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_f ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_ff_const (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_f (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_f ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_cc (self, src_data, exp_data, op, port_prefix='data_complex_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_c (s[1])
            # For components with only one port, no need to specify provides port name
            if port_prefix == 'SINGLE_PORT':
                src.source.connect(op)
            else:
                src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_c ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_cc_const (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_c (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_c ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def test_add_const_ii (self):
        src_data = (1, 2, 3, 4, 5)
        expected_result = (6, 7, 8, 9, 10)
        op = gr.add_const_ii (5)
        self.help_ii_const ((src_data,), expected_result, op)

    def test_add_const_ss (self):
        src_data = (1, 2, 3, 4, 5)
        expected_result = (6, 7, 8, 9, 10)
        op = gr.add_const_ss (5)
        self.help_ss_const ((src_data,), expected_result, op)

    def test_add_const_sf (self):
        src_data = (1, 2, 3, 4, 5)
        expected_result = (6.5, 7.5, 8.5, 9.5, 10.5)
        op = gr.add_const_sf (5.5)
        self.help_sf_const ((src_data,), expected_result, op)

    def test_add_const_cc (self):
        src_data = (1, 2, 3, 4, 5)
        expected_result = (1+5j, 2+5j, 3+5j, 4+5j, 5+5j)
        op = gr.add_const_cc (5j)
        self.help_cc_const ((src_data,), expected_result, op)

    def test_mult_const_ss (self):
        src_data = (-1, 0, 1, 2, 3)
        expected_result = (-5, 0, 5, 10, 15)
        op = gr.multiply_const_ss (5)
        self.help_ss_const ((src_data,), expected_result, op)

    def test_mult_const_ii (self):
        src_data = (-1, 0, 1, 2, 3)
        expected_result = (-5, 0, 5, 10, 15)
        op = gr.multiply_const_ii (5)
        self.help_ii_const ((src_data,), expected_result, op)

    def test_mult_const_ff (self):
        src_data = (-1, 0, 1, 2, 3)
        expected_result = (-5, 0, 5, 10, 15)
        op = gr.multiply_const_cc (5)
        self.help_cc_const ((src_data,), expected_result, op)

    def test_mult_const_cc (self):
        src_data = (-1-1j, 0+0j, 1+1j, 2+2j, 3+3j)
        expected_result = (-5-5j, 0+0j, 5+5j, 10+10j, 15+15j)
        op = gr.multiply_const_cc (5)
        self.help_cc_const ((src_data,), expected_result, op)

    def test_mult_const_cc2 (self):
        src_data = (-1-1j, 0+0j, 1+1j, 2+2j, 3+3j)
        expected_result = (-3-7j, 0+0j, 3+7j, 6+14j, 9+21j)
        op = gr.multiply_const_cc (5+2j)
        self.help_cc_const ((src_data,), expected_result, op)

    def test_add_ii (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (9, -1, 7, 12, 7)
        op = gr.add_ii ()
        self.help_ii ((src1_data, src2_data),
                      expected_result, op)

    def test_add_ss (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (9, -1, 7, 12, 7)
        op = gr.add_ss ()
        self.help_ss ((src1_data, src2_data),
                      expected_result, op)

    def test_mult_ii (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (8, -6, 12, 32, 10)
        op = gr.multiply_ii ()
        self.help_ii ((src1_data, src2_data),
                      expected_result, op)

    def test_mult_ss (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (8, -6, 12, 32, 10)
        op = gr.multiply_ss ()
        self.help_ss ((src1_data, src2_data),
                      expected_result, op)

    def test_mult_ff (self):
        # Note: the GNUHawk multiply_ff only outputs data in multiples of 8,
        # so this test has been modified accordingly
        src1_data = (1,  2, 3, 4, 5, 6, 7, 8)
        src2_data = (8, -3, 4, 8, 2, -0.5, -1, 1.5)
        expected_result = (8, -6, 12, 32, 10, -3, -7, 12)
        op = gr.multiply_ff ()
        self.help_ff ((src1_data, src2_data),
                      expected_result, op)

    def test_mult_cc (self):
        # Note: the GNUHawk multiply_ff only outputs data in multiples of 8,
        # so this test has been modified accordingly
        src1_data = (1+1j,  2+2j, 3+3j, 4+4j, 5+5j,  6+6j, 7+7j, 8+8j)
        src2_data = ( complex(8),  complex(-3), complex(4), complex(8), complex(2), complex(1), complex(2), complex(5) )
        expected_result = (8+8j, -6-6j, 12+12j, 32+32j, 10+10j, 6+6j, 14+14j, 40+40j )
        op = gr.multiply_cc ()
        self.help_cc ((src1_data, src2_data),
                      expected_result, op)

    def test_sub_ii_1 (self):
        src1_data = (1,  2, 3, 4, 5)
        expected_result = (-1, -2, -3, -4, -5)
        op = gr.sub_ii (1)
        self.help_ii ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_sub_ii_2 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (-7, 5, -1, -4, 3)
        op = gr.sub_ii (2)
        self.help_ii ((src1_data, src2_data),
                      expected_result, op, port_prefix='long_in_')

    def test_sub_ii_3 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        src3_data = (-8, 3, 4, -8, -2)
        expected_result = (1, 2, -5, 4, 5)
        op = gr.sub_ii (3)
        self.help_ii ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='long_in_')

    def test_sub_ii_4 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        src3_data = (-8, 3, 4, -8, -2)
        src4_data = (-1, 2, 3, -4, -5)
        expected_result = (2, 0, -8, 8, 10)
        op = gr.sub_ii (4)
        self.help_ii ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='long_in_')

    def test_sub_ff_1 (self):
        src1_data = (-1.0,  2.25, -3.5, 4, -5)
        expected_result = (1, -2.25, 3.5, -4, 5)
        op = gr.sub_ff (1)
        self.help_ff ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_sub_ff_2 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (-7, 5, -1, -4, 3)
        op = gr.sub_ff (2)
        self.help_ff ((src1_data, src2_data),
                      expected_result, op, port_prefix='float_in_')

    def test_sub_ff_3 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        src3_data = (-8, 3, 4, -8, -2)
        expected_result = (1, 2, -5, 4, 5)
        op = gr.sub_ff (3)
        self.help_ff ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='float_in_')

    def test_sub_ff_4 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        src3_data = (-8, 3, 4, -8, -2)
        src4_data = (-1, 2, 3, -4, -5)
        expected_result = (2, 0, -8, 8, 10)
        op = gr.sub_ff (4)
        self.help_ff ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='float_in_')

    def test_sub_ss_1 (self):
        src1_data = (1,  -2, 3, -4, 5)
        expected_result = (-1, 2, -3, 4, -5)
        op = gr.sub_ss (1)
        self.help_ss ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_sub_ss_2 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        expected_result = (-7, 5, -1, -4, 3)
        op = gr.sub_ss (2)
        self.help_ss ((src1_data, src2_data),
                      expected_result, op, port_prefix='short_in_')

    def test_sub_ss_3 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        src3_data = (-8, 3, 4, -8, -2)
        expected_result = (1, 2, -5, 4, 5)
        op = gr.sub_ss (3)
        self.help_ss ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='short_in_')

    def test_sub_ss_4 (self):
        src1_data = (1,  2, 3, 4, 5)
        src2_data = (8, -3, 4, 8, 2)
        src3_data = (-8, 3, 4, -8, -2)
        src4_data = (-1, 2, 3, -4, -5)
        expected_result = (2, 0, -8, 8, 10)
        op = gr.sub_ss (4)
        self.help_ss ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='short_in_')

    def test_sub_cc_1 (self):
        src1_data       = (2-2j,  1-1j,    -8+0j, -3-7j, -4+2j, 5+5j)
        expected_result = (-2+2j, -1+1j, 8+0j, 3+7j, 4-2j, -5-5j)
        op = gr.sub_cc (1)
        self.help_cc ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_sub_cc_2 (self):
        src1_data       = (2-2j,  1-1j, -8+0j,    3-7j,  4+2j, 5+5j)
        src2_data       = (1-1j,  3+2j,  4-2j,    1-8j,  6+1j, 5+5j)
        expected_result = (1-1j, -2+-3j, -12+2j,  2+1j,  -2+1j, 0+0j)
        op = gr.sub_cc (2)
        self.help_cc ((src1_data, src2_data),
                      expected_result, op, port_prefix='complex_in_')

    def test_sub_cc_3 (self):
        src1_data       = (2-2j,  1-1j, -8+0j,    3-7j,  4+2j, 5+5j)
        src2_data       = (1-1j,  3+2j,  4-2j,    1-8j,  6+1j, 5+5j)
        src3_data       = (-1+1j,-3-2j, -4+2j,   -1+8j, -6-1j, -5-5j)
        expected_result = (2-2j,  1-1j, -8+0j,    3-7j,  4+2j, 5+5j)
        op = gr.sub_cc (3)
        self.help_cc ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='complex_in_')

    def test_sub_cc_4 (self):
        src1_data       = (2-2j,  1-1j, -8+0j,    3-7j,  4+2j, 5+5j)
        src2_data       = (1-1j,  3+2j,  4-2j,    1-8j,  6+1j, 5+5j)
        src3_data       = (-1+1j,-3-2j, -4+2j,   -1+8j, -6-1j, -5-5j)
        src4_data       = (1-1j,  3+2j,  4-2j,    1-8j,  6+1j, 5+5j)
        expected_result = (1-1j, -2+-3j, -12+2j,  2+1j,  -2+1j, 0+0j)
        op = gr.sub_cc (4)
        self.help_cc ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='complex_in_')

    def test_div_ff_1 (self):
        src1_data       = (1,  2,  4,    -8)
        expected_result = (1, 0.5, 0.25, -.125)
        op = gr.divide_ff (1)
        self.help_ff ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_div_ff_2 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        expected_result = (0.5, 3,   3,   16)
        op = gr.divide_ff (2)
        self.help_ff ((src1_data, src2_data),
                      expected_result, op, port_prefix='float_in_')

    def test_div_ff_3 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        src3_data       = (2,  3,  -.5,   -4)
        expected_result = (0.25, 1,   -6,   -4)
        op = gr.divide_ff (3)
        self.help_ff ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='float_in_')

    def test_div_ff_4 (self):
        src1_data       = ( 5,   9,  -15, 1024)
        src2_data       = (10,   3,   -5,   64)
        src3_data       = (2,    3,   -.5,  -4)
        src4_data       = (-0.5, -1   ,-3,   2)
        expected_result = (-0.5, -1,   2,  -2)
        op = gr.divide_ff (4)
        self.help_ff ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='float_in_')

    def test_div_ii_1 (self):
        src1_data       = (1,  2,  4,    -8, -1)
        expected_result = (1,  0,  0,    0,   -1)
        op = gr.divide_ii (1)
        self.help_ii ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_div_ii_2 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        expected_result = (0,   3,   3,   16)
        op = gr.divide_ii (2)
        self.help_ii ((src1_data, src2_data),
                      expected_result, op, port_prefix='long_in_')

    def test_div_ii_3 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        src3_data       = (1,   2,  -1,   -4)
        expected_result = (0,   1,  -3,   -4)
        op = gr.divide_ii (3)
        self.help_ii ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='long_in_')

    def test_div_ii_4 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        src3_data       = (1,   2,  -1,   -4)
        src4_data       = (1,   -2,  3,   -2)
        expected_result = (0,   0,  -1,   2)
        op = gr.divide_ii (4)
        self.help_ii ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='long_in_')

    def test_div_ss_1 (self):
        src1_data       = (1,  2,  4,    -8, -1)
        expected_result = (1, 0, 0, 0, -1)
        op = gr.divide_ss (1)
        self.help_ss ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_div_ss_2 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        expected_result = (0, 3,   3,   16)
        op = gr.divide_ss (2)
        self.help_ss ((src1_data, src2_data),
                      expected_result, op, port_prefix='short_in_')

    def test_div_ss_3 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        src3_data       = (1,   2,  -1,   -4)
        expected_result = (0,   1,  -3,   -4)
        op = gr.divide_ss (3)
        self.help_ss ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='short_in_')

    def test_div_ss_4 (self):
        src1_data       = ( 5,  9, -15, 1024)
        src2_data       = (10,  3,  -5,   64)
        src3_data       = (1,   2,  -1,   -4)
        src4_data       = (1,   -2,  3,   -2)
        expected_result = (0,   0,  -1,   2)
        op = gr.divide_ss (4)
        self.help_ss ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='short_in_')

    def test_div_cc_1 (self):
        src1_data       = (2-2j,  1-1j,    -8+0j)
        expected_result = (0.25+.25j, .5+.5j, -.125+0j)
        op = gr.divide_cc (1)
        self.help_cc ((src1_data,),
                      expected_result, op, port_prefix='SINGLE_PORT')

    def test_div_cc_2 (self):
        src1_data = (5-10j,  9+9j, -15-5j, 1024+1024j)
        src2_data = (10+10j, 3+3j, -5-5j, 64+64j)
        expected_result = (-.25-.75j, 3+0j,   2-1j,  16+0j)
        op = gr.divide_cc (2)
        self.help_cc ((src1_data, src2_data),
                      expected_result, op, port_prefix='complex_in_')

    def test_div_cc_3 (self):
        src1_data = (5-10j,  9+9j, -15-5j, 1024+1024j)
        src2_data = (10+10j, 3+3j, -5-5j, 64+64j)
        src3_data = (-.25-.75j, 3+0j,   2-1j,  16+0j)
        expected_result = (1+0j, 1+0j, 1+0j,  1+0j)
        op = gr.divide_cc (3)
        self.help_cc ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='complex_in_')

    def test_div_cc_4 (self):
        src1_data = (5-10j,  9+9j, -15-5j, 1024+1024j)
        src2_data = (10+10j, 3+3j, -5-5j, 64+64j)
        src3_data = (-.25-.75j, 3+0j,   2-1j,  16+0j)
        src4_data = (1+0j, 1+0j, 1-0j, 1+0j)
        expected_result = (1+0j, 1+0j, 1+0j,  1+0j)
        op = gr.divide_cc (4)
        self.help_cc ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='complex_in_')

if __name__ == '__main__':
    gr_unittest.run(test_add_and_friends, "test_add_and_friends.xml")
