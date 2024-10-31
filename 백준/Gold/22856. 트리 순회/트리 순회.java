import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 100005;

	static int child[][] = new int[MX][2];
	static boolean ch[] = new boolean[MX];
	static int par[] = new int[MX];
	static int ret = 0;
	static int last;
	static boolean end = false;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			child[a][0] = b;
			child[a][1] = c;
			if (b != -1)
				par[b] = a;
			if (c != -1)
				par[c] = a;
		}
		inorder(1);
//		System.out.println("last: " + last);
		dfs(1);

	}

	static void inorder(int cur) {

		if (child[cur][0] != -1) {
			inorder(child[cur][0]);
		}
		last = cur;
		if (child[cur][1] != -1) {
			inorder(child[cur][1]);
		}
	}

	static void dfs(int cur) {
//		System.out.print(cur + "->");
		ch[cur] = true;

		int left = child[cur][0];
		int right = child[cur][1];

		if (left != -1 && !ch[left]) {
			ret++;
			dfs(left);
		} else if (right != -1 && !ch[right]) {
			ret++;
			dfs(right);
		} else if (cur == last) {
//			System.out.println();
			System.out.println(ret);
			System.exit(0);
		} else {
			ret++;
			dfs(par[cur]);
		}

	}

}