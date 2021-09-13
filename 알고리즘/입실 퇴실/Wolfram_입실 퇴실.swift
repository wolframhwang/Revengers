// 21.09.13  Solved By Wolfram Hwang
// 완전 탐색
import Foundation
var visit: [Int] = [Int](repeating: 0, count: 1001)
func update(_ stack: inout [Int], _ input: Int) {
    for i in stack {
        visit[i] += 1
    }
    visit[input] = stack.count
    stack.append(input)
}

func solution(_ enter:[Int], _ leave:[Int]) -> [Int] {    
    var map : [Int: Bool] = [:]
    var stack : [Int] = []
    var idx = 0
    var index = 0
    while idx < leave.count {
        let now = leave[idx]
        if map[now] == nil {            
            while index < enter.count {
                if enter[index] == now {
                    update(&stack, enter[index])
                    stack.removeLast()
                    index += 1
                    break
                }else {
                    update(&stack, enter[index])
                    map[enter[index]] = true
                }
                index += 1
            }
        }else {
            for i in 0..<stack.count {
                if stack[i] == now {
                    stack.remove(at: i)
                    break
                }
            }
        }
        idx += 1
    }
    var answer : [Int] = []
    for i in 1...enter.count {
        answer.append(visit[i])
    }
    return answer
}
