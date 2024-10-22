import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 100;
	static final int INF = Integer.MAX_VALUE;

	static int N, M;
	static int[][] map = new int[MX][MX];
	static int[][] dist = new int[MX][MX];
	static boolean[][] ch = new boolean[MX][MX];
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static int[] dy = new int[] { 1, 0, -1, 0 };

	public static void getDist(int sx, int sy) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = INF;
				ch[i][j] = false;
			}
		}

		pq.add(new int[] { 0, sx, sy });
		dist[sx][sy] = 0;

		while (!pq.isEmpty()) {
			int y = pq.peek()[2];
			int x = pq.peek()[1];
			int d = pq.peek()[0];
			pq.poll();

			if (ch[x][y])
				continue;
			ch[x][y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;

				int nd = d + map[nx][ny];
				if (nd < dist[nx][ny]) {
					dist[nx][ny] = nd;
					pq.add(new int[] { nd, nx, ny });
				}
			}

		}
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		getDist(0, 0);
		System.out.println(dist[N - 1][M - 1]);

	}
}