/**
1. 아이디어
연결요소의 갯수와 각 연결요소의 크기를 오름차순
dfs

2. 시간복잡도
dfs: O(V+E) = 25*25 * 5 <= 5e3
모든요소 순회 = 25*25 <= 1e3

가능

3. 자료구조
bool visited[MX][MX]
String board[MX]
vector<int> vec: 연결요소크기를 담을 곳(정렬필요)
*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int MX = 30;
bool visited[MX][MX];
string board[MX];
vector<int> vec;
int n;
int cnt = 0;
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void dfs(int x, int y){
  visited[x][y] = 1;
  cnt++;
  
  for(int i = 0; i < 4; i++){
    int nx = x + dx[i];
    int ny = y + dy[i];
    
    if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
    if(visited[nx][ny] || board[nx][ny] == '0') continue;
    dfs(nx,ny);
  }
}

int main(){
  cin >> n;
  for(int i = 0; i < n; i++){
    cin >> board[i];
  }
  
  for(int i = 0; i < n; i++){
    for(int j = 0; j <n; j++){
      if(board[i][j]=='0' || visited[i][j]) continue;
      cnt = 0;
      dfs(i,j);
      vec.push_back(cnt);
    }
  }
  
  sort(vec.begin(), vec.end());
  
  cout << vec.size() << '\n';
  for(int i =0 ; i < vec.size(); i++){
    cout << vec[i] << "\n";
  }
}