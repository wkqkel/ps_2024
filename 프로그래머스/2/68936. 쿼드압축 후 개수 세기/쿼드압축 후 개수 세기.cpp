#include <string>
#include <vector>
#include <iostream>

using namespace std;

int res[2] = {0, 0};
vector<vector<int>> board;

bool check(int sx ,int sy, int sz){
    // cout << sx << " " << sy << " " << sz << "\n";
    int first = board[sx][sy];
    for(int i = sx; i < sx+sz; i++){
        for(int j = sy; j < sy+sz; j++){
            if(board[i][j] != first) return false;
        }
    }
    return true;
}

void recur(int sx, int sy, int sz){
    if(check(sx,sy, sz)){
        res[board[sx][sy]]++;
        return;
    }
    int half = sz  / 2 ;
    recur(sx, sy, half);
    recur(sx, sy+half, half);
    recur(sx + half, sy, half);
    recur(sx + half, sy+half, half);
}

vector<int> solution(vector<vector<int>> arr) {
    vector<int> ans;
    board = arr;
    recur(0, 0, board.size());
    ans.push_back(res[0]);
    ans.push_back(res[1]);
    
    return ans;
}