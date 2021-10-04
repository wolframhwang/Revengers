class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        var inOrder = IntArray(numCourses)
        var Q:Queue<Int> = LinkedList()
        var adj = Array<ArrayList<Int>>(numCourses){ArrayList<Int>()}
        prerequisites.forEach{
            var a = it[0]
            var b = it[1]
            adj[b].add(a)
            inOrder[a]++
        }
        var res = 0
        inOrder.forEachIndexed{i,v->if(v == 0) {
                Q.add(i)
            }
        }
        
        while(Q.isNotEmpty()) {
            var now = Q.poll()
            res++
            for(nxt in adj[now]) {
                inOrder[nxt]--
                if(inOrder[nxt] == 0) Q.add(nxt)
            }
        }
        return res == numCourses
    }
}
