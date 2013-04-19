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

#ifndef INCLUDED_GNUHAWK_BLOCK_BASES_H
#define INCLUDED_GNUHAWK_BLOCK_BASES_H

class gr_block_base_alt
{
    public: 
        int serviceFunction()
        {
        
            BULKIO_dataFloat_In_i::dataTransfer *dataIn[gr_sptr_ninput_streams];
        
            for (unsigned int ninput = 0; ninput<gr_sptr_ninput_streams; ninput++) {
                dataIn[ninput] = NULL;
            }
        
            // make sure that ports corresponding to gnr streams 0-n are full
            for (unsigned int data_in_idx = 0; data_in_idx<gr_sptr_ninput_streams; data_in_idx++) {
                while ((dataIn[data_in_idx] == NULL) and (this->serviceThread->threadRunning())) {
                    dataIn[data_in_idx] = inputPorts[data_in_idx]->getPacket(-1);
                }
            }
        
            // check to see if the thread is supposed to exit
            if (not this->serviceThread->threadRunning()) {
                // the data no longer matters - the thread is gone
                for (unsigned int ninput= 0; ninput<gr_sptr_ninput_streams; ninput++) {
                    if (dataIn[ninput] != NULL) {
                        delete dataIn[ninput];
                    }
                }
                return NORMAL;
            }
        
            bufferInVec.clear();
            bufferOutVec.clear();
            for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                // NOTE: You must make at least one valid pushSRI call
                if (dataIn[streamNum]->sriChanged) {
                    float_out->pushSRI(dataIn[streamNum]->SRI);
                }
        
                if (dataIn[streamNum]->EOS && dataIn[streamNum]->dataBuffer.size() == 0){
                    // take care of deleting vectors 0-n
                    std::vector<float> emptyVec;
                    for (unsigned int data_in_idx = 0; data_in_idx<gr_sptr_ninput_streams; data_in_idx++) {
                        if (dataIn[data_in_idx] != NULL) {
                            delete dataIn[data_in_idx];
                        }
                        outputPorts[data_in_idx]->pushPacket(emptyVec, dataIn[data_in_idx]->T, true, dataIn[0]->streamID);
                    }
                    return NORMAL;
                }
                bufferInVec.push_back(&dataIn[streamNum]->dataBuffer[0]);
                dataOut[streamNum].resize(dataIn[streamNum]->dataBuffer.size());
        
                bufferOutVec.push_back(&dataOut[streamNum][0]);
        
                // If there was left over data from previous call to serviceFunction, insert it into the beginning of the current dataBuffer
                if (inputStorageVec[streamNum].size() > 0){
                    dataIn[streamNum]->dataBuffer.insert(dataIn[streamNum]->dataBuffer.begin(),inputStorageVec[streamNum].begin(),inputStorageVec[streamNum].end());
                    inputStorageVec[streamNum].clear();
                }
        
                dataOut[streamNum].resize(dataIn[streamNum]->dataBuffer.size());
            }
        
            bool dataReady = true;
            for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                // If not enough data yet, store it for next call to serviceFunction()
                if ((int)dataIn[streamNum]->dataBuffer.size() < gr_sptr->get_minimum_buffer_items(streamNum)) {
                    inputStorageVec[streamNum].insert(inputStorageVec[streamNum].end(),dataIn[streamNum]->dataBuffer.begin(),dataIn[streamNum]->dataBuffer.end());
                    dataReady = false;
                }
            }
            for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                numElementsIn.push_back(dataIn[streamNum]->dataBuffer.size());
            }
            if (dataReady) {
                bool enoughData = true;
                while (enoughData) {
                    for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                        if (gr_sptr->get_read_index(streamNum) >= numElementsIn[streamNum]) {
                            enoughData = false;
                            break;
                        }
                    }
                    if (not enoughData) {
                        continue;
                    }
                    //If number of elements left is less than minimum buffer size, store off the data for the next packet of data
                    std::vector<float*> bufferInVec;
                    std::vector<int> inputElementsVec;
                    std::vector<float*> bufferOutVec;
        
                    for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                        bufferInVec.push_back(&dataIn[streamNum]->dataBuffer[gr_sptr->get_read_index(streamNum)]);
                        inputElementsVec.push_back(numElementsIn[streamNum]-gr_sptr->get_read_index(streamNum));
                        bufferOutVec.push_back(&dataOut[streamNum][0]);
                        dataOut[streamNum].resize(numElementsIn[streamNum]-gr_sptr->get_read_index(streamNum));
                    }
        
                    std::vector<const void *> vecIn;
                    std::vector<void *> vecOut;
                    for (int inStreamNum=0; inStreamNum<(int)bufferInVec.size(); inStreamNum++){
                        vecIn.push_back((const void *)bufferInVec[inStreamNum]);
                    }
                    for (int outStreamNum=0; outStreamNum<(int)bufferInVec.size(); outStreamNum++){
                        vecOut.push_back((void*)bufferOutVec[outStreamNum]);
                    }
        
                    // general_work() returns number of output items written or -1 for EOF
                    // output sample rate is assumed to be the same so no need for vector of output sample lengths
                    // input sample rate can be different so need a vector of ints specifying length of each input buffer
                    int numOut = gr_sptr->general_work(numElementsIn[0]-gr_sptr->get_read_index(0), inputElementsVec, vecIn, vecOut);
        
                    if (numOut > 0){
                        for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                            float_out->pushPacket(dataOut[0], dataIn[streamNum]->T, dataIn[streamNum]->EOS, dataIn[streamNum]->streamID);
                        }
                    }
                }
                //Set read_index back to zero
                gr_sptr->reset_read_index();
            }
            for (unsigned int streamNum = 0; streamNum < gr_sptr_ninput_streams; streamNum++) {
                if ((numElementsIn[streamNum]-gr_sptr->get_read_index(streamNum)) < gr_sptr->get_minimum_buffer_items(streamNum)){
                    inputStorageVec[streamNum].insert(inputStorageVec[streamNum].end(),dataIn[streamNum]->dataBuffer.begin()+gr_sptr->get_read_index(streamNum),dataIn[streamNum]->dataBuffer.end());
                    break;
                }
            }
            for (unsigned int ninput= 0; ninput<gr_sptr_ninput_streams; ninput++) {
                delete dataIn[ninput]; // IMPORTANT: MUST RELEASE THE RECEIVED DATA BLOCK
            }
            bufferInVec.clear();
            bufferOutVec.clear();
        
            return NORMAL;
        };
    
    private:
        std::vector<float> *inputStorageVec;
        unsigned int  gr_sptr_ninput_streams;
        unsigned int  gr_sptr_noutput_streams;
        std::vector<float*> bufferInVec;
        std::vector<float*> bufferOutVec;
        std::vector<float> *dataOut;
        std::vector<BULKIO_dataFloat_Out_i *> outputPorts;
        std::vector<BULKIO_dataFloat_In_i *> inputPorts;
        std::vector<int> numElementsIn;
};

#endif
