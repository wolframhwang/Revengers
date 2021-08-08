// 21/08/09 Solved by Wolfram Hwang
// 간단한 재귀함수 문제

import Foundation

var visit: [Bool] = []
var tg : String = ""
var wd : [String] = []
var ans = 987654321
func recur(_ str: String, _ cnt : Int) {
    if ans < cnt {
        return
    }
    if str == tg {
        if ans > cnt {
            ans = cnt
        }
        return
    }
    var cg : [Character] = []
    for c in str {
        cg.append(c)
    }
    var sidx = 0
    for s in wd {
        if visit[sidx] == true {
            sidx += 1
            continue
        }
        var scnt = 0
        var idx = 0
        for c in s {
            if c != cg[idx]{
                scnt += 1
            }
            idx += 1
        }
        if scnt == 1{
            visit[sidx] = true
            recur(s, cnt + 1)
            visit[sidx] = false
        }
        sidx += 1
    }
}
func solution(_ begin:String, _ target:String, _ words:[String]) -> Int {
    if begin.count != target.count {
        return 0
    }
    tg = target
    wd = words
    visit = [Bool](repeating: false, count: words.count)
    recur(begin, 0)
    if ans == 987654321 {
        ans = 0
    }
    return ans
}
