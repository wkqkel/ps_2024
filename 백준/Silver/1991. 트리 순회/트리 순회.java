import java.io.*;
import java.util.*;

public class Main {
	static final int MX = 1005;

	static int child[][] = new int[MX][2];

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			char a = st.nextToken().charAt(0);
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);

			if(b != '.') child[c2i(a)][0] = c2i(b);
			if(c != '.') child[c2i(a)][1] = c2i(c);
		}
		dfs(0, 1);
		System.out.println();
		dfs(0, 2);
		System.out.println();
		dfs(0, 3);
		System.out.println();
	}

	static void dfs(int cur, int o) {
		if (o == 1)
			System.out.print(i2c(cur));
		if (child[cur][0] > 0)
			dfs(child[cur][0], o);
		if (o == 2)
			System.out.print(i2c(cur));
		if (child[cur][1] > 0)
			dfs(child[cur][1], o);
		if (o == 3)
			System.out.print(i2c(cur));
	}

	static int c2i(char c) {
		return c - 'A';
	}

	static char i2c(int c) {
		return (char) (c + 'A');
	}

}