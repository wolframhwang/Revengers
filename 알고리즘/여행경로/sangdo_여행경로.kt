/*
인접점을 전처리 후에 dfs로 찾는다.
*/
import java.util.*

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        var answer = Array<String>(tickets.size+1){""}
        var conv = ArrayList<String>()
        tickets.forEach{
            conv.add(it[0])
            conv.add(it[1])
        }
        var zip = conv.distinct().mapIndexed{i,v -> v to i}.toMap()
        var adj = Array(zip.size){ArrayList<Int>()}
        tickets.mapIndexed{i,v->i}.sortedWith(Comparator{i,j->
            when {
                tickets[i][1] < tickets[j][1] -> -1
                tickets[i][1] > tickets[j][1] -> 1
                else -> 0
        }}).forEach{
            var f = zip[tickets[it][0]]!!
            adj[f].add(it)
        }
        var s = "ICN"
        answer[0] = "ICN"
        var chk = BooleanArray(tickets.size)
        
        fun go(n: String, i: Int):Boolean {
            answer[i] = n
            if(i == tickets.size) return true
            for(nxt in adj[zip[n]!!]) {
                if(chk[nxt] == true) continue
                chk[nxt] = true
                var nextString = tickets[nxt][1]
                if(go(nextString, i+1)) return true
                chk[nxt] = false
            }
            return false
        }
        go("ICN", 0)
        
        return answer
    }
}
