#include <string>
#include <vector>
#include <queue>
#include <map>
#include <iostream>

using namespace std;

map<char, int> v_map = {{'S',0},{'E',1},{'N',2},{'W',3}};

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};
bool visited[100][100];

bool over_map(int nx, int ny, int n, int m){
    return nx < 0 || nx >= n || ny < 0 || ny >= m;
}


vector<int> solution(vector<string> park, vector<string> routes) {
    vector<int> answer;
    
    pair<int,int> cur = {0,0};
    
    for(int i = 0; i < park.size(); i++){
        for(int j = 0; j < park[i].size(); j++){
            if(park[i][j] == 'S') {
                cur.first = i;
                cur.second = j;
            }
        }
    }

    for(string route: routes){
        char dir = route[0];
        int cnt = route[2] - '0';

        int nx = cur.first;
        int ny = cur.second;
        
        bool flag = false;
        for(int i = 0; i < cnt; i++){
            nx += dx[v_map[dir]];
            ny += dy[v_map[dir]];
            if(over_map(nx,ny,park.size(), park[0].size()) || park[nx][ny] == 'X') {
                flag = true;
                break;
            } 
        }
        if(flag) continue;
        cur.first = nx;
        cur.second = ny;
    }
    
    answer.push_back(cur.first);
    answer.push_back(cur.second);
    
    return answer;
}