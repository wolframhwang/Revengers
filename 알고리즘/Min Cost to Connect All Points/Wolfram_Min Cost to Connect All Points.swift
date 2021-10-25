// 21.10.25 solved by Wolfram Hwang
// Using Minimum spanning Tree
// 오랜만에 푼 문제.. 메모리 사용량이 상당히 높은데 줄일방법을 좀 찾아봐야할듯
struct Edge {
    var node: [Int] = [0,0]
    var value: Int = 0
    init(_ _node: [Int], _ _value: Int){
        node = _node
        value = _value
    }
}

class Solution {
    var arr: [Edge] = []
    var parent: [Int] = []
    func getParent(_ a: Int) -> Int {
        if parent[a] == a {
            return a
        }
        parent[a] = getParent(parent[a])
        return parent[a]
    }

    func unionParent(_ a: Int, _ b: Int) {
        var A = getParent(a);
        var B = getParent(b);
        if A < B { parent[B] = A;}
        else { parent[A] = B; }
    }

    func findParent(_ a: Int, _ b: Int)-> Int {
        var A = getParent(a)
        var B = getParent(b)
        if A == B { return 1 }
        else { return 0 }
    }

    func minCostConnectPoints(_ points: [[Int]]) -> Int {
        parent = [Int](repeating: 0, count: points.count + 1)
        for i in 0..<points.count {
            parent[i] = i
        }
        for i in 0..<points.count {
            for j in (i + 1)..<points.count {
                let dist = abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1])
                let inp = Edge([i, j], dist)
                arr.append(inp)
            }
        }
        arr = arr.sorted { $0.value < $1.value }
        var answer = 0
        for i in 0..<arr.count {
            if findParent(arr[i].node[0], arr[i].node[1]) == 0 {
                answer += arr[i].value
                unionParent(arr[i].node[0], arr[i].node[1])
            } 
        }
        return answer
    }
}
