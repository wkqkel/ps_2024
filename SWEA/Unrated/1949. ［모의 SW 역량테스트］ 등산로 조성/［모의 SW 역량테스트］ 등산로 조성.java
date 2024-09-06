import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, K;
	static int map[][] = new int[10][10];
	static int ori[][] = new int[10][10];
	static int ret = 0;
	static int mxH = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			mxH = 0;
			ret = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ori[i][j] = Integer.parseInt(st.nextToken());
					map[i][j] = ori[i][j];
					mxH = Math.max(mxH, ori[i][j]);
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int k = 0; k <= K; k++) {
						map[r][c] = ori[r][c] - k;
						find();
						map[r][c] = ori[r][c];
					}

				}
			}
			sb.append("#").append(t).append(" ").append(ret).append("\n");
		}
		System.out.println(sb);
	}

	static void find() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != mxH)
					continue;
				bfs(new int[] { r, c });
			}
		}
	}

	static void bfs(int[] s) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean ch[][][] = new boolean[N][N][N * N];

		q.add(s);
		ch[s[0]][s[1]][0] = true;

		int d = 1;
		while (!q.isEmpty()) {
			int sz = q.size();
			ret = Math.max(ret, d++);

			while (sz-- > 0) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				for (int[] dd : new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }) {
					int nx = x + dd[0];
					int ny = y + dd[1];
	
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (ch[nx][ny][d])
						continue;
					if (map[nx][ny] >= map[x][y])
						continue;
					q.add(new int[] { nx, ny });
					ch[nx][ny][d] = true;
				}
			}

		}
	}

	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}