#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <iostream>

using namespace std;

/**
1. 정렬한다음


*/

int solution(vector<int> sco, int K) {
    priority_queue<int, vector<int>, greater<int>> q;
    for(int i = 0; i < sco.size(); i++){
        q.push(sco[i]);
    }
    int cnt = 0;
    while(q.top() < K){
        int f = q.top();
        q.pop();
        if(q.empty()) return -1;
        int s = q.top();
        q.pop();
        int v = f + s * 2;
        q.push(v);
        cnt++;
    }
    
    return cnt;
}