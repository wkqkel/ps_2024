import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 1000;
	static final int INF = Integer.MAX_VALUE;

	static int N, M, X;
	static List<int[]>[] edges = new List[MX + 1];

	static int[] dist = new int[MX + 1];

	static boolean[] ch = new boolean[MX + 1];

	public static void getDist(int st) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

		for (int i = 1; i <= N; i++) {
			dist[i] = INF;
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

		for (int i = 1; i <= N; i++) {
			edges[i] = new LinkedList<>();

		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new int[] { c, b });
			edges[b].add(new int[] { c, a });
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		getDist(a);
		int dist1 = dist[1];
		int dist2 = dist[b];
		int dist3 = dist[N];

		getDist(b);
		int dist4 = dist[1];
		int dist5 = dist[N];

		// 1 -> a -> b -> N
		int sum1 = dist1 + dist2 + dist5;
		// 1 -> b -> a -> N
		int sum2 = dist4 + dist2 + dist3;

		if (dist1 == INF || dist2 == INF || dist3 == INF || dist4 == INF || dist5 == INF) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(sum1, sum2));
		}

	}
}