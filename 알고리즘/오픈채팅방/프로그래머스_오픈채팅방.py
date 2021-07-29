"""
[Topics]
dictionary


[Sketch]
그냥 옮겨적기...?

"""
#
def solution(record):
    answer =[]
    output = []
    inuser = {}

    for i in record:
        command = i.split(" ")
        if command[0] == 'Leave':
            output.append([command[1],"님이 나갔습니다."])
        elif command[0] == 'Enter':
            inuser[command[1]] = command[2]
            output.append([command[1],"님이 들어왔습니다."])
        elif command[0] == 'Change':
            inuser[command[1]] = command[2]
    for log in output:
        answer.append(inuser[log[0]]+log[1])

    return answer
