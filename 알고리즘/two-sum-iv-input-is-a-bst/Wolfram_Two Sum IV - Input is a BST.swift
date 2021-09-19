class Solution {
    var isOK = false
    func find(_ val: Int, _ node : TreeNode?)-> Bool {
        let nodeVal = node?.val ?? 0
        var finding = false

        if nodeVal == val {
            return true
        }
        if nodeVal < val {
            if node?.right != nil {
                finding = find(val, node?.right)
                return finding
            }
        }else{
            if node?.left != nil {
                finding = find(val, node?.left)
                return finding
            }
        }
        return finding
    }
    func depth(_ node: TreeNode?, _ k: Int, _ root: TreeNode?) {
        let val = node?.val ?? 0
        let comp = k - val == val ? 987654321 : k - val
        let ok = find(comp, root)
        if isOK == true { return }
        isOK = ok
        if node?.left != nil, isOK == false {
            depth(node?.left, k, root)
        }
        if node?.right != nil, isOK == false {
            depth(node?.right, k, root)
        }
    }
    
    func findTarget(_ root: TreeNode?, _ k: Int) -> Bool {
        depth(root, k, root)
        if !isOK { return false}
        return true
    }
}
