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
 

(cd ..; find gnuradio/gr-vocoder -type f | grep "\.c" | \
        grep -v "\.cc" | \
        grep -v "\.t" | \
        grep -v "qa_" | \
        grep -v "c2dec\.c" | \
        grep -v "c2demo\.c" | \
        grep -v "c2enc\.c" | \
        grep -v "c2sim\.c" | \
        grep -v "g7xx\/decode\.c" | \
        grep -v "\/encode\.c" | \
        grep -v "fft\.c" | \
        grep -v "globals\.c" | \
        grep -v "generate_codebook\.c" | \
        grep -v "glottal\.c" | \
        grep -v "\/codec2\/" | \
        awk '{print $1}' | sed 's/:/ /g'| awk '{print "libgnuhawk_la_SOURCES += ../"$1}' | sort -u >& ./src/gr-vocoder_c.am )
