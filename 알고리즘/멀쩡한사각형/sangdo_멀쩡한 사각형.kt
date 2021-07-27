/*
대각선이 N+M-1개를 차지한다는걸 발견하지 못했다..
나의 패배다.. 
*/

class Solution {
    fun solution(w: Int, h: Int): Long {
        var answer: Long = 0
        var W = Math.max(w,h).toLong()
        var H = w.toLong()+h.toLong()-W
        var r = W%H
        var a = W
        var b = H
        while(r != 0L){
            a = b
            b = r
            r = a%b
        }
        W /= b
        H /= b
        answer = W+H-1
        answer*= -b
        answer += W*b*H*b
 
        return answer
    }
}
