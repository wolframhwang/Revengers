// 21.09.01 Solved by Wolfram Hwang
// 풀고 보니까 유니온 파인드니 뭐니 하던데.. 생각난게 약간 다리만들기2? 가 생각이 나네요
// BFS로 섬 카운팅해주고 -> 카운팅한거에 개수를 현재 섬 넘버에 저장하여
// 빈곳을 돌면서 주변 섬에 대해 중복없이 합쳤을때 가장 큰 값을 리턴하면 되겠습니다.
class Solution {
    var map : [Int] = [Int](repeating: 0, count: 25001)
    let dx = [0,0,1,-1]
    let dy = [1,-1,0,0]
    
    func bfs(_ grid: [[Int]], _ dist: inout [[Int]], _ x: Int, _ y: Int, _ cnt: Int) {
        var q: [(Int, Int)] = []
        var front = 0
        var total = 1
        q.append((x, y))
        dist[x][y] = cnt
        while front != q.count {
            let output = q[front]
            front += 1
            for i in 0..<4 {
                let nx = output.0 + dx[i]
                let ny = output.1 + dy[i]
                if nx >= 0, nx < grid.count, ny >= 0, ny < grid[0].count {
                    if grid[nx][ny] == 1, dist[nx][ny] == 0 {
                        total += 1
                        dist[nx][ny] = cnt
                        q.append((nx, ny))
                    }
                }
            }
        }
        map[cnt] = total
    }
    func checkSum(_ dist: [[Int]], _ x: Int, _ y: Int)->Int {
        var ret = 0
        var visit = [Int] (repeating: 0, count: 4)
        for i in 0..<4 {
            let nx = x + dx[i]
            let ny = y + dy[i]
            if nx >= 0, nx < dist.count, ny >= 0, ny < dist[0].count {
                if dist[nx][ny] == 0 { continue }
                for j in 0..<i {
                    if visit[j] == dist[nx][ny] { visit[j] = 0 }
                }
                visit[i] = dist[nx][ny]
            }
        }
        return map[visit[0]] + map[visit[1]] + map[visit[2]] + map[visit[3]] + 1
    }
    
    func largestIsland(_ grid: [[Int]]) -> Int {
        let row = grid.count
        let col = grid[0].count
        let layer : [Int] = [Int](repeating: 0, count: col)
        var dist : [[Int]] = [[Int]](repeating: layer, count: row)
        //var visit: [[Int]] = [[Int]](repeating: layer, count: row)
        
        var cnt = 1
        for i in 0..<row {
            for j in 0..<col {
                if grid[i][j] == 1, dist[i][j] == 0 {
                    bfs(grid, &dist, i, j, cnt)
                    cnt += 1
                }
            }
        }
        
        var ans = 0
        for i in 0..<row {
            for j in 0..<col {
                if grid[i][j] == 0 {
                    let ret = checkSum(dist, i, j)
                    ans = ret > ans ? ret : ans
                }
            }
        }
        if ans == 0 {
            if cnt == 1 {
                ans = 1
            }else{
                ans = row * col
            }
        }
        return ans
    }
}
