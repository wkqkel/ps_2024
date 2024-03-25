#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <iostream>
using namespace std;

int solution(vector<int> order) {
    queue<int> q; // belt1
    stack<int> s; // belt2
    queue<int> o;
    
    for(int i= order.size()-1; i >=0 ; i--){
        o.push(order[i]);
    }
  
    
    for(int i = 1; i <= order.size(); i++){
        q.push(i);
    }
    
    int idx = 0;
    
    int cnt = 0;
    
    while((!q.empty() && q.front() <= order[idx]) 
          || (!s.empty() && s.top() == order[idx])){
        if(!q.empty() && q.front() < order[idx]){
            s.push(q.front());
            q.pop();
        }
        else if(!s.empty() && s.top() == order[idx]) {
            s.pop();
            idx++;
            cnt++;
        } 
        else if(!q.empty() && q.front() ==  order[idx]){
            q.pop();
            idx++;
            cnt++;
        }
    }

    return cnt;
}