#include <iostream>
#include <stack>
#include <map>
using namespace std;

map<char, char> pair_c = {{')','('},{']','['}};

int main()
{
    string line;
    
    while(true){
        getline(cin, line);
        if(line == ".") break;
        stack<char> stk;
        bool is_balance = true;
        for(char c : line){
            if(c == '[' || c == '(') {
                stk.push(c);
            }
            else if(c == ']' || c == ')'){
                if(stk.empty()) {
                    is_balance = false;
                    break;
                }
                char top = stk.top();
                stk.pop();
                if(top != pair_c[c]){
                    is_balance = false;
                    break;
                }
            }
        }
                    
        if(!stk.empty() || !is_balance) cout << "no\n";
        else cout << "yes\n";
    }
    
    return 0;
}
