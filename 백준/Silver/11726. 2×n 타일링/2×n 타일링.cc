#include <iostream>
#include <algorithm>
using namespace std;

int arr[10010];
int d[10010];
int main()
{
    int n;
    cin >> n;
    
    d[1] = 1, d[2] = 2;
    for(int i = 3; i <= n; i++){
        d[i] = (d[i-2] + d[i-1]) % 10007;
    }
   
    cout << d[n];
    return 0;
}
