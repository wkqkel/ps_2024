import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 1000;

	static int N, M, X;
	static List<int[]>[] edges = new List[MX + 1];

	static int[] dist = new int[MX + 1];

	static boolean[] ch = new boolean[MX + 1];

	public static void getDist(int st) {
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

		long d1 = 0; // 1 -> a -> b -> N
		long d2 = 0; // 1 -> b -> a -> N
		getDist(a);

		d1 += dist[1]; // d1 += dist[1] + dist[b] 는 오버플로우 위험
        d1 += dist[b];
		d2 += dist[b];
        d2 += dist[N];

		getDist(b);
		d1 += dist[N];
		d2 += dist[1];

		long mn = Math.min(d1, d2);
		if (d1 >= Integer.MAX_VALUE || d2 >= Integer.MAX_VALUE)
			mn = -1;

		System.out.println(mn);

	}
}