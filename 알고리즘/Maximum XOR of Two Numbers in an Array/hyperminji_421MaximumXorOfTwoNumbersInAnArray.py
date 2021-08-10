class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        answer = 0
        for i in range(31,-1,-1):
            prefix = set([ num >> i for num in nums])
            answer <<=1
            candi = answer + 1
            for pre in prefix:
                if candi ^ pre in prefix:
                    answer = candi
                    
        return answer
                
