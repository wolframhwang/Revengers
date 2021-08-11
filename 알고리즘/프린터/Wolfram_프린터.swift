// 21/08/11 Solved By Wolfram Hwang
// 단순한 큐 문제입니다.

import Foundation
var pr : [Int] = [Int](repeating: 0, count: 10)
func check(_ ck : Int) -> Bool {
    if ck == 9 {
        return true
    }
    for i in ck+1..<10 {
        if pr[i] > 0 {
            return false
        }
    }
    return true
}
func solution(_ priorities:[Int], _ location:Int) -> Int {
    var q : [(Int, Int)] = []
    
    var idx = 0
    for i in priorities {
        pr[i] += 1
        q.append((i, idx))
        idx += 1
    }
    var front = 0
    var rear = q.count
    var cnt = 1
    while front != rear {
        let now = q[front]
        front += 1
        if check(now.0) {
            pr[now.0] -= 1
            if location == now.1 {
                return cnt
            }
            cnt += 1
        }else{
            q.append(now)
            rear += 1
        }
    }
    return 0
} 
