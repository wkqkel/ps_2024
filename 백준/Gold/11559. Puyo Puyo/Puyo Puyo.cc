#include <iostream>
#include <queue>
#include <vector>

using namespace std;

/**
1. 전부 아래로 내린다.
 (12 * 12)(세로한줄내리기최대) * 6(전체줄)
2. 맵을 돌면서, 
12*6
3. 같은 색이 4개이상 연결된게 있는지 체크
없다면 끝
있다면, 다 없애고 1 반복.

---
dfs로 체크와 없애기를 한번에 하려다가 잘 안됐음.
확실히 될 것 같은 풀이로 빨리 전환해야하는데, 계속 dfs로 붙잡음.

*/

const int n = 12, m = 6;
char board[n][m];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

bool ch[n][m];

vector<pair<int,int>> dist;

void down(){
    for(int y = 0; y < m; y++){
        for(int i = n - 2; i >= 0; i--){
            int cur = i;
            int nxt = i + 1;
            while(true){
                if(nxt > n || board[nxt][y] != '.') break;
                swap(board[cur][y], board[nxt][y]);
                nxt++;
                cur++;
            }
        }  
    }
}

bool can_pang(int sx, int sy){
    queue<pair<int,int>> q;
    
    q.push({sx,sy});
    ch[sx][sy] = 1;
    
    int nums = 0;
    while(!q.empty()){
        int sz = q.size();
        nums += sz;
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            dist.push_back({x,y});
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(ch[nx][ny] || board[nx][ny] != board[x][y]) continue;
                q.push({nx,ny});
                ch[nx][ny] = 1;
            }
        }

    }

    return nums >= 4;
}

void pang(){
    for(auto [x,y] : dist){
        board[x][y] = '.';
        ch[x][y] = 0;
    }
}

void print_board(){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cout << board[i][j];
        }
        cout << "\n";
    }
}
int main()
{
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
    int res = 0;
    
    while(true){
        down();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                ch[i][j] = 0;
            }
        }
        
        bool found = false;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == '.' || ch[i][j]) continue;
                dist = {};
                if(can_pang(i,j)){
                    pang();
                    found = true;
                } 
            }
        }
      
        if(found) res++;
        else break;
    }
 
    cout << res;

    return 0;
}