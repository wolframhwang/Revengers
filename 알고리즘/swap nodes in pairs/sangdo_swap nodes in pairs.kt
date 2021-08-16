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
    fun swapPairs(head: ListNode?): ListNode? {
        var dummy = ListNode(1)
        dummy.next = head
        var prev:ListNode? = dummy
        while(prev != null) {
            var now = prev.next
            if(now == null) return dummy.next
            var next = now.next
            if(next == null) return dummy.next
            var nextNode = next.next
            now.next = nextNode
            next.next = now
            prev.next = next
            prev = now
        }
        return dummy.next
    }
}
