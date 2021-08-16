// 21/08/16 Solved by Wolfram Hwang
// 간단한 링크드 리스트 문제입니다.
// 변수 두개만 가지고 하면 충분히 값의 변위를 바꿔줄수 있습니다.

class Solution {
    func swapPairs(_ head: ListNode?) -> ListNode? {
        if head == nil {
            return nil
        }
        if head?.next == nil {
            return head
        }
        var node: ListNode? = head
        var cnt = 0
        var ret: ListNode? = nil
        var before: ListNode? = nil
        while node != nil {
            if node?.next == nil {
                break
            }
            let dummynode : ListNode? = node?.next?.next
            node?.next?.next = node
            if cnt == 0 {
                ret = node?.next
            }else{
                before?.next = node?.next
            }
            node?.next = dummynode
            before = node
            node = node?.next
            
            cnt += 1
        }
        return ret
    }
}
