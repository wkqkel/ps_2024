#include <iostream>
#include <queue>
#include <vector>

using namespace std;

#define P pair<long, long>

long n, m, s, e;
long a, b, c;

const long MX = 100002;
vector<P> vec[MX];

const long MOD = 1e9 + 9;

long dist[MX];

long dp[MX]; 

void get_dist(){
    priority_queue<P,vector<P>,greater<P>> q;
    
    for(int i = 0; i < MX; i++){
        dist[i] = 1e20;
    }
    
    q.push({0,s});
    dist[s] = 0;
    fill(dp,dp+MX,1);
    
    while(!q.empty()){
        long d = q.top().first;
        long cur = q.top().second;
        q.pop();
        
        if(dist[cur] != d) continue;
        
        for(int i = 0; i < vec[cur].size(); i++){
            long nxt = vec[cur][i].first;
            long nd = vec[cur][i].second + d;
            
            if(dist[nxt] == nd) {
                dp[nxt] += dp[cur];
                dp[nxt] %= MOD;
            }
            if(dist[nxt] > nd){
                q.push({nd,nxt});
                dist[nxt] = nd;
                dp[nxt] = dp[cur];
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> m >> s >> e;
    
    for(int i = 0; i < m; i++){
        cin >> a >> b >> c;
        vec[a].push_back({b,c});
        vec[b].push_back({a,c});
    }
    
    get_dist();
    
    cout << dp[e];
    return 0;
}
