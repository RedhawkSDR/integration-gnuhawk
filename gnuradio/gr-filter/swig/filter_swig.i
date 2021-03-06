/* -*- c++ -*- */
/*
 * Copyright 2012 Free Software Foundation, Inc.
 *
 * This file is part of GNU Radio
 *
 * GNU Radio is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3, or (at your option)
 * any later version.
 *
 * GNU Radio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GNU Radio; see the file COPYING.  If not, write to
 * the Free Software Foundation, Inc., 51 Franklin Street,
 * Boston, MA 02110-1301, USA.
 */

#define FILTER_API

%include "gnuradio.i"

//load generated python docstrings
%include "gr_filter_swig_doc.i"

%{
#include "filter/firdes.h"
#include "filter/pm_remez.h"
#include "filter/adaptive_fir_ccc.h"
#include "filter/adaptive_fir_ccf.h"
#include "filter/dc_blocker_cc.h"
#include "filter/dc_blocker_ff.h"
#include "filter/filter_delay_fc.h"
#include "filter/fir_filter_ccc.h"
#include "filter/fir_filter_ccf.h"
#include "filter/fir_filter_fcc.h"
#include "filter/fir_filter_fff.h"
#include "filter/fir_filter_fsf.h"
#include "filter/fir_filter_scc.h"
#include "filter/fft_filter_ccc.h"
#include "filter/fft_filter_fff.h"
#include "filter/fractional_interpolator_cc.h"
#include "filter/fractional_interpolator_ff.h"
#include "filter/freq_xlating_fir_filter_ccc.h"
#include "filter/freq_xlating_fir_filter_ccf.h"
#include "filter/freq_xlating_fir_filter_fcc.h"
#include "filter/freq_xlating_fir_filter_fcf.h"
#include "filter/freq_xlating_fir_filter_scf.h"
#include "filter/freq_xlating_fir_filter_scc.h"
#include "filter/hilbert_fc.h"
#include "filter/iir_filter_ffd.h"
#include "filter/interp_fir_filter_ccc.h"
#include "filter/interp_fir_filter_ccf.h"
#include "filter/interp_fir_filter_fcc.h"
#include "filter/interp_fir_filter_fff.h"
#include "filter/interp_fir_filter_fsf.h"
#include "filter/interp_fir_filter_scc.h"
#include "filter/pfb_arb_resampler_ccf.h"
#include "filter/pfb_arb_resampler_fff.h"
#include "filter/pfb_channelizer_ccf.h"
#include "filter/pfb_decimator_ccf.h"
#include "filter/pfb_interpolator_ccf.h"
#include "filter/pfb_synthesizer_ccf.h"
#include "filter/rational_resampler_base_ccc.h"
#include "filter/rational_resampler_base_ccf.h"
#include "filter/rational_resampler_base_fcc.h"
#include "filter/rational_resampler_base_fff.h"
#include "filter/rational_resampler_base_fsf.h"
#include "filter/rational_resampler_base_scc.h"
#include "filter/single_pole_iir_filter_cc.h"
#include "filter/single_pole_iir_filter_ff.h"
#include "filter/channel_model.h"
%}

%include "filter/firdes.h"
%include "filter/pm_remez.h"
%include "filter/adaptive_fir_ccc.h"
%include "filter/adaptive_fir_ccf.h"
%include "filter/dc_blocker_cc.h"
%include "filter/dc_blocker_ff.h"
%include "filter/filter_delay_fc.h"
%include "filter/fir_filter_ccc.h"
%include "filter/fir_filter_ccf.h"
%include "filter/fir_filter_fcc.h"
%include "filter/fir_filter_fff.h"
%include "filter/fir_filter_fsf.h"
%include "filter/fir_filter_scc.h"
%include "filter/fft_filter_ccc.h"
%include "filter/fft_filter_fff.h"
%include "filter/fractional_interpolator_cc.h"
%include "filter/fractional_interpolator_ff.h"
%include "filter/freq_xlating_fir_filter_ccc.h"
%include "filter/freq_xlating_fir_filter_ccf.h"
%include "filter/freq_xlating_fir_filter_fcc.h"
%include "filter/freq_xlating_fir_filter_fcf.h"
%include "filter/freq_xlating_fir_filter_scf.h"
%include "filter/freq_xlating_fir_filter_scc.h"
%include "filter/hilbert_fc.h"
%include "filter/iir_filter_ffd.h"
%include "filter/interp_fir_filter_ccc.h"
%include "filter/interp_fir_filter_ccf.h"
%include "filter/interp_fir_filter_fcc.h"
%include "filter/interp_fir_filter_fff.h"
%include "filter/interp_fir_filter_fsf.h"
%include "filter/interp_fir_filter_scc.h"
%include "filter/pfb_arb_resampler_ccf.h"
%include "filter/pfb_arb_resampler_fff.h"
%include "filter/pfb_channelizer_ccf.h"
%include "filter/pfb_decimator_ccf.h"
%include "filter/pfb_interpolator_ccf.h"
%include "filter/pfb_synthesizer_ccf.h"
%include "filter/rational_resampler_base_ccc.h"
%include "filter/rational_resampler_base_ccf.h"
%include "filter/rational_resampler_base_fcc.h"
%include "filter/rational_resampler_base_fff.h"
%include "filter/rational_resampler_base_fsf.h"
%include "filter/rational_resampler_base_scc.h"
%include "filter/single_pole_iir_filter_cc.h"
%include "filter/single_pole_iir_filter_ff.h"
%include "filter/channel_model.h"

GR_SWIG_BLOCK_MAGIC2(filter, adaptive_fir_ccc);
GR_SWIG_BLOCK_MAGIC2(filter, adaptive_fir_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, dc_blocker_cc);
GR_SWIG_BLOCK_MAGIC2(filter, dc_blocker_ff);
GR_SWIG_BLOCK_MAGIC2(filter, filter_delay_fc);
GR_SWIG_BLOCK_MAGIC2(filter, fir_filter_ccc);
GR_SWIG_BLOCK_MAGIC2(filter, fir_filter_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, fir_filter_fcc);
GR_SWIG_BLOCK_MAGIC2(filter, fir_filter_fff);
GR_SWIG_BLOCK_MAGIC2(filter, fir_filter_fsf);
GR_SWIG_BLOCK_MAGIC2(filter, fir_filter_scc);
GR_SWIG_BLOCK_MAGIC2(filter, fft_filter_ccc);
GR_SWIG_BLOCK_MAGIC2(filter, fft_filter_fff);
GR_SWIG_BLOCK_MAGIC2(filter, fractional_interpolator_cc);
GR_SWIG_BLOCK_MAGIC2(filter, fractional_interpolator_ff);
GR_SWIG_BLOCK_MAGIC2(filter, freq_xlating_fir_filter_ccc);
GR_SWIG_BLOCK_MAGIC2(filter, freq_xlating_fir_filter_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, freq_xlating_fir_filter_fcc);
GR_SWIG_BLOCK_MAGIC2(filter, freq_xlating_fir_filter_fcf);
GR_SWIG_BLOCK_MAGIC2(filter, freq_xlating_fir_filter_scf);
GR_SWIG_BLOCK_MAGIC2(filter, freq_xlating_fir_filter_scc);
GR_SWIG_BLOCK_MAGIC2(filter, hilbert_fc);
GR_SWIG_BLOCK_MAGIC2(filter, iir_filter_ffd);
GR_SWIG_BLOCK_MAGIC2(filter, interp_fir_filter_ccc);
GR_SWIG_BLOCK_MAGIC2(filter, interp_fir_filter_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, interp_fir_filter_fcc);
GR_SWIG_BLOCK_MAGIC2(filter, interp_fir_filter_fff);
GR_SWIG_BLOCK_MAGIC2(filter, interp_fir_filter_fsf);
GR_SWIG_BLOCK_MAGIC2(filter, interp_fir_filter_scc);
GR_SWIG_BLOCK_MAGIC2(filter, pfb_arb_resampler_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, pfb_arb_resampler_fff);
GR_SWIG_BLOCK_MAGIC2(filter, pfb_channelizer_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, pfb_decimator_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, pfb_interpolator_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, pfb_synthesizer_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, rational_resampler_base_ccc);
GR_SWIG_BLOCK_MAGIC2(filter, rational_resampler_base_ccf);
GR_SWIG_BLOCK_MAGIC2(filter, rational_resampler_base_fcc);
GR_SWIG_BLOCK_MAGIC2(filter, rational_resampler_base_fff);
GR_SWIG_BLOCK_MAGIC2(filter, rational_resampler_base_fsf);
GR_SWIG_BLOCK_MAGIC2(filter, rational_resampler_base_scc);
GR_SWIG_BLOCK_MAGIC2(filter, single_pole_iir_filter_cc);
GR_SWIG_BLOCK_MAGIC2(filter, single_pole_iir_filter_ff);
GR_SWIG_BLOCK_MAGIC2(filter, channel_model);
