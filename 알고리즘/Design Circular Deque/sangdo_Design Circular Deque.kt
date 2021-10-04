class MyCircularDeque(var k: Int) {
    var buffer = IntArray(k+1)
    var s = 0
    var e = 0
    
    fun insertFront(value: Int): Boolean {
        if((e - s + k+1)%(k+1) == k) return false
        s = (s+ k)%(k+1)
        buffer[s] = value
        return true
    }

    fun insertLast(value: Int): Boolean {
        if((e - s + k+1)%(k+1) == k) return false
        buffer[e] = value
        e = (e+1)%(k+1)
        return true
    }

    fun deleteFront(): Boolean {
        if(e == s) return false
        s = (s+1)%(k+1)
        return true
    }

    fun deleteLast(): Boolean {
        if(e == s) return false
        e= (e+k)%(k+1)
        return true
    }

    fun getFront(): Int {
        if(isEmpty()) return -1
        return buffer[s]
    }

    fun getRear(): Int {
        if(isEmpty()) return -1
        return buffer[(e+k)%(k+1)]
    }

    fun isEmpty(): Boolean {
        return s == e   
    }

    fun isFull(): Boolean {
        return (e-s + k +1) %(k+1) == k
    }

}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * var obj = MyCircularDeque(k)
 * var param_1 = obj.insertFront(value)
 * var param_2 = obj.insertLast(value)
 * var param_3 = obj.deleteFront()
 * var param_4 = obj.deleteLast()
 * var param_5 = obj.getFront()
 * var param_6 = obj.getRear()
 * var param_7 = obj.isEmpty()
 * var param_8 = obj.isFull()
 */
