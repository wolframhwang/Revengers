/*
코틀린 없는 망문제라 킹-cpp로 품
비트 연산으로  chk
*/
#include <string>
#include <vector>

using namespace std;

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    int chk = 0;
    for(int i = 0; i < skill.size(); ++i) {
        chk |= 1 << (skill[i] - 'A');
    }
    for(int i = 0; i < skill_trees.size(); ++i){
        string sk = skill_trees[i];
        string s = "";
        int cur = 0;
        for(int i = 0; i < sk.size(); ++i){
            if(cur < skill.size() &&(1 << (sk[i]- 'A')) & chk) {
                s.push_back(sk[i]);
                cur++;
            }
        }
        if(s == skill.substr(0, s.size())) answer++;
    }
    
    return answer;
}
