import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static final int MX = 200020;
	static int par[] = new int[MX];
	static ArrayList<long[]> vec = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			if (V == 0 && E == 0)
				break;
			solution();
		}

		System.out.println(sb);
	}

	static void solution() throws Exception {
		init();
		vec.clear();
		long ret = 0;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			vec.add(new long[] { c, a, b });
			ret += c;
		}

		Collections.sort(vec, (o1, o2) -> Long.compare(o1[0], o2[0]));

		for (long[] cur : vec) {
			long c = cur[0];
			int a = (int) cur[1];
			int b = (int) cur[2];
			if (find(a) == find(b))
				continue;
			union(a, b);
			ret -= c;
		}

		sb.append(ret).append("\n");
	}

	static void init() {
		for (int i = 0; i < MX; i++) {
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