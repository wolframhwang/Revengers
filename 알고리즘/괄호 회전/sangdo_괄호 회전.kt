/*
괄호 검사는 스택이 딱이야
O(N^2)
*/
import java.util.*

class Solution {
    fun solution(s: String): Int {
        var pair = Array<Char>(256){' '}
        pair['{'.toInt()] = '}'
        pair['('.toInt()] = ')'
        pair['['.toInt()] = ']'
        var answer: Int = 0
        for(i in s.indices){
            var res = 1
            var stack = Stack<Char>()
            
            for(j in s.indices) {
                var idx = (i+j)%s.length
                res = when(s[idx]) {
                    '{', '[', '('-> {
                        stack.add(s[idx])
                        1
                    }
                    else -> if(stack.isEmpty() || pair[stack.pop().toInt()] != s[idx]) 0 else 1
                }
                if(res == 0) break
            }
            answer += if(stack.isEmpty()) res else 0
        }
        return answer
    }
}
