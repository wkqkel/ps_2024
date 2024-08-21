import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char board[][] = new char[102][102];

	static String dfs(int r, int c, int sz) {
		char first = board[r][c];
		boolean isSame = true;

		for (int i = r; i < r + sz; i++) {
			for (int j = c; j < c + sz; j++) {
				if (first != board[i][j])
					isSame = false;
			}
		}

		String ret = "";

		if (isSame) {
			ret += first;
		} else {
			ret += "(";
			int h = sz / 2;
			ret += dfs(r, c, h);
			ret += dfs(r, c + h, h);
			ret += dfs(r + h, c, h);
			ret += dfs(r + h, c + h, h);
			ret += ")";
		}

		return ret;
	}

	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for (int j = 0; j < n; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		System.out.println(dfs(0, 0, n));

	}
}