/*
  Stack으로 열린,닫힌 괄호 비교 하며 풀었습니다.
*/

import java.util.*;
class Solution {
    public int solution(String s) {
        int size = s.length();
        int sol = 0;
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < size; i++) {
            stack.push(s.charAt(i));
        }
        All:for(int i = 0; i < size; i++) {
            Stack<Character> lotate = new Stack<>();
            for(int j = 0 ; j < size; j++) {
                if (stack.get((j + i) % stack.size()) == ')') {
                    if (lotate.isEmpty()) {
                        continue All;
                    }
                    if (lotate.peek() == '(') {
                        lotate.pop();
                    } else {
                        continue All;
                    }
                } else if (stack.get((j + i) % stack.size()) == ']') {
                    if (lotate.isEmpty()) {
                        continue All;
                    }
                    if (lotate.peek() == '[') {
                        lotate.pop();
                    } else {
                        continue All;
                    }
                } else if (stack.get((j + i) % stack.size()) == '}') {
                    if (lotate.isEmpty()) {
                        continue All;
                    }
                    if (lotate.peek() == '{') {
                        lotate.pop();
                    } else {
                        continue All;
                    }
                } else{
                    lotate.push(stack.get((j + i) % stack.size()));
                }
            }
            if(lotate.size() > 0) {
                continue;
            }
            sol++;
        }
        return sol;
    }
}
