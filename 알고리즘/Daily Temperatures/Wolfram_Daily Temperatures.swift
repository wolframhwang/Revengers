// 21.08.18 Solved by Wolfram Hwang
// idea: Monotonic Stack -> 오름차순 또는 내림차순을 유지하도록 해서 인덱스를 찾아내는것임

class Solution {
    func dailyTemperatures(_ T: [Int]) -> [Int] {
        var res = [Int](repeating: 0, count: T.count)
        var stack = [(Int,Int)]()
        var index = T.count - 1
        while index >= 0 {
            while !stack.isEmpty && stack.last!.1 <= T[index]{
                stack.popLast()
            }
            
            if !stack.isEmpty{
                res[index] = stack.last!.0 - index
            }
            
            stack.append((index, T[index]))
            
            index -= 1
        }
        
        return res
    }
}
