"""
[Topics]
deque
enumerate

[Sketch]
우선순위 점수랑 인덱스번호를 함께 저장

"""
#
def solution(priorities, location):
    from collections import deque


    answer = 0

    d = deque([(v,i) for i,v in enumerate(priorities)])

    while len(d):
        item = d.popleft()
        if d and max(d)[0] > item[0]:
            print(max(d)[0])
            d.append(item)
        else:
            answer += 1
            if item[1] == location:
                break
    return answer

#


