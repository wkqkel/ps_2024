/**
1. 아이디어

dfs를 이용해 잠기지않는 영역의 연결요소 개수를 구하는 문제
인데 잠기는 높이를 완전탐색으로 해봐야함.


2. 시간복잡도
dfs: O(V+E) = 100 * 100 * 5 = 5e4
높이 100

*/

#include <iostream>

using namespace std;

const int MX = 102;
int n;
int board[MX][MX];
bool visited[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void dfs(int x, int y, int h){
  visited[x][y] = 1;
  
  for(int i = 0; i < 4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
    if(board[nx][ny] <= h || visited[nx][ny]) continue;
    dfs(nx,ny, h);
  }
}

void reset(){
  for(int i = 0; i < MX; i++){
    for(int j  = 0; j < MX; j++){
      visited[i][j] = 0;
    }
  }
}

int main(){
  cin >> n;
  for(int i = 0; i < n; i++){
    for(int j = 0; j < n; j++){
      cin >> board[i][j];
    }
  }
  
  int mx = -1;
  for(int h = 0; h < MX; h++){
    reset();
    int cnt = 0;
    for(int i = 0; i < MX; i++){
      for(int j = 0; j < MX; j++){
          if(board[i][j] <= h || visited[i][j]) continue;
          dfs(i,j,h);
          cnt++;
      }
    }
    mx = max(mx, cnt);
  }
  
  cout << mx;
}