"""
python bisect
bisect.bisect(a, x, lo=0, hi=len(a))
bisect()는 a라는 오름차순으로 정렬된 시퀀스에 x값이 들어갈 위치를 리턴
bisect_left(literable, value) : 왼쪽 인덱스를 구하기

hashmap


"""
#
class Solution(object):
    def avoidFlood(self, rains):
        """
        :type rains: List[int]
        :rtype: List[int]
        """
        q = []
        ans = []
        hashmap = {}
        for i in range(len(rains)):
            if rains[i] == 0:
                q.append(i)
                ans.append(1)
            else:
                if rains[i] in hashmap:
                    if len(q) == 0:
                        ans = []
                        break
                    else:
                        index = hashmap[rains[i]]
                        pos = bisect.bisect_right(q, index)
                        if pos < len(q):
                            ans[q[pos]] = rains[i]
                            q.pop(pos)
                        else:
                            ans = []
                            break
                hashmap[rains[i]] = i
                ans.append(-1)

        return ans