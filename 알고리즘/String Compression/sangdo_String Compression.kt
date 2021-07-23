/*
한 문자를 압축하면 무조건 길이가 원본보다 같거나 작음
a -> a
aa -> a2
aaa -> a3
...
따라서, 기존의 문자열에 압축된 문자열을 넣으면서 진행해도 현재 진행하는 압축 알고리즘에는 전혀 영향이 가지 않기 때문에 새로 배열을 할당할 필요가 없다.

ex)
aaaaxxxy진행
- aaa를 a3로 압축
압축결과
a3axxxy

- xxx를 압축
결과 a3x3xxy
- y를 압축
결과 a3x3yxy
return 값은 a3x3y이므로 5를 해주면 됨.

방법은 이전 문자와 달라지면 이전 문자의 길이를 세준 다음에(count) count를 문자열로 바꿔준다.
이전 문자와 문자열로 바뀐 count를 chars에 그대로 넣어주고 마지막으로 압축 알고리즘이 진행된 커서(len)를 count+1만큼 증가시키면, 
기존 배열에 겹치지 않고 압축이 가능하다.
-> compression 람다식 참고

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
