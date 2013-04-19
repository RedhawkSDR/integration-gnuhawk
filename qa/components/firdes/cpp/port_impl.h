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


#ifndef PORT_H
#define PORT_H

#include "ossie/Port_impl.h"
#include "ossie/debug.h"
#include <queue>
#include <list>
#include <boost/thread/condition_variable.hpp>
#include <boost/thread/locks.hpp>

class firdes_base;
class firdes_i;

#define CORBA_MAX_TRANSFER_BYTES omniORB::giopMaxMsgSize()

typedef char                        Char;
typedef int8_t                      Int8;
typedef uint8_t                     UInt8;
typedef int16_t                     Int16;
typedef uint16_t                    UInt16;

typedef int32_t                     Int32;
typedef uint32_t                    UInt32;

typedef int64_t                     Int64;
typedef uint64_t                    UInt64;
typedef float                       Float;
typedef double                      Double;

#endif
