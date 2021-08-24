'''
위상정렬쓰면됩니다.
'''

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        li = [0]*numCourses
        adj = [[] for _ in range(numCourses)]
        for a,b in prerequisites:
            li[a] += 1
            adj[b].append(a)
            
        Q = deque()
        for i,n in enumerate(li):
            if (n == 0):
                Q.append(i)
        answer = []
        while Q:
            now = Q.popleft()
            answer.append(now)
            for nxt in adj[now]:
                li[nxt]-=1
                if li[nxt] == 0:
                    Q.append(nxt)
        return answer if len(answer) == numCourses else []
        
