#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

queue<pair<int,int>> q;

int solution(int bridge, int weight, vector<int> trucks) {
    int idx = 0;
    int cur = 0;
    int sum = 0;
    
    while(true){
        // 탈출조건
        
        if(trucks.size() <= idx && q.empty()) break;
        cur++;
        // 큐가 비지않았으면,
        while(!q.empty()){
            int w = q.front().first;
            int prev = q.front().second;
            if(cur - prev < bridge) break;
            q.pop();
            sum -= w;
        }
        
        if(sum + trucks[idx] > weight || trucks.size() <= idx) continue; 
    
        q.push({trucks[idx], cur});
        sum += trucks[idx];
        idx++;
    }
              
    return cur;
}