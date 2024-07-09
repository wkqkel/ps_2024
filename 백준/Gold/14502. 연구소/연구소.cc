#include <iostream>
#include <queue>
using namespace std;

#define P pair<int, int>
#define X first
#define Y second

/**
# boj14502 연구소

## 설계
1. 벽 3개 경우의 수
벽 3개 세우기
완탐 최대 64^3 = 262144

2. 바이러스 퍼뜨리기 
bfs로 2인 곳에서부터 0으로 상하좌우로 다 퍼뜨린 뒤,
안전영역 갯수 세기
O(V+E) = O(4NM) = O(4*64)

=> 가능
*/

int n,m;

const int MX = 12;
int board[MX][MX];
int board2[MX][MX];
bool ch[MX][MX];
bool ch2[MX][MX];

P arr[MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int mx = 0;

void bfs(int sx, int sy){
    queue<P> q;
    
    q.push({sx,sy});
    ch2[sx][sy] = 1;
    
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int x = q.front().X;
            int y = q.front().Y;
            // board2[x][y] = 2;
            q.pop();
            
            for(int i = 0; i <4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(board2[nx][ny] != 0 || ch2[nx][ny]) continue;
                q.push({nx,ny});
                ch2[nx][ny] = 1;
                board2[nx][ny] = 2;
            }
        }
    }
    

}

void recur(int cur){
    if(cur == 3){
        // 초기화
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
              ch2[i][j] = 0;
              board2[i][j] = board[i][j];
            }
        }
        // 벽세우기
        for(int i = 0; i < 3; i++){
            board2[arr[i].X][arr[i].Y] = 1;
        }
        
        // // 바이러스 퍼뜨리기
        for(int i = 0; i< n; i++){
            for(int j = 0; j< m; j++){
                if(ch2[i][j] || board2[i][j] != 2) continue;
                bfs(i,j);
            }
        }
        
        int cnt = 0;    
        for(int i = 0; i< n; i++){
            for(int j = 0; j< m; j++){
                if(board2[i][j] == 0) cnt++;
            }
        }
        
        mx = max(cnt, mx);
        return;
    }
    
    for(int i = 0; i< n; i++){
        for(int j = 0; j< m; j++){
            if(ch[i][j] || board[i][j] != 0) continue;
            arr[cur] = {i,j};
            ch[i][j] = 1;
            recur(cur+1);
            ch[i][j] = 0;
        }
    }
}

int main()
{
    cin >> n >> m;
    for(int i = 0; i< n; i++){
        for(int j = 0; j< m; j++){
            cin >> board[i][j];
        }
    }
    
    recur(0);

    cout << mx;
    return 0;
}