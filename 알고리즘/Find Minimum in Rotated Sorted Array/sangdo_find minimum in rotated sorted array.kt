/*
nums[0]보다 작은 친구가 있는 곳을 찾으면 된다.
만약 어떤 위치에서 nums[0]보다 작다면, 원본에서 가장 작은 수 는 이 위치보다 무조건 왼쪽에 있다.
만약 어떤 위치에서 nums[0]보다 크다면, 원본에서 가장 작은 수는 이 위치보다 무조건 오른쪽에 있다. 
이를 이용 하면 이분 탐색을 이용해서 처음의 위치를 찾을 수 있다.
*/
class Solution {
    fun findMin(nums: IntArray): Int {
        var s = 0
        var e = nums.size
        while(s < e){
            var m = (s+e)/2
            if(nums[m] < nums[0]){
                e = m
            }
            else s = m+1
        }
        return if(e == nums.size) nums[0] else nums[e]
    }
}
