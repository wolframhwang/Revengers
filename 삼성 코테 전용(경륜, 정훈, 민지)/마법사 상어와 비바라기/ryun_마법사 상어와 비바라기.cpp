#include <iostream>
#include <vector>
using namespace std;

int dr[8] = {0,-1,-1,-1,0,1,1,1};
int dc[8] = {-1,-1,0,1,1,1,0,-1};
int n,m;
int A[51][101];
bool cIdx[51][101];
int d,s;
vector<pair<int, int> > cloud;

int check(int c){
    if(c < 0) return n-1;
    else if(c >= n) return 0;
    else return c;
}
void initIdx(){
    for(int i=0 ; i<n ; i++){
        for(int j=0 ; j<n ; j++) cIdx[i][j] = false;
    }
}
void move(int d, int s){

    initIdx();

    for(int i=0 ; i<cloud.size() ; i++){
        int nr = cloud[i].first;
        int nc = cloud[i].second;

        for(int j=0 ; j<s ; j++){
            nr = check(nr + dr[d]);
            nc = check(nc + dc[d]);
        }
        cloud[i].first = nr;
        cloud[i].second = nc;
        cIdx[nr][nc] = true;
    }
}
void rain(){
    for(int i=0 ; i<cloud.size() ; i++){
        int r = cloud[i].first;
        int c = cloud[i].second;
        A[r][c]++;
    }
}
void bug(){
    for(int i=0 ; i<cloud.size(); i++){
        int r = cloud[i].first;
        int c = cloud[i].second;
        int water = 0;
        for(int j=1 ; j<8 ; j+=2){
            int nr = r + dr[j];
            int nc = c + dc[j];
            if(nr < 0 || nc < 0 || nr >= n || nc >= n || A[nr][nc] < 1) continue;
            water++;
        }
        A[r][c] += water;
    }
    cloud.clear();
}
void make(){
    for(int i=0 ; i<n ; i++){
        for(int j=0 ; j<n ; j++){
            if(A[i][j] >= 2 && !cIdx[i][j]){
                cloud.push_back(make_pair(i,j));
                A[i][j] -= 2;
            }
        }
    }

    initIdx();
    for(int i=0 ; i<cloud.size() ; i++){
        int r = cloud[i].first;
        int c = cloud[i].second;
        cIdx[r][c] = true;
    }
    
}

int result(){
    int ans = 0;
    for(int i=0 ; i<n ; i++){
        for(int j=0 ; j<n ; j++) ans += A[i][j];
    }
    return ans;
}

int main(){
    scanf("%d%d",&n,&m);

    for(int i=0 ; i<n ; i++){
        for(int j=0 ; j<n ; j++){
            scanf("%d",&A[i][j]);
        }
    }
    for(int i=0 ; i<2 ; i++){
        for(int j=0 ; j<2 ; j++){
            cloud.push_back(make_pair(n-1-i, j));
            cIdx[n-1-i][j] = true;
        }
    }

    for(int i=0 ; i<m ; i++){
        scanf("%d%d",&d,&s);
        move(d-1, s);
        rain();
        bug();
        make();
    }
    printf("%d\n", result());
}