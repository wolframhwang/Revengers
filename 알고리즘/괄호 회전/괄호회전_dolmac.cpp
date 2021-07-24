#include <string>
#include <vector>
#include <stack>
using namespace std;

int solution(string s) {
    int answer = 0;
    if(s.length()%2 == 1){
        return 0;
    }
    for(int i=0;i<s.length();i++){
        stack<char> imsi;
        int isok = 1;
        for(int j=0;j<s.length();j++){
            if(s[j] == '[' || s[j] == '(' || s[j] == '{'){
                imsi.push(s[j]);
            }
            else{
                switch(s[j]){
                    case ']':
                        if(imsi.empty() || imsi.top() != '['){
                            isok = 0;
                        }
                        else{
                            imsi.pop();
                        }
                        break;
                    case ')':
                        if(imsi.empty() || imsi.top() != '('){
                            isok = 0;
                        }
                        else{
                            imsi.pop();
                        }
                        break;
                    case '}':
                        if(imsi.empty() || imsi.top() != '{'){
                            isok = 0;
                        }
                        else{
                            imsi.pop();
                        }
                        break;
                }
                
            }
            
        }
        if(isok == 1){
            answer++;
        }
        char a= s[0];
        for(int i=1;i<s.length();i++){
            s[i-1] = s[i];
        }
        s[s.length()-1] = a;
    }
    return answer;
}
