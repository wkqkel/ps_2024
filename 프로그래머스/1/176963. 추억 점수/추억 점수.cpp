#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    vector<int> res;
    for(int i = 0; i < photo.size(); i++){
        int sum = 0;
        for(int j = 0; j < photo[i].size();j++){
            int f = -1;
            for(int k = 0; k < name.size(); k++){
                if(name[k] == photo[i][j]) {
                    f = k;
                    break;
                }
            }
            if(f != -1) {
               sum += yearning[f]; 
            }
        }
        res.push_back(sum);
    }
    return res;
}