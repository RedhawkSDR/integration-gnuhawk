#!/usr/bin/env python
#
# Copyright 2008,2010 Free Software Foundation, Inc.
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
import math

class test_repeat (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_001_complex(self):
        src_data = [n*1.0 for n in range(100)];
        dst_data = []
        for n in range(100):
            dst_data += [1.0*n, 1.0*n, 1.0*n]

        src = gr.vector_source_c(src_data)
        rpt = gr.repeat(gr.sizeof_gr_complex, 3)
        dst = gr.vector_sink_c()
        self.tb.connect(src, rpt, dst)
        self.tb.run()
        self.assertFloatTuplesAlmostEqual(dst_data, dst.data(), 6)

    def test_001_int(self):
        src_data = [n*1 for n in range(100)];
        dst_data = []
        for n in range(100):
            dst_data += [n, n, n]

        src = gr.vector_source_i(src_data)
        rpt = gr.repeat(gr.sizeof_int, 3)
        dst = gr.vector_sink_i()
        self.tb.connect(src, rpt, dst)
        self.tb.run()
        self.assertEqual(tuple(dst_data), dst.data())
        
    def test_001_short(self):
        src_data = [n*1 for n in range(100)];
        dst_data = []
        for n in range(100):
            dst_data += [n, n, n]

        src = gr.vector_source_s(src_data)
        rpt = gr.repeat(gr.sizeof_short, 3)
        dst = gr.vector_sink_s()
        self.tb.connect(src, rpt, dst)
        self.tb.run()
        self.assertEqual(tuple(dst_data), dst.data())

    def test_001_octet(self):
        src_data = [n*1 for n in range(100)];
        dst_data = []
        for n in range(100):
            dst_data += [n, n, n]

        src = gr.vector_source_b(src_data)
        rpt = gr.repeat(gr.sizeof_char, 3)
        dst = gr.vector_sink_b()
        self.tb.connect(src, rpt, dst)
        self.tb.run()
        self.assertEqual(tuple(dst_data), dst.data())



if __name__ == '__main__':
    gr_unittest.run(test_repeat, "test_repeat.xml")
