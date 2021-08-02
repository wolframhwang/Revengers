/*
O(n^2)
1. nums 소팅
2. i를 기준으로 가장 작은수를 s, 가장 큰 수를 e라고 하면, 점점 작은 값을 찾기 위해서 
nums[i] + nums[s] + nums[e]를 했을 때 target 보다 크면 e를 줄여야 하고, target보다 작으면 s를 키워야 한다. (투포인터)
3. i마다 n개를 반복하므로 시간복잡도는 n^2이다.
*/
class Solution {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        var res = 0x3f3f3f3f
        nums.sort()
        for(i in nums.indices){
            var s = i +1
            var e = nums.lastIndex
            while(s < e){
                val sum = nums[i] + nums[s] + nums[e] - target
                if(Math.abs(sum) < Math.abs(res)) res = sum
                if(sum < 0) ++s  
                else --e
            }
        }
        return target + res
    }
}
