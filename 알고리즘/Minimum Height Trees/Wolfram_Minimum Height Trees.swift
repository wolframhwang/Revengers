// 21. 09. 22 Not Solved
// 도저히 토폴로지 소팅 생각안나서 답봤음
// ;; ㅈㅅㅋㅋ;
class Solution {
    func findMinHeightTrees(_ n: Int, _ edges: [[Int]]) -> [Int] {
        if n == 0 { return [] }
        if n == 1 { return [0] }
        var adj = Array(repeating: Set<Int>(), count: n)
        var inDegrees = Array(repeating: 0, count: n)
        var leaves = [Int]()
        var candidateCount = n
        
        for edge in edges {
            let first = edge[0]
            let second = edge[1]
            adj[first].insert(second)
            adj[second].insert(first)
            inDegrees[first] += 1
            inDegrees[second] += 1
        }
        
        for (node, degree) in inDegrees.enumerated() {
            if degree == 1 {
                leaves.append(node)
            }
        }
        while candidateCount > 2 {
            candidateCount -= leaves.count
            
            var tempLeaves = [Int]()
            for leave in leaves {
                let neighbor = adj[leave].first!
                adj[neighbor].remove(leave)
                inDegrees[leave] -= 1
                inDegrees[neighbor] -= 1
                
                if inDegrees[neighbor] == 1 {
                    tempLeaves.append(neighbor)
                }
            }
            leaves = tempLeaves
        }
        
        return leaves
    }
}
