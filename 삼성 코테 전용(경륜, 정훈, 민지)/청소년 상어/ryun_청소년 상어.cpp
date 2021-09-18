#include <iostream>
#include <algorithm>
using namespace std;

struct FISH{
    int x,y,dir;
    bool isLive;
};

int map[4][4];
int ans = 0;
FISH fish[17];

int dx[8] = {0,-1,-1,-1,0,1,1,1};
int dy[8] = {-1,-1,0,1,1,1,0,-1};

void mapCopy(int a[][4], int b[][4]){
    for(int i=0 ; i<4 ; i++){
        for(int j=0 ; j<4 ; j++){
            a[i][j] = b[i][j];
        }
    }
}
void fishCopy(FISH *a, FISH *b){
    for(int i=1 ; i<17 ; i++){
        a[i] = b[i];
    }
}
void move(){
    for(int i=1 ; i<17 ; i++){
        int tx = fish[i].x;
        int ty = fish[i].y;
        int td = fish[i].dir;
        int tLive = fish[i].isLive;
        if(!tLive) continue;
        int nx,ny;
        int nDir = td;

        for(int j=0 ; j<8 ; j++){
            nx = tx + dx[nDir];
            ny = ty + dy[nDir];
            if(nx >= 0 && ny >= 0 && nx < 4 && ny < 4){
                int now = map[ny][nx];
                if(now == 0){
                    nDir = (nDir+1) % 8;
                    continue;
                }

                map[ny][nx] = i;
                fish[i].x = nx;
                fish[i].y = ny;
                fish[i].dir = nDir;

                map[ty][tx] = now;
                fish[now].x = tx;
                fish[now].y = ty;
                break;
            }
            nDir = (nDir+1) % 8;
        }
    }
}
void solve(int x, int y, int dir, int sum){
    int tempMap[4][4];
    FISH tempFish[17];
    mapCopy(tempMap, map);
    fishCopy(tempFish, fish);

    move();

    int nx = x;
    int ny = y;

    while(1){
        nx += dx[dir];
        ny += dy[dir];

        if(nx >= 0 && ny >= 0 && nx < 4 && ny < 4){
            int now = map[ny][nx];
            if(now == -1) continue;

            int nDir = fish[now].dir;
            fish[now].isLive = false;
            map[y][x] = -1;
            map[ny][nx] = 0;

            solve(nx, ny, nDir, now + sum);

            fish[now].isLive = true;
            map[y][x] = 0;
            map[ny][nx] = now;
        }else break;
    }
    ans = max(ans, sum);
    fishCopy(fish, tempFish);
    mapCopy(map,tempMap);
    return;
}

int main(){
    int a,b;
    for(int i=0 ; i<4 ; i++){
        for(int j=0 ; j<4 ; j++){
            scanf("%d%d",&a,&b);
            map[i][j] = a;
            fish[a].x = j;
            fish[a].y = i;
            fish[a].dir = b-1;
            fish[a].isLive = true;
        }
    }

    int start = map[0][0];
    int sDir = fish[start].dir;
    fish[start].isLive = false;
    map[0][0] = 0;
    solve(0, 0, sDir, start);

    printf("%d\n", ans);

    return 0;
}