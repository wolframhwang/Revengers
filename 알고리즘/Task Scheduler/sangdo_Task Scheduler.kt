class Solution {
    fun leastInterval(tasks: CharArray, n: Int): Int {
        var cnt = IntArray(256)
        var t = IntArray(256)
        
        tasks.forEach {
            cnt[it.toInt()]++
        }
        
        var order = {i:Char, j:Char -> if(t[i.toInt()] != t[j.toInt()]) t[i.toInt()] - t[j.toInt()] else cnt[j.toInt()] - cnt[i.toInt()]}
        
        var time = 0
        var allCnt = tasks.size
        t[0] = 0x3f3f3f3f
        while(allCnt != 0) {
            var mmax = 0.toChar()
            for(c in 'A' .. 'Z') {
                if(cnt[c.toInt()] == 0) continue
                if(t[c.toInt()] < time+1) t[c.toInt()] = time+1
                if(order(c,mmax) < 0) {
                    mmax = c
                }
            }
            time = Math.max(time, t[mmax.toInt()])
            cnt[mmax.toInt()]--
            t[mmax.toInt()] = time + n + 1
            
            allCnt--
        }
        return time
    }
}
