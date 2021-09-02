'''
트라이를 이용해서 어떤 노드가 문자열의 끝인지와 어떤 노드까지 가는 경로가 유일한지 알 수 이씁니다.
BFS나 dfs를 이용하면 깊이를 저장하면서 문자열이 끝일때와 남은 노드까지의 경로가 딱 1개만 남았을때
깊이를 저장하면 정답을 구할수 이씁니다!
O(len*)
'''
from collections import deque
class TRIE:
    def __init__(self):
        self.child = [None for _ in range(26)]
        self.num = 0
        self.c = 'a'
        self.isEnd = False
        
def solution(words):
    answer = 0
    root = TRIE()
    root.c = 'R'
    for string in words:
        now = root
        for c in string:
            conv = ord(c) - ord('a')
            now.num+=1
            
            if(now.child[conv] == None) :
                now.child[conv] = TRIE()
            now = now.child[conv]
            now.c = c
        now.num+=1
        now.isEnd = True
    Q = deque()
    for t in root.child:
        if t:
            Q.append(t)
    
    cnt = 1
    while(Q):
       	remain = len(Q)
        while remain != 0:
            remain-=1
            now = Q.popleft()
            if(now.num == 1): 
                answer += cnt
                continue
            if(now.isEnd): answer += cnt
            for t in now.child:
                if t:
                    Q.append(t)
        cnt+=1
    return answer
