import java.util.*
/*
트리를 이해하는 문제
주어진 정보로 이진 트리를 만들 수 있어야 한다.

트리를 만들때 depth마다 O(N)의 시간복잡도를 가지는데
깊이가 1000 이기에 노드가 1만개면
깊이 마다 O(N)의 시간복잡도를 했을 경우 10^7인데, 합리적인 시간복잡도라고 봅니다.

트리만드는 법: 
1. x축에 대해서 정렬한 배열을 가지고 있는다.
2. 이 정렬한 배열 x를 트리의 후보군들로 해서 makeTree 함수를 돌린다.
3. 트리의 후보군들 중에서 가장 큰 y값을 가진 녀석을 root로 지정한다.
4. root로 지정된 x의 값을 이진탐색하여 찾은 다음에, 이 값보다 작은 x를 가진 노드들은 left의 후보, 큰 노드들은 right의 후보로 해서
	다시 makeTree를 호출한다. 이후에 나온 root들을 left, right에 붙이면 된다.
즉, slice를 하거나 가장 큰 y값을 찾아야 하기 때문에, depth마다 총 O(N)의 시간복잡도를 가진다.
(시간복잡도 10^7)


이후에 만든 트리로 preorder, postorder를 수행하면 됩니다.
*/

class Solution {
    lateinit var X: IntArray
    class Tree(val v:Int, var left: Tree?=null, var right: Tree?=null)
    
    fun makeTree(nodeinfo: Array<IntArray>, X: List<Int>): Tree? {
        if(X.isEmpty()) return null
        var x = -1
        var maxDepth = -1
        var id = -1
        for(j in X.indices) {
            var i = X[j] 
            if(nodeinfo[i][1] > maxDepth) {
                maxDepth = nodeinfo[i][1]
                id = i
                x = nodeinfo[i][0]
            }
        }
        var res = Tree(id+1)
        var s = 0
        var e = X.size
        while(s < e) {
            var m = (s+e)/2
            if (nodeinfo[X[m]][0] == x) {
                s = m
                break
            }
            if (nodeinfo[X[m]][0] < x) s = m+1
            else e = m
        }
        res.left = makeTree(nodeinfo, X.slice(IntRange(0, s-1)))
        res.right = makeTree(nodeinfo, X.slice(IntRange(s+1,X.lastIndex)))
        
        return res
    }
    var cur = 0
    fun preOrder(node: Tree?, arr: IntArray) {
        if(node == null) return
        arr[cur++] = node.v
        preOrder(node.left, arr)
        preOrder(node.right, arr)
    }
    
    fun postOrder(node: Tree?, arr: IntArray) {
        if(node == null) return
        postOrder(node.left, arr)
        postOrder(node.right, arr)
        arr[cur++] = node.v
    }
    
    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        var answer = Array<IntArray>(2){IntArray(nodeinfo.size)}
        X = IntArray(nodeinfo.size){i-> 
            i
        }
        var x = X.sortedWith(Comparator{i,j -> nodeinfo[i][0] - nodeinfo[j][0]})
        x.forEach{println(it)}
        var root = makeTree(nodeinfo, x)
        
        cur = 0
        preOrder(root, answer[0])
        cur = 0
        postOrder(root, answer[1])
        
        return answer
    }
}
