import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, R;
	static ArrayList<int[]>[] vec = new ArrayList[200020];
	static int dist[] = new int[200020];
	static int childCnt[] = new int[200020];
	static int order[] = new int[200020];
	static int idx = 0;
	static int giga = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 200020; i++) {
			vec[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			vec[a].add(new int[] { b, d });
			vec[b].add(new int[] { a, d });
		}
		dfs(R, -1);

		for (int i = 0; i < N; i++) {
			int cur = order[i];

			if (childCnt[cur] >= 2) {
				giga = cur;
				break;
			}
			if (i == N - 1) {

				giga = cur;
			}
		}

		int mx = 0;
		for (int i = 0; i < N; i++) {
			int cur = order[i];
			if (childCnt[cur] == 0) { // leaf
				mx = Math.max(mx, dist[cur] - dist[giga]);
			}
		}
		System.out.println(dist[giga] + " " + mx);
	}

	static void dfs(int cur, int par) {
		int cnt = 0;
		order[idx++] = cur;
		for (int[] node : vec[cur]) {
			int child = node[0];
			int d = node[1];
			if (child == par)
				continue;
			dist[child] = dist[cur] + d;

			dfs(child, cur);
			cnt++;
		}

		childCnt[cur] = cnt;
	}
}