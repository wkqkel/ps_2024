#include <iostream>
#include <queue>

using namespace std;

int main()
{
    queue<int> q;
    
    int n;
    cin >> n;
    while(n--){
        string cmd;
        cin >> cmd;
        
        if(cmd == "push"){
            int x;
            cin >> x;
            q.push(x);
        }
        else if(cmd == "pop"){
            if(q.empty()) cout << -1 << "\n";
            else {
                cout << q.front() << "\n";
                q.pop();
            }
        }
        else if(cmd == "size"){
            cout << q.size() << "\n";
        }
        else if(cmd == "empty"){
            cout << q.empty() << "\n";
        }
        else if(cmd == "front"){
            if(q.empty()) cout << -1 << "\n";
            else cout << q.front() << "\n";
        }
        else if(cmd == "back"){
            if(q.empty()) cout << -1 << "\n";
            else cout << q.back() << "\n";
        }
    }

    return 0;
}
