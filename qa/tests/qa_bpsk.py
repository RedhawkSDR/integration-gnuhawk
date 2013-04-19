#
# This file is protected by Copyright. Please refer to the COPYRIGHT file 
# distributed with this source distribution.
# 
# This file is part of GNUHAWK.
# 
# GNUHAWK is free software: you can redistribute it and/or modify is under the 
# terms of the GNU General Public License as published by the Free Software 
# Foundation, either version 3 of the License, or (at your option) any later 
# version.
# 
# GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
# WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR 
# A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

# You should have received a copy of the GNU General Public License along with 
# this program.  If not, see http://www.gnu.org/licenses/.
#
 
import math
import operator
import random
import struct
import bpsk_taps
from ossie.utils import sb

import unittest

import sys

execparams={}

debug_level=None

try:
    for i in xrange(len(sys.argv)):
        if sys.argv[i] == '--debug':
            debug_level = int(sys.argv[i+1])

    if debug_level and debug_level > 0 :
        execparams={"DEBUG_LEVEL":debug_level}

except Exception, e:
    pass


def bits_to_symbols(bits):
    return [(-1+0j, 1+0j)[x] for x in bits]

def symbols_to_samples(symbols, samples_per_symbol):
    samples = []
    for ii in range(len(symbols)):
        samples += [symbols[ii]]*samples_per_symbol
    return samples

def random_sample(stddev):
    r = random.gauss(0.0, stddev)
    phi = random.uniform(-math.pi, math.pi)
    return r * (math.cos(phi) + math.sin(phi)*1j)

def add_noise(samples, stddev):
    return [x+random_sample(stddev) for x in samples]

def expand_complex(samples):
    # Turn each complex value into a 2-element list and concatenate
    if len(samples) > 0 and type(samples[0]) == complex:
        return sum([[x.real, x.imag] for x in samples], [])
    else:
        return sum([[x, 0.0] for x in samples], [])

def apply_rotation(samples, freq):
    rot = 1.0+0j
    rad = 2.0*math.pi*freq
    dphase = complex(math.cos(rad), math.sin(rad))
    data = []
    for sample in samples:
        data.append(sample*rot)
        rot *= dphase
    return data

class test_bpsk(unittest.TestCase):

    def test_bpsk_decode(self):
        nbits = 6000
        samples_per_symbol = 4

        # Generate random bit sequence and convert to BPSK symbols
        bits_in = [random.randint(0,1) for x in range(nbits)]
        samples_in = symbols_to_samples(bits_to_symbols(bits_in), samples_per_symbol)

        # Add Gaussian noise and a slow rotation to the symbols
        sig_in = apply_rotation(add_noise(samples_in, 0.10), 0.01)

        # Convert from a list of complex pairs to a single list of floats
        sig_in = expand_complex(sig_in)

        source = sb.DataSource()
        sink = sb.DataSink()

        # FLL
        fll_ntaps = 55
        freq_recov = sb.Component('../components/fll_band_edge_cc_4o/fll_band_edge_cc_4o.spd.xml',execparams=execparams)
        freq_recov.samples_per_symbol = samples_per_symbol
        freq_recov.rolloff = 0.35
        freq_recov.filter_size = fll_ntaps
        freq_recov.bandwidth = 2.0*math.pi/100.0

        # Timing recovery
        nfilts = 32
        time_recov = sb.Component('../components/pfb_clock_sync_ccf_4o/pfb_clock_sync_ccf_4o.spd.xml', execparams=execparams)
        time_recov.sps = samples_per_symbol
        time_recov.loop_bw = 2*math.pi/100.0
        # Note: taps are hard-coded
        time_recov.taps = list(bpsk_taps.taps)
        time_recov.filter_size = nfilts
        time_recov.init_phase = nfilts/2
        time_recov.max_rate_deviation = 1.5
        time_recov.osps = 1

        # BPSK symbol decode
        receiver = sb.Component('../components/psk_demod_cb/psk_demod_cb.spd.xml', execparams=execparams)
        receiver.constellation = 1
        receiver.loop_bw = 2*math.pi/100.0
        receiver.fmin = -0.25
        receiver.fmax = 0.25

        # Connect components
        source.connect(freq_recov)
        freq_recov.connect(time_recov, usesPortName='data_complex_out')
        time_recov.connect(receiver, usesPortName='data_complex_out')
        receiver.connect(sink)

        # Push data through components, waiting for completion
        sb.start()
        source.push(sig_in, EOS=True, complexData=True)
        sym_out = sink.getData(eos_block=True)
        sb.stop()

        # The symbol is equivalent to the bit value; unpack from a char to
        # a number.
        bits_out = map(lambda x: struct.unpack('b', x)[0], sym_out)

        # The output data is delayed by 34 samples
        delay = 34

        # Verify that our delayed output data matches the input
        bit_errors = 0
        for ii in range(len(bits_out)-delay):
            if bits_out[ii+delay] != bits_in[ii]:
                bit_errors += 1

        self.failUnless(bit_errors == 0, '%d bit errors' % (bit_errors,))

if __name__ == '__main__':
    unittest.main()
