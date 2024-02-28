#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int MX = 100002;
int par[MX];

int n,m;
vector<pair<long, pair<int,int>>> vec;

int find(int x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    if(a== b) return;
    par[a] = b;
}



int main()
{
    int n,m;
    cin >> n >> m;
    for(int i = 1; i <=n; i++) {
        par[i] = i;
    }
    
    for(int i =0; i< m; i++){
        int a, b, c;
        cin >> a>> b>> c;
        vec.push_back({c,{a,b}});
    }
    
    sort(vec.begin(),vec.end());
    
    long res =0;
    
    for(int i = 0; i < vec.size(); i++){
        int a = find(vec[i].second.first);
        int b = find(vec[i].second.second);
        int c = vec[i].first;
        
        if(a == b) continue;
        
        union_(a, b);
        res+= c;
    }
    
    cout << res;

    return 0;
}
