class Solution {
public:
    int rob(vector<int>& nums) {
        if(nums.size()==0){
            return 0;
        }
        else if(nums.size() == 1){
            return nums[0];
        }
        int dp[2][2]; // 0은 여기 안들렀을때 최대 1은 여기 들렀을때 최대
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        dp[1][0] = nums[0];
        dp[1][1] = nums[1];
        for(int i=2;i<nums.size();i++){
            dp[0][0] = dp[1][0];
            dp[0][1] = dp[1][1];
            dp[1][0] = max(dp[0][0],dp[0][1]);
            dp[1][1] = dp[0][0]+nums[i];
        }
        return max(dp[1][0],dp[1][1]);
    }
};
