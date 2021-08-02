// 21/08/02 Solved By Wolfram Hwang
// 바이너리 서치로 풀었습니다.
// 먼저 두개를 선택하고,
// 선택된 두개의 인덱스를 기준으로
// 첫번째 인덱스 밖의영역, 두개 사이의 영역, 두번째 인덱스 오른쪽 밖의 영역
// 이렇게 나눠서 바이너리 서치함 ㅎㅎ

class Solution {
    var list : [Int] = []
    var visit : [Bool] = []
    var tg = 0
    var ans = 987654321
    var abss = 987654321
    func bin(_ f: Int, _ s: Int, _ value: Int)->Int {
        var l = f
        var r = s
        while l < r {
            let m = (l + r) / 2
            let v = value + list[m]
            if v == tg {
                return tg
            }
            if v < tg {
                l = m + 1
            }else{
                r = m
            }
        }
        let ret = value + list[l]
        return ret
    }
    func go(_ arr: [Int], _ idx: Int) {
        if arr.count == 2 {
            let value = list[arr[0]] + list[arr[1]]
            if arr[0] > 0 {
                let r = bin(0, arr[0] - 1, value)
                if abss > abs(tg - r) {
                    abss = abs(tg - r)
                    ans = r
                }
            }
            if arr[0] + 1 <= arr[1] - 1 {
                let r = bin(arr[0] + 1, arr[1] - 1, value)
                if abss > abs(tg - r) {
                    abss = abs(tg - r)
                    ans = r
                }
            }
            if arr[1] + 1 < list.count {
                let r = bin(arr[1] + 1, list.count - 1, value)
                if abss > abs(tg - r) {
                    abss = abs(tg - r)
                    ans = r
                }
            }
            return
        }
        if idx >= list.count {
            return
        }
        if visit[idx] == false {
            var inp = arr
            visit[idx] = true
            inp.append(idx)
            go(inp, idx + 1)
            visit[idx] = false
        }
        go(arr, idx + 1)
    }
    func threeSumClosest(_ nums: [Int], _ target: Int) -> Int {
        list = nums.sorted()
        visit = [Bool](repeating: false, count: nums.count + 1)
        tg = target
        go([], 0)
        return ans
    }
}
