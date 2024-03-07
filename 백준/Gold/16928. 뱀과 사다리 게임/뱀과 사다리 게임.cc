#include <iostream>
#include <queue>

using namespace std;

const int MX = 102;
int ladder[MX];
int snake[MX];
bool visited[MX];

int n, m;

int bfs(){
    queue<pair<int,int>> q;
    
    q.push({1,0});
    visited[1] = true;
    
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int cur = q.front().first;
            int dist = q.front().second;
            q.pop();
            if(cur == 100) return dist;
            for(int i = 1; i <= 6; i++){
                int nxt = cur + i;
                if(nxt < 0 || nxt > 100) continue;
                if(ladder[nxt]){
                    nxt = ladder[nxt];
                } else if(snake[nxt])  {
                    nxt = snake[nxt];
                }
           
                if(visited[nxt]) continue;
                q.push({nxt,dist+1});
                visited[nxt] = true;
            }
        }
    }
}
int main()
{
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        int a, b;
        cin >> a >> b;
        ladder[a] = b; 
    }
    
    for(int i = 0; i < m; i++){
        int a, b;
        cin >> a >> b;
        snake[a] = b; 
    }
    
    cout << bfs();
    
    return 0;
}
