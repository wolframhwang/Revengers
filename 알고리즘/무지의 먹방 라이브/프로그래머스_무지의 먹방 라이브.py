"""

"""
import heapq
def solution(food_times, k):
    answer = 0
    time = 0
    pq = []
    answer_rs = []
    for i in range(len(food_times)):
        heapq.heappush(pq, [food_times[i], i + 1])

    pre_food = 0
    flag = True
    while flag:
        if not pq:
            return -1
        length = len(pq)
        time += (pq[0][0] - pre_food) * length
        if time > k:
            time -= (pq[0][0] - pre_food) * length
            while pq:
                answer_rs.append(heapq.heappop(pq)[1])
            answer_rs.sort()
            answer = answer_rs[(k - time) % length]
            flag = False
        else:
            pre_food = heapq.heappop(pq)[0]
    return answer