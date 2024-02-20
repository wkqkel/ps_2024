#include <iostream>

using namespace std;

int n;
int freeCnt[1000002];
int res;

int main()
{
    cin >> n;
    // 공짜쿠폰이 있으면 쓰고 횟수차감
    // 아니면 돈 내고,
    // 다음에 (뒤에꺼에대해서) 나보다 적은 높이는 1회 공짜로 횟수증가
    
    for(int i = 0; i < n; i++){
        int v;
        cin >> v;
        if(freeCnt[v]){
            freeCnt[v]--;
        } else {
            res++;
        }
        freeCnt[v-1]++;
    }
    
    cout << res;
    return 0;
}
