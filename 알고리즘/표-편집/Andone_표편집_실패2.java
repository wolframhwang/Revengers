import java.util.*;

class Node {
    int number;
    Node beforeNode;
    Node afterNode;

    Node(Node beforeNode, int number, Node afterNode) {
        this.number = number;
        this.beforeNode = beforeNode;
        this.afterNode = afterNode;
    }
}

class Solution {
    static Stack<Node> stack;

    public String solution(int n, int k, String[] cmd) {
        String answer = "";
        Node head;
        
        head = new Node(null, 0, null);
        Node start = head;

        stack = new Stack<>();

        for (int i = 1; i < n; i++) {
            start.afterNode = new Node(start, i, null);
            start = start.afterNode;
        }

        start = head;

        for (int i = 0; i < k; i++) {
            start = start.afterNode;
        }

        Node point = start;

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

        for (int i=0;i<n;i++){
            if(head == null) {
                sb.append("X");
                continue;
            }
            if (head.number == i) {
                head = head.afterNode;
                sb.append("O");
            } else {
                sb.append("X");
            }
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
        Node afterNode = null;
        Node beforeNode = null;
        if(point.beforeNode == null && point.afterNode == null) {

        } else if(point.beforeNode == null) {
            afterNode = point.afterNode;
            afterNode.beforeNode = null;
        } else if(point.afterNode == null) {
            beforeNode = point.beforeNode;
            beforeNode.afterNode = null;
        } else {
            afterNode = point.afterNode;
            beforeNode = point.beforeNode;
            afterNode.beforeNode = beforeNode;
            beforeNode.afterNode = afterNode;
        }

        stack.push(point);

        if (point.afterNode == null) //끝점일 때
            return point.beforeNode;
        return point.afterNode;
    }

    public void restore() {
        if(stack.isEmpty())
            return;
        Node restore = stack.pop();

        if(restore.beforeNode == null && restore.afterNode == null) {
            return;
        }

        if(restore.beforeNode == null) {
            restore.afterNode.beforeNode = restore;
        } else if(restore.afterNode == null) {
            restore.beforeNode.afterNode = restore;
        } else {
            restore.afterNode.beforeNode = restore;
            restore.beforeNode.afterNode = restore;
        }
    }
}
