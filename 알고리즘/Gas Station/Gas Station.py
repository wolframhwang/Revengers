"""
으아 종이 쓰고 싶다. 안돼 여기다가 해.

패턴을 보자
start index refers to si
si 에서 시작해서 si - 1 까지 gas station 순회한다.
이때, 중간부터 끝까지 찍고서 다시 처음 인덱스 값으로 돌아와서 순회하는 법을 알아야 한다.
starting point tank = 0 + gas[si]
first move tank = tank - cost[si] + gas[si + 1]
~ last move tank = tank >= 0 : return si
if not : return -1
"""
# first comes up idea in 5 minutes
class Solution(object):
    def canCompleteCircuit(self, gas, cost):
        """
        :type gas: List[int]
        :type cost: List[int]
        :rtype: int
        """
        # initialize tank
        tank = 0

        # check all the candidates of starting point (index)
        for i in range(len(gas)): # 0, 1, 2, ,,, len(gas)-1
            tank += gas[i]
            for i in range(i, i-1):
                tank = tank - cost[i] + gas[i + 1]

            if tank >= 0:
                return i
            else:
                return -1


# my solution
"""
sum(gas) , sum(cost) 비교 먼저해서 불가능한 경우에는 걍 -1 return
"""
class Solution(object):
    def canCompleteCircuit(self, gas, cost):
        """
        :type gas: List[int]
        :type cost: List[int]
        :rtype: int
        """
        n = len(gas)

        total_tank, curr_tank = 0, 0
        starting_station = 0

        for i in range(n):
            total_tank += gas[i] - cost[i]
            curr_tank += gas[i] - cost[i]

            if curr_tank < 0:
                starting_station = i + 1
                curr_tank = 0

        if total_tank >= 0:
            return starting_station
        else:
            return -1