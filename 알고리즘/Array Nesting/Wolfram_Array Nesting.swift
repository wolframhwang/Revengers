// 21.09.10 Solved By Wolfram Hwang
// 간단한 뎁스 문제같아요

class Solution {
    var visit : [Bool] = []
    var depth = 0
    var isF = false
    var answer = 0
    func go(_ n: Int, _ nums: [Int], _ s: Int){
        if answer < depth {
            return;
        }
        if nums[n] == s{
            isF = true
            return
        }
        if visit[nums[n]] == false {
            depth += 1
            visit[nums[n]] = true
            go(nums[n], nums, s)
        }
    }
    func arrayNesting(_ nums: [Int]) -> Int {            
        answer = nums.count
        visit = [Bool](repeating: false, count: nums.count) 
        var ans = 0
        for n in nums {
            if !visit[n] {
                visit[n] = true
                isF = false
                depth = 1 
                go(n, nums, n)    
                if ans < depth, isF {
                    ans = depth
                }
            }       
        }       
        return ans
    }
}
