#include <iostream>

using namespace std;

const int MX = 70;
int k;
char board[MX][MX];

bool check(int n, int a, int b){
    int v = board[a][b];
    for(int i = a; i < a + n; i++){
        for(int j = b; j < b + n; j++){
            if(v != board[i][j]) return false;
        }
    }
    return true;
}

void recur(int n, int a, int b){
    if(n == 0) return;
    if(check(n, a, b)){
        cout << board[a][b];
        return;
    }
    cout << "(";
    recur(n / 2, a, b);
    recur(n / 2, a, b + n / 2);
    recur(n / 2, a + n / 2, b);
    recur(n / 2, a + n / 2, b + n /2);
    cout << ")";
}

int main()
{
    cin >> k;
    for(int i = 0; i < k; i++){
        for(int j = 0; j < k; j++){
            cin >> board[i][j];
        }
    }
   
    recur(k, 0, 0);
    return 0;
}
