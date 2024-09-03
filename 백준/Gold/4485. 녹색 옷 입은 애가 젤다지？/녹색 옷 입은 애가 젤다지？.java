import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int map[][] = new int[140][140];
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("Problem ").append(t++).append(": ").append(getMinDist()).append("\n");
		}
		System.out.println(sb);
	}

	static int getMinDist() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
		int dist[][] = new int[N][N];
		boolean ch[][] = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		pq.add(new int[] { map[0][0], 0, 0 });
		dist[0][0] = map[0][0];

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int d = cur[0];
			int x = cur[1];
			int y = cur[2];

			if (ch[x][y])
				continue;
			ch[x][y] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				int nd = d + map[nx][ny];
				if (dist[nx][ny] > nd) {
					dist[nx][ny] = nd;
					pq.add(new int[] { nd, nx, ny });
				}

			}
		}

		return dist[N - 1][N - 1];
	}
}