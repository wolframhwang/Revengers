/*
O(N)
오랜만에 근본 cpp
구간이 [s,e]로 들어왔을때, 구간을 2*w+1단위로 쪼개서 설치하는것이 무조건 최소이다.
어짜피, e+1에 기지국이 설치되어있을것이기 때문에, [s,e]에 설치한 기지국은 다음에 쪼개지는 구간에 영향을 주지 못한다.
*/

#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int install(int st, int ed, int w) {
    if(st > ed) return 0;
    int res = (ed+1-st)/(2*w+1);
    return (ed+1-st)/(2*w+1) + !!((ed+1-st)%(2*w+1));
}

int solution(int n, vector<int> stations, int w)
{
    int answer = 0;
    int s = 1;
    for(int i = 0; i < stations.size(); ++i) {
     	answer += install(s, stations[i]-w-1,w);
        s = stations[i]+w+1;
    }
    answer += install(stations[stations.size()-1]+w+1, n,w);

    return answer;
}
