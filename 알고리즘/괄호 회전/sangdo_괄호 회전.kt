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
            var res = true
            var stack:Stack<Char> = Stack()
            for(j in s.indices) {
                var idx = (i+j)%s.length
                when(s[idx]) {
                    '{', '[', '('-> stack.add(s[idx])
                    else -> {
                        if(stack.isEmpty()){
                            res= false
                        }
                        else{
                            if(pair[stack.pop().toInt()] != s[idx]) {
                                res = false
                            }
                        }
                    }
                }
                if(!res) break
            }
            answer += if(res && stack.isEmpty()) 1 else 0
        }
        return answer
    }
}
