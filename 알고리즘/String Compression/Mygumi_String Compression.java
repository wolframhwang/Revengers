
/*
  Stack을 활용하여 Count를 순서대로 index대로 주입하며 풀었습니다.
*/
import java.util.*;

class Solution {
    public int compress(char[] chars) {
        char tempc = chars[0];
        
        int val = 0;
        Stack<Character> stack = new Stack<>();
        int index = 0;
        for(int i = 0 ; i < chars.length; i++) {
            if (tempc == chars[i]) {
                val++;
            } else {
                chars[index++] = tempc; 
                tempc = chars[i];
                if(val == 1){
                    continue;
                }
                while(val != 0) {
                    stack.push((char)(val % 10 + 48));
                    val = val/10;
                }
                while(!stack.isEmpty()) {
                    chars[index++] = stack.pop();
                }
                val = 1;
            }
        }
        if(val == 1) {
            chars[index++] = tempc;
        } else{
            chars[index++] = tempc;
            while(val != 0) {
                    stack.push((char)(val % 10 + 48));
                    val = val/10;
            }
            while(!stack.isEmpty()) {
                chars[index++] = stack.pop();
            }
        }
        return index;
    }
}
