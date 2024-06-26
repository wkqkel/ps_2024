#include <iostream>
#include <algorithm>
using namespace std;

string pt, str;

/**
1
BD*DD
BDD
expected: NE

1
B*DD
BDD
expected: DA
*/
bool check(){
    int idx = pt.find("*");
    string prefix = pt.substr(0, idx);
    string suffix = pt.substr(idx+1);
    if(prefix.size() + suffix.size() > str.size()) return false;

    return str.substr(0, idx) ==prefix && 
    str.substr(str.size() - (pt.size() - 1 - idx)) == suffix;
}

int main()
{
    int n;
    cin >> n >> pt;

    for(int i = 0; i < n; i++){
        cin >> str;
    
        if(check()) cout << "DA\n";
        else cout << "NE\n";
    }
    
    

    return 0;
}