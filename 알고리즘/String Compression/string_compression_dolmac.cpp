class Solution {
public:
    int compress(vector<char>& chars) {
        if(chars.size()==1){//길이 한자리면 그냥 출력하면 됨
            return chars.size();
        }
        else{
            string com_char = "";
            char val = chars[0];//비교 대상 문자
            int val_num = 1;//연속된 갯수
            for(int i=1;i<chars.size();i++){
                if(chars[i]==chars[i-1]){//바로 전과 문자가 같다면 연속된 갯수만 1늘려주고 통과
                    val_num++;
                }
                else{//바로 전과 문자가 다르다면
                    com_char += val;//문자 넣고
                    if(val_num != 1){//길이 넣고
                        com_char += to_string(val_num);
                    }
                    val = chars[i];//새로운 문자로 바꿔줌
                    val_num = 1;//새로운 문자로 바꼈으니 당연히 연속된 갯수는 1
                }
            }
            com_char += val;//마지막꺼도 더해줘야함
            if(val_num != 1){
                com_char += to_string(val_num);
            }
            chars.clear();//기존 벡터를 비워주고
            for(int i=0;i<com_char.length();i++){//압축한 벡터를 넣어줌
                chars.push_back(com_char[i]);
            }
            return chars.size();//압축한 벡터의 길이 리턴해줌
        }
    }
};
