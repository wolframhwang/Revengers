// 21/08/16 Solved by Wolfram Hwang
// 올라가다 -> 내려갈때 계산, 보다 더 크거나 같으면 -> 왼쪽 포지션 갱신
class Solution {
    var ans = 0
    var s = 0
    var hh = [Int]()
    func calc(_ e: Int)->Int{
        let h = hh[s] < hh[e] ? hh[s] : hh[e]
        var ret = 0
        for i in s...e{
            if h - hh[i] < 0 {
                continue
            }
            ret += h - hh[i]
            hh[i] = h
        }
        //print(s, e, h, ret, "CALC")
        return ret
    }
    func down(_ idx: Int) {
        if idx + 1 < hh.count {
            if hh[idx + 1] <= hh[idx] {
                down( idx + 1)
            }else{
                up( idx + 1)
            }
        }
    }
    func up(_ idx: Int) {
        if idx + 1 < hh.count {
            if hh[idx + 1] >= hh[idx] {
                up(idx + 1)
            }else{
                ans += calc(idx)
                if hh[idx] >= hh[s] {
                    s = idx
                }
                down(idx + 1)
            }
        }else{
            ans += calc(idx)
        }
    }
    func trap(_ height: [Int]) -> Int {
        hh = height
        up(0)
        return ans
    }
}
