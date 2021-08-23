/*
근-본 cpp 풀이
해당 시간이 되었을 때, 큐에 담긴것 중에서 가장 짧은 일을 처리하는 것이 가장 최적이다.(그리디)
pq에 현재 시간까지의 작업을 모두 넣어놓고 가장 일이 짧은 task를 처리한 뒤에 현재 시간을 늘린다!
*/
#include <string>
#include <cmath>
#include <algorithm>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;
struct cmp{
    bool operator()(vector<int>&v1, vector<int>& v2){
        if(v1[0] != v2[0]) return v1[0] < v2[0];
        return v1[1] < v2[1];
    }
};

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    int nowTime = 0;
    priority_queue<pair<int,int>> pq;
    sort(jobs.begin(), jobs.end(),cmp());
    for(int i = 0; i < jobs.size(); ) {
        if(pq.size() == 0) nowTime = max(nowTime, jobs[i][0]);
        while(i < jobs.size() && jobs[i][0] <= nowTime) pq.push({-jobs[i][1], jobs[i][0]}), i++;
        answer += -pq.top().first + nowTime - pq.top().second;
        nowTime += -pq.top().first;
        pq.pop();
    }
    while(pq.size()) {
        answer += -pq.top().first + nowTime - pq.top().second;
        nowTime += -pq.top().first;
        pq.pop();
    }
    
    return answer/jobs.size();
}
