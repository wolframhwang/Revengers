/*
아직 안풀었고, 풀이 과정 정리

1. 현 위치에서 "갈수있는" 포인트를 큐에 담아 놓는다.(이 과정은 BFS와 유사하다.)
  갈수 있는 의 가장 큰 의의는, 어떤 방식으로든 통과가 된다는 것이다. -> 몇 회를 하든 갈수 있다가 중요함.
2. "갈수있는" 큐에서 하나씩 꺼내서, 갈수있는 곳까지 가는 최소한의 숫자를 구한다.
  Dijkstra를 생각해볼법도 하지만, 그냥 BFS가 낫겠다.
3. 구한 숫자를 토대로 계속해서 재귀를 돌려서, 최소 값을 찾아낸다.
*/
// Solved By wolfram Hwang
//완탐!

import Foundation
var map : [[Int]] = []
let dx = [0,0,1,-1]
let dy = [1,-1,0,0]
var visit: [[Bool]] = []
let layer = [Bool](repeating: false, count: 4)
var dist : [[Int]] = []
let range = 0..<4
let inf = 987654321
let dst = [Int](repeating: inf, count: 4)
var ans = inf
var total = 0
func initVisit() {
    visit = []
    for _ in range{
        visit.append(layer)
    }
}
func point(_ x: Int, _ y: Int, _ x2: Int, _ y2: Int)-> (Int, Int) {
    dist = []
    for _ in range {
        dist.append(dst)
    }
    dist[x][y] = 0
    var q: [(Int, Int, Int)] = [(x,y,0)]
    var front = 0
    while q.count != front {
        let xx = q[front].0
        let yy = q[front].1
        var cnt = q[front].2
        front += 1
        for i in range {
            let nx = xx + dx[i]
            let ny = yy + dy[i]
            if nx >= 0, nx < 4, ny >= 0, ny < 4 {
                if dist[nx][ny] > cnt + 1 {
                    dist[nx][ny] = cnt + 1
                    q.append((nx, ny, cnt + 1))
                }
            }
        }
        for i in range {
            for j in 1...3 {
                let nx = xx + (dx[i] * j)
                let ny = yy + (dy[i] * j)
                if nx < 0 || nx >= 4 || ny < 0 || ny >= 4 {
                    break
                }
                if map[nx][ny] != 0 {
                    if dist[nx][ny] > cnt + 1{
                        dist[nx][ny] = cnt + 1
                        q.append((nx, ny, cnt + 1))
                    }
                    break
                }else{
                    if nx == 3, ny == yy {
                        if dist[nx][ny] > cnt + 1{
                            dist[nx][ny] = cnt + 1
                            q.append((nx, ny, cnt + 1))
                        }
                        break
                    }
                    if nx == 0, ny == yy {
                        if dist[nx][ny] > cnt + 1{
                            dist[nx][ny] = cnt + 1
                            q.append((nx, ny, cnt + 1))
                        }
                        break
                    }
                    if ny == 3, nx == xx {
                        if dist[nx][ny] > cnt + 1{
                            dist[nx][ny] = cnt + 1
                            q.append((nx, ny, cnt + 1))
                        }
                        break
                    }
                    if ny == 0, nx == xx {
                        if dist[nx][ny] > cnt + 1{
                            dist[nx][ny] = cnt + 1
                            q.append((nx, ny, cnt + 1))
                        }
                        break
                    }
                }
            }
        }
    }
    
    return (dist[x2][y2], map[x2][y2])
}
func recur(_ x: Int, _ y: Int, _ cnt: Int, _ now : Int, _ cardCnt: Int) {
    if cnt >= ans {
        return
    }
    if cardCnt == total {
        if ans > cnt {
            ans = cnt
        }
        return
    }
    initVisit()
    visit[x][y] = true
    var q: [(Int, Int)] = [(x, y)]
    var next: [(Int, Int)] = []
    var front = 0
    while q.count != front {
        let xx = q[front].0
        let yy = q[front].1
        front += 1
        for i in range {
            let nx = xx + dx[i]
            let ny = yy + dy[i]
            if nx >= 0, nx < 4, ny >= 0, ny < 4 {
                if visit[nx][ny] == false {
                    if now == 0 {
                        visit[nx][ny] = true
                        if map[nx][ny] != 0 {
                            next.append((nx, ny))                            
                        }
                        q.append((nx, ny))
                    }else{
                        visit[nx][ny] = true
                        if map[nx][ny] == now {
                            next.append((nx, ny))
                        }
                        q.append((nx, ny))
                    }
                }
            }
        }
    }
    for qp in next {
        let dist = point(x,y,qp.0, qp.1)
        if map[x][y] == 0 {
            recur(qp.0, qp.1, cnt + dist.0 + 1, map[qp.0][qp.1], cardCnt)
        }else{
            let temp = map[x][y]
            let temp2 = map[qp.0][qp.1]
            map[x][y] = 0
            map[qp.0][qp.1] = 0
            recur(qp.0, qp.1, cnt + dist.0 + 1, 0, cardCnt + 2)
            map[x][y] = temp
            map[qp.0][qp.1] = temp2
        }
        
    }
}

func solution(_ board:[[Int]], _ r:Int, _ c:Int) -> Int {
    map = board
    
    for i in range {
        for j in range {
            if map[i][j] != 0 {
                total += 1
            }
        }
    }
    if map[r][c] != 0 {
        recur(r, c, 1, map[r][c], 0)
    }else{
        recur(r,c, 0, map[r][c], 0)
    }
    return ans
}
