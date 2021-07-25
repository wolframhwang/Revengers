// 2021/07/25 Solved by Wolfram Hwang
// 이 문제는 단순한 완전 탐색문제입니다.
// 다만 초보자들에게 함정이라고 생각 될 여지가 있는것은
// 미리 문자열을 return할 함수에 돌려놓고 계속 되돌아가서 변경하게 할수도 있다는것인데
// 조금이라도 문제를 풀어본 경험이 있다면 아마 걸려들지 않을 함정이긴 합니다.

import Foundation

func solution(_ record:[String]) -> [String] {
    var ret : [String] = []
    var seq  :[(String, Int)] = []
    var map : [String : String] = [String :String]()
    let enter = "님이 들어왔습니다."
    let leave = "님이 나갔습니다."
    
    for rec in record {
        let split = rec.split(separator: " ")
        let uid = String(split[1])
        if split[0] == "Enter"{
            let name = String(split[2])
            map[uid] = name
            seq.append((uid, 0))
        }else if split[0] == "Leave" {
            seq.append((uid, 1))
        }else {
            let name = String(split[2])
            map[uid] = name
        }
    }
    for s in seq {
        if let inp = map[s.0] {
            var str = inp
            switch s.1 {
            case 0:
                str += enter
            case 1:
                str += leave
            default:
                break
            }
            ret.append(str)
        }
    }
    return ret
}
