#include <iostream>
#include <algorithm>
using namespace std;

int arr[1001];
int d[1001];
int main()
{
    int q;
    cin >> q;
    
    d[1] = 1, d[2] = 2, d[3] = 4;
    for(int i = 4; i < 11; i++){
        d[i] = d[i-3] + d[i-2] + d[i-1];
    }
    
    while(q--){
        int v;
        cin >> v;
        cout << d[v] << '\n';
    }
    return 0;
}
