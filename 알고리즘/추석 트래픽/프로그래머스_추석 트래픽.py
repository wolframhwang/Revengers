"""
[Topics]
문제 잘 읽기
sliding window
파싱

[Sketch]

"""
def solution(lines):
    answer = 0

    start_times = list()
    end_times = list()

    for line in lines:
        logs = line.split(" ")
        time = logs[1].split(":")

        process_time = float(logs[2][:-1]) * 1000 - 1

        end = (int(time[0]) * 60 * 60 * 1000) + (int(time[1]) * 60 * 1000) + (float(time[2]) * 1000)
        end_times.append(end)

        start = end - process_time
        start_times.append(start)

    for i in range(len(start_times)):
        cnt = 0
        start_range = start_times[i] - 999

        for j in range(len(start_times)):
            if start_range <= start_times[j] <= start_times[i]:
                cnt += 1
            elif start_times[j] <= start_range <= end_times[j]:
                cnt += 1

        answer = max(answer, cnt)

    return answer