#include <iostream>
#include <algorithm>
using namespace std;


int d[502][502];

int main()
{
    int n;
    cin >> n;
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= i; j++){
            cin >> d[i][j];
        }
    }
    
   for(int i = 1; i <= n; i++){
        for(int j = 1; j <= i; j++){
            d[i][j] = max(d[i-1][j-1], d[i-1][j]) + d[i][j];
        }
    }
    
    int mx = -1;
    
    for(int i = 1; i <=n;i++) mx = max(mx, d[n][i]);
    
    cout << mx;
    return 0;
}
