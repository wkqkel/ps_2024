#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times) {
    long long answer = 0;
    
    long long left = 1;
    long long right = *max_element(times.begin(), times.end()) * (long long) n;

    while(left <= right)
    {
        long long mid = (left+right)/2; long long cnt = 0;
        for(int i = 0; i < times.size(); i++)
        {
            cnt += mid / (long long) times[i];
        }
        
        if(cnt >= n)
        {
            right = mid - 1;
            answer = mid;
        }
        else left = mid + 1;
    }
    
    return answer;
    
}