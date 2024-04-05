#include <iostream>
#include <vector>

using namespace std;

int n;
int A[1002];

int dp[1002];

/**
LIS 문제 + 경로추적
LIS
dp[x]를 A[x]를 마지막 값으로 가질 때 가장 긴 부분 수열의 길이로 정의

이중포문을 돌면서,
초기 mx를 0으로 두고,
자신보다 앞에 있는 값 중 더 작은 값을 가진 dp들의 최댓값 + 1로 설정.

경로추적은
이렇게 구한 최댓값에서,
앞에 있는 값중 길이가 -1씩이면서 더 작은 값을 찾으면 됨.

*/

int main()
{
    cin >> n;
    for(int i = 0; i < n; i++){
        cin >> A[i];
    }
    
    for(int i = 0; i < n; i++){
        int mx = 0;
        for(int j = 0; j < i; j++){
            if(A[i] > A[j]) mx = max(mx, dp[j]);
        }
        dp[i] = mx+1;
    }
    
    int mx = -1;
    int mxIdx = -1;
    for(int i = 0; i < n; i++){
        if(mx < dp[i]){
            mx = dp[i];
            mxIdx = i;
        }
    }
 
    vector<int> vec;
    int cur = mx-1;
    vec.push_back(A[mxIdx]);
    
    for(int i = mxIdx-1; i >= 0; i--){
        if(dp[i] == cur && A[i] < vec[vec.size()-1]){
            vec.push_back(A[i]);
            cur--;
        }
    }
    
    cout << mx << "\n";
    
    for(int i = vec.size()-1; i >= 0; i--){
        cout << vec[i] << " ";
    }
    
    return 0;
}
