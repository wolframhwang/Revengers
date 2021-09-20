#include <iostream>
#include <vector>
using namespace std;

vector<int> ballList;
int dx[4] = {0,0,-1,1};
int dy[4] = {-1,1,0,0};
int dir[4] = {2, 1, 3, 0};

int n,m,d,s;
int map[51][51];
int ans = 0;
int ballCnt = 0;
void blizzard(int d, int s){
    for(int i=1 ; i<=s ; i++){
        int ny = n/2 + dy[d]*i;
        int nx = n/2 + dx[d]*i;
        if(map[ny][nx] == 0) continue;
        map[ny][nx] = 0;
        ballCnt--;
    }
}

void serializeBall(){
    ballList.clear();
    int len = 1;
    int d = 0;
    int idx = 0;
    int y = n/2;
    int x = n/2;
    while(idx != ballCnt){
        for(int i=0 ; i<2 ; i++){
            for(int j=0 ; j<len ; j++){
                y += dy[dir[d]];
                x += dx[dir[d]];
                if(map[y][x]){
                    ballList.push_back(map[y][x]);
                    idx++;
                }
                if(idx == ballCnt) return;
            }
            d = (d+1) % 4;
        }
        len++;
    }
}
void deserializeBall(){
    for(int i=0 ; i<n ; i++){
        for(int j=0 ; j<n ; j++) map[i][j] = 0;
    }
    int d = 0;
    int y = n/2;
    int x = n/2;
    int len = 1;
    int idx = 0;
    while(idx != ballList.size()){
        for(int i=0 ; i<2 ; i++){
            for(int j=0 ; j<len ; j++){
                y += dy[dir[d]];
                x += dx[dir[d]];
                map[y][x] = ballList[idx++];

                if(idx == ballList.size()) return;
            }
            d = (d+1) % 4;
        }
        len++;
    }
}

void explodeBall(){

    serializeBall();
    bool flag = true;

    while(flag){
        vector<int> afterBall;
        flag = false;
        for(int start = 0; start < ballList.size() ; start++){
            int end = start;
            while(end < ballList.size() && ballList[end] == ballList[start]) end++;

            if(end - start >= 4){
                ans += ballList[start] * (end-start);
                flag = true;

            }else{
                for(int i=start ; i<end ; i++){
                    afterBall.push_back(ballList[i]);
                }
            }
            start = end - 1;
        }
        ballList = afterBall;
    }
    
}
void changeBall(){
    vector<int> changeBall;
    
    for(int start=0 ; start < ballList.size() ; start++){
        if(changeBall.size() >= n*n) break;
        int end = start;
        while(end < ballList.size() && ballList[end] == ballList[start]) end++;
        
        changeBall.push_back(end - start);
        changeBall.push_back(ballList[start]);
        start = end - 1;
    }
    for(int i=changeBall.size() ; i>=n*n ; i--) changeBall.pop_back();

    ballList = changeBall;
    ballCnt = ballList.size();
}
int main(){
    scanf("%d %d",&n,&m);

    for(int i=0 ; i<n ; i++){
        for(int j=0 ; j<n ; j++){
            scanf("%d",&map[i][j]);
            if(map[i][j]) ballCnt++;
        }
    }

    for(int i=0 ; i<m ; i++){
        scanf("%d %d",&d,&s);
        blizzard(d-1, s);
        explodeBall();
        changeBall();
        deserializeBall();
    }

    printf("%d\n", ans);
}