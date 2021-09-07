// 21.09.07 Solved By Wolfram Hwang
// 이분탐색 풀이.. 선택단계에 해당하니 파라메트릭 서칭인듯

class Solution {
    func splitArray(_ nums: [Int], _ m: Int) -> Int {
        var s = 1
        var e = Int(1e12)
        var ans = Int(1e10)
        while s <= e {
            let mid = (s + e) / 2
            var sum = 0
            var cnt = 1
            var MX = 0
            var zeros = 0
            for i in 0..<nums.count {
                if nums[i] == 0 {
                    if MX < sum {
                        MX = sum
                    }
                    sum = 0
                    zeros += 1
                    continue;
                }                
                if sum + nums[i] <= mid {
                    sum += nums[i]
                }else{
                    if MX < sum { MX = sum}
                    sum = nums[i]
                    cnt += 1
                }
                //if cnt >  m { break; }
            }
            if MX < sum { MX = sum }            
            if cnt == m {
                if ans > MX {
                    ans = MX
                }
                e = mid - 1
            }else if cnt < m {
                if zeros + cnt >= m {
                    if ans > MX {
                        ans = MX
                    }
                }
                e = mid - 1
            }else {
                s = mid + 1
            }
        }
        return ans
    }
}
