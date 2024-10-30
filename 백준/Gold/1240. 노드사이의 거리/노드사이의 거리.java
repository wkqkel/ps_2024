import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 1005;
	static List<int[]> vec[] = new List[MX];
	static int dist[] = new int[MX];
	static int par[] = new int[MX];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < MX; i++) {
			vec[i] = new ArrayList<>();
		}
		int root = -1;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			root = a;
			vec[a].add(new int[] { b, c });
			vec[b].add(new int[] { a, c });

		}
		dfs(root, 0);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int lca = getCommonParent(a, b);
			int ret = dist[a] + dist[b] - dist[lca] * 2;
			sb.append(ret).append("\n");
		}
		System.out.println(sb);

	}

	static int getCommonParent(int a, int b) {
		boolean ch[] = new boolean[MX];
		int cur = a;
		while (true) {
			if (cur == 0)
				break;
			ch[cur] = true;
			cur = par[cur];
		}

		cur = b;
		while (true) {
			if (ch[cur])
				break;
			cur = par[cur];
		}
		return cur;
	}

	static boolean find(int a, int b) {
		int cur = a;
		while (true) {
			if (cur == b)
				break;
			if (cur == 0)
				break;
			cur = par[cur];
		}
		return cur == b;
	}

	static void dfs(int cur, int prev) {

		for (int[] child : vec[cur]) {
			if (child[0] == prev)
				continue;
			par[child[0]] = cur;
			dist[child[0]] = dist[cur] + child[1];
			dfs(child[0], cur);
		}
	}

}