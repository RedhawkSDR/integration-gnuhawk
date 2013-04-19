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
 

(cd ..; find gnuradio/gnuradio-core -type f | grep "\.cc" | \
        grep -v "\.t" | \
        grep -v "qa_" | \
        grep -v gr_basic_block | \
        grep -v gr_block | \
        grep -v gr_block_detail | \
        grep -v gr_message | \
        grep -v gr_msg_accepter | \
        grep -v gr_msg_queue | \
        grep -v gr_sync_block | \
        grep -v gr_sync_decimator | \
        grep -v gr_sync_interpolator | \
        grep -v gr_ofdm_frame_sink2  | \
        grep -v "\/hier\/"  | \
        grep -v "\/missing\/"  | \
        grep -v "\/tests\/"  | \
        grep -v gr_tpb  | \
        grep -v test_shared_block_ptr  | \
        grep -v powerpc | \
        grep -v altivec | \
        grep -v armv7 | \
        grep -v gr_sptr_magic  | \
        grep -v gr_hier_block2  | \
        grep -v "\/decode\.cc"  | \
        grep -v "\/encode\.cc"  | \
        grep -v gnuradio-config-info  | \
        grep -v gr_scheduler_tpb | \
        grep -v gr_top_block | \
        grep -v scheduler | \
        grep -v gr_realtime | \
        grep -v decode_rs_ccsds | \
        grep -v "\/sysconfig_generic" | \
        awk '{print $1}' | sed 's/:/ /g'| awk '{print "libgnuhawk_la_SOURCES += ../"$1}' | sort -u >& ./src/gnuradio-core_cc.am )

(cd ..; find gnuradio/gnuradio-core -type f | grep "\.c" | \
        grep -v "\.conf" | \
        grep -v "\.cmake" | \
        grep -v "\.cc" | \
        grep -v "\.t" | \
        grep -v "qa_" | \
        grep -v altivec | \
        grep -v armv7 | \
        grep -v powerpc | \
        grep -v "\/missing\/"  | \
        grep -v gen_interpolator_taps | \
        grep -v gen_ccsds | \
        grep -v gen_ccsds_tal | \
        grep -v decode_rs_ccsds | \
        grep -v encode_rs_ccsds | \
        grep -v rstest | \
        awk '{print $1}' | sed 's/:/ /g'| awk '{print "libgnuhawk_la_SOURCES += ../"$1}' | sort -u >& ./src/gnuradio-core_c.am )

(cd ..; find gnuradio/gnuradio-core/src/lib/filter -type f | grep "64\.S" | \
        awk '{print $1}' | sed 's/:/ /g'| awk '{print "libgnuhawk_la_SOURCES += ../"$1}' | sort -u >& ./src/gnuradio-core_filter_assembly_64.am )

(cd ..; find gnuradio/gnuradio-core/src/lib/filter -type f | grep "\.S" | \
        grep -v 64 | \
        grep -v simple | \
        awk '{print $1}' | sed 's/:/ /g'| awk '{print "libgnuhawk_la_SOURCES += ../"$1}' | sort -u >& ./src/gnuradio-core_filter_assembly_32.am )


