#include <string>
#include <vector>
#include <algorithm>

using namespace std;

/**
가로 세로 길이를 정렬한후에
최댓값 뽑아낸 뒤 넓이 반환
*/
int solution(vector<vector<int>> sizes) {
    for(int i = 0 ;i < sizes.size(); i++) {
        sort(sizes[i].begin(), sizes[i].end());
    }
    
    int w_mx = -1;
    int h_mx = -1;
    
    for(int i = 0; i < sizes.size(); i++){
        w_mx = max(w_mx, sizes[i][0]);
        h_mx = max(h_mx, sizes[i][1]);
    }

    return w_mx * h_mx;
}