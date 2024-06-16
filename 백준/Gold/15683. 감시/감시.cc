    #include <iostream>
#include <vector>

using namespace std;

/**
접근
- 사각지대의 최소갯수구하기
-CCTV마다 방향이 있고, 벽을 통과할 수 없음.

1. cctv 방향 경우의 수
4(방향)^8(최대CCTV) = 2^16 = 2^10 * 2^6 = 1024 * 64 = 약 60000

2. board업데이트
cctv마다 벽을 만날때까지 WATCHED로 변경
(CCTV는 건너뜀, 맵을 벗어나면 끝냄)
4(방향) * 8(최대맵) * 8(최대CCTV) = 256

3. 사각지대갯수 카운트
아직 0인것 세기
8*8

총 시간복잡도 60000 * (256 + 64) 정도로 가능
*/

int n, m;
const int MX = 10;
int board[MX][MX];
int board2[MX][MX];

const int WALL = 6;
const int WATCHED = 7;

int tmp[MX];
vector<pair<int,int>> cctvs;
vector<string> cases;


int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, -1, 0, 1};

void update(int x, int y, int dir){
    dir %= 4;
    int nx = x;
    int ny = y;
    while(true){
        nx += dx[dir];
        ny += dy[dir];
        if(nx < 0 || nx >= n || ny < 0 || ny >= m) break;
        if(board2[nx][ny] == WALL) break;
        if(board2[nx][ny] != 0) continue;
        board2[nx][ny] = WATCHED;
    }
}

void monitor(int x, int y, int dir){
    int cctv = board2[x][y];
  
    if(cctv == 1){
        update(x, y, dir);
    } 
    else if(cctv == 2){
        update(x, y, dir);
        update(x, y, dir+2);
    }
    else if(cctv == 3){
        update(x, y, dir);
        update(x, y, dir+3);
    }
    else if(cctv == 4){
        update(x, y, dir);
        update(x, y, dir+2);
        update(x, y, dir+3);
    }
    else if(cctv == 5){
        update(x, y, dir);
        update(x, y, dir+1);
        update(x, y, dir+2);
        update(x, y, dir+3);
    }
}

void recur(int cur){
    if(cur == cctvs.size()){
        string str = "";
        for(int i = 0; i < cctvs.size(); i++) {
            str += to_string(tmp[i]);
        }
        cases.push_back(str);
        return;
    }
    for(int i = 0; i < 4; i++){
        tmp[cur] = i;
        recur(cur+1);
    }
}

int main()
{
    cin >> n >> m;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
            if(0 < board[i][j] && board[i][j] < 6) {
                cctvs.push_back({i,j});
            }
        }
    }
    recur(0);
    int mn = 1e9;
    // 1. cctv방향 경우의 수에 대해
    for(string cc : cases){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                board2[i][j] = board[i][j];
            }
        }
        
        // 2. cctv를 돌면서 board update
        for(int i = 0; i < cctvs.size(); i++){
            int x = cctvs[i].first;
            int y = cctvs[i].second;
            int dir = (int) (cc[i] - '0');
          
            monitor(x, y, dir);
        }
  
        // 3. 사각지대 카운트
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board2[i][j] == 0) cnt++;
            }
        }
        
        mn = min(cnt, mn);
    }

    cout << mn;

    return 0;
}