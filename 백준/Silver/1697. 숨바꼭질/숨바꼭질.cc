#include <iostream>
#include <queue>
using namespace std;

int mul[3] = {1,1,2};
int add[3] = {1,-1,0};
int s = 0;
int n,k;
int ch[100020];

void bfs(int st){
    queue<int> q;
    
    q.push(st);
    ch[st] = true;
    
    while(!q.empty()){
        int size = q.size();
        
        while(size--){
            int cur = q.front();
            q.pop();
            
            if(cur == k) return;
            
            for(int i = 0; i < 3; i++){
                int nxt = mul[i] * cur + add[i];
                
                if(nxt < 0 || nxt > 1e5 || ch[nxt]) continue;
                
                q.push(nxt);
                ch[nxt] = true;
            }
        }
        
        s++;
    }
}
int main()
{
    cin >> n >> k;
    
    bfs(n);
    cout << s;
    return 0;
}
