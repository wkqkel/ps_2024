#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

#define ll long long

int main()
{
    int n;
    vector<ll> vec;
    cin >> n;
    for(int i = 0; i < n; i++){
        string s;
        cin >> s;
        reverse(s.begin(), s.end());
        vec.push_back(stol(s));
    }
    
    sort(vec.begin(), vec.end());
    for(ll i : vec){
        cout << i << "\n";
    }
   
    return 0;
}