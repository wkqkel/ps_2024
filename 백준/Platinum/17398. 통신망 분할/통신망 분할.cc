#include <iostream>

using namespace std;

const int MX = 100002;
int par[MX];
long long sz[MX];

int n,m,q;
pair<int,int> vec[MX];
int arr[MX];
bool disconnected[MX];

int find(int x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a== b) return;
    
    sz[b] += sz[a];
    par[a] = b;
}

int main()
{
    ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

    cin >> n >> m >> q;
    
    for(int i = 1; i <= n ; i++){
        par[i] = i;
        sz[i] = 1;
    }
    
    for(int i = 1; i <= m; i++){
        int a, b;
        cin >> a >> b;
        
        vec[i] = {a,b};
    }
    
    for(int i = 1; i<= q; i++){
        cin >> arr[i];

        disconnected[arr[i]] = true;
    }
    
    for(int i = 1; i <= m; i++){
        if(disconnected[i]) continue;
        
        union_(vec[i].first,vec[i].second);
    }
    
    long long res = 0;
    
    for(int i = q; i >=1; i--){
        int v = arr[i];
        
        int a = find(vec[v].first);
        int b = find(vec[v].second);
        
        if(a != b){
            res += sz[a] * sz[b];
        }
        
        union_(a,b);
    }
    
    cout << res << '\n';

    return 0;
}
