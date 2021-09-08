// 21.09.08 Solved By Wolfram Hwang
// Sliding Window

class Solution {
    let alp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var dict : [Character: Int] = [:]
    var comp : [Character: Int] = [:]
    var st: Set<Character> = []
    func check() -> Bool{
        for key in st {
            if comp[key]! < dict[key]! {
                return false
            }
        }
        return true
    }
    func minWindow(_ s: String, _ t: String) -> String {        
        var arr: [Character] = []
        for c in s {
            arr.append(c)
        }
        for c in t {
            st.insert(c)
        }

        for c in alp {
            dict[c] = 0
            comp[c] = 0
        }
        for c in t {
            dict[c]! += 1
        }
        var min = 987654321
        var startP = 0
        var s = 0
        var e = 0
        while s <= e,s < arr.count{
            if(!check()) {
                if e < arr.count {
                    comp[arr[e]]? += 1
                    e += 1
                }else { 
                    break
                }
            }else{
                if min > (e - s) { min = e - s; startP = s}
                comp[arr[s]]? -= 1
                s += 1
            }
        }
        var answer :String = ""
        if min != 987654321{
            for i in startP..<(startP + min) {
                answer += String(arr[i])
            }
        }
        return answer
    }
}
