class Solution {
    fun numJewelsInStones(jewels: String, stones: String): Int {
        var cnt = IntArray(256)
        jewels.forEach{cnt[it.toInt()]= 1}
        var answer = 0
        stones.forEach{answer += cnt[it.toInt()]}
        return answer;
    }
}
