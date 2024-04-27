#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MX = 52;
vector<pair<int, int>> vec[MX];
int dist[MX];
bool ch[MX];

void get_dist(int st){
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    
    for(int i = 0; i < MX; i++){
        dist[i] = 1e9;
        ch[i] = 0;
    }
    
    pq.push({0, st});
    dist[st] = 0;
    
    while(pq.size()){
        int cur = pq.top().second;
        int d = pq.top().first;
        pq.pop();
        
        if(ch[cur]) continue;
        ch[cur] = 1;
        
        for(int i = 0; i < vec[cur].size(); i++){
            int nxt = vec[cur][i].first;
            int nd = vec[cur][i].second + d;
            
            if(dist[nxt] > nd) {
                dist[nxt] = nd;
                pq.push({nd, nxt});
            }
        }
    }
}

int solution(int N, vector<vector<int>> road, int K) {
    int answer = 0;
    
    for(int i = 0; i < road.size(); i++){
        int a = road[i][0];
        int b = road[i][1]; 
        int c = road[i][2];
        
        vec[a].push_back({b,c});
        vec[b].push_back({a,c});
    }
    
    get_dist(1);
    
    int cnt = 0;
    for(int i = 1; i <= N; i++){
        if(dist[i] <= K) cnt++;
    }
    
    return cnt;
}