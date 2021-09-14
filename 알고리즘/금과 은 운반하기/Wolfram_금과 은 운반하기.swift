
import Foundation

func solution(_ a:Int, _ b:Int, _ g:[Int], _ s:[Int], _ w:[Int], _ t:[Int]) -> Int64 {
    var start = 0
    var end = Int(1e15)
    while start < end {
        let mid : Int = (start + end) / 2
        
        var sum = 0
        var gold = 0
        var silver = 0
        
        for i in 0..<g.count {
            if mid >= t[i] {
                let rm = mid - t[i]
                let time : Int = ((rm / 2) / t[i]) + 1
                sum += min(time * w[i], g[i] + s[i])
                gold += min(time * w[i], g[i])
                silver += min(time * w[i], s[i])
            }
        }
        
        if sum >= a + b, gold >= a, silver >= b{
            end = mid
        }else{
            start = mid + 1
        }
    }
    
    return Int64(start)
}
