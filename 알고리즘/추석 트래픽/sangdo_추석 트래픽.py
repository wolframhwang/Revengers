
'''
코틀린 없는 망문제
끝점과 시작점을 파싱해서 구한뒤에 (ms단위)
끝난 지점을 기준으로 + 1000보다 작은 시점에서 시작된 작업의 개수를 구하고(e),
끝난 지점 이전의 작업들은 끝났으므로 이 값을 빼준다.(s)
'''
def solution(lines):
    answer = 0
    st = []
    ed = []
    for t in lines:
        command = t.split(" ")
        period = command[2][:-1].split(".")
        period.append('0')
        p = int(period[0])*1000 + int(period[1])
       	time = command[1].split(":")
        h,m,s,ss = map(int, time[:2] + list(time[-1].split(".")))
        t = (h*3600 + m *60 + s) * 1000 + ss
        st.append(t-p+1)
        ed.append(t)
    st.sort()
    ed.sort()
    mmax = 0
    e =0
    for s in range(0, len(ed)):
        while e != len(st) and st[e] - ed[s] < 1000:
            e+=1
        mmax = max([mmax, e - s])
    return mmax
