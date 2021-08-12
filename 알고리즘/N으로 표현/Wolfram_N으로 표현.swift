// 21/08/12 Solved by Wolfram Hwang
// 간단한 재귀 밑 완전 탐색 문제입니다
// 9보다 초과하는 개수의 제한이 높았다면 절대 이대로 못풀었을것같지만요

import Foundation
var tgn: [Int] = []
var target = 0
var ans = 12
func go(_ cnt: Int, _ val: Int) {
    if cnt > 8 {
        return
    }
    if val == target {
        ans = ans > cnt ? cnt: ans
        return
    }
    for i in 0..<tgn.count {
        go(cnt + i + 1, val + tgn[i])
        go(cnt + i + 1, val - tgn[i])
        if val == 0 {
            continue
        }
        go(cnt + i + 1, val / tgn[i])
        go(cnt + i + 1, val * tgn[i])
    }
}
func solution(_ N:Int, _ number:Int) -> Int {
    var ret = N
    target = number
    for _ in 0...8 {
        tgn.append(ret)
        ret = ret * 10 + N
    }  
    go(0, 0)  
    ans = ans == 12 ? -1 : ans
    return ans
}
