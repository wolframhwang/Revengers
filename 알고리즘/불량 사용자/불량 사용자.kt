/*
미리 밴 id가 가능한 목록을 필터링해서 인덱스로 저장.
이후로 비트를 이용해서 같은 아이디가 중복되어서 밴됐는지 체크,
마지막에 set에 모든 banned id를 비트화 한 것을 저장.
set의 사이즈를 출력하면 완료!
*/

class Solution {
    var answer = HashSet<Int>()
    fun dfs(chk: Int, validList: Array<ArrayList<Int>>, i: Int): Int {
        if(i == validList.size) {
            answer.add(chk)
            return 0
        }
        for(j in validList[i].indices) {
            if(1.shl(validList[i][j]).and(chk) != 0) continue
            dfs(chk.or(1.shl(validList[i][j])), validList, i+1)
        }
        return answer.size
    }
    
    fun isValid(str1: String, str2: String): Boolean {
        if(str1.length != str2.length) return false
        str1.forEachIndexed{
            i,v -> if(str2[i] != '*' && str1[i] != str2[i]) return false
        }
        return true
    }
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        var answer = 0
        var validStrings = Array(banned_id.size){ArrayList<Int>()}
        user_id.forEachIndexed {
            j, s1-> banned_id.forEachIndexed{i, s2-> if(isValid(s1,s2)) validStrings[i].add(j)}
        }
        return dfs(0, validStrings, 0)
    }
}
