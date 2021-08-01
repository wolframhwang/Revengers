import java.io.*;
import java.util.*;

//무슨 문제지..?

class Solution {
    static int answer = 0;
    void dfs(int[] numbers, int target, int now, int depth) {
        if(depth==numbers.length) {
            if(now==target)
                answer++;
            return;
        }
        int number = numbers[depth];
        dfs(numbers, target, now+number, depth+1);
        dfs(numbers, target, now-number, depth+1);
    }

    public int solution(int[] numbers, int target) {
        dfs(numbers,target,0,0);

        return answer;
    }
}
