"""
[Topics]
최대공약수 구하기 - python from math import gcd
유클리드 호제법 - 최대공약수 공식

[Sketch]
sol1.좌표 위에서 생각
가로,세로의 공약수를 구해서
가로*세로-(가로+세로-공약수) 구하기
1. 최대공약수 == 1
2. 최대공약수 > 1

sol2. 유클리드호제
"""
#
def solution(w, h):
    ww, hh = w, h
    while(ww != hh):
        if ww > hh:
            ww -= hh
        else:
            hh -= ww

    return (w*h) - (w+h-hh)





