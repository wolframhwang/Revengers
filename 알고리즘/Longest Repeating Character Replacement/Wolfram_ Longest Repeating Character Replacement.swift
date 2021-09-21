class Solution {
    func check(dict: [Character: Int])->(Character, Int) {
        var ret :Character = "a"
        var MX = 0
        var total = 0
        for (key, val) in dict {
            if val > MX {
                MX = val
                ret = key
            }
            total += val
        }
        return (ret, total - MX)
    }
    
    func characterReplacement(_ s: String, _ k: Int) -> Int {
        //let alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var dict: [Character: Int] = [:]
        //for al in alp{ dict[al] = 0}
        var start = 0
        var end = 0
        var ans = 0
        var str : [Character] = []
        for c in s {
            str.append(c)
        }
        while start <= end {
            if end == str.count {
                break
            }
            
            let MXP = check(dict: dict)
            if MXP.0 == "a" {
                dict[str[end]] = 1
                end += 1
            }else{
                if str[end] == MXP.0 {
                    dict[str[end]]? += 1
                    end += 1
                }else {
                    if MXP.1 == k {
                        dict[str[start]]? -= 1
                        if dict[str[start]] == 0 {
                            dict.removeValue(forKey: str[start])
                        }
                        start += 1
                    }else {
                        dict[str[end]]? += 1
                        end += 1
                    }
                }
            }
            if ans < (end - start) { ans = end - start}
        }
        
        return ans
    }
}
