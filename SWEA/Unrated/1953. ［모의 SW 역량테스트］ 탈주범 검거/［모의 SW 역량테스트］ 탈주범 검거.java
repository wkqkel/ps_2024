import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	// 상하좌우
	static final int UD = 0, DD = 1, LD = 2, RD = 3;
	static final int[][] DIRS = { null, //
			{ UD, DD, LD, RD }, // 1
			{ UD, DD }, // 2
			{ LD, RD }, // 3
			{ UD, RD }, // 4
			{ DD, RD }, // 5
			{ DD, LD }, // 6
			{ UD, LD } // 7
	};
	static int op[] = new int[] { 1, 0, 3, 2 };
	static final int dx[] = new int[] { -1, 1, 0, 0 };
	static final int dy[] = new int[] { 0, 0, -1, 1 };

	static int N, M, R, C, L;
	static int map[][] = new int[52][52];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("#").append(t).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb);
	}

	static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean ch[][] = new boolean[52][52];

		q.add(new int[] { R, C, -1 });
		ch[R][C] = true;

		int d = 0;
		int cnt = 0;
		outer: while (!q.isEmpty()) {
			int sz = q.size();
			cnt += sz;
			d++;
			while (sz-- > 0) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int t = map[x][y];
				
				if (d == L) {
					break outer;
				}
				for (int dir : DIRS[t]) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M)
						continue;
					if (map[nx][ny] == 0 || ch[nx][ny])
						continue;

					// 다음이 현재의 반대방향을 가지고있는지 체크
					boolean isConnected = false;
					for (int nd : DIRS[map[nx][ny]]) {
						if (op[nd] == dir)
							isConnected = true;
					}

					if (!isConnected) {
						continue;
					}

					ch[nx][ny] = true;
					q.add(new int[] { nx, ny });
				}
			}
		}
		
		return cnt;
	}
}