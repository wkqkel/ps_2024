#include <string>
#include <vector>

using namespace std;

int solution(int n, int m, vector<int> section) {
    int c = 0;
    int cnt = 0;
    
    for(int i = 0; i < section.size(); i++){
        if(section[i] <= c) continue;
        c = section[i] + m - 1;
        cnt++;
    }
    
    return cnt;
}