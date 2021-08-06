/*
3600*100시간을 하면 360000이다. 즉 시간 공간은 최대 36만 밖에 안된다는 점을 이용한다. (timeline)

1. 영상 시청 시간의 시작점을 +1, 끝점을 -1로 해서 timeline에 저장한다.
2. for문으로 time[i] += time[i-1]을 하면, 해당 시간에 시청중인 시청자 수를 모두 구할 수 있다.

ex)
000000-----00000
000-----00000000 가 있으면
000+00+0-00-0000 가 되고(+ = +1, - = -1)
이를 2번 방법을 이용하면
0001112211100000 이된다. 해당 시간에 시청하는 시청자가 정확하게 나온다!!!! (해당 문제는 [) 폐구간, 개구간으로 되어있음을 인지하자!)

3. 이제 여기서 또다시 for문으로 time[i] += time[i-1] 을 하면, 0 ~ i시간때까지 시청하는 시간의 prefix sum을 구할 수 있다.

ex)위의 예에서 3의 방법을 사용하면
0001235789(10)000000 이 된다. 정확하지 않은가!?

4. 이제, x에서 시작했을때 광고를 시청한 시간을 구하기 위해서는
timeline[x+adv-1] - timeline[x-1]을 이용하면 된다! 끝이 개구간이기 때문이다!

for문을 3개 쓰기 때문에 시간복잡도는 O(N)이다!

이상.
*/

class Solution {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        var getTime = {str: String -> Int
            var playTime = str.split(":")
            3600*playTime[0].toInt() + 60*playTime[1].toInt() + playTime[2].toInt()
        }
        
        var stringTime = {t: Int -> String
            val h = t/3600
            val m = t%3600/60
            var s = t%60
            (h/10).toString() +
            (h%10).toString() + ":" + 
            (m/10).toString() +
            (m%10).toString() + ":" +
            (s/10).toString() +
            (s%10).toString()
        }
        var answer = ""
        var ed = getTime(play_time)+1
        var adv = getTime(adv_time)
        var timeline = LongArray(ed)
        
        for(l in logs) {
            var time = l.split("-")
            timeline[getTime(time[0])]++
            timeline[getTime(time[1])]--
        }
        
        for(i in 1 until ed) {
            timeline[i] += timeline[i-1]
        }
        for(i in 1 until ed) {
            timeline[i] += timeline[i-1]
        }
        
        var res = 0L
        for(i in 0 .. ed - adv) {
            var before = if(i == 0) 0 else timeline[i-1]
            var watch = timeline[i + adv-1] - before
            if(watch > res) {
                res = watch
                answer = stringTime(i)
            }
        }
        
        return answer
    }
}
