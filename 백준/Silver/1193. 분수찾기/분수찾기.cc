#include <iostream>

using namespace std;

/**
BOJ1193 - 240125 THUR

또 Math.. 
블로그 여러개 보고 겨우 이해..

0. 규칙파악
- 대각선에 따라 이동 -> 대각선의 갯수가 일정하게 증가
- 대각선의 위치가 끝의 분자 또는 분모가 됨.
1. diagonal 몇번째 대각선인지 찾아내고
- 1,2,3,4씩 증가한 값이 x보다 적을 때 위치.
2. order 그 대각선에서 몇번째인지 찾아본다.
- 현재대각선이 3번이라면 현재 x - (1+2)[이전 대각선까지 모든 갯수]
3. 짝홀수 대각선 여부에 따라 [diagonal-order+1, order] 순서 바꿔 출력
*/
int main()
{
    int x;
    cin >> x;
    
    int sum = 0;
    int diagonal = 0;
    
    while(sum < x){
        sum += ++diagonal;
    }
    int order = x - (sum - diagonal);
    
    int a = diagonal-order +1 ;
    int b = order;
    
    if(diagonal % 2) cout << a << '/' << b;
    else cout << b << '/' << a;
    
    return 0;
}
