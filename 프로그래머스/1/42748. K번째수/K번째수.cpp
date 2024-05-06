#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    for(vector<int> command : commands){
        int i = command[0] - 1;
        int j = command[1];
        int k = command[2] - 1;
        vector<int> tmp;
        for(int v = i; v < j; v++){
            tmp.push_back(array[v]);
        }
        sort(tmp.begin(), tmp.end());
        answer.push_back(tmp[k]);
    }
    
    return answer;
}