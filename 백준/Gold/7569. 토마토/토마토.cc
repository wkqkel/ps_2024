#include <iostream>
#include <queue>

using namespace std;

const int MX = 102;

int dz[6] = {1,-1,0,0,0,0};
int dx[6] = {0,0,1,0,-1,0};
int dy[6] = {0,0,0,1,0,-1};

int n,m,h;
int board[MX][MX][MX];
queue<pair<int,pair<int,int>>> q;
int bfs(){
    int d = 0;
    while(!q.empty()){
        int sz = q.size();
   
        while(sz--){
            int z = q.front().first;
            int x = q.front().second.first;
            int y = q.front().second.second;
            q.pop();
            
            for(int i = 0; i < 6; i++){
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny <0 || ny >= m || nz <0 || nz >= h) continue;
                if(board[nz][nx][ny] != 0) continue;
                q.push({nz,{nx,ny}});
                board[nz][nx][ny] = 1;
            }
        }
        d++;
    }
    
    return d;
}

int main()
{
    cin >> m >> n >> h;
    
    for(int i = 0; i < h; i++){
        for(int j = 0; j < n; j++){
            for(int k = 0; k < m; k++){
                cin >> board[i][j][k];
                if(board[i][j][k] == 1) {
                    q.push({i,{j,k}});
                }
            }
        }
    }
    
    int cnt = bfs();
    int flag = true;
    for(int i = 0; i < h; i++){
        for(int j = 0; j < n; j++){
            for(int k = 0; k < m; k++){
                if(board[i][j][k] == 0) {
                   flag = false;
                }
            }
        }
    }

    if(flag) cout << cnt-1;
    else cout << -1;
    
    return 0;
}
