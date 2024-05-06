#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> answers) {
    int score1 = 0;
    int score2 = 0;
    int score3 = 0;
    int solve1[5] = {1, 2, 3, 4, 5};
    int solve2[8] = {2, 1, 2, 3, 2, 4, 2, 5};
    int solve3[10] = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    for(int i = 0; i < answers.size(); i++){
        int ans = answers[i];
        if(ans == solve1[i % 5]) score1++;
        if(ans == solve2[i % 8]) score2++;
        if(ans == solve3[i % 10]) score3++;
    }
    
    int mx = -1;
    int scores[3] = {score1, score2, score3};
    mx = max(score1, max(score2,score3));
    
    vector<int> ans;
    for(int i = 0; i < 3; i++){
        if(scores[i] ==mx) ans.push_back(i+1);
    }
    
    return ans;
}