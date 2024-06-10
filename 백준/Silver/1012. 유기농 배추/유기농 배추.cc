#include <iostream>
#include <queue>

using namespace std;

int n, m, k, x, y, T;
const int MX = 52;
int board[MX][MX];
bool ch[MX][MX];

int dx[4] = {0,1,0,-1};
int dy[4] = {1,0,-1,0};

void bfs(int sx, int sy){
    queue<pair<int,int>> q;
    
    q.push({sx,sy});
    ch[sx][sy] = 1;
    
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >=m) continue;
                if(board[nx][ny] == 0 || ch[nx][ny]) continue;
                q.push({nx,ny});
                ch[nx][ny] = 1;
            }
            
        }
    }
    
}

int main()
{
    cin >> T;
    
    for(int t = 0; t < T; t++){
        cin >> m >> n >> k;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                board[i][j] = 0;
                ch[i][j] = 0;
            }
        }
        

        for(int i = 0; i < k; i++){
            cin >> y >> x;
            
            board[x][y] = 1;
        }
        
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 0 || ch[i][j]) continue;
                cnt++;
                bfs(i,j);
            }
        }
        
        cout << cnt << "\n";
    }

    return 0;
}
