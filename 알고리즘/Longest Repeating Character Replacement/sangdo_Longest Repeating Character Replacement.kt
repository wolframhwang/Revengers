class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        var res = 0
        for(c in 'A' .. 'Z') {
            var e = 0
            var diff = 0
            s.forEachIndexed {
                i,v ->
                while (e < s.length && diff <= k) {
                    if(s[e] != c){
                        if(diff == k) break
                        diff++
                    }
                    e++
                }
                res = Math.max(res,e-i)
                if(s[i] != c) diff--
            }
        }
        return res
    }
}
