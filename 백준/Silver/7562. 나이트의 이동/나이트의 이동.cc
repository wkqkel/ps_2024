#include <iostream>
#include <queue>
using namespace std;

int n;
int st[2];
int en[2];

const int MX = 302;
bool visited[MX][MX];
queue<pair<int,int>> q;

int dx[8] = {1,2,2,1,-1,-2,-2,-1};
int dy[8] = {2,1,-1,-2,-2,-1,1,2};

int bfs(){
    q.push({st[0],st[1]});
    visited[st[0]][st[1]] = true;

    int d = 0;
    while(!q.empty()){
        int sz = q.size();
     
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            if(en[0] == x && en[1] == y) {
                return d;
            }
            for(int i = 0; i < 8; i++){
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny]) continue;
               
                q.push({nx,ny});
                visited[nx][ny] = true;
            }
        }
        d++;
    }
    return 0;
}

void solution(){
    for(int i = 0; i < MX;i++){
        for(int j = 0; j < MX; j++){
            visited[i][j] = false;
        }
    }
    q = {};
    cin >> n >> st[0] >> st[1] >> en[0] >> en[1];
    cout << bfs() << '\n';
}

int main()
{
    int t;
    cin >> t;
    while(t--){
        solution();
    }
    
    return 0;
}
