/*
log(n)이어서 for문 하나로 했습니다.
*/
import java.util.*;
class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < nums.length;i++){
            min = Math.min(min,nums[i]);
        }
        return min;
    }
}
