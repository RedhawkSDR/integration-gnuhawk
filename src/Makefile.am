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
 
lib_LTLIBRARIES = libgnuhawk.la
libgnuhawk_la_SOURCES = gr_basic_block.cc \
                        gr_block_detail.cc \
                        gr_block.cc \
                        gr_msg_accepter.cc \
                        gr_sync_block.cc \
                        gr_sync_interpolator.cc \
                        gr_sync_decimator.cc \
                        runtime/gr_msg_queue.cc \
                        runtime/gr_message.cc \
                        gruel/pmt.cc

INCLUDES = $(PROJECTDEPS_CFLAGS) -I../include/gnuhawk -I../include/gnuhawk/runtime -I../include/gnuhawk/gruel -I../include/gnuhawk/gnuradio/filter -I../include/gnuhawk/gnuradio/general -I../include/gnuhawk/gnuradio/gengen -I../include/gnuhawk/gnuradio/gruel -I../include/gnuhawk/gnuradio/runtime -I../include/gnuhawk/gnuradio -I../gnuradio/volk/include

SUBDIRS = 

include grblocks.am
include more_grblocks.am
include grblocks_dependencies.am
if ARCH_64 
include gnuradio-core_filter_assembly_64.am
else
include gnuradio-core_filter_assembly_32.am
endif
include grblocks_includes.am
