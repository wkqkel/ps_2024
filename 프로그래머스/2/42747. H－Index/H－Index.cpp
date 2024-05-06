#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> citations) {
    sort(citations.begin(), citations.end(), greater<>());
    int n = citations.size();
    for(int i = 0; i < n; i++){
        if(i+1 > citations[i]) return i;
    }

    return n;
}