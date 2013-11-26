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
#from ossie.utils import sb
import cmath

class test_transcendental (gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def test_cos_ff (self):
        src_data = [i for i in range(-100, 100)]
        expected_result = tuple(cmath.cos(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('cos', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_sin_ff (self):
        src_data = [i for i in range(-100, 100)]
        expected_result = tuple(cmath.sin(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('sin', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_tan_ff (self):
        src_data = [i for i in range(-100, 100)]
        expected_result = tuple(cmath.tan(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('tan', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_acos_ff (self):
        src_data = [float(i/100) for i in range(-100, 100)]
        expected_result = tuple(cmath.acos(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('acos', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_asin_ff (self):
        src_data = [float(i/100) for i in range(-100, 100)]
        expected_result = tuple(cmath.asin(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('asin', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_atan_ff (self):
        src_data = [float(i/100) for i in range(-100, 100)]
        expected_result = tuple(cmath.atan(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('atan', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_cosh_ff (self):
        src_data = [i for i in range(-5, 5)]
        expected_result = tuple(cmath.cosh(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('cosh', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_sinh_ff (self):
        src_data = [i for i in range(-5, 5)]
        expected_result = tuple(cmath.sinh(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('sinh', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_tanh_ff (self):
        src_data = [i for i in range(-5, 5)]
        expected_result = tuple(cmath.tanh(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('tanh', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_exp_ff (self):
        src_data = [i for i in range(-5, 5)]
        expected_result = tuple(cmath.exp(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('exp', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_log_ff (self):
        src_data = [i for i in range(1,100)]
        expected_result = tuple(cmath.log(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('log', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_log10_ff (self):
        src_data = [i for i in range(1,100)]
        expected_result = tuple(cmath.log10(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('log10', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_sqrt_ff (self):
        src_data = [i for i in range(100)]
        expected_result = tuple(cmath.sqrt(i) for i in src_data)
        src = gr.vector_source_f(src_data)
        op = gr.transcendental('sqrt', 'float')
        snk = gr.vector_sink_f()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)


    ######################################################
    # Complex Tests
    ######################################################

    def test_cos_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.cos(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('cos', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertComplexTuplesAlmostEqual(expected_result, result, places=5)

    def test_sin_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.sin(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('sin', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_tan_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.tan(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('tan', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_cosh_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.cosh(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('cosh', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_sinh_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.sinh(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('sinh', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_tanh_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.tanh(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('tanh', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_exp_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.exp(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('exp', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_log_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.log(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('log', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_log10_cc (self):
        src_data = [complex(i,i+1) for i in range(-5, 5, 2)]
        expected_result = tuple(cmath.log10(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('log10', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

    def test_sqrt_cc (self):
        src_data = [complex(i,i+1) for i in range(1, 100, 2)]
        expected_result = tuple(cmath.sqrt(i) for i in src_data)
        src = gr.vector_source_c(src_data)
        op = gr.transcendental('sqrt', 'complex_float')
        snk = gr.vector_sink_c()
        self.tb.connect(src, op, snk)
        self.tb.run()
        result = snk.data()
        self.assertFloatTuplesAlmostEqual(expected_result, result, places=5)

if __name__ == '__main__':
    gr_unittest.run(test_transcendental, "test_transcendental")
