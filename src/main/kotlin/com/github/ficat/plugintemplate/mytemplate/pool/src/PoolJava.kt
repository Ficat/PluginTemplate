fun poolJava(
    applicationPackage: String?,
    poolName: String
) = """
package ${applicationPackage};

public class $poolName {
    /**
     * Pool constants and variables
     */
    private static final int MAX_POOL_SIZE = 4;
    private static final Object sKey = new Object();
    private static $poolName sPool;
    private static int sPoolSize = 0;//current pool size
    private $poolName next;
    private volatile boolean isInPool;//is the object in pool

    /**
     * Data
     */
     
     
     
     
    private $poolName() {

    }

    /**
     * Get instance from the pool
     */
    public static $poolName obtain() {
        synchronized (sKey) {
            if (sPool != null) {
                $poolName r = sPool;
                sPool = r.next;
                r.next = null;
                r.isInPool = false;
                sPoolSize--;
                return r;
            }
        }
        return new $poolName();
    }

    /**
     * Recycle instance
     */
    public void recycle() {
        if (isInPool) {
            return;
        }

        resetData();

        synchronized (sKey) {
            if (!isInPool && sPoolSize < MAX_POOL_SIZE) {
                next = sPool;
                sPool = this;
                isInPool = true;
                sPoolSize++;
            }
        }
    }

    /**
     * Reset data when this instance is recycled
     */
    private void resetData() {
        //TODO here reset data
    }
}
"""