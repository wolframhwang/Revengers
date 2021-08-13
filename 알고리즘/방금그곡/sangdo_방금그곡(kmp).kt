def makeKMP(s):
    kmp = [-1] * len(s)
    j = -1
    for i in range(len(s)):
        kmp[i] = j
        while j != -1 and s[j] != s[i]:
            j = kmp[j]
        j+=1
    return kmp

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
    kmp = makeKMP(mm)
    res = (-1, 10000, "(None)")
    
    for ii, m in enumerate(musicinfos):
        info = m.split(',')
        st = int(info[0][0:2])*60 + int(info[0][3:])
        ed = int(info[1][0:2])*60 + int(info[1][3:])
        size = ed-st
        s = matching(info[3], size)
        j = 0
        for i in range(size):
            while j != -1 and s[i] != mm[j]:
                j = kmp[j]
            j += 1
            if j == len(mm):
                data = (size, -ii, info[2])
                if data > res: res = data
                break
    
    return res[2]
