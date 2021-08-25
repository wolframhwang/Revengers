/*
간단한 이분탐색!
x분 / time을 모든 times에 대해 수행하면 x분에 대해서 검사할 수 있는 사람들의 수를 구할 수 있다.
따라서, 사람의 수가 같거나 많으면 e를 줄이면 되고, 적으면 s를 늘리면된다.
*/
class Solution {
    fun solution(n: Int, times: IntArray): Long {
        var answer: Long = 0
        var e = 1_000_000_000_000_00L
        var s = 0L
        while(s < e) {
            var t = 0L
            var m = (s+e)/2
            for(i in times) {
                t+= m/i
            }
            if(t < n) s = m + 1
            else e = m
        }
        return s
    }
}
