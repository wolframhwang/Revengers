"""
[Topics]
String(index)
Array
Linked List
Heapq
python - raise 예외 발생시키기, del 함수

[Sketch]
행 삭제 관련 연산은 C,Z 에서는 이전 배열 연산이 필요
2개씩 따로 구한다.

효율성 통과를 위해 불필요한 이동 X, 순차검색 X
T.C 는 O(N)에 가까워야한다

Linked List나 heapq로 어떻게 하지
"""
#
def solution(n, k, cmd):
    # 처음 표 배열
    presence = [True for _ in range(n)]
    # n행 까지 순회하면서 up [-1, x] down [x, -1] 연산하고
    # 삭제한 행은 deleted에 저장한다
    up = [-1] + [x for x in range(n - 1)]
    down = [x for x in range(1, n)] + [-1]
    deleted = []

    # cmd 연산 앞 글자 자르기
    for c in cmd:
        init = c[0]

        # cmd 앞글자 연산 4개
        if init == 'U':
            # x 잘라내기
            temp = int(c.split()[1])
            # 현재 포인터 k 이동 갱신
            for _ in range(temp):
                k = up[k]
        elif init == 'D':
            # x 잘라내기
            temp = int(c.split()[1])
            # 현재 포인터 k 이동 갱신
            for _ in range(temp):
                k = down[k]
        elif init == 'C':
            if up[k] != -1:
                down[up[k]] = down[k]
            if down[k] != -1:
                up[down[k]] = up[k]
            # 삭제한 행은 presence 배열에서 False로 바꾼다
            presence[k] = False
            # 삭제행만 모아둔 deleted 배열에 k행 쌓기
            deleted.append(k)
            # 현재 포인터 k 이동 갱신, 마지막행일 경우와 아닌 경우 구분
            k = down[k] if down[k] != -1 else up[k]

        elif init == 'Z':
            # 삭제한 행 저장한 deleted 배열에서 가장 최근 것 pop
            d = deleted.pop()
            if up[d] != -1:
                down[up[d]] = d
            if down[d] != -1:
                up[down[d]] = d
            # 연산 중인 배열에서 삭제 취소한 행을 True로 표시
            presence[d] = True
        else:
            # 예외 처리하는 python 의 raise
            raise RuntimeError(init)

    # 모든 연산이 끝난 후에 presence 배열에서 삭제한 행과 그대로인 행 구분하여 OX 표시
    return ''.join(['O' if x else 'X' for x in presence])


