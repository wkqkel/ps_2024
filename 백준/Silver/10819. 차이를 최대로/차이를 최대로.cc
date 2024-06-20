#include <iostream>

using namespace std;

/**
배열의 순서를 바꿔서, 모든 요소의 합 구하기

## 설계 
### 완탐
1. 8개를 순서바꾸는데 
O(109601) 
2. 다 더해서, 최댓값 갱신
O(16)

109601 * 16 => 가능
*/

int n;
int arr[10];
int tmp[10];
int ch[10];

int mx = -1;

int cal(){
    int ret = 0;
    for(int i = 0; i <= n - 2; i++){
        ret += abs(arr[tmp[i]] - arr[tmp[i+1]]);
    }
    return ret;
}
void recur(int cur){
    if(cur == n){
        mx = max(cal(), mx);
        return;
    }
    for(int i = 0; i < n; i++){
        if(ch[i]) continue;
        tmp[cur] = i;
        ch[i] = 1;
        recur(cur+1);
        ch[i] = 0;
    }
}
int main()
{
    cin >> n;
    for(int i =0; i < n; i++){
        cin >> arr[i];
    }
    recur(0);
    cout << mx;
    return 0;
}