#include <iostream>
#include <string>
#include <stack>
using namespace std;

int solution(string s)
{
    int answer = -1;
    stack<char> s_stack;
    if(s.length()%2==1){
        return 0;
    }
    s_stack.push(s[0]);
    for(int i=1;i<s.length();i++){
        if(!s_stack.empty() && s_stack.top() == s[i]){
            s_stack.pop();
        }
        else{
            s_stack.push(s[i]);
        }
    }
    if(s_stack.empty()){
        answer=1;
    }
    else{
        answer = 0;
    }
    return answer;
}
