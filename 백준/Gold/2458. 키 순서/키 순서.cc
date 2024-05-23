#include <iostream>
#include <vector>

using namespace std;

int n, m;
const int MX = 502;
vector<int> vec1[MX];
vector<int> vec2[MX];

int sub[MX];
bool ch1[MX];
bool ch2[MX];
int cnt = 0;

void dfs(int cur, auto& vec, auto& ch){
    ch[cur] = 1;
    for(int i = 0; i < vec[cur].size(); i++){
        int nxt = vec[cur][i];
        if(ch[nxt]) continue;
        cnt++;
        dfs(nxt, vec, ch);
    }
}

int main()
{
    cin >> n >> m;
    
    for(int i = 1; i <= n; i++){
        sub[i] = 0;
    }
    
    for(int i = 0; i < m; i++){
        int a, b;
        cin >> a >> b;
        vec1[a].push_back(b);
        vec2[b].push_back(a);
    }
    
    int res = 0;
    for(int i = 1; i <= n; i++){
        cnt = 0;
        fill(ch1, ch1+MX, 0);
        fill(ch2, ch2+MX, 0);
        dfs(i, vec1, ch1);
        dfs(i, vec2, ch2);
        if(cnt == n -1) res++;
    }
    
    cout << res;
    return 0;
}
