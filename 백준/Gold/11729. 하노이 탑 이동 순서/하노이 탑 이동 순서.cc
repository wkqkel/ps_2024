#include <iostream>

using namespace std;

/**
n개의 원판을 1->3로 옮김

1. n-1개 원판을 1->2로 옮김
2. n번 원판을 1->3으로 옮김
3. n-1개 원판을 2->3로 옮김

1개를 옮길 수 있음. n개가 가능하다고 가정하면
=> n+1도 가능.

void recur(int n, int a, int b)

recur(n-1, a, 6 - a - b);
cout << a << " " << b;
recur(n-1, 6 - a - b, a);

*/

int cnt = 0;
string res = "";

void recur(int n, int a, int b){
    if(n == 0) return;
    recur(n-1, a, 6 - a - b);
    cnt++;
    res += to_string(a) + " " + to_string(b) + '\n';
    recur(n-1, 6 - a - b, b);
}

int main()
{
    int k;
    cin >> k;
    recur(k, 1, 3);
    cout << cnt << "\n";
    cout << res;
    return 0;
}
