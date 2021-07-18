import java.util.*;

class Solution {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    
    public int[] solution(String[][] places) {
        int[] sol = new int[places.length];
        All:for(int l = 0 ; l < places.length; l++) {
            for(int i = 0 ; i < places[l].length; i++) {
                for(int j = 0 ; j < places[l][i].length(); j++) {
                    if(places[l][i].charAt(j) == 'P'){
                        Queue<Tables> q = new LinkedList<>();
                        boolean[][] check = 
                            new boolean[places[l].length][places[l][i].length()];
                        q.add(new Tables(0,i,j));
                        while(!q.isEmpty()){
                            Tables table = q.poll();
                            check[table.y][table.x] = true;
                            if (table.cnt > 0) {
                                if(places[l][table.y].charAt(table.x) == 'P') {
                                    sol[l] = 0;
                                    continue All;
                                }   
                            }
                                
                            if (table.cnt >= 2) {
                                continue;
                            }
                            for(int k=0;k<4;k++){
                                int tempy = table.y + dy[k];
                                int tempx = table.x + dx[k];
                                if (tempy < 0 || tempy > places[i].length - 1
                                   || tempx < 0 || tempx > places[i][j].length() - 1) 
                                    continue;
                                if(check[tempy][tempx])
                                    continue;
                                if(places[l][tempy].charAt(tempx) == 'X')
                                    continue;
                                q.add(new Tables(table.cnt + 1, tempy, tempx));
                            }
                        }
                    }
                    if ( i == places[l].length - 1 && j == places[l][i].length() - 1){
                        sol[l] = 1;
                    }
                }
            }
        }
        return sol;
    }
    public class Tables {
        int cnt,y,x;
        public Tables(int cnt, int y, int x) {
            this.cnt = cnt;
            this.y = y;
            this.x = x;
        }
    }
}
