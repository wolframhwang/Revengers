// solved by Wolfram Hwang
// using Double Linked List
// why? -> it's easy to pop operation in linked list
class Node {
    var key : Int = 0
    var value : Int = 0
    var prev : Node?
    var next : Node?
    init(_ _key : Int, _ _value: Int, _ _prev: Node?, _ _next: Node?) {
        key = _key
        value = _value
        prev = _prev
        next = _next
    }
    func pop() {
        prev?.next = next
        next?.prev = prev
    }
}

class LRUCache {
    // O(1)
    var front : Node? = Node(0, 0, nil, nil)
    var rear : Node? = Node(0, 0, nil, nil)
    var dict : [Int : Node?] = [:]
    var limit = 0
    var size = 0
    init(_ capacity: Int) {
        limit = capacity
        front?.next = rear
        rear?.prev = front
        dict = [:]
        size = 0
    }
    func get(_ key: Int) -> Int {
        guard let v = dict[key] else{
            return -1
        }
        v?.pop()
        v?.next = front?.next
        v?.prev = front
        front?.next?.prev = v
        front?.next = v        
        let ret = v?.value
        return ret!
    }
    
    func put(_ key: Int, _ value: Int) {
        if var node = dict[key] {
            node?.value = value
            node?.pop()
            node?.next = front?.next
            front?.next?.prev = node
            node?.prev = front
            front?.next = node            
        } else {
            var node = Node(key, value, front, front?.next)
            front?.next?.prev = node
            front?.next = node
            dict[key] = node
            if size == limit {
                let rm = rear?.prev
                rm?.pop()
                let vv = rm?.key
                dict[vv!] = nil
            }else{
                size += 1
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * let obj = LRUCache(capacity)
 * let ret_1: Int = obj.get(key)
 * obj.put(key, value)
 */
