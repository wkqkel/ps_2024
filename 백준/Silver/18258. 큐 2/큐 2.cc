#include <iostream>
#include <queue>

using namespace std;

int main()
{
    queue<int> q;
    
    ios_base :: sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    string res  = "";
    
    while(n--){
        string cmd;
        cin >> cmd;
        
        if(cmd == "push"){
            int x;
            cin >> x;
            q.push(x);
        }
        else if(cmd == "pop"){
            if(q.empty()) res += to_string(-1) + "\n";
            else {
                res += to_string(q.front()) + "\n";
                q.pop();
            }
        }
        else if(cmd == "size"){
            res += to_string(q.size()) + "\n";
        }
        else if(cmd == "empty"){
            res += to_string(q.empty()) + "\n";
        }
        else if(cmd == "front"){
            if(q.empty()) res += to_string(-1) + "\n";
            else res += to_string(q.front()) + "\n";
        }
        else if(cmd == "back"){
            if(q.empty()) res += to_string(-1) + "\n";
            else res += to_string(q.back()) + "\n";
        }
    }

    cout << res;
    return 0;
}
