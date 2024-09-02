import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int MX = 20020;
	static ArrayList<Integer[]> vec[] = new ArrayList[MX];
	static int dist[] = new int[MX];
	static boolean ch[] = new boolean[MX];

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < MX; i++) {
			vec[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int S = Integer.parseInt(br.readLine());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			vec[u].add(new Integer[] { v, w });
//			vec[v].add(new Integer[] { u, w });
		}

		getDist(S);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(dist[i]).append("\n");
			}
		}
		System.out.println(sb);
	}

	static void getDist(int st) {
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

		for (int i = 0; i < MX; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[st] = 0;
		pq.add(new Integer[] { 0, st });

		while (!pq.isEmpty()) {
			int sz = pq.size();
			while (sz-- > 0) {
				Integer[] pk = pq.poll();

				int cur = pk[1];
				int d = pk[0];
//				System.out.println(cur + " " + d);
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