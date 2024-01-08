#include <bits/stdc++.h>

using namespace std;

/**
1. 아이디어
- 먼저 한방향에 대해서 tilt함수
- 한줄씩 돌아가며 tilt함수 실행
- 방향은 실제 맵을 돌려가며 구현
- 최대 5번까지 이동가능인데, 이동할 수록 적은 수가 나올 확률은 없으므로 5번 이동

2. 시간복잡도
- (20 * 20) * 5 * 4^5

3. 자료구조
- board1: 원본
- board2: 업데이트된 배열
- mx: 가장 큰 블록의 값
*/ 
#include <bits/stdc++.h>
using namespace std;

int board1[21][21];
int board2[21][21];
int n;

void rotate(){
	int tmp[21][21];
	for(int i = 0; i< n; i++)
		for(int j = 0; j < n; j++)
			tmp[i][j] = board2[i][j];
	for(int i =0; i<n; i++)
		for(int j = 0; j < n; j++)
			board2[i][j] = tmp[n-1-j][i];
}

void tilt(int dir){
	int back = 3 - dir;
	while(dir--) rotate();
	for(int i = 0; i < n; i++) {
		int tilted[21] = {};
		int idx = 0;
		for(int j = 0; j<n;j++) {
			if(board2[i][j] == 0) continue;
			if(tilted[idx] == 0) 
				tilted[idx] = board2[i][j];
			else if(tilted[idx] == board2[i][j])
				tilted[idx++] *= 2;
			else 
				tilted[++idx] = board2[i][j];
		}
		for(int j = 0; j<n; j++) board2[i][j] = tilted[j];
	}
	while(back--) rotate();
}

int main(void){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin >> n;
	for(int i = 0; i< n; i++)
		for(int j = 0; j < n; j++)
			cin >> board1[i][j];
	int mx = 0;
	for(int tmp = 0; tmp < 1024; tmp++){ // 4^5
		for(int i = 0; i< n; i++)
			for(int j = 0; j < n; j++)
				board2[i][j] = board1[i][j];
		int brute = tmp;
		for(int i = 0; i < 5; i++) {
			int dir = brute % 4;
			brute /= 4;
			tilt(dir);
		}
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				mx= max(mx, board2[i][j]);
	}
	cout << mx;
}
