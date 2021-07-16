// Solved By Wolfram
// 세그먼트 트리 풀이

import Foundation

var tree : [Int] = []
var arr: [Int] = []
func update(_ start: Int, _ end: Int, _ node: Int, _ index: Int, _ diff: Int){
    if index < start || index > end {
        return
    }
    tree[node] += diff
    if start == end {
        return
    }
    let mid = (start + end) / 2
    update(start, mid, node * 2, index, diff)
    update(mid + 1, end, node * 2 + 1, index, diff)
}
func makeTree (_ left: Int, _ right: Int, _ node: Int)->Int{
    if left == right {
        tree[node] = arr[left]
        return tree[node]
    }
    let mid = (left + right) / 2
    tree[node] += makeTree(left, mid, node * 2)
    tree[node] += makeTree(mid + 1, right, node * 2 + 1)
    return tree[node]
}
func sum(_ start: Int, _ end: Int, _ node: Int, _ left : Int, _ right: Int) -> Int {
    if left > end || right < start {
        return 0
    }
    if left <= start && end <= right {
        return tree[node]
    }
    let mid = (start + end) / 2
    let ret = sum(start, mid, node * 2, left, right) + sum( mid + 1, end, node * 2 + 1, left, right)
    return ret
}

func solution(_ n:Int, _ k:Int, _ cmd:[String]) -> String {
    arr = [Int](repeating: 1, count: n + 1)
    let cnnt = (n + 1) << 3
    tree = [Int](repeating: 0, count: cnnt)
    var q : [Int] = []
    makeTree(1, n, 1)
    var idx = k + 1
    
    for cd in cmd {
        if cd.first == "U" {
            let split = cd.split(separator: " ")
            let val = Int(String(split.last!))!
            var left = 1
            var right = idx - 1
            let end = idx - 1
            var comp = 0
            while left <= right {
                let mid = (left + right) / 2
                comp = sum(1, n, 1, mid, end)
                if comp == val {
                    left = mid
                    break
                }
                if comp > val {
                    left = mid + 1
                }else {
                    right = mid
                }
            }
            if comp == val {
                while true {
                    if arr[left] == 1 {
                        break
                    }
                    left += 1
                }
            }else if comp < val{
                while true {
                    if sum(1, n, 1, left, end) == val, arr[left] == 1{
                        break
                    }
                    left -= 1
                }
            }else{
                while true {
                    if sum(1, n, 1, left, end) == val, arr[left] == 1 {
                        break
                    }
                    left += 1
                }
            }
            idx = left
        }else if cd.first == "D" {
            let split = cd.split(separator: " ")
            let val = Int(String(split.last!))!
            var left = idx + 1
            var right = n
            let start = idx + 1
            var comp = 0
            while left <= right {
                let mid = (left + right) / 2
                comp = sum(1, n, 1, start, mid)
                if comp == val {
                    right = mid
                    break
                }
                if comp > val {
                    right = mid
                }else {
                    left = mid + 1
                }
            }
            if comp == val {
                while true {
                    if arr[right] == 1 {
                        break
                    }
                    right -= 1
                }
            }else if comp < val{
                while true {
                    if sum(1, n, 1, start, right) == val, arr[right] == 1{
                        break
                    }
                    right += 1
                }
            }else{
                while true {
                    if sum(1, n, 1, start, right) == val, arr[right] == 1 {
                        break
                    }
                    right -= 1
                }
            }
            idx = right
        }else if cd.first == "C" {
            arr[idx] = 0
            q.append(idx)
            update(1, n, 1, idx, -1)
            if sum(1, n, 1, idx, n) >= 1 {
                var left = idx + 1
                var right = n
                var comp = 0
                while left <= right {
                    let mid = (left + right) / 2
                    comp = sum(1, n, 1, idx, mid)
                    if comp == 1 {
                        right = mid
                        break
                    }
                    if comp > 1 {
                        right = mid
                    }else{
                        left = mid + 1
                    }
                }
                if comp == 1 {
                    while true {
                        if arr[right] == 1 {
                            break
                        }
                        right -= 1
                    }
                }else if comp < 1{
                    while true {
                        if sum(1, n, 1, idx + 1, right) == 1, arr[right] == 1{
                            break
                        }
                        right += 1
                    }
                }else{
                    while true {
                        if sum(1, n, 1, idx + 1, right) == 1, arr[right] == 1 {
                            break
                        }
                        right -= 1
                    }
                }
                idx = right
            }else{
                var left = 1
                var right = idx - 1
                var comp = 0
                while left <= right {
                    let mid = (left + right) / 2
                    comp = sum(1, n, 1, mid, idx)
                    if comp == 1 {
                        left = mid
                        break
                    }
                    if comp > 1 {
                        left = mid + 1
                    }else {
                        right = mid
                    }
                }
                if comp == 1 {
                    while true {
                        if arr[left] == 1 {
                            break
                        }
                        left += 1
                    }
                }else if comp < 1{
                    while true {
                        if sum(1, n, 1, left, idx - 1) == 1, arr[left] == 1{
                            break
                        }
                        left -= 1
                    }
                }else{
                    while true {
                        if sum(1, n, 1, left, idx - 1) == 1, arr[left] == 1 {
                            break
                        }
                        left += 1
                    }
                }
                idx = left
            }
        }else{
            let f = q[q.count - 1]
            q.popLast()
            arr[f] = 1
            update(1, n, 1, f, 1)
        }
    }
    var ret = ""
    for x in 1..<arr.count {
        if arr[x] == 1 {
            ret += "O"
        }else{
            ret += "X"
        }
    }
    return ret
}
