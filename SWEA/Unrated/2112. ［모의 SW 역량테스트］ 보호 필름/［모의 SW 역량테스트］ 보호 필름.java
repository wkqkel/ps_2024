import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int n, m, k;
	static int board[][] = new int[32][32];
	static int tmp[][] = new int[32][32];
	static int ch[] = new int[32];
	static int res = 10000;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < 32; i++) {
				ch[i] = 2;
			}
			
			res = 10000;
		
			recur(0, 0);

			sb.append("#").append(t).append(" ").append(res).append("\n");
		}
		
		System.out.println(sb);
	}

	public static void recur(int cur, int cnt) {
		if (cur == n) {
			copy();
			
			if (check(tmp)) {
				res = Math.min(cnt, res);
			}

			return;
		}
		if(res >= cnt) {
			for (int i = 0; i < 3; i++) {
				ch[cur] = i;
				int delta = i == 2 ? 0 : 1;
				recur(cur + 1, cnt + delta);
			}
		}
	}

	public static boolean check(int[][] board) {
		for (int col = 0; col < m; col++) {
			int mx = -1;
			int seq = 1;
			for (int row = 0; row < n - 1; row++) {
				if (board[row][col] == board[row + 1][col]) {
					seq++;
				} else {
					seq = 1;
				}
				
				mx = Math.max(seq, mx);
				if(mx > k) break;
			}

			if (mx < k)
				return false;
		}

		return true;
	}

	public static void copy() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (ch[i] == 0) {
					tmp[i][j] = 0; // A
				} else if (ch[i] == 1) {
					tmp[i][j] = 1; // B
				}

				else {
					tmp[i][j] = board[i][j];
				}
			}
		}

	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(tmp[i][j] + " ");
			}
			System.out.println();
		}
	}
}