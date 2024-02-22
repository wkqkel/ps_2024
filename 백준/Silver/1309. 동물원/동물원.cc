#include <iostream>

using namespace std;

int n;
long dp[100002][3];
int mod = 9901;

int main()
{
    cin >> n;
    for(int i = 0; i < 3; i++) dp[1][i] = 1;
 
    for(int i = 2; i <= n; i++){
        dp[i][0] = dp[i-1][0] + dp[i-1][1]+dp[i-1][2];
        dp[i][1] = dp[i-1][0] + dp[i-1][2];
        dp[i][2] = dp[i-1][0] + dp[i-1][1];
        
        dp[i][0] %= mod;
        dp[i][1] %= mod;
        dp[i][2] %= mod;
    }
    
    long res = dp[n][0] + dp[n][1] + dp[n][2];
    
    cout << res % mod;
    
    return 0;
}
