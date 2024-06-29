#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool cmp(string a, string b){
    if(a.size() == b.size()) {
        if(a[0] == b[0]) return cmp(a.substr(1), b.substr(1));
        return a[0] < b[0];
    }
   
    return a.size() <  b.size();
}

int main()
{
    int n;
    cin >> n;
    vector<string> vec;
    for(int i = 0; i < n; i++){
        string s;
        cin >> s;
        vec.push_back(s);
    }
    sort(vec.begin(),vec.end());
    vec.erase(unique(vec.begin(), vec.end()),vec.end());
    
    sort(vec.begin(), vec.end(), cmp);
    
    for(string s: vec){
        cout << s << "\n";
    }
    return 0;
}