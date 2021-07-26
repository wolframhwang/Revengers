import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        Queue<Target> q = new LinkedList<>();
        q.add(new Target(numbers[0], 1));
        q.add(new Target(-numbers[0], 1));
        int cnt = 0;
        while(!q.isEmpty()){
            Target tar = q.poll();
            if(tar.cnt >= numbers.length){
                if(tar.val == target){
                    cnt++;
                }
                continue;
            }
            q.add(new Target(tar.val + numbers[tar.cnt], tar.cnt+1));
            q.add(new Target(tar.val - numbers[tar.cnt], tar.cnt+1));
        }
        return cnt;
    }
    static class Target {
        int val,cnt;
        
        public Target(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }
}
