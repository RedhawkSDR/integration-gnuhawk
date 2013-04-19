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
import digital_swig
import random, math

taps = (0.0027399752289056778, 0.0091556021943688393, 0.0074748615734279156, -0.0023689665831625462, -0.01158357597887516, -0.010232637636363506, 0.0020354459993541241, 0.013270951807498932, 0.0095369396731257439, -0.0095137273892760277, -0.025359446182847023, -0.014720465987920761, 0.025520168244838715, 0.065102487802505493, 0.056907851248979568, -0.021990923210978508, -0.13466374576091766, -0.1879347562789917, -0.0843767449259758, 0.20610538125038147, 0.60552364587783813, 0.95358270406723022, 1.0915778875350952, 0.95358270406723022, 0.60552364587783813, 0.20610538125038147, -0.0843767449259758, -0.1879347562789917, -0.13466374576091766, -0.021990923210978508, 0.056907851248979568, 0.065102487802505493, 0.025520168244838715, -0.014720465987920761, -0.025359446182847023, -0.0095137273892760277, 0.0095369396731257439, 0.013270951807498932, 0.0020354459993541241, -0.010232637636363506, -0.01158357597887516, -0.0023689665831625462, 0.0074748615734279156, 0.0091556021943688393, 0.0027399752289056778)

class test_fll_band_edge_cc(gr_unittest.TestCase):

    def setUp (self):
        self.tb = gr.top_block ()

    def tearDown (self):
        self.tb = None

    def xtest01 (self):
        sps = 4
        rolloff = 0.35
        bw = 2*math.pi/100.0
        ntaps = 45
        
        # Create pulse shape filter
        #rrc_taps = gr.firdes.root_raised_cosine(
        #    sps, sps, 1.0, rolloff, ntaps)
        rrc_taps = taps

        # The frequency offset to correct
        foffset = 0.2 / (2.0*math.pi)

        # Create a set of 1's and -1's, pulse shape and interpolate to sps
        random.seed(0)
        data = [2.0*random.randint(0, 2) - 1.0 for i in xrange(200)]
        self.src = gr.vector_source_c(data, False)
        self.rrc = gr.interp_fir_filter_ccf(sps, rrc_taps)

        # Mix symbols with a complex sinusoid to spin them
        self.nco = gr.sig_source_c(1, gr.GR_SIN_WAVE, foffset, 1)
        self.mix = gr.multiply_cc()

        # FLL will despin the symbols to an arbitrary phase
        self.fll = digital_swig.fll_band_edge_cc(sps, rolloff, ntaps, bw)

        # Create sinks for all outputs of the FLL
        # we will only care about the freq and error outputs
        self.vsnk_frq = gr.vector_sink_f()
        self.nsnk_fll = gr.null_sink(gr.sizeof_gr_complex)
        self.nsnk_phs = gr.null_sink(gr.sizeof_float)
        self.nsnk_err = gr.null_sink(gr.sizeof_float)
        
        # Connect the blocks
        self.tb.connect(self.nco, (self.mix,1))
        self.tb.connect(self.src, self.rrc, (self.mix,0))
        self.tb.connect(self.mix, self.fll, self.nsnk_fll)
        self.tb.connect((self.fll,1), self.vsnk_frq)
        self.tb.connect((self.fll,2), self.nsnk_phs)
        self.tb.connect((self.fll,3), self.nsnk_err)
        self.tb.run()
        
        N = 700
        dst_data = self.vsnk_frq.data()[N:]

        expected_result = len(dst_data)* [-0.20,]
        self.assertFloatTuplesAlmostEqual (expected_result, dst_data, 4)

if __name__ == '__main__':
    gr_unittest.run(test_fll_band_edge_cc, "test_fll_band_edge_cc.xml")
