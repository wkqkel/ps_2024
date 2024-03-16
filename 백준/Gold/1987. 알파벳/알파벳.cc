#include <iostream>
using namespace std;

/**
1. 아이디어
이미 지난 알파벳 못지나감 -> 알파벳으로 방문처리
visited를 알파벳 배열로 만듬
'A' - 'A'로 해서 배열에 true로 변경.
하면서 cnt 세기
---
였는데 설계 때 생각못한것.
a. 막혔으면 방문처리 다시 false로 해줘야함
-> 재귀밑에 방문취소처리
b. cnt를 전역으로 두면, 돌아갔을 때도 올라가있음 
-> cnt도 파라미터로 하고 최댓값 찾아내기

---
34분 소요.

2. 시간복잡도
O(V+E) = 5 * 20 * 20 = 1000
*/


int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

const int MX = 32;

int n,m;
bool visited[MX];
char board[MX][MX];

int cnt = 0;
int mx = -1;

void dfs(int x, int y, int cnt){
    visited[board[x][y] - 'A'] = true;
    
    mx = max(cnt ,mx);

    for(int i = 0; i < 4; i++){
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

        if(visited[board[nx][ny] - 'A']) continue;
        dfs(nx,ny, cnt+1);
        visited[board[nx][ny] - 'A'] = false;
    }
}

int main()
{
    cin >> n >> m;
    
    for(int i =0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
    
    dfs(0,0,1);
    
    cout << mx;
    
    return 0;
}
