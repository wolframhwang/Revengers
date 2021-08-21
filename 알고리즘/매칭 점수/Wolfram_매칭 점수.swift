// 21.08.21 solved By Wolfram Hwang

// 시 뮬 레 이 션

import Foundation

func solution(_ word:String, _ pages:[String]) -> Int {
    let alp = "abcdefghijklmnopqrstuvwxyz"
    var isSt: Set<String> = []
    
    var normalScore:[String: Int] = [String: Int]()
    var map: [String: [String]] = [String: [String]]()
    var numbering: [String: Int] = [String: Int]()
    var score = [Int]()
    var comp :[String] = []
    for c in word {
        comp.append(c.lowercased())
    }
    var idx = 0
    for s in pages {
        var str = ""
        score.append(0)
        for c in s.lowercased() {
            if alp.contains(c) {
                str += String(c)
            }else{
                if str == word.lowercased() {
                    score[idx] += 1
                }
                str = ""
            }
        }
        idx += 1
    }
    idx = 0
    for s in pages {
        
        let ss = s.lowercased().split(separator: "\n")
        var name : String = ""
        for c in ss {
            if c.contains("<meta"), c.contains("https://") {
                let vv = c.split(separator: "\"")
                if vv.contains(" content=") {
                    for ii in vv {
                        if ii.contains("https://") {
                            normalScore[String(ii)] = 0
                            map[String(ii)] = [String]()
                            name = String(ii)
                            numbering[name] = idx
                            idx += 1
                        }
                    }
                }
            }
            let vv = c.split(separator: "\"")
            //print("NAME", name)
            var isLink = false
            for ss in vv {
                if ss.contains("a href") {
                    isLink = true
                }
                if isLink {
                    if ss.contains("https://") {
                        map[name]?.append(String(ss))
                    }
                }
            }
        }
    }
    
    var ans: [Double] = [Double](repeating: 0, count: idx)
    for (key, value) in numbering {
        let name = key
        let normalN = score[value]
        ans[value] += Double(normalN)
        for other in map[name]! {
            let nname = other
            if numbering[nname] != nil {
                if map[name] != nil {
                    if map[name]?.count == 0 {
                        continue
                    }
                    let LinkP :Double = Double(normalN) / Double(map[name]!.count)
                    ans[numbering[nname]!] += LinkP
                }
            }
        }
    }
    var mx :Double = 0
    var midx = 0
    for i in 0..<ans.count {
        if mx < ans[i] {
            mx = ans[i]
            midx = i
        }
    }
    return midx
}

