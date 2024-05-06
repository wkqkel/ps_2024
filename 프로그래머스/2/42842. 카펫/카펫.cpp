#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> res;
    int area = brown + yellow;
    for(int w = 2; w < 2e9; w++){
        int h = area / w;
        if(w * 2 + h * 2 - 4 == brown && (w - 2) * (h - 2) == yellow){
            res.push_back(w);
            break;
        }
    }
    res.push_back(area / res[0]);
    sort(res.begin(),res.end(), greater<>());
    return res;
}