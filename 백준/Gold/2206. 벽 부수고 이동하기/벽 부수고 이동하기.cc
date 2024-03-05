#include <iostream>
#include <queue>
using namespace std;

const int MX = 1002;
string board[MX];
int n, m;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int dist1[MX][MX];
bool ch1[MX][MX];
int dist2[MX][MX];
bool ch2[MX][MX];

void bfs(pair<int,int> s, int dist[][MX], bool ch[][MX]){
    queue<pair<int,int>> q;
    q.push(s);
    ch[s.first][s.second] = true;
    
 
    for(int i = 0; i < MX;i++){
        for(int j = 0; j<MX;j++){
            dist[i][j] = 1e8;
        }
    }
    
    int d = 0;
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            dist[x][y] = d;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(ch[nx][ny]) continue;
                if(board[nx][ny] == '1'){
                    dist[nx][ny] = d + 1;
                    ch[nx][ny] = 1;
                    continue;
                }
                
                q.push({nx,ny});
                ch[nx][ny] = true;
            }
        }
        d++;
    }
}

int main()
{
    cin >> n >> m;
    
    for(int i =0; i<n;i++){
        cin >> board[i];
    }
    
    bfs({0,0}, dist1, ch1);
    bfs({n-1,m-1},dist2,ch2);
    
    int mn = 1e9;
    
    for(int i =0; i <n;i++){
        for(int j = 0; j <m;j++){
            mn = min(dist1[i][j] + dist2[i][j], mn);
        }
    }
    
    if(mn >= 1e8) cout << -1;
    else cout << mn + 1;
    
    
    return 0;
}
