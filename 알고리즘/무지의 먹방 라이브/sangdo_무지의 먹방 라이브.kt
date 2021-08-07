/*
최대 먹은 개수를 x 로 했을 경우: for문을 통해 x보다 작으면 해당food_time의 길이, x보다 크면 x로 해서 더하면
한 접시에서 최대 x개씩 먹었을 경우의 시간초를 구할 수 있다.

ex)
|
||
||
|||

일 경우, 최대 높이를 2라고하면

|
||
==============
||
|||

2 + 2 + 1 = 5초일 경우가 지난다.

만약, 최대 높이를 3으로 하면,

|
=============
||
||
|||
4초, 3초, 1초
3+3+1 = 7초가 경과한 것을 알 수 있다.

그리고 k와 가장 가까우면서 작은 초가 지나는 x의 크기를 찾으면,
x+1을 높이로 잡았을 경우에는 음식을 모두 먹는 시간이 k보다 '커진다'는 것을 알 수 있다.
따라서, x+1높이 for문을 한 번만 돌아주면 k초 에 먹어야하는 음식이 나온다는 것을 의미한다.

따라서, k보다 커지게 하는 x를 찾은 뒤에(upper_bound)
이 값보다 하나 작은 값을 높이로 잡아서, 높이를 x만큼 잡았을 경우에 소요되는 음식 섭취 시간과 k의 차이를 구하고(remain)
food_times를 한 번 순회하면서 food_timesp[i]이 x보다 큰 개수가 remain이 되는 시점의 인덱스를 반환 해주면 된다. 
*/
class Solution {
    fun solution(food_times: IntArray, k: Long): Int {
        var answer = 0L
        var cnt = 0L
        food_times.forEach{cnt += it}
        if(cnt <= k) return -1
        var s = 0
        var e = 100_000_000
        while(s < e) {
            var h = 0L
            var m = (s+e)/2
            food_times.forEach{h += Math.min(m,it)}
            if(h <= k) {
                s = m+1
            }
            else e = m
        }
        s--
        var remain = k
        food_times.forEach{remain -= Math.min(s, it)}
        for(i in food_times.indices) {
            if(food_times[i] > s) {
                if(remain == 0L) {
                    return i+1
                }
                remain--
            }
        }
        
        return -1
    }
}
