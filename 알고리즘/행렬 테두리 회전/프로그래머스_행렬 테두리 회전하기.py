"""
[Topics]
array
matrix


[Sketch]
중앙만 빼고 테두리만 회전
1-1. 중앙이 있는 케이스
1-2. 중앙이 없는 케이스 구분

상 우 하 좌 , 모서리, 다 나눠줘야 하나
2-1. 상 - 오른쪽 1칸 , 모서리일 경우 아래로 1칸
2-2. 우 - 아래로 1칸 , 모서리일 경우 왼쪽으로 1칸
2-3. 하 - 왼쪽 1칸 , 모서리일 경우 위쪽으로 1칸
2-4. 좌 - 위로 1칸 , 모서리일 경우 오른쪽으로 1칸

마지막에 테두리 (회전한 원소) 만 모아서 min
아니면 2의 과정에서 계속 min 갱신
(min은 T.O 가 몇이지)
"""
# Wrong Answer
def solution(rows, columns, queries):
    answer = []

    # rows, columns 담은 배열 arr
    arr = []
    num = 1
    for i in range(rows):
        cur = []
        for j in range(columns):
            cur.append(num)
            num += 1
        arr.append(cur)

    # queries 에 따라 회전
    for x1, y1, x2, y2 in queries:
        min_num = 999999999999999
        # out of range
        temp = arr[x1-1][y2-1]

        # top 부분 회전
        min_num = min(min(arr[x1-1][y1-1:y2]), min_num)
        arr[x1-1][y1:y2] = arr[x1-1][y1-1:y2-1]

        # right 부분 회전
        for x in range(x2-1, x1, -1):
            min_num = min(arr[x-1][y2-1], min_num)
            arr[x][y2-1] = arr[x-1][y2-1]

        # bottom 부분 회전
        min_num = min(min(arr[x2-1][y1-1:y2]), min_num)
        arr[x2-1][y1-1:y2-1] = arr[x2-1][y1:y2]

        # left 부분 회전
        for x in range(x1, x2):
            min_num = min(arr[x][y1-1], min_num)
            arr[x-1][y1-1] = arr[x][y1-1]

        arr[x1][y2-1] = temp

        answer.append(min(temp, min_num))

    return answer

# Right Answer
def solution(rows, columns, queries):
    answer = []
    arr = [[] for _ in range(rows)]

    for i in range(1, rows + 1):
        for j in range(1, columns + 1):
            arr[i - 1].append((i - 1) * columns + j)

    for x1, y1, x2, y2 in queries:
        temp = arr[x1 - 1][y2 - 1]
        min_value = 10001

        # top
        min_value = min(min(arr[x1 - 1][y1 - 1:y2 - 1]), min_value)
        arr[x1 - 1][y1:y2] = arr[x1 - 1][y1 - 1:y2 - 1]

        # left
        for i in range(x1, x2):
            min_value = min(arr[i][y1 - 1], min_value)
            arr[i - 1][y1 - 1] = arr[i][y1 - 1]

        # bottom
        min_value = min(min(arr[x2 - 1][y1:y2]), min_value)
        arr[x2 - 1][y1 - 1:y2 - 1] = arr[x2 - 1][y1:y2]

        # 동쪽 테두리
        for i in range(x2 - 2, x1 - 2, -1):
            min_value = min(arr[i][y2 - 1], min_value)
            arr[i + 1][y2 - 1] = arr[i][y2 - 1]

        arr[x1][y2 - 1] = temp
        min_value = min(min_value, temp)

        answer.append(min_value)

    return answer

# References. 2차 행렬 회전시키기, 달팽이 행렬 template

















