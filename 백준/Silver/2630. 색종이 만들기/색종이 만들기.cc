#include <iostream>

using namespace std;

int n;
int board[130][130];

int res[2] = {0,0};

bool check(int k, int a, int b){
    int v = board[a][b];
    for(int i = a; i < a + k; i++){
        for(int j = b; j < b + k; j++){
            if(board[i][j] != v) return false;
        }
    }
    return true;
}

void recur(int k, int a, int b){
    if(k == 0) return;
    if(check(k, a, b)){
        res[board[a][b]]++;
        return;
    }
    recur(k / 2, a, b);
    recur(k / 2, a, b + k / 2);
    recur(k / 2, a + k / 2, b);
    recur(k / 2, a + k / 2, b + k / 2);
}

int main()
{
    cin >> n;
    
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> board[i][j];
        }
    }
    recur(n, 0, 0);
    cout << res[0] << '\n' << res[1];
    return 0;
}
