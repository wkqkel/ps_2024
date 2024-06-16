#include <iostream>

using namespace std;

int n, m;

int arr[30];
int res;
    
void recur(int cur, int sum){
    if(cur == n){
        if(sum == m) res++;
        return;
    }
    recur(cur+1, sum + arr[cur]);
    recur(cur+1, sum);
}
int main()
{
    cin >> n >> m;
    for(int i = 0; i < n; i++){
        cin >> arr[i];
    }
    
    recur(0, 0);
    if(m == 0) res--;
    cout << res;
    
    return 0;
}