// 21.09.17 solved by Wolfram Hwang
// Recursive, Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public var val: Int
 *     public var left: TreeNode?
 *     public var right: TreeNode?
 *     public init() { self.val = 0; self.left = nil; self.right = nil; }
 *     public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
 *     public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
 *         self.val = val
 *         self.left = left
 *         self.right = right
 *     }
 * }
 */
class Solution {
    func inver(_ node: TreeNode?){
        if node == nil {
            return
        }
        let L = node?.left
        let R = node?.right
        if node?.left != nil {
            inver(L)
        }
        if node?.right != nil {
            inver(R)
        }
        let temp = node?.left
        node?.left = node?.right
        node?.right = temp        
    }
    func invertTree(_ root: TreeNode?) -> TreeNode? {
        if root == nil {
            return root
        }
        
        inver(root)
        return root
    }
}
