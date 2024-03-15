#include <iostream>

using namespace std;

/**
1. 아이디어
- dfs로 연결요소의 갯수 세기
- 적록색약을 위한 board2 색 하나 통일


2. 시간복잡도
dfs: O(V+E) = 5e4;
*/

int n;
const int MX = 102;
char board1[MX][MX];
char board2[MX][MX];

bool visited[MX][MX];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void dfs(int x, int y, char v, char board[MX][MX]){
    visited[x][y] = 1;
    
    for(int i = 0; i < 4; i++){
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
        if(visited[nx][ny] || board[nx][ny] != v) continue;
        dfs(nx,ny, v, board);
    }
}

int main()
{
    cin >> n;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> board1[i][j];
            board2[i][j] = board1[i][j];
            if(board2[i][j] == 'R') board2[i][j] ='G';
        }
    }
    int cnt1 = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(visited[i][j]) continue;
            dfs(i,j, board1[i][j], board1);
            cnt1++;
        }
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            visited[i][j] = 0;
        }
    }
    
    int cnt2 = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(visited[i][j]) continue;
            dfs(i,j, board2[i][j], board2);
            cnt2++;
        }
    }
    
    cout << cnt1 << " " << cnt2;
    return 0;
}
