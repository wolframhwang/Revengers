/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        var cnt = k
        var cursor = head
        var dummy = ListNode(-1)
        var prev = dummy
        dummy.next = head
        
        while(cursor != null) {
            var now = cursor
            cnt = k
            while(cursor != null && cnt != 0) {
                cursor = cursor!!.next
                cnt--
            }
            var nextCursor = cursor
            if(cnt == 0) {
                var nextPrev = now
                for(i in 1 .. k) {
                    var next = now!!.next
                    now.next = cursor
                    cursor = now
                    prev.next = now
                    now = next
                }
                prev = nextPrev
            }
            cursor = nextCursor
        }
        return dummy.next
    }
}
