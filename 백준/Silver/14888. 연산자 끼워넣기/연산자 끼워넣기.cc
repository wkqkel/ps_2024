#include <iostream>

/**
#boj14888 연산자 끼워넣기

##설계

1. 완탐 
최대 4^11 = 2^2^10 => 1024^2 => 가능
*/

using namespace std;

const int MX = 20;
int n;
int arr[MX];
int cnts[MX];
int ops[MX];

int mn = 1e9;
int mx = -1e9;

void recur(int cur){
    if(cur == n -1){
        int res = arr[0];
        for(int i = 1; i <= n - 1; i++){
            int nxt = arr[i];
            int op = ops[i - 1];
            if(op == 0) {
                res += nxt;
            }
            else if(op == 1){
                res -= nxt;
            }
            else if(op == 2){
                res *= nxt;
            }
            else if(op == 3){
                res /= nxt;
            }
        }
        mn = min(mn, res);
        mx = max(mx, res);
        return;
    }
    for(int i = 0; i < 4; i++){
        if(cnts[i] <= 0) continue;
        cnts[i]--;
        ops[cur] = i;
        recur(cur+1);
        cnts[i]++;
    }
}

int main()
{
    cin >> n;
    for(int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    for(int i = 0; i < 4; i++){
        cin >> cnts[i];
    }

    recur(0);
    
    cout << mx << "\n" << mn;
    return 0;
}