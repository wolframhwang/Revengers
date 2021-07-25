// Solved by Wolfram Hwang at 2021/07/26 
// 간단한 재귀함수 문제
// BFS, DFS랑은 거리가 좀 있는듯..

import Foundation

var t = 0
var ans = 0
func go(_ numbers: [Int], _ target :Int, _ idx : Int) {
    if idx == numbers.count {
        if target == t {
            ans += 1
        }
        return 
    }
    go(numbers, target + numbers[idx], idx + 1)
    go(numbers, target - numbers[idx], idx + 1)
}
func solution(_ numbers:[Int], _ target:Int) -> Int {
    t = target
    go(numbers, 0, 0)
    return ans
}
