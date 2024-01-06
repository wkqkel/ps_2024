#include <bits/stdc++.h>
using namespace std;

int n, m;
int board1[10][10];
int board2[10][10];
int dx[4] = {0, 1, 0, -1};
int dy[4] = {1, 0, -1, 0};

bool oop(int x, int y){
  return 0 > x || x >= n || 0 > y || y >= m;
}

void upd(int x, int y, int dir){
  dir %= 4;

  while(true) {
    x += dx[dir];
    y += dy[dir];
    if(oop(x,y) || board1[x][y] == 6) break;
    if(board1[x][y] != 0) continue; 
    board2[x][y] = 7;
  }
}




int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  int mn = 0;

  vector<pair<int,int>> cctv;

  for(int i = 0; i < n; i++){
    for(int j = 0; j < m; j++){
      cin >> board1[i][j];
      if(board1[i][j] != 0 && board1[i][j] != 6) {
        cctv.push_back({i,j});
      }
      if(board1[i][j] == 0) mn++;
    }
  }

  for(int j = 0; j < n; j++){
    for(int k = 0; k < m; k++){
      board2[j][k] = board1[j][k];
    }
  }



  for(int i = 0; i < 1 << (2 * cctv.size()); i++ ){
    // board2 원복 복사
    for(int j = 0; j < n; j++){
      for(int k = 0; k < m; k++){
        board2[j][k] = board1[j][k];
      }
    }
    // 방향 경우의 수 하나에 cctv만큼 돌고, 해당 방향 처리
    int tmp = i;

    for(int j = 0; j < cctv.size(); j++){
      int dir = tmp % 4;
      tmp /= 4;

      int x = cctv[j].first;
      int y = cctv[j].second;
      int cctv_type = board1[x][y];

      if(cctv_type == 1){
        upd(x,y,dir);
      } 
      else if (cctv_type == 2){
        upd(x,y,dir);
        upd(x,y,dir+2);
      }
      else if (cctv_type == 3){
        upd(x,y,dir);
        upd(x,y,dir+1);
      }
      else if (cctv_type == 4){
        upd(x,y,dir);
        upd(x,y,dir+1);
        upd(x,y,dir+2);
      } else {
        upd(x,y,dir);
        upd(x,y,dir+1);
        upd(x,y,dir+2);
        upd(x,y,dir+3);
      }
    }
    int cnt =0;
    for(int j = 0; j < n; j++){
      for(int k = 0; k < m; k++){
        if(board2[j][k] == 0) cnt++; 
      }
    }
    mn = min(mn, cnt);
  }
  cout << mn;
}