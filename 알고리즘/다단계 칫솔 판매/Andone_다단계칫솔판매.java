import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Integer> map = new HashMap<>();
        Map<String, String> tree = new HashMap<>();

        for(String item : enroll) {
            map.put(item,0);
        }

        for(int i=0;i<enroll.length;i++) {
            tree.put(enroll[i], referral[i]);
        }

        for(int i=0;i<seller.length;i++) {
            String parrent = seller[i];
            int baseMoney = amount[i] * 100;

            while(!parrent.equals("-")) {
                if(baseMoney < 1)
                    break;
                int myMoney = baseMoney - (int) (baseMoney * 0.1);
                baseMoney = (int) (baseMoney * 0.1);
                
                map.computeIfPresent(parrent, (key, value) -> value += myMoney);
                parrent = tree.get(parrent);
            }
        }
        
        for(int i=0;i<enroll.length;i++) {
            answer[i] = map.get(enroll[i]);
        }

        return answer;
    }
}