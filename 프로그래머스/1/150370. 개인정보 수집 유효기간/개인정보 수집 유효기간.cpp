#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

map<char, int> termMap;

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    
    int sy = stoi(today.substr(0,4));
    int sm = stoi(today.substr(5,7));
    int sd = stoi(today.substr(8,10));
    
    for(string t : terms){
        termMap[t[0]] = stoi(t.substr(2));
    }
    
    int idx = 0;
    
    for(string p : privacies){
       idx++;
       int y = stoi(p.substr(0,4));
       int m = stoi(p.substr(5,7));
       int d = stoi(p.substr(8,10));
        
       m = m + termMap[p[11]];
       
       if(m % 12 == 0){
           y += (m / 12) -1;
           m = 12;
       } else {
            y += m / 12;
            m %= 12;
       }
   
  
        
       if(y > sy || (y == sy && m > sm) || (y == sy && m == sm && d > sd)) {
           continue;
       }
       answer.push_back(idx);
    }
    
    
    return answer;
}