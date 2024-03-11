#include <iostream>
#include <queue>
using namespace std;

const int MX = 102;
int n;
int board[MX][MX];
bool visited[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int cnt = 1;
void bfs(pair<int,int> s){
    queue<pair<int,int>> q;
    q.push(s);
    visited[s.first][s.second] = 1;
    
  
    
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            board[x][y] = cnt;
            for(int i = 0 ; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(board[nx][ny] == 0 || visited[nx][ny]) continue;
                q.push({nx,ny});
                visited[nx][ny] = 1;
            }
        }
    }
    cnt++;
}

int res = 1e5;

void bfs2(pair<int,int> s){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            visited[i][j] =false;
        }
    }
    queue<pair<int,int>> q;
    q.push(s);
    visited[s.first][s.second] = 1;
    
    int d = 0;
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            for(int i = 0 ; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny]) continue;
                
                if(board[nx][ny] > board[s.first][s.second]) {
                    res = min(res,d);
                    return;
                }
             
                q.push({nx,ny});
                visited[nx][ny] = 1;
            }
        }
        d++;
    }
}

int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> board[i][j];
        }
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
           if(board[i][j]==0 || visited[i][j])continue;
           bfs({i,j});
        }
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
          if(board[i][j] == 0) continue;
          bfs2({i,j});
        }
    }
    
    cout << res;
    return 0;
}
