class Solution {
    func findMin(_ nums: [Int]) -> Int {
        var cnt : [Int] = nums
        var left = 0
        var right = nums.count - 1
        while nums[left] > nums[right] {
            let mid = (left + right) / 2
            if nums[mid] > nums[right] {
                left = mid + 1
            }else{
                right = mid
            }
        }
        return nums[left]
    }
}
