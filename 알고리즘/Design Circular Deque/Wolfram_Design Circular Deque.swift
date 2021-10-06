// 21.10.06 Solveed By Wolfram Hwang
// 간단한 API 구성
class Node {
    var value: Int = 0
    var EP : Bool = false
    var front : Node?
    var rear: Node?
    init(_ v: Int, _ E : Bool, _ f : Node?, _ r: Node?) {
        value = v
        EP = E
        front = f
        rear = r
    }
}
class MyCircularDeque {
    var R: Node?
    var F: Node?
    var SIZE = 0  
    var sz = 0
    init(_ k: Int) {
        R = Node(0, true, nil, nil)
        F = Node(0, true, nil, nil)
        R?.front = F
        F?.rear = R
        SIZE = k
    }
    
    func insertFront(_ value: Int) -> Bool {
        if SIZE == sz { return false }
        let newNode: Node? = Node(value, false, F, F?.rear)
        let next = F?.rear
        next?.front = newNode
        F?.rear = newNode
        sz += 1
        return true
    }
    
    func insertLast(_ value: Int) -> Bool {
        if SIZE == sz { return false }
        let newNode: Node? = Node(value, false, R?.front, R)
        let prev = R?.front
        prev?.rear = newNode
        R?.front = newNode
        sz += 1
        return true
    }
    
    func deleteFront() -> Bool {
        if sz == 0 {
            return false
        }  
        let delR = F?.rear?.rear
        delR?.front = F
        F?.rear = delR
        sz -= 1
        return true
    }
    
    func deleteLast() -> Bool {
        if sz == 0 { return false}
        let delF = R?.front?.front
        delF?.rear = R
        R?.front = delF
        sz -= 1
        return true
    }
    
    func getFront() -> Int {
        let isEnd = F?.rear?.EP
        if isEnd! { return -1 }
        let ret = F?.rear?.value
        return ret!
    }
    
    func getRear() -> Int {
        let isEnd = R?.front?.EP
        if isEnd! { return -1}
        let ret = R?.front?.value
        return ret!
    }
    
    func isEmpty() -> Bool {
        if sz == 0 { return true }
        return false
    }
    
    func isFull() -> Bool {
        if sz == SIZE { return true}
        return false
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * let obj = MyCircularDeque(k)
 * let ret_1: Bool = obj.insertFront(value)
 * let ret_2: Bool = obj.insertLast(value)
 * let ret_3: Bool = obj.deleteFront()
 * let ret_4: Bool = obj.deleteLast()
 * let ret_5: Int = obj.getFront()
 * let ret_6: Int = obj.getRear()
 * let ret_7: Bool = obj.isEmpty()
 * let ret_8: Bool = obj.isFull()
 */
