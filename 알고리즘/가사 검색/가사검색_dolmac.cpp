#include <string>
#include <vector>
// 두개 시간초과 나오는데 trie 자료구조 쓰면 된다는데 나는 모르니까 넘어가도록 한다(문자열 검색 빠르게 해주는거라는데 아몰랑)
using namespace std;

vector<int> solution(vector<string> words, vector<string> queries) {
    vector<int> answer;
    for(int i=0;i<queries.size();i++){
        int answer_num = 0;
        int len = queries[i].length();
        for(int j=0;j<words.size();j++){
            int words_len = words[j].length();
            if(len == words_len){
                int same_val = 0;
                for(int k=0;k<len;k++){
                    if(queries[i][k] != '?'){
                        if(queries[i][k] != words[j][k]){
                            same_val = 1;
                            break;
                        }
                    }
                }
                if(same_val == 0){
                    answer_num++;
                }
            }
            else{
                
            }
        }
        answer.push_back(answer_num);
    }
    return answer;
}
