#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

const int U = 0;
const int D = 9;
const int F = 18;
const int B = 27;
const int L = 36;
const int R = 45;
char origin[54] = {
	// U 0~8
	'w','w','w', // 0~2
	'w','w','w', // 3~5
	'w','w','w', // 6~8
	// D 9~17 = +9
	'y','y','y', // 9~11
	'y','y','y', // 12~14
	'y','y','y', // 15~17
	// F 18~26 = +18
	'r','r','r', // 18~20
	'r','r','r', // 21~23
	'r','r','r', // 24~26
	// B 27~35 = +27
	'o','o','o', // 27~29
	'o','o','o', // 30~32
	'o','o','o', // 33~35
	// L 36~44 = +36
	'g','g','g', // 36~38
	'g','g','g', // 39~41
	'g','g','g', // 42~44
	// R 45~53 = +45
	'b','b','b', // 45~47
	'b','b','b', // 48~50
	'b','b','b', // 51~53
};

char cube[54];

// B- U- L- L+ L- B+ L- R- U- F+
            
int up[12] = {
   L+0, L+1, L+2,
   B+0, B+1, B+2,
   R+0, R+1, R+2,
   F+0, F+1, F+2,
};

int dp[12] = {
    F+6, F+7, F+8,
    R+6, R+7, R+8,
    B+6, B+7, B+8,
    L+6, L+7, L+8,
};

int fp[12] = {
    U+6, U+7, U+8, 
    R+0, R+3, R+6,
    D+2, D+1, D+0,
    L+8, L+5, L+2,
};

int bp[12] = {
    L+0, L+3, L+6, 
    D+6, D+7, D+8,
    R+8, R+5, R+2,
    U+2, U+1, U+0,
};

int lp[12] = {
    U+0, U+3, U+6,
    F+0, F+3, F+6,
    D+0, D+3, D+6,
    B+8, B+5, B+2,
};

int rp[12] = {
    F+2, F+5, F+8,
    U+2, U+5, U+8,
    B+6, B+3, B+0,
    D+2, D+5, D+8,
};
   

void rotateCube(int arr[], int dir) {
	int n = 12;
	char tmp[n];
	for (int i = 0; i < n; ++i) {
		tmp[i] = cube[arr[i]];
	}
	if(dir == 1) {
		rotate(tmp, tmp + n - 3, tmp + n);
	} else {
		rotate(tmp, tmp + 3, tmp + n);
	}

	for (int i = 0; i < n; ++i) {
		cube[arr[i]] = tmp[i];
	}
}

void print_u() {
	for(int i = 0; i < 9; i++) {
		cout << cube[i];
		if((i+1)%3==0) cout << "\n";
	}
}

void rotateSelf(int c, int dir) {
	int tmp[9];

	int dir1[9] = {6,3,0,7,4,1,8,5,2};
	int dir2[9] = {2,5,8,1,4,7,0,3,6};

	for(int i = 0; i < 9; i++) {
		int delta = dir == 1 ? dir1[i] : dir2[i];
		tmp[i] = cube[c+delta];
	}

	for(int i = 0; i < 9; i++) {
		cube[c+i] = tmp[i];
	}
}

int main() {
	int T;
	cin >> T;

	for(int t = 0; t < T; t++) {
		int n;
		cin >> n;

		for(int i = 0; i < 54; i++) {
			cube[i] = origin[i];
		}

		for(int i = 0; i < n; i++) {
			char v, d;
			cin >> v >> d;

			int dir = d == '+' ? 1 : -1;

			if(v == 'F') {
				rotateCube(fp, dir);
				rotateSelf(F, dir);
			}
			else if(v == 'B') {
				rotateCube(bp, dir);
				rotateSelf(B, dir);
			}
			else if(v == 'L') {
				rotateCube(lp, dir);
				rotateSelf(L, dir);
			}
			else if(v == 'R') {
				rotateCube(rp, dir);
				rotateSelf(R, dir);
			}
			else if(v == 'U') {
				rotateCube(up, dir);
				rotateSelf(U, dir);
			}
			else {
				rotateCube(dp, dir);
				rotateSelf(D, dir);
			}
		}

		print_u();
	}



	return 0;
}
