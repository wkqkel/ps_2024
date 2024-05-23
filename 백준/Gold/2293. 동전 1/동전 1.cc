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
    dp[0] = 1;
    for(int i = 0; i < n; i++){
        int coin = coins[i];
        for(int j = 1; j <= 10000; j++){
            if(j-coin <0) continue;
            dp[j] += dp[j-coin];
        }
    }
    
    cout << dp[k];

    return 0;
}
