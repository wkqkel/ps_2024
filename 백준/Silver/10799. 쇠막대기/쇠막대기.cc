#include <iostream>
#include <stack>
using namespace std;

int main()
{
    string str;
    cin >> str;
    
    stack<char> stk;
    int res = 0;
    
    for(int i = 0; i < str.size(); i++){
        char c = str[i];
        char prev = str[i-1];
     
        if(c == '(') {
            stk.push('(');
        }
        else {
            stk.pop();
            if(prev == ')') res++;
            else res += stk.size();
        }
    }
    cout << res;

    return 0;
}
