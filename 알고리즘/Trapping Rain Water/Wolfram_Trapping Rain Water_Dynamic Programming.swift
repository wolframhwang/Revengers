// 21/08/16 Solved By Wolfram Hwang

// Using : Dynamic Programming -> 왼쪽부터 읽어서 큰것 나열, 오른쪽부터 읽어서 큰것 나열
// -> 각 포지션에서 작은것 - 높이 더해나가면 됨

class Solution {
    func trap(_ height: [Int]) -> Int {
        let n = height.count
        guard n > 0 else {return 0}
        var maxl = [Int]()
        var maxr = Array(repeating: 0, count: n)
        maxl.append(height.first!)
        maxr[n-1] = height.last!
        for i in 1..<n {
            maxl.append(max(height[i], maxl[i-1]))
        }
        for i in (0..<n-1).reversed() {
            maxr[i] = max(maxr[i+1], height[i])
        }
        var ans = 0
        for i in 0..<n {ans += min(maxr[i], maxl[i]) - height[i]}
        return ans
    }
}
