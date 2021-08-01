/* 효율성 out */
class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        StringBuilder sb = new StringBuilder(s);
        int start = 0;
        int l = sb.length();
        while (start+1 < l) {
            if(sb.charAt(start) == sb.charAt(start+1)) {
                sb.delete(start,start+2);
                start=0;
                l=sb.length();
                continue;
            }
            start++;
        }

        if(sb.length() == 0)
            return 1;

        return 0;
    }
}

/* 정답 */
import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for(int i=0;i<s.length();i++) {
            if(stack.isEmpty()) {
                stack.push(s.charAt(i));
                continue;
            }
            
            if(stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        
        if(stack.isEmpty())
            return 1;

        return 0;
    }
}
