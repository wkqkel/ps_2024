#include <bits/stdc++.h>

using namespace std;

int main(){
    bool ch[32];
    fill(ch, ch+32, false);

    for(int i = 0; i < 28; i++){
        int v;
        cin >> v;
        ch[v] = true;
    }

    for(int i = 1; i <= 30; i++){
        if(ch[i] != true) cout << i << '\n';
    }
}