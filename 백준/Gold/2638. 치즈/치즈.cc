#include <iostream>
#include <queue>

using namespace std;

struct coord{
    int x, y;
};
int n, m;
const int MX = 102;
int board[MX][MX];

bool visited[MX][MX];
int outs[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void input(){
     cin >> n >> m;
     for(int i = 0; i < n; i++){
         for(int j = 0; j < m; j++){
             cin >> board[i][j];
         }
     }
}

void reset(){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            visited[i][j] = 0;
            outs[i][j] = 0;
        }
    }
}

void spread(){
    queue<coord> q;
    q.push({0,0});
    visited[0][0] = true;
    
    while(!q.empty()){
        int x = q.front().x;
        int y = q.front().y;
        q.pop();
        
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
    
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            
            if(visited[nx][ny]) continue;
            if(board[nx][ny] == 1){
                outs[nx][ny]++;
            } else {
                q.push({nx,ny});
                visited[nx][ny] = true;
            }
        }
    }
}

bool melt(){
    bool flag = false;
    for(int i = 0; i < n; i++){
        for(int j = 0; j< m; j++){
            if(outs[i][j] < 2)  continue;
    
            board[i][j] = 0;
            flag = true;
        }
    }
    return flag;
}

void solutuion(){
    int t = 0;
    while(true){
   
        reset();
        spread();
        
        if(melt()){
            t++;
        } else {
            break;
        }
    }
    
    cout << t;
}

int main(){
    input();
    solutuion();
    return 0;
}