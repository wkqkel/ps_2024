import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int ori[][] = new int[10][10];
	static int map[][] = new int[10][10];
	// 오, 아, 왼, 위
	static int dr[] = new int[] { 0, 1, 0, -1 };
	static int dc[] = new int[] { 1, 0, -1, 0 };
	static int rotate[] = new int[10];
	static int mn = Integer.MAX_VALUE;
	static ArrayList<Integer[]> cctvs = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				ori[r][c] = Integer.parseInt(st.nextToken());
				int type = ori[r][c];
				if (ori[r][c] >= 1 && ori[r][c] <= 5) {
					cctvs.add(new Integer[] { r, c, type });
				}
			}
		}

		recur(0);

		System.out.println(mn);
	}

	static void recur(int cur) {
		if (cur == cctvs.size()) {
			map = copy(ori);
			for (int i = 0; i < cur; i++) {
				Integer[] cctv = cctvs.get(i);
				scan(cctv, rotate[i]);
			}

			int cnt = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == 0)
						cnt++;
				}
			}

			mn = Math.min(cnt, mn);
			return;
		}
		for (int i = 0; i < 4; i++) {
			rotate[cur] = i;
			recur(cur + 1);
		}
	}

	static void scan(Integer[] cctv, int rotate) {
		int r = cctv[0];
		int c = cctv[1];
		int type = cctv[2];

		// 오, 아, 왼, 위
		int right = 0;
		int down = 1;
		int left = 2;
		int up = 3;

		if (type == 1) {
			scan(r, c, (right + rotate) % 4);
		} else if (type == 2) {
			scan(r, c, (right + rotate) % 4);
			scan(r, c, (left + rotate) % 4);
		} else if (type == 3) {
			scan(r, c, (up + rotate) % 4);
			scan(r, c, (right + rotate) % 4);
		} else if (type == 4) {
			scan(r, c, (up + rotate) % 4);
			scan(r, c, (right + rotate) % 4);
			scan(r, c, (left + rotate) % 4);
		} else if (type == 5) {
			scan(r, c, (up + rotate) % 4);
			scan(r, c, (right + rotate) % 4);
			scan(r, c, (left + rotate) % 4);
			scan(r, c, (down + rotate) % 4);
		}
	}

	static void scan(int r, int c, int dir) {
		int nr = r;
		int nc = c;

		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
				break;
			}
			if (map[nr][nc] == 6) { // wall
				break;
			}
			if (map[nr][nc] == 0) {
				map[nr][nc] = 9;
			}
		}
	}

	static int[][] copy(int[][] map) {
		int tmp[][] = new int[R][C];

		for (int i = 0; i < R; i++) {
			System.arraycopy(map[i], 0, tmp[i], 0, C);
		}

		return tmp;
	}
}