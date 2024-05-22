#include <iostream>

using namespace std;

const int ROOT = 1;
const int MX = 10000 * 100 + 2;
int unused = 2;
bool ch[MX];
int nxt[MX][12];

int c2i(char c){
    return c - '0';
}
void insert(string& s){
    int cur = ROOT;
    for(char c : s){
        if(nxt[cur][c2i(c)] == -1) 
            nxt[cur][c2i(c)] = unused++;
        cur = nxt[cur][c2i(c)];
    }
    ch[cur] = true;
}

bool startsWith(string& s){
    int cur = ROOT;
    for(char c : s){
        if(nxt[cur][c2i(c)] == -1)
            return false;
        cur = nxt[cur][c2i(c)];
        if(ch[cur]) {
          return true;
        }

    }    
    return true;
}

int main()
{
    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n;
    
        for(int i = 0; i < MX; i++){
            ch[i] = false;
            fill(nxt[i], nxt[i] + 10, -1);
            unused = 2;
        }
        
        bool flag = true;
        for(int i = 0; i < n; i++){
            string v;
            cin >> v;
            if(startsWith(v)){
                 flag = false;
            }
            insert(v);
        }
        
        if(flag) cout << "YES\n";
        else cout << "NO\n";
    }
    
    return 0;
}
