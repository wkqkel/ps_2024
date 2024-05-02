#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int solution(vector<int> bandage, int mxHp, vector<vector<int>> attacks) {
    int hp = mxHp;
    int row = 0;
    int time = 0;
    int cnt = 0;
    int monster_cursor = 0;
    while(true){
        time++;
        if(monster_cursor == attacks.size() || hp <= 0) break;
        bool isAttack = attacks[monster_cursor][0] == time;
        row++;
        if(isAttack){
            hp = hp - attacks[monster_cursor][1];
            row = 0;
            monster_cursor++;
        } else {
            if(row == bandage[0]) {
                hp = min(mxHp, hp + bandage[1] + bandage[2]);
                row = 0;
            } else {
                hp = min(mxHp, hp + bandage[1]);
            }
        }
                    cout << time << " " << hp << " " << row << " " << isAttack <<"\n";
    }
    
    return hp <= 0 ? -1 : hp;
}