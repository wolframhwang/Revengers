#include <string>
#include <vector>
#include <sstream>
#include <map>
#include <iostream>
using namespace std;
vector<string> split(string input, char delimiter) {// 문자열 스플릿
    vector<string> answer;
    stringstream ss(input);
    string temp;
 
    while (getline(ss, temp, delimiter)) {
        answer.push_back(temp);
    }
 
    return answer;
}

vector<string> solution(vector<string> record) {
    vector<string> answer;
    map<string, string> nickname;
    vector<string> record_el;
    vector<string> record_id;
    for(int i=0;i<record.size();i++){
        vector<string> imsi_record = split(record[i],' '); //레코드 스플릿한거 넣어둘 임시 벡터
        if(imsi_record[0] == "Enter"){//입장일 경우(아이디에 대한 변경점을 저장하기 위한 로직)
            if(nickname.find(imsi_record[1]) == nickname.end()){//uid를 map에서 찾았는데 없다!
                nickname.insert(pair<string,string>(imsi_record[1],imsi_record[2])); //uid와 닉네임을 묶어서 맵에 넣어주고
            }
            else{
                nickname[imsi_record[1]]=imsi_record[2];//아닌경우에는 uid에 대한 닉네임만 맵핑해준다
            }           
        }
        else if(imsi_record[0] == "Change"){//닉변할 경우
            nickname[imsi_record[1]]=imsi_record[2]; //uid에 대한 닉네임만 변경
        }
        record_el.push_back(imsi_record[0]);
        record_id.push_back(imsi_record[1]);//누가 무슨동작 했는지 넣어줌
    }
    for(int i=0;i<record_el.size();i++){//쭉 보면서
        string answer_push="";//들어왔냐 나갔냐 출력
        if(record_el[i]=="Enter"){
            answer_push = nickname[record_id[i]]+"님이 들어왔습니다.";
        }
        else if(record_el[i]=="Leave"){
            answer_push = nickname[record_id[i]]+"님이 나갔습니다.";
        }
        else{
            continue;
        }
        answer.push_back(answer_push);
    }
    return answer;
}
