import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 1000;
	static int N, M, X;
	static List<int[]>[] edges = new List[MX + 1];
	static List<int[]>[] redges = new List[MX + 1];
	static int[] dist1 = new int[MX + 1];
	static int[] dist2 = new int[MX + 1];
	static boolean[] ch = new boolean[MX + 1];

	public static void getDist(int st, int[] dist, List<int[]>[] edges) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
			ch[i] = false;
		}

		pq.add(new int[] { 0, st });
		dist[st] = 0;

		while (!pq.isEmpty()) {
			int cur = pq.peek()[1];
			int d = pq.peek()[0];
			pq.poll();

			if (ch[cur])
				continue;
			ch[cur] = true;

			for (int[] edge : edges[cur]) {
				int nxt = edge[1];
				int nd = edge[0] + d;
				if (nd < dist[nxt]) {
					dist[nxt] = nd;
					pq.add(new int[] { nd, nxt });
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			edges[i] = new LinkedList<>();
			redges[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new int[] { c, b });
			redges[b].add(new int[] { c, a });
		}

		getDist(X, dist1, edges);
		getDist(X, dist2, redges);

		int mx = 0;
		for (int i = 1; i <= N; i++) {
			mx = Math.max(mx, dist1[i] + dist2[i]);
		}

		System.out.println(mx);
	}
}