// 트라이

import Foundation

class Node {
    var value: String
    var count: Int
    var child: [String: Node]
    
    var isEmp: Bool {
        return child.isEmpty
    }
    
    init(value: String) {
        self.value = value
        count = 0
        child = [:]
    }
}

class Trie {
    var root: Node
    
    init() {
        root = Node(value: "")
    }
    
    func insert(_ word: String) {
        var now = root
        
        for char in word {
            now.count += 1
            if now.child[String(char)] == nil {
                now.child[String(char)] = Node(value: String(char))
            }
            
            now = now.child[String(char)]!
        }
    }
    
    func search(_ query: String) -> Int {        
        var now = root
        for char in query {
            if char == "?" { 
                return now.count 
            }
            if now.child[String(char)] == nil { return 0 }
            
            now = now.child[String(char)]!
        }
        
        return now.count
    }
}

func solution(_ words:[String], _ queries:[String]) -> [Int] {
    var fdict: [Int: Trie] = [:]
    var bdict: [Int: Trie] = [:]

    words.forEach { word in
        if fdict[word.count] == nil {
            fdict[word.count] = Trie()

            bdict[word.count] = Trie()
        }

        fdict[word.count]?.insert(word)
        bdict[word.count]?.insert(String(word.reversed()))
    }

    var answer: [Int] = []
    queries.forEach { query in
        if query[query.startIndex] == "?" {
            if let reverseTrie = bdict[query.count] {
                let reverseQuery = String(query.reversed())
                answer.append(reverseTrie.search(reverseQuery))
            } else {
                answer.append(0)
            }
        } else {
            if let trie = fdict[query.count] {
                answer.append(trie.search(query))
            } else {
                answer.append(0)
            }
        }
    }

    return answer
}
