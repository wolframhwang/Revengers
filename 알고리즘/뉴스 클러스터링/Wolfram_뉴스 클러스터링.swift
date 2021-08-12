// 21/08/13 Solved by Wolfram Hwang
// 맵 사용해서 개수비교해서 품

func solution(_ str1:String, _ str2:String) -> Int {
    var alp = "abcdefghijklmnopqrstuvwxyz"
    alp = alp.uppercased()
    var tot : [String: Int] = [String: Int]()
    var s1 = str1.uppercased()
    var s2 = str2.uppercased()
    var ss1 : [String] = []
    var ss2 : [String] = []
    for c in s1 {
        ss1.append(String(c))
    }
    for c in s2 {
        ss2.append(String(c))
    }
    var inp : String = ""
    for i in 1..<ss1.count {
        if alp.contains(ss1[i - 1]), alp.contains(ss1[i]) {
            inp = ss1[i - 1] + ss1[i]
            if tot[inp] != nil {
                tot[inp]? += 1
            }else{
                tot[inp] = 1
            }
        }
    }
    var comp: [String: Int] = tot
    var gyo : [String: Int] = [String: Int]()
    for i in 1..<ss2.count {
        if alp.contains(ss2[i - 1]), alp.contains(ss2[i]) {
            inp = ss2[i - 1] + ss2[i]
            if comp[inp] != nil {
                if comp[inp]! > 0 {
                    comp[inp]? -= 1
                    if gyo[inp] != nil {
                        gyo[inp]? += 1
                    }else{
                        gyo[inp] = 1
                    }
                }else{
                    if tot[inp] != nil {
                        tot[inp]? += 1
                    }else{
                        tot[inp] = 1
                    }    
                }
            }else{
                if tot[inp] != nil {
                    tot[inp]? += 1
                }else{
                    tot[inp] = 1
                }
            }
        }
    }
    var child = 0
    var parent = 0
    for (key, val) in gyo {
        child += val
    }
    for (key, val) in tot {
        parent += val
    }
    var val : Double = 1
    if parent != 0 {
        val = Double(child) / Double(parent)
    }
    val *= 65536
    return Int(val)
}
