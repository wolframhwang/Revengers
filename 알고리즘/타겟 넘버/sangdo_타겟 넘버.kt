/*
비트 이용 
0 ~ (2^n)
i비트가 1이면 i번 number 더하기
i비트가 0이면 i번 number 빼기
sum이 target과 같은지 
*/
class Solution {
    fun solution(numbers: IntArray, target: Int): Int {
        var answer = 0
        for(i in 0 .. (1 shl numbers.size)){
            var b = i
            var sum = 0
            for(j in 0 until numbers.size){
                if((b shr j) and 1 == 1){
                    sum += numbers[j]
                }
                else sum-= numbers[j]
            }
            answer += if(sum == target) 1 else 0
        }
        return answer
    }
}
