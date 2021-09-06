/*
난이도 사기임 이건 하드 아니지

지난번 트리 파라메트릭 보다는 쉬움
최대 합이 X가 되도록 그룹을 나누고,
X보다 커지면 그룹을 추가합시다.
그리고 나눠진 그룹이 m보다 작으면 sum값의 합을 줄여서 그룹을 늘려보고,
m보다 크다면 sum값의 합을 늘려 그룹을 줄여봅시다.

이렇게 이분탐색을 돌려보면 최대값이 가장 작은 부분을 찾을 수 있을겁니다.
이분탐색 한번에 O(N)번 수행되고, 0x3f3f3f3f의 로그번 수해오디므로 

시간복잡도는 O(n*log(0x3f3f3f3f))
*/
class Solution {
    fun splitArray(nums: IntArray, m: Int): Int {
        var s = 0
        var e = 0x3f3f3f3f
        while(s < e){
            var mid = (s+e)/2
            var sum = 0
            var k = 0
            nums.forEach{
                if(it > mid) k = m+1
                if(sum + it > mid) {
                    ++k
                    sum = it
                } else sum+=it
            }
            ++k
            if(k <= m) e = mid
            else s = mid+1
        }
        return s
    }
}
