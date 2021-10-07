
class Solution {
    func sortedList(from head: ListNode?, to tail:ListNode?) -> TreeNode? {
        if tail === head { return nil}
        var f = head
        var s = head
        
        while !(f === tail), !(f?.next === tail){
            f = f?.next?.next
            s = s?.next
        }
        
        let node = TreeNode(s!.val)
        node.left = sortedList(from: head, to: s)
        node.right = sortedList(from: s?.next, to: tail)
        return node
    }
    
    func sortedListToBST(_ head: ListNode?) -> TreeNode? {
        return sortedList(from: head, to: nil)
        
        
        
    }
}
