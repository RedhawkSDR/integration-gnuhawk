/* -*- c++ -*- */
/*
 * Copyright 2004,2009,2010 Free Software Foundation, Inc.
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

#ifdef HAVE_CONFIG_H
#include "config.h"
#endif

#include <gr_block_detail.h>

using namespace pmt;

static long s_ncurrently_allocated = 0;

long
gr_block_detail_ncurrently_allocated ()
{
  return s_ncurrently_allocated;
}

gr_block_detail::gr_block_detail (unsigned int ninputs, unsigned int noutputs)
  : d_produce_or(0),
    d_ninputs (ninputs), d_noutputs (noutputs),
    d_input (ninputs), d_output (noutputs),
    d_done (false)
{
}

gr_block_detail::~gr_block_detail ()
{
}

void
gr_block_detail::set_input (unsigned int which, gr_buffer_reader_sptr reader)
{
}

void
gr_block_detail::set_output (unsigned int which, gr_buffer_sptr buffer)
{
}

gr_block_detail_sptr
gr_make_block_detail (unsigned int ninputs, unsigned int noutputs)
{
  return gr_block_detail_sptr (new gr_block_detail (ninputs, noutputs));
}

void
gr_block_detail::set_done (bool done)
{
}

void 
gr_block_detail::consume (int which_input, int how_many_items)
{
}


void
gr_block_detail::consume_each (int how_many_items)
{
}

void
gr_block_detail::produce (int which_output, int how_many_items)
{
}

void
gr_block_detail::produce_each (int how_many_items)
{
}


void
gr_block_detail::_post(pmt_t msg)
{
}

uint64_t
gr_block_detail::nitems_read(unsigned int which_input) 
{
  return 0;
}

uint64_t
gr_block_detail::nitems_written(unsigned int which_output) 
{
  return 0;
}

void
gr_block_detail::add_item_tag(unsigned int which_output, const gr_tag_t &tag)
{
}

void
gr_block_detail::get_tags_in_range(std::vector<gr_tag_t> &v,
				   unsigned int which_input,
				   uint64_t abs_start,
				   uint64_t abs_end)
{
}

void
gr_block_detail::get_tags_in_range(std::vector<gr_tag_t> &v,
				   unsigned int which_input,
				   uint64_t abs_start,
				   uint64_t abs_end,
				   const pmt_t &key)
{
}
