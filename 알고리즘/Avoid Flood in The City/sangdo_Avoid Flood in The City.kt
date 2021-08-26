/*
X호수에 비가 내렸으면, 다음에 비가 올 X의 위치를 구할 수 있다.
만약 0이 나왔으면, 현재 범람직전인 호수들 중에서 다음에 비가 오는 날이 가장 빠른 호수부터 말리면 된다.
따라서, X호수에 비가오는 날짜들을 전처리 해놓고,
i날에 A번 호수에 비가 왔으면 우선순위큐에 다음에 A호수에 비가 올 날짜를 넣어놓고,
i날에 0이 나온다면, 우선순위 큐에서 꺼낸 호수가 가장 비가 빨리 올 호수이고 말려버려야 하는 호수인 것이다.

시간복잡도: O(N log(N))
*/

import java.util.*

class Solution {
    fun avoidFlood(rains: IntArray): IntArray {
        var cnt = HashMap<Int,Int>()
        var deques = rains.groupBy{it}.keys.map{
            it to ArrayDeque<Int>()
        }.toMap()
        rains.forEachIndexed{i,v->deques[v]!!.addLast(i)}
        deques.keys.forEach{deques[it]!!.addLast(0x3f3f3f3f)}
        var pq = PriorityQueue<Pair<Int,Int>>{i,j -> i.first - j.first}
        var chk = HashSet<Int>()
        var res = IntArray(rains.size)
        var st = ArrayDeque<Int>()
        var flood = false
        rains.forEachIndexed{
            i,v->
            res[i] = if(v == 0){
                if(pq.isNotEmpty()) {
                    var a = pq.poll().second
                    chk.remove(a)
                    a
                }
                else 1
            } else{
                if(v in chk) flood = true
                chk.add(v)
                var dq = deques[v]!!
                while(dq.peekFirst() <= i) dq.pollFirst()
                pq.add(dq.peekFirst() to v)
                -1   
            }
            
        }
        return if(flood) intArrayOf() else res
    }
}
