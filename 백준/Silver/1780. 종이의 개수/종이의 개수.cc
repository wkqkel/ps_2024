#include <iostream>
#include <cmath>
#include <map>

using namespace std;

// 3^7 = 2187

int board[2200][2200];

bool check(int n, int a, int b){
    int v = board[a][b];
    for(int i = a; i < a + n; i++){
        for(int j = b; j < b + n; j++){
            if(v != board[i][j]) return false;
        }
    }
    return true;
}

map<int,int> mp = {{-1,0},{0,0},{1,0}};

void recur(int n, int a, int b){
    if(n < 1) return;
    // cout << n << " " << a << " " << b <<"____\n";
    if(check(n, a, b)) {
        // cout << board[a][b] << "check\n";
        mp[board[a][b]]++;
        return;
    }
    recur(n / 3, a, b);
    recur(n / 3, a, b + n / 3);
    recur(n / 3, a, b + n / 3 * 2);
    recur(n / 3, a + n / 3, b);
    recur(n / 3, a + n / 3, b + n / 3);
    recur(n / 3, a + n / 3, b + n / 3 * 2);
    recur(n / 3, a + n / 3 * 2, b);
    recur(n / 3, a + n / 3 * 2, b + n / 3);
    recur(n / 3, a + n / 3 * 2, b + n / 3 * 2);
}

int main()
{
    int k; 
    cin >> k;
    for(int i = 0; i < k; i++){
        for(int j = 0; j < k; j++){
            cin >> board[i][j];
        }
    }
    recur(k, 0, 0);
    cout << mp[-1] << '\n' << mp[0] << '\n' << mp[1];

    return 0;
}
