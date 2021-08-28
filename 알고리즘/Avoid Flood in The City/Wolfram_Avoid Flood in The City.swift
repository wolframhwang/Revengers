// 21.08.27 Solved By Wolfram Hwang
// 두가지 풀이 
// 거꾸로 먼저 탐색하여 다음 노드의 기준치를 정해준 뒤 순회하면서 priority queue를 사용해서 넣어주는 방식 -> TLE 왜인지 모르겠음.. C++나 다른언어로 하면 성공하는데 진짜 이해가안감
// enumerate사용한 완전탐색 -> Discussion에 있는거 보고 공부했는데, 이거 그냥완전탐색인데 왜 얘는 되고 pq가 안되는지 도저히 이해할수가없음

// HASH, PQ
class Solution {
    var pq : [(Int,Int)] = [(Int, Int)](repeating: (0,0), count: 100001)
    var idx = 0
    func compare(_ a: Int, _ b :Int) -> Bool {
        return pq[a].0 < pq[b].0
    }
    func push(_ inp: (Int, Int)) {
        var c = 0
        var p = 0
        idx += 1
        pq[idx] = inp
        c = idx; p = c / 2
        while p >= 0, !compare(p, c) {
            let temp = pq[c]; pq[c] = pq[p]; pq[p] = temp
            c = p; p = c / 2
        }
    }

    func pop() {
        pq[1] = pq[idx]
        idx -= 1
        var p = 1; var l = 2; var r = 3; var c = 0
        while l <= idx {
            if r > idx {
                c = l;
            }else { c = compare(l, r) ? l: r}
            if compare(p, c) {break}
            let temp = pq[p]; pq[p] = pq[c]; pq[c] = temp;
            p = c; l = p * 2; r = l + 1
        }
    }

    func avoidFlood(_ rains: [Int]) -> [Int] {
        let n = rains.count
        var next = [Int](repeating: -1, count: n)
        var map: [Int: Int] = [Int: Int]()
        
        for i in (0..<n).reversed() {
            if rains[i] > 0 {
                if map[rains[i]] == nil {
                    map[rains[i]] = i
                }else{
                    next[i] = map[rains[i]]!
                    map[rains[i]] = i
                }
            }
        }
        var ans : [Int] = [Int](repeating: -1, count: n)
        for i in 0..<n {
            if rains[i] > 0 {
                if next[i] != -1 {
                    push((next[i], rains[i]))
                }
            }else{
                if idx > 0 {
                    let top = pq[1]
                    if top.0 < i {
                        return []
                    }
                    pop()
                    ans[i] = top.1
                }else{
                    ans[i] = 1
                }
            }
        }
        
        //print(ans)
        return idx == 0 ? ans: []
    }
}

//HASH
class Solution {
    func avoidFlood(_ rains: [Int])->[Int] {
        let len = rains.count
        var res = [Int](repeating: -1, count: len)
        var map: [Int: Int] = [:]
        var dry: [Int] = []
        for i in 0..<len {
            if rains[i] == 0 {
                res[i] = 1
            }
        }
        
        for (i,n) in rains.enumerated() {
            if n == 0 { dry.append(i); continue}
            else{
                if map[n] == nil {
                    map[n] = i
                }else{
                    if dry.count > 0 {
                        var isFind = false
                        for (j, day) in dry.enumerated() {
                            if day > map[n]! {
                                let remove = dry.remove(at: j)
                                res[remove] = n
                                map[n] = i
                                isFind = true
                                break
                            }
                        }
                        if isFind == false {
                            res = []
                            break
                        }
                    }
                    else{
                        res = []
                        break
                    }
                }
            }
        }
        return res
    }
}
