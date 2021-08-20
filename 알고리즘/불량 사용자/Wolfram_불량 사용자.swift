// 21.08.20 Solved by Wolfram Hwang
// Hashing

import Foundation
var map: [Int: Set<String>] = [Int: Set<String>]()
var cmp: [Int: Set<String>] = [Int: Set<String>]()
var visit: [String: Bool] = [String: Bool]()
var ccnt = 0
var ans: Set<[String]> = Set<[String]>()

func combi(_ str: String,_ hsh:Int,  _ idx: Int, _ count: Int) {
    if count == ccnt {
        if map[hsh] == nil {
            map[hsh] = Set<String>()
        }
        map[hsh]?.insert(str)
        return
    }
    if idx >= str.count {
        return 
    }    
    var h = hsh - (hsh & (63 << (idx * 6)))
    combi(str, h, idx + 1, count + 1)

    combi(str, hsh, idx + 1, count)
}

func findAns(_ banned_id:[(Int, Int)], _ nb: [String], _ idx: Int) {
    if idx == banned_id.count {
        let inp = nb.sorted()
        ans.insert(inp)
        return
    }
    if banned_id[idx].0 < 0 {
        for s in cmp[-banned_id[idx].0]! {
            var newN = nb
            if nb.contains(s) == false {
                newN.append(s)
                findAns(banned_id, newN, idx + 1)
            }
        }
    }else{
        for s in map[banned_id[idx].0]! {
            if banned_id[idx].1.count != s.count {
                continue
            }
            var newN = nb
            if nb.contains(s) == false {
                newN.append(s)
                findAns(banned_id, newN, idx + 1)
            }
        }
    }
}

func solution(_ user_id:[String], _ banned_id:[String]) -> Int {
    var mp : [Character: Int] = [Character: Int]()
    let alp = "abcdefghijklmnopqrtsuvwxyz"
    let nb = "0123456789"
    var idx = 1
    for c in alp {
        mp[c] = idx
        idx += 1
    }
    for c in nb {
        mp[c] = idx
        idx += 1
    }
    for uid in user_id {
        var hash = 0
        for c in uid {
            hash = (hash << 6) + mp[c]!
        }
        if cmp[uid.count] == nil {
            cmp[uid.count] = Set<String>()
        }
        cmp[uid.count]?.insert(uid)
        for i in 0..<uid.count {
            ccnt = i
            combi(uid, hash, 0, 0)
        }
    }
    var vd = [(Int, Int)]()
    for bid in  banned_id {
        var hash = 0
        for c in bid {
            if c == "*" {
                hash = (hash << 6)
            }else{
                hash = (hash << 6) + mp[c]!
            }        
        }
        if hash == 0 {
            hash = -bid.count
        }
        vd.append((hash, bid.count))
    }
    var inp: [String] = []
    findAns(vd, inp, 0)
    
    
    return ans.count
}
