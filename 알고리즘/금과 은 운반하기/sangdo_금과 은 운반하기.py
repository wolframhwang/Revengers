'''
시간복잡도 : O(log(4*10^14))
옮겨야하는 금과 은의 개수가 1_000_000_000 개 씩이므로 2*10^9
그리고, 편도 1*10^5 가 걸리면 왕복 2*10^5의 시간이 걸리므로
최악의 경우는 2*10^5초 마다 금이나 은 1개씩을 옮겨야하고, 2*10^9개의 금과 은을 옮겨야 하기 때문에
최대 걸리는 시간은 4*10^14 입니다. (long 안에 포함 가능)
요거만 잘 생각하면 금과 은을 모두 옮길 수 있는 개수를 이분탐색으로 탐색하면 됩니다!
'''
def solution(a, b, g, s, w, t):
    answer = -1
    st = 0
    ed = 400000*1000000000
    while st < ed:
        m = (st+ed)//2
        total = 0
        gTotal = 0
        sTotal = 0
        for i in range(0, len(g)):
            if(m >= t[i]) :
                remain = m-t[i]
                move = remain//2//t[i] + 1
                total += min(move*w[i], g[i] + s[i])
                gTotal += min(move*w[i], g[i])
                sTotal += min(move*w[i], s[i])
        if total >= a+b and gTotal >= a and sTotal >= b: ed = m
        else: st = m+1
    return st
