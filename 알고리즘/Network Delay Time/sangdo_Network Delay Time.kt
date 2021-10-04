class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        var Q:Queue<Int> = LinkedList()
        var t = IntArray(n+1){0x3f3f3f3f}
        t[k] = 0
        var chk = IntArray(n+1)
        Q.add(k)
        var cnt = 0
        chk[k] = 1
        var adj = Array<ArrayList<Pair<Int,Int>>>(n+1){ArrayList<Pair<Int,Int>>()}
        for (i in times) {
            var f = i[0]
            var t = i[1]
            var w = i[2]
            adj[f].add(t to w)
        }
        while(Q.isNotEmpty()) {
            var now = Q.poll()
            chk[now] = 1
            for((nxt, w) in adj[now]) {
                chk[nxt] = 1
                if(t[now] + w < t[nxt]) { 
                    t[nxt] = t[now] + w
                    Q.add(nxt)
                }
            }
        }
        var ret = 0
        for(i in 1 .. n) ret = Math.max(t[i],ret)
        chk.forEach{cnt+= it}
        return if(ret == 0x3f3f3f3f) -1 else ret
    }
}
