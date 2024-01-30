#include <string>
#include <vector>

using namespace std;

long getCanProcessingNumber(long t, vector<int> times){
    long sum = 0;
    for(int i = 0; i < times.size(); i++){
        sum += t / times[i];
    }
    return sum;
}

long solution(int n, vector<int> times) {
    long l = 1;
    long r = 1e9 * 1e5;
    
    while(l <= r){
        long mid = (l + r) / 2;
        long pn = getCanProcessingNumber(mid, times);
        
        if(n > pn){
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    
    return l;
}