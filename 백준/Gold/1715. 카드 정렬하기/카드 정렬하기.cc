#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

#define pq priority_queue

int main()
{
 
    pq<int, vector<int>, greater<int>> pq; // min pq
    
    int n;
    cin >> n;
    
    for(int i = 0; i < n; i++){
        int v;
        cin >> v;
        pq.push(v);
    }
    
    int ret = 0;
    
    while(pq.size() > 1){
        int mn1 = pq.top();
        pq.pop();
        int mn2 = 0;
        
        mn2 = pq.top();
        pq.pop();
      
        if(!pq.empty()) pq.push(mn1+mn2);
        
        ret += mn1 + mn2;
    }
    
    cout << ret;
    
    return 0;
}