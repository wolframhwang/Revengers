class Solution {
    var minDepth = 0x3f3f3f
    lateinit var adj : Array<ArrayList<Int>>
    lateinit var chk : IntArray
    lateinit var dp: IntArray
    lateinit var idx: IntArray
    lateinit var second: IntArray
    fun makeTree(n:Int):Int {
        var secondIdx = -1
        for(i in adj[n].indices) {
            var nxt = adj[n][i]
            if(chk[nxt] == 1) continue
            chk[nxt] = 1
            var res = makeTree(nxt)
            if(res > second[n]) {
                second[n] = res
                secondIdx = nxt
            }
            if(second[n] > dp[n]) {
                idx[n] = secondIdx
                var t = dp[n]
                dp[n] = second[n]
                second[n] = t
            }
        }
        return dp[n] + 1
    }
    
    fun makeRes(list:ArrayList<Int>, n:Int, d:Int) {
        var len = Math.max(d, dp[n])
        for(nxt in adj[n]) {
            if(chk[nxt] == 2) continue
            chk[nxt] = 2
            if(nxt == idx[n]) {
                makeRes(list, nxt, Math.max(second[n],d)+1)
            }
            else makeRes(list, nxt, Math.max(dp[n],d)+1)
        }
        
        if(len < minDepth) {
            minDepth = len
            list.clear()
            list.add(n)
        }
        else if(len == minDepth) {
            list.add(n)
        }
    }
    
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        minDepth = 0x3f3f3f3f
        adj = Array<ArrayList<Int>>(n){ArrayList()}
        chk = IntArray(n)
        dp = IntArray(n)
        idx = IntArray(n)
        second = IntArray(n)
        chk[0] = 1
        edges.forEach{
            var f = it[0]
            var t = it[1]
            adj[f].add(t)
            adj[t].add(f)
        }
        makeTree(0)
        var li = ArrayList<Int>()
        chk[0] = 2
        makeRes(li, 0, 0)
        
        return li
    }
}
