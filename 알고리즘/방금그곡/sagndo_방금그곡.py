```
코틀린 없는 망문제
만약 정규식이나 stl 아니면 문자열 비교 할 때 kmp 구현해야 할듯.
```
import re

def solution(m, musicinfos):
    li = ['C','C#','D', 'D#', 'E','F','F#', 'G', 'G#', 'A', 'E#', 'A#', 'B'] 
    alpha = 'abcdefghijklmnopqrstuvwxyz'
    dic = {li[i]: alpha[i] for i in range(len(li))}
    
    def matching(m, size=-1):
        i = 0
        conv = []
        while len(conv) != size:
            if size == -1 and i == len(m): break
            idx = i%len(m)
            if idx + 2 <= len(m) and m[idx:idx+2] in dic:
                conv.append(dic[m[idx:idx+2]])
                i+=2
            else:
                conv.append(dic[m[idx:idx+1]])
                i+=1
        return ''.join(conv)
    
    mm = matching(m)
    regex = re.compile(mm)
    res = (-1, 10000, "(None)")
    
    for ii, m in enumerate(musicinfos):
        info = m.split(',')
        st = int(info[0][0:2])*60 + int(info[0][3:])
        ed = int(info[1][0:2])*60 + int(info[1][3:])
        size = ed-st
        s = matching(info[3], size)
        data = (size, -ii, info[2])
        if regex.search(s) and res < data: res = data
            
    return res[2]
