#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> triangle) {
    int answer = 0;
    for(int i=1;i<triangle.size();i++){
        triangle[i][0]=triangle[i-1][0]+triangle[i][0];
        triangle[i][i]=triangle[i][i]+triangle[i-1][i-1];
        if(i>1){
            for(int j=1;j<i;j++){
                triangle[i][j] = max(triangle[i-1][j-1]+triangle[i][j],triangle[i-1][j]+triangle[i][j]);
            }
        }
    }
    for(int i=0;i<triangle.size();i++){
        if(answer<triangle[triangle.size()-1][i]){
            answer = triangle[triangle.size()-1][i];
        }
    }
    return answer;
}
