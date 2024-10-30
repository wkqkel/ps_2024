import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 500;
	static int par[] = new int[MX + 1];

	static void setup() {
		for (int i = 0; i < MX + 1; i++) {
			par[i] = i;
		}
	}

	static int find(int a) {
		if (a == par[a])
			return a;

		return par[a] = find(par[a]);
	}

	static void union(int a, int b) {
		
		a = find(a);
		b = find(b);
		if(a == 0 || b == 0) {
		    par[a] = par[b] = 0;
			return;
		}
		if (a == b) {
			par[b] = 0;
			return;
		}

		par[b] = a;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int c = 1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;

			setup();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b);
			}

			for (int i = 1; i <= N; i++) {
				find(i);
			}

			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				if (par[i] == 0)
					continue;
				set.add(par[i]);
			}

			sb.append("Case ").append(c++).append(": ");
			int cnt = set.size();
			if (cnt == 1) {
				sb.append("There is one tree.\n");
			} else if (cnt == 0) {
				sb.append("No trees.\n");
			} else {
				sb.append("A forest of ").append(cnt).append(" trees.\n");
			}

		}
		System.out.println(sb);
	}

}