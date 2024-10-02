import java.io.*;
import java.util.*;

public class Solution {
	static int dx[] = new int[] { 0, 1, 0, -1 };
	static int dy[] = new int[] { 1, 0, -1, 0 };
	static int map[][] = new int[105][105];
	static int N;
	static int R = 0, D = 1, L = 2, U = 3;
	static int op[] = new int[] { 2, 3, 0, 1 };
	static int wop[][] = new int[][] { {}, //
			{ L, R, U, D }, //
			{ L, U, D, R }, //
			{ D, U, R, L }, //
			{ U, L, R, D }, //
			{ L, U, R, D } //
	};
	static int mx = 0;
	static Map<Integer, ArrayList<int[]>> whole = new HashMap<>();

	static class Node {
		int x, y, d, cnt;

		public Node(int x, int y, int dir, int cnt) {

			this.x = x;
			this.y = y;
			this.d = dir;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", d=" + d + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());

			mx = 0;
			whole.clear();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int c = 0; c < N; c++) {
					int v = Integer.parseInt(st.nextToken());
					map[r][c] = v;

					if (!whole.containsKey(v)) {
						whole.put(v, new ArrayList<>());
					}
					whole.get(v).add(new int[] { r, c });
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] != 0)
						continue;
					for (int dir = 0; dir < 4; dir++) {
						Node node = new Node(r, c, dir, 0);

						bfs(node);
					}
				}
			}

			sb.append("#").append(t).append(" ").append(mx).append("\n");
		}
		System.out.println(sb);
	}

	static void bfs(Node st) {

		Node cur = st;

		while (true) {

			int tx = cur.x + dx[cur.d];
			int ty = cur.y + dy[cur.d];
	
			// 5. 출발지점이라면

			// 0. 벽
			if (overMap(tx, ty)) {
				// 반대방향 , cnt++
			
				cur = new Node(tx, ty, op[cur.d], cur.cnt + 1);
			}
			// 시작지점에 도착
			else if (tx == st.x && ty == st.y) {

				mx = Math.max(mx, cur.cnt);
				return;
			}
			// 1. 블록이라면
			else if (1 <= map[tx][ty] && map[tx][ty] <= 5) {
				int w = map[tx][ty];
	
				cur = new Node(tx, ty, wop[w][cur.d], cur.cnt + 1);
			}
			// 2. 빈공간이라면
			else if (map[tx][ty] == 0) {

				cur = new Node(tx, ty, cur.d, cur.cnt);
			}
			// 3. 블랙홀이면
			else if (map[tx][ty] == -1) {

				mx = Math.max(mx, cur.cnt);
				return;
			}
			// 4. 웜홀이라면
			else if (6 <= map[tx][ty] && map[tx][ty] <= 10) {
		
				int h1[] = whole.get(map[tx][ty]).get(0);
				int h2[] = whole.get(map[tx][ty]).get(1);

				tx = tx ^ h1[0] ^ h2[0];
				ty = ty ^ h1[1] ^ h2[1];
				cur = new Node(tx, ty, cur.d, cur.cnt);
			}

		}

	}

	static boolean overMap(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= N;
	}
}