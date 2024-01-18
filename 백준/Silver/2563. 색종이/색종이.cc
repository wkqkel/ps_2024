#include <iostream>

using namespace std;

int n;
bool board[101][101];
   
int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++){
        int sx, sy;
        cin >> sx >> sy;
        for(int y = sy; y < sy + 10; y++){
            for(int x = sx; x < sx + 10; x++){
                board[y][x] = true;
            }
        }
    }
    
    int res = 0;
    for(int i = 1; i <= 100; i++){
        for(int j = 1; j <= 100; j++){
            if(board[i][j]) res++;
        }
    }
    
    cout << res;
    return 0;
}
