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


#include "gr_head_octet_base.h"

/*******************************************************************************************

    AUTO-GENERATED CODE. DO NOT MODIFY
    
 	Source: gr_head_octet.spd.xml
 	Generated on: Mon Feb 25 13:43:32 EST 2013
 	Redhawk IDE
 	Version:M.1.8.3
 	Build id: v201302151037

*******************************************************************************************/

/******************************************************************************************

    The following class functions are for the base class for the component class. To
    customize any of these functions, do not modify them here. Instead, overload them
    on the child class

******************************************************************************************/
 
gr_head_octet_base::gr_head_octet_base(const char *uuid, const char *label) :
                                     Resource_impl(uuid, label), serviceThread(0) {
    construct();
}

void gr_head_octet_base::construct()
{
    Resource_impl::_started = false;
    loadProperties();
    serviceThread = 0;
    
    PortableServer::ObjectId_var oid;
    octet_in = new BULKIO_dataOctet_In_i("octet_in", this);
    oid = ossie::corba::RootPOA()->activate_object(octet_in);
    octet_out = new BULKIO_dataOctet_Out_i("octet_out", this);
    oid = ossie::corba::RootPOA()->activate_object(octet_out);

    registerInPort(octet_in);
    registerOutPort(octet_out, octet_out->_this());
}

/*******************************************************************************************
    Framework-level functions
    These functions are generally called by the framework to perform housekeeping.
*******************************************************************************************/
void gr_head_octet_base::initialize() throw (CF::LifeCycle::InitializeError, CORBA::SystemException)
{
}

void gr_head_octet_base::start() throw (CORBA::SystemException, CF::Resource::StartError)
{
    boost::mutex::scoped_lock lock(serviceThreadLock);
    if (serviceThread == 0) {
        octet_in->unblock();
        serviceThread = new ProcessThread<gr_head_octet_base>(this, 0.1);
        serviceThread->start();
    }
    
    if (!Resource_impl::started()) {
    	Resource_impl::start();
    }
}

void gr_head_octet_base::stop() throw (CORBA::SystemException, CF::Resource::StopError)
{
    boost::mutex::scoped_lock lock(serviceThreadLock);
    // release the child thread (if it exists)
    if (serviceThread != 0) {
        octet_in->block();
        if (!serviceThread->release(2)) {
            throw CF::Resource::StopError(CF::CF_NOTSET, "Processing thread did not die");
        }
        serviceThread = 0;
    }
    
    if (Resource_impl::started()) {
    	Resource_impl::stop();
    }
}

CORBA::Object_ptr gr_head_octet_base::getPort(const char* _id) throw (CORBA::SystemException, CF::PortSupplier::UnknownPort)
{

    std::map<std::string, Port_Provides_base_impl *>::iterator p_in = inPorts.find(std::string(_id));
    if (p_in != inPorts.end()) {

        if (!strcmp(_id,"octet_in")) {
            BULKIO_dataOctet_In_i *ptr = dynamic_cast<BULKIO_dataOctet_In_i *>(p_in->second);
            if (ptr) {
                return BULKIO::dataOctet::_duplicate(ptr->_this());
            }
        }
    }

    std::map<std::string, CF::Port_var>::iterator p_out = outPorts_var.find(std::string(_id));
    if (p_out != outPorts_var.end()) {
        return CF::Port::_duplicate(p_out->second);
    }

    throw (CF::PortSupplier::UnknownPort());
}

void gr_head_octet_base::releaseObject() throw (CORBA::SystemException, CF::LifeCycle::ReleaseError)
{
    // This function clears the component running condition so main shuts down everything
    try {
        stop();
    } catch (CF::Resource::StopError& ex) {
        // TODO - this should probably be logged instead of ignored
    }

    // deactivate ports
    releaseInPorts();
    releaseOutPorts();

    delete(octet_in);
    delete(octet_out);
 
    Resource_impl::releaseObject();
}

void gr_head_octet_base::loadProperties()
{
    addProperty(num,
                0, 
               "num",
               "",
               "readwrite",
               "",
               "external",
               "configure");

    addProperty(done,
                false, 
               "done",
               "",
               "readwrite",
               "",
               "external",
               "configure");

}
