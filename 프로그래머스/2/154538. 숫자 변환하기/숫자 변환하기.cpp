#include <string>
#include <vector>
#include <queue>
#include <iostream>
using namespace std;

const int MX = 1000002;
bool ch[MX];

int add[3] = {0,0,0};
int mul[3] = {2,3,1};

int solution(int x, int y, int n) {
    add[2] = n;
    queue<int> q;
    q.push(x);
    ch[x] = 1;
    
    int d = 0;
    while(!q.empty()){
        int sz = q.size();
        
        while(sz--){
            int x = q.front();
            q.pop();
            
            if(x == y) {
                return d;
            }
            
            for(int i = 0; i < 3; i++){
                int nxt = (x + add[i]) * mul[i];
      
                if(nxt > y || ch[nxt]) continue;
                q.push(nxt);
                ch[x] = 1;
            }
        }
        d++;
    }
    return -1;
}