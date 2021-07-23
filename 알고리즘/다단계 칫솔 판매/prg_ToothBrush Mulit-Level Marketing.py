def solution(enroll, referral, seller, amount):
    answer = [0] * (len(enroll))
    graph_dict = dict(zip(enroll, range(len(enroll))))

    for i in range(len(seller)):
        man = seller[i]
        price = amount[i] * 100
        while True:
            node_num = graph_dict[man]
            div = price // 10
            answer[node_num] += price - div
            price = div
            man = referral[node_num]
            if man == "-": break
            if div == 0: break

    return answer