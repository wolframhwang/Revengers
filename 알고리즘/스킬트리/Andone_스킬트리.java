//스킬트리는 중복되지 않으며 무조건 스킬트리는 한줄로만 움직이기 때문에 링크드리스트를 구현하여 풀었습니다.

class Node {
    char skillName;
    Node nextNode;
    
    Node (char name, Node nextNode) {
        this.skillName = name;
        this.nextNode = nextNode;
    }
}

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        Node rootNode = new Node(skill.charAt(0), null);
        Node head = rootNode;
        
        for(int i=1;i<skill.length();i++) {
            rootNode.nextNode = new Node(skill.charAt(i), null);
            rootNode = rootNode.nextNode;
        }
        
        for(int i=0;i<skill_trees.length;i++) {
            Node now = head;
            boolean flag = true;
            String treeNow = skill_trees[i];
            for(int j=0;j<treeNow.length();j++) {
                char charNow = treeNow.charAt(j);
                if(!skill.contains(String.valueOf(charNow))) {
                    continue;
                } else if(skill.contains(String.valueOf(charNow)) && now.skillName == charNow) {
                    now = now.nextNode;
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        
        return answer;
    }
}
