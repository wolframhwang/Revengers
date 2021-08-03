// 21/08/04 Solved by Wolfram Hwang
// 간단한 규칙찾기 문제입니다.
// 현재 배열 기준 가장 가운데부터 채워나가고
// 다음 함수로 현재 배열 시작 - 가운데, 현재 배열 가운데, 끝
// 해서 계속해서 채워나가면 되는 간단한 문제죠

class Solution {
    var visit : [Bool] = []
    var arr : [Int] = []
    func sort(_ s: Int, _ e : Int)->TreeNode? {
        let m = (s + e) / 2
        var tree: TreeNode? = TreeNode()
        tree?.val = arr[m]
        visit[m] = true
        let lm = (s + m) / 2
        if visit[lm] == false {
            tree?.left = sort(s, m)
        }else{
            tree?.left = nil
        }
        let rm = (m + e + 1) / 2
        if visit[rm] == false {
            tree?.right = sort(m + 1, e)
        }
        else{
            tree?.right = nil
        }
        //print(tree)
        return tree
    }
    func sortedArrayToBST(_ nums: [Int]) -> TreeNode? {
        visit = [Bool](repeating: false, count: nums.count)
        arr = nums
        var tr : TreeNode? = sort(0, nums.count - 1)
        return tr
    }
}
