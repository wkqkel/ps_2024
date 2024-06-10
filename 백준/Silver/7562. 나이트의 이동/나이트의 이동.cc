#include <iostream>
#include <queue>

using namespace std;

int t, n;
pair<int, int> st, en;

const int MX = 302;
bool ch[MX][MX];

int dx[8] = {-2,-1,1,2,2,1,-1,-2};
int dy[8] = {1,2,2,1,-1,-2,-2,-1};

void bfs(){
    queue<pair<int,int>> q;
    q.push(st);
    ch[st.first][st.second] = 1;
    
    int cnt = 0;
    while(!q.empty()){
        int sz = q.size();
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            if(x == en.first && y == en.second) cout << cnt << "\n";
            
            for(int i = 0; i < 8; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(ch[nx][ny]) continue;
                q.push({nx,ny});
                ch[nx][ny] = 1;
            }
        }
        cnt++;
    }
}

int main()
{
    cin >> t;
    while(t--) {
        cin >> n;
        cin >> st.first >> st.second;
        cin >> en.first >> en.second;
        
        for(int i = 0; i < n; i++){
            fill(ch[i], ch[i]+MX, 0);
        }
        
        bfs();
    }

    return 0;
}
