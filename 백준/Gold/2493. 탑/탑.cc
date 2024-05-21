#include <iostream>
#include <stack>
using namespace std;

const int MX = 500002;
int arr[MX];

int main()
{
    int n;
    cin >> n;
    for(int i = 1; i <= n; i++){
        cin >> arr[i];
    }
    stack<int> stk;
    arr[0] = 2e9;
    stk.push(0);
    for(int i = 1; i <= n; i++){
        while(!stk.empty() && arr[stk.top()] <= arr[i]) stk.pop();
        cout << stk.top() << " ";
        stk.push(i);
    }
    return 0;
}
