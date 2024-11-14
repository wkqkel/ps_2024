import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int[][] map = new int[22][22];
	static int ps[][] = new int[420][5];
	static int dists[][] = new int[22][22];
	static boolean ch[] = new boolean[420];
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int tr = Integer.parseInt(st.nextToken()) - 1;
		int tc = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken()) - 1;
			int sc = Integer.parseInt(st.nextToken()) - 1;
			int dr = Integer.parseInt(st.nextToken()) - 1;
			int dc = Integer.parseInt(st.nextToken()) - 1;
			// 1. 모든 승객들의 도착지점까지의 거리(=연료소모량) 전처리 -> bfs
			getDist(sr, sc);
			int dist = dists[dr][dc];

			ps[i] = new int[] { sr, sc, dr, dc, dist };
		}

		// 2. 어떤 승객한테 갈지 결정
		// 현재 택시 위치에서 모든 승객까지의 최단거리를 구한 후, 가까운 애 먼저감.

		// 3. 승객 태우고 운전
		// 승객태우고도착까지거리 < 현재 연료량(K)이면 break
		// 아니라면 현재 승객은 체크
		// 현재연료량 += -승객까지거리 -승객부터도착 + 승객부터도착 * 2
		boolean fail = false;
		outer: for (int i = 0; i < M; i++) {
			getDist(tr, tc);
			PriorityQueue<P> pq = new PriorityQueue<>();
			for (int j = 0; j < M; j++) {
				if (ch[j])
					continue;
			
				int d = dists[ps[j][0]][ps[j][1]];
				if (d == -1) {
					fail = true;
					break outer;
				}
				pq.add(new P(j, ps[j][0], ps[j][1], d));
			}

			P top = pq.poll();

			if (K < top.d + ps[top.i][4]) {
				fail = true;
				break;
			}

			ch[top.i] = true;
			K += -top.d + ps[top.i][4];
			tr = ps[top.i][2];
			tc = ps[top.i][3];
		}

		if (fail)
			K = -1;

		System.out.println(K);
	}

	static void getDist(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < N; i++) { // 22 대신 N 사용
			Arrays.fill(dists[i], -1); // 더 빠른 초기화
		}
		q.add(new int[] { sr, sc });
		dists[sr][sc] = 0;
		int d = 0;
		while (!q.isEmpty()) {
			int sz = q.size();
			d++;
			while (sz-- > 0) {
				int[] cur = q.poll();

				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (dists[nx][ny] > -1 || map[nx][ny] == 1)
						continue;
					q.add(new int[] { nx, ny });
					dists[nx][ny] = d;
				}
			}

		}
	}

}

class P implements Comparable<P> {
	int i, x, y, d;

	public P(int i, int x, int y, int d) {
		this.i = i;
		this.x = x;
		this.y = y;
		this.d = d;
	}

	@Override
	public int compareTo(P o) {
		if (d != o.d) {
			return d - o.d;
		}
		if (x != o.x) {
			return x - o.x;
		}
		if (y != o.y) {
			return y - o.y;
		}
		return 0;
	}

}