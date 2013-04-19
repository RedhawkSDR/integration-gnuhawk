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
 
from ossie.utils import sb
# To set component debug level to TRACE do the following
#c = sb.Component(<component name>,execparams={"DEBUG_LEVEL":5})

import os
import sys
from optparse import OptionParser

execparams={}
debug_level=None

def setDebug(level):
    global debug_level
    global execparams
    debug_level = level
    try:
        if debug_level and debug_level > 0 :
            execparams={"DEBUG_LEVEL":debug_level}
    except Exception, e:
        pass

components_dir = '../components/'

def copy(sizeof=4):
    c = sb.Component(components_dir+'copy_octet/copy_octet.spd.xml', execparams=execparams)
    return c

def add_ii(vlen=1):
    c = sb.Component(components_dir+'add_ii_2i/add_ii_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_ss(vlen=1):
    c = sb.Component(components_dir+'add_ss_2i/add_ss_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_cc(vlen=1):
    c = sb.Component(components_dir+'add_cc_2i/add_cc_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_ff(vlen=1):
    c = sb.Component(components_dir+'add_ff_2i/add_ff_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_const_ii(k):
    c = sb.Component(components_dir+'add_const_ii/add_const_ii.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_sf(k):
    c = sb.Component(components_dir+'add_const_sf/add_const_sf.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_ss(k):
    c = sb.Component(components_dir+'add_const_ss/add_const_ss.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_vii(k):
    c = sb.Component(components_dir+'add_const_vii/add_const_vii.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def add_const_vff(k):
    c = sb.Component(components_dir+'add_const_vff/add_const_vff.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def add_const_vss(k):
    c = sb.Component(components_dir+'add_const_vss/add_const_vss.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def and_bb():
    c = sb.Component(components_dir+'and_bb_2i/and_bb_2i.spd.xml', execparams=execparams)
    return c

def and_ii():
    c = sb.Component(components_dir+'and_ii_2i/and_ii_2i.spd.xml', execparams=execparams)
    return c

def and_ss():
    c = sb.Component(components_dir+'and_ss_2i/and_ss_2i.spd.xml', execparams=execparams)
    return c

def and_const_bb(k):
    c = sb.Component(components_dir+'and_const_bb/and_const_bb.spd.xml', execparams=execparams)
    c.k = k
    return c

def fft_vcc(fft_size=8192, forward=True, window=[], shift=False, nthreads=1):
    c = sb.Component(components_dir+'fft_vcc/fft_vcc.spd.xml', execparams=execparams)
    c.fft_size = fft_size
    c.forward = forward
    # use below workaround for bug
    # can't set seq prop to empy list
    if len(window) > 0:
        c.window = window
    c.shift = shift
    c.nthreads = nthreads
    return c

def fir_filter_ccf(decimation=1, taps=[]):
    c = sb.Component(components_dir+'fir_filter_ccf/fir_filter_ccf.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = list(taps) 
    return c

def fir_filter_fff(decimation=1, taps=[]):
    c = sb.Component(components_dir+'fir_filter_fff/fir_filter_fff.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = list(taps) 
    return c

def fir_filter_fsf(decimation=1, taps=[]):
    c = sb.Component(components_dir+'fir_filter_fsf/fir_filter_fsf.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = list(taps) 
    return c

def fll_band_edge_cc(samples_per_symbol, rolloff, filter_size, bandwidth):
    c = sb.Component(components_dir+'fll_band_edge_cc_4o/fll_band_edge_cc_4o.spd.xml', execparams=execparams)
    c.samples_per_symbol = samples_per_symbol
    c.rolloff = rolloff
    c.filter_size = filter_size
    c.bandwidth = bandwidth
    return c

def fractional_interpolator_ff(phase_shift=0.0, interp_ratio=1.0):
    c = sb.Component(components_dir+'fractional_interpolator_ff/fractional_interpolator_ff.spd.xml', execparams=execparams)
    c.interp_ratio = interp_ratio
    c.phase_shift = phase_shift
    return c

def fractional_interpolator_cc(phase_shift=0.0, interp_ratio=1.0):
    c = sb.Component(components_dir+'fractional_interpolator_cc/fractional_interpolator_cc.spd.xml', execparams=execparams)
    c.interp_ratio = interp_ratio
    c.phase_shift = phase_shift
    return c


def rational_resampler_base_fff(interpolation=1,decimation=1,taps=[]):
    c = sb.Component(components_dir+'rh_gr_rational_resampler_fff/rh_gr_rational_resampler_fff.spd.xml')
    c.interpolation = interpolation
    c.decimation = decimation
    c.taps = list(taps)
    return c

def kludge_copy(sizeof=4):
    c = sb.Component(components_dir+'kludge_copy_float/kludge_copy_float.spd.xml', execparams=execparams)
    return c

def int_to_float(vlen=1, scale=1.0):
    c = sb.Component(components_dir+'int_to_float/int_to_float.spd.xml', execparams=execparams)
    c.scale = scale
    c.vlen = 1
    return c

def integrate_cc(decim):
    c = sb.Component(components_dir+'integrate_cc/integrate_cc.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def integrate_ff(decim):
    c = sb.Component(components_dir+'integrate_ff/integrate_ff.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def integrate_ii(decim):
    c = sb.Component(components_dir+'integrate_ii/integrate_ii.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def integrate_ss(decim):
    c = sb.Component(components_dir+'integrate_ss/integrate_ss.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def interp_fir_filter_fff(interpolation=1,taps=[]):
    c = sb.Component(components_dir+'interp_fir_filter_fff/interp_fir_filter_fff.spd.xml', execparams=execparams)
    c.interpolation = interpolation
    c.taps = list(taps)
    return c

def interp_fir_filter_ccf(interpolation=1,taps=[]):
    c = sb.Component(components_dir+'interp_fir_filter_ccf/interp_fir_filter_ccf.spd.xml', execparams=execparams)
    c.interpolation = interpolation
    c.taps = list(taps)
    return c

def multiply_cc(vlen=1):
    c = sb.Component(components_dir+'multiply_cc_2i/multiply_cc_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_const_ii(k):
    c = sb.Component(components_dir+'multiply_const_ii/multiply_const_ii.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_ff(k):
    c = sb.Component(components_dir+'multiply_const_ff/multiply_const_ff.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_ss(k):
    c = sb.Component(components_dir+'multiply_const_ss/multiply_const_ss.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_vff(k):
    c = sb.Component(components_dir+'multiply_const_vff/multiply_const_vff.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def multiply_const_vii(k):
    c = sb.Component(components_dir+'multiply_const_vii/multiply_const_vii.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def multiply_const_vss(k):
    c = sb.Component(components_dir+'multiply_const_vss/multiply_const_vss.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def multiply_ff(vlen=1):
    c = sb.Component(components_dir+'multiply_ff_2i/multiply_ff_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_ii(vlen=1):
    c = sb.Component(components_dir+'multiply_ii_2i/multiply_ii_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_ss(vlen=1):
    c = sb.Component(components_dir+'multiply_ss_2i/multiply_ss_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def noise_source_f(type_, ampl, seed=0):
    c = sb.Component(components_dir+'noise_source_f/noise_source_f.spd.xml', execparams=execparams)
    c.type = type_
    c.amplitude = ampl
    c.seed = seed
    return c

def null_sink(sizeof=4):
    c = sb.DataSink()
    return c

def pfb_arb_resampler_ccf(rate=1,taps=[],filter_size=32):
    c = sb.Component(components_dir+"pfb_arb_resampler_ccf/pfb_arb_resampler_ccf.spd.xml")
    c.rate= rate
    c.taps = list(taps)
    c.filter_size = filter_size
    return c

def pfb_arb_resampler_fff(rate=1,taps=[],filter_size=32):
    c = sb.Component(components_dir+"pfb_arb_resampler_fff/pfb_arb_resampler_fff.spd.xml")
    c.rate= rate
    c.taps = list(taps)
    c.filter_size = filter_size
    return c

def pfb_clock_sync_ccf(sps, loop_bw, taps, filter_size=32, init_phase=0, max_rate_deviation=1.5, osps=1):
    c = sb.Component(components_dir+'pfb_clock_sync_ccf_4o/pfb_clock_sync_ccf_4o.spd.xml', execparams=execparams)
    c.sps = sps
    c.loop_bandwidth = loop_bw
    c.taps = list(taps)
    c.filter_size = filter_size
    c.init_phase = init_phase
    c.max_rate_deviation = max_rate_deviation
    c.osps = osps
    return c

def pfb_channelizer_ccf(nchans=1,taps=[],osrate=1):
    c = sb.Component(components_dir+"pfb_channelizer_ccf/pfb_channelizer_ccf.spd.xml", execparams=execparams )
    c.numchans=nchans
    c.taps = taps
    c.oversample_rate=osrate
    return c


def short_to_float(vlen=1, scale=1.0):
    c = sb.Component(components_dir+'short_to_float/short_to_float.spd.xml', execparams=execparams)
    c.scale = scale
    c.vlen = 1
    return c

def sig_source_i(sampling_freq=10000.0, waveform=101, frequency=10.0, amplitude=1000.0, offset=0):
    c = sb.Component(components_dir+'sig_source_i/sig_source_i.spd.xml', execparams=execparams)
    c.waveform = waveform
    c.frequency = frequency
    c.sampling_freq = sampling_freq
    c.amplitude = amplitude
    c.offset = offset
    if sampling_freq <= 500000.0:
        c.transfer_size = long(sampling_freq)
    else:
        c.transfer_size = long(500000)
    return c

def sig_source_f(sampling_freq=10000.0, waveform=101, frequency=10.0, amplitude=100.0, offset=0.0):
    c = sb.Component(components_dir+'sig_source_f/sig_source_f.spd.xml', execparams=execparams)
    c.waveform = waveform
    c.frequency = frequency
    c.sampling_freq = sampling_freq
    c.amplitude = amplitude
    c.offset = offset
    if sampling_freq <= 500000.0:
        c.transfer_size = long(sampling_freq+1)
    else:
        c.transfer_size = long(500000)
    return c

def agc2_ff(attack_rate = 1e-1, decay_rate = 1e-2, reference = 1.0,gain = 1.0, max_gain = 0.0):
    c = sb.Component(components_dir+'agc2_ff/agc2_ff.spd.xml', execparams=execparams)
    c.attack_rate = attack_rate
    c.decay_rate = decay_rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def agc2_cc(attack_rate = 1e-1, decay_rate = 1e-2, reference = 1.0,gain = 1.0, max_gain = 0.0):
    c = sb.Component(components_dir+'agc2_cc/agc2_cc.spd.xml', execparams=execparams)
    c.attack_rate = attack_rate
    c.decay_rate = decay_rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def agc_ff(rate, reference, gain, max_gain):
    c = sb.Component(components_dir+'agc_ff/agc_ff.spd.xml', execparams=execparams)
    c.rate = rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def agc_cc(rate, reference, gain, max_gain):
    c = sb.Component(components_dir+'agc_cc/agc_cc.spd.xml', execparams=execparams)
    c.rate = rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def feedforward_agc_cc(nsamples, reference):
    c = sb.Component(components_dir+'feedforward_agc_cc/feedforward_agc_cc.spd.xml', execparams=execparams)
    c.nsamples = nsamples
    c.reference = reference
    return c

def glfsr_source_f(degree, repeat=False):
    c = sb.Component(components_dir+'glfsr_source_f/glfsr_source_f.spd.xml', execparams=execparams)
    c.degree = degree
    c.repeat = repeat
    return c

def glfsr_source_b(degree, repeat=False):
    c = sb.Component(components_dir+'glfsr_source_b/glfsr_source_b.spd.xml', execparams=execparams)
    c.degree = degree
    c.repeat = repeat
    return c

def pn_correlator_cc(degree, mask=0, seed=1):
    c = sb.Component(components_dir+'pn_correlator_cc/pn_correlator_cc.spd.xml', execparams=execparams)
    c.degree = degree
    c.mask = mask
    c.seed = seed
    return c

def float_to_complex(vlen=1):
    c = sb.Component(components_dir+'float_to_complex/float_to_complex.spd.xml',execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_arg(vlen=1):
    c = sb.Component(components_dir+'complex_to_arg/complex_to_arg.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_float(vlen=1,numPorts=2):
    if numPorts == 2:
        c = sb.Component(components_dir+'complex_to_float_2o/complex_to_float_2o.spd.xml', execparams=execparams)
    elif numPorts == 1:
        c = sb.Component(components_dir+'complex_to_float_1o/complex_to_float_1o.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports"
        return None
    c.vlen = vlen
    return c

def complex_to_imag(vlen=1):
    c = sb.Component(components_dir+'complex_to_imag/complex_to_imag.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_mag(vlen=1):
    c = sb.Component(components_dir+'complex_to_mag/complex_to_mag.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_mag_squared(vlen=1):
    c = sb.Component(components_dir+'complex_to_mag_squared/complex_to_mag_squared.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_real(vlen=1):
    c = sb.Component(components_dir+'complex_to_real/complex_to_real.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def chunks_to_symbols_bf(symbol_table, D):
    c = sb.Component(components_dir+'chunks_to_symbols_bf/chunks_to_symbols_bf.spd.xml')
    c.symbol_table = symbol_table
    c.D = D
    return c

def dc_blocker_ff(d_len, lng_form):
    c = sb.Component(components_dir+'dc_blocker_ff/dc_blocker_ff.spd.xml', execparams=execparams)
    c.delay_length = d_len
    c.long_form = lng_form
    return c

def dc_blocker_cc(d_len, lng_form):
    c = sb.Component(components_dir+'dc_blocker_cc/dc_blocker_cc.spd.xml', execparams=execparams)
    c.delay_length = d_len
    c.long_form = lng_form
    return c

def single_pole_iir_filter_ff(alpha, block_size=1):
    c = sb.Component(components_dir+'single_pole_iir_filter_ff/single_pole_iir_filter_ff.spd.xml', execparams=execparams)
    c.alpha = alpha
    c.vlen = block_size
    return c

def single_pole_iir_filter_cc(alpha, block_size=1):
    c = sb.Component(components_dir+'single_pole_iir_filter_cc/single_pole_iir_filter_cc.spd.xml', execparams=execparams)
    c.alpha = alpha
    c.vlen = block_size
    return c

def iir_filter_ffd(fftaps, fbtaps):
    c = sb.Component(components_dir+'iir_filter_ffd/iir_filter_ffd.spd.xml', execparams=execparams)
    c.fftaps = fftaps
    c.fbtaps = fbtaps
    return c

def fft_filter_fff(dec, taps, nthreads=1):
    c = sb.Component(components_dir+'fft_filter_fff/fft_filter_fff.spd.xml', execparams=execparams)
    c.decimation = dec
    c.taps = taps
    c.nthreads = nthreads
    return c

def hilbert_fc(ntaps):
    c = sb.Component(components_dir+'hilbert_fc/hilbert_fc.spd.xml', execparams=execparams)
    c.ntaps = ntaps
    return c

def goertzel_fc(rate, length, freq):
    c = sb.Component(components_dir+'goertzel_fc/goertzel_fc.spd.xml', execparams=execparams)
    c.rate = rate
    c.length = length
    c.frequency = freq
    return c

def firdes_hilbert(ntaps=19, window=3, beta=6.76):
    c = sb.Component('./components/firdes/firdes.spd.xml', execparams=execparams)
    c.hilbert_ntaps = ntaps
    c.windowtype = window
    c.beta = beta
    return list(c.hilbert)

def filter_delay_fc(taps, in_ports=1):
    if in_ports == 1:
        c = sb.Component(components_dir+'filter_delay_fc_1i/filter_delay_fc_1i.spd.xml', execparams=execparams)
    elif in_ports == 2:
        c = sb.Component(components_dir+'filter_delay_fc_2i/filter_delay_fc_2i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."

    c.taps = taps
    return c

def unpack_k_bits_bb(k):
    c = sb.Component(components_dir+'unpack_k_bits_bb/unpack_k_bits_bb.spd.xml')
    c.k = k
    return c
