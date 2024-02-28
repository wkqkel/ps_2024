#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;

const int MX =  1002;

int par[MX];
int arr[MX][2];

vector<pair<int, pair<int, int>>> vec;

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
    int n;
    cin >> n;
    
    for(int i = 0; i < MX; i++) par[i] = i;
    
    for(int i = 0; i < n; i++) {
        float a, b;
        cin >> a >> b;
        arr[i][0] = a * 100;
        arr[i][1] = b * 100;
    }
    
    for(int i = 0; i < n; i++){
        for(int j = i+1; j < n;j++){
            float dx = (arr[i][0] - arr[j][0]);
            float dy = (arr[i][1] - arr[j][1]);
         
            int d = sqrt(dx * dx + dy * dy);
       
            vec.push_back({d,{i,j}});
        }
    }
    
    sort(vec.begin(),vec.end());
    
    int sum = 0;
    
    for(int i = 0; i < vec.size();i++){
       int a = find(vec[i].second.first);
       int b = find(vec[i].second.second);
       int c = vec[i].first;

       if(a == b) continue;
        sum += c;
        union_(a,b);
    }
    
    float res = float(sum) / 100;
    cout <<  res;
    
    return 0;
}
