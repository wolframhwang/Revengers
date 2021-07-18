import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0 ; i < cmd.length; i++) {
            if(cmd[i].charAt(0) == 'D') {
                k += Integer.parseInt(cmd[i].substring(2));
            } else if(cmd[i].charAt(0) == 'U') {
                k -= Integer.parseInt(cmd[i].substring(2));
            } else if(cmd[i].charAt(0) == 'C') {
                stack.push(k);
                n -=1;
                if (n <= k) {
                    k--;
                }
            } else if(cmd[i].charAt(0) == 'Z') {
                n +=1;
                if (k >= stack.pop()) {
                    k++;
                }
            }
        }
        StringBuffer sb = new StringBuffer("");
        
        for(int i = 0 ; i < n;i++){
            sb.append("O");
        }
        int size = stack.size();
        for(int i = 0 ; i < size;i++){
            sb.insert(stack.pop(),"X");
        }
        return sb.toString();
    }
}
