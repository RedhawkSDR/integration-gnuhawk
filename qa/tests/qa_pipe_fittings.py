#!/usr/bin/env python
#
# Copyright 2005,2007,2010 Free Software Foundation, Inc.
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

if 0:
    import os
    print "pid =", os.getpid()
    raw_input("Attach, then press Enter to continue")


def calc_expected_result(src_data, n):
    assert (len(src_data) % n) == 0
    result = [list() for x in range(n)]
    #print "len(result) =", len(result)
    for i in xrange(len(src_data)):
        (result[i % n]).append(src_data[i])
    return [tuple(x) for x in result]


class test_pipe_fittings(gr_unittest.TestCase):
    
    def setUp(self):
        self.tb = gr.top_block ()

    def tearDown(self):
        self.tb = None
    
    def test_001_b(self):
        
        #Test stream_to_streams.
        
        n = 2
        src_len = n * 8
        src_data = range(src_len)

        expected_results = calc_expected_result(src_data, n)
        src = gr.vector_source_b(src_data)
        op = gr.stream_to_streams_comp(gr.sizeof_char, n)
        self.tb.connect(src, op)

        dsts = []
        for i in range(n):
            dst = gr.vector_sink_b()
            self.tb.connect((op, i), (dst, 0))
            dsts.append(dst)

        self.tb.run()

        for d in range(n):
            self.assertEqual(expected_results[d], dsts[d].data())
    
    def test_001_c(self):
        
        #Test stream_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = range(src_len)

        expected_results = calc_expected_result(src_data, n)
        src = gr.vector_source_c(src_data)
        op = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        self.tb.connect(src, op)

        dsts = []
        for i in range(n):
            dst = gr.vector_sink_c()
            self.tb.connect((op, i), (dst, 0))
            dsts.append(dst)

        self.tb.run()

        for d in range(n):
            self.assertEqual(expected_results[d], dsts[d].data())

    def test_001_f(self):
        
        #Test stream_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = range(src_len)

        expected_results = calc_expected_result(src_data, n)
        src = gr.vector_source_f(src_data)
        op = gr.stream_to_streams_comp(gr.sizeof_float, n)
        self.tb.connect(src, op)

        dsts = []
        for i in range(n):
            dst = gr.vector_sink_f()
            self.tb.connect((op, i), (dst, 0))
            dsts.append(dst)

        self.tb.run()

        for d in range(n):
            self.assertEqual(expected_results[d], dsts[d].data())

    def test_001_i(self):
        
        #Test stream_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = range(src_len)

        expected_results = calc_expected_result(src_data, n)
        src = gr.vector_source_i(src_data)
        op = gr.stream_to_streams_comp(gr.sizeof_int, n)
        self.tb.connect(src, op)

        dsts = []
        for i in range(n):
            dst = gr.vector_sink_i()
            self.tb.connect((op, i), (dst, 0))
            dsts.append(dst)

        self.tb.run()

        for d in range(n):
            self.assertEqual(expected_results[d], dsts[d].data())

    def test_001_s(self):
        
        #Test stream_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = range(src_len)

        expected_results = calc_expected_result(src_data, n)
        src = gr.vector_source_s(src_data)
        op = gr.stream_to_streams_comp(gr.sizeof_short, n)
        self.tb.connect(src, op)

        dsts = []
        for i in range(n):
            dst = gr.vector_sink_s()
            self.tb.connect((op, i), (dst, 0))
            dsts.append(dst)

        self.tb.run()

        for d in range(n):
            self.assertEqual(expected_results[d], dsts[d].data())
    


    
    
    def test_002_b_1(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_002_b_2(self):
        
        #Test streams_to_stream (using stream_to_streams).

        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_002_b_3(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_b_4(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_i_1(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_i_2(self):
        
        #Test streams_to_stream (using stream_to_streams).

        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_002_i_3(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_i_4(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_f_1(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_f_2(self):
        
        #Test streams_to_stream (using stream_to_streams).

        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_002_f_3(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_f_4(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_c_1(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_c_2(self):
        
        #Test streams_to_stream (using stream_to_streams).

        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    
    def test_002_c_3(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_c_4(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_s_1(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_s_2(self):
        
        #Test streams_to_stream (using stream_to_streams).

        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_002_s_3(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_002_s_4(self):
        
        #Test streams_to_stream (using stream_to_streams).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_003_b_1(self):
        
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_char, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_b_1(self):
        
        #Test vector_to_streams.
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_char, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_char, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_b_2(self):
        
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_char, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_b_2(self):
        
        #Test vector_to_streams.
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_char, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_char, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_003_b_3(self):
        
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_char, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_004_b_3(self):
        
        #Test vector_to_streams.
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_char, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_char, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_003_b_4(self):
        
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_char, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_char, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_004_b_4(self):
        
        #Test vector_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_b(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_char, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_char, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_char, n)
        dst = gr.vector_sink_b()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_c_1(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_gr_complex, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_c_1(self):
        
        #Test vector_to_streams.
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_gr_complex, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_gr_complex, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_c_2(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_gr_complex, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_c_2(self):
        
        #Test vector_to_streams.
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_gr_complex, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_gr_complex, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_c_3(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_gr_complex, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_c_3(self):
        
        #Test vector_to_streams.
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_gr_complex, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_gr_complex, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_c_4(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_gr_complex, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_gr_complex, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_c_4(self):
        
        #Test vector_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_c(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_gr_complex, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_gr_complex, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_gr_complex, n)
        dst = gr.vector_sink_c()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_f_1(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_float, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_f_1(self):
        
        #Test vector_to_streams.
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_float, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_float, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_f_2(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_float, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_f_2(self):
        
        #Test vector_to_streams.
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_float, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_float, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_003_f_3(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_float, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_f_3(self):
        
        #Test vector_to_streams.
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data
        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_float, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_float, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()
        
        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)
        
        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_f_4(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_float, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_float, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_f_4(self):
        
        #Test vector_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_f(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_float, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_float, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_float, n)
        dst = gr.vector_sink_f()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_i_1(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_int, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_i_1(self):
        
        #Test vector_to_streams.
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_int, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_int, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_i_2(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_int, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_i_2(self):
        
        #Test vector_to_streams.
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_int, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_int, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_i_3(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_int, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_i_3(self):
        
        #Test vector_to_streams.
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_int, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_int, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_i_4(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_int, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_int, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_i_4(self):
        
        #Test vector_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_i(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_int, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_int, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_int, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_s_1(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_short, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_s_1(self):
        
        #Test vector_to_streams.
        
        n = 1
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_short, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_short, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_s_2(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_short, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_s_2(self):
        
        #Test vector_to_streams.
        
        n = 2
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_short, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_short, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_s_3(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_short, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_s_3(self):
        
        #Test vector_to_streams.
        
        n = 3
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_short, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_short, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())

    def test_003_s_4(self):
        #Test streams_to_vector (using stream_to_streams & vector_to_stream).
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_streams_comp(gr.sizeof_short, n)
        op2 = gr.streams_to_vector_comp(gr.sizeof_short, n)
        op3 = gr.vector_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_i()

        self.tb.connect(src, op1)
        for i in range(n):
            self.tb.connect((op1, i), (op2, i))
        self.tb.connect(op2, op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())
    
    def test_004_s_4(self):
        
        #Test vector_to_streams.
        
        n = 4
        src_len = n * 8
        src_data = tuple(range(src_len))
        expected_results = src_data

        src = gr.vector_source_s(src_data)
        op1 = gr.stream_to_vector_comp(gr.sizeof_short, n)
        op2 = gr.vector_to_streams_comp(gr.sizeof_short, n)
        op3 = gr.streams_to_stream_comp(gr.sizeof_short, n)
        dst = gr.vector_sink_s()

        self.tb.connect(src, op1, op2)
        for i in range(n):
            self.tb.connect((op2, i), (op3, i))
        self.tb.connect(op3, dst)

        self.tb.run()
        self.assertEqual(expected_results, dst.data())


if __name__ == '__main__':
    gr_unittest.run(test_pipe_fittings, "test_pipe_fittings.xml")

