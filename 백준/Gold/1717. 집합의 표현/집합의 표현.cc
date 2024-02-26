#include <iostream>

using namespace std;

int n, m;

const int MX = 1000003;
int par[MX];

int find(int x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a == b) return;
    par[a] = b;
}

// n 은 100만 logN 10^6 => log(2^10)2 = 20
// https://www.acmicpc.net/problem/1717

int main()
{
    cin >> n >> m;
    string res = "";
    for(int i = 0; i <= n; i++) par[i] = i;
    
    while(m--){
        int a, b, c;
        cin >> a >> b >> c;
        if(a == 0) union_(b, c);
        else if(find(b) == find(c)) res += "YES\n";
        else res += "NO\n";
    }
    cout << res;
    return 0;
}
