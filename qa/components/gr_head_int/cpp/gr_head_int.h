/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file 
 * distributed with this source distribution.
 * 
 * This file is part of GNUHAWK.
 * 
 * GNUHAWK is free software: you can redistribute it and/or modify is under the 
 * terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 * 
 * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more 
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program.  If not, see http://www.gnu.org/licenses/.
 */

 
#ifndef GR_HEAD_INT_IMPL_H
#define GR_HEAD_INT_IMPL_H

#include "gr_head_int_base.h"

class gr_head_int_i;

class gr_head_int_i : public gr_head_int_base
{
    ENABLE_LOGGING
    public: 
        gr_head_int_i(const char *uuid, const char *label);
        ~gr_head_int_i();
        int serviceFunction();
        int numOutCount;
};

#endif
