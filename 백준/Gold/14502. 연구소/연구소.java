import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };
	static int tmp[] = new int[3];
	static boolean ch[][] = new boolean[10][10];

	static int ori[][] = new int[10][10];
	static int map[][] = new int[10][10];
	static int mx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ori[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recur(0, 0);
		System.out.println(mx);
	}

	static void recur(int cur, int st) {
		if (cur == 3) {
			solve();
			return;
		}

		for (int i = st; i < N * M; i++) {
			int x = i / M;
			int y = i % M;
			if (ori[x][y] != 0)
				continue;
			tmp[cur] = i;
			recur(cur + 1, i + 1);
		}
	}

	static void solve() {
		ch = new boolean[10][10];
		copy();
		for (int i : tmp) {
			int x = i / M;
			int y = i % M;

			map[x][y] = 1;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 2 || ch[i][j])
					continue;
				bfs(i, j);
			}
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					cnt++;
			}
		}
		mx = Math.max(mx, cnt);
	}

	static void bfs(int sx, int sy) {
		Queue<Integer[]> q = new ArrayDeque<>();

		q.add(new Integer[] { sx, sy });
		ch[sx][sy] = true;

		while (!q.isEmpty()) {
			int sz = q.size();
			while (sz-- > 0) {
				Integer[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				map[x][y] = 2;

				for (int dir = 0; dir < 4; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						continue;
					if (map[nx][ny] != 0 || ch[nx][ny])
						continue;
					q.add(new Integer[] { nx, ny });
					ch[nx][ny] = true;
				}
			}
		}
	}

	static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = ori[i][j];
			}
		}
	}

}