class Solution:
    def find132pattern(self, nums: List[int]) -> bool:
        mmax = 0x3f3f3f3f
        mmin = 0x3f3f3f3f
        st = []
        for i in nums:
            if i <= mmin:
                st.append((mmin, mmax))
                mmin = i
                mmax = i
            elif mmax <= i:
                while st and st[-1][1] <= i: st.pop()
                if st and st[-1][0] < i: return True
                mmax = i
            else: return True
            
                    
                
        return False
