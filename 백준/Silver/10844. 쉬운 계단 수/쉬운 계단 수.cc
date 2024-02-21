#include <iostream>

using namespace std;

int n;
long dp[102][10];
int mod = 1e9;

int main()
{
    cin >> n;

    for(int i = 1; i <= 9; i++) dp[1][i] = 1;
    
    for(int i = 2; i <= n; i++){
        for(int j = 0; j <= 9; j++){
            if(j == 0) dp[i][j] = dp[i-1][1];
            else if(j == 9) dp[i][j] = dp[i-1][8];
            else dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
            
            dp[i][j] %= mod;
        }
    }
    
    long sum = 0;
    for(int i = 0; i <= 9; i++){
        sum += dp[n][i];
    }
    
    cout << sum  % mod;

    return 0;
}
