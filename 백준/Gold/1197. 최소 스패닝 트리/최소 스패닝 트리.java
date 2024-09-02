import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int par[] = new int[100020];
	static ArrayList<Integer[]> vec = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		init();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			vec.add(new Integer[] { c, a, b });

		}

		Collections.sort(vec, (o1, o2) -> o1[0] - o2[0]);
		int ret = 0;
		int cnt = 0;
		for (Integer[] cur : vec) {
			int c = cur[0];
			int a = cur[1];
			int b = cur[2];
			if (find(a) == find(b))
				continue;
			union(a, b);
			ret += c;

			cnt++;
			if (cnt == V - 1)
				break;
		}

		System.out.println(ret);
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