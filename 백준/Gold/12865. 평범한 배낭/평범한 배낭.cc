#include <iostream>
#include <vector>
using namespace std;


vector<pair<int, int>> vec;
int dp[100002];

int main()
{
    int n, m;
    cin >> n >> m;
    for(int i = 0; i < n; i++){
        int w,v;
        cin >> w >> v;
        vec.push_back({w,v});
    }
    
    for(auto [w,v]: vec){
        for(int x = m; x >= 0; x--){
            if(x-w < 0) continue;
            dp[x] = max(dp[x], dp[x-w]+v);
        }
    }
    
    cout << dp[m];
    return 0;
}
