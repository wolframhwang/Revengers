import java.util.*;

class Trie {
    Map<Character, Trie> childNode = new HashMap<>();
    boolean isLastNode;
}
//front, fr
class Solution {
    static int[] answer;

    public void dfs(String query, int index, int indexQuery, Trie nodeNow) {
        if (index == query.length() && nodeNow.isLastNode) {
            answer[indexQuery]++;
            return;
        } else if (index == query.length()) {
            return;
        }
        //frozen  query : froze

        Character now = query.charAt(index);

        if (now == '?') {
            Map<Character, Trie> child = nodeNow.childNode;
            for (Trie item : child.values()) {
                dfs(query, index + 1, indexQuery, item);
            }
        } else if (nodeNow.childNode.containsKey(now)) {
            dfs(query, index + 1, indexQuery, nodeNow.childNode.get(now));
        }
    }

    public int[] solution(String[] words, String[] queries) {
        answer = new int[queries.length];
        Arrays.fill(answer, 0);

        Trie rootNode = new Trie();

        for (String item : words) {
            Trie node = rootNode;

            for (int i = 0; i < item.length(); i++) {
                Character c = item.charAt(i);

                if (node.childNode.containsKey(c)) {
                    node = node.childNode.get(c);
                } else {
                    node.childNode.put(c, new Trie());
                    node = node.childNode.get(c);
                }
            }
            node.isLastNode = true;
        }

        for (int i = 0; i < queries.length; i++) {
            dfs(queries[i], 0, i, rootNode);
        }

        return answer;
    }
}