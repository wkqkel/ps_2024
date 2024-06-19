#include <iostream>

using namespace std;

int n, m, x, y, k;

/**
굴리기 = 이동 + 회전
1. 주사위 이동 
주사위 좌표를 방향에 맞게 맵에서 이동
이때 범위를 벗어나면 break

2. 주사위를 회전
전개도를 기준으로
       1   2    3      4    5     6
{null,top,back,right,left,front,bottom}
동: 1364-> 4136
서: 1364 -> 3641
북: 1562 -> 5621
남: 1562 -> 2156
배열 밀리는 것을 생각했는데, 당장 좋은 방법이 안떠올라 하드코딩

3. 지도를 반영하고 출력
맵의 칸이 0이면, 주사위의 bottom을 맵에 반영
아니면, 주사위의 bottom에 반영
그러고 top 출력
*/

int board[22][22];

int dice[6] = {0, 0, 0, 0, 0, 0};
string rotation[4][2] = {{"1364","4136"},{"1364", "3641"}, {"1562", "5621"}, {"1562","2156"}};

int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};

void rotate(int dir){
    int tmp[6];
    for(int i = 0; i < 6; i++){
        tmp[i] = dice[i];
    }
 
    auto [pre, nxt] = rotation[dir];
    
    for(int i = 0; i < 4; i++){
        int from = (int)(pre[i] - '1');
        int to = (int)(nxt[i] - '1');
        
        dice[from] = tmp[to];
    }
}

int main()
{
    cin >> n >> m >> x >> y >> k;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
    
    for(int i = 0; i < k; i++){
        int dir;
        cin >> dir;
        dir--;
        // 이동
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        // cout << nx << " " << ny << "\n";
        if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        x = nx;
        y = ny;
        // 굴리기
        rotate(dir);
        if(board[x][y] == 0) {
            board[x][y] = dice[5];
        } else {
            dice[5] = board[x][y];
            board[x][y] = 0;
        }
        
        cout << dice[0] << "\n";
    }
    
    return 0;
}