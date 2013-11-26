#!/usr/bin/env python
#
# Copyright 2011 Free Software Foundation, Inc.
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

class test_sample_and_hold (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_sample_and_hold_ff(self):
        src_data1 = tuple([i for i in range(10)])
        src_data2 =       (0, 0, 1, 1, 0, 1, 0, 0, 1, 1)
        expected_result = (0, 0, 2, 3, 3, 5, 5, 5, 8, 9)

        src1 = gr.vector_source_f(src_data1)
        src2 = gr.vector_source_b(src_data2)
        op = gr.sample_and_hold_ff()
        dst = gr.vector_sink_f()

        self.tb.connect(src1, op)
        self.tb.connect(src2, op)
        self.tb.connect(op, dst)
        self.tb.run()
        result_data = dst.data()

        self.assertFloatTuplesAlmostEqual(expected_result, result_data)

    def test_sample_and_hold_ii(self):
        src_data1 = tuple([i for i in range(10)])
        src_data2 =       (0, 0, 1, 1, 0, 1, 0, 0, 1, 1)
        expected_result = (0, 0, 2, 3, 3, 5, 5, 5, 8, 9)

        src1 = gr.vector_source_i(src_data1)
        src2 = gr.vector_source_b(src_data2)
        op = gr.sample_and_hold_ii()
        dst = gr.vector_sink_i()

        self.tb.connect(src1, op)
        self.tb.connect(src2, op)
        self.tb.connect(op, dst)
        self.tb.run()
        result_data = dst.data()

        self.assertEqual(expected_result, result_data)

    def test_sample_and_hold_ss(self):
        src_data1 = tuple([i for i in range(10)])
        src_data2 =       (0, 0, 1, 1, 0, 1, 0, 0, 1, 1)
        expected_result = (0, 0, 2, 3, 3, 5, 5, 5, 8, 9)

        src1 = gr.vector_source_s(src_data1)
        src2 = gr.vector_source_b(src_data2)
        op = gr.sample_and_hold_ss()
        dst = gr.vector_sink_s()

        self.tb.connect(src1, op)
        self.tb.connect(src2, op)
        self.tb.connect(op, dst)
        self.tb.run()
        result_data = dst.data()

        self.assertEqual(expected_result, result_data)

    def test_sample_and_hold_bb(self):
        src_data1 = tuple([i for i in range(10)])
        src_data2 =       (0, 0, 1, 1, 0, 1, 0, 0, 1, 1)
        expected_result = (0, 0, 2, 3, 3, 5, 5, 5, 8, 9)

        src1 = gr.vector_source_b(src_data1)
        src2 = gr.vector_source_b(src_data2)
        op = gr.sample_and_hold_bb()
        dst = gr.vector_sink_b()

        src1.connect((op, "data"), providesPortName='byte_in')
        src2.connect((op, "control"), providesPortName='ctrl_in')
        # Make sure sources get add to TB
        self.tb.sources.append(src1)
        self.tb.sources.append(src2)
        self.tb.connect(op, dst)
        self.tb.run()
        result_data = dst.data()

        self.assertEqual(expected_result, result_data)

if __name__ == '__main__':
    gr_unittest.run(test_sample_and_hold, "test_sample_and_hold.xml")

