#include <iostream>
#include <vector>

using namespace std;

const int MX = 20020;
int k,v,e;
int a,b;

vector<int> vec[MX];
bool visited[MX];
int color[MX];

bool flag = true;

void dfs(int cur, int col){
    visited[cur] = 1;
    color[cur] = col;
    
    for(int i = 0; i < vec[cur].size(); i++){
        int nxt = vec[cur][i];
        if(color[nxt]!=0 && color[nxt]==color[cur]) {
            flag = false;
        }
        if(visited[nxt]) continue;
        dfs(nxt, col * -1);
    }
}

void init(){
    
    flag = true;
    for(int i = 0; i <MX;i++){
        color[i] = 0;
        visited[i] = 0;
        vec[i] = {};
    }
    
    cin >> v >> e;
    for(int i = 0; i < e; i++){
        cin >> a >> b;
        vec[a].push_back(b);
        vec[b].push_back(a);
    }
}


int main()
{
    cin >> k;
    while(k--){
        init();
        for(int i = 1; i <= v; i++){
            if(visited[i]) continue;
            dfs(i,1);
        }
        if(flag) cout << "YES\n";
        else cout << "NO\n";
    }

    return 0;
}
