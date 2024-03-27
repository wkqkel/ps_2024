#include <string>
#include <vector>
#include <queue>
#include <set>
#include <iostream>

using namespace std;

const int MX = 32;
bool ch[MX][MX];
set<pair<int,int>> s;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int dx1[4] = {-1,0,1,0};
int dy1[4] = {0,-1,0,1};
int dx2[4] = {-1,1,1,-1};
int dy2[4] = {-1,-1,1,1};
int dx3[4] = {0,1,0,-1};
int dy3[4] = {-1,0,1,0};

bool over(int nx, int ny, vector<string>& board){
   return (nx < 0 || nx >= board.size() || ny < 0 || ny >= board[0].size());
}

bool pang(int x, int y, vector<string> board){
    char t = board[x][y];

    for(int i = 0; i < 4; i++){
        int nx1 = x + dx1[i];
        int ny1 = y + dy1[i];
        int nx2 = x + dx2[i];
        int ny2 = y + dy2[i]; 
        int nx3 = x + dx3[i];
        int ny3 = y + dy3[i];
        
        if(over(nx1,ny1,board) || over(nx2,ny2,board) || over(nx3,ny3, board)) continue;
        if(board[nx1][ny1] == t && board[nx2][ny2] == t && board[nx3][ny3] == t) return true;
    }
    
    return false;
}

void bfs(int sx, int sy, vector<string> board){
    queue<pair<int,int>> q;
    q.push({sx,sy});
    ch[sx][sy] = 1;
    
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
              
                if(nx < 0 || nx >= board.size() 
                   || ny < 0 || ny >=board[0].size()) continue;
                
                if(board[nx][ny] == board[sx][sy] && pang(nx,ny,board)) s.insert({nx,ny});
                
                if(ch[nx][ny] || board[nx][ny] != board[sx][sy]) continue;
            
                q.push({nx,ny});
                ch[nx][ny] = 1;
            }
        }
    }
}

void reset(){
    s.clear();
    
    for(int i = 0; i < MX; i++){
        for(int j = 0; j < MX; j++){
            ch[i][j] = false;
        }
    }
}

void down(vector<string>& board){
    for(int j = 0; j < board[0].size(); j++){
        for(int i =  board.size() -2; i >= 0; i--){
            int idx = i;

            while(idx + 1 < board.size() && board[idx+1][j]== 'X'){
                swap(board[idx+1][j],board[idx][j]);
                idx++;
            }
        } 
    }
}

int solution(int n, int m, vector<string> board) {
    int res = 0;
    int cnt = 0;
    while(true){
        cnt++;
        reset();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(ch[i][j] || board[i][j] == 'X') continue;
                bfs(i,j, board);
            }
        }
        
        if(s.size() == 0) break;
        res += s.size();
        for(pair<int,int> p : s){
            board[p.first][p.second] = 'X';
        }
      
        down(board);
    }
    
    return res;
}