```
코틀린 없는 망문제
만약 파이썬 아니면 찾을때 kmp 구현해야 할듯.
```

import re
class INFO:
    def __init__(self, size, st, content, title):
       	self.size = size
        self.st = st
        self.content = content
        self.title = title
    def __lt__(self, other):
        if self.size != other.size: return self.size > other.size
        return self.st < other.st
    
def solution(m, musicinfos):
    answer = ''
    li = ['C','C#','D', 'D#', 'E','F','F#', 'G', 'G#', 'A', 'E#', 'A#', 'B'] 
    alpha = 'abcdefghijklmnopqrstuvwxyz'
    dic = {li[i]: alpha[i] for i in range(len(li))}
    i = 0
    conv = []
    
    while i != len(m) :
        if i + 2 <= len(m) and m[i:i+2] in dic:
            conv.append(dic[m[i:i+2]])
            i+=2
        else:
            conv.append(dic[m[i:i+1]])
            i+=1
    regex = re.compile(''.join(conv))
    
    res = []
    
    for ii, m in enumerate(musicinfos):
        info = m.split(',')
        st = int(info[0][0:2])*60 + int(info[0][3:])
        ed = int(info[1][0:2])*60 + int(info[1][3:])
        size = ed-st
        li = []
        i = 0
        
        while len(li) != size:
            idx = i%len(info[3])
            if idx + 2 <= len(info[3]) and info[3][idx:idx+2] in dic:
                li.append(dic[info[3][idx:idx+2]])
                i += 2
            else:
                li.append(dic[info[3][idx:idx+1]])
                i+=1
        s = ''.join(li)
        if regex.search(s):
            res.append(INFO(size, ii, s, info[2]))
    
    if not res: return "(None)"
    res.sort()
    return res[0].title
