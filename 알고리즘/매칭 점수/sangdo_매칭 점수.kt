import java.util.*
//this is so 구현하기 어려움

class Solution {
    fun solution(word: String, pages: Array<String>): Int {
        var hash = HashMap<String, Int>()
        var externalLink = Array(pages.size){ArrayList<String>()}
        var linkScores = DoubleArray(pages.size)
        var scores = DoubleArray(pages.size)
        pages.forEachIndexed{
            i,s -> 
            var url = s.split("<head>")[1].split("<meta property=\"og:url\" content=")[1].split("\"")[1]
            hash[url] = i
            var body = s.split("<body>")[1].split("</body>")[0]
            var regex = "<a href=\"\\S+\">".toRegex()
            var matchResult = regex.findAll(body)
            var bb = StringBuilder()
            for(m in matchResult){
                externalLink[i].add(m.value.split("\"")[1])
            }
            regex = "<.+>.*<.+>".toRegex()
            matchResult = regex.findAll(body)
            regex = ">.+<".toRegex()
            var left = 0
            var right = 0
            for(m in matchResult) {
                var match = regex.find(m.value)
                bb.append(body.substring(left, m.range.first))
                match?.let{bb.append(it.value.substring(1,it.value.length-1))}
                left = m.range.last+1
            }
            bb.append(body.substring(left, body.length))
            println(bb.toString())
            regex = "[a-z|A-Z]+".toRegex()
            matchResult = regex.findAll(bb)
            for(c in matchResult) {
                if(c.value.toLowerCase() == word.toLowerCase()) scores[i]+=1.0
            }
        }
        
        var res = Pair(-1.0,-0x3f3f3f3f)
        externalLink.forEachIndexed{
            i,v-> 
            var chk = HashSet<Int>()
            v.forEach{
                s->
                hash[s]?.let{
                    if(it !in chk) linkScores[it] += scores[i]/v.size
                    chk.add(it)
                }
            }
        }
        for(i in pages.indices) {
            var now = Pair(scores[i] + linkScores[i], i)
            println(now)
            if(res.first < now.first) {
                res = now
            }
        }
        return res.second
    }
}
