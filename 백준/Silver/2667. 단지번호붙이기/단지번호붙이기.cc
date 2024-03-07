#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

/**
map에서 인접한 것들의 집합을 오름차순 정렬해서 찾는 문제.

bfs를 이용
모든 노드를 탐색하며 갯수 세기

시간복잡도 O(V+E)
25*25 + 25 *25 *4 => 통과
*/

const int MX = 25;
int n;
string board[MX];
bool visited[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

vector<int> vec;

int bfs(pair<int,int> st){
    queue<pair<int,int>> q;
    
    q.push(st);
    visited[st.first][st.second] = true;
    
    int cnt = 0;
    
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >=n) continue;
                if(visited[nx][ny] || board[nx][ny] =='0') continue;
                
                q.push({nx,ny});
                visited[nx][ny] = true;
            }
            cnt++;
        }
    }
    
    return cnt;
}

int main()
{
    cin >> n;
    
    for(int i = 0; i < MX; i++){
        cin >> board[i];
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            if(board[i][j] == '0' || visited[i][j]) continue;
            vec.push_back(bfs({i,j}));
        }
    }
    
    sort(vec.begin(), vec.end());
    
    cout << vec.size() << '\n';
    for(int i = 0; i < vec.size(); i++){
        cout << vec[i] << '\n';
    }
}
