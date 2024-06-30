#include <iostream>

using namespace std;

/**
# 문제
boj 13460 구슬탈출2

## 문제 이해
n*m 보드에 R,B구슬이 존재
중력을 이용해서 판을 기울일 수 있음.
몇번만에 구슬을 빼는지

.은 빈칸
#은 벽
O는 구멍의 위치
R,B

기울이기

## 설계
1. 경우의수 (recur)
4방향 기울이기^10번씩
최대 4^10 = 2^10^2 => 10^6 

2. 이동 (down)
move 방향에 따라, .이 아닐때까지 이동
최대 10 * 2(공 2개)

3. 공의 순서 (is_red_first)
근데, 어떻게 겹치는걸 막지?
단순히 떠오르는 건, 
기울일 방향에 따라,
먼저 움직일 공의 순서를 달리함.

1. 왼쪽 기울임
y좌표가 작은거 먼저 
2. 오른쪽 기울임
y좌표가 큰거 먼저
3. 위로기울임
x좌표가 작은거 먼저
4. 아래로 기울임
x좌표가 큰거 먼저


--
도착한 경우 어떻게 판단 할지랑 
어떻게 각 경우마다 다르게?

---
처음엔 보드와 좌표를 전역변수로 두고, 변형시키는 식으로 구현했는데,
구현을 할수록 초기화 관련해서 복잡도가 너무 올라갔다
하루종일 하다, 접근이 잘못됐다는 걸 알아차리고, 
파라미터로 넘겨서 업데이트하는 방식으로 구현
*/

#define P pair<int,int>
#define X first
#define Y second

const int MX = 12;
int n, m;

char board[MX][MX];
bool ch1[MX][MX];
bool ch2[MX][MX];

int dx[4] = {0,0,-1,1};
int dy[4] = {-1,1,0,0};
int op[4] = {1,0,3,2};
int mn = 1e9;
string dist[4] = {"left","right","up","down"};

pair<int, int> down(P coord,int dir, P other){
    int nx = coord.X;
    int ny = coord.Y;
    
    while(true){
        nx += dx[dir];
        ny += dy[dir];
        if(nx < 0 || nx >= n || ny < 0 || ny >= m) break;
        // 다른 거에 막혀도 멈춤
        if(nx == other.X && ny == other.Y && board[nx][ny] != 'O'){
            break;
        }
        // goal에 도착한 경우 O에서 멈춤
        if(board[nx][ny] != '.') {
            if(board[nx][ny] == 'O') {
                return {nx, ny};
            }
            break;
        }
    }
    // 막힌 경우, 막히기 전으로.
    nx -= dx[dir];
    ny -= dy[dir];
    
    return { nx, ny };
}

bool is_red_first(int dir, P red, P blue){
   if(dir == 0) {
       return red.Y <= blue.Y;
   }
   if(dir == 1){
       return red.Y >= blue.Y;
   }
   if(dir == 2){
       return red.X <= blue.X;
   }
   if(dir == 3){
       return red.X >= blue.X;
   }
}


void recur(int cur, int dir, P red, P blue){
    if(cur == 12){
      return;
    }
 
    if(is_red_first(dir, red,blue)){
        red = down(red, dir, blue);
        blue = down(blue, dir, red);
    } else {
        blue = down(blue, dir, red);
        red = down(red, dir, blue);
    }
    // cout << cur << " " << dist[dir]  << "\n";
    // cout << " red.X: " << red.X << " , red.Y: " << red.Y;
    // cout << " blue.X: " << blue.X << " , blue.Y: " << blue.Y <<"\n";
    if(board[blue.X][blue.Y] == 'O'){
        return;
    }

    if(board[red.X][red.Y] == 'O'){
        mn = min(cur, mn);
        return;
    }
  
    for(int i = 0; i < 4; i++){
        if(i == op[dir] || i == dir) continue;
        if(board[red.X + dx[i]][red.Y + dy[i]] == '#' && board[blue.X + dx[i]][blue.Y + dy[i]] == '#') continue;
        recur(cur+ 1, i, red, blue);
    }
}

int main()
{
    cin >> n >> m;
    
    pair<int,int> red,blue;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
            if(board[i][j] == 'R'){
                red = {i,j};
                board[i][j] = '.';
            }
            else if(board[i][j] == 'B') {
                blue = {i,j};
                board[i][j] = '.';
            }
        }
    }

    for(int i = 0; i < 4; i++){
        recur(1, i, red, blue);
    }
    if(mn > 10) cout << -1;
    else cout << mn;

    return 0;
}