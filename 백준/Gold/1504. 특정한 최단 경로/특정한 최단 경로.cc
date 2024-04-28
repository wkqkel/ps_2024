#include <iostream>
#include <queue>
#include <vector>

using namespace std;
/**
v1, v2 두 경로 반드시 지나기
1 -> v1 -> v2 -> n
1 -> v2 -> v1 -> n 
중 최소

무방향 그래프이므로
[v1 -> 1] + [v2 -> n] + [v1 -> v2]
[v2 -> 1] + [v1 -> n] + [v1 -> v2]

시간복잡도 O(ElogV)
2e5 * 10 = 2e6 가능
*/
int n, m; 
int a, b, c;
int v1, v2;

#define P pair<int, int>
const int MX = 802;
vector<P> vec[MX];
long dist[MX];
bool ch[MX];

void get_dist(int s){
    priority_queue<P, vector<P>, greater<P>> pq;
    
    for(int i = 0; i < MX; i++){
        dist[i] = 2e9;
        ch[i] = 0;
    }
    
    pq.push({0, s});
    dist[s] = 0;
    
    while(pq.size()){
        int cur = pq.top().second;
        int d= pq.top().first;
        pq.pop();
        
        if(ch[cur]) continue;
        ch[cur] = 1;
        
        for(int i = 0; i < vec[cur].size(); i++){
            int nxt = vec[cur][i].first;
            int nd = vec[cur][i].second + d;
            
            if(dist[nxt] > nd){
                dist[nxt] = nd;
                pq.push({nd, nxt});
            }
        }
    }
}

int main()
{   
    cin >> n >> m;
    
    for(int i = 0; i < m; i++){
        cin >> a >> b >> c;
        
        vec[a].push_back({b,c});
        vec[b].push_back({a,c});
    }
    
    cin >> v1 >> v2;
    
    get_dist(v1);
    long d1 = dist[1];
    long d2 = dist[n];
    long d3 = dist[v2];
    
    get_dist(v2);
    long d4 = dist[1];
    long d5 = dist[n];
    
    long res = min(d1 + d5 + d3, d2 + d4 + d3);
    
    if(res >= 2e9) cout << -1;
    else cout << res;
}
