#include <iostream>
#include <vector>

using namespace std;

int n;
const int MX = 130000;
vector<int> vec[MX];
using ll = long long;

bool visited[MX];
int counts[MX];

int res = 0;

ll dfs(int cur){
    visited[cur] = 1;
    ll sum = counts[cur];

    for(int i = 0; i < vec[cur].size(); i++){
        int nxt = vec[cur][i];
        
        if(visited[nxt]) continue;
        
        sum += dfs(nxt);
    }
    
    return sum < 0 ? 0 : sum;
}

int main()
{
    cin >> n;
    
    for(int i = 2; i <= n; i++){
        char t;
        int a,p;
        
        cin >> t >> a >> p;
    
        vec[i].push_back(p);
        vec[p].push_back(i);
        
        counts[i] = a;
        
        if(t=='W') counts[i] *= -1;
    }
    
    cout << dfs(1);

    return 0;
}
