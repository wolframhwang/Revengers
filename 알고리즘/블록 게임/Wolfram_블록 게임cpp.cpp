// 21.09.03 Solved By Wolfram Hwang
// 그래프라 오랜만에 CPP를 해보았어요.

#include <string>
#include <vector>
using namespace std;
vector<vector<vector<int> > > check =
        {{{1, 0, 0},
                 {1, 1, 1}},
         {{0,1},
                 {0,1},
                 {1,1}},
         {{1,0},
                 {1,0},
                 {1,1}},
         {{0,0,1},
                 {1,1,1}},
         {{0,1,0},
                 {1,1,1}}
        };
bool checkComp(vector<vector<int> > &board, vector<vector<int> > &comp, int h, int w){
    int Comp = -1;
    int min = 987654321;
    for(int i = 0; i < comp.size(); i++){
        for(int j = 0; j < comp[0].size(); j++){
            if(board[h + i][w + j] && comp[i][j]) {
                if(Comp == -1) { Comp = board[h + i][w + j];}
                else{ if (Comp != board[h + i][w + j]) return false;}
                if(min > h + i) { min = h + i;}
            }else if(!board[h + i][w + j] && !comp[i][j]) { }
            else { return false; }
        }
    }

    for(int i = 0; i < comp[0].size(); i++){
        if(board[min][w + i] == Comp) continue;
        for(int j = h - 1; j >= 0; j--){ if(board[j][w + i]) return false; }
    }

    for(int i = 0; i < comp.size(); i++){
        for(int j = 0; j < comp[0].size(); j++){
            if(board[h + i][w + j] && comp[i][j]) {
                board[h + i][w + j] = 0;
            }
        }
    }
    return true;
}
int solution(vector<vector<int>> board) {
    int answer = 0;
    int boardH = board.size();
    int boardW = board[0].size();
    int ans = 0;
    while(1) {
        bool isFind = false;
        for(int i = 0; i < check.size(); i++){
            int h = check[i].size();
            int w = check[i][0].size();
            for(int j = 0; j <= boardH - h; j++){
                for(int k = 0; k <= boardW - w; k++){
                    if(checkComp(board,check[i],j,k)){
                        ans += 1;
                        isFind = true;
                        break;
                    }
                }
            }
        }
        if(!isFind) break;
    }
    return ans;
}
