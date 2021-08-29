// 21.08.30 solved by Wolfram Hwang
// 거꾸로 뒤집어서 sum 을 해도 가장 큰값을 찾는것
// 근데 큰값이 있음에도 만약 0보다 작아진다면, 그것은 될수없는거라..


class Solution {
    func canCompleteCircuit(_ gas: [Int], _ cost: [Int]) -> Int {
        var gascost: [Int] = [Int](repeating: 0, count: gas.count)
        let len = gas.count
        let range = 0..<len
        var sum = 0
        var MX = -987654321
        var ans = -1
        var idx = len - 1
        while idx >= 0 {
            sum += gas[idx] - cost[idx]
            if sum > MX {
                MX = sum
                ans = idx
            }
            idx -= 1
        }
        return sum < 0 ? -1 : ans
    }
}
