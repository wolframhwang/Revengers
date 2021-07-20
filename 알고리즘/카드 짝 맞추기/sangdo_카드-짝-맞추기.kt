import java.util.*

/*
예전에 풀었던건데 극혐이네요
풀이는 일단 bfs. 단, 단순히 visit[x][y]를 확장하여 visit[check][x][y]로 확장.

핵심은 해당 카드는 2쌍씩 6장과 하나를 뒤집었는지 나타내는 플래그로 (총 12 비트 + 플래그 = 2048) * 4*4

1. 1,2,3,4,5,6을 01 비트, 23비트 45비트 67비트, ~ 10 11 비트로 묶도록 쪼갬

2.bfs 돌림
	1) enter
		1. 만약, 카드를 뒤집지 않고, 바닥이 0이 아니라면 해당 카드를 뒤집음 (13비트 1로 on)
    	2. 카드를 이미 한장 뒤집었고 (13비트) 현재 바닥과 짝이 이루는 카드의 비트가 1이라면, 똑같은 카드 2개를 뒤집은 것임. (현재카드 번호 xor 1)
    2) 상하좌우
    	일반 bfs처럼 상하좌우 한칸씩 이동
    3) 컨트롤 키
    	1. 바닥이 0인경우 이동방향으로 계속 이동
        2. 바닥이 0이 아닌경우 : 만약 해당 바닥과 짝을 이루는 카드(xor 1)와 해당 바닥 카드가 모두 비트가 1이라면 이미 둘 다 뒤집어진 카드이므로 넘어감
        3. 바닥이 0이 아니고 2번이 아닌경우 해당 (r,c) 로 이동
        4. 맵 밖으로 이동한 경우: -dr, -dc를 해줌
        
3. 만약, check를 모두 뒤집었다면, 해당 dp[check][r][c] 를 반환해주면 됨
*/

class Solution {
    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        var nums = IntArray(7)
        var dp = Array(1 shl 13){IntArray(16){0x3f3f3f3f}}
        var info = IntArray(16)
        var answer: Int = 0
        var Q:Queue<Pair<Int,Int>> = LinkedList()
        var end = 0
        
        for(i in 0 .. 3){
            for(j in 0 .. 3){
                if(board[i][j] == 0) continue
                var num = 2*(board[i][j] -1) + nums[board[i][j]]++
                info[i*4+j] = num
                end = end or (1 shl num)
            }
        }
        Q.add(Pair(0,r*4+c))
        var dr = intArrayOf(-1,0,1,0)
        var dc = intArrayOf(0,1,0,-1)
        dp[0][r*4 + c] = 0
       	var chk:(Int,Int)->Boolean = {r,c -> r < 0 || c < 0 || r == 4 || c == 4}
        while(Q.isNotEmpty()) {
            var (check, now) = Q.poll()
            var r = now/4
            var c = now%4
            if(check == end) return dp[check][now]
            
            var other = info[now] xor 1
            if(board[r][c] != 0 && ((check and (1 shl info[now])) == 0 )&& ((check shr 12) == 0)){
                var nextCheck = check or (1 shl info[now]) or (1 shl 12)
                if(dp[nextCheck][now] > dp[check][now] + 1) {
                    dp[nextCheck][now] = dp[check][now] + 1
                    Q.add(Pair(nextCheck, now))
                }
            }
            if(board[r][c] != 0 && (check and (1 shl info[now])) == 0 && (check shr 12) == 1 && (check and (1 shl other) != 0)){
                var nextCheck = (check or (1 shl info[now])) and 0xfff
                if(dp[nextCheck][now] > dp[check][now] + 1) {
                    dp[nextCheck][now] = dp[check][now] + 1
                    Q.add(Pair(nextCheck, now))
                }
            }
            
            for(d in 0 .. 3) {
                var nr = r + dr[d]
                var nc = c + dc[d]
                if(chk(nr,nc)) continue
                if(dp[check][now] + 1 < dp[check][nr*4+nc]) {
                    dp[check][nr*4+nc] = dp[check][now] + 1
                    Q.add(Pair(check,nr*4+nc))
                }
                while(!chk(nr,nc) && (board[nr][nc] == 0 || (check and (1 shl info[nr*4+nc])) != 0  &&(check and (1 shl (info[nr*4+nc] xor 1))) != 0)){
                    nr+=dr[d]
                    nc+=dc[d]
                }
                if(chk(nr,nc)){
                    nr-=dr[d]
                    nc-=dc[d]
                }
                if(dp[check][now] + 1 < dp[check][nr*4+nc]) {
                    dp[check][nr*4+nc] = dp[check][now]+1
                    Q.add(Pair(check,nr*4+nc))
                }
            }
        }
        
        return -1
    }
}
