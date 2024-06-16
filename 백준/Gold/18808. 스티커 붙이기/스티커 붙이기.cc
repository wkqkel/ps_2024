#include <iostream>
using namespace std;

/**
설계

1. 스티커를 돌면서 
=> 100
2. 보드를 2중반복문을 돌면서,
=> 40 * 40 = 1600
3. 스티커 회전하면서
=> 4 * 40 * 40 = 약8000
4. 스티커붙일수있는지체크
=> 40 * 40 = 1600
5. 붙일수 있으면 붙인다.
=> 1600
6. 스티커 붙은 칸 수 출력
총 복잡도
100 * 1600 * (8000 + 1600 + 1600) 
=> 100 * 1000 * 10000 => 1e9 정도

---
설계에서 실수한 부분. 
2중반복문을 돌면서 -> 스티커를 회전하는게 아니라,
스티커를 회전하면서 맞는위치를 찾아야함.(반대)

*/
int n, m, k;
int a, b;

int board[50][50];
int sticker[20][20];
int rotated[20][20];

bool canAttach(int x, int y){
    for(int i = 0; i < a; i++){
        for(int j = 0; j < b; j++){
            int nx = x + i;
            int ny = y + j;
            if(nx < 0 || nx >= n || ny < 0 || ny >= m) return false;
            if(board[x + i][y + j] == 1 && sticker[i][j] == 1) {
                return false;
            }
        }
    }
    
    return true;
}

void attach(int x, int y){
    for(int i = 0; i < a; i++){
        for(int j = 0; j < b; j++){
            if(sticker[i][j] == 0) continue;
            board[x + i][y + j] = sticker[i][j];
        }
    }   
}

void rotate(){
    for(int i = 0; i < a; i++){
        for(int j = 0; j < b; j++){
            rotated[j][a - i - 1] = sticker[i][j];
        }
    }
    
    swap(a,b);
    
    for(int i = 0; i < a; i++){
        for(int j = 0; j < b; j++){
            sticker[i][j] = rotated[i][j];
        }
    }
}

int main()
{
    cin >> n >> m >> k;
  
    // 1. 스티커를 돌면서
    for(int s = 0; s < k; s++){
        cin >> a >> b;
     
        for(int i = 0; i < a; i++){
            for(int j = 0; j < b; j++){
                cin >> sticker[i][j];
            }
        }
        
        // 3. 스티커회전
        bool flag = false;
        for(int dir = 0; dir < 4; dir++){
            // 2. 보드 2중반복문 
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    // 4. 붙일 수 있는지 체크
                    if(canAttach(i,j)) {
                       flag = true;
                       // 5. 붙일 수 있으면 붙인다.
                       attach(i, j);
                       break;
                    }
                }
                if(flag) break;
            }
            if(flag) break;
            rotate();
        }
    }

    // 6. 스티커 붙은 칸 수 출력
    int cnt = 0;
    for(int i = 0; i < n; i++){
         for(int j = 0; j < m; j++){
             if(board[i][j] == 1) cnt++;
         }
     }
     cout << cnt;
         

    return 0;
}