#include <iostream>

using namespace std;

int arr[100020];
int n, m;
int mx = -1e9;
int main()
{
    cin >> n >> m;
    for(int i = 0; i < n; i++) cin >> arr[i];
    int sum = 0;
    
    for(int i = 0; i < m; i++) sum += arr[i];
    mx = sum;
    for(int i = m; i < n; i++){
        sum += arr[i] - arr[i - m];
        mx = max(sum, mx);
    }
    
    cout << mx;
    return 0;
}