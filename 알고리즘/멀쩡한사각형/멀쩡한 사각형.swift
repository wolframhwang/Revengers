// 21/07/27 Solved by Wolfram Hwang
// 그냥 .. 드러운 문제임;
import Foundation

func solution(_ w:Int, _ h:Int) -> Int64{
    var answer:Int64 = 0
    if w == h {
        return (Int64(w) * Int64(h - 1))
    }
    var r = w > h ? w : h
    var c = w > h ? h : w
    if r % c == 0 {
        let remain = r / c
        return (Int64(w) * Int64(h)) - Int64(remain * c)
    }
    var a = r
    var b = c
    while b != 0 {
        let temp = a % b
        a = b
        b = temp
    }
    if a != 0 {
        r = r / a
        c = c / a
    }
    let height = Double(r) / Double(c)
    var bottom :Double = 0
    var ms : Int64 = 0
    for i in 0..<c {
        let hh = bottom + height
        if i != c - 1{
            ms += Int64(hh)
            if hh > Double(Int64(hh)) {
                ms += 1
            }
        }else {
            if Int(hh) != r {
                ms += Int64(r)
            }else{
                ms += Int64(hh)
            }
        }
        ms -= Int64(bottom)
        bottom = hh
    }
    print(ms)
    answer = Int64(h) * Int64(w)
    ms = a == 0 ? ms : ms * Int64(a)
    answer -= ms
    return answer
}
