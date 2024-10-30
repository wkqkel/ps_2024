import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 100005;
	static List<Integer> vec[] = new List[MX];
	static int sz[] = new int[MX];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N + 2; i++) {
			vec[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			vec[a].add(b);
			vec[b].add(a);
		}

		dfs(R, 0);
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			sb.append(sz[v]).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int cur, int prev) {
		sz[cur] = 1;
		for (int child : vec[cur]) {
			if (child == prev)
				continue;
			dfs(child, cur);
			sz[cur] += sz[child];
		}
	}

}