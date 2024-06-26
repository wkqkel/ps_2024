#include <iostream>
#include <map>

using namespace std;

int n;
map<char, int> mp;
int main()
{
    cin >> n;
    for(int i = 0; i < n; i++){
        string s;
        cin >> s;
        mp[s[0]]++;
    }
    bool lose = true;
    for(auto [k,v]: mp){
        if(v < 5) continue;
        cout << k;
        lose = false;
    }
    if(lose) cout << "PREDAJA";
    
    return 0;
}