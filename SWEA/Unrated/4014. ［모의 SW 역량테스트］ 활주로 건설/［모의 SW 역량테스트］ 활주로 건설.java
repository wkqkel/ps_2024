import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

//9 4
//4, 4, 3, 3, 3, 3, 2, 2, 2 
//4, 4, 3, 3, 1, 1, 2, 2, 3 
//3, 3, 2, 2, 1, 1, 1, 1, 2 
//1, 1, 1, 1, 1, 1, 1, 1, 1 => O
//1, 1, 1, 1, 1, 1, 1, 1, 1  => O
//2, 2, 1, 1, 1, 1, 1, 1, 1 => O
//2, 2, 1, 1, 1, 1, 1, 1, 1  => O
//2, 2, 2, 2, 2, 2, 1, 1, 1 
//3, 3, 3, 3, 2, 2, 2, 2, 1 
public class Solution {
	static int N, X;

	static int[][] tmp = new int[52][52];

	public static void main(String[] args) throws Exception {
//      int row[] = new int[] { 2, 2, 2, 2, 2, 2, 1, 1, 1 };
//      int M = 4;
//      if (canRow(row, M)) {
//          System.out.println("YES");
//      } else {
//          System.out.println("NO");
//      }

		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			int map[][] = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 가로
			int ret = 0;
			for (int i = 0; i < N; i++) {
				if (canRow(map[i])) {
					ret++;
				}
			}

			rotate(map);

			for (int i = 0; i < N; i++) {
				if (canRow(map[i])) {
					ret++;
				}
			}

			sb.append("#").append(t).append(" ").append(ret).append("\n");
		}
		System.out.println(sb);
	}

	static void rotate(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = map[j][N - 1 - i];
			}
		}
		for (int i = 0; i < N; i++) {
			System.arraycopy(tmp[i], 0, map[i], 0, N);
		}
	}

	static boolean canRow(int[] row) {
		boolean ch[] = new boolean[row.length];
		int cnt = 1;
		for (int i = 0; i < row.length - 1; i++) {
			int diff = row[i + 1] - row[i];
			if (diff > 1) {
				return false;
			}
			if (diff < 0) {
				cnt = 1;
				continue;
			} else if (diff == 1) {
				if (cnt < X) {
					return false;
				}
				for (int j = 0; j < X; j++) {
					ch[i - j] = true;
				}
				cnt = 1;
			} else {
				cnt++;
			}
		}
		cnt = 1;
		for (int i = row.length - 1; i > 0; i--) {
			int diff = row[i - 1] - row[i];
			if (diff > 1) {
				return false;
			}

			if (diff < 0) {
				cnt = 1;
				continue;
			} else if (diff == 1) {
				if (cnt < X) {
					return false;
				}

				cnt = 1;
			} else {
				if (ch[i - 1] || ch[i]) {
					cnt = 1;
				} else {
					cnt++;
				}
			}
		}

		return true;
	}

	static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
}