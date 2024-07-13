#include <iostream>

using namespace std;


// d[x]는 x를 1,2,3의 총합으로 나타내는 법 
int dp[20];
int main()
{
    
    int t;
    cin >> t;
    dp[1] = 1;
    dp[2] = 2;
    dp[3] = 4;
    while(t--){
        int n;
        cin >> n;
        for(int i = 4; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        cout << dp[n] << "\n";
    }
 
    return 0;
}