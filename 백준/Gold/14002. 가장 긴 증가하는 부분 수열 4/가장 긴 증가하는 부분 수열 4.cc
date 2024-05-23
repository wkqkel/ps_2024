#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


vector<pair<int, int>> vec;
int dp[1002];
int arr[1002];

int main()
{
    int n;
    cin >> n;
    for(int i = 0; i < n; i++) cin >> arr[i];
    
    for(int i = 0; i < n; i++){
        int mx = 0;
        for(int j = 0; j < i; j++){
            if(arr[j] >= arr[i]) continue;
            mx = max(dp[j], mx);
        }
        dp[i] = max(dp[i], mx + 1);
    }
    
    int mx = 0;
    int idx = -1;
    for(int i = 0; i < n; i++){
        if(dp[i] > mx){
            mx = dp[i];
            idx = i;
        }
    }

    int cur = mx;
    vector<int> path;
    for(int i = idx; i >= 0; i--){
        if(cur == dp[i]){
            path.push_back(arr[i]);
            cur--;
        }
    }
    
    reverse(path.begin(), path.end());
    cout << path.size() << "\n";
    for(int v: path) cout << v << " ";
    
    return 0;
}
