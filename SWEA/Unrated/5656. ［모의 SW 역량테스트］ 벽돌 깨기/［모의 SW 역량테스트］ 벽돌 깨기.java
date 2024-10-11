import java.io.*;
import java.util.*;

public class Solution {

	static int N, W, H;
	static int ori[][] = new int[22][22];
	static int map[][] = new int[22][22];
	static int tmp[] = new int[22];
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };
	static boolean ch[][] = new boolean[22][22];
	static int mn = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			mn = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					ori[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			recur(0);
			sb.append("#").append(t).append(" ").append(mn).append("\n");
		}

		System.out.println(sb);
	}

	// 모든 경우의 수
	static void recur(int cur) {
		if (cur == N) {
			copyMap();

			for (int i = 0; i < N; i++) {
				attack(tmp[i]);
				down();
			}
			mn = Math.min(mn, count());
			return;
		}
		for (int i = 0; i < W; i++) {
			tmp[cur] = i;
			recur(cur + 1);
		}
	}

	static void copyMap() {
		for (int i = 0; i < H; i++) {
			System.arraycopy(ori[i], 0, map[i], 0, W);
		}
	}

	static void attack(int sw) {
		int sh = -1;
		for (int i = 0; i < H; i++) {
			if (map[i][sw] >= 1) {
				sh = i;
				break;
			}
		}
		if (sh == -1)
			return;

		for (int i = 0; i < H; i++) {
			Arrays.fill(ch[i], false);
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sh, sw });
		ch[sh][sw] = true;

		while (!q.isEmpty()) {
			int sz = q.size();

			while (sz-- > 0) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];

				if (map[x][y] >= 2) {
					for (int k = 1; k < map[x][y]; k++) {
						for (int dir = 0; dir < 4; dir++) {
							int nx = x + dx[dir] * k;
							int ny = y + dy[dir] * k;
							if (overMap(nx, ny))
								continue;
							if (ch[nx][ny])
								continue;

							q.add(new int[] { nx, ny });
							ch[nx][ny] = true;
						}
					}

				}

				map[x][y] = 0;
			}
		}

	}

	static boolean overMap(int x, int y) {
		return x < 0 || x >= H || y < 0 || y >= W;
	}

	static void down() {
		for (int i = 0; i < W; i++) {
			for (int j = H - 2; j >= 0; j--) {
				if (map[j][i] == 0)
					continue;
				int cnt = 0; // 연속된 0 카운트
				for (int k = j + 1; k < H; k++) {
					if (map[k][i] == 0)
						cnt++;
					else
						break;
				}

				int val = map[j][i];
				map[j][i] = 0;
				map[j + cnt][i] = val;
			}
		}
	}

	static int count() {
		int ret = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0)
					ret++;
			}
		}

		return ret;
	}

	static void print(int[][] map) {
		System.out.println("----Map----");
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------");
	}
}