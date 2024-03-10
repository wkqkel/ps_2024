#include <iostream>
#include <queue>

using namespace std;

const int MX = 22;
int n;
int board[MX][MX];
bool visited[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int shark_size = 2;
int res = 0;
int eats = 0;

int main()
{
    cin >> n;
   
  
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> board[i][j];
        }
    }
    
    while(true){
        pair<int,int> s;
        queue<pair<int,int>> q;
        // priority_queue는 디폴트가 내림차순!
        priority_queue<pair<int, pair<int, int>>, vector<pair<int, pair<int, int>>>, greater<pair<int, pair<int, int>>>> pq;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                visited[i][j] = false;
            }
        }
        
        if(eats== shark_size){
            shark_size++;
            eats = 0;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == 9) {
                    s = {i,j};
                    q.push(s);
                    visited[i][j] = true;
                    break;
                }
            }
        }
        
        int d = 0;
        while(!q.empty()){
            int sz = q.size();
            while(sz--){
                int x = q.front().first;
                int y = q.front().second;
                q.pop();
                // cout << "x: " << x << " y: " << y <<'\n';
                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                        
                    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                    if(visited[nx][ny] || board[nx][ny] > shark_size) continue;
                    
                    // 먹을 수 있으면
                    if(0 < board[nx][ny] && board[nx][ny] < shark_size){
                        // cout << "먹이추가 "<<"d: " << d << " nx: " << nx << " ny: " << ny << '\n';
                        pq.push({d+1,{nx,ny}});
                    } else {
                        // cout << "방문: " << " nx: " << nx << " ny: " << ny << '\n';
                        q.push({nx,ny});
                    }
                    visited[nx][ny] = true;
                }
            }
            d++;
        }
        
        if(pq.size() == 0){
            break;
        } 
        
        int x = pq.top().second.first;
        int y = pq.top().second.second;
        int dist = pq.top().first;
        
        
        board[s.first][s.second] = 0;
        board[x][y] = 9;
        eats++;
        // cout << "먹음:" << " x: "<< x << " y: " << y << " dist: "<< dist << '\n';
        res += dist;
    }
    
    cout << res;

    return 0;
}
