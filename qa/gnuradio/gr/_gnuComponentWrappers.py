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
    c = sb.launch(components_dir+'copy_octet/copy_octet.spd.xml', execparams=execparams)
    return c

def argmax(sizeof, len, ninputs):
    if str(sizeof.__name__).find("sizeof_float") != -1:
        if (ninputs == 1):
            c = sb.launch(components_dir+'argmax_fs_1i/argmax_fs_1i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 2):
            c = sb.launch(components_dir+'argmax_fs_2i/argmax_fs_2i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 3):
            c = sb.launch(components_dir+'argmax_fs_3i/argmax_fs_3i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 4):
            c = sb.launch(components_dir+'argmax_fs_4i/argmax_fs_4i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
    if str(sizeof.__name__).find("sizeof_int") != -1:
        if (ninputs == 1):
            c = sb.launch(components_dir+'argmax_is_1i/argmax_is_1i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 2):
            c = sb.launch(components_dir+'argmax_is_2i/argmax_is_2i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 3):
            c = sb.launch(components_dir+'argmax_is_3i/argmax_is_3i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 4):
            c = sb.launch(components_dir+'argmax_is_4i/argmax_is_4i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
    if str(sizeof.__name__).find("sizeof_short") != -1:
        if (ninputs == 1):
            c = sb.launch(components_dir+'argmax_ss_1i/argmax_ss_1i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 2):
            c = sb.launch(components_dir+'argmax_ss_2i/argmax_ss_2i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 3):
            c = sb.launch(components_dir+'argmax_ss_3i/argmax_ss_3i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 4):
            c = sb.launch(components_dir+'argmax_ss_4i/argmax_ss_4i.spd.xml', execparams=execparams)
            c.vlen = len
            return c

def adaptive_fir_ccc(name, decimation, taps):
    c = sb.launch(components_dir+'adaptive_fir_ccc/adaptive_fir_ccc.spd.xml', execparams=execparams)
    c.name = name
    c.decimation = decimation
    c.taps = taps
    return c

def adaptive_fir_ccf(name, decimation, taps):
    c = sb.launch(components_dir+'adaptive_fir_ccf/adaptive_fir_ccf.spd.xml', execparams=execparams)
    c.name = name
    c.decimation = decimation
    c.taps = taps
    return c

def add_ii(vlen=1):
    c = sb.launch(components_dir+'add_ii_2i/add_ii_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_ss(vlen=1):
    c = sb.launch(components_dir+'add_ss_2i/add_ss_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_cc(vlen=1, inputs=2):
    if inputs == 2:
        c = sb.launch(components_dir+'add_cc_2i/add_cc_2i.spd.xml', execparams=execparams)
    elif inputs == 5:
        c = sb.launch(components_dir+'add_cc_5i/add_cc_5i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of input ports"
        return None
    c.vlen = vlen
    return c

def add_ff(vlen=1):
    c = sb.launch(components_dir+'add_ff_2i/add_ff_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def add_const_cc(k):
    c = sb.launch(components_dir+'add_const_cc/add_const_cc.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_ff(k):
    c = sb.launch(components_dir+'add_const_ff/add_const_ff.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_ii(k):
    c = sb.launch(components_dir+'add_const_ii/add_const_ii.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_sf(k):
    c = sb.launch(components_dir+'add_const_sf/add_const_sf.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_ss(k):
    c = sb.launch(components_dir+'add_const_ss/add_const_ss.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_vcc(k):
    c = sb.launch(components_dir+'add_const_vcc/add_const_vcc.spd.xml', execparams=execparams)
    c.k = k
    return c

def add_const_vii(k):
    c = sb.launch(components_dir+'add_const_vii/add_const_vii.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def add_const_vff(k):
    c = sb.launch(components_dir+'add_const_vff/add_const_vff.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def add_const_vss(k):
    c = sb.launch(components_dir+'add_const_vss/add_const_vss.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def additive_scrambler_bb(m, s, l, count=1):
    c = sb.launch(components_dir+'additive_scrambler_bb/additive_scrambler_bb.spd.xml')
    c.mask = m
    c.seed = s
    c.len = l
    c.count = count
    return c

def agc_ff(rate, reference, gain, max_gain):
    c = sb.launch(components_dir+'agc_ff/agc_ff.spd.xml', execparams=execparams)
    c.rate = rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def agc_cc(rate, reference, gain, max_gain):
    c = sb.launch(components_dir+'agc_cc/agc_cc.spd.xml', execparams=execparams)
    c.rate = rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def agc2_ff(attack_rate = 1e-1, decay_rate = 1e-2, reference = 1.0,gain = 1.0, max_gain = 0.0):
    c = sb.launch(components_dir+'agc2_ff/agc2_ff.spd.xml', execparams=execparams)
    c.attack_rate = attack_rate
    c.decay_rate = decay_rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def agc2_cc(attack_rate = 1e-1, decay_rate = 1e-2, reference = 1.0,gain = 1.0, max_gain = 0.0):
    c = sb.launch(components_dir+'agc2_cc/agc2_cc.spd.xml', execparams=execparams)
    c.attack_rate = attack_rate
    c.decay_rate = decay_rate
    c.reference = reference
    c.gain = gain
    c.max_gain = max_gain
    return c

def alaw_decode_bs():
    c = sb.launch(components_dir+'alaw_decode_bs/alaw_decode_bs.spd.xml', execparams=execparams)
    return c

def alaw_encode_sb():
    c = sb.launch(components_dir+'alaw_encode_sb/alaw_encode_sb.spd.xml', execparams=execparams)
    return c

def and_bb():
    c = sb.launch(components_dir+'and_bb_2i/and_bb_2i.spd.xml', execparams=execparams)
    return c

def and_ii():
    c = sb.launch(components_dir+'and_ii_2i/and_ii_2i.spd.xml', execparams=execparams)
    return c

def and_ss():
    c = sb.launch(components_dir+'and_ss_2i/and_ss_2i.spd.xml', execparams=execparams)
    return c

def and_const_bb(k):
    c = sb.launch(components_dir+'and_const_bb/and_const_bb.spd.xml', execparams=execparams)
    c.k = k
    return c

def binary_slicer_fb():
    c = sb.launch(components_dir+'binary_slicer_fb/binary_slicer_fb.spd.xml', execparams=execparams)
    return c

def bytes_to_syms():
    c = sb.launch(components_dir+'bytes_to_syms/bytes_to_syms.spd.xml', execparams=execparams)
    return c

def chunks_to_symbols_bc(symbol_table, D=1):
    c = sb.launch(components_dir+'chunks_to_symbols_bc/chunks_to_symbols_bc.spd.xml', execparams=execparams)
    c.symbol_table = symbol_table
    c.D = D
    return c

def chunks_to_symbols_bf(symbol_table, D=1):
    c = sb.launch(components_dir+'chunks_to_symbols_bf/chunks_to_symbols_bf.spd.xml', execparams=execparams)
    c.symbol_table = symbol_table
    c.D = D
    return c

def chunks_to_symbols_ic(symbol_table, D=1):
    c = sb.launch(components_dir+'chunks_to_symbols_ic/chunks_to_symbols_ic.spd.xml', execparams=execparams)
    c.symbol_table = symbol_table
    c.D = D
    return c

def chunks_to_symbols_if(symbol_table, D=1):
    c = sb.launch(components_dir+'chunks_to_symbols_if/chunks_to_symbols_if.spd.xml', execparams=execparams)
    c.symbol_table = symbol_table
    c.D = D
    return c

def chunks_to_symbols_sc(symbol_table, D=1):
    c = sb.launch(components_dir+'chunks_to_symbols_sc/chunks_to_symbols_sc.spd.xml', execparams=execparams)
    c.symbol_table = symbol_table
    c.D = D
    return c

def chunks_to_symbols_sf(symbol_table, D=1):
    c = sb.launch(components_dir+'chunks_to_symbols_sf/chunks_to_symbols_sf.spd.xml', execparams=execparams)
    c.symbol_table = symbol_table
    c.D = D
    return c

def clock_recovery_mm_cc(omega, gain_omega, mu, gain_mu, omega_rel_lim):
    c = sb.launch(components_dir+'clock_recovery_mm_cc_1o/clock_recovery_mm_cc_1o.spd.xml', execparams=execparams)
    c.omega = omega
    c.gain_omega = gain_omega
    c.mu = mu
    c.gain_mu = gain_mu
    c.omega_relative_limit = omega_rel_lim
    return c

def clock_recovery_mm_ff(omega, gain_omega, mu, gain_mu, omega_rel_lim):
    c = sb.launch(components_dir+'clock_recovery_mm_ff/clock_recovery_mm_ff.spd.xml', execparams=execparams)
    c.omega = omega
    c.gain_omega = gain_omega
    c.mu = mu
    c.gain_mu = gain_mu
    c.omega_relative_limit = omega_rel_lim
    return c

def cma_equalizer_cc(num_taps, modulus, mu, sps):
    c = sb.launch(components_dir+'cma_equalizer_cc/cma_equalizer_cc.spd.xml', execparams=execparams)
    c.num_taps = num_taps
    c.modulus = modulus
    c.mu = mu
    c.sps = sps
    return c

def complex_to_arg(vlen=1):
    c = sb.launch(components_dir+'complex_to_arg/complex_to_arg.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_float(vlen=1,numPorts=2):
    if numPorts == 2:
        c = sb.launch(components_dir+'complex_to_float_2o/complex_to_float_2o.spd.xml', execparams=execparams)
    elif numPorts == 1:
        c = sb.launch(components_dir+'complex_to_float_1o/complex_to_float_1o.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports"
        return None
    c.vlen = vlen
    return c

def complex_to_imag(vlen=1):
    c = sb.launch(components_dir+'complex_to_imag/complex_to_imag.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_mag(vlen=1):
    c = sb.launch(components_dir+'complex_to_mag/complex_to_mag.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_mag_squared(vlen=1):
    c = sb.launch(components_dir+'complex_to_mag_squared/complex_to_mag_squared.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def complex_to_real(vlen=1):
    c = sb.launch(components_dir+'complex_to_real/complex_to_real.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def conjugate_cc():
    c = sb.launch(components_dir+'conjugate_cc/conjugate_cc.spd.xml', execparams=execparams)
    return c

def constellation_decoder_cb(cons):
    c = sb.launch(components_dir+'constellation_decoder_cb/constellation_decoder_cb.spd.xml', execparams=execparams)
    c.constellation = cons
    return c

def copy(sizeof, enabled, items):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'copy_bb/copy_bb.spd.xml', execparams=execparams)
        c.itemsize = items
        c.enable = enabled
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'copy_ss/copy_ss.spd.xml', execparams=execparams)
        c.itemsize = items
        c.enable = enabled
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'copy_ii/copy_ii.spd.xml', execparams=execparams)
        c.itemsize = items
        c.enable = enabled
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'copy_cc/copy_cc.spd.xml', execparams=execparams)
        c.itemsize = items
        c.enable = enabled
    return c

def correlate_access_code_bb(access_code, threshold):
    c = sb.launch(components_dir+'correlate_access_code_bb/correlate_access_code_bb.spd.xml', execparams=execparams)
    c.access_code = access_code
    c.threshold = threshold
    return c

def correlate_access_code_tag_bb(access_code, threshold, tag_name):
    c = sb.launch(components_dir+'correlate_access_code_tag_bb/correlate_access_code_tag_bb.spd.xml', execparams=execparams)
    c.access_code = access_code
    c.threshold = threshold
    c.tag_name = tag_name
    return c

def costas_loop_cc(loop_bw, order):
    c = sb.launch(components_dir+'costas_loop_cc_2o/costas_loop_cc_2o.spd.xml', execparams=execparams)
    c.loop_bw = loop_bw
    c.order = order
    return c

def dc_blocker_ff(d_len, lng_form):
    c = sb.launch(components_dir+'dc_blocker_ff/dc_blocker_ff.spd.xml', execparams=execparams)
    c.delay_length = d_len
    c.long_form = lng_form
    return c

def dc_blocker_cc(d_len, lng_form):
    c = sb.launch(components_dir+'dc_blocker_cc/dc_blocker_cc.spd.xml', execparams=execparams)
    c.delay_length = d_len
    c.long_form = lng_form
    return c

def decode_ccsds_27_fb():
    c = sb.launch(components_dir+'decode_ccsds_27_fb/decode_ccsds_27_fb.spd.xml', execparams=execparams)
    return c

def deinterleave(sizeof, size):
    if str(sizeof.__name__).find("sizeof_float") != -1:
        if size == 1:
            c = sb.launch(components_dir+'deinterleave_ff_1o/deinterleave_ff_1o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 2:
            c = sb.launch(components_dir+'deinterleave_ff_2o/deinterleave_ff_2o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 3:
            c = sb.launch(components_dir+'deinterleave_ff_3o/deinterleave_ff_3o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 4:
            c = sb.launch(components_dir+'deinterleave_ff_4o/deinterleave_ff_4o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        if size == 1:
            c = sb.launch(components_dir+'deinterleave_ss_1o/deinterleave_ss_1o.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
        elif size == 2:
            c = sb.launch(components_dir+'deinterleave_ss_2o/deinterleave_ss_2o.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
        elif size == 3:
            c = sb.launch(components_dir+'deinterleave_ss_3o/deinterleave_ss_3o.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
        elif size == 4:
            c = sb.launch(components_dir+'deinterleave_ss_4o/deinterleave_ss_4o.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
    elif str(sizeof.__name__).find("sizeof_char") != -1:
        if size == 1:
            c = sb.launch(components_dir+'deinterleave_bb_1o/deinterleave_bb_1o.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
        elif size == 2:
            c = sb.launch(components_dir+'deinterleave_bb_2o/deinterleave_bb_2o.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
        elif size == 3:
            c = sb.launch(components_dir+'deinterleave_bb_3o/deinterleave_bb_3o.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
        elif size == 4:
            c = sb.launch(components_dir+'deinterleave_bb_4o/deinterleave_bb_4o.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        if size == 1:
            c = sb.launch(components_dir+'deinterleave_ii_1o/deinterleave_ii_1o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 2:
            c = sb.launch(components_dir+'deinterleave_ii_2o/deinterleave_ii_2o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 3:
            c = sb.launch(components_dir+'deinterleave_ii_3o/deinterleave_ii_3o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 4:
            c = sb.launch(components_dir+'deinterleave_ii_4o/deinterleave_ii_4o.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        if size == 1:
            c = sb.launch(components_dir+'deinterleave_cc_1o/deinterleave_cc_1o.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
        elif size == 2:
            c = sb.launch(components_dir+'deinterleave_cc_2o/deinterleave_cc_2o.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
        elif size == 3:
            c = sb.launch(components_dir+'deinterleave_cc_3o/deinterleave_cc_3o.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
        elif size == 4:
            c = sb.launch(components_dir+'deinterleave_cc_4o/deinterleave_cc_4o.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c

def descrambler_bb(m, s, l):
    c = sb.launch(components_dir+'descrambler_bb/descrambler_bb.spd.xml', execparams=execparams)
    c.mask = m
    c.seed = s
    c.len = l
    return c

def diff_decoder_bb(mod):
    c = sb.launch(components_dir+'diff_decoder_bb/diff_decoder_bb.spd.xml',  execparams=execparams)
    c.modulus = mod
    return c

def diff_encoder_bb(mod):
    c = sb.launch(components_dir+'diff_encoder_bb/diff_encoder_bb.spd.xml',  execparams=execparams)
    c.modulus = mod
    return c

def diff_phasor_cc():
    c = sb.launch(components_dir+'diff_phasor_cc/diff_phasor_cc.spd.xml', execparams=execparams)
    return c

def divide_cc(num_in, vlen=1):
    if num_in == 1:
        c = sb.launch(components_dir+'divide_cc_1i/divide_cc_1i.spd.xml', execparams=execparams)
    elif num_in == 2:
        c = sb.launch(components_dir+'divide_cc_2i/divide_cc_2i.spd.xml', execparams=execparams)
    elif num_in == 3:
        c = sb.launch(components_dir+'divide_cc_3i/divide_cc_3i.spd.xml', execparams=execparams)
    elif num_in == 4:
        c = sb.launch(components_dir+'divide_cc_4i/divide_cc_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of input ports'
        return None
    c.vlen=vlen
    return c

def divide_ff(num_in, vlen=1):
    if num_in == 1:
        c = sb.launch(components_dir+'divide_ff_1i/divide_ff_1i.spd.xml', execparams=execparams)
    elif num_in == 2:
        c = sb.launch(components_dir+'divide_ff_2i/divide_ff_2i.spd.xml', execparams=execparams)
    elif num_in == 3:
        c = sb.launch(components_dir+'divide_ff_3i/divide_ff_3i.spd.xml', execparams=execparams)
    elif num_in == 4:
        c = sb.launch(components_dir+'divide_ff_4i/divide_ff_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of input ports'
        return None
    c.vlen=vlen
    return c

def divide_ii(num_in, vlen=1):
    if num_in == 1:
        c = sb.launch(components_dir+'divide_ii_1i/divide_ii_1i.spd.xml', execparams=execparams)
    elif num_in == 2:
        c = sb.launch(components_dir+'divide_ii_2i/divide_ii_2i.spd.xml', execparams=execparams)
    elif num_in == 3:
        c = sb.launch(components_dir+'divide_ii_3i/divide_ii_3i.spd.xml', execparams=execparams)
    elif num_in == 4:
        c = sb.launch(components_dir+'divide_ii_4i/divide_ii_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of input ports'
        return None
    c.vlen=vlen
    return c

def divide_ss(num_in, vlen=1):
    if num_in == 1:
        c = sb.launch(components_dir+'divide_ss_1i/divide_ss_1i.spd.xml', execparams=execparams)
    elif num_in == 2:
        c = sb.launch(components_dir+'divide_ss_2i/divide_ss_2i.spd.xml', execparams=execparams)
    elif num_in == 3:
        c = sb.launch(components_dir+'divide_ss_3i/divide_ss_3i.spd.xml', execparams=execparams)
    elif num_in == 4:
        c = sb.launch(components_dir+'divide_ss_4i/divide_ss_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of input ports'
        return None
    c.vlen=vlen
    return c

def encode_ccsds_27_bb():
    c = sb.launch(components_dir+'encode_ccsds_27_bb/encode_ccsds_27_bb.spd.xml', execparams=execparams)
    return c

def endian_swap(sizeof):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'endain_swap_bb/endian_swap_bb.spd.xml', execparams=execparams)
        c.item_size_bytes = 1
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'endian_swap_ss/endian_swap_ss.spd.xml', execparams=execparams)
        c.item_size_bytes = 2
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'endian_swap_ii/endian_swap_ii.spd.xml', execparams=execparams)
        c.item_size_bytes = 4
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'endian_swap_cc/endian_swap_cc.spd.xml', execparams=execparams)
        c.item_size_bytes = 8
    return c

def feedforward_agc_cc(nsamples, reference):
    c = sb.launch(components_dir+'feedforward_agc_cc/feedforward_agc_cc.spd.xml', execparams=execparams)
    c.nsamples = nsamples
    c.reference = reference
    return c

def fft_vcc(fft_size=8192, forward=True, window=[], shift=False, nthreads=1):
    c = sb.launch(components_dir+'fft_vcc/fft_vcc.spd.xml', execparams=execparams)
    c.fft_size = fft_size
    c.forward = forward
    # use below workaround for bug
    # can't set seq prop to empy list
    if len(window) > 0:
        c.window = window
    c.shift = shift
    c.nthreads = nthreads
    return c

def fft_filter_ccc(decimation=1, taps=[],nthreads=1):
    c = sb.launch(components_dir+'fft_filter_ccc/fft_filter_ccc.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.nthreads = nthreads
    return c

def fft_filter_fff(dec, taps, nthreads=1):
    c = sb.launch(components_dir+'fft_filter_fff/fft_filter_fff.spd.xml', execparams=execparams)
    c.decimation = dec
    c.taps = taps
    c.nthreads = nthreads
    return c

def filter_delay_fc(taps, in_ports=1):
    if in_ports == 1:
        c = sb.launch(components_dir+'filter_delay_fc_1i/filter_delay_fc_1i.spd.xml', execparams=execparams)
    elif in_ports == 2:
        c = sb.launch(components_dir+'filter_delay_fc_2i/filter_delay_fc_2i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."

    c.taps = taps
    return c

def fir_filter_ccc(decimation=1, taps=[]):
    c = sb.launch(components_dir+'fir_filter_ccc/fir_filter_ccc.spd.xml', execparams=execparams)
    c.decimation = decimation
    complexTaps=[]
    for i in taps:
        complexTaps.append(complex(i))
    c.taps = complexTaps
    return c

def fir_filter_ccf(decimation=1, taps=[]):
    c = sb.launch(components_dir+'fir_filter_ccf/fir_filter_ccf.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    return c

def fir_filter_fcc(decimation=1, taps=[]):
    c = sb.launch(components_dir+'fir_filter_fcc/fir_filter_fcc.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = list(taps) 
    return c

def fir_filter_fff(decimation=1, taps=[]):
    c = sb.launch(components_dir+'fir_filter_fff/fir_filter_fff.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = list(taps) 
    return c

def fir_filter_fsf(decimation=1, taps=[]):
    c = sb.launch(components_dir+'fir_filter_fsf/fir_filter_fsf.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = list(taps) 
    return c

def fir_filter_scc(decimation=1, taps=[]):
    c = sb.launch(components_dir+'fir_filter_scc/fir_filter_scc.spd.xml', execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    return c

def firdes_hilbert(ntaps=19, window=3, beta=6.76):
    c = sb.launch('./components/firdes/firdes.spd.xml', execparams=execparams)
    c.hilbert_ntaps = ntaps
    c.windowtype = window
    c.beta = beta
    return list(c.hilbert)

def fll_band_edge_cc(samples_per_symbol, rolloff, filter_size, bandwidth):
    c = sb.launch(components_dir+'fll_band_edge_cc_4o/fll_band_edge_cc_4o.spd.xml', execparams=execparams)
    c.samples_per_symbol = samples_per_symbol
    c.rolloff = rolloff
    c.filter_size = filter_size
    c.bandwidth = bandwidth
    return c

def float_to_char(v=1, s=1):
    c = sb.launch(components_dir+'float_to_char/float_to_char.spd.xml', execparams=execparams)
    c.vlen = v
    c.scale = s
    return c

def float_to_complex(v=1, numPorts=2):
    if numPorts == 2:
        c = sb.launch(components_dir+'float_to_complex_2i/float_to_complex_2i.spd.xml', execparams=execparams)
    elif numPorts == 1:
        c = sb.launch(components_dir+'float_to_complex_1i/float_to_complex_1i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports"
        return None
    c.vlen = v
    return c

def float_to_int(v=1, s=1):
    c = sb.launch(components_dir+'float_to_int/float_to_int.spd.xml', execparams=execparams)
    c.vlen = v
    c.scale = s
    return c
    
def float_to_short(v=1, s=1):
    c = sb.launch(components_dir+'float_to_short/float_to_short.spd.xml', execparams=execparams)
    c.vlen = v
    c.scale = s
    return c

def float_to_uchar():
    c = sb.launch(components_dir+'float_to_uchar/float_to_uchar.spd.xml', execparams=execparams)
    return c

def fractional_interpolator_ff(phase_shift=0.0, interp_ratio=1.0):
    c = sb.launch(components_dir+'fractional_interpolator_ff/fractional_interpolator_ff.spd.xml', execparams=execparams)
    c.interp_ratio = interp_ratio
    c.phase_shift = phase_shift
    return c

def fractional_interpolator_cc(phase_shift=0.0, interp_ratio=1.0):
    c = sb.launch(components_dir+'fractional_interpolator_cc/fractional_interpolator_cc.spd.xml', execparams=execparams)
    c.interp_ratio = interp_ratio
    c.phase_shift = phase_shift
    return c

def freq_xlating_fir_filter_ccc(decimation, taps, cf, sf):
    c = sb.launch(components_dir+'freq_xlating_fir_filter_ccc/freq_xlating_fir_filter_ccc.spd.xml',execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.center_freq = cf
    c.sampling_freq = sf
    return c

def freq_xlating_fir_filter_ccf(decimation, taps, cf, sf):
    c = sb.launch(components_dir+'freq_xlating_fir_filter_ccf/freq_xlating_fir_filter_ccf.spd.xml',execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.center_freq = cf
    c.sampling_freq = sf
    return c

def freq_xlating_fir_filter_fcc(decimation, taps, cf, sf):
    c = sb.launch(components_dir+'freq_xlating_fir_filter_fcc/freq_xlating_fir_filter_fcc.spd.xml',execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.center_freq = cf
    c.sampling_freq = sf
    return c

def freq_xlating_fir_filter_fcf(decimation, taps, cf, sf):
    c = sb.launch(components_dir+'freq_xlating_fir_filter_fcf/freq_xlating_fir_filter_fcf.spd.xml',execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.center_freq = cf
    c.sampling_freq = sf
    return c

def freq_xlating_fir_filter_scc(decimation, taps, cf, sf):
    c = sb.launch(components_dir+'freq_xlating_fir_filter_scc/freq_xlating_fir_filter_scc.spd.xml',execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.center_freq = cf
    c.sampling_freq = sf
    return c

def freq_xlating_fir_filter_scf(decimation, taps, cf, sf):
    c = sb.launch(components_dir+'freq_xlating_fir_filter_scf/freq_xlating_fir_filter_scf.spd.xml',execparams=execparams)
    c.decimation = decimation
    c.taps = taps
    c.center_freq = cf
    c.sampling_freq = sf
    return c

def frequency_modulator_fc(sens):
    c = sb.launch(components_dir+'frequency_modulator_fc/frequency_modulator_fc.spd.xml', execparams=execparams)
    c.sensitivity = sens
    return c

def rational_resampler_base_fff(interpolation=1,decimation=1,taps=[]):
    c = sb.launch(components_dir+'rh_gr_rational_resampler_fff/rh_gr_rational_resampler_fff.spd.xml')
    c.interpolation = interpolation
    c.decimation = decimation
    c.taps = list(taps)
    return c

def glfsr_source_b(degree, repeat=True, mask=0, seed=1):
    c = sb.launch(components_dir+'glfsr_source_b/glfsr_source_b.spd.xml', execparams=execparams)
    c.degree = degree
    c.repeat = repeat
    c.mask = mask
    c.seed = seed
    return c

def glfsr_source_f(degree, repeat=True, mask=0, seed=1):
    c = sb.launch(components_dir+'glfsr_source_f/glfsr_source_f.spd.xml', execparams=execparams)
    c.degree = degree
    c.repeat = repeat
    c.mask = mask
    c.seed = seed
    return c

def goertzel_fc(rate, length, freq):
    c = sb.launch(components_dir+'goertzel_fc/goertzel_fc.spd.xml', execparams=execparams)
    c.rate = rate
    c.length = length
    c.frequency = freq
    return c

def head_gnu(size, nitems):
    if size == 1:
        c = sb.launch(components_dir+'head_bb/head_bb.spd.xml', execparams=execparams)
    elif size == 2:
        c = sb.launch(components_dir+'head_ss/head_ss.spd.xml', execparams=execparams)
    elif size == 4:
        c = sb.launch(components_dir+'head_ii/head_ii.spd.xml', execparams=execparams)
    elif size == 8:
        c = sb.launch(components_dir+'head_cc/head_cc.spd.xml', execparams=execparams)
    c.nitems = nitems
    return c

def hilbert_fc(ntaps):
    c = sb.launch(components_dir+'hilbert_fc/hilbert_fc.spd.xml', execparams=execparams)
    c.ntaps = ntaps
    return c

def interleave(sizeof, size):
    if str(sizeof.__name__).find("sizeof_float") != -1:
        if size == 1:
            c = sb.launch(components_dir+'interleave_ff_1i/interleave_ff_1i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 2:
            c = sb.launch(components_dir+'interleave_ff_2i/interleave_ff_2i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 3:
            c = sb.launch(components_dir+'interleave_ff_3i/interleave_ff_3i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 4:
            c = sb.launch(components_dir+'interleave_ff_4i/interleave_ff_4i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        if size == 1:
            c = sb.launch(components_dir+'interleave_ss_1i/interleave_ss_1i.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
        elif size == 2:
            c = sb.launch(components_dir+'interleave_ss_2i/interleave_ss_2i.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
        elif size == 3:
            c = sb.launch(components_dir+'interleave_ss_3i/interleave_ss_3i.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
        elif size == 4:
            c = sb.launch(components_dir+'interleave_ss_4i/interleave_ss_4i.spd.xml', execparams=execparams)
            c.itemsize = 2
            return c
    elif str(sizeof.__name__).find("sizeof_char") != -1:
        if size == 1:
            c = sb.launch(components_dir+'interleave_bb_1i/interleave_bb_1i.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
        elif size == 2:
            c = sb.launch(components_dir+'interleave_bb_2i/interleave_bb_2i.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
        elif size == 3:
            c = sb.launch(components_dir+'interleave_bb_3i/interleave_bb_3i.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
        elif size == 4:
            c = sb.launch(components_dir+'interleave_bb_4i/interleave_bb_4i.spd.xml', execparams=execparams)
            c.itemsize = 1
            return c
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        if size == 1:
            c = sb.launch(components_dir+'interleave_cc_1i/interleave_cc_1i.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
        elif size == 2:
            c = sb.launch(components_dir+'interleave_cc_2i/interleave_cc_2i.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
        elif size == 3:
            c = sb.launch(components_dir+'interleave_cc_3i/interleave_cc_3i.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
        elif size == 4:
            c = sb.launch(components_dir+'interleave_cc_4i/interleave_cc_4i.spd.xml', execparams=execparams)
            c.itemsize = 8
            return c
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        if size == 1:
            c = sb.launch(components_dir+'interleave_ii_1i/interleave_ii_1i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 2:
            c = sb.launch(components_dir+'interleave_ii_2i/interleave_ii_2i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 3:
            c = sb.launch(components_dir+'interleave_ii_3i/interleave_ii_3i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c
        elif size == 4:
            c = sb.launch(components_dir+'interleave_ii_4i/interleave_ii_4i.spd.xml', execparams=execparams)
            c.itemsize = 4
            return c

def kludge_copy(sizeof=4):
    c = sb.launch(components_dir+'kludge_copy_float/kludge_copy_float.spd.xml', execparams=execparams)
    return c

def keep_m_in_n(sizeof, itemsize,_m,_n,_offset):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'keep_m_in_n_bb/keep_m_in_n_bb.spd.xml', execparams=execparams)
        c.item_size = itemsize
        c.m = _m
        c.n = _n
        c.offset = _offset
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'keep_m_in_n_ss/keep_m_in_n_ss.spd.xml', execparams=execparams)
        c.item_size = itemsize
        c.m = _m
        c.n = _n
        c.offset = _offset
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'keep_m_in_n_ii/keep_m_in_n_ii.spd.xml', execparams=execparams)
        c.item_size = itemsize
        c.m = _m
        c.n = _n
        c.offset = _offset
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'keep_m_in_n_cc/keep_m_in_n_cc.spd.xml', execparams=execparams)
        c.item_size = itemsize
        c.m = _m
        c.n = _n
        c.offset = _offset
    return c

def lms_dd_equalizer_cc(num_taps, mu, sps, const):
    c = sb.launch(components_dir+'lms_dd_equalizer_cc/lms_dd_equalizer_cc.spd.xml', execparams=execparams)
    c.num_taps = num_taps
    c.mu = mu
    c.sps = sps
    c.constellation = const
    return c

def iir_filter_ffd(fftaps, fbtaps):
    c = sb.launch(components_dir+'iir_filter_ffd/iir_filter_ffd.spd.xml', execparams=execparams)
    c.fftaps = fftaps
    c.fbtaps = fbtaps
    return c

def int_to_float(vlen=1, scale=1.0):
    c = sb.launch(components_dir+'int_to_float/int_to_float.spd.xml', execparams=execparams)
    c.scale = scale
    c.vlen = 1
    return c

def integrate_cc(decim):
    c = sb.launch(components_dir+'integrate_cc/integrate_cc.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def integrate_ff(decim):
    c = sb.launch(components_dir+'integrate_ff/integrate_ff.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def integrate_ii(decim):
    c = sb.launch(components_dir+'integrate_ii/integrate_ii.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def integrate_ss(decim):
    c = sb.launch(components_dir+'integrate_ss/integrate_ss.spd.xml', execparams=execparams)
    c.decim = decim
    return c

def interp_fir_filter_ccf(interpolation=1,taps=[]):
    c = sb.launch(components_dir+'interp_fir_filter_ccf/interp_fir_filter_ccf.spd.xml', execparams=execparams)
    c.interpolation = interpolation
    c.taps = list(taps)
    return c

def interp_fir_filter_fff(interpolation=1,taps=[]):
    c = sb.launch(components_dir+'interp_fir_filter_fff/interp_fir_filter_fff.spd.xml', execparams=execparams)
    c.interpolation = interpolation
    c.taps = list(taps)
    return c

def keep_m_in_n_bb(item_size=1, m=1, n=1, offset=0):
    c = sb.launch(components_dir+'keep_m_in_n_bb/keep_m_in_n_bb.spd.xml', execparams=execparams) 
    c.item_size=item_size
    c.m=m
    c.n=n
    c.offset = offset
    return c

def map_bb(m=[]):
    c = sb.launch(components_dir+'map_bb/map_bb.spd.xml', execparams=execparams)
    c.map = m
    return c

def max_(sizeof, ninputs, len):
    if str(sizeof.__name__).find("sizeof_float") != -1:
        if (ninputs == 1):
            c = sb.launch(components_dir+'max_ff_1i/max_ff_1i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 2):
            c = sb.launch(components_dir+'max_ff_2i/max_ff_2i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 3):
            c = sb.launch(components_dir+'max_ff_3i/max_ff_3i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 4):
            c = sb.launch(components_dir+'max_ff_4i/max_ff_4i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
    if str(sizeof.__name__).find("sizeof_short") != -1:
        if (ninputs == 1):
            c = sb.launch(components_dir+'max_ss_1i/max_ss_1i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 2):
            c = sb.launch(components_dir+'max_ss_2i/max_ss_2i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 3):
            c = sb.launch(components_dir+'max_ss_3i/max_ss_3i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 4):
            c = sb.launch(components_dir+'max_ss_4i/max_ss_4i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
    if str(sizeof.__name__).find("sizeof_int") != -1:
        if (ninputs == 1):
            c = sb.launch(components_dir+'max_ii_1i/max_ii_1i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 2):
            c = sb.launch(components_dir+'max_ii_2i/max_ii_2i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 3):
            c = sb.launch(components_dir+'max_ii_3i/max_ii_3i.spd.xml', execparams=execparams)
            c.vlen = len
            return c
        if (ninputs == 4):
            c = sb.launch(components_dir+'max_ii_4i/max_ii_4i.spd.xml', execparams=execparams)
            c.vlen = len
            return c

def mpsk_receiver_cc(M, theta, loop_bw, fmin, fmax, mu, gain_mu, omega, gain_omega, omega_rel):
    c = sb.launch(components_dir+'mpsk_receiver_cc/mpsk_receiver_cc.spd.xml', execparams=execparams)
    c.M = M
    c.theta = theta
    c.loop_bw = loop_bw
    c.fmin = fmin
    c.fmax = fmax
    c.mu = mu
    c.gain_mu = gain_mu
    c.omega = omega
    c.gain_omega = gain_omega
    c.omega_rel = omega_rel
    return c

def mpsk_snr_est_cc(t, tag_nsamples=10000, alpha=0.001):
    c = sb.launch(components_dir+'mpsk_snr_est_cc/mpsk_snr_est_cc.spd.xml', execparams=execparams)
    c.type = t
    c.tag_nsamples = tag_nsamples
    c.alpha = alpha
    return c

def multiply_cc(vlen=1):
    c = sb.launch(components_dir+'multiply_cc_2i/multiply_cc_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_conjugate_cc(vlen=1):
    c = sb.launch(components_dir+'multiply_conjugate_cc/multiply_conjugate_cc.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_const_cc(k):
    c = sb.launch(components_dir+'multiply_const_cc/multiply_const_cc.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_ii(k):
    c = sb.launch(components_dir+'multiply_const_ii/multiply_const_ii.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_ff(k):
    c = sb.launch(components_dir+'multiply_const_ff/multiply_const_ff.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_ss(k):
    c = sb.launch(components_dir+'multiply_const_ss/multiply_const_ss.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_vcc(k):
    c = sb.launch(components_dir+'multiply_const_vcc/multiply_const_vcc.spd.xml', execparams=execparams)
    c.k = k
    return c

def multiply_const_vff(k):
    c = sb.launch(components_dir+'multiply_const_vff/multiply_const_vff.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def multiply_const_vii(k):
    c = sb.launch(components_dir+'multiply_const_vii/multiply_const_vii.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def multiply_const_vss(k):
    c = sb.launch(components_dir+'multiply_const_vss/multiply_const_vss.spd.xml', execparams=execparams)
    c.k = list(k)
    return c

def multiply_ff(vlen=1):
    c = sb.launch(components_dir+'multiply_ff_2i/multiply_ff_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_ii(vlen=1):
    c = sb.launch(components_dir+'multiply_ii_2i/multiply_ii_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def multiply_ss(vlen=1):
    c = sb.launch(components_dir+'multiply_ss_2i/multiply_ss_2i.spd.xml', execparams=execparams)
    c.vlen = vlen
    return c

def mute_cc(m=False):
    c = sb.launch(components_dir+'mute_cc/mute_cc.spd.xml')
    c.mute = m
    return c

def mute_ii(m=False):
    c = sb.launch(components_dir+'mute_ii/mute_ii.spd.xml')
    c.mute = m
    return c

def nlog10_ff(n=1.0, vlen=1 , k=0):
    c = sb.launch(components_dir+'nlog10_ff/nlog10_ff.spd.xml')
    c.n = n
    c.vlen = vlen
    c.k = k
    return c

def noise_source_f(type_, ampl, seed=0):
    c = sb.launch(components_dir+'noise_source_f/noise_source_f.spd.xml', execparams=execparams)
    c.type = type_
    c.amplitude = ampl
    c.seed = seed
    return c

def not_bb():
    c = sb.launch(components_dir+'not_bb/not_bb.spd.xml', execparams=execparams)
    return c

def not_ii():
    c = sb.launch(components_dir+'not_ii/not_ii.spd.xml', execparams=execparams)
    return c

def not_ss():
    c = sb.launch(components_dir+'not_ss/not_ss.spd.xml', execparams=execparams)
    return c

def null_sink(sizeof=4):
    c = sb.DataSink()
    return c

def or_bb(num_ports=2):
    if num_ports == 2:
        c = sb.launch(components_dir+'or_bb_2i/or_bb_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'or_bb_3i/or_bb_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'or_bb_4i/or_bb_4i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."
        return None
    return c

def or_ii(num_ports=2):
    if num_ports == 2:
        c = sb.launch(components_dir+'or_ii_2i/or_ii_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'or_ii_3i/or_ii_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'or_ii_4i/or_ii_4i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."
        return None
    return c

def or_ss(num_ports=2):
    if num_ports == 2:
        c = sb.launch(components_dir+'or_ss_2i/or_ss_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'or_ss_3i/or_ss_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'or_ss_4i/or_ss_4i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."
        return None
    return c

def pack_k_bits_bb(k):
    c = sb.launch(components_dir+'pack_k_bits_bb/pack_k_bits_bb.spd.xml', execparams = execparams)
    c.k = k
    return c

def packed_to_unpacked_bb(bits_per_chunk, endianness=1):
    c = sb.launch(components_dir+'packed_to_unpacked_bb/packed_to_unpacked_bb.spd.xml', execparams=execparams)
    c.bits_per_chunk = bits_per_chunk
    c.endianness = endianness
    return c

def packed_to_unpacked_ss(bits_per_chunk, endianness=1):
    c = sb.launch(components_dir+'packed_to_unpacked_ss/packed_to_unpacked_ss.spd.xml', execparams=execparams)
    c.bits_per_chunk = bits_per_chunk
    c.endianness = endianness
    return c

def packed_to_unpacked_ii(bits_per_chunk, endianness=1):
    c = sb.launch(components_dir+'packed_to_unpacked_ii/packed_to_unpacked_ii.spd.xml', execparams=execparams)
    c.bits_per_chunk = bits_per_chunk
    c.endianness = endianness
    return c

def pfb_arb_resampler_ccf(rate=1,taps=[],filter_size=32):
    c = sb.launch(components_dir+"pfb_arb_resampler_ccf/pfb_arb_resampler_ccf.spd.xml")
    c.rate= rate
    c.taps = list(taps)
    c.filter_size = filter_size
    return c

def pfb_arb_resampler_fff(rate=1,taps=[],filter_size=32):
    c = sb.launch(components_dir+"pfb_arb_resampler_fff/pfb_arb_resampler_fff.spd.xml")
    c.rate= rate
    c.taps = list(taps)
    c.filter_size = filter_size
    return c

def pfb_clock_sync_ccf(sps, loop_bw, taps, filter_size=32, init_phase=0, max_rate_deviation=1.5, osps=1):
    c = sb.launch(components_dir+'pfb_clock_sync_ccf_4o/pfb_clock_sync_ccf_4o.spd.xml', execparams=execparams)
    c.sps = sps
    c.loop_bandwidth = loop_bw
    c.taps = list(taps)
    c.filter_size = filter_size
    c.init_phase = init_phase
    c.max_rate_deviation = max_rate_deviation
    c.osps = osps
    return c

def pfb_clock_sync_fff(sps, gain, taps, filter_size=32, init_phase=0, max_rate_deviation=1.5, osps=1):
    c = sb.launch(components_dir+'pfb_clock_sync_fff_4o/pfb_clock_sync_fff_4o.spd.xml', execparams=execparams)
    c.sps = sps
    c.gain = gain
    c.taps = taps
    c.filter_size = filter_size
    c.init_phase = init_phase
    c.max_rate_deviation = max_rate_deviation
    c.osps = osps
    return c

def pfb_channelizer_ccf(nchans=5,taps=[],osrate=1):
    if nchans == 5:
        c = sb.launch(components_dir+"pfb_channelizer_ccf_5i5o/pfb_channelizer_ccf_5i5o.spd.xml", execparams=execparams)
    else:
        print "Invalid number of input channels"
        return None
    c.taps = taps
    c.oversample_rate=osrate
    return c

def pfb_interpolator_ccf(interp, taps):
    c = sb.launch(components_dir+'pfb_interpolator_ccf/pfb_interpolator_ccf.spd.xml', execparams=execparams)
    c.interp = interp
    c.taps = taps
    return c

def pfb_decimator_ccf(m, tap, chan):
    if m == 5:
        c = sb.launch(components_dir+'pfb_decimator_ccf_5i/pfb_decimator_ccf_5i.spd.xml',execparams=execparams)
    else:
        print "Invalid number of input ports"
        return None
    c.taps = tap
    c.channel = chan
    return c

def pfb_synthesizer_ccf(numchans, taps, twox=False):
    if numchans == 5:
        c = sb.launch(components_dir+'pfb_synthesizer_ccf_5i/pfb_synthesizer_ccf_5i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports"
        return None
    c.taps = taps
    c.twox = twox
    return c

def pll_carriertracking_cc(l, max_f, min_f):
    c = sb.launch(components_dir+'pll_carriertracking_cc/pll_carriertracking_cc.spd.xml', execparams=execparams)
    c.loop_bw = l
    c.max_freq = max_f
    c.min_freq = min_f
    return c

def pll_freqdet_cf(l, max_f, min_f):
    c = sb.launch(components_dir+'pll_freqdet_cf/pll_freqdet_cf.spd.xml', execparams=execparams)
    c.loop_bw = l
    c.max_freq = max_f
    c.min_freq = min_f
    return c

def pll_refout_cc(l, max_f, min_f):
    c = sb.launch(components_dir+'pll_refout_cc/pll_refout_cc.spd.xml', execparams=execparams)
    c.loop_bw = l
    c.max_freq = max_f
    c.min_freq = min_f
    return c

def pn_correlator_cc(degree, mask=0, seed=1):
    c = sb.launch(components_dir+'pn_correlator_cc/pn_correlator_cc.spd.xml', execparams=execparams)
    c.degree = degree
    c.mask = mask
    c.seed = seed
    return c

def probe_density_b(alpha):
    c = sb.launch(components_dir+'probe_density_b/probe_density_b.spd.xml', execparams=execparams)
    c.alpha = alpha
    return c

def probe_mpsk_snr_est_c(t, msg_nsamples=1000, alpha=0.001):
    c = sb.launch(components_dir+'probe_mpsk_snr_est_c/probe_mpsk_snr_est_c.spd.xml', execparams=execparams)
    c.type = t
    c.msg_nsamples = msg_nsamples
    c.alpha = alpha
    return c

def probe_signal_b():
    c = sb.launch(components_dir+'probe_signal_b/probe_signal_b.spd.xml', execparams=execparams)
    return c

def probe_signal_c():
    c = sb.launch(components_dir+'probe_signal_c/probe_signal_c.spd.xml', execparams=execparams)
    return c

def probe_signal_f():
    c = sb.launch(components_dir+'probe_signal_f/probe_signal_f.spd.xml', execparams=execparams)
    return c

def probe_signal_i():
    c = sb.launch(components_dir+'probe_signal_i/probe_signal_i.spd.xml', execparams=execparams)
    return c

def probe_signal_s():
    c = sb.launch(components_dir+'probe_signal_s/probe_signal_s.spd.xml', execparams=execparams)
    return c

def probe_signal_vb(size):
    c = sb.launch(components_dir+'probe_signal_vb/probe_signal_vb.spd.xml', execparams=execparams)
    c.size = size
    return c

def probe_signal_vc(size):
    c = sb.launch(components_dir+'probe_signal_vc/probe_signal_vc.spd.xml', execparams=execparams)
    c.size = size
    return c

def probe_signal_vf(size):
    c = sb.launch(components_dir+'probe_signal_vf/probe_signal_vf.spd.xml', execparams=execparams)
    c.size = size
    return c

def probe_signal_vi(size):
    c = sb.launch(components_dir+'probe_signal_vi/probe_signal_vi.spd.xml', execparams=execparams)
    c.size = size
    return c

def probe_signal_vs(size):
    c = sb.launch(components_dir+'probe_signal_vs/probe_signal_vs.spd.xml', execparams=execparams)
    c.size = size
    return c

def rail_ff(lo, hi):
    c = sb.launch(components_dir+'rail_ff/rail_ff.spd.xml', execparams=execparams)
    c.lo = lo
    c.hi = hi
    return c

def regenerate_bb(p, m=500):
    c = sb.launch(components_dir+'regenerate_bb/regenerate_bb.spd.xml', execparams=execparams)
    c.period = p
    c.max_regen = m
    return c

def repeat(sizeof, interp_cnt):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'repeat_bb/repeat_bb.spd.xml', execparams=execparams)
        c.itemsize = 1
        c.interp = interp_cnt
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'repeat_ss/repeat_ss.spd.xml', execparams=execparams)
        c.itemsize = 2
        c.interp = interp_cnt
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'repeat_ii/repeat_ii.spd.xml', execparams=execparams)
        c.itemsize = 4
        c.interp = interp_cnt
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'repeat_cc/repeat_cc.spd.xml', execparams=execparams)
        c.itemsize = 8
        c.interp = interp_cnt
    return c

def sample_and_hold_bb():
    c = sb.launch(components_dir+'sample_and_hold_bb/sample_and_hold_bb.spd.xml', execparams=execparams)
    return c

def sample_and_hold_ff():
    c = sb.launch(components_dir+'sample_and_hold_ff/sample_and_hold_ff.spd.xml', execparams=execparams)
    return c

def sample_and_hold_ii():
    c = sb.launch(components_dir+'sample_and_hold_ii/sample_and_hold_ii.spd.xml', execparams=execparams)
    return c

def sample_and_hold_ss():
    c = sb.launch(components_dir+'sample_and_hold_ss/sample_and_hold_ss.spd.xml', execparams=execparams)
    return c

def scrambler_bb(m, s, l):
    c = sb.launch(components_dir+'scrambler_bb/scrambler_bb.spd.xml', execparams=execparams)
    c.mask = m
    c.seed = s
    c.len = l
    return c

def short_to_char(vlen=1):
    c = sb.launch(components_dir+'short_to_char/short_to_char.spd.xml', execparams=execparams)
    c.vlen=vlen
    return c

def short_to_float(vlen=1, scale=1.0):
    c = sb.launch(components_dir+'short_to_float/short_to_float.spd.xml', execparams=execparams)
    c.scale = scale
    c.vlen = 1
    return c

def sig_source_c(sampling_freq=10000.0, waveform=101, frequency=10.0, amplitude=1000.0, offset=0.0):
    c = sb.launch(components_dir+'sig_source_c/sig_source_c.spd.xml', execparams=execparams)
    c.waveform = waveform
    c.frequency = frequency
    c.sampling_freq = sampling_freq
    c.amplitude = amplitude
    if sampling_freq <= 250000.0:
        c.transfer_size = long(sampling_freq+1)
    else:
        c.transfer_size = long(250000)
    c.offset = offset
    return c

def sig_source_i(sampling_freq=10000.0, waveform=101, frequency=10.0, amplitude=1000.0, offset=0):
    c = sb.launch(components_dir+'sig_source_i/sig_source_i.spd.xml', execparams=execparams)
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
    c = sb.launch(components_dir+'sig_source_f/sig_source_f.spd.xml', execparams=execparams)
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

def sig_source_s(sampling_freq=10000.0, waveform=101, frequency=10.0, amplitude=100.0, offset=0.0):
    c = sb.launch(components_dir+'sig_source_s/sig_source_s.spd.xml', execparams=execparams)
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

def simple_framer(payload_bytesize):
    c = sb.launch(components_dir+'simple_framer/simple_framer.spd.xml', execparams=execparams)
    c.payload_bytesize = payload_bytesize
    return c

def single_pole_iir_filter_cc(alpha, block_size=1):
    c = sb.launch(components_dir+'single_pole_iir_filter_cc/single_pole_iir_filter_cc.spd.xml', execparams=execparams)
    c.alpha = alpha
    c.vlen = block_size
    return c

def single_pole_iir_filter_ff(alpha, block_size=1):
    c = sb.launch(components_dir+'single_pole_iir_filter_ff/single_pole_iir_filter_ff.spd.xml', execparams=execparams)
    c.alpha = alpha
    c.vlen = block_size
    return c

def skiphead(sizeof, skip_cnt):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'skiphead_bb/skiphead_bb.spd.xml', execparams=execparams)
        c.itemsize = 1
        c.nitems_to_skip = skip_cnt
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'skiphead_ss/skiphead_ss.spd.xml', execparams=execparams)
        c.itemsize = 2
        c.nitems_to_skip = skip_cnt
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'skiphead_ii/skiphead_ii.spd.xml', execparams=execparams)
        c.itemsize = 4
        c.nitems_to_skip = skip_cnt
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'skiphead_cc/skiphead_cc.spd.xml', execparams=execparams)
        c.itemsize = 8
        c.nitems_to_skip = skip_cnt
    return c

def squash_ff(igrid=[], ogrid=[]):
    c = sb.launch(components_dir+'squash_ff/squash_ff.spd.xml', execparams=execparams)
    c.igrid = igrid
    c.ogrid = ogrid
    return c

def stream_mux(sizeof, nstreams):
    if str(sizeof.__name__).find("sizeof_float") != -1:
        c = sb.launch(components_dir+'stream_mux_ff_1i/stream_mux_ff_1i.spd.xml', execparams=execparams)
        c.itemsize = 4
    return c

def stream_to_streams_comp(sizeof, nstreams):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'stream_to_streams_bb_1o/stream_to_streams_bb_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'stream_to_streams_bb_2o/stream_to_streams_bb_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'stream_to_streams_bb_3o/stream_to_streams_bb_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'stream_to_streams_bb_4o/stream_to_streams_bb_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'stream_to_streams_cc_1o/stream_to_streams_cc_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'stream_to_streams_cc_2o/stream_to_streams_cc_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'stream_to_streams_cc_3o/stream_to_streams_cc_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'stream_to_streams_cc_4o/stream_to_streams_cc_4o.spd.xml', execparams=execparams)
        elif nstreams == 5:
            c = sb.launch(components_dir+'stream_to_streams_cc_5o/stream_to_streams_cc_5o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_float") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'stream_to_streams_ff_1o/stream_to_streams_ff_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'stream_to_streams_ff_2o/stream_to_streams_ff_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'stream_to_streams_ff_3o/stream_to_streams_ff_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'stream_to_streams_ff_4o/stream_to_streams_ff_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'stream_to_streams_ii_1o/stream_to_streams_ii_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'stream_to_streams_ii_2o/stream_to_streams_ii_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'stream_to_streams_ii_3o/stream_to_streams_ii_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'stream_to_streams_ii_4o/stream_to_streams_ii_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'stream_to_streams_ss_1o/stream_to_streams_ss_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'stream_to_streams_ss_2o/stream_to_streams_ss_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'stream_to_streams_ss_3o/stream_to_streams_ss_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'stream_to_streams_ss_4o/stream_to_streams_ss_4o.spd.xml', execparams=execparams)
    return c

def streams_to_stream_comp(sizeof, nstreams):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_stream_bb_1i/streams_to_stream_bb_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_stream_bb_2i/streams_to_stream_bb_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_stream_bb_3i/streams_to_stream_bb_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_stream_bb_4i/streams_to_stream_bb_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_stream_cc_1i/streams_to_stream_cc_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_stream_cc_2i/streams_to_stream_cc_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_stream_cc_3i/streams_to_stream_cc_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_stream_cc_4i/streams_to_stream_cc_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_float") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_stream_ff_1i/streams_to_stream_ff_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_stream_ff_2i/streams_to_stream_ff_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_stream_ff_3i/streams_to_stream_ff_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_stream_ff_4i/streams_to_stream_ff_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_stream_ii_1i/streams_to_stream_ii_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_stream_ii_2i/streams_to_stream_ii_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_stream_ii_3i/streams_to_stream_ii_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_stream_ii_4i/streams_to_stream_ii_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_stream_ss_1i/streams_to_stream_ss_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_stream_ss_2i/streams_to_stream_ss_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_stream_ss_3i/streams_to_stream_ss_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_stream_ss_4i/streams_to_stream_ss_4i.spd.xml', execparams=execparams)
    return c

def streams_to_vector_comp(sizeof, nstreams):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_vector_bb_1i/streams_to_vector_bb_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_vector_bb_2i/streams_to_vector_bb_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_vector_bb_3i/streams_to_vector_bb_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_vector_bb_4i/streams_to_vector_bb_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_vector_cc_1i/streams_to_vector_cc_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_vector_cc_2i/streams_to_vector_cc_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_vector_cc_3i/streams_to_vector_cc_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_vector_cc_4i/streams_to_vector_cc_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_float") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_vector_ff_1i/streams_to_vector_ff_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_vector_ff_2i/streams_to_vector_ff_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_vector_ff_3i/streams_to_vector_ff_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_vector_ff_4i/streams_to_vector_ff_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_vector_ii_1i/streams_to_vector_ii_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_vector_ii_2i/streams_to_vector_ii_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_vector_ii_3i/streams_to_vector_ii_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_vector_ii_4i/streams_to_vector_ii_4i.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'streams_to_vector_ss_1i/streams_to_vector_ss_1i.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'streams_to_vector_ss_2i/streams_to_vector_ss_2i.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'streams_to_vector_ss_3i/streams_to_vector_ss_3i.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'streams_to_vector_ss_4i/streams_to_vector_ss_4i.spd.xml', execparams=execparams)
    return c

def stream_to_vector_comp(sizeof, nitems):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'stream_to_vector_bb/stream_to_vector_bb.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'stream_to_vector_cc/stream_to_vector_cc.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_float") != -1:
        c = sb.launch(components_dir+'stream_to_vector_ff/stream_to_vector_ff.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'stream_to_vector_ii/stream_to_vector_ii.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'stream_to_vector_ss/stream_to_vector_ss.spd.xml', execparams=execparams)
    c.nitems_per_block = nitems
    return c



def stretch_ff(lo, vlen):
    c = sb.launch(components_dir+'stretch_ff/stretch_ff.spd.xml', execparams=execparams)
    c.lo = lo
    c.vlen = vlen
    return c

def sub_cc(num_ports, vlen=1):
    if num_ports == 1:
        c = sb.launch(components_dir+'sub_cc_1i/sub_cc_1i.spd.xml', execparams=execparams)
    elif num_ports == 2:
        c = sb.launch(components_dir+'sub_cc_2i/sub_cc_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'sub_cc_3i/sub_cc_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'sub_cc_4i/sub_cc_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of ports'
        return None
    c.vlen = vlen
    return c

def sub_ff(num_ports, vlen=1):
    if num_ports == 1:
        c = sb.launch(components_dir+'sub_ff_1i/sub_ff_1i.spd.xml', execparams=execparams)
    elif num_ports == 2:
        c = sb.launch(components_dir+'sub_ff_2i/sub_ff_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'sub_ff_3i/sub_ff_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'sub_ff_4i/sub_ff_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of ports'
        return None
    c.vlen = vlen
    return c

def sub_ii(num_ports, vlen=1):
    if num_ports == 1:
        c = sb.launch(components_dir+'sub_ii_1i/sub_ii_1i.spd.xml', execparams=execparams)
    elif num_ports == 2:
        c = sb.launch(components_dir+'sub_ii_2i/sub_ii_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'sub_ii_3i/sub_ii_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'sub_ii_4i/sub_ii_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of ports'
        return None
    c.vlen = vlen
    return c

def sub_ss(num_ports, vlen=1):
    if num_ports == 1:
        c = sb.launch(components_dir+'sub_ss_1i/sub_ss_1i.spd.xml', execparams=execparams)
    elif num_ports == 2:
        c = sb.launch(components_dir+'sub_ss_2i/sub_ss_2i.spd.xml', execparams=execparams)
    elif num_ports == 3:
        c = sb.launch(components_dir+'sub_ss_3i/sub_ss_3i.spd.xml', execparams=execparams)
    elif num_ports == 4:
        c = sb.launch(components_dir+'sub_ss_4i/sub_ss_4i.spd.xml', execparams=execparams)
    else:
        print 'Invalid number of ports'
        return None
    c.vlen = vlen
    return c

def transcendental(name, t='float'):
    if t == 'float':
        c = sb.launch(components_dir+'transcendental_ff/transcendental_ff.spd.xml', execparams=execparams)
    elif t == 'complex_float':
        c = sb.launch(components_dir+'transcendental_cc/transcendental_cc.spd.xml', execparams=execparams)
    else:
        print "Invalid type."
        return None
    c.name = name
    return c

def uchar_to_float():
    c = sb.launch(components_dir+'uchar_to_float/uchar_to_float.spd.xml', execparams=execparams)
    return c

def udp_sink(sizeof, h, p):
    c = sb.launch(components_dir+'udp_sink_f/udp_sink_f.spd.xml')
    c.itemsize = 4
   # c.host = h
    c.port = p
    return c

def udp_source (sizeof, h, p, e):
    c = sb.launch(components_dir+'udp_source_f/udp_source_f.spd.xml')
    c.itemsize =4
   # c.host = h
    c.port = p
    c.eof = e
    return c

def unpack_k_bits_bb(k):
    c = sb.launch(components_dir+'unpack_k_bits_bb/unpack_k_bits_bb.spd.xml')
    c.k = k
    return c

def unpacked_to_packed_bb(bits_per_chunk, endianness=1):
    c = sb.launch(components_dir+'unpacked_to_packed_bb/unpacked_to_packed_bb.spd.xml', execparams=execparams)
    c.bits_per_chunk = bits_per_chunk
    c.endianness = endianness
    return c

def unpacked_to_packed_ss(bits_per_chunk, endianness=1):
    c = sb.launch(components_dir+'unpacked_to_packed_ss/unpacked_to_packed_ss.spd.xml', execparams=execparams)
    c.bits_per_chunk = bits_per_chunk
    c.endianness = endianness
    return c

def unpacked_to_packed_ii(bits_per_chunk, endianness=1):
    c = sb.launch(components_dir+'unpacked_to_packed_ii/unpacked_to_packed_ii.spd.xml', execparams=execparams)
    c.bits_per_chunk = bits_per_chunk
    c.endianness = endianness
    return c

def vector_insert_b(data, periodicity, offset=0, rewind=False):
    c = sb.launch(components_dir+'vector_insert_b/vector_insert_b.spd.xml', execparams=execparams)
    c.data = data
    c.periodicity = periodicity
    c.offset = offset
    c.rewind = rewind
    return c

def vector_to_stream_comp(sizeof, nitems):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        c = sb.launch(components_dir+'vector_to_stream_bb/vector_to_stream_bb.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        c = sb.launch(components_dir+'vector_to_stream_cc/vector_to_stream_cc.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_float") != -1:
        c = sb.launch(components_dir+'vector_to_stream_ff/vector_to_stream_ff.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        c = sb.launch(components_dir+'vector_to_stream_ii/vector_to_stream_ii.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        c = sb.launch(components_dir+'vector_to_stream_ss/vector_to_stream_ss.spd.xml', execparams=execparams)
    c.nitems_per_block = nitems
    return c

def vector_to_streams_comp(sizeof, nstreams):
    if str(sizeof.__name__).find("sizeof_char") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'vector_to_streams_bb_1o/vector_to_streams_bb_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'vector_to_streams_bb_2o/vector_to_streams_bb_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'vector_to_streams_bb_3o/vector_to_streams_bb_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'vector_to_streams_bb_4o/vector_to_streams_bb_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_gr_complex") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'vector_to_streams_cc_1o/vector_to_streams_cc_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'vector_to_streams_cc_2o/vector_to_streams_cc_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'vector_to_streams_cc_3o/vector_to_streams_cc_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'vector_to_streams_cc_4o/vector_to_streams_cc_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_float") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'vector_to_streams_ff_1o/vector_to_streams_ff_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'vector_to_streams_ff_2o/vector_to_streams_ff_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'vector_to_streams_ff_3o/vector_to_streams_ff_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'vector_to_streams_ff_4o/vector_to_streams_ff_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_int") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'vector_to_streams_ii_1o/vector_to_streams_ii_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'vector_to_streams_ii_2o/vector_to_streams_ii_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'vector_to_streams_ii_3o/vector_to_streams_ii_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'vector_to_streams_ii_4o/vector_to_streams_ii_4o.spd.xml', execparams=execparams)
    elif str(sizeof.__name__).find("sizeof_short") != -1:
        if nstreams == 1:
            c = sb.launch(components_dir+'vector_to_streams_ss_1o/vector_to_streams_ss_1o.spd.xml', execparams=execparams)
        elif nstreams == 2:
            c = sb.launch(components_dir+'vector_to_streams_ss_2o/vector_to_streams_ss_2o.spd.xml', execparams=execparams)
        elif nstreams == 3:
            c = sb.launch(components_dir+'vector_to_streams_ss_3o/vector_to_streams_ss_3o.spd.xml', execparams=execparams)
        elif nstreams == 4:
            c = sb.launch(components_dir+'vector_to_streams_ss_4o/vector_to_streams_ss_4o.spd.xml', execparams=execparams)
    return c

def wavfile_sink(outfile, channels, samp_rate, bits_per_samp):
    c = sb.launch(components_dir+'wavfile_sink_f_1i/wavfile_sink_f_1i.spd.xml')
    c.filename = outfile
    c.n_channels = channels
    c.sample_rate = samp_rate
    c.bits_per_sample = bits_per_samp
    return c

def wavfile_source(infile):
    c = sb.launch(components_dir+'wavfile_source_f_1o/wavfile_source_f_1o.spd.xml',execparams=execparams)
    c.filename = infile
    return c

def wvps_ff(ilen):
    c = sb.launch(components_dir+'wvps_ff/wvps_ff.spd.xml', execparams=execparams)
    c.ilen = ilen
    return c

def xor_bb(num_ports=2):
    if num_ports == 2:
        c = sb.launch(components_dir+'xor_bb_2i/xor_bb_2i.spd.xml', execparams=execparams)
    elif  num_ports == 3:
        c = sb.launch(components_dir+'xor_bb_3i/xor_bb_3i.spd.xml', execparams=execparams)
    elif  num_ports == 4:
        c = sb.launch(components_dir+'xor_bb_4i/xor_bb_4i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."
        return None
    return c

def xor_ii(num_ports=2):
    if num_ports == 2:
        c = sb.launch(components_dir+'xor_ii_2i/xor_ii_2i.spd.xml', execparams=execparams)
    elif  num_ports == 3:
        c = sb.launch(components_dir+'xor_ii_3i/xor_ii_3i.spd.xml', execparams=execparams)
    elif  num_ports == 4:
        c = sb.launch(components_dir+'xor_ii_4i/xor_ii_4i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."
        return None
    return c

def xor_ss(num_ports=2):
    if num_ports == 2:
        c = sb.launch(components_dir+'xor_ss_2i/xor_ss_2i.spd.xml', execparams=execparams)
    elif  num_ports == 3:
        c = sb.launch(components_dir+'xor_ss_3i/xor_ss_3i.spd.xml', execparams=execparams)
    elif  num_ports == 4:
        c = sb.launch(components_dir+'xor_ss_4i/xor_ss_4i.spd.xml', execparams=execparams)
    else:
        print "Invalid number of ports."
        return None
    return c
