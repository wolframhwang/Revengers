import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int n = queries.length;
        
        int[] answer = new int[n];
        int[][] map = new int[rows][columns];
        int count = 1;
        
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                map[i][j] = count++;
            }
        }
        
        for(int i=0;i<n;i++) {
            answer[i] = rotate(rows, columns, queries[i], map);
        }
        
        return answer;
    }
    
    public void print(int rows, int columns, int[][] map) {
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }
    
    public int rotate(int rows, int columns, int[] queries, int[][] map) {        
        int x1 = queries[0];
        int y1 = queries[1];
        int x2 = queries[2];
        int y2 = queries[3];
        
        int[] arr = new int[Math.abs(x1-x2) * 2 + Math.abs(y1-y2) * 2];
        /*
        map[x1][y1];
        (x1,y1),(x1,y2),(x2,y1).(x2,y2);
        */
        
        int num = 0;
        
        //x1 행기준으로 배열에 적재
        for(int i=y1-1;i<=y2-1;i++) {
            arr[num++] = map[x1-1][i];
        }
        
        num--;
        
        //열 기준으로 배열에 적재
        for(int i=x1-1;i<=x2-1;i++) {
            arr[num++] = map[i][y2-1];
        }
        
        num--;
        
        //행 역순으로 배열에 적재
        for(int i=y2-1;i>=y1-1;i--) {
            arr[num++] = map[x2-1][i];
        }
        
        num--;
        
        //열 역순으로 배열에 적재
        for(int i=x2-1;i>x1-1;i--) {
            arr[num++] = map[i][y1-1];
        }
        

        //System.out.println(Arrays.toString(arr));
        
        /*
        넣는 부분
        */
         //x1 행기준으로 map에 적재
        num = arr.length-1;
         
        //x1 행기준으로 배열에 적재
        for(int i=y1-1;i<=y2-1;i++) {
            map[x1-1][i] = arr[(num++)%arr.length];
        }
        
        num--;
        
        //열 기준으로 배열에 적재
        for(int i=x1-1;i<=x2-1;i++) {
             map[i][y2-1] = arr[(num++)%arr.length];
        }
        
        num--;
        
        //행 역순으로 배열에 적재
        for(int i=y2-1;i>=y1-1;i--) {
            map[x2-1][i] = arr[(num++)%arr.length];
        }
        
        num--;
        
        //열 역순으로 배열에 적재
        for(int i=x2-1;i>x1-1;i--) {
            map[i][y1-1] = arr[(num++)%arr.length];
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0;i<arr.length;i++) {
            min = Math.min(arr[i],min);
        }
        
        return min;
    }
}