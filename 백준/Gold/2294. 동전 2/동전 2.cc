#include <iostream>

using namespace std;

const int MX = 100002;
int coins[102];
int dp[MX];

int main(){
    int n, k;
    cin >> n >> k;
    
    for(int i = 0; i < n; i++){
        cin >> coins[i];
    }
    fill(dp, dp+MX, 1e9);
    dp[0] = 0;
    for(int i = 0; i < n; i++){
        int coin = coins[i];
        for(int j = coin; j <= 10000; j++){
            dp[j] = min(dp[j-coin] + 1, dp[j]);
        }
    }
    if(dp[k] == 1e9) cout << -1;
    else cout << dp[k];

    return 0;
}
