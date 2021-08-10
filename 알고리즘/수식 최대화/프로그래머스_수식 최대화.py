"""
[Topics]
python isdigit

[Sketch]
- calculator 함수를 만든다
- expression이 string으로 주어지는데 어떻게 잘라야 좋은가
- 연산자 기점으로 앞 숫자,뒤 숫자,연산자로 나누는 array로 만든다
- 케이스가 6개뿐이니까 다 해봐도 되겠지 ?

1. 연산자 6개 조합 : permutation
- operation 함수 만든다
-

2. calculator 함수

"""
from itertools import permutations


def operation(num1, num2, op):
    if op == '+':
        return str(int(num1) + int(num2))
    if op == '-':
        return str(int(num1) - int(num2))
    if op == '*':
        return str(int(num1) * int(num2))


def calculate(exp, op):
    array = []
    tmp = ""
    for i in exp:
        if i.isdigit() == True:
            tmp += i
        else:
            array.append(tmp)
            array.append(i)
            tmp = ""
    array.append(tmp)

    for o in op:
        stack = []
        while len(array) != 0:
            tmp = array.pop(0)
            if tmp == o:
                stack.append(operation(stack.pop(), array.pop(0), o))
            else:
                stack.append(tmp)
        array = stack

    return abs(int(array[0]))


def solution(expression):
    op = ['+', '-', '*']
    op = list(permutations(op, 3))
    result = []
    for i in op:
        result.append(calculate(expression, i))
    return max(result)