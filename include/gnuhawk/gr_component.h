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

#ifndef GR_COMPONENT_H
#define GR_COMPONENT_H

#include <boost/shared_ptr.hpp>

#include "gr_properties.h"
#include "gr_malloc.h"

template < typename GR_BLOCK >
class GHComponent : public Resource_impl
{
        
protected:
    typedef GR_BLOCK                                       GNU_RADIO_BLOCK;
    typedef typename boost::shared_ptr< GNU_RADIO_BLOCK >  GNU_RADIO_BLOCK_PTR;

    GHComponent(const char* uuid, const char* label) :
        Resource_impl(uuid, label),
        gr_sptr()
    {
    }

    virtual ~GHComponent() { }

    /**
     * Associate a getter for the wrapped GNU Radio block with a REDHAWK property.
     */
    template <class value_type>
    bool registerGetter (const std::string& id, value_type (GNU_RADIO_BLOCK::*func)() const)
    {
        return registerGetterSetter(id, func, (void (GNU_RADIO_BLOCK::*)(value_type))0);
    }

    /**
     * Associate a by-value setter for the wrapped GNU Radio block with a REDHAWK property.
     */
    template <class value_type>
    bool registerSetter (const std::string& id, void (GNU_RADIO_BLOCK::*func)(value_type))
    {
        return registerGetterSetter(id, (value_type (GNU_RADIO_BLOCK::*)() const)0, func);
    }

    /**
     * Associate a by-reference setter for the wrapped GNU Radio block with a REDHAWK property.
     */
    template <class value_type>
    bool registerSetter (const std::string& id, void (GNU_RADIO_BLOCK::*func)(value_type&))
    {
        return registerGetterSetter(id, (value_type (GNU_RADIO_BLOCK::*)() const)0, func);
    }

    /**
     * Associate a getter and by-value setter for the wrapped GNU Radio block with a REDHAWK property.
     */
    template <class value_type>
    bool registerGetterSetter (const std::string& id, value_type (GNU_RADIO_BLOCK::*getter)() const, void (GNU_RADIO_BLOCK::*setter)(value_type))
    {
        return registerGetterSetter_<value_type>(id, getter, setter);
    }

    /**
     * Associate a getter and by-reference setter for the wrapped GNU Radio block with a REDHAWK property.
     */
    template <class value_type>
    bool registerGetterSetter (const std::string& id, value_type (GNU_RADIO_BLOCK::*getter)() const, void (GNU_RADIO_BLOCK::*setter)(value_type&))
    {
        return registerGetterSetter_<value_type>(id, getter, setter);
    }

    /**
     * Register a generic callback to return the value of a property.
     */
    template <class Te, class value_type>
    bool registerGetValue (std::string id, Te* target, value_type (Te::*func)())
    {
        PropertyInterface* property = getPropertyFromId(id);
        if (property) {
            GR_PropertyWrapper<value_type> *wrapper = NULL;
            wrapper = reinterpret_cast<GR_PropertyWrapper<value_type>*>(property);
            if (wrapper) {
                wrapper->setGetValueOverride(target, func);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a property with no initial value.
     */
    template <typename T>
    PropertyInterface* addProperty (T& value, const std::string& id, const std::string& name, const std::string& mode,
                                    const std::string& units, const std::string& action, const std::string& kinds)
    {
        PropertyInterface* wrapper = GR_PropertyWrapperFactory::Create(value);
        wrapper->configure(id, name, mode, units, action, kinds);
        wrapper->isNil(false);
        ownedWrappers.push_back(wrapper);
        propTable[wrapper->id] = wrapper;
        return wrapper;
    }

    /**
     * Adds a property with an initial value.
     */
    template <typename T, typename T2>
    PropertyInterface* addProperty (T& value, const T2& initial_value, const std::string& id, const std::string& name,
                                    const std::string& mode, const std::string& units, const std::string& action,
                                    const std::string& kinds)
    {
        PropertyInterface* wrapper = addProperty(value, id, name, mode, units, action, kinds);
        value = initial_value;
        wrapper->isNil(false);
        return wrapper;
    }

    inline bool validGRBlock() {
        return (gr_sptr != NULL);
    }

    GNU_RADIO_BLOCK_PTR gr_sptr;

private:
    template <class value_type, typename Getter, typename Setter>
    bool registerGetterSetter_ (const std::string& id, Getter getter, Setter setter)
    {
        PropertyInterface* property = getPropertyFromId(id);
        if (property) {
            GR_PropertyWrapper<value_type>* wrapper = reinterpret_cast<GR_PropertyWrapper<value_type>*>(property);
            if (wrapper) {
                if (getter) {
                    wrapper->setGetValueOverride(gr_sptr, getter);
                }
                if (setter) {
                    wrapper->setSetValueOverride(gr_sptr, setter);
                }
                return true;
            }
        }
        return false;
    }

};

#endif
