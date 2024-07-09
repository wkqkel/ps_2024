#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int n;

const int MX = 24;
vector<int> A;

int mn = 1e9;
int board[MX][MX];

/**
#boj14889 스타트와 링크

## 설계
팀능력 차이 최대로
1. N개를 N/2뽑기 
2. 팀스코어 구하기

---
팀스코어에서 team[i]가 아닌 i로 해줘서 디버깅 시간걸림
함수별로 나눠서 그래도 빨리 찾았음.
*/

vector<int> get_b_team(vector<int>& team){
    vector<int> b;
    for(int i = 0; i < n; i++){
        bool exist = false;
        for(int j = 0; j < team.size(); j++){
            if(i == team[j]) exist = true;
        }
        if(!exist) b.push_back(i); 
    }
    return b;
}

int get_team_score(vector<int>& team){
    int res = 0;
    for(int i = 0; i < team.size(); i++){
        for(int j = 0; j < team.size(); j++){
            if(i == j) continue;
            res += board[team[i]][team[j]];
        }
    }
    return res;
}
void recur(int cur, int s){
    if(cur == n / 2){
        auto B = get_b_team(A);
        int diff = abs(get_team_score(A) - get_team_score(B));
        mn = min(mn, diff);
        return;
    }
    
    for(int i = s; i < n; i++){
        A.push_back(i);
        recur(cur+1, i+1);
        A.pop_back();
    }
}

int main(){
    cin >> n;
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n; j++){
            cin >> board[i][j];
        }
    }
    
    recur(0, 0);
    cout << mn;
} 