#include <iostream>
#include <queue>

using namespace std;

/**

1. 아이디어
최소경로 구하기
bfs
인접한 노드를 큐에 넣어주면서 dist를 증가.

2. 시간복잡도 
(V+E), max 100
1e4 + 1e4 * 4 = 5e4 >> 가능 

3. 자료구조
bool visited
string board
int dist

*/
int n,m;
const int MX = 102;
string board[MX];
bool visited[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int bfs(){
    queue<pair<int,int>> q;
    q.push({0,0});
    visited[0][0] = true;
    int dist = 1;
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            if(x == n-1 && y == m-1) {
                return dist;
            }
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || board[nx][ny] == '0') continue;
                q.push({nx,ny});
                visited[nx][ny] = true;
            }
        }
        dist++;
    }
}

int main(){
    cin >> n >> m;
    
    for(int i = 0; i < n;i++){
        cin >> board[i];
    }
    
    cout << bfs();
    
}