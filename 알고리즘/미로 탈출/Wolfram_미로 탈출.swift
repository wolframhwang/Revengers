// 21.08.21 Solved By Wolfram Hwang
// 난이도가 좀 있는 다익스트라 문제입니다.
// 비트따라서 이동할수 있는 visit 의 Row크기를 (1 << traps 카운트 크기) 만큼 해줘서 얻어냈고
// 비트 따라 움직이는 것을 구현해두었습니다.

import Foundation

var pq : [(Int,Int, Int)] = [(Int, Int, Int)](repeating: (0,0, 0), count: 100001)
var idx = 0
func compare(_ a: Int, _ b :Int) -> Bool {
    return pq[a].0 <= pq[b].0
}
func push(_ inp: (Int, Int, Int)) {
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

var mapping: [Int: Int] = [Int: Int]()
var map: [[(Int, Int, Bool)]] = []
var visit: [[Int]] = []
func choicedir(_ bit: Int, _ id1: Int, _ id2: Int) -> Bool {
    var v1 = 0
    var v2 = 0
    if mapping[id1] != nil {
        if bit & (1 << mapping[id1]!) > 0 {
            v1 = 1
        }
    }
    if mapping[id2] != nil {
        if bit & (1 << mapping[id2]!) > 0 {
            v2 = 1
        }
    }
    if v1 == v2 {
        return false
    }
    return true
}
func dijkstra(_ start: Int, _ end: Int) {
    var bit = 0
    if mapping[start] != nil {
        bit |= (1 << mapping[start]!)
    }
    visit[0][start] = 0
    push((0, start, bit))
    while idx != 0 {
        let now = pq[1].1
        let nowCost = pq[1].0
        var trapInfo = pq[1].2
        if mapping[now] != nil {//누르는 거는 해당 위치에 진입 했을떄 
            let bitinfo = (1 << mapping[now]!)
            if trapInfo & bitinfo > 0{
                trapInfo -= bitinfo
            }else {
                trapInfo |= bitinfo
            }
        }
        pop()
        for road in map[now] {
            let next = road.0
            if choicedir(trapInfo, now, next) != road.2 { continue }
            let nextCost = road.1 + nowCost
            if visit[trapInfo][next] > nextCost {
                visit[trapInfo][next] = nextCost
                push((nextCost, next, trapInfo))
            }
        }
    }
}

func solution(_ n:Int, _ start:Int, _ end:Int, _ roads:[[Int]], _ traps:[Int]) -> Int {
    let INF = 987654321
    let l2 = [Int](repeating: INF, count: n + 1)
    visit = [[Int]](repeating: l2, count: (1 << traps.count) )
    for i in 0..<traps.count {
        mapping[traps[i]] = i
    }
    let layer = [(Int, Int, Bool)]()
    for _ in 0...n {
        map.append(layer)
    }
    
    for road in roads {
        let s = road[0]
        let e = road[1]
        let c = road[2]
        map[s].append((e,c, false))
        if mapping[s] != nil || mapping[e] != nil {
            map[e].append((s, c, true))
        }
    }
    dijkstra(start, end)
    var ans = INF
    for i in 0...(1 << traps.count)-1 {
        if ans > visit[i][end] {
            ans = visit[i][end]
        }
    }
    return ans
}
