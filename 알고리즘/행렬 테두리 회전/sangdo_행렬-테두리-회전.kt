/*
단순히 큐이용해서 돌림
*/

import java.util.*

class Solution {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = IntArray(queries.size)
        var cnt = 1
        var arr = Array(rows){IntArray(columns){i->cnt++}}
        var dr = intArrayOf(0,1,0,-1)
        var dc = intArrayOf(1,0,-1,0)
        for(i in queries.indices){
            var query = queries[i]
            var x1 = query[0]-1
            var y1 = query[1]-1
            var x2 = query[2]-1
            var y2 = query[3]-1
            var x = x1
            var y = y1
            var Q:Queue<Int> = LinkedList() 
            var dest = arrayOf(Pair(x1,y2), Pair(x2,y2), Pair(x2,y1), Pair(x1,y1))
            var res = 0x3f3f3f3f
            for(d in 0 .. 3){
                var (dx,dy) = dest[d]
                while(x != dx || y != dy) {
                    res = Math.min(arr[x][y],res)
                    Q.add(arr[x][y])
                    x += dr[d]
                    y += dc[d]
                }
            }
            x = x1
            y = y1+1
            for(d in 0 .. 3){
                var (dx,dy) = dest[d]
                while(x != dx || y != dy) {
                    arr[x][y] = Q.poll()
                    x += dr[d]
                    y += dc[d]
                }
            }
            arr[x][y] = Q.poll()
            answer[i] = res
        }
        return answer
    }
}
