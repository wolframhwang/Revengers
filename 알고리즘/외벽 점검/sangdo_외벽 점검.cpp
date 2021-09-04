#include <string>
#include <vector>
#include<iostream>
#include<algorithm>
#include<cstring>

using namespace std;

vector<int> *d;
vector<int> *w;
int len;

int getcnt(){
    int n = (*w).size();
    int m = (*d).size();
    
    int remain;
    int ret = 0x3f3f3f3f;
    for(int i = 0 ; i < n; ++i){
        int std = (*w)[i];
        int pos = 0;
        int prev = 0;
        int cnt = 0;
        for(int j = 0; j < m; ++j){
            remain = (*d)[j];
            pos =  (len+(*w)[(i+cnt)%n] - std)%len;
            prev = pos;
            while(cnt < n && remain >=  pos - prev){
                cnt++;
                remain -= pos - prev;
                prev = pos;
                pos = (len + (*w)[(i+cnt)%n] - std)%len;
            }
            
            if(cnt == n) {
                if(j+1 < ret) {
                    ret = j+1;
                }
            }
        }
    }
    return ret;
}

int permutation(int n){
    if(n == (*d).size()){
       return getcnt();     
    }
    int sz = (*d).size();
    int ret = 0x3f3f3f3f;
    for(int i = n; i < sz; ++i){
        int temp = (*d)[n];
        (*d)[n] = (*d)[i];
        (*d)[i] = temp;
        int res = permutation(n+1);
        if(res < ret) ret = res;
        
        temp = (*d)[n];
        (*d)[n] = (*d)[i];
        (*d)[i] = temp;
    }
    return ret;
}

int solution(int n, vector<int> weak, vector<int> dist) {
    d = &dist;
    w = &weak;
    len = n;
    int answer = permutation(0);
    return answer ==  0x3f3f3f3f ? -1 : answer;
}
