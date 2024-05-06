#include <string>
#include <vector>
#include <iostream>

using namespace std;

const int MX = 102;
vector<int> vec[MX];

int a, b;
int par[MX];
int sz[MX];

int find(int a){
    if(par[a] == a) return a;
    else return par[a] = find(par[a]);
}

void union_(int a, int b){
    a = find(a);
    b = find(b);
    if(a==b) return;
    sz[b] += sz[a];
    par[a] = b;
}

int solution(int n, vector<vector<int>> wires) {
    int mn = 1e9;
    
    for(int i = 0; i < wires.size(); i++){
        for(int j = 0; j < MX; j++){
            par[j] = j;
            sz[j] = 1;
        }
        int fa, fb;
        for(int j = 0; j < wires.size(); j++){
            int a = wires[j][0];
            int b = wires[j][1];
            if(j == i){
                fa = a;
                fb = b;
                continue;
            } else {
                union_(a,b);
            } 
        }
        mn = min(mn, abs(sz[find(fa)] - sz[find(fb)]));
    }
    
    return mn;
}