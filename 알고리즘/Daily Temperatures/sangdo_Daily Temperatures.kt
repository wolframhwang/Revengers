class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        var st = Stack<Int>()
        var res = IntArray(temperatures.size)
        
        for(i in res.lastIndex downTo 0) {
            val v = temperatures[i]
            while(st.isNotEmpty() && temperatures[st.peek()] <= v) st.pop()
            res[i] = if(st.isEmpty()) {
                0
            }
            else {
                st.peek() - i
            }
            st.add(i)
        }
        return res
    }
}
