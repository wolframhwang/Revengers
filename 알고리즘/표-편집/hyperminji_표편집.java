import java.util.*;
 
class Solution {
    
    public String solution(int n, int k, String[] cmd) { 
        Stack<Integer> remove = new Stack<>();
        int tableSize = n;
        
        for(int i = 0; i < cmd.length; i++) {
            char c = cmd[i].charAt(0);
 
            if(c == 'U') {
              //'U'
                k = k- Integer.valueOf(cmd[i].substring(2));
            } else if(c == 'D') {
              //'D'
                k = k + Integer.valueOf(cmd[i].substring(2));
            } else if(c == 'C') {
              //'C'
                remove.push(k);
                tableSize = tableSize - 1;
                if(k == tableSize) k = k - 1;
            } else {
              //'Z'
                int r = remove.pop(); 
                if(k >= r) k = k + 1;
                tableSize = tableSize + 1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < tableSize; i++) {
            sb.append('O');
        }
        while(!remove.isEmpty()) {
            sb.insert(remove.pop().intValue(), 'X');
        }
        return sb.toString();
    }
}
