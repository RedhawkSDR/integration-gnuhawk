
#ifndef __gnuhawk_bulkio_p_h__
#define __gnuhawk_bulkio_p_h__

#include <queue>
#include <list>
#include <boost/date_time/posix_time/posix_time.hpp>
#include <boost/thread/condition_variable.hpp>
#include <boost/thread/locks.hpp>
#include <ossie/prop_helpers.h>
#include <BULKIO/bio_runtimeStats.h>

#include <bulkio.h>
#define CORBA_MAX_TRANSFER_BYTES omniORB::giopMaxMsgSize()

namespace bulkio    {

  typedef   boost::unique_lock< boost::mutex >   UNIQUE_LOCK;

  typedef    boost::mutex::scoped_lock           SCOPED_LOCK;



  class queueSemaphore
  {
  private:
    unsigned int maxValue;
    unsigned int currValue;
    MUTEX mutex;
    CONDITION condition;

  public:
  queueSemaphore(unsigned int initialMaxValue):mutex(),condition() {
      maxValue = initialMaxValue;
    }

    void release() {
      currValue=0;
      condition.notify_all();
    }

    void setMaxValue(unsigned int newMaxValue) {
      UNIQUE_LOCK lock(mutex);
      maxValue = newMaxValue;
    }

    unsigned int getMaxValue(void) {
      UNIQUE_LOCK lock(mutex);
      return maxValue;
    }

    void setCurrValue(unsigned int newValue) {
      UNIQUE_LOCK lock(mutex);
      if (newValue < maxValue) {
	unsigned int oldValue = currValue;
	currValue = newValue;

	if (oldValue > newValue) {
	  condition.notify_one();
	}
      }
    }

    void incr() {
      UNIQUE_LOCK lock(mutex);
      while (currValue >= maxValue) {
	condition.wait(lock);
      }
      ++currValue;
    }

    void decr() {
      UNIQUE_LOCK lock(mutex);
      if (currValue > 0) {
	--currValue;
      }
      condition.notify_one();
    }
  };

  class linkStatistics
  {
  public:

    linkStatistics( std::string &portName, const int nbytes=1 );

    linkStatistics();

    virtual ~linkStatistics() {};

    virtual void setEnabled(bool enableStats);

    virtual void setBitSize( double bitSize );

    virtual void update(unsigned int elementsReceived, float queueSize, bool EOS, const std::string &streamID, bool flush = false);

    virtual BULKIO::PortStatistics retrieve();


  protected:

    struct statPoint {
      unsigned int elements;
      float queueSize;
      double secs;
      double usecs;
    };

    std::string  portName;      
    bool enabled;
    int  nbytes;
    double bitSize;
    BULKIO::PortStatistics runningStats;
    std::vector< statPoint > receivedStatistics;
    StreamIDList activeStreamIDs;
    unsigned long historyWindow;
    int receivedStatistics_idx;

    double flush_sec;                   // track time since last queue flush happened
    double flush_usec;                  // track time since last queue flush happened

  };


}   // end of namespace


#endif  // __bulkio_p_h__

