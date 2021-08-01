// 21/08/01 Solved by Wolfram Hwang
// 재귀 완전탐색

import Foundation
var formular : [String] = []
var inchant : [String] = ["-", "+", "*"]
var ans: Int64 = Int64.min
var visit : [Bool] = [false, false, false]
func op(_ str: String) {
    var formdm = formular
    for cc in str {
        for i in (0..<formdm.count).reversed() {
            if formdm[i] == String(cc) {
                var ret : Int64 = 0
                if formdm[i] == "+" {
                    ret += Int64(formdm[i - 1])!
                    ret += Int64(formdm[i + 1])!
                }else if formdm[i] == "-" {
                    ret += Int64(formdm[i - 1])!
                    if i - 1 > 0, formdm[i - 2] == "-" {
                        ret += Int64(formdm[i + 1])!
                    }
                    else{
                        ret -= Int64(formdm[i + 1])!
                    }
                }else{
                    ret += Int64(formdm[i - 1])! * Int64(formdm[i + 1])!
                }
                formdm.remove(at: i + 1)
                formdm.remove(at: i)
                formdm[i - 1] = String(ret)
            }
        }
    }
    if ans < abs(Int64(formdm[0])!) {
        ans = abs(Int64(formdm[0])!)
    }
}
func go(_ idx :Int, _ str: String) {
    if idx == 3 {
        op(str)
        return
    }
    for i in 0..<3 {
        if visit[i] == false{
            visit[i] = true
            go(idx + 1, str + inchant[i])
            visit[i] = false
        }
    }
}
func solution(_ expression:String) -> Int64 {
    var value = ""
    
    for c in expression {
        switch c {
        case "+":
            formular.append(value)
            value = ""
            formular.append(String(c))
        case "-":
            formular.append(value)
            value = ""
            formular.append(String(c))
        case "*":
            formular.append(value)
            value = ""
            formular.append(String(c))
        default:
            value += String(c)
        }
    }
    formular.append(value)
    go(0, "")
    
    return ans
}

