import java.io.*;
import java.util.*;

/**

1-4로 가는 경로가
1-(4)->4
1-(2)->2->(3)->4 
가 있을 때 C에 따라 갈 수 있을지 말지가 달라짐
그래서 C미만이면 q에 다 넣어줌.
그렇게 되면 도착점까지 C이하인 경로만 C에 닿을 수 있고, 과정에서 최대통로비용을 가져감.
그 후 C에 닿은 경로 중 최솟값을 구함

 */
public class Main {
	static final int MX = 100000;

	static int N, M, A, B;
	static long C;

	static long[] dist = new long[MX + 1];
	static boolean[] ch = new boolean[MX + 1];
	static List<int[]>[] edges = new List[MX + 1];
	static int ret = Integer.MAX_VALUE;

	static class Edge implements Comparable<Edge> {
		int v;
		long d;
		int mn;

		Edge(int v, long d, int mn) {
			this.v = v;
			this.d = d;
			this.mn = mn;
		}

		@Override
		public int compareTo(Edge o) {

			return Long.compare(d, o.d);
		}

		@Override
		public String toString() {
			return "Edge [v=" + v + ", d=" + d + ", mn=" + mn + "]";
		}

	}

	public static void getDist(int st) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 1; i <= N; i++) {
			ch[i] = false;
		}

		pq.add(new Edge(st, 0, 0));

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int cur = e.v;
			long d = e.d;
			int cmn = e.mn;
			if (cur == B) {
				ret = Math.min(ret, cmn);
			}

			if (ch[cur])
				continue;
			ch[cur] = true;
			
			for (int[] edge : edges[cur]) {
				long nd = edge[0] + d;
				int nxt = edge[1];

				if (nd <= C) {
					int nmn = Math.max(cmn, edge[0]);
					if (cmn == 0)
						nmn = edge[0];
					Edge nedge = new Edge(nxt, nd, nmn);
					pq.add(nedge);
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

		getDist(A);

		if (ret == Integer.MAX_VALUE) {
			ret = -1;
		}

		System.out.println(ret);
	}
}