#include <iostream>

using namespace std;

int n, k;
int arr[102];
int dp[10002];
int main()
{
    cin >> n >> k;
    
    for(int i = 0; i < n; i++) cin >> arr[i];
    
    for(int i = 0; i <= k; i++) dp[i] = 1e9;
    
    dp[0] = 0;
    for(int i = 0; i < n; i++){
        for(int j = arr[i]; j <= k; j++){
            dp[j] = min(dp[j], dp[j-arr[i]]+1);
        }
    }
    
    if(dp[k] == 1e9) cout << -1;
    else cout << dp[k];
    
    return 0;
}
