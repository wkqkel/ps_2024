#include <string>
#include <vector>
#include <iostream>


using namespace std;
#define ll long long

ll dp[22];

ll fact(ll n){
    if(dp[n]) return dp[n];
    if(n < 1){
        return 1;
    }
    return dp[n] = n * fact(n-1);
}

vector<int> solution(int n, long long k) {
    vector<int> ans;
    vector<int> num;
    
    fact(n);
    for(int i = 1; i <= n; i++){
        num.push_back(i);
    }
   
    k--;
    
    dp[0] = 1;
    while(n--){
        int idx = k / dp[n];
        k %= dp[n];
        ans.push_back(num[idx]);
        num.erase(num.begin() + idx);
    }

    return ans;
}