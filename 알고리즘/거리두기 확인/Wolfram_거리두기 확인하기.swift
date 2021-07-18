// Solved by Wolfram Hwang
// 큐를 두번 비워주는 방식으로 문제를 해결하였습니다.
// 딱히 문제될건 없는거 같고, 간단한 문제같네요.
import Foundation
func check(_ map: [[Int]], _ list: [(Int, Int)]) -> Int {
    var layer : [Bool] = [Bool](repeating: false, count: 5)
    var visit : [[Bool]] = [[Bool]](repeating: layer, count: 5)
    var ret = 1
    let dx = [0,0,1,-1]
    let dy = [1,-1,0,0]
    for val in list {
        if visit[val.0][val.1] == true {
            return 0
        }
        var q : [(Int, Int)] = [val]
        visit[val.0][val.1] = true
        var front = 0
        for _ in 0..<2 {
            let up = q.count
            for i in front..<q.count {
                let x = q[i].0; let y = q[i].1
                for j in 0..<4 {
                    let nx = x + dx[j]; let ny = y + dy[j]
                    if nx >= 0, nx < 5, ny >= 0, ny < 5 {
                        if visit[nx][ny] == true {
                            continue
                        }
                        if map[nx][ny] == 2 {
                            continue
                        }
                        if map[nx][ny] == 1 {
                            return 0
                        }
                        visit[nx][ny] = true
                        q.append((nx, ny))
                    }
                }
            }
            front = up
        }
    }
    return ret
}
func solution(_ places:[[String]]) -> [Int] {
    var ans : [Int] = [Int]()
    
    for i in 0..<places.count {
        let layer2: [Int] = [Int](repeating: 0, count: places[0].count)
        var map : [[Int]] = [[Int]](repeating: layer2, count: places.count)
        var list : [(Int, Int)] = [(Int, Int)]()
        for j in 0..<places[i].count {
            var idx = 0
            for k in places[i][j] {
                if k == "P" {
                    map[j][idx] = 1
                    list.append((j, idx))
                }else if k == "O" {
                    map[j][idx] = 0
                }else {
                    map[j][idx] = 2
                }
                idx += 1
            }
        }
        ans.append(check(map, list))
    }
    return ans
}
