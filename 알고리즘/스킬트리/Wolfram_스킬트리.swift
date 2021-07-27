// 21/07/28 Solved by Wolfram Hwang
// 간단한 문제입니다.
// 다만 이걸 위상정렬로 풀수도있을거같은데
// 개수가 너무 작기도하고 충분한 조건이여서
// 조상 루트 타는걸로 해결했습니다.

import Foundation
var parent : [Character: Character] = [Character: Character]()
var check: [Character: Bool] = [Character: Bool]()
var bc : Bool = false
func skillCheck(_ p: Character, _ str : String){
    if p == "." {
        for c in str {
            check[c] = true
        }
        bc = true
        return
    }
    if check[parent[p]!] == true { 
        skillCheck(parent[p]!, str + String(p))
    }
}

func solution(_ skill:String, _ skill_trees:[String]) -> Int {
    
    var idx = 0
    var before:Character = "z"
    for c in skill {
        if idx == 0 {
            parent[c] = "."
        }else{
            parent[c] = before
        }
        check[c] = false
        before = c
        idx += 1
    }
    var ret = 0
    for sk in skill_trees {
        for (key, value) in check {
            check[key] = false
        }
        check["."] = true
        var point = true
        for c in sk {
            if let v = parent[c] {
                skillCheck(c, "")
                if bc == true {
                    bc = false
                }else{
                    point = false
                    break
                }
            }else{
                ret += 1
            }
        }
        if point {
            ret += 1
        }
    }
    return ret
}
