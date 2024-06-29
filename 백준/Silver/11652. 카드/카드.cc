#include <iostream>
#include <map>
using namespace std;
#define ll long long

map<ll, int> mp;
int main()
{
    int n;
    cin >> n;
    for(int i = 0; i < n; i++){
        ll v;
        cin >> v;
        mp[v]++;
    }

    int mx = -1;
    for(auto [k,v] : mp){
        mx = max(mx, v);
    }
    for(auto [k,v]: mp){
        if(v == mx) {
            cout << k;
            break;
        }
    }

    return 0;
}