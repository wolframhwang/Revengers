import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        int stringLength = s.length();
        StringBuilder sb;
                
        for(int i=0;i<stringLength;i++) {
            sb = new StringBuilder();
            for(int j=i;j<stringLength+i;j++) {
                sb.append(s.charAt(j%stringLength));
            }
            if(checkRight(sb.toString()))
                answer++;
        }
        
        return answer;
    }
    
    public boolean checkRight(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++) {
            char now = s.charAt(i);
            if(now == '[' || now == '{' || now == '(')
                stack.push(now);
            else {
                if(stack.isEmpty()) 
                    return false;
                char before = stack.pop();
                if(before == '[' && now == ']')
                    continue;
                
                if(before == '{' && now == '}')
                    continue;
                
                if(before == '(' && now == ')')
                    continue;

                return false;
            }
        }
        if(!stack.isEmpty())
            return false;
        return true;
    }
}
