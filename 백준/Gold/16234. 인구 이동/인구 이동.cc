#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int n, l, r;
const int MX = 54;
int board[MX][MX];
bool ch[MX][MX];
#define P pair<int,int>
#define X first
#define Y second

vector<P> vec[MX][MX]; 

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void bfs(int sx, int sy){
    queue<P> q;
    
    q.push({sx,sy});
    ch[sx][sy] = 1;
    
    int sum = 0;
    int cnt = 0;
    
    vector<P> path;
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().X;
            int y = q.front().Y;
            q.pop();
           
            sum += board[x][y];
            cnt++;
            path.push_back({x,y});
            for(int i = 0; i < vec[x][y].size(); i++){
                P nxt = vec[x][y][i];
                if(ch[nxt.X][nxt.Y]) continue;
                ch[nxt.X][nxt.Y] = 1;
                q.push({nxt.X, nxt.Y});
            }
        }
    }
    
    int new_value = sum / cnt;
    
    for(P p : path){
        board[p.X][p.Y] = new_value;
    }
}
bool open(){
    bool flag = false;

    for(int x = 0; x < n; x++){
        for(int y = 0; y < n; y++){
            int cur = board[x][y];
            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                int nxt = board[nx][ny];
                int diff = abs(nxt - cur);
                if(l > diff || diff > r) continue;
        
                vec[x][y].push_back({nx,ny});
                flag = true;
            }
        }
    }
    
    for(int x = 0; x < n; x++){
        for(int y = 0; y < n; y++){
            if(ch[x][y] || vec[x][y].size() == 0) continue;
            bfs(x,y);
        }
    }
    

    return flag;
}

int main()
{
    cin >> n >> l >> r;    
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> board[i][j];
        }
    }
    
    int ret = 0;
    while(open()){
        for(int i = 0; i < MX; i++){
            for(int j = 0; j < MX; j++) {
                ch[i][j] = 0;
                vec[i][j] ={};
            }
        }
      
        ret++;
    }
    
    cout << ret;
    return 0;
}