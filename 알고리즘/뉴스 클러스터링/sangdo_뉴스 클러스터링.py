'''
파이썬 아니면 개극혐일듯!
'''

from collections import defaultdict
import re
def solution(str1, str2):
    str1 = str1.lower()
    str2 = str2.lower()
    dic1 = defaultdict(int)
    dic2 = defaultdict(int)
    regex = re.compile('[a-z]{2}')
    for s in [str1[i:i+2] for i in range(len(str1)-1)]:
        res = regex.match(s)
        if res:
            dic1[s]+=1
    
    for s in [str2[i:i+2] for i in range(len(str2)-1)]:
        res = regex.match(s)
        if res:
            dic2[s]+=1
            
    sum1,sum2  = 0,0
    for k in dic1.keys() | dic2.keys():
        sum1 += max(dic1[k], dic2[k])
        sum2 += min(dic2[k], dic1[k])
    
    return(65536*sum2//sum1 if sum1 != 0 else 65536) 
