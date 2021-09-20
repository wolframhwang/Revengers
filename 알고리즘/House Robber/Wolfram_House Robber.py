class Solution {
    func rob(_ nums: [Int]) -> Int {
        var dp: [[Int]] = [[Int]](repeating: [Int](repeating: 0, count: nums.count), count: 2)
        dp[0][0] = 0
        dp[1][0] = nums[0]
        
        for i in 1..<nums.count {
            dp[1][i] = dp[0][i - 1] + nums[i]
            dp[0][i] = max(dp[0][i  - 1], dp[1][i - 1])
        }
        
        return max(dp[1][nums.count - 1], dp[0][nums.count - 1])
    }
}
