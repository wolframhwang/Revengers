def solution(s):
    answer = -1
    st = []
    for c in s:
        if st and st[-1] == c: 
            st.pop()
        else: st.append(c)

    return 1 if(len(st) == 0) else 0
