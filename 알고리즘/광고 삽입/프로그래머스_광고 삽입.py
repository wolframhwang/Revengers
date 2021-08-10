"""
memoization
"""
from datetime import datetime, timedelta


def str2sec(string):

    hour, min, sec = map(int, string.split(':'))
    return hour * 3600 + min * 60 + sec


def sec2str(seconds):

    string = ''
    hour = seconds // 3600
    seconds -= hour * 3600
    min = seconds // 60
    seconds -= min * 60

    return f"{str(hour).zfill(2)}:{str(min).zfill(2)}:{str(seconds).zfill(2)}"


def solution(play_time, adv_time, logs):
    play_time = str2sec(play_time)
    adv_time = str2sec(adv_time)

    timeline = [0] * (play_time + 1)

    for log in logs:
        start, end = map(str2sec, log.split('-'))
        timeline[start] += 1
        timeline[end] -= 1

    for i in range(1, play_time + 1):
        timeline[i] = timeline[i] + timeline[i - 1]

    for i in range(1, play_time + 1):
        timeline[i] = timeline[i] + timeline[i - 1]

    answer = 0
    max_cnt = timeline[adv_time]

    for start in range(1, play_time):
        end = play_time if start + adv_time >= play_time else start + adv_time

        if max_cnt < timeline[end] - timeline[start]:
            max_cnt = timeline[end] - timeline[start]
            answer = start + 1

    return sec2str(answer)
