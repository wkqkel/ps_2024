#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

bool mf(pair<float, int> l, pair<float, int> r){
     if(l.first == r.first){
         return l.second < r.second;
     }
     return l.first > r.first;
}

vector<int> solution(int N, vector<int> stages) {
    vector<pair<float,int>> vec;
    for(int i = 1; i <= N; i++){
        int up = 0;
        int down = 0;
        for(int j = 0; j < stages.size(); j++){
            if(stages[j] == i) up++;
            if(stages[j] >= i) down++;
        }
        
        float f;
        if(down != 0) f = float(up) / float(down);
        else f = 0;

        vec.push_back({f,i});
    }
    
    sort(vec.begin(),vec.end(),  mf);
    
    vector<int> res;
    for(int i = 0; i < vec.size(); i++){
        res.push_back(vec[i].second);
    }
    return res;
}