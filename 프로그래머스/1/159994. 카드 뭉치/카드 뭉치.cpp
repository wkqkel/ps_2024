#include <string>
#include <vector>
#include <iostream>

using namespace std;

string solution(vector<string> cards1, vector<string> cards2, vector<string> goal) {
    int lt = 0;
    int rt = 0;
    string res = "Yes";
    
    for(int i = 0; i < goal.size(); i++){
        if(lt < cards1.size() && cards1[lt] == goal[i]) lt++;
        else if(rt < cards2.size() && cards2[rt] == goal[i]) rt++;
        else res = "No";
    }

    return res;
}