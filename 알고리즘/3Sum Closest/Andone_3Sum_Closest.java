import java.util.*;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int answer = 0;

        if(nums.length == 3) {
            return nums[0] + nums[1] + nums[2];
        }

        Arrays.sort(nums);
        int minClosest = Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++) {
            int a=i+1;
            int b=nums.length-1;
            while(a<b) {
                int sum = nums[i] + nums[a] + nums[b];

                if(Math.abs(target-sum)<minClosest) {
                    minClosest = Math.abs(target-sum);
                    answer = sum;
                }
                if(target>sum) a++;
                else b--;
            }
        }

        return answer;
    }
}