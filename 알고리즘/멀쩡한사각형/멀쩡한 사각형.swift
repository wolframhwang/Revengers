// 21/07/27 Solved by Wolfram Hwang
// 그냥 .. 드러운 문제임;
import Foundation

func gcd(_ a:Int, _ b:Int) -> Int64{
    if a == 0 { return Int64(b) }
    return gcd(b%a,a)
}

func solution(_ w:Int, _ h:Int) -> Int64{
    let r:Int64 = Int64(w)
    let c:Int64 = Int64(h)
    return r * c - r - c + gcd(w,h)
}
