#include <iostream>

using namespace std;

int n;
int dp[100020];
int arr[100020];

int main()
{
  
    cin >> n;
    for(int i = 1; i <= n; i++) cin >> arr[i];
    

    for(int i = 1; i <= n; i++) {
        dp[i] = max(dp[i-2], arr[i-1] + dp[i-3]) + arr[i];
    }
    
    cout << dp[n];
}
