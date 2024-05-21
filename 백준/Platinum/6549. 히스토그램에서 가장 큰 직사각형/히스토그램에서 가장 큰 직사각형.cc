#include <iostream>
#include <stack>
#include <vector>
using namespace std;

const int MX = 100002;
long arr[MX];

int main()
{

    
    while(true){
        int n;
        cin >> n;
        if(n == 0) break;
        stack<int> stk;
        long mx = -1;
        
        for(int i = 1; i <= n; i++) {
            cin >> arr[i];
        }
        arr[0] = -1;
        arr[n+1] = -1;
        // int lt[MX];
        // int cnt = 0;
        int lt[MX];
        int rt[MX];

        stk.push(0);
        for(int i = 1; i <= n; i++){
            while(!stk.empty() && arr[stk.top()] >= arr[i]) stk.pop();
            
            lt[i] = stk.top();
         
            stk.push(i);
        }
        
        while(!stk.empty()) stk.pop();
        
        stk.push(n+1);
        for(int i = n; i >= 1; i--){
            while(!stk.empty() && arr[stk.top()] >= arr[i]) stk.pop();
            
            rt[i] = stk.top();
            
            stk.push(i);
        }
   
        for(int i = 1; i <= n; i++){
            mx = max(mx, arr[i] * (rt[i] - lt[i] - 1));
        }
        
        cout << mx << "\n";
    }
   
    return 0;
}
