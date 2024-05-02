#include <string>
#include <vector>
#include <queue>
#include <map>
#include <iostream>

using namespace std;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int ch[502][502];

bool over_map(int nx, int ny, int n, int m){
    return nx < 0 || nx >= n || ny < 0 || ny >= m;
}

int sicu_idx = 1;
map<int, int> sicu_map;
map<int, bool> sicu_ch;

void bfs(int sx, int sy, vector<vector<int>>& board){
    int n = board.size();
    int m = board[0].size();
    
    queue<pair<int, int>> q;
    
    q.push({sx,sy});
    ch[sx][sy] = sicu_idx;
    int sicu_sz = 0;
    
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            sicu_sz++;
            q.pop();
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(over_map(nx,ny, n,m)) continue;
                if(board[nx][ny] == 0 || ch[nx][ny]) continue;
                
                q.push({nx,ny});
                ch[nx][ny] = sicu_idx;
            }
        }
    }
    
    sicu_map[sicu_idx++] = sicu_sz;
}

int solution(vector<vector<int>> board) {
    int n = board.size();
    int m = board[0].size();
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board[i][j] == 0 || ch[i][j]) continue; 
            bfs(i, j, board);
        }
    }
    
    int mx = -1;
    for(int i = 0; i < m; i++){
        sicu_ch = {};
        int sum = 0;

        for(int j = 0; j < n; j++){
            int sicu_cur = ch[j][i];
            if(sicu_ch[sicu_cur]) continue;
            sicu_ch[sicu_cur] = 1;
          
            sum += sicu_map[sicu_cur];
        }
;
        mx = max(mx, sum);
    }
    
    return mx;
}