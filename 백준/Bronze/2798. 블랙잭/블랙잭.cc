#include <iostream>

using namespace std;

/**
# BOJ 2798 블랙잭

## 설계
1. 경우의 수
최대 100장중 3개뽑기, 순서X, 중복불가 => O(166751) 가능

2. 차이 값 갱신
보다 클 땐 X, 차이가 기존보다 작으면 sum값 갱신 => O(1)
*/

const int k = 3;
int n, m;
int arr[102];
int tmp[k];
int diff = 1e9;
int res;
void recur(int cur, int st){
    if(cur == k){
        int sum = arr[tmp[0]] + arr[tmp[1]] + arr[tmp[2]];
        int ndiff = abs(m - sum);
        if(sum > m) return;
        if(diff > ndiff){
            diff = ndiff;
            res = sum;
        }
        return;
    }
    for(int i = st; i < n; i++){
        tmp[cur] = i;
        recur(cur+1, i + 1);
    }
}
int main()
{
    cin >> n >> m;
    for(int i = 0; i < n; i++){
        cin >> arr[i];
    }
    recur(0, 0);
    cout << res;

    return 0;
}