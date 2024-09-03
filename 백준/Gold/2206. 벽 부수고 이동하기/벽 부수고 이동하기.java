import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int map[][] = new int[1002][1002];
	static final int dx[] = new int[] { 1, 0, -1, 0 };
	static final int dy[] = new int[] { 0, -1, 0, 1 };
	static int dist1[][] = new int[1002][1002];
	static int dist2[][] = new int[1002][1002];
	static final int INF = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs(0, 0, dist1);
		bfs(N - 1, M - 1, dist2);

		int mn = INF;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				mn = Math.min(mn, dist1[i][j] + dist2[i][j]);
			}
		}

		if (mn >= INF) {
			System.out.println(-1);
		} else {
			System.out.println(mn + 1);
		}
	}

	static void bfs(int sx, int sy, int[][] dist) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean ch[][] = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = INF;
			}
		}
		q.add(new int[] { sx, sy });
		ch[sx][sy] = true;
		dist[sx][sy] = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (ch[nx][ny])
					continue;
				if (map[nx][ny] == 1) {
					dist[nx][ny] = Math.min(dist[nx][ny], dist[x][y] + 1);
					continue;
				}
				q.add(new int[] { nx, ny });
				ch[nx][ny] = true;
				dist[nx][ny] = dist[x][y] + 1;

			}
		}
	}

}