class Solution {
    static int answer;
    
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        boolean check = false;
        
        for(String item : words) {
            if(item.equals(target)) {
                check = true;
                break;
            }
        }
        
        if(!check)
            return 0;
        
        boolean[] visited = new boolean[words.length];
        
        bfs(begin, target, words, 0, visited);
        
        return answer;
    }
    
    void bfs(String begin, String target, String[] words, int changeNum, boolean[] visited) {
        int sameChar = 0;
        
        if(target.equals(begin))
            answer = Math.min(answer, changeNum);
        
        for(int j=0;j<words.length;j++) {
            if(visited[j])
                continue;
            
            String candidate = words[j];
            sameChar = 0;
            for(int i=0; i<candidate.length(); i++) {
                if(begin.charAt(i) == candidate.charAt(i)) {
                    sameChar++;
                }
            }
            if(sameChar == candidate.length() - 1) {
                visited[j] = true;
                bfs(candidate, target, words, changeNum+1, visited);
                visited[j] = false;
            }
        }
    }
}