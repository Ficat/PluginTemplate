fun poolKt(
    applicationPackage: String?,
    poolName: String
) = """
package $applicationPackage

class $poolName {
    /**
     * Pool constants and variables
     */
    companion object{
        private const val MAX_POOL_SIZE = 4
    }
    private val sKey = Any()
    private var sPool: $poolName? = null
    private var sPoolSize = 0 //current pool size
    private var next: $poolName? = null
    @Volatile
    private var isInPool = false//is the object in pool


    /**
     * Data
     */
     
     
     
    
    private constructor() {
    
    }

    /**
     * Get instance from the pool
     */
    fun obtain(): $poolName {
        synchronized(sKey) {
            if (sPool != null) {
                val r: $poolName = sPool!!
                sPool = r.next
                r.next = null
                r.isInPool = false
                sPoolSize--
                return r
            }
        }
        return $poolName()
    }

    /**
     * Recycle instance
     */
    fun recycle() {
        if (isInPool) {
            return
        }
        resetData()
        synchronized(sKey) {
            if (!isInPool && sPoolSize < Companion.MAX_POOL_SIZE) {
                next = sPool
                sPool = this
                isInPool = true
                sPoolSize++
            }
        }
    }

    /**
     * Reset data when this instance is recycled
     */
    private fun resetData() {
        //TODO here reset data
    }
}
"""