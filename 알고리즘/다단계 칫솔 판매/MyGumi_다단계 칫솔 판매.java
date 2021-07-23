import java.util.*;
/*
  Map을 이용하여 부모 - 자식간의 관계 표현하여
  COST가 0이 될 때 까지 계속 COST를 더해주는 방식
*/
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> mapName = new HashMap<>();
        Map<String, Integer> mapValue = new HashMap<>();
        
        for(int i = 0 ; i < enroll.length;i++) {
            mapName.put(enroll[i],referral[i]);
            mapValue.put(enroll[i],0);
        }
        
        int cost = 0;
        String temps = "";
        for(int i = 0 ; i < seller.length; i++) {
            temps = seller[i];
            cost = amount[i] * 100;
            int val = mapValue.get(seller[i]) + (int)(Math.ceil(cost * 0.9));
            mapValue.put(seller[i], val);
            while(mapName.get(temps).charAt(0) != '-') {
                if (cost == 0) {
                    break;
                }
                temps = mapName.get(temps);
                cost = (int)Math.floor(cost * 0.1);
                mapValue.put(temps, mapValue.get(temps) + 
                         (int)(Math.ceil(cost * 0.9)));
            }
            
        }
        int[] sol = new int[enroll.length];
        for(int i = 0 ; i < enroll.length;i++){
            sol[i] = mapValue.get(enroll[i]);
        }
        return sol;
    }
}
