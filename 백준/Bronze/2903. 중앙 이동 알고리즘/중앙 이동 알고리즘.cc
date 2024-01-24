#include <iostream>

using namespace std;

/**
BOJ2903 - 240124
가로의 점은 기존 점 * 2 - 1만큼 늘어난다.
그걸 제곱해주면 답.
*/
int main()
{
    int n;
    cin >> n;
    
    int dot = 2;
    while(n--){
        dot = (dot * 2 - 1);
    }
    
    cout << dot * dot;
    return 0;
}

/**
브론즈3문제인데, 
하루종일 해도 못 풀었다.

결국 블로그를 몇개 보고 겨우 이해.

다른 관점으로 보는게너무 약하다.
진짜 많이 풀어봐야겠다.
*/