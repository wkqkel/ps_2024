import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int map[][] = new int[12][12];
	static int seq = 1;
	static int dx[] = new int[] { 1, 0, -1, 0 };
	static int dy[] = new int[] { 0, -1, 0, 1 };
	static ArrayList<int[]> vec = new ArrayList<>();

	static int par[] = new int[12];
	static int ret = 0;

	public static void main(String[] args) throws Exception {

		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. flood fill로 숫자 매기기
		mark();

		// 2. 섬을 잇는 모든 간선(다리)만들기 O(100*10*4)
		makeBridge();
		// 3. 크루스칼
		kruskal();

		if (!isConnected()) {
			ret = -1;
		}

		System.out.println(ret);
	}

	static void mark() {
		boolean ch[][] = new boolean[12][12];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0 || ch[i][j])
					continue;
				// bfs

				Queue<int[]> q = new ArrayDeque<>();
				q.add(new int[] { i, j });
				ch[i][j] = true;

				while (!q.isEmpty()) {
					int sz = q.size();
					while (sz-- > 0) {
						int[] cur = q.poll();
						int x = cur[0];
						int y = cur[1];
						map[x][y] = seq;
						for (int dir = 0; dir < 4; dir++) {
							int nx = x + dx[dir];
							int ny = y + dy[dir];
							if (overMap(nx, ny))
								continue;
							if (map[nx][ny] != 1 || ch[nx][ny])
								continue;
							q.add(new int[] { nx, ny });
							ch[nx][ny] = true;
						}
					}
				}
				seq++;
			}
		}
	}

	static void makeBridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 옆으로
				int a = map[i][j];
				if (a == 0)
					continue;
				// 4방향

				for (int dir = 0; dir < 4; dir++) {
					int nx = i;
					int ny = j;
					while (true) {
						nx += dx[dir];
						ny += dy[dir];
						if (overMap(nx, ny))
							break;
						if (map[nx][ny] != 0)
							break;
					}
					if (!overMap(nx, ny)) {
						int b = map[nx][ny];
						int c = Math.abs(i + j - (nx + ny)) - 1;
						if (c < 2)
							continue;

						vec.add(new int[] { c, a, b });
					}
				}
			}
		}
	}

	static void kruskal() {
		vec.sort((a, b) -> a[0] - b[0]);
		for (int i = 1; i <= seq; i++) {
			par[i] = i;
		}

		for (int i = 0; i < vec.size(); i++) {
			int a = find(vec.get(i)[1]);
			int b = find(vec.get(i)[2]);
			int c = vec.get(i)[0];

			if (a == b)
				continue;

			union(a, b);

			ret += c;
		}
	}

	private static boolean overMap(int nx, int ny) {
		return (nx < 0 || nx >= N || ny < 0 || ny >= M);
	}

	private static int find(int a) {
		if (par[a] == a) {
			return a;
		}
		return par[a] = find(par[a]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;

		par[b] = a;
	}

	private static boolean isConnected() {
		boolean ret = true;
		int st = find(par[1]);
		for (int i = 1; i < seq; i++) {
			if (find(par[i]) != st)
				ret = false;
		}
		return ret;
	}
}