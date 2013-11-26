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
# You should have received a copy of the GNU General Public License along
# with this program; if not, write to the Free Software Foundation, Inc.,
# 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
#

from gnuradio import gr, gr_unittest
import sys
import random



class test_keep_m_in_n_octet(gr_unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_001(self):
        self.maxDiff = None;
        tb = gr.top_block()
        src1 = gr.vector_source_b( range(0,100) )
        src2 = gr.vector_source_b( range(0,100) )
        src3 = gr.vector_source_b( range(0,100) )

        # itemsize, M, N, offset
        km2 = gr.keep_m_in_n( gr.sizeof_char, 1, 1, 2, 0 );
        km3 = gr.keep_m_in_n( gr.sizeof_char, 1, 1, 3, 1 );
        km7 = gr.keep_m_in_n( gr.sizeof_char, 1, 1, 7, 2 );
        snk2 = gr.vector_sink_b();
        snk3 = gr.vector_sink_b();
        snk7 = gr.vector_sink_b();
        tb.connect(src1,km2,snk2);
        tb.connect(src2,km3,snk3);
        tb.connect(src3,km7,snk7);
        tb.run();

        self.assertEqual(range(0,100,2), list(snk2.data()));
        self.assertEqual(range(1,100,3), list(snk3.data()));
        self.assertEqual(range(2,100,7), list(snk7.data()));

class test_keep_m_in_n_short(gr_unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_001(self):
        self.maxDiff = None;
        tb = gr.top_block()
        src1 = gr.vector_source_s( range(0,100) )
        src2 = gr.vector_source_s( range(0,100) )
        src3 = gr.vector_source_s( range(0,100) )

        # itemsize, M, N, offset
        km2 = gr.keep_m_in_n( gr.sizeof_short, 2, 1, 2, 0 );
        km3 = gr.keep_m_in_n( gr.sizeof_short, 2, 1, 3, 1 );
        km7 = gr.keep_m_in_n( gr.sizeof_short, 2, 1, 7, 2 );
        snk2 = gr.vector_sink_s();
        snk3 = gr.vector_sink_s();
        snk7 = gr.vector_sink_s();
        tb.connect(src1,km2,snk2);
        tb.connect(src2,km3,snk3);
        tb.connect(src3,km7,snk7);
        tb.run();

        self.assertEqual(range(0,100,2), list(snk2.data()));
        self.assertEqual(range(1,100,3), list(snk3.data()));
        self.assertEqual(range(2,100,7), list(snk7.data()));

class test_keep_m_in_n_int(gr_unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_001(self):
        self.maxDiff = None;
        tb = gr.top_block()
        src1 = gr.vector_source_i( range(0,100) )
        src2 = gr.vector_source_i( range(0,100) )
        src3 = gr.vector_source_i( range(0,100) )

        # itemsize, M, N, offset
        km2 = gr.keep_m_in_n( gr.sizeof_int, 4, 1, 2, 0 );
        km3 = gr.keep_m_in_n( gr.sizeof_int, 4, 1, 3, 1 );
        km7 = gr.keep_m_in_n( gr.sizeof_int, 4, 1, 7, 2 );
        snk2 = gr.vector_sink_i();
        snk3 = gr.vector_sink_i();
        snk7 = gr.vector_sink_i();
        tb.connect(src1,km2,snk2);
        tb.connect(src2,km3,snk3);
        tb.connect(src3,km7,snk7);
        tb.run();

        self.assertEqual(range(0,100,2), list(snk2.data()));
        self.assertEqual(range(1,100,3), list(snk3.data()));
        self.assertEqual(range(2,100,7), list(snk7.data()));

class test_keep_m_in_n_complex(gr_unittest.TestCase):

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_001(self):
        self.maxDiff = None;
        tb = gr.top_block()
        src1 = gr.vector_source_c( range(0,100,1) )
        src2 = gr.vector_source_c( range(0,100) )
        src3 = gr.vector_source_c( range(0,100) )

        # itemsize, M, N, offset
        km2 = gr.keep_m_in_n( gr.sizeof_gr_complex, 8, 1, 2, 0 );
        km3 = gr.keep_m_in_n( gr.sizeof_gr_complex, 8, 1, 3, 1 );
        km7 = gr.keep_m_in_n( gr.sizeof_gr_complex, 8, 1, 7, 2 );
        snk2 = gr.vector_sink_c();
        snk3 = gr.vector_sink_c();
        snk7 = gr.vector_sink_c();
        tb.connect(src1,km2,snk2);
        tb.connect(src2,km3,snk3);
        tb.connect(src3,km7,snk7);
        tb.run();

        self.assertEqual(range(0,100,2), list(snk2.data()));
        self.assertEqual(range(1,100,3), list(snk3.data()));
        self.assertEqual(range(2,100,7), list(snk7.data()));


if __name__ == '__main__':
    gr_unittest.run(test_keep_m_in_n, "test_keep_m_in_n.xml")

