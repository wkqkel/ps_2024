import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 100000;
	static int par[] = new int[MX + 1];
	static List<Integer> vec[] = new List[MX + 1];

	static int score[] = new int[MX + 1];
	static int ret[] = new int[MX + 1];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < MX + 1; i++) {
			vec[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1)
				continue;
			vec[p].add(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			score[a] += w;
		}

		recur(1, -1, 0);
		for (int i = 1; i <= N; i++) {
			sb.append(ret[i]).append(" ");
		}
		System.out.println(sb);
	}

	static void recur(int cur, int prev, int v) {
		v += score[cur];
		ret[cur] = v;
		for (int child : vec[cur]) {
			if (child == prev)
				continue;
			recur(child, cur, v);
		}
	}

}