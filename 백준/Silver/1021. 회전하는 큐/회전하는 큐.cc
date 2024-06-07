#include <iostream>
#include <deque>

using namespace std;

/**
1. 접근
양방향순환큐 -> deque
2,3번 중 최솟값 -> 매순간(그리디?)
2. 시간복잡도
n,m최대 50
최대 50 * 50 

*/

int main()
{
    deque<int> dq;
    
    int n, m;
    cin >> n >> m;
    
    for(int i = 1; i <= n; i++){
        dq.push_back(i);
    }
    
    int res = 0;
    
    for(int i =0 ; i < m; i++){
        int find;
        cin >> find;
        
        auto rt = dq.end() - 1;
        int cnt1 = 1;
        while(*rt != find) {
            rt--;
            cnt1++;
        }
        
        auto lt = dq.begin();
        int cnt2 = 0;
      
        while(*lt != find){
            lt++;
            cnt2++;
        }
        
        if(cnt1 < cnt2){
            while(dq.front() != find) {
                dq.push_front(dq.back());
                dq.pop_back();
            }
            res+=cnt1;
        } else {
            while(dq.front() != find) {
                dq.push_back(dq.front());
                dq.pop_front();
            }
            res+=cnt2;
        }
        
        dq.pop_front();
    }
    
    cout << res;

    return 0;
}
