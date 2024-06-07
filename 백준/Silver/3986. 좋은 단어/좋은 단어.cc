#include <iostream>
#include <stack>

using namespace std;

/**
좋은단어가 되려면 교차해서는 안됨.
그러려면 a나 b가 cnt가 2개가 됐을 때.
최근 나온게 해당 것과 같아야함.
*/

int main()
{
    int n;
    cin >> n;
    int res = 0;
    for(int i = 0; i < n; i++){
        string s;
        cin >> s;
        stack<char> stk;
 
        // ABAABA 
        for(char c : s){
           if(!stk.empty() && stk.top() == c){
               stk.pop();
           } else {
               stk.push(c);
           }
        }
    
        if(stk.empty()) res++;
    }
    cout << res;
    
    return 0;
}
