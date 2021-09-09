// 21.09.09 solved By Wolfram HWang
// Monotonic Stack
class Solution {
    func find132pattern(_ nums: [Int]) -> Bool {
        guard nums.count > 2 else {
            return false
        }
        var arr = [Int](repeating: 0, count: nums.count)
        var stk: [Int] = []
        arr[0] = nums[0]
        
        for i in 1..<nums.count {
            arr[i] = min(arr[i - 1], nums[i])
        }
        
        for i in stride(from: nums.count - 1, to: 0, by: -1){
            if nums[i] > arr[i] {
                while !stk.isEmpty, stk.last! <= arr[i] {
                    stk.removeLast()
                }
                
                if !stk.isEmpty, stk.last! < nums[i] {
                    return true;
                }
            }
            stk.append(nums[i])
        }
        return false;
    }
}
