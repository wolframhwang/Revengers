'''
O(n^2)
나가는 사람 기준으로 처리한다.
나가는 사람이 들어올때까지 들어오는 사람들을 회의실 셋 안에 넣어주고
나갈 때 회의실에 있는 사람들에게 +1씩 카운트, 나가는 사람들에게 회의실 안에 있던 사람들을 카운트 해주면 만남처리가 끝난다. 
'''
from collections import defaultdict

def solution(enter, leave):
    se = set()
    answer = [0]*len(enter)
    j = 0
    for lid in leave:
        while lid not in se:
            se.add(enter[j])
            j+=1
        se.remove(lid)
        for id in se:
            answer[id-1] += 1
        answer[lid-1] += len(se)
    return answer
