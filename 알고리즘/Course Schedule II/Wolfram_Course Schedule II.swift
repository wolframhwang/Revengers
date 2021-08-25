// 21.08.25 Solved By Wolfram Hwang
// 
class Solution {
    func findOrder(_ numCourses: Int, _ prerequisites: [[Int]]) -> [Int] {
        let layer :[Int] = []
        var adj: [[Int]] = [[Int]](repeating: layer, count: numCourses)
        var q: [Int] = []
        var front = 0
        var result: [Int] = [Int] (repeating: 0, count: numCourses)
        var indegree: [Int] = [Int](repeating: 0, count: numCourses)

        for i in 0..<prerequisites.count {
            let prev = prerequisites[i][1]
            let next = prerequisites[i][0]
            adj[prev].append(next)
            indegree[next] += 1;
        }

        for i in 0..<numCourses {
            if indegree[i] == 0 { q.append(i) }
        }

        var ret = 0
        while (q.count != front) {
            let cur = q[front]
            front += 1

            result[ret] = cur
            ret += 1
            for c in adj[cur] {
                indegree[c] -= 1
                if indegree[c] == 0 { q.append(c) }
            }
        }

        if ret != numCourses { return [] }
        return result
    }
}
