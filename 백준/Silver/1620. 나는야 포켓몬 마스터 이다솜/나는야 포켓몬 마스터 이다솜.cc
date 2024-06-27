#include <iostream>
#include <map>
#include <vector>
using namespace std;

map<string, int> mp;
vector<string> vec;

int n, m;
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> n >> m;
    vec.push_back("_");
    for(int i = 1; i <= n; i++){
        string v;
        cin >> v;
        vec.push_back(v);
        mp[v] = i;

    }
    string ret = "";
    int aa = 52;

    while(m--){
        string v;
        cin >> v;
        char upper = toupper(v[0]);
        if('A' <= upper && upper <= 'Z'){
            ret += to_string(mp[v]);
        } else {
            ret += vec[stoi(v)];
        }
        ret += "\n";
    }
    
    cout << ret;

    return 0;
}