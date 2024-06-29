#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;

int n, c;
vector<int> vec;
// 길이는 1000인데 크기는 1e9므로 배열아닌 맵 활용
map<int, int> sz;

// 바꿔야할때만 트루
bool cmp(int a, int b){
    if(sz[a] == sz[b]){
        return false;
    }
    return sz[a] > sz[b];
}

int main()
{
 
    cin >> n >> c;

    for(int i = 0; i < n; i++){
        int v;
        cin >> v;
        if(!sz[v]) vec.push_back(v);
        sz[v]++;
    }
    stable_sort(vec.begin(), vec.end(), cmp);

    for(int v: vec){
        while(sz[v]--){
            cout << v << " ";
        }
    }
    return 0;
}