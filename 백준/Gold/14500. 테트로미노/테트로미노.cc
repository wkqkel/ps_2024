#include <iostream>
#include <vector>
using namespace std;

#define ll long long

/**
## 문제이해
테트로미노를 하나 놓아서,
회전도 하면서, 
합을 최대로 만듦.

---
스티커별로  => 5
하나씩 붙여보고 * 회전4방향 => 500 * 500 * 4 * 16
갯수를 센다.

이 때 맵을 벗어나면 안됨.
반전 - 빼먹음
*/
int n, m;
const int MX = 520;
int board[MX][MX];

string sticker[5][4] = {
    {"1111","0000","0000","0000"},
    {"1100","1100","0000","0000"},
    {"1000","1000","1100","0000"},
    {"1000","1100","0100","0000"},
    {"1110","0100","0000","0000"},
};
ll mx = -1;
void mirror_x(int idx){
    auto origin = sticker[idx];
    string mirrored[4];
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            mirrored[i][4-j-1] = origin[i][j];
        }
    }
    
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            sticker[idx][i][j] = mirrored[i][j];
        }
    }
}

void mirror_y(int idx){
    auto origin = sticker[idx];
    string mirrored[4];
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            mirrored[4-i-1][j] = origin[i][j];
        }
    }
    
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            sticker[idx][i][j] = mirrored[i][j];
        }
    }
}


void rotate(int idx){
    auto origin = sticker[idx];
    string rotated[4];
    
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            rotated[i][j] = origin[j][4-i-1];
        }
    }
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            sticker[idx][i][j] = rotated[i][j];
        }
    }
}

void attach(int idx, int x, int y){
    ll sum = 0;
    int cnt = 0;

    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            int nx = x + i;
            int ny = y + j;
           
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        
            if(sticker[idx][i][j] == '0') continue;
            cnt++;
      
            sum += board[nx][ny];
        }
    }
    if(cnt != 4) return;

    mx = max(mx, sum);
}
int main()
{
 
    cin >> n >> m;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
    // 0. 스티커별로
    for(int i = 0; i < 5; i++){
        // 1. 맵을 돌면서
        for(int x = -4; x < n+4; x++){
            
            for(int y = -4; y < m+4; y++){
                // 미러
                for(int mir = 0; mir < 4; mir++){
                    if(mir % 2 == 0) mirror_x(i);
                    else mirror_y(i);
                     // 2. 회전시킨 후
                    for(int dir = 0; dir < 4; dir++){
                        rotate(i);
                        attach(i, x, y);
                    }
                }
               
            }
        }
    }

    cout << mx;
    return 0;
}