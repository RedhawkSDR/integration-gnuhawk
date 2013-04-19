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

#ifndef INCLUDED_GR_MSG_ACCEPTER_H
#define INCLUDED_GR_MSG_ACCEPTER_H

#include <gr_core_api.h>
#include <gruel/msg_accepter.h>
#include <gnuhawk.h>
#include "ossie/MessageInterface.h"
#include "ossie/CF/cf.h"

/*!
 * \brief Accepts messages and inserts them into a message queue, then notifies
 * subclass gr_basic_block there is a message pending.
 */
class GR_CORE_API gr_msg_accepter : public gruel::msg_accepter
{
public:
  gr_msg_accepter();
  ~gr_msg_accepter();

  void post(pmt::pmt_t msg);
    
    void setMessageDispatch(MessageSupplierPort *ptr) {
        outboundMessages = ptr;
    }

  protected:
    MessageSupplierPort *outboundMessages;
    CORBA::Any msg_out; // this is the format over the channel
    CF::Properties msg_prop_struct; // this is the container for the structures
    CF::Properties msg_prop; // this is for the elements in the structure

};

#endif /* INCLUDED_GR_MSG_ACCEPTER_H */
