// Solved by Wolfram Hwang
// 가벼운 완전탐색 문제입니다.
// 물론 더 가볍고 빠르게 풀수도 있을것 같긴합니다만.
// 저는 그냥 다 돌렸어요 시간남길래.. ㅋㅋ
import Foundation

func solution(_ rows:Int, _ columns:Int, _ queries:[[Int]]) -> [Int] {
    let layer : [Int] = [Int](repeating : 0, count: columns)
    var map : [[Int]] = [[Int]](repeating: layer, count: rows)
    var inp = 1;
    for i in 0..<rows {
        for j in 0..<columns {
            map[i][j] = inp
            inp += 1
        }
    }
    var ans : [Int] = []
    for ar in queries {
        let p1 = (ar[0] - 1, ar[1] - 1)//왼쪽 위
        let p2 = (ar[2] - 1, ar[3] - 1)// 오른쪽아래
        let p3 = (p2.0, p1.1)//왼쪽 아래
        let p4 = (p1.0, p2.1)// 오른쪽 위
        var temp = map[p4.0][p4.1]
        var mx = 987654321
        if temp < mx {
            mx = temp
        }
        for i in ((p1.1 + 1)...p4.1).reversed(){
            map[p1.0][i] = map[p1.0][i - 1]
            if map[p1.0][i] < mx {
                mx = map[p1.0][i]
            }
        }
        var temp2 = map[p3.0][p3.1]
        if temp2 < mx {
            mx = temp
        }
        for i in p1.0...(p3.0 - 1) {
            map[i][p1.1] = map[i + 1][p1.1]            
            if map[i][p1.1] < mx {
                mx = map[i][p1.1]
            }
        }
        var temp3 = map[p2.0][p2.1]
        if temp3 < mx {
            mx = temp3
        }
        for i in p3.1...(p2.1 - 1) {
            map[p3.0][i] = map[p3.0][i + 1]
            if map[p3.0][i] < mx {
                mx = map[p3.0][i]
            }
        }
        for i in ((p1.0 + 1)...p2.0).reversed() {
            map[i][p4.1] = map[i - 1][p4.1]
            if map[i][p4.1] < mx {
                mx = map[i][p4.1]
            }
        }
         map[p4.0 + 1][p4.1] = temp
        ans.append(mx)
    }
    return ans
}
