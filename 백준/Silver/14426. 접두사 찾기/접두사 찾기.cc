#include <iostream>

using namespace std;

const int ROOT = 1;
const int MX = 10000 * 500 + 2;
int unused = 2;
bool ch[MX];
int nxt[MX][26];

void insert(string& s){
    int cur = ROOT;
    for(char c : s){
        if(!nxt[cur][c - 'a']) 
            nxt[cur][c - 'a'] = unused++;
        cur = nxt[cur][c - 'a'];
    }
    ch[cur] = true;
}

bool startsWith(string& s){
    int cur = ROOT;
    for(char c : s){
        if(!nxt[cur][c - 'a']) 
            return false;
        cur = nxt[cur][c - 'a'];
    }    
    return true;
}

int main()
{
    int n, m;
    cin >> n >> m;
    
    for(int i = 0; i < n; i++){
        string v;
        cin >> v;
        insert(v);
    }
    
    int cnt = 0;
    for(int i = 0; i < m; i++){
        string v;
        cin >> v;
        
        if(startsWith(v)) cnt++;
    }
    cout << cnt;
    
    return 0;
}
