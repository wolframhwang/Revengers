//21.09.05 Solved By wolfram Hwang
// 포인트 기준으로 완전탐색함
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int ans = 9;
int road[201];
bool visit[21];
bool check(vector<int>& weak) { for (int i = 0; i < weak.size(); i++) if (road[weak[i]]) return false; return true;}
void recur(int N, int idx, vector<int>& weak, vector<int>& dist) {
    if (ans <= idx) return;
    if (check(weak)) { if (ans > idx) ans = idx; return; }
    if (idx == dist.size()) return;    
    for (int i = 0; i < weak.size(); i++) {
        if (visit[i]) continue;
        int start = weak[i];
        int end = weak[i] + dist[idx];
        vector<int> del;
        if (end < N) {
            for (int j = i; j < weak.size(); j++) {
                if (weak[j] <= end) {
                    if (road[weak[j]]) {
                        road[weak[j]] = 0;
                        del.push_back(weak[j]);
                    }
                }
                else { break; }
            }
        }
        else {
            for (int j = i; j < weak.size(); j++) {
                if (road[weak[j]]) {
                    road[weak[j]] = 0;
                    del.push_back(weak[j]);
                }
            }
            for (int j = 0; j < i; j++) {
                if (weak[j] <= (end % N)) {
                    if (road[weak[j]]) {
                        road[weak[j]] = 0;
                        del.push_back(weak[j]);
                    }
                }
            }
        }
        visit[i] = true;
        //for (int j = 0; j < del.size(); j++) { cout << del[j] << ' '; }cout << '\n';
        if(del.size() > 0)
            recur(N, idx + 1, weak, dist);
        visit[i] = false;
        for (int j = 0; j < del.size(); j++) {
            road[del[j]] = 1;
        }
    }
}
bool comp(int a, int b) { return a > b; }
int solution(int n, vector<int> weak, vector<int> dist) {
    int answer = 0;
    sort(dist.begin(), dist.end(), comp);
    for (int i = 0; i < weak.size(); i++) road[weak[i]] = 1;
    recur(n, 0, weak, dist);
    if (ans == 9) answer = -1;
    else answer = ans;
    return answer;
}
