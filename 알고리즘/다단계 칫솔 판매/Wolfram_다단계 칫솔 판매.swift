// Solved by Wolfram
// 그냥 부모를 계속 타고올라가면 되는 쉬운 문제입니다.
// 전형적인 문제를 제대로 읽어야 실수하지 않는 문제입니다.
// 포인트는 소수점 단위로 upPrice가 계산된다면, 그것을 절삭하고 myPrice에 += 1을 해주면 되는 문제네요.

import Foundation

func solution(_ enroll:[String], _ referral:[String], _ seller:[String], _ amount:[Int]) -> [Int] {
    let PriceOfBrush = 100
    var map : [String: String] = [String: String]()
    var matching: [String: Int] = [String: Int]()
    map["root"] = "end"
    for i in 0..<referral.count {
        if referral[i] == "-" {
            map[enroll[i]] = "root"
        }else{
            map[enroll[i]] = referral[i]
        }
        matching[enroll[i]] = 0
    }
    matching["root"] = 0
    for i in 0..<seller.count {
        var totalPrice = PriceOfBrush * amount[i]
        var upPrice = Double(totalPrice) * 0.1
        var myPrice = Double(totalPrice) * 0.9
        if upPrice > Double(Int(upPrice)) {
            upPrice = Double(Int(upPrice))
            myPrice += 1
        }
        matching[seller[i]]? += Int(myPrice)
        var parent = map[seller[i]] ?? "root"
        while true {
            totalPrice = Int(upPrice)
            if totalPrice == 0 {
                break
            }
            if parent == "root" {
                matching[parent]? += totalPrice
                break
            }
            upPrice = Double(totalPrice) * 0.1
            myPrice = Double(totalPrice) * 0.9
            if upPrice > Double(Int(upPrice)) {
                upPrice = Double(Int(upPrice))
                myPrice += 1
            }
            if upPrice < 1 {
                matching[parent]? += totalPrice
                break
            }
            else{
                matching[parent]? += Int(myPrice)
            }
            parent = map[parent]!
        }
    }
    var ans : [Int] = []
    for name in enroll {
        ans.append(matching[name]!)
    }
    return ans
}
