import java.io.*;
import java.util.*;

public class Main {
	static int map[][] = new int[24][24];

	static int mark[][] = new int[24][24];
	static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if (x < x + d1 + d2 && x + d1 + d2 <= N && 1 <= y - d1 && y - d1 < y && y < y + d2
								&& y + d2 <= N) {
							mark(x, y, d1, d2, N, mark);
							getscore(N);
						}
					}
				}
			}
		}

		System.out.println(ret);
	}

	static void getscore(int N) {
		int score[] = new int[6];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				score[mark[r][c]] += map[r][c];
			}
		}
		int mn = Integer.MAX_VALUE;
		int mx = Integer.MIN_VALUE;
		for (int i = 1; i <= 5; i++) {
			mn = Math.min(mn, score[i]);
			mx = Math.max(mx, score[i]);
		}

		ret = Math.min(ret, mx - mn);
	}

	static void mark(int x, int y, int d1, int d2, int N, int map[][]) {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = 5;
			}
		}

		// 1번
		for (int r = 1; r < x + d1; r++) {
			for (int c = 1; c <= y; c++) {
				for (int i = 0; i <= d1; i++) {
					int bx = x + i;
					int by = y - i;
					if (r >= bx || c > by)
						continue;
					map[r][c] = 1;
				}
			}
		}

		// 2번
		for (int r = 1; r <= x + d2; r++) {
			for (int c = y + 1; c <= N; c++) {
				for (int i = 0; i <= d2; i++) {
					int bx = x + i;
					int by = y + i;
					if (r > bx || c <= by)
						continue;
					map[r][c] = 2;
				}
			}
		}

		// 3번
		for (int r = x + d1; r <= N; r++) {
			for (int c = 1; c < y - d1 + d2; c++) {
				for (int i = 0; i <= d2; i++) {
					int bx = x + d1 + i;
					int by = y - d1 + i;
					if (r < bx || c >= by)
						continue;
					map[r][c] = 3;
				}
			}
		}

		// 4번
		for (int r = x + d2 + 1; r <= N; r++) {
			for (int c = y - d1 + d2; c <= N; c++) {
				for (int i = 0; i <= d1; i++) {
					int bx = x + d2 + i;
					int by = y + d2 - i;
					if (r <= bx || c < by)
						continue;
					map[r][c] = 4;
				}
			}
		}
	}
}