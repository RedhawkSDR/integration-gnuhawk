#!/usr/bin/env python
#
# Copyright 2004,2007,2008,2010 Free Software Foundation, Inc.
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
import struct

class test_boolean_operators (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def help_ss (self, src_data, exp_data, op, port_prefix='data_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_s (s[1])
            src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_s ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_const_ss (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_s (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_s ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_bb (self, src_data, exp_data, op, port_prefix='data_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_b (s[1])
            src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_b ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_const_bb (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_b (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_b ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_ii (self, src_data, exp_data, op, port_prefix='data_in_'):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_i (s[1])
            src.source.connect(op,providesPortName=port_prefix+str(s[0]))
            src.streamID = str(s[0])
            self.tb.sources.append(src)
        dst = gr.vector_sink_i ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def help_const_ii (self, src_data, exp_data, op):
        for s in zip (range (len (src_data)), src_data):
            src = gr.vector_source_i (s[1])
            self.tb.connect (src, (op, s[0]))
        dst = gr.vector_sink_i ()
        self.tb.connect (op, dst)
        self.tb.run ()
        result_data = dst.data ()
        self.assertEqual (exp_data, result_data)

    def test_xor_ss_2i (self):
        src1_data =       (1,  2,  3,  0x5004,   0x1150)
        src2_data =       (8,  2,  1 , 0x0508,   0x1105)
        expected_result = (9,  0,  2,  0x550C,   0x0055)
        op = gr.xor_ss (2)
        self.help_ss ((src1_data, src2_data),
                      expected_result, op, port_prefix='short_in_')

    def test_xor_ss_3i (self):
        src1_data =       (1,  2,  3,  4,    0x50)
        src2_data =       (8,  2,  1 , 8,    0x05)
        src3_data =       (2,  2,  15 , 0,   0x1100)
        expected_result = (11, 2,  13,  0xC, 0x1155)
        op = gr.xor_ss (3)
        self.help_ss ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='short_in_')

    def test_xor_ss_4i (self):
        src1_data =       (1,  2,  3,  4,      0x50)
        src2_data =       (8,  2,  1 , 8,      0x05)
        src3_data =       (2,  2,  15 , 0,   0x1100)
        src4_data =       (11, 2,  13,  0xC, 0x1155)
        expected_result = (0, 0, 0, 0, 0)
        op = gr.xor_ss (4)
        self.help_ss ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='short_in_')

    def test_xor_bb_2i (self):
        src1_data =       (1,  2,  3,  4,   0x50)
        src2_data =       (8,  2,  1 , 8,   0x05)
        expected_result = (9,  0,  2,  0xC, 0x55)
        op = gr.xor_bb (2)
        self.help_bb ((src1_data, src2_data),
                      expected_result, op, port_prefix='byte_in_')

    def test_xor_bb_3i (self):
        src1_data =       (1,  2,  3,  4,    0x50)
        src2_data =       (8,  2,  1 , 8,    0x05)
        src3_data =       (2,  2,  15 , 0,   0x00)
        expected_result = (11, 2,  13,  0xC, 0x55)
        op = gr.xor_bb (3)
        self.help_bb ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='byte_in_')

    def test_xor_bb_4i (self):
        src1_data =       (1,  2,  3,  4,    0x50)
        src2_data =       (8,  2,  1 , 8,    0x05)
        src3_data =       (2,  2,  15 , 0,   0x00)
        src4_data =       (11, 2,  13,  0xC, 0x55)
        expected_result = (0, 0, 0, 0, 0)
        op = gr.xor_bb (4)
        self.help_bb ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='byte_in_')

    def test_xor_ii_2i (self):
        src1_data =       (1,  2,  3,  0x5000004,   0x11000050)
        src2_data =       (8,  2,  1 , 0x0500008,   0x11000005)
        expected_result = (9,  0,  2,  0x550000C,   0x00000055)
        op = gr.xor_ii (2)
        self.help_ii ((src1_data, src2_data),
                      expected_result, op, port_prefix='long_in_')

    def test_xor_ii_3i (self):
        src1_data =       (1,  2,  3,  4,    0x50)
        src2_data =       (8,  2,  1 , 8,    0x05)
        src3_data =       (2,  2,  15 , 0,   0x1100)
        expected_result = (11, 2,  13,  0xC, 0x1155)
        op = gr.xor_ii (3)
        self.help_ii ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='long_in_')

    def test_xor_ii_4i (self):
        src1_data =       (1,  2,  3,  4,      0x50)
        src2_data =       (8,  2,  1 , 8,      0x05)
        src3_data =       (2,  2,  15 , 0,   0x841100)
        src4_data =       (11, 2,  13,  0xC, 0x841155)
        expected_result = (0, 0, 0, 0, 0)
        op = gr.xor_ii (4)
        self.help_ii ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='long_in_')

    def test_and_ss (self):
        src1_data =       (1,  2,  3,  0x5004,   0x1150)
        src2_data =       (8,  2,  1 , 0x0508,   0x1105)
        expected_result = (0,  2,  1,  0x0000,   0x1100)
        op = gr.and_ss ()
        self.help_ss ((src1_data, src2_data),
                      expected_result, op)

    def test_and_bb (self):
        src1_data =       (1,  2, 2,  3,  0x04,   0x50)
        src2_data =       (8,  2, 2,  1,  0x08,   0x05)
        expected_result = (0,  2, 2,  1,  0x00,   0x00)
        op = gr.and_bb ()
        self.help_bb ((src1_data, src2_data),
                      expected_result, op)

    def test_and_ii (self):
        src1_data =       (1,  2,  3,  0x50005004,   0x11001150)
        src2_data =       (8,  2,  1 , 0x05000508,   0x11001105)
        expected_result = (0,  2,  1,  0x00000000,   0x11001100)
        op = gr.and_ii ()
        self.help_ii ((src1_data, src2_data),
                      expected_result, op)

    def test_and_const_bb (self):
        src1_data =       (0xf1, 0x82, 0x03, 0x40, 0xff)
        expected_result = (1,    2,    3,    0,    0x0f)
        op = gr.and_const_bb(0x0f)
        self.help_const_bb ((src1_data,),
                      expected_result, op)

    def test_or_ss_2i (self):
        src1_data =       (1,  2,  3,  0x5004,   0x1150)
        src2_data =       (8,  2,  1 , 0x0508,   0x1105)
        expected_result = (9,  2,  3,  0x550C,   0x1155)
        op = gr.or_ss (2)
        self.help_ss ((src1_data, src2_data),
                      expected_result, op, port_prefix='short_in_')

    def test_or_ss_3i (self):
        src1_data =       (1,  2, 2,  3,  0x04,   0x50)
        src2_data =       (8,  2, 2,  1 , 0x08,   0x05)
        src3_data =       (8,  2, 1,  1 , 0x08,   0x05)
        expected_result = (9,  2, 3,  3,  0x0C,   0x55)
        op = gr.or_ss (3)
        self.help_ss ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='short_in_')

    def test_or_ss_4i (self):
        src1_data =       (1,  2, 2,  3 ,   0x04,     0x50)
        src2_data =       (8,  2, 2,  1 ,   0x08,     0x05)
        src3_data =       (8,  2, 1,  1 ,   0x08,     0x05)
        src4_data =       (8,  2, 1,  5 , 0x3508,   0x4105)
        expected_result = (9,  2, 3,  7,  0x350C,   0x4155)
        op = gr.or_ss (4)
        self.help_ss ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='short_in_')

    def test_or_bb_2i (self):
        src1_data =       (1,  2,  3,  0x04,   0x50)
        src2_data =       (8,  2,  1 , 0x08,   0x05)
        expected_result = (9,  2,  3,  0x0C,   0x55)
        op = gr.or_bb (2)
        self.help_bb ((src1_data, src2_data),
                      expected_result, op, port_prefix='byte_in_')

    def test_or_bb_3i (self):
        src1_data =       (1,  2, 2,  3,  0x04,   0x50)
        src2_data =       (8,  2, 2,  1 , 0x08,   0x05)
        src3_data =       (8,  2, 1,  1 , 0x08,   0x05)
        expected_result = (9,  2, 3,  3,  0x0C,   0x55)
        op = gr.or_bb (3)
        self.help_bb ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='byte_in_')

    def test_or_bb_4i (self):
        src1_data =       (1,  2, 2,  3 , 0x04,   0x50)
        src2_data =       (8,  2, 2,  1 , 0x08,   0x05)
        src3_data =       (8,  2, 1,  1 , 0x18,   0x05)
        src4_data =       (8,  2, 1,  5 , 0x58,   0x15)
        expected_result = (9,  2, 3,  7,  0x5C,   0x55)
        op = gr.or_bb (4)
        self.help_bb ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='byte_in_')

    def test_or_ii_2i (self):
        src1_data =       (1,  2,  3,  0x50005004,   0x11001150)
        src2_data =       (8,  2,  1 , 0x05000508,   0x11001105)
        expected_result = (9,  2,  3,  0x5500550C,   0x11001155)
        op = gr.or_ii (2)
        self.help_ii ((src1_data, src2_data),
                      expected_result, op, port_prefix='long_in_')

    def test_or_ii_3i (self):
        src1_data =       (1,  2, 2,  3,  0x04,   0x50)
        src2_data =       (8,  2, 2,  1 , 0x08,   0x05)
        src3_data =       (8,  2, 1,  1 , 0x08,   0x05)
        expected_result = (9,  2, 3,  3,  0x0C,   0x55)
        op = gr.or_ii (3)
        self.help_ii ((src1_data, src2_data, src3_data),
                      expected_result, op, port_prefix='long_in_')

    def test_or_ii_4i (self):
        src1_data =       (1,  2, 2,  3,        0x04,         0x50)
        src2_data =       (8,  2, 2,  1 ,       0x08,         0x05)
        src3_data =       (8,  2, 1,  1 ,       0x08,         0x05)
        src4_data =       (8,  2, 1,  5 , 0x05000508,   0x11001105)
        expected_result = (9,  2, 3,  7,  0x0500050C,   0x11001155)
        op = gr.or_ii (4)
        self.help_ii ((src1_data, src2_data, src3_data, src4_data),
                      expected_result, op, port_prefix='long_in_')

    def test_not_ss (self):
        src1_data =       (1,      2,      3,       0x5004,   0x1150)
        expected_result = (~1,     ~2,      ~3,       ~0x5004,   ~0x1150)
        op = gr.not_ss ()
        self.help_const_ss ((((src1_data),)),
                      expected_result, op)

    def test_not_bb (self):
        src1_data =       (1,     2,    2,     3,     0x04,   0x50)
        expected_result = (0xFE,  0xFD, 0xFD,  0xFC,  0xFB,   0xAF)
        op = gr.not_bb ()
        self.help_const_bb (((src1_data), ),
                      expected_result, op)

    def test_not_ii (self):
        src1_data =       (1,    2,  3,  0x50005004,   0x11001150)
        expected_result = (~1 , ~2, ~3, ~0x50005004,  ~0x11001150)
        op = gr.not_ii ()
        self.help_const_ii (((src1_data),),
                      expected_result, op)



if __name__ == '__main__':
    gr_unittest.run(test_boolean_operators, "test_boolean_operators.xml")
