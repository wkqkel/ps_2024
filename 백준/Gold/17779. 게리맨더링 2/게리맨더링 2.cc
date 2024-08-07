#include <iostream>
#include <algorithm>

const int MX = 24;
int N;
int board[MX][MX];
int board2[MX][MX];

using namespace std;

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int ret = 1e7;

void sep(int x, int y, int d1, int d2){
    int n = N;
    n++;
    for(int r = 1; r < x + d1; r++){
        for(int c = 1; c <= y; c++){
            board2[r][c] = 1;
        }
    }
    
     for(int r = 1; r <= x + d2; r++){
        for(int c = y + 1; c <= n; c++){
            board2[r][c] = 2;
        }
    }
    
    
    for(int r = x + d1; r <= n; r++){
        for(int c = 1; c < y - d1 + d2; c++){
            board2[r][c] = 3;
        }
    }
    
    for(int r = x + d2 + 1; r <= n; r++){
        for(int c = y - d1 + d2; c <= n; c++){
            board2[r][c] = 4;
        }
    }
    
    for(int i = 0; i <= n; i++){
        if(i <= d1) board2[x+i][y-i] = 5;
        if(i <= d2) board2[x+i][y+i] = 5;
        if(i <= d2) board2[x+d1+i][y-d1+i] = 5;
        if(i <= d1) board2[x+d2+i][y+d2-i] = 5;
    }
    
    for(int r = 1; r <= n; r++){
        for(int c = 1; c <= n; c++){
            if(board2[r][c] == 5) continue;

            int cnt = 0;
            for(int dir = 0; dir < 4; dir++){
                int nx = r;
                int ny = c;
             
                while(true){
                    nx += dx[dir];
                    ny += dy[dir];
                    if(nx < 1 || nx > n || ny < 1 || ny > n) break;
                    if(board2[nx][ny] == 5) {
                        cnt++;
                        break;
                    }
                }
            }
      
            if(cnt == 4) board2[r][c] = 5;
        }
    }
}

void get_score(){
    int cnt[6] = {0,0,0,0,0,0};
    
    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
         
            cnt[board2[i][j]] += board[i][j];
        }
    }
    
    for(int i = 1; i <= 5; i++){
        if(cnt[i] == 0) return;
    }
    
    int mn = 1e7;
    int mx = -1;
    
    for(int i = 1; i <= 5; i++){
        mn = min(mn, cnt[i]);
        mx = max(mx, cnt[i]);
    }

    ret = min(ret, mx - mn);
}
int main()
{
    cin >> N;

    for(int i = 1; i <= N; i++){
        for(int j = 1; j <= N; j++){
            cin >> board[i][j];
        }
    }

    for(int x = 1; x <= N; x++){
        for(int y = 1; y <= N; y++){
            for(int d1 = 1; d1 <= N; d1++){
                for(int d2 = 1; d2 <= N; d2++){
                    if(x + d1 + d2 <= N && 1 <= y - d1 && y < y + d2 && y + d2 <= N){
                        sep(x,y,d1,d2);
                        get_score();
                    }  
                }
            }
        }
    }
    
    cout << ret;
    
    return 0;
}