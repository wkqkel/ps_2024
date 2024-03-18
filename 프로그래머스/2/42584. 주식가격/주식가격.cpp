#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> prices) {
    // 자신의 인덱스 - (자기보다 낮은게 나온시점||마지막)
    vector<int> answer;
    
    for(int i = 0; i < prices.size(); i++){
        int idx = prices.size() - 1;
        for(int j = i+1; j < prices.size(); j++){
            if(prices[i] > prices[j]) {
                idx = j;
                break;
            }
        }
        answer.push_back(idx - i);
    }
    return answer;
}