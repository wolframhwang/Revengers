// 21.08.28 Solved By wolfram Hwang
// 옛날에 풀었던 문제.. 아무리생각해도 다른방법으로는 풀기힘듬
import Foundation
var ans : [String] = [String]()
var rans : [String] = [String]()
var limit: Int = 0
var graph: [String: [String]] = [String: [String]]()
var visit: [String: [Bool]] = [String: [Bool]]()
func go(_ now : String, _ count : Int) {
    if let arr = graph[now] {
        let len = arr.count
        for i in 0..<len {
            if visit[now]![i] == false {
                visit[now]![i] = true
                ans.append(arr[i])
                if count + 1 == limit {
                    rans = ans
                    return
                }
                go(arr[i], count + 1)
                ans.popLast()
                visit[now]![i] = false
            }
        }
    }
}
func solution(_ tickets:[[String]]) -> [String] {
    for node in tickets {
        if let arr = graph[node[0]] {
            graph[node[0]]!.append(node[1])
            limit += 1
        }else{
            graph[node[0]] = [node[1]]
            limit += 1
        }
    }
    for (key, value) in graph {
        graph[key] = value.sorted()
        visit[key] = [Bool](repeating: false, count: graph[key]!.count)
    }
    ans.append("ICN")
    go("ICN", 0)
    return rans
}
