#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/** 
하나의 연결요소로 만드는 적은 비용.

다 이어야함.
연결되는 두개 숫자도 정렬하고
그 다음 숫자리스트(비행기)도 정렬한 다음.
연결안돼있으면 유니온.하면서 cnt세줌
*/

int n, m; 

const int MX = 1002;
int par[MX];

vector<pair<int, int>> vec;

int find(int x){
    if(par[x] == x) return x;
    else return par[x] =find(par[x]);
}

void union_(int a, int b) {
    a = find(a);
    b = find(b);
    
    if(a == b) return;
    par[a] = b;
}

void solution(){
    cin >> n >> m;
    vec = {};
    for(int i = 1; i <= n; i++) par[i] = i;
    while(m--){
        int a, b;
        cin >> a >> b;
        if(a > b) vec.push_back({b,a});
        else vec.push_back({a,b});
    }
    
    sort(vec.begin(), vec.end());
    
    int cnt = 0;
    
    for(int i = 0; i < vec.size(); i++){
        int a = find(vec[i].first);
        int b = find(vec[i].second);
        
        if(a == b) continue;
        union_(a,b);
        cnt++;
    }
    
    cout << cnt << '\n';
}

int main()
{
    int t;
    cin >> t;
    while(t--){
        solution();
    }
    
    return 0;
}
