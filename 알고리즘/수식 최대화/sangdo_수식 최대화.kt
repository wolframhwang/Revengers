/*
음.. 완탐으로 +-* 순서를 6가지 조합 찾아내고,
후위연산자 만들어서 계산하는 방식을 만들었는데,
이거 맞겠지? 헿
*/
import java.util.*

class Cal(var value: Long=0, var isOper: Boolean=false)
class Solution {
    var order = IntArray(256)
    fun calculate(expression: ArrayList<Cal>, oper:StringBuilder): Long {
        var stack = Stack<Cal>()
        for(i in oper.indices) {
            order[oper[i].toInt()] = i
        }
        var postCal = ArrayList<Cal>()
        
        for(c in expression){
            if(c.isOper) {
                while(stack.isNotEmpty() && order[stack.peek().value.toInt()] >= order[c.value.toInt()]){
                    postCal.add(stack.pop())
                }
                stack.add(c)
            }
            else {
                postCal.add(c)
            }
        }
        while(stack.isNotEmpty()){
            postCal.add(stack.pop())
        }
        var calStack = Stack<Long>()
        for(i in postCal) {
            if(i.isOper){
                var r = calStack.pop()
                var l = calStack.pop()
                calStack.add(when(i.value) {
                    '-'.toLong()->l-r
                    '+'.toLong()->l+r
                    '*'.toLong()->l*r
                    else->-1L
                })
            }
            else {
                calStack.add(i.value)
            }
        }
        
        return Math.abs(calStack.pop())
    }
    fun makeOrder(expression: ArrayList<Cal>, oper: StringBuilder, idx: Int):Long {
        if(idx == 3) {
            return calculate(expression, oper)
        }
        var res = 0L
        for(i in idx until 3){
            var t = oper[i]
            oper[i]=  oper[idx]
            oper[idx] = t
            res = Math.max(res, makeOrder(expression, oper, idx+1))
            oper[idx] = oper[i]
            oper[i] = t
        }
        return res
    }
    
    fun solution(expression: String): Long {
        var answer: Long = 0
        var cal = expression.split("-", "+", "*")
        var arr = ArrayList<Cal>()
        var cur = 0
        for(c in expression){
            when (c) {
                '-', '+', '*'->{
                    arr.add(Cal(value=cal[cur++].toLong()))
                    arr.add(Cal(isOper=true, value=c.toLong()))
                }
            }
        }
        arr.add(Cal(value=cal[cur].toLong()))
        var oper = StringBuilder("+-*")
        return makeOrder(arr, oper, 0)
    }
}
