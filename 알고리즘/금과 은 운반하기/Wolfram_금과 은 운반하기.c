// 21.09.15 Solved By Wolfram Hwang
// 파라 메트릭 서치문제

#include <stdio.h>

#define MIN(a, b) a < b ? a : b
typedef long long ll;

long long solution(int a, int b, int g[], size_t g_len, int s[], size_t s_len, int w[], size_t w_len, int t[], size_t t_len) {
    ll start = 0;
    ll end = 1e16;
    int i = 0;
    while (start < end) {
        ll mid = (start + end) / 2;

        int totalSum = 0;
        int Gold = 0;
        int Silver = 0;

        for (i = 0; i < g_len; i++){
            if(mid >= t[i]) {
                ll Remain = mid - t[i];//마지막 운전 할만큼 뺴주기
                ll time = ((Remain / 2) / t[i]) + 1;// 운전 횟수
                totalSum += MIN(time * w[i], g[i] + s[i]);
                Gold += MIN(time * w[i], g[i]);
                Silver += MIN(time * w[i], s[i]);
            }
        }
        if (totalSum >= (a + b) && Gold >= a && Silver >= b) {
            end = mid;
        }else {
            start = mid + 1;
        }
    }
    return start;
}
