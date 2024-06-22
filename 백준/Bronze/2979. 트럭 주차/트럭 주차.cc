#include <iostream>

using namespace std;

int prices[3];
int delta[104];
int t1, t2;
int prefix[104];

/**
1. delta에 시간에따른 주차대수 변화량을 저장
2. prefix에 시간당 현재 주차대수 저장
*/
int main()
{
    cin >> prices[0] >> prices[1] >> prices[2];
    
    for(int i = 0; i < 3; i++){
        cin >> t1 >> t2;
        delta[t1] += 1;
        delta[t2] -= 1;
    }
    
    for(int i = 1; i <= 100; i++){
        prefix[i] = prefix[i-1] + delta[i];
    }
    
    int sum = 0;
    for(int i = 1; i <= 100; i++){
        sum += (prefix[i] * prices[prefix[i] - 1]);
    }
    
    cout << sum;
    return 0;
}