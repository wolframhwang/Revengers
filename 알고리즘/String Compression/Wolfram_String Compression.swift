// Solved by Wolfram Hwang
// 킹-전-탐-색
class Solution {
    func compress(_ chars: inout [Character]) -> Int {
        var idx = 0
        var cnt = 0
        var before: Character = " "
        while idx < chars.count {
            if chars[idx] != before {
                if cnt > 1 {
                    let str = String(cnt)            
                    for c in str {
                        chars.insert(c, at: idx)
                        idx += 1
                    }
                }
                cnt = 1
                before = chars[idx]
                idx += 1
            }else{
                cnt += 1
                chars.remove(at : idx)                
            }
        }
        if cnt > 1 {
            let str = String(cnt)
            for c in str {
                chars.insert(c, at : idx)
                idx += 1
            }
        }
        return chars.count
    }
}
