/* -*- c++ -*- */
/*
 * Copyright 2009 Free Software Foundation, Inc.
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
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

#if HAVE_CONFIG_H
#include <config.h>
#endif

#include <gr_msg_accepter.h>
#include <gr_block.h>
#include <gr_block_detail.h>
#include <stdexcept>

using namespace pmt;

gr_msg_accepter::gr_msg_accepter()
{
    msg_prop_struct.length(1);
    msg_prop.length(1);
    msg_prop[0].id = CORBA::string_dup("pmt");
    msg_prop_struct[0].id = CORBA::string_dup("pmt_struct");
    outboundMessages = NULL;
}

gr_msg_accepter::~gr_msg_accepter()
{
}

void
gr_msg_accepter::post(pmt_t msg)
{
    msg_prop[0].value <<= msg;
    msg_prop_struct[0].value <<= msg_prop;
    msg_out <<= msg_prop_struct;
    outboundMessages->push(msg_out);
}
