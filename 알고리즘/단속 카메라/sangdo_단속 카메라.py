'''
최대한 겹치는 곳의 끝점에 놓는다.
만약 이 끝점을 벗어나면 새로 놓는다.
'''
def solution(routes):
    answer = 0
    routes.sort()
    ed = -0x3f3f3f3f
    
    for f,t in routes:
        if ed < f:
            answer+=1
            ed = t
        ed = min(ed, t)
         
    return answer
