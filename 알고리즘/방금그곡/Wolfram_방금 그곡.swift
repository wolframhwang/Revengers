// 21/08/14 Solved by Wolfram Hwang
// KMP라고 하는지는 모르겠는데.. 그냥 배열 만들어서 다 비교했어요


func commp(_ p: [String] , _ c: [String])-> Bool {
    for i in 0..<p.count {
        if p[i] == c[0] {
            var isComp = true
            for j in 0..<c.count {
                if i + j >= p.count {
                    isComp = false
                    break
                }
                if p[i + j] != c[j]{
                    isComp = false
                    break
                }
            }
            if isComp {
                return true
            }
        }
    }
    return false
}

func solution(_ m:String, _ musicinfos:[String]) -> String {
    var ansT = 0
    var ans = ""
    var comp : [String] = []
    for c in m {
        if c == "#" {
            comp[comp.count - 1] += String(c)
        }else{
            comp.append(String(c))
        }
    }

    for s in musicinfos {
        let str = s.split(separator: ",")
        let stime = str[0].split(separator: ":")
        let etime = str[1].split(separator: ":")

        let st = Int(stime[0])! * 60 + Int(stime[1])!
        let et = Int(etime[0])! * 60 + Int(etime[1])!
        let realTime = et - st
        let music = String(str[3])
        var musicArr : [String] = []
        for c in music {
            if c == "#" {
                musicArr[musicArr.count - 1] += String(c)
            }else{
                musicArr.append(String(c))
            }
        }
        //print(musicArr)
        var mm : [String] = []
        var idx = 0
        var rdx = 0
        while idx < realTime {
            rdx %= musicArr.count
            mm.append(musicArr[rdx])
            rdx += 1
            idx += 1
        }
        if commp(mm, comp) {
            if ansT < realTime {
                ansT = realTime
                ans = String(str[2])
            }
        }
    }
    if ans == "" {
        ans = "(None)"
    }
    return ans
}
