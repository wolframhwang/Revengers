// This Problem solved at 2021/08/08 By Wolfram Hwang
// 문제가 고약하네요
// 원래 행열 줄세워놓고 거기에 맞춰서 한 블록 크기만큼 줄여가면서 하는것
// 만약 다 했다면, 가장 작은 블록의 높이를 토대로 로워바운드 통해서 줄여주기
// 다했다면 return ans
import Foundation

func solution(_ food_times:[Int], _ k:Int64) -> Int {
    var arr = food_times
    var acount : [(Int, Int)] = []
    for i in 0..<arr.count {
        acount.append((arr[i], i))
    }
    acount = acount.sorted { s1, s2 in
        if s1.0 < s2.0{
            return true
        }else{
            if s1.0 == s2.0, s1.1 < s2.1 {
                return true
            }
        }
        return false
    }
    var before = 0
    var K = k
    var height = 0
    for i in 0..<arr.count {
        var tot: Int64 = Int64(arr.count - i) * Int64(acount[i].0 - before)
        if K - tot < 0 {
            break
        }
        else if K == tot {
            let comp = acount[i].0
            arr[acount[i].1] = 0
            for j in 0..<arr.count {
                if arr[j] != 0,  arr[j] != comp{
                    return j + 1
                }
            }
        }
        else{
            K -= tot
            before = acount[i].0
            arr[acount[i].1] = 0
        }
    }
    var min = 987654321
    var cnt = 0
    for i in 0..<arr.count {
        if arr[i] != 0 {
            arr[i] -= before
            cnt += 1
            if min > arr[i], arr[i] != 0 {
                min = arr[i];
            }
        }
    }
    var s = 0;
    var e = min
    while s < e{
        let mid = (s + e) / 2
        let tot = Int64(cnt) * Int64(mid)
        if tot > K {
            e = mid
        }else{
            s = mid + 1
        }
    }
    s -= 1
    let vv = Int64(s * cnt)
    K = K >= Int64(s * cnt) ? K - vv: K
    for i in 0..<arr.count {
        if arr[i] != 0 {
            if K <= 0 {
                return i + 1
            }
            K -= 1
        }
    }
    
    
    return -1
}
