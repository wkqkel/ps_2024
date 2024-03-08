#include <iostream>
#include <queue>

using namespace std;
/**
1. 아이디어
벽을 3개 세운 뒤 안전영역의 최대 크기를 구하라
n,m이 최대 8인 것을 보고 완전탐색 + bfs를 떠올림.

2. 시간복잡도
완탐
조합 64 * 63 * 62 / 1 * 2 * 3 = 4e4

bfs
V+E = 64 + 64 * 4 = 320

충분

3. 자료구조
visited 방문체크

--
2차원에서 완탐 3개 고르는 법을 구현하는데 오래 걸렸다..
완탐에서 막혀서 약 1시간~1시간반정도 걸림...

*/
const int MX = 8;
int board1[MX][MX];
int board2[MX][MX];

int n,m;

int visited[MX][MX];

int arr[102];
int cnt = 0;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int mx = -1;

void reset(){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            board2[i][j] = board1[i][j];
            visited[i][j] = false;
        }
    }
}

bool over_map(int nx, int ny){
    return nx < 0 || nx >= n || ny < 0 || ny >= m;
}


int bfs(){
    queue<pair<int,int>> q;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board2[i][j] != 2) continue;
            q.push({i,j});
            visited[i][j] = true;
        }
    }

    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            board2[x][y] = 2;
            q.pop();
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(over_map(nx,ny)) continue;
                if(board2[nx][ny]!=0 || visited[nx][ny]) continue;
                
                q.push({nx,ny});
                visited[nx][ny] = true;
            }
        }
    }
    
    int cnt = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board2[i][j] == 0) cnt++;
        }
    }
    
    return cnt;
}

void recur(int cur, int s){
    if(cur == 3){
        reset();
        
        for(int i = 0; i < 3; i++){
            int x = arr[i] / m;
            int y = arr[i] % m;
            if(board2[x][y] != 0) {
                return;
            };
            board2[x][y] = 1;
        }
        mx = max(mx, bfs());
        return;
    }
    
    for(int i = s; i < n * m; i++){
        arr[cur] = i;
        recur(cur+1, i+1);
    }
}


int main()
{
    cin >> n >> m;
    
   for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board1[i][j];
        }   
    }
   
    recur(0, 0);
    cout << mx;
    return 0;
}
