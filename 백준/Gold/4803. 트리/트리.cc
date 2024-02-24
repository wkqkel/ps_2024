#include <iostream>
#include <vector>
#include <map>

using namespace std;

const int MX = 502;
vector<int> vec[MX];
int n, m;
int a, b;
int t = 0;

map<int, int> mp;

int par[MX];

int find(int x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a==b) return;
    
    if(b < a) par[a] = b;
    else par[b] =a;
}


void solution(){
    for(int i = 1; i <= n; i++) par[i] = i;
    
    for(int i = 0; i < m; i++){
        cin >> a >> b;
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) {
            par[pa] = 0;
            par[pb] = 0;
            continue;
        }
        union_(a,b);
    }
    
    int cnt = 0;
    bool ch[MX];
    fill(ch, ch+MX, false);
    for(int i = 1; i <= n; i++){
        int p = find(i);
        if(p == 0 || ch[p]) continue;
        cnt++;
        ch[p] = true;
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
        mp = {};
        if(n == 0 && m == 0) break;
        cout << "Case " << t << ": ";
        solution();
    };
    
    return 0;
}
