#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MX = 20002;

int v, e, k;
vector<pair<int, int>> vec[MX];
int dist[MX];
bool ch[MX];

void get_dist(int s){
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    
    for(int i = 0; i < MX; i++) dist[i] = 1e9;
    
    dist[s] = 0;
    pq.push({0,s});
    
    while(!pq.empty()){
        int cur = pq.top().second;
        int d = pq.top().first;
        pq.pop();
        
        if(ch[cur]) continue;
        ch[cur] = true;
        
        for(int i = 0; i < vec[cur].size(); i++){
            int nxt = vec[cur][i].first;
            int nd = d + vec[cur][i].second;
            
            if(dist[nxt] > nd) {
                dist[nxt] = nd;
                pq.push({nd, nxt});
            }
        }
    }
}

int main()
{
    cin >> v >> e >> k;
    
    for(int i = 0; i < e; i++){
        int a, b, c;
        cin >> a >> b >> c;
        vec[a].push_back({b,c});
        // vec[b].push_back({a,c});
    }
    
    get_dist(k);
    
    for(int i = 1; i <= v; i++){
        int d = dist[i];
        if(d == 1e9) cout << "INF" << '\n';
        else cout << d << '\n';
    }
    return 0;
}
