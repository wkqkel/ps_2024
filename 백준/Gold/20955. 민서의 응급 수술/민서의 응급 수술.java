import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 100000;
	static int par[] = new int[MX + 1];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int ret = 0;
		setup();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (!union(a, b)) {
				ret++;
			}
		}

		for (int i = 1; i <= N; i++) {
			find(i);
		}

		for (int i = 1; i <= N; i++) {

			if (par[i] == i)
				ret++;
		}

		System.out.println(ret - 1);

	}

	static void setup() {
		for (int i = 0; i < MX + 1; i++) {
			par[i] = i;
		}
	}

	static int find(int a) {
		if (par[a] == a)
			return a;
		return par[a] = find(par[a]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return false;

		par[a] = b;

		return true;
	}
}