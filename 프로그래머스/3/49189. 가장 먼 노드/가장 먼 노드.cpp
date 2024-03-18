#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MX = 20002;
bool visited[MX];
int dist[MX];

vector<int> vec[MX];

void bfs(){
    queue<int> q;
    q.push(1);
    visited[1] = true;
    
    int d = 0;
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int cur = q.front();
            q.pop();
            dist[cur] = d;
            for(int i = 0; i < vec[cur].size(); i++){
                int nxt = vec[cur][i];
                if(visited[nxt]) continue;
                q.push(nxt);
                visited[nxt]= true;
            }
        }
        d++;
    }
}

int solution(int n, vector<vector<int>> edge) {

    
    for(int i = 0; i < edge.size(); i++){
        int a = edge[i][0];
        int b = edge[i][1];
        vec[a].push_back(b);
        vec[b].push_back(a);
    }
    
    bfs();
    
    int mx = -1;
    for(int i = 0; i < MX; i++){
        mx = max(mx, dist[i]);
    }
    
    int cnt = 0;
    for(int i = 0; i < MX; i++){
        if(mx == dist[i]) cnt++;
    }
    
    return cnt;
}