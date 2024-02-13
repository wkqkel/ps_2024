#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int MX = 300020;
vector<int> vec[MX];

int dist[MX];
bool ch[MX];

void bfs(int s){
    queue<int> q;
    
    for(int i = 0; i < MX; i++) dist[i] = 1e9;
    q.push(s);
    ch[s] = true;
    dist[s] = 0;
   
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int cur = q.front();
            q.pop();
           
            for(int i = 0; i < vec[cur].size(); i++){
                int nxt = vec[cur][i];
                if(ch[nxt]) continue;
                
                q.push(nxt);
                ch[nxt] = true;
                dist[nxt] = min(dist[nxt], dist[cur] + 1);
            }
        }
    }
}

int main()
{
    int n, m, k ,x;
    cin >> n >> m >> k >> x;
    
    for(int i = 0; i < m; i++){
        int a, b;
        cin >> a >> b;
        vec[a].push_back(b);
    }
    
    bfs(x);
    
    bool flag = true;
    
    for(int i = 0; i < MX; i++){
        if(dist[i] == k) {
            flag = false;
            cout << i << '\n';
        }
    }
    
    if(flag) cout << -1;
    
    return 0;
}
