// 21.09.02 Solved By Wolfram Hwang
// TRIE 구조를 구현하여 문제를 해결하였읍니다.
// insert시, childnum을 계속해서 업데이트 하게됩니다.
// 만약 탐색시, 마지막으로 탐색 string에서 childnum의 값이 1보다 큰경우를 찾아내고
// 여기서 다음구간부턴 유일한 하나이므로 해당 영역에 대해 리턴합니다.
// 만약 리턴 값이, 길이를 넘어간다면, 길이를 받게 만듭니다.

class Node {
    var value: String
    var children: [String : Node] = [:]
    var childnum: [String: Int] = [:]
    var isFinal: Bool = false
    var data: String?
    
    init(value: String) {
        self.value = value
    }
    
    func add(value: String) {
        let newNode = Node(value: value)
        children[value] = newNode
        childnum[value] = 0
    }
}


class TRIE{
    let root: Node
    
    init() {
        self.root = Node(value: "")
    }
    
    func insert(word: String) {
        var node = root
        let wordArr = word.map { String($0) }
        for i in 0..<wordArr.count {
            let c = wordArr[i]
            if !node.children.keys.contains(c) {
                node.add(value: c)
            }
            node.childnum[c]? += 1
            node = node.children[c]!
        }
        node.isFinal = true
        node.data = word
    }
    
    func search(prefix: String) -> Int {
        var node = root
        let wordArr = prefix.map { String($0) }
        var last = 0
        var idx = 1
        for c in wordArr {
            if node.children.keys.contains(c) {
                if node.childnum[c]! > 1 {
                    last = idx
                }
                node = node.children[c]!
            }
            idx += 1
        }
        return last + 1
    }
}
func solution(_ words:[String]) -> Int {
    var T : TRIE = TRIE()
    for i in words {
        T.insert(word: i)
    }
    var ans = 0
    for s in words {
        let len = T.search(prefix: s)
        ans += len < s.count ? len : s.count
    }
    return ans
}
