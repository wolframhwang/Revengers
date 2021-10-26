class Solution {
    func findAnagrams(_ s: String, _ p: String) -> [Int] {
        if p.count > s.count {
            return []
        }
        let S = Array(s)

        var answer : [Int] = []
        var dict : [Character: Int] = [:]
        var isFull : [Character: Bool] = [:]
        var total = 0
        var isFullCnt = 0
        var left = 0
        var right = 0

        for c in p {
            if dict[c] == nil {
                dict[c] = 1
                isFull[c] = false
                total += 1
            }
            else {
                dict[c]? += 1
            }
        }

        while right < p.count {
            if dict[S[right]] != nil {
                dict[S[right]]? -= 1

                if dict[S[right]]! == 0 {
                    if isFull[S[right]]! == false {
                        isFull[S[right]] = true
                        isFullCnt += 1
                    }
                }else{
                    if isFull[S[right]]! == true {
                        isFull[S[right]] = false
                        isFullCnt -= 1
                    }
                }
            }
            right += 1
        }

        while right < s.count {
            if isFullCnt == total {
                answer.append(left)
            }
            if dict[S[left]] != nil {
                dict[S[left]]? += 1
                if dict[S[left]]! == 0 {
                    if isFull[S[left]]! == false { 
                        isFull[S[left]] = true
                        isFullCnt += 1
                    }
                }else{
                    if isFull[S[left]]! == true {
                        isFull[S[left]] = false
                        isFullCnt -= 1
                    }
                }
            }
            left += 1
            if dict[S[right]] != nil {
                dict[S[right]]? -= 1
                if dict[S[right]]! == 0 {
                    if isFull[S[right]]! == false {
                        isFull[S[right]] = true
                        isFullCnt += 1
                    }
                }else{
                    if isFull[S[right]]! == true {
                        isFull[S[right]] = false
                        isFullCnt -= 1
                    }
                }
            }
            right += 1
        }
        if isFullCnt == total {
            answer.append(left)
        }
        return  answer
    }
}
