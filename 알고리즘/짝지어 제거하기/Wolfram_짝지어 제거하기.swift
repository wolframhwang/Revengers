// 21/07/30 Solved by Wolfram Hwang
// 스택문제
import Foundation

func solution(_ s:String) -> Int{
    var answer:Int = 0

    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    var stk: [Character] = []
    for c in s {
        if stk.count == 0 {
            stk.append(c)
        }else{
            if stk.last == c {
                stk.removeLast()
            }else{
                stk.append(c)
            }
        }
    }
    if stk.count == 0 {
        answer = 1
    }    
    return answer
}
