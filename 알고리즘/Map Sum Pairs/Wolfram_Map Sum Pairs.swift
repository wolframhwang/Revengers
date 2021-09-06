// 21.09.06 solved By Wolfram Hwang
// Trie를 이용한 문제
// Trie를 하여 insert하고, sum 하는단계에서 search를 통해 해당하는 모든 문자열을 리턴함
class Node {
    var value: String
    var children: [String: Node] = [:]
    var isFinal : Bool = false
    var data: String?
    init(value: String) {self.value = value}
    func add(value: String) {
        let newNode = Node(value: value)
        children[value] = newNode
    }
}
class Trie {
    let root: Node
    init() {self.root = Node(value: "")}
    func insert(_ word: String) {
        var node = root
        let wordArr = word.map{ String($0)}
        for i in 0..<wordArr.count {
            let c = wordArr[i]
            if !node.children.keys.contains(c) {
                node.add(value: c)
            }
            node = node.children[c]!
        }
        node.isFinal = true
        node.data = word
    }

    func search(prefix: String) -> [String] {
        var node = root
        let wordArr = prefix.map { String($0)}
        for c in wordArr {
            if node.children.keys.contains(c) {
                node = node.children[c]!
            }else{
                return []
            }
        }

        func remainWord(node: Node) ->[String] {
            let keys = node.children.keys.map { String($0)}
            var result : [String] = []
            if node.isFinal {
                result.append(node.data!)
            }
            for key in keys {
                result += remainWord(node: node.children[key]!)

            }
            return result
        }
        return remainWord(node: node)
    }
}
class MapSum {
    /** Initialize your data structure here. */
    let trie = Trie()
    var dict: [String: Int] = [:]
    init() {
    }
    
    func insert(_ key: String, _ val: Int) {
        trie.insert(key)
        dict[key] = val
    }
    
    func sum(_ prefix: String) -> Int {
        let data = trie.search(prefix: prefix)
        var ret = 0
        for s in data {
            ret += dict[s]!
        }        
        return ret
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * let obj = MapSum()
 * obj.insert(key, val)
 * let ret_2: Int = obj.sum(prefix)
 */
