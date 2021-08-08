/*
처음에 단어들의 쌍을 찾아서 인접할 수 있는지 여부를 확인한 뒤에
시작 지점에서 갈 수 있는 단어들을 큐에 넣고,
BFS를 이용해 dest까지 갈 수 있는지 찾는다.

단어 쌍의 인접 여부를 확인해야하기 때문에 O(N^2)의 시간복잡도를 가진다.
*/

import java.util.*

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        var adj = Array(words.size){ArrayList<Int>()}
        var d = -1
        for(i in words.indices) {
            if(words[i] == target) {
                d = i
                break
            }
        }
        var cango = {i:String,j:String -> Boolean
            var diff = 0
            for(c in i.indices){
                if(i[c] != j[c]) diff++
            }
            diff == 1
        }
        
        for(i in words.indices){
            for(j in i+1 .. words.lastIndex) {
                if(cango(words[i], words[j])) {
                    adj[i].add(j)
                    adj[j].add(i)
                }
            }
        }
        var Q: Queue<Int> = LinkedList()
        var chk = IntArray(words.size)
        for(i in words.indices) {
            if(cango(begin, words[i])) {
                Q.add(i)
                chk[i] = 1
            }
        }
        var cnt = 1
        while(Q.isNotEmpty()) {
            var count = Q.size
            while(count != 0) {
                count--
                var now = Q.poll()
                if(now == d) return cnt
                for(nxt in adj[now]) {
                    if(chk[nxt] == 1) continue
                    chk[nxt] = 1
                    Q.add(nxt)
                }
            }
            cnt++
        }
        return 0
    }
}
