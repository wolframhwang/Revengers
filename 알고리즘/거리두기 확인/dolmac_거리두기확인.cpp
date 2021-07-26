#include <string>
#include <vector>
//응애 노가다쨩
using namespace std;

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    for(int k = 0; k<5;k++){
        int result = 1;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(places[k][i][j] == 'P'){
                    if((i-1>=0 && places[k][i-1][j] == 'P') || (j-1>=0 && places[k][i][j-1] == 'P') || 
                      (i+1<=4 && places[k][i+1][j] == 'P') || (j+1<=4 && places[k][i][j+1] == 'P') ){//상하좌우 1칸
                        result = 0;
                        break;
                    }
                    if(i-2>=0 && (places[k][i-2][j] == 'P' && places[k][i-1][j] != 'X')){//상하좌우 두칸
                        result = 0;
                        break;
                    }
                    if(i+2<=4 && (places[k][i+2][j] == 'P' && places[k][i+1][j] != 'X')){
                        result = 0;
                        break;
                    }
                    if(j-2>=0 && (places[k][i][j-2] == 'P' && places[k][i][j-1] != 'X')){
                        result = 0;
                        break;
                    }
                    if(j+2>=0 && (places[k][i][j+2] == 'P' && places[k][i][j+1] != 'X')){
                        result = 0;
                        break;
                    }
                    if((i-1>=0 && j-1>=0) && places[k][i-1][j-1] == 'P'){//좌상,좌하, 우상, 우하
                        if(places[k][i-1][j] != 'X' || places[k][i][j-1] != 'X'){
                            result = 0;
                            break;
                        }
                    }
                    if((i-1>=0 && j+1<=4) && places[k][i-1][j+1] == 'P'){
                        if(places[k][i-1][j] != 'X' || places[k][i][j+1] != 'X'){
                            result = 0;
                            break;
                        }
                    }
                    if((i+1<=4 && j-1>=0) && places[k][i+1][j-1] == 'P'){
                        if(places[k][i+1][j] != 'X' || places[k][i][j-1] != 'X'){
                            result = 0;
                            break;
                        }
                    }
                    if((i+1<=4 && j+1<=4) && places[k][i+1][j+1] == 'P'){
                        if(places[k][i+1][j] != 'X' || places[k][i][j+1] != 'X'){
                            result = 0;
                            break;
                        }
                    }
                }
            }
            if(result == 0){
                break;
            }
        }
        answer.push_back(result);
    }
    
    return answer;
}
