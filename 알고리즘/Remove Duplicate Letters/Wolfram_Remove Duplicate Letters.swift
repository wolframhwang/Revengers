// not solved by Wolfram Hwang -> from : https://leetcode.com/problems/remove-duplicate-letters/discuss/889841/Two-Swift-solutions%3A-with-stack-and-without-stack

class Solution {
    func removeDuplicateLetters(_ s: String) -> String {
        let s: [Int] = Array(s).map({ Int($0.asciiValue ?? 97) - 97 })
        let c = s.count
        let alphabet = 26

        var counts = [Int](repeating: 0, count: alphabet)
        var visited = [Bool](repeating: false, count: alphabet)
        var stack = [Int]()
        for char in s {
            counts[char] += 1
        }
        for char in s {
            counts[char] -= 1
            if visited[char] {
                continue
            }
            while stack.count > 0 && stack.last! > char && counts[stack.last!] > 0 {
                let last = stack.removeLast()
                visited[last] = false
            }
            stack.append(char)
            visited[char] = true
        }
       return String(stack.map({ Character(UnicodeScalar(UInt8($0 + 97))) }))
    }
}
