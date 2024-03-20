#include <string>
#include <vector>
#include <iostream>

using namespace std;

int dp[502][502];

int solution(vector<vector<int>> arr) {
    int n = arr.size();

    for(int i = 0; i < n; i++){
        for(int j = 0; j <= i; j++){
          dp[i][j] = arr[i][j] + max(dp[i-1][j-1], dp[i-1][j]);
        }
    }
    
    int mx = -1;
    for(int i = 0; i < n; i++){
        mx = max(dp[n-1][i],mx);
    }

    return mx;
}