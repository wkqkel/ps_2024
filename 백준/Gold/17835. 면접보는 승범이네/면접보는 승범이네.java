import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 100000;
	static final long INF = Long.MAX_VALUE;

	static int N, M, K;

	static long[] dist = new long[MX + 1];
	static boolean[] ch = new boolean[MX + 1];
	static List<int[]>[] edges = new List[MX + 1];
	static int[] spot = new int[MX + 1];

	static class Edge implements Comparable<Edge> {
		int v;
		long d;

		Edge(int v, long d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {

			return Long.compare(d, o.d);
		}
	}

	public static void getDist() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			dist[i] = INF;
			ch[i] = false;
		}

		for (int i = 0; i < K; i++) {
			int st = spot[i];
			pq.add(new Edge(st, 0));
			dist[st] = 0;
		}

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.v;
			long d = e.d;

			if (ch[cur])
				continue;
			ch[cur] = true;

			for (int[] edge : edges[cur]) {
				int nxt = edge[1];
				long nd = edge[0] + d;
				if (nd < dist[nxt]) {
					dist[nxt] = nd;
					pq.add(new Edge(nxt, nd));
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
		K = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			edges[i] = new LinkedList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new int[] { c, b });
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			spot[i] = Integer.parseInt(st.nextToken());
		}
		long mx = 0;
		int idx = Integer.MAX_VALUE;

		getDist();
		for (int i = 1; i <= N; i++) {
			if (mx < dist[i]) {
				mx = dist[i];
				idx = i;
			} else if (mx == dist[i] && idx > i)
				idx = i;
		}
		System.out.println(idx);
		System.out.println(mx);
	}
}