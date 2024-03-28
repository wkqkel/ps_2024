#include <iostream>

using namespace std;

int n, m;
const int MX = 1002;
int dp[MX][MX];

int main()
{
  
    cin >> n >> m;
    
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= m; j++){
            cin >> dp[i][j];
            dp[i][j] += max(dp[i-1][j], max(dp[i-1][j-1], dp[i][j-1]));
        }
    }
    
    int mx = -1;
    
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= m; j++){
           mx =max(mx, dp[i][j]);
        }
    }
    
    cout << mx;
    return 0;
}
