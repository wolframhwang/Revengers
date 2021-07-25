class Solution {
    fun solution(record: Array<String>): Array<String> {
        var answer = ArrayList<String>()
        var id = HashMap<String,String>()
        
        for (str in record) {
            val command = str.split(" ")
            if(command.size == 3) id[command[1]] = command[2]
        }
        for(str in record){
            val command = str.split(" ")
            when(command[0]) {
                "Enter"->answer.add("${id[command[1]]}님이 들어왔습니다.")
                "Leave"->answer.add("${id[command[1]]}님이 나갔습니다.")
                else->{}
            }
        }
        return answer.toArray(Array<String>(answer.size){""})
    }
}
