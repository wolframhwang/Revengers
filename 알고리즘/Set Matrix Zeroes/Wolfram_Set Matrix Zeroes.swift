// solved By Wolfram Hwang at 21.09.12
// 완전탐색 왜냐, 배열이 작기때문에
class Solution {
    let dx = [0,0,1,-1]
    let dy = [1,-1,0,0]
    func makeZeros(_ zeros: inout [[Bool]], _ x: Int, _ y: Int) {
        zeros[x][y] = true
        for i in 0..<4 {
            var nx = x
            var ny = y
            while true {
                nx += dx[i]
                ny += dy[i]
                if nx < 0 || nx >= zeros.count || ny < 0 || ny >= zeros[0].count {
                    break
                }
                zeros[nx][ny] = true
            }
        }
    }
    func setZeroes(_ matrix: inout [[Int]]) {
        var zeros = [[Bool]](repeating: [Bool](repeating: false, count: matrix[0].count), count: matrix.count)
        
        for i in 0..<matrix.count{
            for j in 0..<matrix[i].count {
                if matrix[i][j] == 0 {
                    makeZeros(&zeros, i, j)
                }
            }
        }
        for i in 0..<matrix.count {
            for j in 0..<matrix[i].count {
                if zeros[i][j] == true { matrix[i][j] = 0}
            }
        }
    }
}
