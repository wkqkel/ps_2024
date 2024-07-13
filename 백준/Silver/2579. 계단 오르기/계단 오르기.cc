#include <iostream>

using namespace std;

int d[304][3];
int arr[304];
int main()
{
    int n;
    cin >> n;
    for(int i = 1; i <= n; i++) cin >> arr[i];
    
    for(int i = 1; i <= n; i++){
          d[i][1] = max(d[i-2][1], d[i-2][2]) + arr[i];
          d[i][2] = d[i-1][1] + arr[i];
    }
    
    cout << max(d[n][1], d[n][2]);
    
    return 0;
}