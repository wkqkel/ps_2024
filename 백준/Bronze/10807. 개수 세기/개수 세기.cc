#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n, v;
    int cnt = 0;
    cin >> n;
    int arr[102];
    for(int i = 0; i < n; i++) cin >> arr[i];
    cin >> v;
    for(int i = 0; i < n; i++) if(arr[i]==v) cnt++;
    cout << cnt;
    
    return 0;
}
