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
 

ls -l gnuradio/filter/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./filter_includes.am
ls -l gnuradio/general/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./general_includes.am
ls -l gnuradio/gengen/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gengen_includes.am
ls -l gnuradio/gr-atsc/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-atsc_includes.am
ls -l gnuradio/gr-audio/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-audio_includes.am
ls -l gnuradio/gr-audio/alsa/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-audio_alsa_includes.am
ls -l gnuradio/gr-digital/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-digital_includes.am
ls -l gnuradio/gr-fcd/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-fcd_includes.am
ls -l gnuradio/gr-fft/fft/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-fft_fft_includes.am
ls -l gnuradio/gr-fft/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-fft_includes.am
ls -l gnuradio/gr-filter/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-filter_includes.am
ls -l gnuradio/gr-filter/filter/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-filter_filter_includes.am
ls -l gnuradio/gr-howto-write-a-block/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-howto-write-a-block_includes.am
ls -l gnuradio/gr-noaa/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-noaa_includes.am
ls -l gnuradio/gr-pager/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-pager_includes.am
ls -l gnuradio/gr-trellis/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-trellis_includes.am
ls -l gnuradio/gruel/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gruel_includes.am
ls -l gnuradio/gr-vocoder/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-vocoder_includes.am
ls -l gnuradio/gr-vocoder/codec2/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-vocoder_codec2_includes.am
ls -l gnuradio/gr-vocoder/g7xx/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-vocoder_g7xx_includes.am
ls -l gnuradio/gr-vocoder/gsm/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-vocoder_gsm_includes.am
ls -l gnuradio/gr-wavelet/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./gr-wavelet_includes.am
ls -l gnuradio/hier/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./hier_includes.am
ls -l gnuradio/io/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./io_includes.am
ls -l gnuradio/runtime/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./runtime_includes.am
ls -l gnuradio/viterbi/*.h | grep -v "qa_" | awk '{print "nobase_pkginclude_HEADERS += "$9}' >& ./viterbi_includes.am
