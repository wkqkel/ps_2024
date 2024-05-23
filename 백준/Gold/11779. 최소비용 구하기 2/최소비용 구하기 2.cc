#include <iostream>
#include <queue>
#include <algorithm>
#include <vector>

using namespace std;

#define P pair<int, int>

int n, m;
int s, e;

const int MX = 1002;

int dist[MX];

vector<P> vec[MX];

int pre[MX];
int a, b, c;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> m;
    
    for(int i = 0; i < m; i++){
        cin >> a >> b >> c;
        
        vec[a].push_back({b,c});
    }
    
    cin >> s >> e;
    priority_queue<P, vector<P>, greater<P>> q;
    
    for(int i = 0; i < MX; i++){
        dist[i] = 1e9;
    }
    
    q.push({0,s});
    dist[s] = 0;
    
    int d, cur, nxt, nd;
    
    while(!q.empty()){
        d = q.top().first;
        cur = q.top().second;
        q.pop();
        
        if(d != dist[cur]) continue;
        
        for(int i = 0; i < vec[cur].size(); i++){
            nxt = vec[cur][i].first;
            nd = vec[cur][i].second + d;
            // if(dist[nxt] == nd) pre[nxt] = cur;
            if(dist[nxt] > nd){
                q.push({nd, nxt});
                dist[nxt] = nd;
                pre[nxt] = cur;
            }
        }
    }
    
    
    int cursor = e;
    vector<int> path;
    while(cursor != 0){
        path.push_back(cursor);
        cursor = pre[cursor];
    }
    reverse(path.begin(), path.end());
    cout << dist[e] << "\n" << path.size() << "\n";
    for(int c : path) cout << c << " ";
    
    return 0;
}
