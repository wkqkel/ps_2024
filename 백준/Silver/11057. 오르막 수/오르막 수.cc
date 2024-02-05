#include <iostream>

using namespace std;

#define mod 10007

int n;
int d[10][1020];

/**
d[i][j]는 길이가 j이면서 첫 자리가 i일 때 가능한 갯수

d[0][1]~d[9][1]은 1개 
d[0][2]은 d[0][1]~d[9][1]의 합
d[1][2]는 d[1][1]~d[9][1]의 합

d[i][j] = d[i~9][j-1]의 합
*/ 
int main()
{
    cin >> n;
    
    for(int i = 0; i < 10; i++){
        d[i][1] = 1;
    }
    
    for(int i = 2; i <= n; i++){
        for(int j = 0; j < 10; j++){
            for(int k = j; k < 10; k++){
                d[j][i] += d[k][i-1];
            }
            d[j][i] %= mod;
        }
    }
     
    int res = 0;
    for(int i = 0; i < 10; i++){
        res += d[i][n];
    }
    
    cout << res % mod;
}
