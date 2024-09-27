import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());

			ArrayList<Integer> indexes[] = new ArrayList[26];

			for (int i = 0; i < 26; i++) {
				indexes[i] = new ArrayList<>();
			}
			int n = str.length();

			for (int i = 0; i < n; i++) {
				indexes[c2i(str.charAt(i))].add(i);
			}

			int mx = -1;
			int mn = 100000;
			for (int i = 0; i < 26; i++) {
				if (indexes[i].size() < K)
					continue;

				for (int j = 0; j <= indexes[i].size() - K; j++) {
					int len = indexes[i].get(j + K - 1) - indexes[i].get(j) + 1;
					mn = Math.min(mn, len);
					mx = Math.max(mx, len);
				}
			}

			if (mx == -1) {
				sb.append(-1).append("\n");
			} else {
				sb.append(mn).append(" ").append(mx).append("\n");
			}

		}
		System.out.println(sb);
	}

	static int c2i(char c) {
		return c - 'a';
	}
}