// 21.08.22 Solved By Wolfram Hwang
// Greedy Problem

import Foundation
func solution(_ n:Int, _ stations:[Int], _ w:Int) -> Int{
    var answer = 0
    let length = w * 2 + 1
    var left = stations[0] - w
    var right = stations[0] + w + 1
    var range = left - 1
    if range > 0 {
        answer += range / length
        if range % length != 0 {
            answer += 1
        }
    }
    for i in 1..<stations.count {
        let l = stations[i] - w
        let r = stations[i] + w + 1
        range = l - right
        if range > 0 {
            answer += range / length
            if range % length != 0 { answer += 1}
        }
        right = r
    }
    if right <= n {
        range = n - right + 1
        if range > 0 {
            answer += range / length
            if range % length != 0 { answer += 1 }
        }
    }
    return answer
}
