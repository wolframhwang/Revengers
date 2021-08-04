// 21/08/04 Solved By Wolfram Hwang
// 왔다갔다 이리로저리로

class Solution {
    func reverseKGroup(_ head: ListNode?, _ k: Int) -> ListNode? {
        var cnt = 0
        var node : ListNode? = head
        while node != nil, cnt < k {
            node = node?.next
            cnt += 1
        }
        if cnt < k {
            return head
        }
        node = head
        cnt = 0
        var prev : ListNode? = nil
        var next : ListNode?
        while cnt < k {
            next = node?.next
            node?.next = prev
            prev = node
            node = next
            cnt += 1
        }
        head?.next = reverseKGroup(node, k)
        return prev
    }
}
