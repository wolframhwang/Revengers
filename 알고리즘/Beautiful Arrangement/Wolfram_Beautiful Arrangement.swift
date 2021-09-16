class Solution {
    var dp : [Int] = []
    func recur(_ num : Int, _ n: Int, _ mask: Int)->Int{
        if num  == (n + 1) { return 1}
        if dp[mask] != -1 {return dp[mask]}
        var ret = 0
        for i in 0..<n {
            if (mask & (1 << i)) == 0 && (num % (i + 1) == 0 || (i + 1) % num == 0){
                let temp = mask | (1 << i)
                ret += recur(num + 1, n, temp)
            }            
        }
        dp[mask] = ret
        return ret
    }
    func countArrangement(_ n: Int) -> Int {
        var mask = 0
        dp = [Int](repeating: -1, count: (1 << (n + 1)))
        return recur(1, n, mask)
    }
}
