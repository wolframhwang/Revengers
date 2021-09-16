'''
permutation이므로 i번 노드를 향하는 노드는 하나씩이다.
따라서, visit 체크만 해주고, 0~n-1 인덱스를 보며, 아직 visit이 안됐으면 visit이 나올때까지 돌아보면 된다.
무한루프는 일어나지 않는다.
O(N)
'''

class Solution:
    def cycle(self, nums, chk, i, d):
        if chk[i] == 1: return d
        chk[i] = 1
        return self.cycle(nums, chk, nums[i], d+1)
        
    def arrayNesting(self, nums: List[int]) -> int:
        chk = [0]*len(nums)
        answer = 0
        for i in range(0, len(nums)):
            if chk[i] == 0:
                answer = max(answer, self.cycle(nums, chk, i,0))
        return answer
