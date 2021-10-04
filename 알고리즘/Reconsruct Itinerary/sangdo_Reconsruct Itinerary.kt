class Solution {
    fun makeRes(list:ArrayDeque<Int>, now:Int, chk:Array<ArrayList<Int>>, adj:Array<ArrayList<Int>>, cnt:Int): Boolean{
        list.addLast(now)
        if(cnt == 0) return true
        for(i in adj[now].indices) {
            if(chk[now][i] == 1) continue
            chk[now][i] = 1
            var nxt = adj[now][i]
            var res = makeRes(list, nxt, chk, adj, cnt-1)
            if(res) return true
            chk[now][i] = 0
        }
        list.pollLast()
        return false
    }
    
    fun findItinerary(tickets: List<List<String>>): List<String> {
        var hash = HashMap<String, Int>()
        var inv = HashMap<Int,String>()
        var cnt = 0
        tickets.forEach{s->
            if(s[0] !in hash) {
                hash.put(s[0] , cnt)
                inv.put(cnt, s[0])
                cnt++
            }
            if(s[1] !in hash) {
                hash.put(s[1] , cnt)
                inv.put(cnt, s[1])
                cnt++
            }
        }
        var adj = Array<ArrayList<Int>>(cnt){ArrayList<Int>()}
        var chk = Array<ArrayList<Int>>(cnt){ArrayList<Int>()}
        for(i in tickets) {
            var a = hash[i[0]]!!
            var b = hash[i[1]]!!
            adj[a].add(b)
            chk[a].add(0)
        }
        for(i in 0 until cnt) {
            adj[i].sortWith(Comparator{a,b -> 
                    if(inv[a]!! < inv[b]!!) -1
                    else if(inv[a]!! > inv[b]!!) 1
                    else 0
                }
            )
        }
        
        var res = ArrayDeque<Int>()
        makeRes(res, hash["JFK"]!!, chk, adj, tickets.size)
        var a = ArrayList<String>()
        res.forEach{a.add(inv[it]!!)}
        return a
    }
}
