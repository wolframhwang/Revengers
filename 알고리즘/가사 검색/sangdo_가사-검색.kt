import java.util.*
/*
모든 글자의 합이 100만이기 때문에 Trie로 접두사, 접미사를 관리하면 E-Z하게 풀 수 있음.

TRIE의 기본 문제
*/

class Trie(var cnt:Int = 0, var child:Array<Trie?> = Array<Trie?>(27){null})
class Solution {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        var conv = IntArray(256){0}
        var cnt = 0
        for(i in 'a' ..'z') conv[i.toInt()] = cnt++
        var tries = Array<Trie>(10001){Trie()}
        var tries2 = Array<Trie>(10001){Trie()}
        
        var answer = IntArray(queries.size)
        
        words.forEach{
            str->
            var s = 0
            var e = str.lastIndex
            var cur = tries[str.length]
            var cur2 = tries2[str.length]
            for(i in 1 .. str.length) {
                cur.cnt++
                cur2.cnt++
                
                if(cur.child[conv[str[s].toInt()]] == null) {
                    cur.child[conv[str[s].toInt()]] = Trie()
                }
                cur = cur.child[conv[str[s++].toInt()]]!!
                
                if(cur2.child[conv[str[e].toInt()]] == null) {
                    cur2.child[conv[str[e].toInt()]] = Trie()
                }
                cur2 = cur2.child[conv[str[e--].toInt()]]!!
            }
            cur.cnt++
            cur2.cnt++
        }
        
        for(i in queries.indices) {
            var cnt = 0
            var cur:Trie? = null
            var str = ""
            queries[i].forEach{if(it == '?') cnt++}
            
            if(queries[i][0] == '?') {
                cur = tries2[queries[i].length]
                str = queries[i].substring(cnt, queries[i].length).reversed()
            }
            else {
                cur = tries[queries[i].length]
                str = queries[i].substring(0,queries[i].length-cnt)
            }
            
            for(i in str.indices) {
                if(cur == null) break
                cur = cur!!.child[conv[str[i].toInt()]]
            }
            answer[i] = if (cur == null) 0 else cur!!.cnt
        }
        
        return answer
    }
}
