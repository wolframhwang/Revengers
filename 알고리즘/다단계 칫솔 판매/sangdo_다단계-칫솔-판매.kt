import java.util.*

/*
한사람당 이익은 최대 1만원.
트리를 타고 0.1배가 되기 때문에 amount 1가 최대 영향을 끼칠 수 있는 부모의 노드는 4개이다. (자기 노드 포함하면 5개)
따라서 amount는 10만이므로, 단순히 amount를 순회하면서 이익금을 계산하면 5*N의 시간복잡도 정도가 나온다.
O(N)

문제를 살짝만 바꾸면 위상정렬 문제
*/

class Solution {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        var eId = HashMap<String,Int>()
        var cnt = 0
        enroll.forEach{eId[it] = cnt++}
        var parent = IntArray(cnt+1)
        eId["-"] = cnt
        var answer: IntArray = IntArray(cnt)
        
        for(i in enroll.indices) {
            eId[enroll[i]] = i
            var id = i
            var recommand = eId[referral[i]]!!
            parent[id] = recommand
        }
        for(i in amount.indices) {
            var id = eId[seller[i]]!!
            var earn = amount[i]*100
            while(earn != 0 && id != cnt) {
                var pId = parent[id]
                var extra = earn/10
                answer[id] += earn-extra
                
                earn = extra
                id = pId
            }
        }
        
        return answer
    }
}
