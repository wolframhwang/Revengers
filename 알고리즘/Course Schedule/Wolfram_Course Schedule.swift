// Solved By Wolfram Hwang

class Solution {
    func canFinish(_ numCourses: Int, _ prerequisites: [[Int]]) -> Bool {
        var matrix = [[Int]](repeatElement([Int](repeatElement(0, count: numCourses)), count: numCourses))
        var inDegree = [Int](repeatElement(0, count: numCourses))
        var count = 0
        var queue = [Int]()
        
        for i in 0..<prerequisites.count {
            let ready = prerequisites[i][0]
            let pre = prerequisites[i][1]
            if matrix[pre][ready] == 0  {
                inDegree[ready] += 1
            }
            matrix[pre][ready] = 1
        }
        for i in 0..<inDegree.count {
            if inDegree[i] == 0 {
                queue.append(i)
            }
        }
        while !queue.isEmpty {
            let course = queue.removeFirst()
            count += 1
            for i in 0..<numCourses {
                if matrix[course][i] != 0 {
                    inDegree[i] -= 1
                    if inDegree[i] == 0 {
                        queue.append(i)
                    }
                }
            }
        }
        
        return count == numCourses
    }
}
