#include <iostream>

using namespace std;

int n;
int arr[1002];
int dp[1002];

int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++) cin >> arr[i];
   
    for(int i = 0; i <n;i++){
      int mx = 0;
      for(int j = 0; j < i; j++){
          if(arr[j] < arr[i] && mx < dp[j]) mx = dp[j] ;
      }
      dp[i] = mx + 1;
    }
    int res = -1;
    for(int i = 0; i < n; i++){
        res = max(res, dp[i]);
    }
    
    cout << res;

    return 0;
}
