#include <iostream>

using namespace std;

const int MX = 102;
int dp[MX][MX][MX];

int a, b, c;

int w(int a, int b, int c){
    if(a <= 0 || b <= 0 || c<=0) {
        return 1;
    }
    
    if(dp[a][b][c] != -1) return dp[a][b][c];
    
    
    if(a > 20 || b > 20 || c > 20) {
        return dp[a][b][c] = w(20,20,20);
    }
    
    if(a < b && b < c) {
        return dp[a][b][c] =w(a,b,c-1) + w(a,b-1,c-1) - w(a,b-1,c);
    }
    
    return dp[a][b][c] = w(a-1,b,c) + w(a-1,b-1,c) + w(a-1,b,c-1) - w(a-1,b-1,c-1);
}

int main()
{
    for(int i = 0; i < MX; i++){
      for(int j = 0; j < MX; j++){
        for(int k = 0; k < MX; k++){
            dp[i][j][k] = -1;
        }  
      }  
    }
    while(true){
        cin >> a >> b >> c;
        if(a == -1 && b == -1 && c == -1) break;
        cout << "w" << "(" << a << ", " << b << ", " << c << ")" << " = ";
        cout << w(a,b,c) << '\n';
    }
    
    return 0;
}
