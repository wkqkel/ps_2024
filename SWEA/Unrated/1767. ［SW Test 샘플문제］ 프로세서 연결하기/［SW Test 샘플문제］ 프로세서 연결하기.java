import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int dr[] = new int[] { 1, 0, -1, 0 };
	static int dc[] = new int[] { 0, -1, 0, 1 };
	static int N;
	static ArrayList<Core> list = new ArrayList<>();

	static int ori[][] = new int[22][22];
	static int map[][];

	static int mx = Integer.MIN_VALUE;
	static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					ori[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			map = copy(ori);
			mx = Integer.MIN_VALUE;
			ret = Integer.MAX_VALUE;
			list.clear();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (r == 0 || r == N - 1 || c == 0 || c == N - 1)
						continue;
					if (map[r][c] != 1)
						continue;
					list.add(new Core(r, c));
				}
			}

			dfs(0, 0);

			sb.append("#").append(t).append(" ").append(ret).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int cur, int cnt) {
		if (cur == list.size()) {
			if (mx < cnt) {
				mx = cnt;
				ret = getLength();
			} else if (mx == cnt) {
				ret = Math.min(ret, getLength());
			}

			return;
		}
		Core core = list.get(cur);

		for (int dir = 0; dir < 4; dir++) {
			if (!canMove(core.r, core.c, dir)) {
				continue;
			}

			move(core.r, core.c, dir);
			dfs(cur + 1, cnt + 1);
			reset(core.r, core.c, dir);
		}

		dfs(cur + 1, cnt);
	}

	static boolean canMove(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				return true;
			}
			if (map[nr][nc] != 0) {
				return false;
			}
		}
	}

	static void move(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				return;
			}
			map[nr][nc] = -1;
		}
	}

	static void reset(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				return;
			}
			map[nr][nc] = 0;
		}
	}

	static int getLength() {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == -1)
					cnt++;
			}
		}
		return cnt;
	}

	static class Core {
		int r, c;

		Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] copy(int[][] map) {
		int tmp[][] = new int[22][22];

		for (int i = 0; i < 22; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, 22);
		}
		return tmp;
	}
}