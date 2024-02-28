#include <iostream>
#include <map>
#include <vector>

using namespace std;


map<string, string> par;
map<string, long> sz;
vector<pair<string,string>> vec;
int mx = 0;
string find(string x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(string a, string b){
    a = find(a);
    b = find(b);
    if(a == b) return;

    sz[b] += sz[a];
    par[a] = b;
}


void solution(){
    int n;
    cin >> n;
    
    sz = {};
    par = {};
    vec = {};
    mx = 0;
    
    for(int i =0; i< n; i++){
        string a, b;
        cin >> a >> b;

        sz[b] = 1;
        sz[a] = 1;
        par[a] = a;
        par[b] = b;
        
        vec.push_back({a,b});
    }
    
    for(int i = 0; i < vec.size(); i++){
        string a = vec[i].first;
        string b = vec[i].second;
        
        union_(a, b);
       
        cout << sz[find(a)] << '\n';
    }
}
int main()
{

    int q;
 
    cin >> q;
    while(q--){
        solution();
    }
  
    return 0;
}
