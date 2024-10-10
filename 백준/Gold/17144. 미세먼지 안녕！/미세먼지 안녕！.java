import java.io.*;
import java.util.*;

public class Main {
	static int R, C, T;
	static int dr[] = new int[] { 1, 0, -1, 0 }; // D, L, U, R
	static int dc[] = new int[] { 0, -1, 0, 1 };

	static int map[][] = new int[52][52];
	static int[][] pos = new int[2][2];

	static int UDIRS[] = new int[] { 2, 3, 0, 1 };
	static int DDIRS[] = new int[] { 0, 3, 2, 1 };
	static int UB[] = new int[4];
	static int DB[] = new int[4];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int cnt = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) {
					pos[cnt++] = new int[] { r, c };
				}
			}
		}

		UB = new int[] { 0, pos[0][0], 0, C - 1 };
		DB = new int[] { pos[1][0], R - 1, 0, C - 1 };

		while (T-- > 0) {
			// 미세먼지 확산
			spread();
			// 공기청정기 정화
			clean();
		}

		int ret = 0;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == -1)
					continue;
				ret += map[r][c];
			}
		}

		System.out.println(ret);

	}

	static void spread() {
		int nmap[][] = new int[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int cnt = 0;
				int nv = map[r][c] / 5;
				for (int dir = 0; dir < 4; dir++) {
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					if (overMap(nr, nc))
						continue;
					if (map[nr][nc] == -1)
						continue;
					nmap[nr][nc] += nv;
					cnt++;
				}
				nmap[r][c] += map[r][c] - nv * cnt;
			}
		}

		for (int r = 0; r < R; r++) {
			System.arraycopy(nmap[r], 0, map[r], 0, C);
		}
	}

	static void clean() {
		rotate(pos[0], UDIRS, UB);
		rotate(pos[1], DDIRS, DB);
	}

	static void rotate(int[] st, int[] dirs, int[] boundary) {
		int r = st[0];
		int c = st[1];
		int dir = 0;
		while (true) {

			int nr = r + dr[dirs[dir]];
			int nc = c + dc[dirs[dir]];
//			System.out.printf("%d: (%d, %d) => (%d, %d)\n", dir, r, c, nr, nc);
			if (overMap(nr, nc, boundary)) {
				dir++;
			} else {
				if (map[nr][nc] == -1) {
					map[r][c] = 0;
					return;
				}
				if (map[r][c] == -1) {
					map[nr][nc] = 0;
				} else {
					map[r][c] = map[nr][nc];
				}

				r = nr;
				c = nc;
			}
		}
	}

	static boolean overMap(int r, int c, int[] b) {
		return r < b[0] || r > b[1] || c < b[2] || c > b[3];
	}

	static boolean overMap(int r, int c) {
		return r < 0 || r >= R || c < 0 || c >= C;
	}

}