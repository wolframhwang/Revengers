import java.util.*

class Solution {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0
        var pq = PriorityQueue<Int>{i,j -> priorities[j]-priorities[i]}
        var deque = ArrayDeque<Int>()
        for(i in priorities.indices) {
            pq.add(i)
            deque.add(i)
        }
        
        var order = 0 
        while(pq.isNotEmpty()) {
            var idx = deque.removeFirst()
            var v = priorities[idx]
            if(priorities[pq.peek()] == v){
                order++
                if(idx == location) return order
                pq.poll()
            }
            else {
                deque.addLast(idx)
            }
        }
        return answer
    }
}
