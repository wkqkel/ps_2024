#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

const int MX = 1010;
int n, m;
int par[MX];
int arr[MX][2];
double res;

vector<pair<double,pair<int,int>>> vec;

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
    
    for(int i = 1; i <= n; i++){
        par[i] = i;
    }
    
    for(int i = 1; i <= n; i++) {
        cin >> arr[i][0] >> arr[i][1];
    }
    
    for(int i = 0; i < m;i++){
        int a, b;
        cin >> a >> b;
        
        union_(a, b);
    }
  
    for(int i = 1; i <= n; i++){
        for(int j = i+1; j <= n; j++){
            long dx = arr[i][0] - arr[j][0];
            long dy = arr[i][1] - arr[j][1];
            
            double d = sqrt(dx * dx + dy * dy);
            
            vec.push_back({d, {i,j}});
        }
    }
    
    sort(vec.begin(), vec.end());
    
    for(int i= 0; i< vec.size(); i++){
        int a = find(vec[i].second.second);
        int b = find(vec[i].second.first);
        double c = vec[i].first;
        
        if(a == b) continue;
      
        union_(a, b);

        res += c;
    }
    
    printf("%.2f",res);
    return 0;
}
