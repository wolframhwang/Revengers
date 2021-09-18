class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        cnt = 0 
        now = 0
        for v in nums:
            if v == now: cnt+=1
            if v != now: cnt-=1
            if cnt < 0: 
                now = v
                cnt = 0
        return now
