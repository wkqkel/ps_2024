#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

map<char, int> m;

vector<int> solution(vector<string> keymap, vector<string> targets) {
    vector<int> ans;
    for(char i = 'A'; i <= 'Z'; i++){
        m[i] = 0;
    }
    
    for(int i = 0; i < keymap.size(); i++){
        string str = keymap[i];
        for(int j = 0; j < str.size(); j++){
            char c = str[j];
            int v = m[c];
            if(v == 0 || v > j){
                 m[c] = j+1;
            }
        }
    }
    
    for(int i = 0; i < targets.size(); i++){
        string str = targets[i];
        int sum = 0;
        for(int j = 0; j < str.size(); j++){
            char c = str[j];
            int v = m[c];
            if(v > 0) {
               sum += v;
            } else {
               sum = -1;
               break;
            }
        }
        ans.push_back(sum);
    }
    
    return ans;
}