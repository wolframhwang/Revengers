using namespace std;
int gcd(int a, int b){
    while(b!=0){
        int c=a%b;
        a=b;
        b=c;
    }
    return a;
}
long long solution(int w,int h) {//가로 + 세로 - 최대공약수를 하면 나온다는데 머선짓을 해도 안풀려서 답봤다 응애
    long long w_long = w;
    long long h_long = h;
    long long answer = 1;
    long long minus_val = (w+h) - gcd(w,h);
    answer = w_long * h_long - minus_val;
    return answer;
}
