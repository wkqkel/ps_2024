#include <iostream>
#include <stack>

using namespace std;

int main()
{
    stack<int> stk;

    int n;
    cin >> n;
    
    string res = "";
    int idx = 1;
    for(int i = 0; i < n;i++){
      int v;
      cin >> v;
      if(idx <= v){
        while(idx <= v) {
          stk.push(idx++);
          res += "+\n";
        }
        res += "-\n";
        stk.pop();
      } else {
          int f = stk.top();
          if(f == v) {
              res += "-\n";
              stk.pop();
          } else {
              res = "NO";
              break;
          }
      }
    }
    
    cout << res;
    return 0;
}
