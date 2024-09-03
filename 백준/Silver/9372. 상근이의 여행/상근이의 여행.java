import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int par[] = new int[100020];
	static ArrayList<int[]> vec = new ArrayList<>();
	static BufferedReader br;
	static StringTokenizer st;
	static int V, E;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			solution();
		}

		System.out.println(sb);
	}

	static void solution() throws Exception {
		init();
		int cnt = 0;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (find(a) != find(b)) {
				union(a, b);
				cnt++;
			}

		}

		sb.append(cnt).append("\n");
	}

	static void init() {
		for (int i = 0; i < 100020; i++) {
			par[i] = i;
		}
	}

	static int find(int a) {
		if (par[a] == a)
			return a;
		return par[a] = find(par[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;
		par[b] = a;
	}
}