import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Map<Character, String> map = new HashMap<>();
        Map<Character, String> map2 = new HashMap<>();
        int sol = 0;
        for(int i = 0 ; i < skill.length(); i++) {
            map.put(skill.charAt(i),skill.substring(i,skill.length()));
        }
        for(int i = 0 ; i < skill.length(); i++) {
            map2.put(skill.charAt(i),skill.substring(0,i));
        }
        
        All:for(int i = 0 ; i < skill_trees.length; i++) {
            
            Map<Character, Integer> tempmap = new HashMap<>();
            
            for(int j = 0 ; j < skill_trees[i].length(); j++) {
                if(map.containsKey(skill_trees[i].charAt(j))) {
                    String temps = map.get(skill_trees[i].charAt(j));
                    for (int k = 0 ; k < temps.length(); k++) {
                        if(tempmap.containsKey(temps.charAt(k))){
                            continue All;
                        }
                    }
                    
                    String temps2 = map2.get(skill_trees[i].charAt(j));
                    for (int k = 0 ; k < temps2.length(); k++) {
                        if(!tempmap.containsKey(temps2.charAt(k))){
                            continue All;
                        }
                    }
                }
                
                tempmap.put(skill_trees[i].charAt(j),1);
            }   
            sol++;
        }
        
        return sol;
    }
}
