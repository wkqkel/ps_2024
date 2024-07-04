#include <iostream>
#include <algorithm>

using namespace std;

#define ll long long
ll n, b, c;
ll arr[1000020];

int main()
{
    cin >> n;
    for(int i = 0; i < n; i++) cin >> arr[i];
    cin >> b >> c;
    
    sort(arr, arr+n);
    
    ll cnt = 0;
    for(ll i = 0; i < n; i++){
        ll v= arr[i];
        // 총감독관 1명
        v -= b;
        cnt++;
        // 부감독관 n
        if(v <= 0) continue;
        ll delta = v % c == 0 ? 0 : 1;
        cnt += v / c + delta;
    }
  
    cout << cnt;
    return 0;
}