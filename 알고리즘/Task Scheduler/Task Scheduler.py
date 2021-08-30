"""
CPU 가 하는 일로 문제를 만들다니 재밌네

ord() : 문자 -> 아스키 코드 값
chr() : 아스키 코드 값 -> 문자
.count("검색 요소", start, end) : **문자열** 카운팅

1. 각 문자가 몇개씩 나오는지 저장한다.
2. 등장 횟수가 모두 똑같으면 cool time 필요 없다.
3. 다르면, 등장 횟수가 젤 큰 문자는 연속적으로 작업을 수행해야 할 때가 온다.
4. num = 등장 횟수가 젤 큰 거 - 두번쨰로 큰 거


1. 각 알파벳 문자가 몇개씩 나오는지 저장한다.
2, 문제의 답은 빈도수가 가장 큰 알파벳 문자에 의해 결정된다
    나머지는 다른 문자들로 번갈아 나온다.
3. 그 빈도값 맥스 문자의
4. 최소 길이는 tasks의 길이 (빈도수가 다 똑같을 떄)

"""
#
class Solution(object):
    def leastInterval(self, tasks, n):
        # alphabets
        frequencies = [0] * 26
        for t in tasks:
            frequencies[ord(t) - ord('A')] += 1

        # max(frequency)
        f_max = max(frequencies)

        # counting most frequent tasks
        n_max = frequencies.count(f_max)

        return max(len(tasks), (f_max - 1)*(n + 1) + n_max)

