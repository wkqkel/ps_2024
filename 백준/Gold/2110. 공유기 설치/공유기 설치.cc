#include <iostream>
#include <algorithm>

using namespace std;

int n, c;
int arr[200020];

bool solve(int s){
    // 거리가 n이상이게 c개 설치할 수 있는지
    int cnt = 1;
    int prev = arr[0];
    
    for(int i = 1; i < n; i++){
        if(arr[i] - prev >= s){
            cnt++;
            prev = arr[i];
        }
    }
    
    return cnt >= c;
}

int main()
{

    cin >> n >> c;
    for(int i = 0; i < n;i++) cin >> arr[i];
    
    sort(arr, arr+n);
    
    int l = 1;
    int r = 1e9+1;
    
    while(l + 1 < r){
        int mid = (l+r) / 2;
        if(solve(mid)) l = mid;
        else r = mid;
    }
    
    cout << l;
    return 0;
}