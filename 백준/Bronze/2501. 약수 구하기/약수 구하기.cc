#include <iostream>

using namespace std;

int n,k;
int arr[10002];
int cnt = 0;
 
int main()
{
    cin >> n >> k;
   
    for(int i = 1; i <= n; i++){
        if(n % i == 0){
            arr[cnt++] = i;
        } 
    }
    if(k > cnt) cout << 0;
    else cout << arr[k-1];

    return 0;
}
