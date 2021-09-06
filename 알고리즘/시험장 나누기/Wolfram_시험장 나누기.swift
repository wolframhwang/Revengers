// 21.09.05 Solved By Wolfram Hwang
// 정말 어려운 문제같습니다.
// 최적화 문제를 결정 문제로 바꾼다는 발상을 하는게 쉬운일은아니네요
// 이분탐색으로 그룹을 나눠주면서 값을 비교해나가는 방식으로 문제를 해결하면 됩니다.
import Foundation
var root : Int = 0
let INF = 99999999999
var adj: [[Int]] = []
var visit :[Bool] = []
var group = 0
var value = true
func search(_ node: Int, _ mid: Int, _ num: [Int])->Int {
    if num[node] > mid { value = false; return -1 }
    var left = 0
    var right = 0
    if adj[node].count == 2 {
        left = search(adj[node][0], mid, num)
        right = search(adj[node][1], mid, num)
        //num[node]에 기록된 값과 그 그룹에 해당하는 값이 mid보다 작으면 리턴해주면됌
        if left + right + num[node] <= mid { return left + right + num[node]}
        else {
            if left + num[node] > mid, right + num[node] > mid {
                group += 2 
                return num[node]
            }
            else if left + num[node] > mid {
                group += 1
                return right + num[node]
            }else if right + num[node] > mid {
                group += 1
                return left + num[node]                
            }else{
                if left > right { group += 1; return right + num[node]}
                else { group += 1; return left + num[node]}
            }
        }
    }else if adj[node].count == 1 {
        left = search(adj[node][0], mid, num)
        if left + num[node] > mid { 
            group += 1
            return num[node]
        }
        else {
            return left + num[node]
        }
    }else{ return num[node] }//자식 없으면 그냥 자기 자신 리턴해주기
}

func check(_ k: Int, _ mid: Int, _ num: [Int]) -> Bool {
    group = 0
    value = true
    search(root, mid, num)
    if group >= k || value == false { return false}
    return true
}
func solution(_ k:Int, _ num:[Int], _ links:[[Int]]) -> Int {
    var answer = INF
    adj = [[Int]](repeating: [Int](), count: num.count + 1)
    visit = [Bool](repeating: false, count: num.count + 1)
    for i in 0..<links.count {
        if links[i][0] != -1 {
            adj[i].append(links[i][0])
            visit[links[i][0]] = true
        }
        if links[i][1] != -1 {
            adj[i].append(links[i][1])
            visit[links[i][1]] = true
        }
    }
    for i in 0..<num.count {
        if visit[i] == false { 
            root = i
        }
    }
    var s = 1
    var e = 100000010
    while s <= e {
        let mid = (s + e) / 2
        if (check(k, mid, num)) {
            answer = min(answer, mid)
            e = mid - 1
        }else{
            s = mid + 1
        }
    }
    return answer
}
