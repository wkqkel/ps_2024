#include <iostream>
#include <queue>

using namespace std;

const int MX = 102;
int board[MX][MX][MX];
bool ch[MX][MX][MX];

int n,m,h;

queue<pair<int, pair<int, int>>> q;
int dz[6] = {0,0,0,0,1,-1};
int dx[6] = {1,0,-1,0,0,0};
int dy[6] = {0,1,0,-1,0,0};
int days = 0;

void bfs(){
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int z = q.front().first;
            int x = q.front().second.first;
            int y = q.front().second.second;
            q.pop();
            
            for(int i = 0; i < 6; i++){
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nz < 0 || nz >= h || nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(ch[nz][nx][ny] || board[nz][nx][ny] != 0) continue;
                q.push({nz,{nx,ny}});
                ch[nz][nx][ny] = 1;
            }
        }
        days++;
    }
}

int main()
{
    cin >> m >> n >> h;
    
    int unused = 0; 
    for(int k = 0; k < h; k++){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                cin >> board[k][i][j];
                if(board[k][i][j] == 1) {
                    q.push({k,{i,j}});
                    ch[k][i][j] = 1;
                } else if(board[k][i][j] == 0){
                    unused++;
                }
            }
        }
    }
    bfs();

    int cnt = 0;
    for(int k = 0; k < h; k++){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(ch[k][i][j] && board[k][i][j] == 0) cnt++;
            }
        }
    }
    
    if(unused == 0) cout << 0;
    else if(unused != cnt) cout << -1;
    else cout << days - 1;
    
    return 0;
}
