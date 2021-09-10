import java.util.*;
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        int sol = -6000;
        
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                for(int k = j + 1; k < nums.length; k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    if(Math.abs(target - sum) < Math.abs(target - sol)){
                        sol = sum;
                    }
                }    
            }       
        }
        
        return sol;
    }
}
