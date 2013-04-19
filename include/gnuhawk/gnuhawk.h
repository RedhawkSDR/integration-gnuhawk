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

#ifndef INCLUDED_GNUHAWK_H
#define INCLUDED_GNUHAWK_H

#include <boost/shared_ptr.hpp>
#include "gruel/pmt.h"
#include "gr_properties.h"

/*namespace pmt{
    typedef void* pmt_t;
    const bool PMT_F=false;
}*/

namespace gnuradio {

  template<class T>
  boost::shared_ptr<T>
  get_initial_sptr(T *p)
  {
    return boost::shared_ptr<T>(p);
  }
};

#endif /* INCLUDED_GNUHAWK_H */
