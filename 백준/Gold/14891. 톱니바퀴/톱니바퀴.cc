#include <iostream>

using namespace std;

/**
# BOJ 14891 톱니바퀴

## 설계

1. 회전여부 판별
오른쪽 2 왼쪽 6
돌리기 전에 맞닿은 것으로 판별.
빠르게 좋은 방법이 안떠올라서,
기준을 기준으로 왼쪽과 오른쪽 각각 판별

O(4)

2. 회전
시계또는 반시계 배열 밀기.
이 때, 위의 회전여부 판별에서, 못돌리면 넘어가고,
돌릴 수 있으면, 기준이랑 idx % 2가 같으면, 같은 방향으로 회전

O(8 * 4)

3. 점수 판별
톱니바퀴 돌면서, t[0] == 0이면 res += 2^idx

---
1시간 반 소요

## 병목지점.
- 큰 설계는 얼마 안걸렸는데, (20-30분)
- 답이 안나올때, 함수 하나하나 여러 테케 넣어보며, 디버깅하는데 오래거림
- ndir을 비교할때, i % 2 == 0으로 함.
- 점수판별 할 때, t[i][0] == 0으로 함.
*/

const int n = 8, m = 4;
string t[4];
int k;
bool canRotate[4];

void detect_rotate(int s){
    canRotate[s] = true;
    
    // 왼쪽 판별
    for(int cur = s; cur > 0; cur--){
        int l = cur -1;
        if(t[cur][6] != t[l][2]) canRotate[l] = true;
        else break;
    }
    // 오른쪽 판별
    for(int cur = s; cur < m - 1; cur++){
        int r = cur + 1;
        if(t[cur][2] != t[r][6]) canRotate[r] = true;
        else break;
    }   
}

void rotate(string& arr, int dir){
    if(dir == 1){
        // 시계방향으로 배열 밀기
        char tmp = arr[n-1];
        for(int i = n-2; i >= 0; i--){
            // 앞에 있는걸 뒤로 미룸.
            // 이 때 값이 안덮어지게 뒤에서 부터
            swap(arr[i+1], arr[i]);
        }
        arr[0] = tmp;
    } else {
        
        // 반시계방향으로 배열 밀기
        char tmp = arr[0];
        for(int i = 0; i < n-1; i++){
            // 뒤에 있는걸 앞으로 땡김.
            swap(arr[i+1], arr[i]);
        }
        arr[n-1] = tmp;
    }
}


int main()
{
    for(int i = 0; i < m; i++){
        cin >> t[i];
    }
 
    cin >> k;
    while(k--){
        int st, dir;
        
        cin >> st >> dir;
        st--;
        for(int i = 0; i < m; i++){
            canRotate[i] = false;
        }
        detect_rotate(st);
        for(int i = 0; i < m; i++){
            if(!canRotate[i]) continue;
            int ndir = i % 2 == st % 2 ? dir : dir * -1;
            rotate(t[i], ndir);
        }
        
    }
    
    int sum = 0;
    for(int i = 0; i < m; i++){
        if(t[i][0] == '0') continue;
        sum += (1 << i);
    }

    cout << sum;

    return 0;
}