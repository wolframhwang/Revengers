class Solution {
    func majorityElement(_ nums: [Int]) -> Int {
        var dict : [Int: Int] = [:]
        let n = nums.count / 2
        var ans = -1
        for i in nums {
            if dict[i] == nil {
                dict[i] = 1
            }else{
                dict[i]? += 1
            }
            if dict[i]! > n {
                return i
            }
        }
        return 0
    }
}
