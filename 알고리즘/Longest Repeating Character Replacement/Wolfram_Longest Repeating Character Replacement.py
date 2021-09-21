# 21.09.21 Solved By Wolfram Hwang
# using Two Pointer(Sliding Window)

class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        alp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        dict = {}
        for a in alp:
            dict[a] = 0
        start,end = 0,0
        answer = 0
        while start <= end:
            if end == len(s) :
                break
            MAXV, MAXC, TOTAL = 0,"a",0
            for key, value in dict.items():
                TOTAL += value
                if value > MAXV:
                    MAXV = value
                    MAXC = key
            TOTAL -= MAXV
            if MAXC == "a" :
                dict[s[end]] += 1
                end += 1
            else:
                if s[end] == MAXC:
                    dict[s[end]] += 1
                    end += 1
                else:
                    if TOTAL == k :
                        dict[s[start]] -= 1
                        start += 1
                    else:
                        dict[s[end]] += 1
                        end += 1

            if answer < (end - start):
                answer = end - start

        return answer


