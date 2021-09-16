'''
단순한 완전 탐색과 가지치기를 이용한 방식.
permutation을 짜는  백준의 n과 m을 풀어보길 바람
'''

class Solution:
    def permutation(self, i, l, nums):
        if(i == l) :
            self.res+=1
            return
        for idx in range(i, l):
            nums[i], nums[idx] = nums[idx], nums[i]
            if nums[i] % (i+1) == 0 or (i+1)% nums[i] == 0:
                self.permutation(i+1, l, nums)
            nums[i], nums[idx] = nums[idx], nums[i]
            
    def countArrangement(self, n: int) -> int:
        self.res = 0
        nums = [i+1 for i in range(0, n)]
        self.permutation(0, n, nums)
        return self.res
        
