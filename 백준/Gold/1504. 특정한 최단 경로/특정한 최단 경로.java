import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int MX = 802;
	static final int INF = Integer.MAX_VALUE;
	static ArrayList<Integer[]> vec[] = new ArrayList[MX];
	static int dist[] = new int[MX];
	static boolean ch[] = new boolean[MX];
	static int N, E;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < MX; i++) {
			vec[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			vec[u].add(new Integer[] { v, w });
			vec[v].add(new Integer[] { u, w });
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

	static void getDist(int st) {
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

		ch = new boolean[MX];
		Arrays.fill(dist, INF);

		dist[st] = 0;
		pq.add(new Integer[] { 0, st });

		while (!pq.isEmpty()) {
			int sz = pq.size();
			while (sz-- > 0) {
				Integer[] pk = pq.poll();

				int cur = pk[1];
				int d = pk[0];

				if (ch[cur])
					continue;
				ch[cur] = true;

				for (Integer[] ni : vec[cur]) {
					int nxt = ni[0];
					int nd = d + ni[1];
					if (nd < dist[nxt]) {
						dist[nxt] = nd;
						pq.add(new Integer[] { nd, nxt });
					}
				}
			}
		}
	}
}