#include <iostream>

using namespace std;

const int ROOT = 1;
const int MX = 100000 * 10 + 2;
int unused = 2;
int ch[MX];
int nxt[MX][26];

int c2i(char c){
    return c - 'a';
}
void insert(string& s){
    int cur = ROOT;
    for(char c : s){
        if(nxt[cur][c2i(c)] == -1) 
            nxt[cur][c2i(c)] = unused++;
        cur = nxt[cur][c2i(c)];
    }
    ch[cur]++;
}

void printStarName(string& s){
    int cur = ROOT;
    string str = "";
    
    bool flag = true;
    for(char c : s){
        str += c;
        if(nxt[cur][c2i(c)] == -1){
            flag = false;
            break;
        } 
        cur = nxt[cur][c2i(c)];
    }    
    
    cout << str;
    if(flag && ch[cur]) cout << ch[cur] + 1;
    cout << "\n";
}

int main()
{

    ios_base::sync_with_stdio(false);
    cin.tie(0);

    int n;
    cin >> n;

    for(int i = 0; i < MX; i++){
        fill(nxt[i], nxt[i] + 26, -1);
    }
    
    for(int i = 0; i < n; i++){
        string v;
        cin >> v;
        printStarName(v);
        insert(v);
    }
    
    return 0;
}
