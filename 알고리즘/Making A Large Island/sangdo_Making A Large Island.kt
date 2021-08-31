/*
우선 island들을 bfs를 이용하여 구분한다. (O(N^2))
이제,
2차원 맵을 돌면서 0일때만 상/하/좌/우에 있는 island의 크기들을 합친다. 
이 때, 상/하/좌/우에 있는 island의 종류가 다른 경우에만 합친다!
이것으로 O(N^2)으로 해결
*/

var sizeMap = IntArray(250001)
val dr = intArrayOf(-1,0,1,0)
val dc = intArrayOf(0,1,0,-1)
var query2 = 0
var chk2 = IntArray(500*500+1)
var chk = Array(500){IntArray(500)}
var query = 0
class Solution {
    fun largestIsland(grid: Array<IntArray>): Int {
        var n = grid.size
        var m = grid[0].size
        var Q:Queue<Int> = LinkedList()
        var answer = 0
        val q = query
        
        for(i in 0 until n) {
            for(j in 0 until m) {
                if(grid[i][j] == 1) {
                    query++
                    if(chk[i][j] > q) continue
                    sizeMap[query-q] = 0
                    Q.add(i*500+j)
                    chk[i][j] = query
                    
                    while(Q.isNotEmpty()){
                        var p = Q.poll()
                        var r = p/500
                        var c = p%500
                        sizeMap[query-q]++
                        
                        for(d in 0 .. 3) {
                            var nr = r + dr[d]
                            var nc = c + dc[d]
                            if(nr < 0 || nc < 0 || nr == n || nc == m) continue
                            if(chk[nr][nc] == query) continue
                            if(grid[nr][nc] == 0) continue
                            chk[nr][nc] = query
                            Q.add(nr*500 + nc)
                        }
                    }
                    answer = Math.max(answer, sizeMap[query-q])
                }
                else {
                    chk[i][j] = q
                }
            }
        }
        for(i in 0 until n){
            for(j in 0 until m){
                if(grid[i][j] == 0){
                    query2++
                    var maxSize = 0
                    for(d in 0 .. 3){
                        var nr = i + dr[d]
                        var nc = j + dc[d]
                        if(nr < 0 || nc < 0 || nr == n || nc == m) continue
                        if(chk2[chk[nr][nc]-q] == query2) continue
                        chk2[chk[nr][nc]-q] = query2
                        maxSize += sizeMap[chk[nr][nc]-q]
                    }
                    answer = Math.max(answer, maxSize+1)
                }
            }
        }
        return answer
    }
}
