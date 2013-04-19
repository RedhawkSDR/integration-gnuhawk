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
 
AC_INIT([gnuhawk], [1.8.3])
AC_PREREQ(2.59)

AM_INIT_AUTOMAKE(nostdinc)

AM_PROG_AS

AC_PROG_CC
AC_PROG_CXX

AC_PROG_INSTALL
AC_PROG_LIBTOOL
AM_PATH_PYTHON([2.2])

AX_BOOST_BASE([1.41])
AX_BOOST_THREAD

OSSIE_CHECK_OSSIE
OSSIE_SDRROOT_AS_PREFIX([dom/deps/gnuhawk])

AM_CONDITIONAL([ARCH_64], test $gr_libdir_suffix = "64")

CHECK_VECTOR_IMPL
PKG_CHECK_MODULES([PROJECTDEPS], [ossie >= 1.8 omniORB4 >= 4.0.0])

# Require FFTW3
PKG_CHECK_MODULES([FFTW], [fftw3f >= 3.0])

# Undefine the PACKAGE_ variables to avoid warnings when omniORB is installed outside of
# /usr.  OSSIE doesn't use these variables anyways...and even if it did
# they would get undefined in any file that included CORBA.h (because ossieconfig.h
# undefines them)
CPPFLAGS="${CPPFLAGS} -UPACKAGE_NAME -UPACKAGE_BUGREPORT -UPACKAGE_TARNAME -UPACKAGE_STRING -UPACKAGE_VERSION"

# Add the OSSIE include flags
CXXFLAGS="$CXXFLAGS -Wall -Wno-strict-aliasing $OSSIE_CFLAGS"

# Include the Boost CPP flags to make sure we get the right headers
GNUHAWK_CPPFLAGS="$GNUHAWK_CPPFLAGS $BOOST_CPPFLAGS -msse2"
AC_MSG_RESULT($GNUHAWK_CPPFLAGS)
AC_SUBST(GNUHAWK_CPPFLAGS)

# Build the library with the platform preprocessor flags.
CPPFLAGS="${CPPFLAGS} ${GNUHAWK_CPPFLAGS}"

AC_CONFIG_FILES(Makefile \
		include/Makefile \
		include/gnuhawk/Makefile \
		src/Makefile \
        components/Makefile \
        qa/Makefile \
		gnuhawk.pc)

AC_ARG_WITH(with_componets, [  --with-components       enable component configure])

if test "$with_components" == "yes"
then
    AC_CONFIG_SUBDIRS(components/add_cc_2i/cpp \
        components/add_const_ff/cpp \
        components/add_const_ii/cpp \
        components/add_const_sf/cpp \
        components/add_const_ss/cpp \
        components/add_const_vff/cpp \
        components/add_const_vii/cpp \
        components/add_const_vss/cpp \
        components/add_ff_2i/cpp \
        components/add_ii_2i/cpp \
        components/add_ss_2i/cpp \
        components/agc2_cc/cpp \
        components/agc2_ff/cpp \
        components/agc_cc/cpp \
        components/agc_ff/cpp \
        components/and_bb_2i/cpp \
        components/and_const_bb/cpp \
        components/and_ii_2i/cpp \
        components/and_ss_2i/cpp \
        components/complex_to_arg/cpp \
        components/complex_to_float_1o/cpp \
        components/complex_to_float_2o/cpp \
        components/complex_to_imag/cpp \
        components/complex_to_mag/cpp \
        components/complex_to_mag_squared/cpp \
        components/complex_to_real/cpp \
        components/dc_blocker_cc/cpp \
        components/dc_blocker_ff/cpp \
        components/feedforward_agc_cc/cpp \
        components/fft_filter_fff/cpp \
        components/fft_vcc/cpp \
        components/fft_vfc/cpp \
        components/filter_delay_fc_1i/cpp \
        components/filter_delay_fc_2i/cpp \
        components/fir_filter_ccf/cpp \
        components/fir_filter_fff/cpp \
        components/fir_filter_fsf/cpp \
        components/fll_band_edge_cc_4o/cpp \
        components/fractional_interpolator_cc/cpp \
        components/fractional_interpolator_ff/cpp \
        components/glfsr_source_f/cpp \
        components/goertzel_fc/cpp \
        components/hilbert_fc/cpp \
        components/iir_filter_ffd/cpp \
        components/integrate_cc/cpp \
        components/integrate_ff/cpp \
        components/integrate_ii/cpp \
        components/integrate_ss/cpp \
        components/interp_fir_filter_ccf/cpp \
        components/interp_fir_filter_fff/cpp \
        components/int_to_float/cpp \
        components/multiply_cc_2i/cpp \
        components/multiply_const_ff/cpp \
        components/multiply_const_ii/cpp \
        components/multiply_const_ss/cpp \
        components/multiply_const_vff/cpp \
        components/multiply_const_vii/cpp \
        components/multiply_const_vss/cpp \
        components/multiply_ff_2i/cpp \
        components/multiply_ii_2i/cpp \
        components/multiply_ss_2i/cpp \
        components/noise_source_c/cpp \
        components/noise_source_f/cpp \
        components/noise_source_i/cpp \
        components/noise_source_s/cpp \
        components/pfb_arb_resampler_ccf/cpp \
        components/pfb_arb_resampler_fff/cpp \
        components/pfb_clock_sync_ccf_4o/cpp \
        components/phase_modulator_fc/cpp \
        components/psk_demod_cb/cpp \
        components/quadrature_demod_cf/cpp \
        components/short_to_float/cpp \
        components/sig_source_f/cpp \
        components/sig_source_i/cpp \
        components/single_pole_iir_filter_cc/cpp \
        components/single_pole_iir_filter_ff/cpp \
        components/unpack_k_bits_bb/cpp \
        components/vector_sink_b/cpp \
        components/vector_sink_f/cpp \
        components/vector_sink_i/cpp \
        components/vector_sink_s/cpp \
        components/vector_source_b/cpp \
        components/vector_source_f/cpp \
        components/vector_source_i/cpp \
        components/vector_source_s/cpp \
        qa/components/firdes/cpp \
        qa/components/gr_head_char/cpp \
        qa/components/gr_head_complex/cpp \
        qa/components/gr_head_float/cpp \
        qa/components/gr_head_int/cpp \
        qa/components/gr_head_octet/cpp \
        qa/components/gr_head_short/cpp)
    components=true
else
    components=false
fi

AM_CONDITIONAL(COMPONENTS, test x$components = xtrue)

# volk setup
(cd gnuradio/volk;cmake -DCMAKE_INSTALL_PREFIX:PATH=${prefix} -DLIB_SUFFIX:STRING="" .)

libdir=${prefix}/lib

AC_OUTPUT
