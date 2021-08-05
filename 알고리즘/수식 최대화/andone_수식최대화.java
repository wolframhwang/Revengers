import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = 0;

        StringBuilder sb = new StringBuilder(expression);
        StringBuilder temp = new StringBuilder();

        List<Stack> order = new ArrayList<>();
        makeOrder(order);

        ArrayList<String> expressionList = new ArrayList<>();
        int length = sb.length();
        for(int i = 0;i<length;i++) {
            if(sb.charAt(i) == '+' || sb.charAt(i) == '-' || sb.charAt(i) == '*') {
                expressionList.add(temp.toString());
                expressionList.add(String.valueOf(sb.charAt(i)));
                temp = new StringBuilder();
            }
            else
                temp.append(sb.charAt(i));
        }

        expressionList.add(temp.toString());
        for(Stack<Character> orderNow : order) {
            List<String> calList = (List<String>) expressionList.clone();
            Deque<String> dq1 = new ArrayDeque<>();
            while(!orderNow.isEmpty()) {
                Character now = orderNow.pop();

                for(int i=0;i<calList.size();i++) {
                    String expressionNow = calList.get(i);
                    if(expressionNow.equals(String.valueOf(now))) {
                        String number1 = dq1.pop();
                        i++;
                        String number2 = calList.get(i);
                        long result = calculate(number1, expressionNow, number2);
                        dq1.push(String.valueOf(result));
                    } else {
                        dq1.push(expressionNow);
                    }
                }
                calList = new ArrayList<>();
                while(!dq1.isEmpty()) {
                    calList.add(dq1.pollLast());
                }
            }
            answer = Math.max(Math.abs(Long.parseLong(calList.get(0))),answer);
        }

        return answer;
    }

    public long calculate (String number1, String expression, String number2) {
        long a = Long.parseLong(number1);
        long b = Long.parseLong(number2);

        switch(expression) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                return 0;
        }
    }

    public void makeOrder(List<Stack> list) {
        Stack<Character> order1 = new Stack<>();
        order1.push('+');
        order1.push('-');
        order1.push('*');

        Stack<Character> order2 = new Stack<>();
        order2.push('+');
        order2.push('*');
        order2.push('-');

        Stack<Character> order3 = new Stack<>();
        order3.push('-');
        order3.push('*');
        order3.push('+');

        Stack<Character> order4 = new Stack<>();
        order4.push('-');
        order4.push('+');
        order4.push('*');

        Stack<Character> order5 = new Stack<>();
        order5.push('*');
        order5.push('+');
        order5.push('-');

        Stack<Character> order6 = new Stack<>();
        order6.push('*');
        order6.push('-');
        order6.push('+');

        list.add(order1);
        list.add(order2);
        list.add(order3);
        list.add(order4);
        list.add(order5);
        list.add(order6);
    }
}