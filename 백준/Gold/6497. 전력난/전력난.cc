#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

const int MX =  200020;
int m,n;
int x,y;
long long z;

int par[MX];
vector<pair<long, pair<int,int>>> vec;

int find(int x){
    if(par[x] ==x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a==b) return;
    
    par[a] = b;
}

void solution(){
    vec = {};
    for(int i = 0; i < m; i++){
        par[i] = i;
    }
    
    for(int i = 0; i < n; i++){
        cin >> x >> y >> z;
        
        vec.push_back({z,{x,y}});
    }
    
    sort(vec.begin(),vec.end());

    long sum = 0;
    long used = 0;
    for(int i = 0; i < n; i++){
        int a = find(vec[i].second.first);
        int b = find(vec[i].second.second);
        long c = vec[i].first;
        
        sum += c;
        if(a == b) continue;
      
        used += c;
        union_(a,b);
    }
    
    cout << (sum - used) << '\n';
}

int main()
{
    int t;
    while(true){
        cin >> m >> n;
        if(m == 0 && n == 0) break;
        solution();
    }
    
    return 0;
}
