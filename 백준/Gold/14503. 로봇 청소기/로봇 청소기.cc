#include <iostream>
#include <queue>

using namespace std;

#define X first
#define Y second
#define P pair<int, int>

const int CLEANED = 3;
const int MX = 52;

int n, m;
int board[MX][MX];
bool ch[MX][MX];

int sx, sy, dir;

int dx[4] = {-1,0,1,0};
int dy[4] = {0,1,0,-1};

int main()
{
    cin >> n >> m;
    cin >> sx >> sy >> dir;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
    
    queue<P> q;
    
    q.push({sx,sy});
    ch[sx][sy] = 1;
    
    bool flag = false;
    
    while(!q.empty()){
        if(flag) break;
        int sz = q.size();
        while(sz--){
            int x = q.front().X;
            int y = q.front().Y;
         
            q.pop();
            // 1. 청소
            board[x][y] = CLEANED;
            
            // 2. 네방향탐색
            int cnt = 0;
            for(int i = dir; i < dir+4; i++){
                int ndir = i % 4;
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(board[nx][ny] != 0) continue;
                cnt++;
            }
            //   cout << "x: " << x << " y: " << y << " dir: " << dir << " cnt: " << cnt << "\n";
            if(cnt == 0){
                int ndir = (dir + 2) % 4;
                int nx = x + dx[ndir];
                int ny = y + dy[ndir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                    continue;
                }
                if(board[nx][ny] == 1) {
                    flag = true;
                    break;
                }
                q.push({nx,ny});
                ch[nx][ny] = 1;
            } else {
                dir = (dir + 4 - 1) % 4;
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] != 0) {
                    q.push({x,y});
                    continue;
                }
                q.push({nx,ny});
                ch[nx][ny] = 1;
            }
        }
    }
    
    int res = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board[i][j] == CLEANED) res++;
        }
    }
    
    cout << res;
    
    return 0;
}