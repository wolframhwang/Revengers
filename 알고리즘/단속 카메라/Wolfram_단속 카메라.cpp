// 21/08/10 Solved by Wolfram Hwang
// 그리디한 문제이긴한데, 나가는 순서대로 정렬해두고, 가장 먼저 나가는 녀석보다,
// 조금 일찍 들어오는 녀석들을 개수를 세면 됨
#include <vector>
#include <algorithm>

using namespace std;

bool comp(vector<int> a, vector<int> b) {
	if (a[1] < b[1]) {
		return true;
	}
	return false;
}
int solution(vector<vector<int>> routes) {
	int ans = 0;
	sort(routes.begin(), routes.end(), comp);
	int i = 0;
	while (!routes.empty()) {
		int p = routes[0][1];
		for (int i = 0; i < routes.size(); i++) {
			if (routes[i][0] <= p) {
				routes.erase(routes.begin() + i);
				i -= 1;
			}
		}
		ans += 1;
	}
	return ans;
}
