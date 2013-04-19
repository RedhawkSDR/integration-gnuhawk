#!/bin/sh
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
 

(cd ..; grep -R "make_io_signature" gnuradio/* | grep cc | \
        grep -v gnuradio-core | \
        grep -v "\.t" | \
        grep -v "qa_" | \
        grep -v templates | \
        grep -v alsa | \
        grep -v osx | \
        grep -v jack | \
        grep -v portaudio | \
        grep -v audio_windows | \
        grep -v wavelet_squash_ff_impl | \
        grep -v gr_ofdm_frame_sink2 | \
        grep -v comedi_sink_s | \
        grep -v comedi_source_s | \
        grep -v channel_model_impl | \
        grep -v digital_cpmmod_bc | \
        grep -v fcd_source_c_impl | \
        grep -v atsc_viterbi_decoder | \
        grep -v qtgui | \
        grep -v gr-shd | \
        grep -v gr-uhd | \
        grep -v gr-video-sdl | \
        grep -v wavelet_wavelet_ff_impl | \
        grep -v codec2 | \
        awk '{print $1}' | sed 's/:/ /g'| awk '{print "libgnuhawk_la_SOURCES += ../"$1}' | sort -u >& ./src/grblocks.am )
