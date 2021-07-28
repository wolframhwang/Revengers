// 21/07/29 Solved by Wolfram Hwang
// 간단한 DP문제입니다.

#include <string>
#include <vector>

using namespace std;
int dp[500][500];
int mx(int a, int b) {
	return a > b ? a : b;
}
int solution(vector<vector<int>> triangle) {
	int answer = 0;
	dp[0][0] = triangle[0][0];
	for (int i = 1; i < triangle.size(); i++) {
		for (int j = 0; j < triangle[i].size(); j++) {
			if (j == 0) {
				dp[i][j] = triangle[i][j] + dp[i - 1][j];
			}
			else if (j == triangle[i].size() - 1) {
				dp[i][j] = triangle[i][j] + dp[i - 1][j - 1];
			}
			else {
				dp[i][j] = triangle[i][j] + mx(dp[i - 1][j - 1], dp[i - 1][j]);
			}
			answer = answer < dp[i][j] ? dp[i][j] : answer;
		}
	}
	
	return answer;
}
