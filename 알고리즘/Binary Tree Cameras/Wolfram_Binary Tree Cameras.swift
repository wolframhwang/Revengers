
class Solution {
    let MX = Int(Int32.max / 2)
    func find(_ node : TreeNode? ) -> [Int]{
        var res = [0,0,0]
        res[0] = MX
        res[2] = MX
        if node == nil {
            return res
        }
        res[1] = MX
        let l = find(node?.left)
        let r = find(node?.right)
        let range = 0..<3
        for i in range {
            for j in range {
                res[0] = min(res[0], l[i] + r[j] + 1)
            }
        }
        res[1] = min(res[1], l[0] + r[1])
        res[1] = min(res[1], l[0] + r[0])
        res[1] = min(res[1], l[1] + r[0])
        res[2] = l[1] + r[1]
        return res
    }
    func minCameraCover(_ root: TreeNode?) -> Int {
        let result = find(root)
        return min(result[0], result[1])
    }
}
