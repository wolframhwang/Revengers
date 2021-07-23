/*
한 문자를 압축하면 무조건 길이가 원본보다 작음
a -> a
aa -> a2
aaa -> a3
...


따라서, 이전 문자와 달라지면 이전 문자를 넣고, 이전 문자의 길이를 세준 다음에(count)
count를 문자로 바꿔주고
바꾼 문자를 chars에 그대로 count만큼 넣어주고 마지막으로 count+1만큼 len을 증가시키면
완료! -> compression 람다식 

*/

class Solution {
    fun compress(chars: CharArray): Int {
        var prev = 'X'
        var count = 0
        var len = 0
        var compression = {
            if(count == 1) {
               chars[len++] = prev 
            }
            else if(count > 1){
               chars[len++] = prev 
               var digit = count.toString()
               digit.forEach{chars[len++] = it}
            }
        }
        chars.forEach {
            if(prev == it){
                count++
            }
            else {
                compression()
                prev = it
               count = 1
            }
        }
        compression()
        return len
    }
}
