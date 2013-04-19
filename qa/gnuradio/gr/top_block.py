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
 
from ossie.utils import sb
import gnuradioStubs
import sources
import commands

def _uuidgen():
    return commands.getoutput('uuidgen')

def _isStubClass(comp):
    return (isinstance(comp, gnuradioStubs.stream_to_vector) or
            isinstance(comp, gnuradioStubs.vector_to_stream) or
            str(comp.__class__).find("stream_to_streams") >= 0)

class top_block(object):
    def __init__(self):
        self.sources = []

    def __del__(self):
       sb.domainless._cleanUpLaunchedComponents()

    def stop(self):
        # TODO: consider removing this
        sb.stop()

    def connect(self, src, dest, *next):
        # If the source is not a real object, skip this connection
        if _isStubClass(src):
            if len(next) > 0:
                self.connect(dest, *next)
            return

        # If the destination is not a real object, skip both possible connections involving it
        if _isStubClass(dest):
            if len(next) > 0:
                self.connect(src, *next)
            return
        
        src_port_name = None
        if type(src) == tuple:
            src, index = src
        else:
            # Default to the first port
            index = 0
        if isinstance(src, sb.Component):
            # Get just the uses ports from the source
            uses_ports = filter(lambda x: x._direction == 'Uses', src._ports)
            if len(uses_ports) > index:
                src_port_name = uses_ports[index]._name
        if isinstance(src, gnuradioStubs.head):
            src = src.comp

        if type(dest) == tuple:
            dest, index = dest
        else:
            # Default to the first port
            index = 0
        if isinstance(dest, sb.Component):
            # Get just the provides ports from the destination
            provides_ports = filter(lambda x: x._direction == 'Provides', dest._ports)
            # -1 inputs; connect everybody to the same input port until existing components are
            # modified to have 1 port per allowed input
            if len(provides_ports) == 1:
                index = 0
            dest_port_name = provides_ports[index]._name
        else:
            dest_port_name = None

        # Connect directly to the destination Component object for the stub head class.
        if isinstance(dest, gnuradioStubs.head):
            dest_obj = dest.comp
        else:
            dest_obj = dest

        if isinstance(src, sources.sbSource):
            # Generate a unique ID to disambiguate multiple streams into the same component.
            stream_id = _uuidgen()
            src.connect((dest_obj,stream_id))
            self.sources.append(src)
        else:
            src.connect(dest_obj, usesPortName=src_port_name, providesPortName=dest_port_name)

        # Make next pair of connections
        if len(next) > 0:
            self.connect(dest, *next)

    def run(self):
        sb.start()
        for source in self.sources:
            # TODO: only do this if this is an sbSource
            # try statement is a little sloppy
            try:
                source.push()
            except AttributeError:
                pass

