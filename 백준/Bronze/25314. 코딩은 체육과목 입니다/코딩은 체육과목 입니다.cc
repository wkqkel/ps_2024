#include <bits/stdc++.h>

using namespace std;

int main()
{
    int n;

    cin >> n;
    
    string res = "int";
    for(int i = 0; i < n / 4; i++){
        res = "long " + res;
    }
    
    cout << res;
}
