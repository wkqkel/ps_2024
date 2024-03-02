#include <iostream>

using namespace std;

const int MX =  500002;
int par[MX];

int find(int x) {
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a == b) return;
    
    par[a] = b;
}

int n,m;
int main()
{
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        par[i] = i;
    }
    
    int res = 0;
    for(int i = 0; i < m;i++){
        int a, b;
        cin >> a >> b;
        
        a= find(a);
        b= find(b);
        if(res == 0 && a==b){
            res = i+1;
        }
        union_(a,b);
    }
    
    cout << res;
    return 0;
}
