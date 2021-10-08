// 21.10.08 solved by Wolfram Hwang
// Using By DP
class Solution {
    func shoppingOffers(_ price: [Int], _ special: [[Int]], _ needs: [Int]) -> Int {
        var cache = [[Int]:Int]()
        
        func eachBuying(_ items: [Int]) -> Int {
            var ret = 0
            for i in (0..<items.count) {
                ret += items[i] * price[i]
            }
            return ret
        }
        func isValid(_ offer: [Int], _ req: [Int]) -> Bool {
            for i in 0..<req.count {
                if req[i] < offer[i] {
                    return false
                }
            }
            return true
        }
        
        func update( _ req: [Int], by offer: [Int]) -> [Int] {
            var cReq = req
            for i in 0..<req.count {
                cReq[i] -= offer[i]
            }
            return cReq
        }
        func dp(_ remNeeds: [Int], _ cache: inout [[Int]:Int]) -> Int {
            if let val = cache[remNeeds] {
                return val
            }
            
            var minSpent = eachBuying(remNeeds)            
            for offer in special where isValid(offer, remNeeds) {
                minSpent = min(minSpent, offer[offer.count - 1] + dp(update(remNeeds, by: offer), &cache))
            }
                        
            cache[remNeeds] = minSpent
            return minSpent
        }
        return dp(needs, &cache)
    }
}
