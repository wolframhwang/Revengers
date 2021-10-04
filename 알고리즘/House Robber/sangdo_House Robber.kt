class Solution {
    fun rob(nums: IntArray): Int {
        var dp = IntArray(nums.size)
        if(nums.size == 1) return nums[0]
        dp[0] = nums[0]
        dp[1] = Math.max(nums[0], nums[1])
        var res = dp[1]
        
        for(i in 2 .. dp.lastIndex) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])
            res = Math.max(res, dp[i])
        }
        return res
    }
}
