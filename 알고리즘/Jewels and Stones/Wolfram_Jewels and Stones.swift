// 21.08.19 Solved by Wolfram Hwang
// Hash 
class Solution {
    func numJewelsInStones(_ jewels: String, _ stones: String) -> Int {
        var map : [Character: Int] = [Character: Int]()
        //let alp = "abcdefghijklmnopqrstuvwxyz"
        for c in jewels {
            map[c] = 0 
        }

        for c in stones {
            if map[c] == nil {
                continue
            }
            map[c]? += 1
        }
        var ans = 0
        for (key, value) in map {
            ans += value
        }
        return ans
    }
}
