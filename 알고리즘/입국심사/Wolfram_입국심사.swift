// 21.08.26 Solved By Wolfram Hwang
// 간단한 이분탐색 문제입니다.
// 평균값 문제인데, 시간에 대해 최대로 나올수 있는 시간을 기준으로하여
// 순회하면서 값을 계산해 나가는 방식입니다.
// 최대 시간복잡도는 log2(1000000000) * 100000
// 몇개안되네요 ^^
import Foundation

func solution(_ n:Int, _ times:[Int]) -> Int64 {
    let time = times.sorted(by: <)
    var end = time[time.count - 1] * n
    var start = 0
    var ans = 9876543219333
    while start <= end {
        let mid = (start + end) / 2
        var sum = 0
        for x in time {
            sum += (mid / (x))
            if sum > n {
                break
            }
        }
        if n <= sum {
            ans = mid
            end = mid - 1
        }
        else{
            start = mid + 1
        }
    }
    
    return Int64(ans)
}
