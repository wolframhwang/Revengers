import java.util.*
/*
갓-분 탐색 풀이
1. 같은 길이를 가진 단어를 한곳에 모두 모은다. (접미사 처리를 위해 거꾸로 바꾼 것의 단어도 따로 관리)
2. 소팅한다
3. query의 길이가 있는 단어들을 모아놓은 곳에서 해당 단어들을 query에서 물음표가 아닌 부분들의 개수만 비교해서
upper bound, lower bound를 돌려서 뺀다.

이유:
이러면 AAABB BBBBB BBBCC BBBDD CCCCC 라는 단어가 있을때, BBB를 upper, lower로 찾으면
lower bound에 의해 BBB가 있는 최소 위치인 BBBBB와  BBB가 있는 바로 다음 위치인 CCCCC가 찾아지게 되고, 이 둘의 차이를 구하면 BBB를 가진 개수를 구할 수 있다.
*/

class Solution {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        var answer = IntArray(queries.size)
        var word = Array(100001){ArrayList<String>()}
        var reverseWord = Array(100001){ArrayList<String>()}
        for(w in words){
            word[w.length].add(w)
            reverseWord[w.length].add(w.reversed())
        }
        word.forEach{arr->arr.sort()}
        reverseWord.forEach{arr->arr.sort()}
        for(i in queries.indices){
            val query = queries[i]
            var count = 0
            query.forEach{count += if (it == '?') 1 else 0}
            var q =  if(query[0] == '?'){query.reversed().substring(0,query.length - count)} else query.substring(0,query.length - count)
            var w = if(query[0] == '?') reverseWord[query.length] else word[query.length]
            var s = 0
            var e = w.size
            while(s < e) {
                var m = (s+e)/2
                if(q > w[m].substring(0,q.length)) s = m+1
                else e = m
            }
            var l = e
            s = 0
            e = w.size
            while(s < e){
                var m = (s+e)/2
                if(q >= w[m].substring(0,q.length)) s = m+1
                else e = m
            }
            answer[i] = e - l 
        }
        return answer
    }
}
