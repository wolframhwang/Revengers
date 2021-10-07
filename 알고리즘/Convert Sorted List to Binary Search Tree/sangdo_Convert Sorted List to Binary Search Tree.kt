class Solution {
    fun sortedListToBST(head: ListNode?): TreeNode? {
        if(head == null) return null
        var prev: ListNode? = null
        var cur = head
        var cnt = 0
        while(cur != null) {
            cnt++
            cur = cur.next
        }
        var remain = 0
        cur = head
        prev = null
        while(remain < cnt / 2) {
            prev = cur
            cur = cur!!.next
            remain++
        }
        var node = TreeNode(cur!!.`val`)
        prev?.let{it.next = null}
        node.left = sortedListToBST(if(head == cur) null else  head)
        node.right = sortedListToBST(cur!!.next)
        return node
    }
}
