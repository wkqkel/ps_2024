#include <iostream>
#include <vector>
using namespace std;

/**
참고
https://velog.io/@sj-lee33/백준-15685-드래곤-커브-c-알고리즘-풀이 

*/
int n, x, y ,d ,g;

const int MX = 102;
bool board[MX][MX];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,-1,0,1};

string ND[4] = {"→","↑","←", "↓"};
void draw(vector<int>& dirs){
    
    int s = dirs.size();
    // cout << "d " << d << " x: " << x << " y: " << y << "\n";
    for(int i = s - 1; i >= 0; i--){
        int nd = (dirs[i] + 1) % 4;
        // cout << ND[nd] << "\n";
        x = x + dx[nd];
        y = y + dy[nd];
        // cout << "(" << x <<"," << y <<")\n"; 
        board[y][x] = 1;
        dirs.push_back(nd);
    }
}
// 0 1 => 2 1 
int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++){
        cin >> x >> y >> d >> g;
        vector<int> dirs = {};
        board[y][x] = 1;
        x = x + dx[d];
        y = y + dy[d];
        board[y][x] = 1;
        dirs.push_back(d);
        for(int i = 1; i <= g; i++){
            draw(dirs);
        }
    }
    
    int cnt = 0;
    for(int i = 0; i <= 100; i++){
        for(int j = 0; j <= 100; j++){
            if(board[i][j] && board[i+1][j] && board[i][j+1] && board[i+1][j+1]) cnt++;
        }
    }
    cout << cnt;
    return 0;
}