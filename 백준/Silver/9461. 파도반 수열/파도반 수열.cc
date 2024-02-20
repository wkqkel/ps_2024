#include <iostream>

using namespace std;

long dp[102];

int main()
{
    int n;
    cin >> n;
    dp[1] = 1;
    dp[2] = 1;
    dp[3] = 1;
    dp[4] = 2;
    for(int i = 5; i <= 102; i++){
        dp[i] = dp[i-5] + dp[i-1];
    }
    
    while(n--){
        int v;
        cin >> v;
        cout << dp[v] << '\n';
    }

    return 0;
}
