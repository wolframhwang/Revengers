/*
배열을 가운데를 기준으로 2개로 나누고, 가운데 노드를 값으로 하는 노드로 만들고
가운데를 기준으로 왼쪽 배열에서 똑같이 왼쪽 트리를 만들고, 배열에서 오른쪽 트리를 만들어서 반환하면
노드의 차이가 최대 1개씩 나기 때문에 높이 차이가 최대 1 나는 이진트리를 만들 수 있다.
*/

class Solution {
    fun sortedArrayToBST(nums: IntArray, s:Int = 0, e: Int = nums.size): TreeNode? {
        if(s >= e) return null
        var m = (s+e)/2
        var ret = TreeNode(nums[m])
        ret.left = sortedArrayToBST(nums, s, m)
        ret.right = sortedArrayToBST(nums,m+1,e)
        return ret
    }
}
