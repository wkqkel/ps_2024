#include <iostream>
#include <queue>
#include <vector>

using namespace std;

int v, e, k;
bool ch[20020];
int dist[20020];

vector<pair<int, int>> vec[20020];

void get_dist(int st){
    priority_queue<pair<int,int>, vector<pair<int,int>>,greater<pair<int,int>>> pq;
    
    for(int i =0; i < 20020; i++) dist[i] = 2e9;
    
    pq.push({0,st});
    dist[st] = 0;
    
    while(!pq.empty()){
        int cur = pq.top().second;
        int d = pq.top().first;
        pq.pop();
        
        if(ch[cur]) continue;
        ch[cur] = true;
        
        for(int i = 0; i < vec[cur].size();i++){
            int nxt = vec[cur][i].first;
            int nd = d + vec[cur][i].second;
            
            if(nd < dist[nxt]){
                dist[nxt] = nd;
                pq.push({nd,nxt});
            }
        }
    }
}

int main(){
    cin >> v >> e >> k;
    
    for(int i = 0; i < e; i++){
        int a, b, c;
        cin >> a >> b >> c;
        vec[a].push_back({b,c});
    }
    
    get_dist(k);
    
    for(int i = 1; i <=v; i++){
        if(dist[i] == 2e9) cout << "INF\n";
        else cout << dist[i] << '\n';
    }
    
    return 0;
}
