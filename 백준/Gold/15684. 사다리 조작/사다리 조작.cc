#include <iostream>
#include <vector>

using namespace std;


const int MX = 34;
int N, M, H;
int mn = 1e9;
int line[MX][MX];

bool check(){
    for(int i = 1; i <= N; i++){
        int pos = i;
        for(int j = 1; j <= H; j++){
            if(line[j][pos]) pos++;
            else if(line[j][pos-1]) pos--;
        }
        if(pos != i) return false;
    }
    return true;
}
void dfs(int h, int cnt){
    if(cnt > 3) return;
    if(check()){
        mn = min(mn, cnt);
        return;
    }
    
    for(int i = h; i <= H; i++){
        for(int j = 1; j < N; j++){
            if(line[i][j] || line[i][j-1] || line[i][j+1]) continue;
            line[i][j] = 1;
            dfs(i, cnt+1);
            line[i][j] = 0;
        }
    }
}
int main()
{
 
    cin >> N >> M >> H;
    for(int i = 0; i < M; i++){
        int a, b; cin >> a >> b;
        line[a][b] = 1;
    }
    
    dfs(1, 0);
    
    if(mn > 3) cout << -1;
    else cout << mn;

    return 0;
}