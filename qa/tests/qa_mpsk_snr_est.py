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
import digital_swig as digital
import math, random

def get_cplx():
    return complex(2*random.randint(0,1) - 1, 0)
def get_n_cplx():
    return complex(random.random()-0.5, random.random()-0.5)

class test_mpsk_snr_est (gr_unittest.TestCase):
    def setUp (self):
        self.tb = gr.top_block ()

        random.seed(0) # make repeatable
        N = 10000
        self._noise = [get_n_cplx() for i in xrange(N)]
        self._bits = [get_cplx() for i in xrange(N)]

    def tearDown (self):
        self.tb = None

    def mpsk_snr_est_setup (self, t, N, alpha):
        result = []
        for i in xrange(1,6):
            src_data = [b+(i*n) for b,n in zip(self._bits, self._noise)]
            
            src = gr.vector_source_c (src_data)
            op = digital.mpsk_snr_est_cc (t, N, alpha)
            dst = gr.null_sink (gr.sizeof_gr_complex)

            self.tb.connect (src, op)
            self.tb.connect (op, dst)
            self.tb.run ()               # run the graph and wait for it to finish

            result.append(op.snr.queryValue())

            # Reset python sandbox environment
            self.tb.stop()
            self.tb.__del__()
        return result
            
    def test_mpsk_snr_est_simple (self):
        expected_result = [11.48, 5.91, 3.30, 2.08, 1.46]

        N = 10000
        alpha = 0.001

        actual_result = self.mpsk_snr_est_setup(digital.SNR_EST_SIMPLE, N, alpha)
        self.assertFloatTuplesAlmostEqual (expected_result, actual_result, 2)

    def test_mpsk_snr_est_skew (self):
        expected_result = [11.48, 5.91, 3.30, 2.08, 1.46]

        N = 10000
        alpha = 0.001

        actual_result = self.mpsk_snr_est_setup(digital.SNR_EST_SKEW, N, alpha)
        self.assertFloatTuplesAlmostEqual (expected_result, actual_result, 2)

    def test_mpsk_snr_est_m2m4 (self):
        expected_result = [11.02, 6.20, 4.98, 5.16, 5.66]

        N = 10000
        alpha = 0.001

        actual_result = self.mpsk_snr_est_setup(digital.SNR_EST_M2M4, N, alpha)
        self.assertFloatTuplesAlmostEqual (expected_result, actual_result, 2)

    def test_mpsk_snr_est_svn (self):
        expected_result = [10.90, 6.00, 4.76, 4.97, 5.49]

        N = 10000
        alpha = 0.001

        actual_result = self.mpsk_snr_est_setup(digital.SNR_EST_SVR, N, alpha)
        self.assertFloatTuplesAlmostEqual (expected_result, actual_result, 2)

    def test_probe_mpsk_snr_est_m2m4 (self):
        expected_result = [11.02, 6.20, 4.98, 5.16, 5.66]

        actual_result = []
        for i in xrange(1,6):
            src_data = [b+(i*n) for b,n in zip(self._bits, self._noise)]
            
            src = gr.vector_source_c (src_data)

            N = 10000
            alpha = 0.001
            op = digital.probe_mpsk_snr_est_c (digital.SNR_EST_M2M4, N, alpha)

            self.tb.connect (src, op)
            self.tb.run ()               # run the graph and wait for it to finish

            actual_result.append(op.snr.queryValue())

            # Reset python sandbox environment
            self.tb.stop()
            self.tb.__del__()
        self.assertFloatTuplesAlmostEqual (expected_result, actual_result, 2)


if __name__ == '__main__':
    # Test various SNR estimators; we're not using a Gaussian
    # noise source, so these estimates have no real meaning;
    # just a sanity check.
    gr_unittest.run(test_mpsk_snr_est, "test_mpsk_snr_est.xml")

