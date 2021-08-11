from collections import defaultdict

def solution(N, number):
    dic = defaultdict(lambda: 0x3f3f3f3f)
    now = []
    n = N
    for i in range(8):
        now.append(n)
        dic[n] = i+1
        dic[-n] = i+1
        n = n*10 + N
        
    dic[0] = 0
        
    while now:
        nxt = []
        keys = list(dic.keys())
        
        for i in now:
            for j in keys:
                c = dic[i] + dic[j]
                if(c > 8):
                    continue
                if dic[i+j] > c:
                    dic[i+j] = c
                    nxt.append(i+j)
                if dic[i-j] > c:
                    dic[i-j] = c
                    nxt.append(i-j)
                if dic[i*j] > c:
                    dic[i*j] = c
                    nxt.append(i*j)
                if j != 0 and dic[i//j] > c:
                    dic[i//j] = c
                    nxt.append(i//j)
        now = nxt
    
    return dic[number] if dic[number] != 0x3f3f3f3f else -1
