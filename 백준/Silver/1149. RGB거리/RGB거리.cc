#include <iostream>
#include <algorithm>
using namespace std;


int d[1020][1020];

int main()
{
    int n;
    cin >> n;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= 3; j++){
            cin >> d[i][j];
        }
    }
    

    
    for(int i = 2; i <= n; i++){
        d[i][1] = min(d[i-1][2],d[i-1][3]) + d[i][1];
        d[i][2] = min(d[i-1][1],d[i-1][3]) + d[i][2];
        d[i][3] = min(d[i-1][1],d[i-1][2]) + d[i][3];
    }
    
    cout << min(d[n][1], min(d[n][2], d[n][3]));
    return 0;
}
