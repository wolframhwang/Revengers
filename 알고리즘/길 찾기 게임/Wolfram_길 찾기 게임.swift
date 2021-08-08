// 21/08/08 Solved by Wolfram Hwang
// 이분탐색으로 자식 찾아서 연결해준뒤..
// 전위, 후위탐색하면됌

import Foundation
var st : [Set<Int>] = [Set<Int>](repeating: Set<Int>(), count: 100001)
var map : [Int: [Int]] = [Int: [Int]]()
var height: Set<Int> = Set<Int>()
var hei = [Int]()
var dict : [Int: Int] = [Int: Int]()
var node : [[Int]] = []
var par: [Int] = []
var lar: [Int] = []
func post(_ now: Int) {
    par.append(now)
    for i in node[now] {
        if i == -1 {
            continue
        }
        post(i)
    }
}
func last(_ now: Int) {
    for i in node[now] {
        if i == -1 {
            continue
        }
        last(i)
    }
    lar.append(now)
}
func lower(_ value: Int, _ hgt: Int)->Int {
    var l = 0
    var h = st[hgt].count
    while l < h {
        let mid = (l + h) / 2
        if  value < map[hgt]![mid] {
            h = mid
        }else{
            l = mid + 1
        }
    }
    return l
}
func upper(_ value: Int, _ hgt: Int)->Int {
    var l = 0
    var h = st[hgt].count
    while l < h {
        let mid = (l + h) / 2
        if value >= map[hgt]![mid] {
            l = mid + 1
        }else{
            h = mid
        }
    }
    return l
}
func go(_ pnx: Int, _ pxx: Int, _ now: Int, _ hegt: Int) {
    if hegt >= hei.count {
        return
    }
    let hgt = hei[hegt]
    let lb = lower(now, hgt)
    var ll = lb - 1
    if ll < 0 {
        ll = 0
    }
    let lv = map[hgt]![ll]
    
    var uu = lb
    if uu >= map[hgt]!.count {
        uu = map[hgt]!.count - 1
    }
    let uv = map[hgt]![uu]
    node[dict[now]!] = [Int](repeating: -1, count: 2)
    if lv > pnx, lv < now {
        node[dict[now]!][0] = dict[lv]!
        go(pnx, now, lv, hegt + 1)
    }
    if uv > now, uv < pxx {
        node[dict[now]!][1] = dict[uv]!
        go(now, pxx, uv, hegt + 1)
    }
}
func solution(_ nodeinfo:[[Int]]) -> [[Int]] {
    var cnt = 1
    for nd in nodeinfo {
        let x = nd[0]
        let y = nd[1]
        dict[x] = cnt
        st[y].insert(x)
        height.insert(y)
        cnt += 1
    }
    hei = height.sorted {
        s1, s2 in
        s1 > s2
    }
    let layer = [Int]()
    node = [[Int]](repeating: layer, count: cnt + 1)
    var st1 = st[hei[0]].sorted()
    for i in hei {
        map[i] = [Int]()
        map[i] = st[i].sorted()
    }
    go(-1, 100001, st1[0], 1)
    
    
    var ans = [[Int]]()
    post(dict[st1[0]]!)
    last(dict[st1[0]]!)
    ans.append(par)
    ans.append(lar)
    return ans
}
