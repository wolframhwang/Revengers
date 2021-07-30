"""
[Topics]
stack - push pop

[Sketch]
인덱싱으로 원소 하나씩
stack에 넣었다가 하나씩 빼면서 같으면 pop
다르면 push

"""
def solution(s):
    stack = []
    # for i in s: -> 하면 wrong answer 
    for i in range(len(s)):
        # stack 이 비어있다면 push()
        if not stack:
            stack.append(s[i])
        else:
            # stack 마지막 값 == s[i] -> pop()
            if s[i] == stack[-1]:
                stack.pop()
            # 다르면 -> push()
            else:
                stack.append(s[i])
    # stack을 다 비워냈다면 return 1, 다 비우지 못했다면 return 0
    if stack:
        return 0
    else:
        return 1





