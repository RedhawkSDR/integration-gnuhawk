#!/usr/bin/env python
#
# Copyright 2012 Free Software Foundation, Inc.
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

import time

from gnuradio import gr, gr_unittest

class test_probe_signal (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block()

    def tearDown (self):
        self.tb = None

    def test_001_f(self):

        value = 12.3
        repeats = 100
        src_data = [value] * repeats

        src = gr.vector_source_f(src_data)
        dst = gr.probe_signal_f()

        self.tb.connect(src, dst)
        self.tb.run()
        output = dst.level
        self.assertAlmostEqual(value, output, places=6)

    def test_002_vf(self):

        vector_length = 10
        repeats = 10
        value = [0.5+i for i in range(0, vector_length)]
        src_data = value * repeats

        src = gr.vector_source_f(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, vector_length)
        dst = gr.probe_signal_vf(vector_length)

        self.tb.connect(src, s2v, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(len(output), vector_length)
        self.assertAlmostEqual(value[3], output[3], places=6)

    def test_003_s(self):

        value = 12
        repeats = 100
        src_data = [value] * repeats

        src = gr.vector_source_s(src_data)
        dst = gr.probe_signal_s()

        self.tb.connect(src, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(value, output)

    def test_004_vs(self):

        vector_length = 10
        repeats = 10
        value = [5+i for i in range(0, vector_length)]
        src_data = value * repeats

        src = gr.vector_source_s(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, vector_length)
        dst = gr.probe_signal_vs(vector_length)

        self.tb.connect(src, s2v, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(len(output), vector_length)
        self.assertAlmostEqual(value[3], output[3], places=6)

    def test_005_i(self):

        value = 12
        repeats = 100
        src_data = [value] * repeats

        src = gr.vector_source_i(src_data)
        dst = gr.probe_signal_i()

        self.tb.connect(src, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(value, output)

    def test_006_vi(self):

        vector_length = 10
        repeats = 10
        value = [5+i for i in range(0, vector_length)]
        src_data = value * repeats

        src = gr.vector_source_i(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, vector_length)
        dst = gr.probe_signal_vi(vector_length)

        self.tb.connect(src, s2v, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(len(output), vector_length)
        self.assertAlmostEqual(value[3], output[3], places=6)

    def test_007_b(self):

        value = 12
        repeats = 100
        src_data = [value] * repeats

        src = gr.vector_source_b(src_data)
        dst = gr.probe_signal_b()

        self.tb.connect(src, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(value, output)

    def test_008_vb(self):

        vector_length = 10
        repeats = 10
        value = [5+i for i in range(0, vector_length)]
        src_data = value * repeats

        src = gr.vector_source_b(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, vector_length)
        dst = gr.probe_signal_vb(vector_length)

        self.tb.connect(src, s2v, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(len(output), vector_length)
        self.assertAlmostEqual(value[3], output[3], places=6)

    def test_009_c(self):

        value = complex(1,2)
        repeats = 100
        src_data = [value] * repeats

        src = gr.vector_source_c(src_data)
        dst = gr.probe_signal_c()

        self.tb.connect(src, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(value, output)

    def test_010_vc(self):

        vector_length = 10
        repeats = 10
        value = [complex(5,2)+i for i in range(0, vector_length)]
        src_data = value * repeats

        src = gr.vector_source_c(src_data)
        s2v = gr.stream_to_vector(gr.sizeof_float, vector_length)
        dst = gr.probe_signal_vc(vector_length)

        self.tb.connect(src, s2v, dst)
        self.tb.run()
        output = dst.level
        self.assertEqual(len(output), vector_length)
        self.assertAlmostEqual(value[3], output[3], places=6)

if __name__ == '__main__':
    gr_unittest.run(test_probe_signal, "test_probe_signal.xml")
