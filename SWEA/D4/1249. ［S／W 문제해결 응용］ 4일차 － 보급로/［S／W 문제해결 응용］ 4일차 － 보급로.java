import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int map[][] = new int[102][102];
	static int N;
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = str.charAt(c) - '0';
				}
			}

			sb.append("#").append(t).append(" ").append(getDist()).append("\n");
		}

		System.out.println(sb);
	}

	static int getDist() {
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
		int dist[][] = new int[N][N];
		boolean ch[][] = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		pq.add(new Integer[] { 0, 0, 0 });
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Integer[] cur = pq.poll();
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
				if (nd < dist[nx][ny]) {
					dist[nx][ny] = nd;
					pq.add(new Integer[] { nd, nx, ny });
				}
			}
		}

		if (dist[N - 1][N - 1] == Integer.MAX_VALUE)
			return -1;

		return dist[N - 1][N - 1];
	}
}