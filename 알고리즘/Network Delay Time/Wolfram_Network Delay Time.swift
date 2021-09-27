// 21.09.27 Solved By Wolfram Hwang
// by Using BFS Algorithm
// expected : silver 3
class Solution {
    func bfs(_ start: Int, _ adj: [[(Int, Int)]])->Int {
        var q : [Int] = []
        var dist: [Int] = [Int](repeating: 987654321, count: adj.count + 1)
        var rear = 0
        var front = 0
        q.append(start)
        rear += 1
        dist[start] = 0
        while rear != front {
            let now = q[front]
            front += 1
            for node in adj[now] {
                let next = node.0
                let weight = node.1
                if dist[next] > dist[now] + weight {
                    dist[next] = dist[now] + weight
                    q.append(next)
                    rear += 1
                }
            }
        }
        var ret = 0
        for i in 1..<adj.count {
            if dist[i] == 987654321 {
                return 0
            }
            if ret < dist[i] {
                ret = dist[i]
            }
        }
        return ret
    }
    func networkDelayTime(_ times: [[Int]], _ n: Int, _ k: Int) -> Int {
        var adj : [[(Int, Int)]] = [[(Int, Int)]](repeating: [(Int, Int)](), count: n + 1)
        // tiems 0: source, 1: destination 2: weight
        // n : Number of Node
        // k : signal Soruce Node Number
        for t in times {
            let s = t[0]
            let d = t[1]
            let w = t[2]
            adj[s].append((d, w))
        }
        let ans = bfs(k, adj)
        return ans == 0 ? -1 : ans
    }
}
