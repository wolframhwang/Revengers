"""
[Topics]
array
stack
queue


[Sketch]
check 함수에 올바른 괄호, stack으로 구현

deque 에 input 넣고, 거꾸로 민다

"""
#
from collections import deque


def solution(ip):
    def check(s):
        stack = []
        for e in s:
            if e == '(' or e == '{' or e == '[':
                stack.append(e)
            else:
                if not stack:
                    return False
                x = stack.pop()
                if e == ')' and x != '(':
                    return False
                elif e == ')' and x != '(':
                    return False
                elif e == '}' and x != '{':
                    return False
        return len(stack) == 0

    q_lst = deque(ip)
    answer = 0

    for x in range(len(q_lst)):
        q_lst.rotate(-1)
        if check(q_lst):
            answer += 1
    return answer


