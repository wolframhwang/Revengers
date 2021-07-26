#include <string>
#include <vector>

using namespace std;
int answer = 0;
void dfs(vector<int> numbers, int target, int sum, int count){
    if(count < numbers.size()){//마지막까지 확인했는가?
        dfs(numbers, target, sum + numbers[count], count+1);
        dfs(numbers, target, sum - numbers[count], count+1);
    }
    else{//마지막까지 확인했는데
        if(sum == target){//sum이 target이랑같으면 +1
            answer++;
        }
    }
}

int solution(vector<int> numbers, int target) {
    dfs(numbers, target, 0, answer);
    return answer;
}
