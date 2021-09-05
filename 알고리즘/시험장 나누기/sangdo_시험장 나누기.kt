/*
이 문제는 약간 이해하기 힘듭니다.
문제를 알기 위해선 '그룹을 나눌 때 그룹이 가질 수 있는 최대 금액'을 트리에서 어떻게 나누냐가 핵심인데,
텍스트로 설명하긴 어렵지만 단순히 말단 노드부터 그룹이 가지는 최대 금액을 M이라고 했을때, 
자신의 왼쪽 그룹과 오른쪽 그룹이 M의 가치를 가지는 최소의 그룹으로 잘 나뉘어있을때,
단순히 M의 값을 '넘어 버리면' 간선을 '나눠'버리는게 핵심입니다.

대충 설명하면
왼쪽 그림에서 10과 7이 단절되어있는데, 
이 단절된 선을 복구하기 위해선 어짜피 자식노드에서도 자식 노드들의 간선을 '최소' 하나 이상 제거해야하기 때문에 
(트리 문제에서 뭔가 간선을 그리디로 이용하고자 할 때 자주 나오는 개념, 매우 중요****)
단순히 그룹을 가장 적게 유지하면서도 부모 노드가 포함되는 그룹에서의 최대 값이 최소가 되는 경우에만 초점을 맞추면 됩니다.
(즉, 자식 노드와 부모 노드의 값 핪이 M보다 클 경우에만 잘라주면 됩니다.)

이를 이용하면 값 합의 최솟값을 M으로 정해놓고, 이진 트리를 돌아서 그룹이 k개가 나오는지 볼 수 있는데,
이를 통해 정답을 구하면 됩니다.

참고로 이 문제에서 개인 노드의 값을 M보다 넘는 경우도 꼭 처리해주셔야합니다.
안그러면 저처럼 삽질의 삽질의 삽질의 삽질을 하게됩니다.
*/
class Solution {
    lateinit var n: IntArray
    lateinit var l: Array<IntArray>
    fun makeGroup(node: Int, max: Int): Pair<Int,Int> {
        if(node == -1) return 1 to 0
        if(n[node] > max) return 0x3f3f3f3f to -1
        var left = makeGroup(l[node][0], max)
        var right = makeGroup(l[node][1], max)
        if(left.second == -1 || right.second == -1) return 0x3f3f3f3f to -1
        return if(n[node] + left.second + right.second > max) {
            var small =  if(left.second < right.second) left else right
            if(small.second + n[node] <= max) {
                (left.first + right.first) to (small.second + n[node])
            }
            else{
                (left.first + right.first+1) to (n[node])
            }
        }
        else{
             (left.first + right.first - 1) to (left.second + right.second + n[node])
        }
    }
    fun solution(k: Int, num: IntArray, links: Array<IntArray>): Int {
        n = num
        l = links
        var parents = BooleanArray(num.size)
        links.forEach{
            if(it[0] != -1) parents[it[0]] = true
            if(it[1] != -1) parents[it[1]] = true
        }
        var p = -1
        parents.forEachIndexed{i,v-> if(v == false) p = i}
        var s = 0
        var e = 0x3f3f3f3f
        while(s < e) {
            var m = (s+e)/2
            var res = makeGroup(p, m)
            if(res.first <= k) {
                e = m
            }
            else s= m +1
        }
        return s
    }
}
