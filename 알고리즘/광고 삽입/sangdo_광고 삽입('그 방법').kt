/*
lazy segment tree를 이용한다!!
호모나 세상에!
*/

class Solution {
    var segment = LongArray(360001*4)
    var lazy = LongArray(360001*4)
    fun prop(i:Int, s:Int, e:Int) {
        if(s + 1 != e) {
            lazy[i*2] += lazy[i]
            lazy[i*2+1] += lazy[i]
        }
        lazy[i] = 0
    }
    
    fun query(i:Int, l:Int, r:Int, s:Int, e:Int): Long {
        segment[i] += lazy[i]*(e-s)
        prop(i, s, e)
       if(l <= s && e <= r) {
           return segment[i]
       }
       if(r <= s || e <= l) return 0
        var m = (s+e)/2
        return query(i*2,l,r,s,m) + query(i*2+1,l,r,m,e)
    }
    
    fun add(i:Int, l:Int, r:Int, s:Int, e:Int, n:Int): Long {
        segment[i] += lazy[i]*(e-s)
        prop(i,s,e)
        if(l <= s && e <= r) {
            lazy[i] += n.toLong()
            segment[i] += lazy[i]*(e-s)
            prop(i,s,e)
            return segment[i]
        }
        if(r <= s || e <= l) {
            return  segment[i]
        }
        val m = (s+e)/2
        segment[i] = add(i*2, l,r,s,m,n) + add(i*2+1,l,r,m,e,n)
        return segment[i]
    }
    
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        var getTime = {str:String -> Int
            var t = str.split(":")
            t[0].toInt()*3600 + t[1].toInt()*60 + t[2].toInt()
           }
        var stringTime = {
            t:Int -> String
            val h = t/3600
            val m = t%3600/60
            val s = t%60
            (h/10).toString() + (h%10).toString() + ":" + (m/10).toString() + (m%10).toString() + ":" + (s/10).toString() + (s%10).toString()
        }
        var end = getTime(play_time)
        var adv = getTime(adv_time)
        
        segment = LongArray((end+1)*4)
        lazy = LongArray((end+1)*4)
        
        for(l in logs) {
            var t = l.split("-")
            
            var s = getTime(t[0])
            var e = getTime(t[1])
            add(1, s,e,0,end+1,1)
        }
        var res = 0L
        var answer = ""
        
        for(i in IntRange(0, end-adv)) {
           	var num = query(1, i, i+adv, 0, end+1)
            
            if(num > res) {
                answer = stringTime(i)
                res = num
            }
        }
        
        return answer
    }
}
