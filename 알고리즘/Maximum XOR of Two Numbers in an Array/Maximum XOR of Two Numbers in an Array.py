"""

"""
class Solution:
    def findMaximumXOR(self, nums: List[int]) -> int:
        manipul, output = 0, 0
        for i in range(32, -1, -1):
            manipul = manipul | 1<<i
            found = set([n & manipul for n in nums])

            temp = output | 1 << i
            for f in found:
                if f ^ temp in found:
                    output = temp

        return output