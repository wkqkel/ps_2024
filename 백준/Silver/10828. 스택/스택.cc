#include <iostream>
#include <stack>

using namespace std;

int main()
{
    int n;
    cin >> n;
    
    stack<int> stk;
    
    while(n--){
        string cmd;
        cin >> cmd;
        
        if(cmd == "push"){
            int x; 
            cin >> x;
            stk.push(x);
        } 
        else if(cmd == "pop"){
            if(stk.empty()) cout << -1 << "\n";
            else {
                cout << stk.top() << "\n";
                stk.pop();
            }
        }
        else if(cmd == "size"){
            cout << stk.size() << "\n";
        }
        else if(cmd == "empty"){
            cout << stk.empty() << "\n";
        }
        else if(cmd == "top"){
            if(stk.empty()) cout << -1 << "\n";
            else cout << stk.top() << "\n";
        }
        
        
    }

    return 0;
}
