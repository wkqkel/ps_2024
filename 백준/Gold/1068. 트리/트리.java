import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 50;

	static List<Integer>[] vec = new List[MX + 1];
	static int ret = 0;
	static int N, R;
	static boolean leaf[] = new boolean[MX + 1];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			vec[i] = new ArrayList<>();
		}
		int root = -1;
		for (int i = 0; i < N; i++) {

			int p = Integer.parseInt(st.nextToken());
			if (p != -1)
				vec[p].add(i);
			if (p == -1)
				root = i;
		}
		R = Integer.parseInt(br.readLine());
		if (R != root)
			dfs(root, -1);

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (leaf[i])
				cnt++;
		}
		System.out.println(cnt);
	}

	static void dfs(int cur, int prev) {
		boolean isLeaf = true;

		for (int child : vec[cur]) {
			if (child == prev || child == R)
				continue;
			isLeaf = false;
			dfs(child, cur);

		}
		leaf[cur] = isLeaf;
	}

}