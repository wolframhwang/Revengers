class Solution {
    public int findMaximumXOR(int[] nums) {
        int answer = -1;

        Set<Integer> set = new HashSet<>();

        for(int item : nums) {
            set.add(item);
        }

        HashMap<Integer, Map> trie = new HashMap<>();

        List<Integer> answerList = new ArrayList<>();
        for(int item : nums) {
            Map now = trie;
            for(int i=31;i>=0;i--) {
                int mask = (1 << i) ;
                if((mask & item) != 0) {
                    if(!now.containsKey(1)) {
                        now.put(1, new HashMap<Integer, Map>());
                    }
                    now = (Map) now.get(1);
                } else {
                    if(!now.containsKey(0)) {
                        now.put(0, new HashMap<Integer, Map>());
                    }
                    now = (Map) now.get(0);
                }
            }

        }

        for(int item : nums) {
            Map now = trie;
            StringBuilder sb = new StringBuilder();
            for(int i=31; i>=0; i--) {
                int mask = 1 << i;

                if((mask & item) != 0) {
                    if(now.containsKey(0)) {
                        now = (Map) now.get(0);
                        sb.append(0);
                    }
                    else {
                        now = (Map) now.get(1);
                        sb.append(1);
                    }
                } else {
                    if(now.containsKey(1)) {
                        now = (Map) now.get(1);
                        sb.append(1);
                    }
                    else {
                        now = (Map) now.get(0);
                        sb.append(0);
                    }
                }
            }
            answer = Math.max(answer, item^Integer.valueOf(sb.toString(),2));
        }

        return answer;
    }
}