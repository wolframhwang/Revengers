// 21.09.29 Solved By Wolfram hwang
// using by DFS

class Solution {
    var adj : [String: [String]] = [:]
    var visit: [String: [Bool]] = [:]
    var limit = 0
    var ans : [[String]] = []
    func recur(_ now: String, _ q: [String], _ cnt : Int) {      
        if ans.count == 1 { return }  
        var qq = q
        qq.append(now)
        var go = false
        if adj[now] != nil {        
            for i in 0..<adj[now]!.count {
                if visit[now]![i] { continue }
                let next = adj[now]![i]
                go = true
                visit[now]![i] = true
                recur(next, qq, cnt + 1)
                visit[now]![i] = false
            }
        }
        if !go,  cnt == limit {
            ans.append(qq)
        }
    }

    func findItinerary(_ tickets: [[String]]) -> [String] {
        let start = "JFK"        
        for str in tickets{
            if adj[str[0]] == nil {
                adj[str[0]] = [str[1]]
                limit += 1
            }else{
                adj[str[0]]?.append(str[1])
                limit += 1
            }
        }

        for (key, value) in adj {
            visit[key] = [Bool](repeating: false, count: value.count)
            adj[key] = value.sorted{ $0 < $1 }
        }
        recur(start, [] , 0)
        //print()
        return ans[0]
    }
}
