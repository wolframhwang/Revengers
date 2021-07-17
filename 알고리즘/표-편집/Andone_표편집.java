import java.util.*;

class Node {
    int number;
    Node beforeNode;
    Node afterNode;

    Node() {
    }

    Node(int number, Node beforeNode, Node afterNode) {
        this.number = number;
        this.beforeNode = beforeNode;
        this.afterNode = afterNode;
    }
}

class Solution {
    static Stack<Node> stack;
    static Node dummy = new Node();

    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Node head;
        dummy.beforeNode = dummy;
        dummy.afterNode = dummy;
        stack = new Stack<>();
        Node point = null;

        for(int i=0;i<n;i++) {
            Node temp = new Node(i, dummy.beforeNode, dummy);
            dummy.beforeNode.afterNode = temp;
            dummy.beforeNode = temp;

            if(i==k) point = temp;
        }
        head = dummy.afterNode;

        for (int i = 0; i < cmd.length; i++) {
            String[] ncmd = cmd[i].split(" ");
            switch (ncmd[0]) {
                case "D" -> point = down(Integer.parseInt(ncmd[1]), point);
                case "U" -> point = up(Integer.parseInt(ncmd[1]), point);
                case "C" -> point = delete(point);
                case "Z" -> restore();
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append("X".repeat(Math.max(0, n)));

        var cur = dummy.afterNode;
        while (cur != dummy) {
            sb.setCharAt(cur.number, 'O');
            cur = cur.afterNode;
        }

        answer = sb.toString();
        return answer;
    }

    public Node down(int n, Node point) {
        Node next = point;
        for (int j = 0; j < n; j++) {
            next = next.afterNode;
        }

        return next;
    }

    public Node up(int n, Node point) {
        Node beforeNode = point;
        for (int j = 0; j < n; j++) {
            beforeNode = beforeNode.beforeNode;
        }

        return beforeNode;
    }

    public Node delete(Node point) {
        point.afterNode.beforeNode = point.beforeNode;
        point.beforeNode.afterNode = point.afterNode;

        stack.push(point);

        if (point.afterNode == dummy) //끝점일 때
            return point.beforeNode;
        return point.afterNode;
    }

    public void restore() {
        if(stack.isEmpty())
            return;
        Node restore = stack.pop();

        restore.afterNode.beforeNode = restore;
        restore.beforeNode.afterNode = restore;
    }
}