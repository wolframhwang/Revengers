// 21.09.03 Solved By Wolfram Hwang
// i solved Using Swift
import Foundation

var comp = [
    [[1,0,0],
    [1,1,1]],
    [[0,1],
    [0,1],
    [1,1]],
    [[1,0],
    [1,0],
    [1,1]],
    [[0,0,1],
    [1,1,1]],
    [[0,1,0],
    [1,1,1]]
]
func checkComp(_ board: inout [[Int]], _ cp: [[Int]], _ h: Int, _ w: Int)->Bool {
    var C = -1
    var min = 987654321
    for i in 0..<cp.count {
        for j in 0..<cp[0].count {
            if board[h + i][w + j] > 0, cp[i][j] > 0 {
                if C == -1 { C = board[h + i][w + j] }
                else { if C != board[h + i][w + j] { return false }}
                if min > (h + i) { min = h + i}
            }else if board[h + i][j + w] == 0, cp[i][j] == 0{ }
            else { return false }
        }
    }
    
    for i in 0..<cp[0].count {
        if board[min][w + i] == C { continue}
        for j in (0..<h).reversed() {
            if board[j][w + i] != 0 { return false}
        }
    }
    
    for i in 0..<cp.count {
        for j in 0..<cp[0].count {
            if board[h + i][w + j] > 0 && cp[i][j] > 0 { board[h + i][w + j] = 0}
        }
    }
    return true;
}

func solution(_ board:[[Int]]) -> Int {
    
    var answer = 0
    let boardH = board.count
    let boardW = board[0].count
    var bd = board
    while true {
        var isFind = false
        for i in 0..<comp.count {
            let h = comp[i].count
            let w = comp[i][0].count
            for j in 0...boardH-h {
                for k in 0...boardW-w {
                    if(checkComp(&bd, comp[i], j, k)) {
                        answer += 1
                        isFind = true;
                    }
                }
            }
        }
        if !isFind { break }
    }
    return answer
}
