#include <iostream>
#include <vector>

using namespace std;

const int MX = 100002;
vector<pair<int, int>> vec[MX];
int v;
long long dist[MX];

void dfs(int cur, int par){
    for(auto p : vec[cur]){
        auto [child, d] = p;
        if(child == par) continue;
        dist[child] = d;
        dist[child] += dist[cur]; 
        dfs(child, cur);
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> v;
    
    for(int i = 0; i < v; i++){
        int a , b , d;
        cin >> a;
   
        while(1){
            cin >> b;
            if(b == -1) break;
            cin >> d;
            vec[a].push_back({b, d});
        }
    }
    
    
    int r = 1;
    dfs(r, -1);
    int mx = -1;
    for(int i = 1; i <= v; i++){
        if(mx <= dist[i]){
            mx = dist[i];
            r = i;
        }
        dist[i] = 0;
    }
    dfs(r, -1);
     for(int i = 1; i <= v; i++){
        if(mx <= dist[i]){
            mx = dist[i];
            r = i;
        }
    }
   
    cout << mx;
    return 0;
}