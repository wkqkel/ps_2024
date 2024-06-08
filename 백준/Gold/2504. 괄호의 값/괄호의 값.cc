#include <iostream>
#include <stack>
#include <map>

using namespace std;

map<char, char> pair_map = {{')','('},{']','['}};
map<char, int> score_map = {{')',2},{']',3},{'(',2},{'[',3}};
//(([])[[]]) 30
int main()
{
    string str;
    cin >> str;

    stack<char> stk;
    int res = 0;

    int mul = 1;
    int dv = 1;
    for(int i = 0; i < str.size(); i++){
        char c = str[i];
        char nxt = str[i+1];
      
        if(c == ')' || c == ']'){
            if(stk.empty() || stk.top() != pair_map[c]) {
                res = 0;
                break;
            }
            dv *= score_map[c];
            if(i == str.size() - 1 || nxt == '[' || nxt == '('){
                res += mul;
                mul /= dv;
                dv = 1;
            }
            stk.pop();
        } else {
            mul *= score_map[c];
            stk.push(c);
        }
    }
    
    if(!stk.empty()) res = 0;
    
    cout << res;

    return 0;
}
