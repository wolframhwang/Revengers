/*
t에 있는 문자들이 들어올때마다 카운트를 하나씩 늘려준다.
또한, 카운트가 들어온 문자들의 개수를 체크한다.
(AABC의 경우 A의 카운트는 2, B는 1, C는 1, 카운트가 있는 문자(flag) = 3 (A,B,C))

투포인터를 이용하여 맨끝과 처음 문자를 i,j로 두고, 포인터가 지나갈때마다 해당 문자의 카운터를 1씩 낮춘다.
만약, 카운터가 0이된다면, 해당 문자는 t에 있는 충족조건을 만족한 것을 의미하므로 flag 또한 1 감소한다.
flag가 0이 되면 t에서 나온 모든 타입이 등장했다는 것을 의미한다.
뒤의 문자에서 조건이 충족되면, 앞에서 문자를 빼면서 조건이 충족되는지 확인하고,
조건이 충족되고 길이가 가장 짧으면 결과값을 업데이트 한다.

시간복잡도 : O(N + M)
*/

class Solution {
    fun minWindow(s: String, t: String): String {
        var flag = 0
        var cnt = IntArray(256)
        t.forEach {
            var v = it.toInt()
            if(cnt[v] == 0) flag++
            cnt[it.toInt()]++
        }
        
        var j = 0
        var res = 0x3f3f3f3f
        var ret = ""
        
        for(i in s.indices) {
            var v = s[i].toInt()
            cnt[v]--
            if(cnt[v] == 0) {
                flag--
            }
            
            if(flag == 0) {
                if(i-j+1 < res) {
                    ret = s.substring(j,i+1)
                    res = i-j+1
                }
            }
            while(j <= i && flag == 0) {
                if(i-j+1 < res) {
                    ret = s.substring(j,i+1)
                    res = i-j+1
                }
                var a = s[j].toInt()
                if(cnt[a] == 0) flag++
                cnt[a]++
                ++j
            }
        }
        return ret
    }
}
