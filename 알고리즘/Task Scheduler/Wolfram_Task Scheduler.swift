// 21.08.31 solved By Wolfram Hwang
// 우선순위큐 쓰면 왜터지ㅡㄴㄴ지모르겠음;
class Solution {
    func leastInterval(_ tasks: [Character], _ n: Int) -> Int {
        var map: [Character: Int] = [:]
        for c in tasks {
            if map[c] == nil {
                map[c] = 1
            }else{
                map[c]? += 1
            }
        }
        let len = n + 1
        
        var ans = 0
        while true {
            var q: [(Character, Int)] = []
            var front = 0
            var isDone = true
            for (key, value) in map {
                if value > 1 {
                    isDone = false
                }
                if value > 0 {
                    q.append((key, value))
                } 
            }
            q = q.sorted { $0.1 > $1.1}
            //print(ans, q)
            if !isDone  {
                ans += len
            }else{ ans += q.count }
            while front != len, front != q.count {
                let c = q[front].0
                let v = q[front].1
                front += 1
                map[c]? -= 1
            }
            if isDone { break }
        }
        return ans
    }
}
