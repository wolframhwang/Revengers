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

    func search(_ word: [String], _ index: Int, _ nd: Node) -> Bool {
        if index == word.count {
            if nd.isFinal {
                return true
            }else { return false}
        }
        var node = nd
        var ret = false
        if word[index] == "." {
            for (key, value) in node.children {
                let result = search(word, index + 1, node.children[key]!)
                if ret == false, result == true { ret = true }
            }
        }
        else{
            if node.children.keys.contains(word[index]) {
                let result = search(word, index + 1, node.children[word[index]]!)
                if ret == false, result == true { ret = true }
            }
            else {
                return false
            }
        }
        return ret
    }
}
class WordDictionary {

    /** Initialize your data structure here. */
    let trie = Trie()
    init() {
        
    }
    
    func addWord(_ word: String) {
        trie.insert(word)
    }
    
    func search(_ word: String) -> Bool {
        let inp = word.map { String($0) }
        return trie.search(inp, 0, trie.root)
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * let obj = WordDictionary()
 * obj.addWord(word)
 * let ret_2: Bool = obj.search(word)
 */
