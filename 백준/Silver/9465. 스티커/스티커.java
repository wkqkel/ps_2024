import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int arr[][] = new int[N + 5][3];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i][2] = Integer.parseInt(st.nextToken());
			}
			// i번쨰를 j(0미선택, 1선택, 2선택)한 경우
			int dp[][] = new int[N + 5][3];

			for (int i = 1; i <= N; i++) {
				dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
				dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
				dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
			}

			int mx = 0;
			for (int i = 0; i < 3; i++) {
				mx = Math.max(mx, dp[N][i]);
			}
			sb.append(mx).append("\n");
		}

		System.out.println(sb);

	}

}