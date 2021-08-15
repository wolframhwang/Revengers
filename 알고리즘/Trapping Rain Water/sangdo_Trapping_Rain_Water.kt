//O(N)
class Solution {
    fun trap(height: IntArray): Int {
        var h = IntArray(height.size){0x3f3f3f3f}
        h[0] = height[0]
        for(i in 1 .. h.lastIndex) {
            h[i] = Math.max(h[i-1], height[i])
        }
        h[h.lastIndex] = Math.min(height.last(), h.last())
        var res = 0
        var hh = h.last()
        for(i in h.lastIndex-1 downTo 0) {
            hh = Math.max(height[i], hh)  
            res += Math.min(hh, h[i]) - height[i]
        }
        return res
    }
}
