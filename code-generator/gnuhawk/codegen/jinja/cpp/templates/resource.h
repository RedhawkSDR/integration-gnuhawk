//% set includeGuard = component.prefix.upper() + '_IMPL_H'
//% set className = component.userclass.name

${component.cppLicense}

#ifndef ${includeGuard}
#define ${includeGuard}

#include "${component.baseclass.header}"

class ${className} : public ${component.baseclass.name}
{
    public:
        ${className}(const char *uuid, const char *label);
        ~${className}();

        //
        // createBlock
        //
        // Create the actual GNU Radio Block to that will perform the work method. The resulting
        // block object is assigned to gr_stpr
        //
        // Add property change callbacks for getter/setter methods
        //
        //
        void createBlock();

/*{% if component.hasbulkiouses %}*/
        //
        // createOutputSRI
        //
        // Called by setupIOMappings when an output mapping is defined. For each output mapping
        // defined, a call to createOutputSRI will be issued with the associated output index.
        // This default SRI and StreamID will be saved to the mapping and pushed down stream via pushSRI.
        // The subclass is responsible for overriding behavior of this method. The index provide matches
        // the stream index number that will be use by the GR Block object
        //
        // @param idx : output stream index number to associate the returned SRI object with
        // @return sri : default SRI object passed down stream over a RedHawk port
        //      
        BULKIO::StreamSRI  createOutputSRI( int32_t oidx, int32_t &in_idx );
/*{% endif %}*/
};

#endif
