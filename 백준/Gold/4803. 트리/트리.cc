#include <iostream>

using namespace std;

const int MX = 502;

int n, m;
int a, b;
int t = 0;

int par[MX];
int sz[MX];

int find(int x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a==b) return;
    
    sz[b] += sz[a];
    par[a] = b;
}


void solution(){
    for(int i = 1; i <= n; i++) {
        par[i] = i;
        sz[i] = 1;
    }
    
    for(int i = 0; i < m; i++){
        cin >> a >> b;
        a = find(a);
        b = find(b);
        if(a == b) {
          sz[a] = -1e5;
          sz[b] = -1e5;
        }
        union_(a,b);
    }
    
    int cnt = 0;
    
    for(int i = 1; i <= n; i++){
        if(sz[find(i)] <= 0) continue;
        
        sz[find(i)] = 0;
        cnt++;
    }
    
    if(cnt == 0) cout << "No trees.";
    else if(cnt == 1) cout << "There is one tree.";
    else cout << "A forest of " << cnt << " trees.";
    cout << '\n';
}

int main()
{
  
    while(true){
        t++;
        cin >> n >> m;
 
        if(n == 0 && m == 0) break;
        cout << "Case " << t << ": ";
        solution();
    };
    
    return 0;
}
