import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 10000;

	static int vec[][] = new int[MX + 1][2];

	static int x = 1;
	static int posX[] = new int[MX + 1];
	static int minX[] = new int[MX + 1];
	static int maxX[] = new int[MX + 1];
	static int par[] = new int[MX + 1];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			vec[i][0] = -1;
			vec[i][1] = -1;
			par[i] = -1;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			vec[a][0] = b;
			vec[a][1] = c;

			if (b != -1)
				par[b] = a;
			if (c != -1)
				par[c] = a;
		}
		for (int i = 1; i <= N; i++) {
			minX[i] = Integer.MAX_VALUE;
			maxX[i] = Integer.MIN_VALUE;
		}

		int root = 0;
		for (int i = 1; i <= N; i++) {
			if (par[i] == -1) {
				root = i;
			}
		}
		
		recur(root, -1, 1);
		int mxW = 0;
		int mxI = 0;
		for (int i = 1; i <= N; i++) {
			if (minX[i] == Integer.MAX_VALUE)
				continue;

			int width = maxX[i] - minX[i] + 1;

			if (width > mxW) {
				mxW = width;
				mxI = i;
			}
		}

		System.out.println(mxI + " " + mxW);
	}

	static void recur(int cur, int prev, int lv) {

		int lt = vec[cur][0];
		int rt = vec[cur][1];

		if (lt != -1)
			recur(lt, cur, lv + 1);

		posX[cur] = x++;

		minX[lv] = Math.min(minX[lv], posX[cur]);
		maxX[lv] = Math.max(maxX[lv], posX[cur]);

		if (rt != -1)
			recur(rt, cur, lv + 1);
	}

}