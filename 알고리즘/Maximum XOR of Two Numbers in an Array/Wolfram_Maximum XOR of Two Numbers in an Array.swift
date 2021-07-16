// Solved by Wolfram Hwang
// use Trie Algorithm
class Solution {
    func findMaximumXOR(_ nums: [Int]) -> Int {
        var ret = 0, mask = 0
        
        for i in stride(from: 31, through: 0, by: -1) {
            mask |= 1 << i
            
            var trie = Set<Int>()
            for num in nums {
                trie.insert(num & mask)
            }
            
            let MAXVAL = ret | 1 << i
            for prefix in trie {
                if trie.contains(MAXVAL ^ prefix) {
                    ret = MAXVAL
                    break
                }
            }
        }
        
        return ret
    }
    
}
