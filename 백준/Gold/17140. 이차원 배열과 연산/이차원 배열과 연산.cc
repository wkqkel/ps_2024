#include <iostream>
#include <map>
#include <vector>
#include <algorithm>

#define P pair<int, int>
#define X first
#define Y second
using namespace std;

const int MX = 104;
int board[MX][MX];
int r, c, k;
int n = 3, m = 3;

bool cmp(P a, P b){
    if(a.Y == b.Y) return a.X < b.X;
    
    return a.Y < b.Y;
}

void r_sort(){
    for(int i = 0; i < n; i++){
        map<int, int> mp = {};
        for(int j = 0; j < m; j++){
            if(board[i][j] == 0) continue;
           
            mp[board[i][j]]++;
        }
        vector<P> vec;
        for(auto v : mp){
            vec.push_back({v.X, v.Y});
        }
        
        
        sort(vec.begin(), vec.end(), cmp);
        int l = vec.size() * 2;
        int tmp[l];
        for(int j = 0; j < vec.size(); j++){
            tmp[j*2] = vec[j].X;
            tmp[j*2+1] = vec[j].Y;
        }
        for(int j = 0; j < l; j++){
            board[i][j] = tmp[j];
        }
        for(int j = l; j < MX; j++){
            board[i][j] = 0;
        }
        m = max(m, l);
    }
}

void c_sort(){
    for(int y = 0; y < m; y++){
        map<int, int> mp = {};
        for(int x = 0; x < m; x++){
            if(board[x][y] == 0) continue;
           
            mp[board[x][y]]++;
        }
        vector<P> vec;
        for(auto v : mp){
            vec.push_back({v.X, v.Y});
        }
        
        sort(vec.begin(), vec.end(), cmp);
        int l = vec.size() * 2;
        int tmp[l];
        for(int j = 0; j < vec.size(); j++){
            tmp[j*2] = vec[j].X;
            tmp[j*2+1] = vec[j].Y;
        }
        for(int x = 0; x < l; x++){
            board[x][y] = tmp[x];
        }
        for(int x = l; x < MX; x++){
            board[x][y] = 0;
        }
        n = max(n, l);
    }
}

int main()
{
    cin >> r >> c >> k;
    r--;
    c--;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            cin >> board[i][j];
        }
    }
    
    int t = 0;
    while(t <= 100 && board[r][c] != k){
        if(n < m){
            c_sort();
        } else {
            r_sort();
        }
        t++;
    }
    
    if(t > 100) cout << -1;
    else cout << t;

    return 0;
}