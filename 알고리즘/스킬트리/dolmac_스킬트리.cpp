#include <string>
#include <vector>
using namespace std;

int solution(string skill, vector<string> skill_trees) {
    int answer = 0;
    int skill_loc[26]={ 0 };
    for(int i=0;i<skill.size();i++){
        skill_loc[skill[i]-65] = 1;
    }
    for(int i=0; i<skill_trees.size(); i++){
        string skill_imsi = "";
        for(int j=0;j<skill_trees[i].size();j++){
            if(skill_loc[skill_trees[i][j]-65] == 1){
                skill_imsi += skill_trees[i][j];
            }
        }
        int tf_count = 0;
        for(int j=0;j<skill_imsi.size();j++){
            if(skill_imsi[j]!=skill[j]){
                tf_count = 1;
                break;
            }
        }
        if(tf_count == 0){
            answer++;
        }
    }
    return answer;
}
