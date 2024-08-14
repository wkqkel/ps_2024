#include <iostream>
#include <algorithm>
using namespace std;
 
struct FISH{
	int y, x, dir;
	bool isdead;
};
 
 
const int dx[] = { 0,-1,-1,-1,0,1,1,1 };
const int dy[] = { -1,-1,0,1,1,1,0,-1 };
 
void cpmap(int c[][4], int o[4][4])
{
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            c[i][j] = o[i][j];
        }
    }
}
void cpfish(FISH c[17], FISH o[17])
{
    for(int i = 0; i < 17; i++){
        c[i] = o[i];
    }
}

int dfs(int origin_map[][4], int y, int x, FISH origin_fish[17])
{
	
	int map[4][4];
	FISH fish[17];
	cpmap(map, origin_map);
	cpfish(fish, origin_fish);

	int eat = map[y][x];
	int dir = fish[map[y][x]].dir;
    
    // 1. shark_eat
	fish[map[y][x]].x = -1;
	fish[map[y][x]].y = -1;
	fish[map[y][x]].isdead = true;
	map[y][x] = 0;
 
    
	// 2. fish_move
	for (int i = 1; i <= 16; ++i) {
		if (fish[i].isdead) continue;
        
        // 방향찾기
		int nx, ny;
		for (int d = 0; d < 8; d++){
            int td = (fish[i].dir + d) % 8;
            int tx = fish[i].x + dx[td];
            int ty = fish[i].y + dy[td];
            if(tx < 0 || tx >= 4 || ty < 0 || ty >= 4) continue;
            if(tx == x && ty == y) continue;
            nx = tx;
            ny = ty;
            fish[i].dir = td;
			break;
		}
		
	    if (map[ny][nx] == 0) {
		    swap(fish[i].x, nx);
		    swap(fish[i].y, ny);
		    swap(map[fish[i].y][fish[i].x], map[ny][nx]);
		}
		else {
			int nxt = map[ny][nx];
			swap(fish[i].x, fish[nxt].x);
			swap(fish[i].y, fish[nxt].y);
            swap(map[fish[i].y][fish[i].x], map[fish[nxt].y][fish[nxt].x]);
		}
	}
 
 
    // 3. shark_move
    int ret = 0;
 
    for(int d = 1; d <= 4; d++){
        int nx = x + (dx[dir] * d);
        int ny = y + (dy[dir] * d);
        if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
        if(map[ny][nx] == 0) continue;

  	    ret = max(ret, dfs(map, ny, nx, fish));
    }
    
    return ret + eat;
}
 
int main()
{
	FISH fish[17];
    int map[4][4];
    
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            map[i][j] = 0;
            int f_n, f_dir;
            cin >> f_n >> f_dir;
            f_dir--;
            fish[f_n] = FISH{i, j, f_dir, 0};
            map[i][j] = f_n;
        }
    }
    
	cout << dfs(map, 0, 0, fish);
	return 0;
}