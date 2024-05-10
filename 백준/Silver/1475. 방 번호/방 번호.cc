#include <iostream>
#include <cmath>

using namespace std;
int ch[10];

int main()
{

    int n;
    cin >> n;
    for(char c: to_string(n)){
        if(c == '9') ch[6]++;
        else ch[c - '0']++;
    }
    ch[6] = ceil(float(ch[6]) / 2);

    int mx = 0;
    for(int i : ch){
       mx = max(mx, i);
    }
    cout << mx;
}
