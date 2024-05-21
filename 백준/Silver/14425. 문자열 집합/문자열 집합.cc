#include <iostream>

using namespace std;

const int ROOT = 1;
const int MX = 10000 * 500 + 2;
int unused = 2;
bool ch[MX];
int nxt[MX][26];

int c2i(char c){
    return c - 'a';
}

void insert(string& s){
    int cur = ROOT;
    for(auto c : s){
        if(nxt[cur][c2i(c)] == -1)
            nxt[cur][c2i(c)] = unused++;
        cur = nxt[cur][c2i(c)];
    }
    ch[cur] = true;
}

bool search(string& s){
    int cur = ROOT;
    for(auto c : s){
        if(nxt[cur][c2i(c)] == -1)
            return false;
        cur = nxt[cur][c2i(c)];
    }
    return ch[cur];
}

void erase(string& s){
    int cur = ROOT;
    for(auto c : s){
        if(nxt[cur][c2i(c)] == -1)
            return;
        cur = nxt[cur][c2i(c)];
    }
    ch[cur] = false;
}

int main()
{
    int n, m;
    cin >> n >> m;
    
    for(int i = 0; i < MX; i++){
        fill(nxt[i], nxt[i]+26, -1);
    }
    
    for(int i = 0; i < n; i++){
        string v;
        cin >> v;
        insert(v);
    }
    int cnt = 0;
    for(int i = 0; i < m; i++){
        string v;
        cin >> v;
        if(search(v)) cnt++;
    }
    cout << cnt;
    return 0;
}
