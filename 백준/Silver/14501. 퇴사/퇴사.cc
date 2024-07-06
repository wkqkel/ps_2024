#include <iostream>
#include <vector>

using namespace std;

/**
최대 n이 15
2^15
=> 넣고, 안넣고 재귀 
=> 조건에 의해 맞을 경우만 체크해서 최댓값 찾기
=> 조건은 이전선택날짜+소요시간 <= 다음선택날짜
=> 이때 마지막 부분 예외처리
*/
const int MX = 20;
int n;
int T[MX];
int P[MX];
int mx = 0;
vector<int> pick;
bool ch[MX];
// 0 5 7 
// 1 6 8
bool check(){
    pick = {};
    for(int i = 1; i <= n; i++){
        if(ch[i]) pick.push_back(i);
    }
    for(int i = 0; i < pick.size() - 1; i++){
        int cur = pick[i];
        int next = pick[i+1];
        if(cur + T[cur] > next) return false;
    }
    
    int last = pick[pick.size()-1];
    if(last + T[last] > n + 1) return false;
 
    return true;
}

void recur(int cur){
    if(cur == n+1){
        if(check()){
            int sum = 0;
            for(int i = 1; i <= n; i++) if(ch[i]) sum += P[i];
            mx = max(mx, sum);
        }
        return;
    }
    ch[cur] = 1;
    recur(cur+1);
    ch[cur] = 0;
    recur(cur+1);
}
int main()
{
    cin >> n;
    for(int i = 1; i <= n; i++) {
        cin >> T[i] >> P[i];
    }
    recur(1);
    
    cout << mx;
    return 0;
}