#include <iostream>

using namespace std;

/** 
하나의 연결요소인지 체크하는 문제
유니온파인드로 여행계획에 대해 조상이 다른게 나오면 
NO
*/

const int MX = 1002;
int n, m;
int par[MX];
int plan[MX];

int find(int x){
    if(par[x] == x) return x;
    else return par[x] = find(par[x]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    
    if(a == b) return;
    par[a] = b;
}

int main()
{
    cin >> n >> m;
    for(int i = 1; i <= n; i++) par[i] = i;
    
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            int v;
            cin >> v;
            if(v == 1) union_(i, j);
        }
    }

    for(int i = 0; i < m; i++) cin >> plan[i];
    
    bool flag = true;
    for(int i = 0; i < m-1;i++){
        if(find(plan[i])!= find(plan[i+1])) flag = false;
    }
    
    if(flag) cout << "YES";
    else cout << "NO";

    return 0;
}
