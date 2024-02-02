#include <iostream>
#include <algorithm>
using namespace std;

int arr[1001];
int d[1001];
int main()
{
    int n;
    cin >> n;
    
    for(int i = 1; i <= n; i++) cin >> arr[i];
    
    d[1] = arr[1];
    d[2] = arr[1] + arr[2];
    for(int i = 3; i <= n; i++){
        d[i] = max(d[i-2], arr[i-1] + d[i-3]) + arr[i];
    }
   
    cout << d[n];
    return 0;
}
