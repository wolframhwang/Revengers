/*
열심히 구현하면 됩니다.
*/
import java.util.*

class Solution {
    //직사각형 좌표
    data class COD(var l: Int, var r : Int, var u : Int, var d : Int)
    var chk = Array(50){IntArray(50)}
    var bo = Array<IntArray>(0){IntArray(0)}
    var query = 0
    var dr = intArrayOf(-1,0,1,0)
    var dc = intArrayOf(0,1,0,-1)
    fun isIn(r:Int, c: Int, n: Int): Boolean {
        if(r < 0 || c < 0 || r >= n || c >= n) return false
        return true
    }
    //검은 블록을 놓았을때 깨질 수 있는지 확인 위로 올라가면서 계속 0이면 된다.
    fun canDistroy(i: Int, j: Int):Boolean {
        var i = i
        var j = j
        while(i >= 0) {
            if(bo[i][j] != 0) return false
            i-=1
        }
        return true
    }
    //따로 블록위치 저장하기 귀찮아서 직사각형을 모두 0으로 만듦. (유효하다)
    fun distroy(p: COD){
        for(i in p.u .. p.d) {
            for(j in p.l .. p.r){
                bo[i][j] = 0
            }
        }
    }
    
    
    var size = 0
    //어떤 블록의 직사각형 위치를 구함
    fun getInfo(b: Int, i: Int,j :Int): COD {
        query++
        var l = j
        var r = j
        var u = i
        var d = i
        var Q: Queue<Pair<Int,Int>> = LinkedList()
        chk[i][j] = query
        Q.add(Pair(i,j))
        while(Q.isNotEmpty()) {
            val(i, j) = Q.poll()
            l = Math.min(j,l)
            r = Math.max(j,r)
            u = Math.min(i,u)
            d = Math.max(i,d)
            for(d in 0 .. 3) {
                var nr = i + dr[d]
                var nc = j + dc[d]
                if(isIn(nr,nc, size) && chk[nr][nc] != query && b == bo[nr][nc]) {
                    chk[nr][nc] = query
                    Q.add(Pair(nr,nc))
                }
            }
        }
        
        return COD(l,r,u,d)
    }
    fun solution(board: Array<IntArray>): Int {
        size = board.size
        bo = board
        var c = IntArray(201)
        var info = ArrayList<ArrayList<Pair<Int,Int>>>()
        var info2 = ArrayList<COD>()
        var answer = 0
        for(i in board.indices){
            for(j in board.indices){
                if(board[i][j] != 0 && c[board[i][j]] == 0) {
                    info.add(ArrayList<Pair<Int,Int>>())
                    c[board[i][j]] = 1
                    val(l,r,u,d) = getInfo(board[i][j], i,j)
                    info2.add(COD(l,r,u,d))
                    var b = board[i][j]
                    for(i in u .. d) {
                        for(j in l .. r) {
                            if(board[i][j] != b) {
                                info.last().add(i to j)
                            }
                        }
                    }
                }
            }
        }
        var distroyed = IntArray(info.size)
        var continued = true
        while(continued) {
            continued = false
            for(i in info.indices) {
                if(distroyed[i] == 1) continue
                var d = true
                for((r, c) in info[i]) {
                    if(!canDistroy(r,c)) {
                        d = false
                        break
                    }
                }
                if(d) {
                    continued = true
                    answer++
                    distroyed[i] = 1
                    distroy(info2[i])
                }
            }
        }
        return answer
    }
}
