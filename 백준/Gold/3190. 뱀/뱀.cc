#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

/**
### 시작
- (0,0)에서 시작하고, 오른쪽을 향한다.

### move
- 1초마다 이동을 한다.
- 이동할 떄 규칙
1. 머리를 다음 칸에 위치
2. 벽이나 자신의 몸에 부딪히면 끝
3. 이동한 칸이 사과라면, 사과는 없어진다.
4. 아니라면, 꼬리를 줄인다.

### 방향전환
- 'L'이라면 왼쪽으로 90도 회전 reverse(t, t+4);     
- 'R'이라면 오른쪽으로 90도 회전 rotate(t,t+1, t+4);

*/

#define P pair<int, int>
#define X first
#define Y second

int n, k, L;

const int MX = 104;
const int APPLE = 1;

int board[MX][MX];
bool ch[MX][MX];

P dirs[4] = {{0,1},{1,0},{0,-1},{-1,0}};

char times[10040];


bool over_map(int nx, int ny){
    return nx < 0 || nx >= n || ny < 0 || ny >= n;
}
int main()
{
    cin >> n >> k;
    
    for(int i = 0; i < k; i++){
        int a, b;
        cin >> a >> b;
        a--; b--;
        board[a][b] = APPLE;
    }
    
    cin >> L;
    for(int i = 0; i < L; i++){
        int x; char c;
        cin >> x >> c;
        times[x] = c;
    }
   
    deque<P> q;
    int t = 0; 
    int dir = 0;
    
    q.push_back({0,0});
    while(++t){
        // move
        // 1. 머리를 다음칸에 위치
        P head = q.front();
        P tail = q.back();
        int nx = head.X + dirs[dir].X;
        int ny = head.Y + dirs[dir].Y;
        // 2. 벽이나 몸과 부딪히면 끝
        if(over_map(nx,ny) || ch[nx][ny]){
            break;
        }
        q.push_front({nx,ny});
        ch[nx][ny] = 1;
        // 3. 사과가 있다면, 없앰
          // 사과가 없다면 꼬리를 없앰
        if(board[nx][ny] == APPLE){
            board[nx][ny] = 0;
        } 
        else {
            ch[tail.X][tail.Y] = 0;
            q.pop_back();
        }

        
        // 방향전환
        if(times[t] == 'L') {
           dir = (dir+3) % 4;
        }
        else if(times[t] == 'D') {
           dir = (dir+1) % 4;
        }
    }
    
    cout << t;
   
    return 0;
}