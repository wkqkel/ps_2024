#include <iostream>

using namespace std;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

const int MX = 502;
int board[MX][MX];
int n;
int ch[MX][MX];

int mx;

int dfs(int x, int y){
    int ret = 1;
    
    if(ch[x][y]){
        return ch[x][y];
    }

    for(int i = 0; i < 4; i++){
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        if(nx < 0 || nx >= n || ny < 0 || ny >=n) continue;
        if(board[nx][ny] <= board[x][y]) continue;
        ret = max(ret, dfs(nx,ny) + 1);
    }
    
    return ch[x][y] = ret;
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
            if(ch[i][j]) continue;
            mx = max(mx, dfs(i,j));
        }
    }

    cout << mx;
    return 0;
}
