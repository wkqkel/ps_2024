#include <iostream>
#include <map>
#include <queue>

using namespace std;

int n;
const int MX = 102;

char board1[MX][MX];
bool ch1[MX][MX];
int cnts1[3];

char board2[MX][MX];
bool ch2[MX][MX];
int cnts2[3];

map<char, int> color_map = {{'R', 0}, {'G',1}, {'B',2}};


int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void bfs(int sx, int sy, auto& board, auto& ch){
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
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(ch[nx][ny] || board[x][y] != board[nx][ny]) continue;
                q.push({nx,ny});
                ch[nx][ny] = 1;
            } 
        }
     
    }
}

int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            char c;
            cin >> c;
            board1[i][j] = c;
            if(c == 'G'){
                board2[i][j] = 'R';
            } else {
                board2[i][j] = c;
            }
        }
    }

    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(ch1[i][j]) continue;
            bfs(i,j, board1, ch1);
            cnts1[color_map[board1[i][j]]]++;
        }
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(ch2[i][j]) continue;
            bfs(i,j, board2, ch2);
            cnts2[color_map[board2[i][j]]]++;
        }
    }
    
    
    int sum1 = cnts1[0] + cnts1[1] + cnts1[2];
    int sum2 = cnts2[0] + cnts2[1] + cnts2[2];
    
    cout << sum1 << " " << sum2;
    return 0;
}
