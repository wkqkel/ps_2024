import java.io.*;
import java.util.*;

/**
 * 
 * 1-4로 가는 경로가 1-(4)->4 1-(2)->2->(3)->4 가 있을 때 C에 따라 갈 수 있을지 말지가 달라짐 그래서 C미만이면
 * q에 다 넣어줌. 그렇게 되면 도착점까지 C이하인 경로만 C에 닿을 수 있고, 과정에서 최대통로비용을 가져감. 그 후 C에 닿은 경로 중
 * 최솟값을 구함
 * 
 */
public class Main {
	static final int MX = 100000;

	static int N, M, A, B;
	static long C;
	static long INF = Long.MAX_VALUE;

	static long[] dist = new long[MX + 1];
	static boolean[] ch = new boolean[MX + 1];
	static List<int[]>[] edges = new List[MX + 1];
	static int ret = Integer.MAX_VALUE;

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

		@Override
		public String toString() {
			return "Edge [v=" + v + ", d=" + d + "]";
		}

	}

	public static boolean getDist(int mx) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			ch[i] = false;
			dist[i] = INF;
		}

		pq.add(new Edge(A, 0));
		dist[A] = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.v;
			long d = e.d;

			if (ch[cur])
				continue;
			ch[cur] = true;

			for (int[] edge : edges[cur]) {
				long nd = edge[0] + d;
				int nxt = edge[1];

				if (edge[0] > mx)
					continue;
				if (nd > C)
					continue;

				if (nd < dist[nxt]) {
					dist[nxt] = nd;
					pq.add(new Edge(nxt, nd));
				}
			}
		}

		return dist[B] != INF;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());
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

		int s = 1;
		int e = (int) 1e9;
		int ret = -1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (getDist(mid)) {
				ret = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		
		System.out.println(ret);
	}
}