/*
  수학적으로 푸는 문제입니다...
*/
import java.util.*;
class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        Map<Integer,Integer> wlist = new HashMap<>();
        Map<Integer,Integer> hlist = new HashMap<>();
        int tempw = w;
        int temph = h;
        for(int i=2;i<tempw;i++){
            if(tempw%i==0){
                if(wlist.containsKey(i)){
                    wlist.put(i,wlist.get(i)+1);
                }else{
                    wlist.put(i,1);
                }
                tempw/=i;
                i--;
            }
        }
        if(tempw!=1){
            if(wlist.containsKey(tempw)){
                wlist.put(tempw,wlist.get(tempw)+1);
            }else{
                wlist.put(tempw,1);
            }
        }
        
        for(int i=2;i<temph;i++){
            if(temph%i==0){
                if(hlist.containsKey(i)){
                    hlist.put(i,hlist.get(i)+1);
                }else{
                    hlist.put(i,1);
                }
                temph/=i;
                i--;
            }
        }
        if(temph!=1){
            if(hlist.containsKey(temph)){
                hlist.put(temph,hlist.get(temph)+1);
            }else{
                hlist.put(temph,1);
            }
        }
        int tempsol = w;
        long sol = 1;
        for(int i=2;i<tempsol;i++){
            if(tempsol%i==0){
                if(wlist.containsKey(i) && hlist.containsKey(i)){
                    int val = 0;
                    val = Math.min(wlist.get(i),hlist.get(i));
                    for(int j=0;j<val;j++){
                        sol *=i;
                    }
                    wlist.remove(i);
                }
                tempsol/=i;
                i--;
            }
        }
        if(tempsol!=1){
            if(wlist.containsKey(tempsol) && hlist.containsKey(tempsol)){
                    int val = 0;
                    val = Math.min(wlist.get(tempsol),hlist.get(tempsol));
                    for(int j=0;j<val;j++){
                        sol *=tempsol;
                    }
                    wlist.remove(tempsol);
                }
        }
        long answerw = (long)w;
        long answerh = (long)h;
        answer = answerw*answerh;
        answer -= (w+h - sol);
        return answer;
    }
}
