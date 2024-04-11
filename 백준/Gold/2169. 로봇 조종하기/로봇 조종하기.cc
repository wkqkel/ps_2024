#include <iostream>

using namespace std;

int n, m;

int board[1002][1002];

int dx[3] = {0,1,0};
int dy[3] = {1,0,-1};

int dp[1002][1002][3];

int dfs(int x, int y, int prevDir){
    if(x == n-1 && y == m-1) return board[x][y];
    
    int ret = -1e7;
    
    if(dp[x][y][prevDir]) {
        return dp[x][y][prevDir];
    }
    
    for(int i = 0; i < 3; i++){
        if((prevDir +2) % 4 == i) continue;
        int nx = x + dx[i];
        int ny = y + dy[i];
        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        
        ret = max(ret, board[x][y] + dfs(nx,ny,i));
    }
    
    return dp[x][y][prevDir] = ret;
}

int main()
{
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
 
    cout << dfs(0,0,1);

    return 0;
}
