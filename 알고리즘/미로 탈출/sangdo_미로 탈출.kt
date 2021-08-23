/*
다익쓰트라 헤햏 (하지만 거기에 비트를 곁들인)
시간복잡도: 2^(traps.size)* N *log(N)
트랩 i가 한 번 밟힐때마다 0(순방향) 1(역방향) 으로 놓을 수 있기 때문에 그래프의 상태는 2^(traps.size)가지가 나올 수 있다. 
*/
import java.util.*

data class INFO(var p:Int, var s:Int, var i:Int)

class Solution {
    fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
        var pq =PriorityQueue<INFO>{i,j -> i.p - j.p}
        var adj = Array(1.shl(traps.size)){Array(n){ArrayList<Int>()}}
        var cost = Array(1.shl(traps.size)){Array(n){ArrayList<Int>()}}
        var isTrap = BooleanArray(n)
        var hashMap = HashMap<Int,Int>()
        traps.forEachIndexed{
            i,v->
            hashMap[v-1]=i
            isTrap[v-1] = true
        }
        
        roads.forEach {
            var a = it[0]-1
            var b = it[1]-1
            var c = it[2]
            for(bit in 0 until 1.shl(traps.size)) {
                var cond1 = if(a in hashMap && 1.shl(hashMap[a]!!).and(bit) !=0) 1 else 0
                var cond2 = if(b in hashMap && 1.shl(hashMap[b]!!).and(bit) !=0) 1 else 0
                    if(cond1.xor(cond2) == 1) {
                        adj[bit][b].add(a)
                        cost[bit][b].add(c)
                    }
                    else{
                        adj[bit][a].add(b)
                        cost[bit][a].add(c)
                    }
            }
        }
        var dist = Array(1.shl(traps.size)){IntArray(n+1){0x3f3f3f3f}}
        dist[0][start-1] = 0
        pq.add(INFO(0, 0, start-1))
        while(pq.isNotEmpty()) {
            val (c, s, i) = pq.poll()
            if(dist[s][i] < c) continue
            if(i == end-1) return c
            for(idx in adj[s][i].indices) {
                var ni = adj[s][i][idx]
                var ns = if(isTrap[ni]) s.xor(1.shl(hashMap[ni]!!)) else s
                var nc = c + cost[s][i][idx]
                if(dist[ns][ni] > nc) {
                    dist[ns][ni] = nc
                    pq.add(INFO(nc,ns,ni))
                }
            }
        }
        return -1
    }
}
