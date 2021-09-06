/*
간단한 트라이문제.
구조체 안쓰고 int array 만으로도 처리할 수 있습니다.

시간복잡도는 글씨의 길이 * 글씨의 개수 
*/
class MapSum() {
    var tries = Array(50*50){IntArray(26)}
    var values = IntArray(50*50)
    var v = IntArray(50*50)
    /** Initialize your data structure here. */
    var tid = 0
    
    fun insert(key: String, `val`: Int) {
        var now = 0
        for(c in key) {
            var conv = c.toInt() - 'a'.toInt()
            if(tries[now][conv] == 0) {
                tries[now][conv] = ++tid
            }
            now = tries[now][conv]
        }
        var change = `val` - v[now]
        v[now] = `val`
        now = 0
        for(c in key) {
            values[now] += change
            var nxt = c.toInt() - 'a'.toInt()
            now = tries[now][nxt]
        }
        values[now] += change
    }

    fun sum(prefix: String): Int {
        var now = 0
        for(c in prefix) {
            var nxt = c.toInt() - 'a'.toInt()
            if(tries[now][nxt] == 0) return 0
            now = tries[now][nxt]
        }
      return values[now]
    }
}
