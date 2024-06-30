#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool is_digit(char c){
    return '0' <= c && c <= '9';
}

bool cmp(string& a, string& b){
    if(a.size() != b.size()){
       // 1
       return a.size() < b.size();
    } else {
        int asum = 0;
        int bsum = 0;
        for(char c : a){
            if(!is_digit(c)) continue;
            asum += int (c - '0'); 
        }
        for(char c : b){
            if(!is_digit(c)) continue;
            bsum += int (c - '0'); 
        }
        // 2
        if(asum != bsum){
            return asum < bsum;
        } else {
            return a < b;
        }
    }
}

int main()
{
    int n;
    vector<string> vec;
    cin >> n;
    
    for(int i = 0; i < n; i++) {
        string v;
        cin >> v;
        vec.push_back(v);
    }
    
    sort(vec.begin(),vec.end(), cmp);
    
    for(string s: vec){
        cout << s << "\n";
    }
    return 0;
}