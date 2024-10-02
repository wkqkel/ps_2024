import java.io.FileInputStream;

import java.util.Scanner;

public class Main {
	static int arr[][] = new int[9][9];
	static int N = 0;
	static int empty[][] = new int[81][2];

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			String v = sc.next();
			for (int j = 0; j < 9; j++) {
				arr[i][j] = v.charAt(j) - '0';
				if (arr[i][j] == 0) {
					empty[N++] = new int[] { i, j };
				}
			}
		}

		dfs(0);
	}

	static void printMap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	static void dfs(int cur) {
		if (cur == N) {
			printMap();
			System.exit(0);
		}

		for (int i = 1; i <= 9; i++) {
			int r = empty[cur][0];
			int c = empty[cur][1];

			arr[r][c] = i;
			if (check(r, c)) {
				dfs(cur + 1);
			}
			arr[r][c] = 0;
		}
	}

	static public boolean check(int r, int c) {

		// 가로 보기
		int cur = arr[r][c];
		for (int i = 0; i < 9; i++) {
			if (i == c)
				continue;
			if (cur == arr[r][i])
				return false;
		}

		// 세로보기
		for (int i = 0; i < 9; i++) {
			if (i == r)
				continue;
			if (cur == arr[i][c])
				return false;
		}

		// 사각형 보기
		int sr = (r / 3) * 3;
		int sc = (c / 3) * 3;
		for (int i = sr; i < sr + 3; i++) {
			for (int j = sc; j < sc + 3; j++) {
				if (i == r && j == c)
					continue;
				if (cur == arr[i][j])
					return false;
			}
		}

		return true;
	}
}