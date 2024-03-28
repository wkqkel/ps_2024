#include <iostream>
#include <vector>

using namespace std;

pair<int,int> arr[102];

long dp[100002];

int main()
{
    int n,k;
    cin >> n >> k;
    
    for(int i = 0; i < n; i++){
        int w, v;
        cin >> w >> v;
        
        arr[i] = {w,v};
    }
    
    for(int i = 0; i < n; i++){
        int w = arr[i].first;
        int v = arr[i].second;
        
        for(int j = k; j >= w; j--){
            dp[j] = max(dp[j], dp[j-w]+v);
        }
    }
    
    cout << dp[k];
    
    return 0;
}
