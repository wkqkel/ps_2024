#include <iostream>

/**
1. 먼지 확산
값만 가지고 비교한 후 반영됨


2. 순환
반시계나 시계로 돈다.
클리너를 만나면 사라짐.
*/

using namespace std;

const int MX  = 59;
int board[MX][MX];

const int CLEANER = -1;

int n, m, t;
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int delta[MX][MX];

int pos = -1;
void spread(){
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            delta[i][j] = 0;
        }
    }
   
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board[i][j] == 0 || board[i][j] == CLEANER) continue;
         
            int cnt = 0;
            for(int dir = 0; dir < 4; dir++){
                int nx = i + dx[dir];
                int ny = j + dy[dir];
         
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(board[nx][ny] == CLEANER) continue;
                delta[nx][ny] += board[i][j] / 5;
                cnt++;
            }
     
            delta[i][j] += board[i][j] - (board[i][j] / 5 * cnt);
        }
    }
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board[i][j] == CLEANER) continue;
            board[i][j] = delta[i][j];
        }
    }
}
void rotate(){
    // 위쪽(왼-위-오-아래)
    for(int i = pos - 1; i > 0; i--) {
        board[i][0] = board[i-1][0];
    }
    for(int i = 1; i < m; i++){
        board[0][i - 1] = board[0][i];
    }
    for(int i = 0; i < pos; i++){
        board[i][m-1] = board[i+1][m-1];
    }
    for(int i = m - 1; i > 0 ; i--){
        board[pos][i] = board[pos][i-1];
    }
    board[pos][1] = 0;
    
    int nxt_pos = pos + 1;
    // 아래쪽(왼-아래-오-위)
    for(int i = nxt_pos + 1; i < n - 1; i++){
        board[i][0] = board[i+1][0];
    }
    for(int i = 0; i < m - 1; i++){
        board[n-1][i] = board[n-1][i+1];
    }
    for(int i = n - 1; i > nxt_pos; i--){
        board[i][m-1] = board[i-1][m-1];
    }
    for(int i = m - 1; i > 1; i--){
        board[nxt_pos][i] = board[nxt_pos][i-1];
    }
    board[nxt_pos][1] = 0;
}

void print_board(){
    cout << "----print board------\n";
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cout << board[i][j] << " ";
        }
        cout << "\n";
    }
}
int main()
{
    cin >> n >> m >> t;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
            if(pos == -1 && board[i][j] == -1) pos = i;
        }
    }
    
    for(int i = 0; i < t; i++){
        spread();   
        rotate();  
        // print_board();
    }


    int ret = 0;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            if(board[i][j] <= 0) continue;
            ret += board[i][j];
        }
    }
    
    cout << ret;

    return 0;
}