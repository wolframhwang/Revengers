class Solution {
    static Stack<Integer> stack;


    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        List<Integer> list = new LinkedList<>();
        stack  = new Stack<>();

        for(int i=0; i<n;i++) {
            list.add(i);
        }

        ListIterator<Integer> iter = list.listIterator(k);
        for(int i=0;i<cmd.length;i++) {
            String[] ncmd = cmd[i].split(" ");
            switch(ncmd[0]) {
                case "D" :
                    down(Integer.parseInt(ncmd[1]), iter);
                    break;
                case "U" :
                    up(Integer.parseInt(ncmd[1]), iter);
                    break;
                case "C" :
                    delete(iter);
                    break;
                case "Z" :
                    restore(list);
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        int no = 0;

        for(int i=0;i<n;i++) {
            if(list.get(i-no)==i) {
                sb.append("O");
            } else {
                sb.append("X");
                no++;
                continue;
            }
        }
        answer = sb.toString();
        return answer;
    }

    public void down(int n, ListIterator<Integer> iter) {
        for(int j=0;j<n;j++) {
            System.out.println("down:"+iter.next());
        }
    }

    public void up(int n, ListIterator<Integer> iter) {
        try {
            for(int j=0;j<n;j++) {
                System.out.println("up:"+iter.previous());
            }
        }catch (Exception e) {
            iter.next();
        }
    }

    public void delete(ListIterator<Integer> iter) {
        Integer temp = iter.next();
        iter.remove();
        System.out.println("delete:"+temp);
        stack.push(temp);
    }

    public void restore(List<Integer> list) {
        int restore=stack.pop();
        int index=restore;

        System.out.println("restore:"+restore);

        if(list.size()<=restore) {
            restore = list.size();
        }
        ListIterator<Integer> iter = list.listIterator(restore);

        iter.add(index);
    }
}
