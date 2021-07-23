// Solved by Wolfram Hwang
// 스택
import Foundation

func solution(_ s:String) -> Int {
    // stack 이 필요하다...
    // 논리적으로 스택이 필요한 이유에 대해 말해보자.
    // 만약 그냥 brace의 개수만 세고, 그 뒤에 오는 } ] ) 에 대하여
    // += 0을 만들어주게 된다면, // 가짓수가 3가지이므로 맞지 않는경우가 존재한다.
    // 만약 brace의 종류가 1가지였다면 그냥 해도 무방함
    let range = 0..<s.count
    var str  = [Character]()
    for c in s {
        str.append(c)
    }
    var ans = 0
    let map: [Character : Character] = ["}" : "{" , "]" : "[", ")" : "("]
    for i in range {
        var stk: [Character] = []
        var isFind = true
        for v in i..<str.count {
            let c = str[v]
            if c == "{" || c == "(" || c == "["{
                stk.append(c)
            }
            if c == "}" || c == ")" || c == "]" {
                if stk.last == map[c]! {
                    stk.removeLast()
                }else{
                    isFind = false
                    break
                }
            }
        }
        if isFind == true, stk.count == 0 {
            ans += 1
        }
        str.append(str[i])
    }
    return ans
}
