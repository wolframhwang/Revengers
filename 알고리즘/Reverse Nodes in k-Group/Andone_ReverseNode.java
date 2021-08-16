class Solution {    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cursor = head;
        int cnt = 0;
        
        List<Deque> stackList = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();
        
        if(cursor.next == null || k == 1)
            return head;
        
        while(cursor != null) {
            //뒤집은걸 stack에 넣기
            while(cursor != null && cnt<k) {
                dq.push(cursor.val);
                cursor = cursor.next;
                cnt++;
            }

            //stack을 List에 넣기
            stackList.add(dq);
            dq = new ArrayDeque<>();
            cnt = 0;
        }        
        
        ListNode newHead = new ListNode();
        cursor = newHead;
        
        
        for(int i=0;i<stackList.size();i++) {
            Deque<Integer> item = stackList.get(i);
            
            if(item.size() < k) {
                while(!item.isEmpty()) {
                    cursor.val = item.pollLast();
                    if(!item.isEmpty()) {
                        cursor.next = new ListNode();
                        cursor = cursor.next;
                    }
                }
            }
            
            while(!item.isEmpty()) {
                cursor.val = item.pop();
                if(i == stackList.size()-1 && item.isEmpty()) {
                    break;
                }
                cursor.next = new ListNode();
                cursor = cursor.next;
            }
        }
        
        return newHead;
    }
}